package br.com.box.dao;

import java.io.PrintStream;
import java.util.List;

import javax.persistence.Query;

import br.com.box.model.Agenda;
import br.com.box.util.Util;

import com.google.common.base.Strings;

public class AgendaDAO extends GenericDAO<Agenda> {

    public List<Agenda> buscarAgendasPorFiltros(Agenda agendaFiltro) {
        StringBuilder str = new StringBuilder(
                "SELECT a FROM Agenda a LEFT JOIN FETCH a.pessoa p LEFT JOIN FETCH a.usuario u "
                        + "LEFT JOIN FETCH a.recurso r LEFT JOIN FETCH r.tipoRecurso WHERE 1=1 ");
        if (!Util.objectIsNull(agendaFiltro.getDataInicio())) {
            str.append(" AND a.dataInicio >= :dataInicio");
        }
        if (!Util.objectIsNull(agendaFiltro.getDataFim())) {
            str.append(" AND a.dataFim <= :dataFim");
        }
        if (!Util.objectIsNull(agendaFiltro.getRecurso())
                && !Util.objectIsNull(agendaFiltro.getRecurso().getId())) {
            str.append(" AND a.recurso.id = :idRecurso");
        }
        if (!Util.objectIsNull(agendaFiltro.getPessoa())
                && !Util.objectIsNull(agendaFiltro.getPessoa().getId())) {
            str.append(" AND a.pessoa.id = :idPessoa");
        }
        if (!Strings.isNullOrEmpty(agendaFiltro.getTituloEvento())) {
            str.append(" AND LOWER(a.tituloEvento) LIKE :titulo");
        }
        Query query = getEntityManager().createQuery(str.toString());
        query = setParameters(query, agendaFiltro);

        return query.getResultList();
    }

    public List<Agenda> buscarAgendasPorRecursoDataAgendamento(Agenda agenda) {

        String hql = "SELECT a FROM Agenda a "
                + "WHERE a.recurso.id = :idRecurso "
                + "AND ((a.dataInicio BETWEEN :dataInicio "
                + "AND :dataFim) OR (a.dataFim BETWEEN :dataInicio "
                + "AND :dataFim)) ";
        if (!Util.objectIsNull(agenda.getId())) {
            hql += "AND a.id != :idAgenda ";
        }
        Query query = getEntityManager().createQuery(hql);
        query = setParameters(query, montarAgendaParaFiltro(agenda));
        return query.getResultList();
    }

    private Query setParameters(Query query, Agenda agendaFiltro) {
        if (!Util.objectIsNull(agendaFiltro.getId())) {
            query.setParameter("idAgenda", agendaFiltro.getId());
        }
        if (!Util.objectIsNull(agendaFiltro.getDataInicio())) {
            query.setParameter("dataInicio", agendaFiltro.getDataInicio());
        }
        if (!Util.objectIsNull(agendaFiltro.getDataFim())) {
            query.setParameter("dataFim", agendaFiltro.getDataFim());
        }
        if (!Util.objectIsNull(agendaFiltro.getRecurso())
                && !Util.objectIsNull(agendaFiltro.getRecurso().getId())) {
            query.setParameter("idRecurso", agendaFiltro.getRecurso().getId());
        }
        if (!Util.objectIsNull(agendaFiltro.getPessoa())
                && !Util.objectIsNull(agendaFiltro.getPessoa().getId())) {
            query.setParameter("idPessoa", agendaFiltro.getPessoa().getId());
        }
        if (!Strings.isNullOrEmpty(agendaFiltro.getTituloEvento())) {
            query.setParameter("titulo", "%"
                    + agendaFiltro.getTituloEvento().toLowerCase() + "%");
        }
        return query;
    }

    private Agenda montarAgendaParaFiltro(Agenda agenda) {
        Agenda a2 = new Agenda();
        a2.setDataInicio(agenda.getDataInicio());
        a2.setDataFim(agenda.getDataFim());
        a2.setRecurso(agenda.getRecurso());
        a2.setId(agenda.getId());

        return a2;
    }

    public List<Agenda> listarAgendasCompleta() {
        StringBuilder str = new StringBuilder(
                "SELECT a FROM Agenda a "
                        + "LEFT JOIN FETCH a.pessoa p LEFT JOIN FETCH a.usuario u LEFT JOIN FETCH a.recurso r LEFT JOIN FETCH r.tipoRecurso");
        Query query = getEntityManager().createQuery(str.toString());
        return query.getResultList();
    }

    public boolean testeInjecao(PrintStream io, String mensagem) {
        io.println(mensagem);
        return true;
    }

}
