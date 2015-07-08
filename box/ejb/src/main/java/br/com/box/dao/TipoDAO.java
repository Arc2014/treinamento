package br.com.box.dao;

import javax.ejb.Stateless;

import br.com.box.model.TipoRecurso;

@Stateless
public class TipoDAO extends GenericDAO<TipoRecurso> {

    public TipoRecurso buscarPorNome(String nome) {
        return buscarPor("nome", nome);
    }

}
