package br.com.box.facade;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.box.exception.AgendamentoNaoDisponivelException;
import br.com.box.exception.DataInicioMaiorDataFimException;
import br.com.box.model.Agenda;
import br.com.box.service.AgendaService;
import br.com.box.service.NotificacaoService;

@Stateless
public class AgendaFacade {

    @Inject
    private AgendaService agendaService;

    @Inject
    private NotificacaoService notificacacaoService;

    public void salvarAgenda(Agenda agenda) throws AgendamentoNaoDisponivelException, DataInicioMaiorDataFimException {
        agendaService.salvar(agenda);
        notificacacaoService.gerarNotificacaoEventoAgenda(agenda);
    }

}
