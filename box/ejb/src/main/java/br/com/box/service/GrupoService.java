package br.com.box.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.box.dao.GrupoDAO;
import br.com.box.model.Grupo;

@Named
@Stateless
public class GrupoService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private GrupoDAO grupoDao;

    public List<Grupo> recuperarTodos() {
        return grupoDao.listar();
    }

    public void gravar(Grupo grupo) {
        grupoDao.salvar(grupo);
    }

    public void deletar(Grupo grupo) {
        grupoDao.delete(grupo);
    }

    public void alterar(Grupo grupo) {
        grupoDao.atualizar(grupo);
    }

    public Grupo buscarPorId(Long id) {
        return grupoDao.buscar(id);

    }

    public Grupo buscarPorNome(String nome) {
        return grupoDao.buscarPorNome(nome);
    }
}
