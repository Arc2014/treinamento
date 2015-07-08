package br.com.box.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DualListModel;

import br.com.box.model.Grupo;
import br.com.box.model.Pessoa;
import br.com.box.model.Usuario;
import br.com.box.qualifier.UsuarioBean;

@Named
public class UsuarioForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    @UsuarioBean
    private Usuario usuario;

    private List<Usuario> usuarios = new ArrayList<Usuario>();
    private String filtro;
    private List<Pessoa> pessoas = new ArrayList<Pessoa>();
    private boolean atualizar = false;
    private String confirmaSenha;
    private DualListModel<Grupo> grupoModel;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> listaUsuario) {
        this.usuarios = listaUsuario;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> listaPessoa) {
        this.pessoas = listaPessoa;
    }

    public boolean isAtualizar() {
        return atualizar;
    }

    public void setAtualizar(boolean atualizar) {
        this.atualizar = atualizar;
    }

    public DualListModel<Grupo> getGrupoModel() {
        return grupoModel;
    }

    public void setGrupoModel(DualListModel<Grupo> grupoModel) {
        this.grupoModel = grupoModel;
    }

}
