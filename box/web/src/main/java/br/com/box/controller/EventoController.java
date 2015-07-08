package br.com.box.controller;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import org.jboss.logging.Logger;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import br.com.box.exception.AgendamentoNaoDisponivelException;
import br.com.box.exception.DataInicioMaiorDataFimException;
import br.com.box.facade.AgendaFacade;
import br.com.box.form.EventoForm;
import br.com.box.interceptor.InterceptadorExcecaoController;
import br.com.box.model.Agenda;
import br.com.box.model.Pessoa;
import br.com.box.model.Recurso;
import br.com.box.service.AgendaService;
import br.com.box.service.PessoaService;
import br.com.box.service.RecursoService;
import br.com.box.util.DataUtil;
import br.com.box.util.FacesUtil;

import com.google.common.collect.Iterables;

@ManagedBean
@ViewScoped
@Interceptors(InterceptadorExcecaoController.class)
public class EventoController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EventoForm eventoForm;

    @Inject
    private AgendaService agendaService;

    @Inject
    private RecursoService recursoService;

    @Inject
    private PessoaService pessoaService;

    @Inject
    private AgendaFacade agendaFacade;

    private static final Logger LOG = Logger.getLogger(EventoController.class);

    private DefaultScheduleEvent event = new DefaultScheduleEvent();

    private boolean novoRegistro;
    private static final Integer ZERO = 0;

    public ScheduleModel getEventos() {

        if (Iterables.isEmpty(eventoForm.getEventModel().getEvents())) {
            preencherAgenda(false);
        }

        return eventoForm.getEventModel();
    }

    public List<Agenda> getAgendas() {
        if (Iterables.isEmpty(eventoForm.getListaAgendas())) {
            atualizarListaAgendas();
        }
        return eventoForm.getListaAgendas();
    }

    private void atualizarListaAgendas() {
        eventoForm.setListaAgendas(agendaService.listar());
    }

    public Date getInitialDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY, calendar.get(Calendar.DATE), 0, 0, 0);

        return calendar.getTime();
    }

    public void gravar(ActionEvent actionEvent) {
        try {
            Agenda agenda = (Agenda) event.getData();
            acrescentarNotificacao(agenda);
            agendaFacade.salvarAgenda(agenda);
            preencherAgenda(true);
            FacesUtil.mostrarMensagemSucesso("cadastro.sucesso");
        } catch (AgendamentoNaoDisponivelException e) {
            LOG.error(e);
            FacesUtil.mostrarMensagemAlerta("erro.existe.evento.para.este.recurso");
        } catch (DataInicioMaiorDataFimException e) {
            FacesUtil.mostrarMensagemAlerta("erro.datafim.maior.datainicio");
            LOG.error(e);
        }
    }

    public void onEventSelect(SelectEvent selectEvent) {
        event = (DefaultScheduleEvent) selectEvent.getObject();
        setNovoRegistro(true);
    }

    public void apagar(ActionEvent actionEvent) {
        agendaService.deletar((Agenda) event.getData());
        preencherAgenda(true);
        FacesUtil.mostrarMensagemSucesso("cadastro.sucesso");
    }

    public void onDateSelect(SelectEvent selectEvent) {
        Agenda agenda = new Agenda();
        agenda.setDataInicio((Date) selectEvent.getObject());
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject(), agenda);
    }

    public void onEventMove(ScheduleEntryMoveEvent event) {
        try {
            Agenda agenda = (Agenda) event.getScheduleEvent().getData();
            agendaFacade.salvarAgenda(agenda);
            preencherAgenda(false);
            String mensagemEvento = "Dia:" + event.getDayDelta() + ", Minutos:" + event.getMinuteDelta();
            FacesUtil.mostrarMensagem(FacesMessage.SEVERITY_INFO, "Evento movido", mensagemEvento);
        } catch (AgendamentoNaoDisponivelException e) {
            FacesUtil.mostrarMensagemAlerta("Não foi possivel alterar este evento");
            LOG.error(e);
        } catch (DataInicioMaiorDataFimException e) {
            FacesUtil.mostrarMensagemAlerta("erro.datafim.maior.datainicio");
            LOG.error(e);
        }
    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        try {
            Agenda agenda = (Agenda) event.getScheduleEvent().getData();
            agendaFacade.salvarAgenda(agenda);
            preencherAgenda(false);
            String mensagemEvento = "Dia:" + event.getDayDelta() + ", Minutos:" + event.getMinuteDelta();
            FacesUtil.mostrarMensagem(FacesMessage.SEVERITY_INFO, "Evento Ajustado", mensagemEvento);
        } catch (Exception e) {
            FacesUtil.mostrarMensagemAlerta("Não foi possivel alterar este evento");
            LOG.error(e);
        }
    }

    public List<Recurso> getRecursos() {
        if (Iterables.isEmpty(eventoForm.getListaRecursos())) {
            eventoForm.setListaRecursos(recursoService.recuperarTodos());
        }
        return eventoForm.getListaRecursos();
    }

    public List<Pessoa> getPessoas() {
        if (Iterables.isEmpty(eventoForm.getListaPessoas())) {
            eventoForm.setListaPessoas(pessoaService.recuperarTodos());
        }
        return eventoForm.getListaPessoas();
    }

    public void filtrarAgenda() {
        eventoForm.setListaAgendas(agendaService.filtrarAgenda(eventoForm.getAgendaFiltro()));
        if (Iterables.isEmpty(eventoForm.getListaAgendas())) {
            FacesUtil.mostrarMensagemAlerta("erro.mensagem.buscarPorFiltro");
        }
        preencherAgenda(false);
    }

    private void acrescentarNotificacao(Agenda agenda) {
        if (ZERO.intValue() != eventoForm.getNotificacaoMinutos().intValue()) {
            Date dataNotificacao = DataUtil.subtrairMinutos(agenda.getDataInicio(), eventoForm.getNotificacaoMinutos());
            agenda.setDataNotificacao(dataNotificacao);
        }
    }

    private void preencherAgenda(boolean limparListaAgendas) {
        eventoForm.getEventModel().clear();
        if (limparListaAgendas) {
            eventoForm.getListaAgendas().clear();
        }
        for (Agenda eventoAgenda : this.getAgendas()) {
            event = new DefaultScheduleEvent(eventoAgenda.getLabel(), eventoAgenda.getDataInicio(), eventoAgenda.getDataFim(), eventoAgenda);
            event.setStyleClass(eventoAgenda.getCorEvento());
            eventoForm.getEventModel().addEvent(event);
        }
    }

    public EventoForm getEventoForm() {
        return eventoForm;
    }

    public void setEventoForm(EventoForm eventoForm) {
        this.eventoForm = eventoForm;
    }

    public boolean isNovoRegistro() {
        return novoRegistro;
    }

    public void setNovoRegistro(boolean novoRegistro) {
        this.novoRegistro = novoRegistro;
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(DefaultScheduleEvent event) {
        this.event = event;
    }

}
