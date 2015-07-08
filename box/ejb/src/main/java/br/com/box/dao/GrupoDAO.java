package br.com.box.dao;

import javax.ejb.Stateless;

import br.com.box.model.Grupo;

@Stateless
public class GrupoDAO extends GenericDAO<Grupo> {

    public Grupo buscarPorNome(String nome) {
        return buscarPor("nome", nome);
    }

}
