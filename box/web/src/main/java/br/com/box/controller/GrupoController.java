package br.com.box.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import br.com.box.form.GrupoForm;
import br.com.box.interceptor.InterceptadorExcecaoController;
import br.com.box.model.Grupo;
import br.com.box.service.GrupoService;
import br.com.box.util.FacesUtil;

import com.google.common.collect.Iterables;

@ManagedBean
@ViewScoped
@Interceptors(InterceptadorExcecaoController.class)
public class GrupoController implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String URLCONSULTA = "/pages/protected/grupo/consultar.jsf";

    @Inject
    private GrupoService grupoService;

    @Inject
    private GrupoForm grupoForm;

    public String gravar() {
        grupoService.gravar(grupoForm.getGrupo());
        FacesUtil.mostrarMensagemSucesso("cadastro.sucesso");
        return URLCONSULTA + "?faces-redirect=true";
    }

    public String alterar() {
        grupoService.alterar(grupoForm.getGrupo());
        FacesUtil.mostrarMensagemSucesso("cadastro.sucesso");
        return URLCONSULTA + "?faces-redirect=true";
    }

    public List<Grupo> getGrupos() {
        if (Iterables.isEmpty(grupoForm.getGrupos())) {
            this.atualizarListaGrupo();
        }
        return grupoForm.getGrupos();
    }

    private void atualizarListaGrupo() {
        grupoForm.setGrupos(grupoService.recuperarTodos());
    }

    public void deletar(Grupo grupo) {
        grupoService.deletar(grupo);
        FacesUtil.mostrarMensagemSucesso("deletado.sucesso");
        this.atualizarListaGrupo();
    }

    public GrupoForm getGrupoForm() {
        return grupoForm;
    }

    public void setGrupoForm(GrupoForm grupoForm) {
        this.grupoForm = grupoForm;
    }

}
