package br.com.box.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.box.model.Pessoa;

@Stateless
public class PessoaDAO extends GenericDAO<Pessoa> {

    @SuppressWarnings("unchecked")
    public List<Pessoa> buscarPorEmail(String email) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT p ");
        sb.append("FROM Pessoa p ");
        sb.append("JOIN FETCH p.emails e ");
        sb.append("WHERE e.email = :email");

        Query query = criarQuery(sb.toString());
        query.setParameter("email", email);

        try {
            return (List<Pessoa>) query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
