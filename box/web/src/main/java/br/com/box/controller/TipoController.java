package br.com.box.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import br.com.box.enuns.Cor;
import br.com.box.form.TipoForm;
import br.com.box.interceptor.InterceptadorExcecaoController;
import br.com.box.model.TipoRecurso;
import br.com.box.service.TipoService;
import br.com.box.util.FacesUtil;

import com.google.common.collect.Iterables;

@ManagedBean
@ViewScoped
@Interceptors(InterceptadorExcecaoController.class)
public class TipoController implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String URLCONSULTA = "/pages/protected/tipo/consultar.jsf";

    @Inject
    private TipoService tipoService;

    @Inject
    private TipoForm tipoForm;

    public List<TipoRecurso> getTipos() {
        if (Iterables.isEmpty(tipoForm.getListaTipos())) {
            atualizarListaTipos();
        }
        return tipoForm.getListaTipos();
    }

    public String gravar() {
        tipoService.gravar(tipoForm.getTipo());
        FacesUtil.mostrarMensagemSucesso("cadastro.sucesso");
        return URLCONSULTA + "?faces-redirect=true";
    }

    public String alterar() {
        tipoService.alterar(tipoForm.getTipo());
        FacesUtil.mostrarMensagemSucesso("cadastro.sucesso");
        return URLCONSULTA + "?faces-redirect=true";
    }

    public void deletar(TipoRecurso tipo) {
        if (tipoService.deletar(tipo)) {
            FacesUtil.mostrarMensagemSucesso("deletado.sucesso");
            atualizarListaTipos();
        } else {
            FacesUtil.mostrarMensagemAlerta("erro.mensagem.deletar.registro.associado");
        }
    }

    private void atualizarListaTipos() {
        tipoForm.setListaTipos(tipoService.listar());
    }

    public TipoForm getTipoForm() {
        return tipoForm;
    }

    public void setTipoForm(TipoForm tipoForm) {
        this.tipoForm = tipoForm;
    }

    public Cor[] getCores() {
        return Cor.values();
    }

}
