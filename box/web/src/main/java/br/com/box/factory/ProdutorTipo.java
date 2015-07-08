package br.com.box.factory;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import br.com.box.model.TipoRecurso;
import br.com.box.qualifier.TipoBean;
import br.com.box.service.TipoService;
import br.com.box.util.FacesUtil;

import com.google.common.base.Strings;

public class ProdutorTipo {

    @Inject
    private TipoService tipoService;

    @Produces
    @TipoBean
    public TipoRecurso produzirTipo() {
        TipoRecurso tipo = new TipoRecurso();
        String id = FacesUtil.getRequestParameter("idTipo");
        if (!Strings.isNullOrEmpty(id)) {
            Long idLong = new Long(id);
            return tipoService.buscarPorId(idLong);
        }
        return tipo;
    }
}
