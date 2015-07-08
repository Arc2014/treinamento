package br.com.box.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.box.model.Recurso;
import br.com.box.util.Util;

import com.google.common.base.Strings;

@SuppressWarnings("unchecked")
@Stateless
public class RecursoDAO extends GenericDAO<Recurso> {

    public List<Recurso> listarAtivos() {
        Query query = getEntityManager().createQuery("SELECT r FROM Recurso r WHERE r.ativo = 1");
        return (List<Recurso>) query.getResultList();
    }

    public List<Recurso> buscarRecursosPorFiltros(Recurso recursoFiltro) {
        StringBuilder str = new StringBuilder("SELECT r FROM Recurso r WHERE 1=1 ");

        if (!Strings.isNullOrEmpty(recursoFiltro.getNumeroPatrimonio())) {
            str.append(" AND LOWER(r.numeroPatrimonio) LIKE :numeroPatrimonio");
        }

        if (!Util.objectIsNull(recursoFiltro.getAtivo())) {
            str.append(" AND r.ativo = :ativo");
        }

        Query query = getEntityManager().createQuery(str.toString());
        if (!Strings.isNullOrEmpty(recursoFiltro.getNumeroPatrimonio())) {
            query.setParameter("numeroPatrimonio", "%" + recursoFiltro.getNumeroPatrimonio().toLowerCase() + "%");
        }
        if (!Util.objectIsNull(recursoFiltro.getNumeroPatrimonio())) {
            query.setParameter("ativo", recursoFiltro.getAtivo());
        }

        return query.getResultList();
    }

}
