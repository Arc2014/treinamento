package br.com.box.factory;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import br.com.box.model.Agenda;
import br.com.box.qualifier.AgendaBean;
import br.com.box.service.AgendaService;
import br.com.box.util.FacesUtil;

import com.google.common.base.Strings;

public class ProdutorAgenda {

    @Inject
    private AgendaService agendaService;

    @Produces
    @AgendaBean
    public Agenda produzirAgenda() {
        Agenda agenda = new Agenda();
        String id = FacesUtil.getRequestParameter("idAgenda");
        if (!Strings.isNullOrEmpty(id)) {
            Long idLong = new Long(id);
            agenda = agendaService.buscarPorId(idLong);
        }
        return agenda;
    }
}
