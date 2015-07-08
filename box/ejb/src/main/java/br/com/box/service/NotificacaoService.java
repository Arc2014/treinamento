package br.com.box.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.box.dao.NotificacaoDAO;
import br.com.box.model.Agenda;
import br.com.box.model.Notificacao;

@Named
@Stateless
public class NotificacaoService implements Serializable {

    private static final long serialVersionUID = -656264840285792744L;

    @Inject
    private NotificacaoDAO notificacaoDAO;

    @Inject
    private TemplateService templateService;

    public void gerarNotificacaoEventoAgenda(Agenda agenda) {
        Notificacao notificacao = new Notificacao();
        notificacao.setDataNotificar(agenda.getDataNotificacao());
        // notificacao.setDestinatario(agenda.getPessoa().getEmail());
        notificacao.setCorpo(templateService.gerarCorpoEmailNotificacaoAgendamento(agenda));
        notificacao.setTipo("reserva");
        notificacao.setTitulo(agenda.getTituloEvento());
        notificacaoDAO.salvar(notificacao);
    }

    public List<Notificacao> notificacoesPendentes() {
        return notificacaoDAO.notificacoesPendentes();
    }

    public Notificacao buscarPorId(Long id) {
        return notificacaoDAO.buscar(id);
    }

    public void atualizar(Notificacao notificacao) {
        notificacaoDAO.atualizar(notificacao);
    }

}
