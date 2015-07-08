package br.com.box.dao;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.box.model.Usuario;

@Stateless
public class UsuarioDAO extends GenericDAO<Usuario> {

    public Usuario buscarPorLogin(String login) {
        return buscarPor("login", login);
    }

    public Usuario recuperarUsuario(Usuario usuario) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DISTINCT (u) ");
        sb.append("FROM Usuario u ");
        sb.append("LEFT JOIN FETCH u.pessoa pes ");
        sb.append("LEFT JOIN FETCH u.grupos g ");
        sb.append("LEFT JOIN FETCH g.permissoes p ");
        sb.append("WHERE u.login = :login");
        sb.append(" AND ");
        sb.append("u.senha = :senha");

        Query query = criarQuery(sb.toString());
        query.setParameter("login", usuario.getLogin());
        query.setParameter("senha", usuario.getSenhaCriptografada());

        try {
            return (Usuario) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
