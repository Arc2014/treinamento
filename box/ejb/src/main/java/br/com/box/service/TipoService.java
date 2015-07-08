package br.com.box.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.box.dao.RecursoDAO;
import br.com.box.dao.TipoDAO;
import br.com.box.model.Recurso;
import br.com.box.model.TipoRecurso;
import br.com.box.util.Util;

import com.google.common.base.Preconditions;

@Named
@Stateless
public class TipoService {

    @Inject
    private TipoDAO tipoDAO;

    @Inject
    private RecursoDAO recursoDAO;

    public List<TipoRecurso> listar() {
        return tipoDAO.listar();
    }

    public TipoRecurso buscarPorId(Long idLong) {
        return tipoDAO.buscar(idLong);
    }

    public boolean deletar(TipoRecurso tipo) {
        if (!possuiRelacionamento(tipo)) {
            tipoDAO.delete(tipo);
            return true;
        }
        return false;
    }

    public TipoRecurso buscarPorNome(String nome) {
        Preconditions.checkArgument(!Util.objectIsNull(nome), "Argumento nome não pode ser nulo");
        return tipoDAO.buscarPorNome(nome);
    }

    public void gravar(TipoRecurso tipo) {
        tipoDAO.salvar(tipo);
    }

    public void alterar(TipoRecurso tipo) {
        tipoDAO.atualizar(tipo);
    }

    public boolean possuiRelacionamento(TipoRecurso tipo) {
        Preconditions.checkArgument(!Util.objectIsNull(tipo.getId()), "Argumento Tipo precisa de um id");
        List<Recurso> listaRecursos = recursoDAO.listarPor("tipoRecurso.id", tipo.getId());
        return !listaRecursos.isEmpty();
    }
}
