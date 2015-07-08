package br.com.schedule;

import java.io.IOException;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import br.com.box.model.Notificacao;
import br.com.box.service.EmailService;
import br.com.box.service.NotificacaoService;
import br.com.box.util.PropriedadesUtil;

@Singleton
public class EmailSchedule {

    @Inject
    private NotificacaoService notificacaoService;

    @Inject
    private EmailService emailService;

    private static final  Logger LOGGER = Logger.getLogger(EmailService.class);

    @Schedule(hour = "*", minute = "*", persistent = false)
    public void executarTarefa() {
        try {
            if (scheduleHabilitado()) {
                this.enviarEmails();
            }
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

    private void enviarEmails() {
        for (Notificacao notificacao : notificacaoService.notificacoesPendentes()) {
            try {
                emailService.enviarEmailNotificacao(notificacao);
                emailService.atualizarNotificacaoEnviada(notificacao);
            } catch (Exception e) {
                LOGGER.error(e);
            }
        }
    }

    private boolean scheduleHabilitado() throws IOException {
        return PropriedadesUtil.getPropertyAsBoolean("habilitar.schedule");
    }

}
