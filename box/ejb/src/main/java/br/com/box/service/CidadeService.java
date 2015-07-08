package br.com.box.service;

import java.io.Serializable;
import java.util.List;

import br.com.box.dao.CidadeDAO;
import br.com.box.model.Cidade;

public class CidadeService implements Serializable {

    private static final long serialVersionUID = -4699562073097356327L;

    private CidadeDAO cidadeDAO;

    public CidadeService() {
        cidadeDAO = new CidadeDAO();
    }

    public List<Cidade> recuperarCidades(String uf) {
        return cidadeDAO.recuperarCidades(uf);
    }

}
