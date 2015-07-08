package br.com.box.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import org.primefaces.model.DualListModel;

import br.com.box.form.UsuarioForm;
import br.com.box.interceptor.InterceptadorExcecaoController;
import br.com.box.model.Grupo;
import br.com.box.model.Pessoa;
import br.com.box.model.Usuario;
import br.com.box.service.GrupoService;
import br.com.box.service.PessoaService;
import br.com.box.service.UsuarioService;
import br.com.box.util.FacesUtil;

import com.google.common.collect.Iterables;

@ManagedBean
@ViewScoped
@Interceptors(InterceptadorExcecaoController.class)
public class UsuarioController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private UsuarioForm usuarioForm;

    @Inject
    private UsuarioService usuarioService;

    @Inject
    private PessoaService pessoaService;

    private static final String URLCONSULTA = "/pages/protected/usuario/consultar.jsf";

    @Inject
    private GrupoService grupoService;

    public List<Pessoa> getPessoas() {
        if (Iterables.isEmpty(usuarioForm.getPessoas())) {
            usuarioForm.setPessoas(pessoaService.recuperarTodos());
        }
        return usuarioForm.getPessoas();
    }

    public List<Usuario> getUsuarios() {
        if (Iterables.isEmpty(usuarioForm.getUsuarios())) {
            this.atualizarListaUsuarios();
        }
        return usuarioForm.getUsuarios();
    }

    public String gravar() {
        associarGrupoUsuario();

        usuarioService.gravar(usuarioForm.getUsuario());
        FacesUtil.mostrarMensagemSucesso("cadastro.sucesso");
        return URLCONSULTA + "?faces-redirect=true";
    }

    public List<String> completarTexto(String query) {
        List<String> results = new ArrayList<String>();
        for (Usuario usuario : usuarioForm.getUsuarios()) {
            if (usuario.getLogin().contains(query)) {
                results.add(usuario.getLogin());
            }
        }
        return results;
    }

    public void buscarPorFiltro() {
        List<Usuario> usuarios = usuarioService.buscarPorFiltro(usuarioForm.getFiltro());
        if (Iterables.isEmpty(usuarios)) {
            FacesUtil.mostrarMensagemErro("erro.menssagem.buscarPorFiltro");
        }
        usuarioForm.setUsuarios(usuarios);
    }

    public String alterar() {
        associarGrupoUsuario();
        usuarioService.alterar(usuarioForm.getUsuario());
        FacesUtil.mostrarMensagemSucesso("cadastro.sucesso");
        return URLCONSULTA + "?faces-redirect=true";
    }

    public void deletar(Usuario usuario) {
        usuarioService.deletar(usuario);
        FacesUtil.mostrarMensagemSucesso("deletado.sucesso");
        this.atualizarListaUsuarios();
    }

    private void atualizarListaUsuarios() {
        usuarioForm.setUsuarios(usuarioService.recuperarTodos());
    }

    public UsuarioForm getUsuarioForm() {
        return usuarioForm;
    }

    public void setUsuarioForm(UsuarioForm usuarioForm) {
        this.usuarioForm = usuarioForm;
    }

    public void preparaFormulario() {
        usuarioForm.setGrupoModel(organizarGrupos());
    }

    public DualListModel<Grupo> organizarGrupos() {
        List<Grupo> gruposDisponiveis = grupoService.recuperarTodos();
        List<Grupo> gruposAssociados = usuarioForm.getUsuario().getGrupos();
        if (gruposAssociados != null) {
            gruposDisponiveis.removeAll(gruposAssociados);
        } else {
            gruposAssociados = new ArrayList<Grupo>();
        }
        return new DualListModel<Grupo>(gruposDisponiveis, gruposAssociados);
    }

    public void associarGrupoUsuario() {
        usuarioForm.getUsuario().setGrupos(usuarioForm.getGrupoModel().getTarget());
    }

}
