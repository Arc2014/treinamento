package br.com.box.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.box.dao.PessoaDAO;
import br.com.box.dao.UsuarioDAO;
import br.com.box.model.Usuario;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

@Named
@Stateless
public class UsuarioService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private UsuarioDAO usuarioDao;

    @Inject
    private PessoaDAO pessoaDao;

    public void gravar(Usuario usuario) {
        this.prepararUsuario(usuario);
        usuarioDao.salvar(usuario);
    }

    public void alterar(Usuario usuario) {
        this.prepararUsuario(usuario);
        usuarioDao.atualizar(usuario);
    }

    public List<Usuario> recuperarTodos() {
        return usuarioDao.listar();
    }

    public Usuario buscarPorLogin(String login) {
        return usuarioDao.buscarPorLogin(login);
    }

    public List<Usuario> buscarPorFiltro(String filtro) {
        return usuarioDao.listarPorLike("login", filtro.trim());
    }

    public void deletar(Usuario usuario) {
        usuarioDao.delete(usuario);
    }

    public Usuario buscarPorId(Long id) {
        return usuarioDao.buscar(id);

    }

    private void prepararUsuario(Usuario usuario) {
        usuario.criptografarSenha();
        usuario.setPessoa(pessoaDao.attach(usuario.getPessoa()));
    }

    public Usuario obterUsuario(String login) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(login));
        return usuarioDao.buscarPorLogin(login);
    }

}
