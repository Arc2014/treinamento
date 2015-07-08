package br.com.box.factory;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import br.com.box.model.Recurso;
import br.com.box.qualifier.RecursoBean;
import br.com.box.service.RecursoService;
import br.com.box.util.FacesUtil;

import com.google.common.base.Strings;

public class ProdutorRecurso {

    @Inject
    private RecursoService recursoService;

    @Produces
    @RecursoBean
    public Recurso produzirRecurso() {
        Recurso recurso = new Recurso();
        recurso.setAtivo(Boolean.TRUE);
        String id = FacesUtil.getRequestParameter("idRecurso");
        if (!Strings.isNullOrEmpty(id)) {
            Long idLong = new Long(id);
            recurso = recursoService.buscarPorId(idLong);
        }
        return recurso;
    }

}
