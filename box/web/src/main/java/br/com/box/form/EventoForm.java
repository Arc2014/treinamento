package br.com.box.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

import br.com.box.model.Agenda;
import br.com.box.model.Pessoa;
import br.com.box.model.Recurso;
import br.com.box.qualifier.AgendaBean;

@Named
public class EventoForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private ScheduleModel eventModel = new DefaultScheduleModel();

    @Inject
    @AgendaBean
    private Agenda agenda;

    private List<Agenda> listaAgendas = new ArrayList<Agenda>();

    private Agenda agendaFiltro = new Agenda();

    private List<Recurso> listaRecursos = new ArrayList<Recurso>();

    private List<Pessoa> listaPessoas = new ArrayList<Pessoa>();

    private Integer notificacaoMinutos;

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public List<Agenda> getListaAgendas() {
        return listaAgendas;
    }

    public void setListaAgendas(List<Agenda> listaAgendas) {
        this.listaAgendas = listaAgendas;
    }

    public Agenda getAgendaFiltro() {
        return agendaFiltro;
    }

    public void setAgendaFiltro(Agenda agendaFiltro) {
        this.agendaFiltro = agendaFiltro;
    }

    public List<Recurso> getListaRecursos() {
        return listaRecursos;
    }

    public void setListaRecursos(List<Recurso> listaRecursos) {
        this.listaRecursos = listaRecursos;
    }

    public List<Pessoa> getListaPessoas() {
        return listaPessoas;
    }

    public void setListaPessoas(List<Pessoa> listaPessoas) {
        this.listaPessoas = listaPessoas;
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    public Integer getNotificacaoMinutos() {
        return notificacaoMinutos;
    }

    public void setNotificacaoMinutos(Integer notificacaoMinutos) {
        this.notificacaoMinutos = notificacaoMinutos;
    }

}
