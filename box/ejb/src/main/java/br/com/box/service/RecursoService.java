package br.com.box.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.jboss.logging.Logger;

import br.com.box.dao.RecursoDAO;
import br.com.box.model.Recurso;
import br.com.box.util.ArquivoUtil;
import br.com.box.util.PropriedadesUtil;
import br.com.box.util.Util;

@Named
@Stateless
public class RecursoService {

	private static final Logger LOGGER = Logger.getLogger(PropriedadesUtil.class);
	
    @Inject
    private RecursoDAO recursoDAO;

    public List<Recurso> recuperarTodos() {
        return recursoDAO.listar();
    }

    public List<Recurso> recuperarTodosAtivos() {
        return recursoDAO.listarAtivos();
    }

    public List<Recurso> recuperarPorFiltro(Recurso recursoFiltro) {
        return recursoDAO.buscarRecursosPorFiltros(recursoFiltro);
    }

    @Transactional
    public void salvar(Recurso recurso, InputStream is) {
        salvar(recurso);
        salvarImagemRecurso(recurso, is);
    }

    private void salvarImagemRecurso(Recurso recurso, InputStream is) {
        if (!Util.objectIsNull(is)) {
            ArquivoUtil.salvarArquivo(is, recurso.getCaminhoImagem());
        }
    }

    public void salvar(Recurso recurso) {
        recursoDAO.salvar(recurso);
    }

    public void deletarLogico(Recurso recurso) {
        File file = new File(recurso.getCaminhoImagem());
        file.delete();
        recurso.setDescricaoImagem("");
        recurso.setAtivo(Boolean.FALSE);
        recursoDAO.atualizar(recurso);
    }

    public void alterar(Recurso recurso, InputStream is) {
        recursoDAO.atualizar(recurso);
        salvarImagemRecurso(recurso, is);
    }

    public Recurso buscarPorPatrimonio(String patrimonio) {
        return recursoDAO.buscarPor("numeroPatrimonio", patrimonio);
    }

    public Recurso buscarPorId(Long id) {
        return recursoDAO.buscar(id);
    }

    public List<Recurso> listarRecursos(Recurso recurso) {
        if (!Util.objectIsNull(recurso.getNumeroPatrimonio()) && !Util.objectIsNull(recurso.getAtivo())) {
            return this.recuperarPorFiltro(recurso);
        } else {
            return this.recuperarTodosAtivos();
        }
    }

    public void verificarDiretorio() {
        try {
            File temp = new File(PropriedadesUtil.getProperty("caminho.arquivo.temp"));
            File diretorioFinal = new File(PropriedadesUtil.getProperty("caminho.arquivo.servidor"));
            if (!temp.exists()) {
                temp.mkdir();
            }
            if (!diretorioFinal.exists()) {
                diretorioFinal.mkdir();
            }
        } catch (Exception e) {
        	LOGGER.error(e);
            throw new RuntimeException(e.getMessage());
        }
    }

}
