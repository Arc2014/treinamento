package br.com.box.service;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Named;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.jboss.logging.Logger;

import br.com.box.model.Agenda;

@Named
@Stateless
public class TemplateService {

    private static final String CAMINHO_TEMPLATE = "/template/";
    private static final String CHARSET_ENCODING = "UTF-8";
    private static final Logger LOGGER = Logger.getLogger(TemplateService.class);

    public String gerarCorpoEmailNotificacaoAgendamento(Agenda agenda) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("agenda", agenda);
        return gerarTemplate(parametros, "email.vm");
    }

    private String gerarTemplate(Map<String, Object> parametros, final String nomeTemplate) {
        try {
            VelocityEngine ve = new VelocityEngine();
            ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
            ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
            ve.init();
            String caminhoTemplate = CAMINHO_TEMPLATE + nomeTemplate;
            Template t = ve.getTemplate(caminhoTemplate, CHARSET_ENCODING);
            VelocityContext context = new VelocityContext(parametros);
            StringWriter writer = new StringWriter();
            t.merge(context, writer);
            return writer.toString();
        } catch (Exception e) {
        	LOGGER.error(e);
            throw new RuntimeException("Erro na geração do template de Email", e);
        }
    }

}
