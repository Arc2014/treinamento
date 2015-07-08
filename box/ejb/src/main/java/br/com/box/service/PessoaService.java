package br.com.box.service;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.box.dao.PessoaDAO;
import br.com.box.model.Pessoa;
import br.com.box.util.ArquivoUtil;

@Named
@Stateless
public class PessoaService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private PessoaDAO pessoaDAO;

    public List<Pessoa> recuperarTodos() {
        return pessoaDAO.listar();
    }

    public List<Pessoa> listar() {
        return pessoaDAO.listar();
    }

    public void salvar(Pessoa pessoa, InputStream imagem) {
        salvar(pessoa);
        salvarImagemPessoa(pessoa, imagem);
    }

    public void salvar(Pessoa pessoa) {
        pessoaDAO.salvar(pessoa);
    }

    public void atualizar(Pessoa pessoa, InputStream imagem) {
        atualizar(pessoa);
        salvarImagemPessoa(pessoa, imagem);
    }

    public void atualizar(Pessoa pessoa) {
        pessoaDAO.atualizar(pessoa);
    }

    public void excluir(Pessoa p) {
        pessoaDAO.delete(p);
        deletarImagemPessoa(p);
    }

    public void excluir(Long id) {
        pessoaDAO.delete(id);
    }

    public Pessoa buscar(Long id) {
        return pessoaDAO.buscaDetach(id);
    }

    public void delete(Long id) {
        pessoaDAO.delete(id);
    }

    private void salvarImagemPessoa(Pessoa pessoa, InputStream imagem) {
        if (pessoa.possuiImagem() && imagem != null) {
            ArquivoUtil.salvarArquivo(pessoa.getCaminhoImagem(), imagem);
        }
    }

    private void deletarImagemPessoa(Pessoa pessoa) {
        if (pessoa.possuiImagem()) {
            ArquivoUtil.deletarArquivo(pessoa.getCaminhoImagem());
        }
    }

    public Pessoa buscarPorCPF(String valor) {
        return pessoaDAO.buscarPor("cpf", valor);
    }

    public List<Pessoa> buscarPorEmail(String email) {
        return pessoaDAO.buscarPorEmail(email);
    }

}
