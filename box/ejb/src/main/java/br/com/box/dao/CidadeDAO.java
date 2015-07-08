package br.com.box.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.box.model.Cidade;

public class CidadeDAO extends GenericDAO<Cidade> implements Serializable {

    private static final long serialVersionUID = -5828854152854362939L;

    @SuppressWarnings("unchecked")
    public List<Cidade> recuperarCidades(String uf) {
        List<Cidade> cidades = null;
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT (c) ");
        sb.append("FROM Cidade c ");
        sb.append("WHERE c.uf = :uf");

        Query query = criarQuery(sb.toString());
        query.setParameter("uf", uf);

        try {
            cidades = query.getResultList();
            return cidades;
        } catch (NoResultException e) {
            return cidades;
        }
    }

}
