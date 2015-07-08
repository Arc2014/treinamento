package br.com.box.service;

import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.com.box.model.Notificacao;

@Stateless
public class EmailService {

    @Inject
    private NotificacaoService notificacaoService;

    @Resource(mappedName = "java:jboss/mail/box")
    private Session mailSession;

    public void enviarEmailNotificacao(Notificacao notificacao) throws MessagingException {
        MimeMessage mail = gerarMensagemNotificacao(notificacao);
        Transport.send(mail);
    }

    private MimeMessage gerarMensagemNotificacao(Notificacao notificacao) throws MessagingException {
        MimeMessage mail = new MimeMessage(mailSession);
        Address to = new InternetAddress(notificacao.getDestinatario());
        mail.setRecipient(Message.RecipientType.TO, to);
        mail.setSubject("Cadastro");
        mail.setSentDate(new Date());
        mail.setContent(notificacao.getCorpo(), "text/html");
        return mail;
    }

    public void atualizarNotificacaoEnviada(Notificacao notificacao) {
        notificacao.setDataEnvio(new Date());
        notificacaoService.atualizar(notificacao);
    }

}
