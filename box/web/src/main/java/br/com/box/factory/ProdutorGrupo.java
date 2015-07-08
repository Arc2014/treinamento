package br.com.box.factory;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import br.com.box.model.Grupo;
import br.com.box.qualifier.GrupoBean;
import br.com.box.service.GrupoService;
import br.com.box.util.FacesUtil;

import com.google.common.base.Strings;

public class ProdutorGrupo {

    @Inject
    private GrupoService grupoService;

    @Produces
    @GrupoBean
    public Grupo produzirGrupo() {
        Grupo grupo = new Grupo();
        String id = FacesUtil.getRequestParameter("idGrupo");
        if (!Strings.isNullOrEmpty(id)) {
            Long idLong = new Long(id);
            grupo = grupoService.buscarPorId(idLong);
        }
        return grupo;
    }

}
