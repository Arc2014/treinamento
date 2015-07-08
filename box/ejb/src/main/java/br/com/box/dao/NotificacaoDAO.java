package br.com.box.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.box.model.Notificacao;

@Stateless
public class NotificacaoDAO extends GenericDAO<Notificacao> {

    public List<Notificacao> notificacoesPendentes() {
        String hql = "SELECT n FROM Notificacao n WHERE n.dataEnvio is null"
                + " AND n.dataNotificar <= (NOW())";
        Query query = getEntityManager().createQuery(hql);
        return query.getResultList();
    }
}
