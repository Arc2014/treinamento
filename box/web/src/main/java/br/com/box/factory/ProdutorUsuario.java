package br.com.box.factory;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import br.com.box.model.Usuario;
import br.com.box.qualifier.UsuarioBean;
import br.com.box.service.UsuarioService;
import br.com.box.util.FacesUtil;

import com.google.common.base.Strings;

public class ProdutorUsuario {

    @Inject
    private UsuarioService usuarioService;

    @Produces
    @UsuarioBean
    public Usuario produzirUsuario() {
        Usuario usuario = new Usuario();
        String id = FacesUtil.getRequestParameter("idUsuario");
        if (!Strings.isNullOrEmpty(id)) {
            Long idLong = new Long(id);
            usuario = usuarioService.buscarPorId(idLong);
        }
        return usuario;
    }

}
