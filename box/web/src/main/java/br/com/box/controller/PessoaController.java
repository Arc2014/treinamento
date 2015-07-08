package br.com.box.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;

import org.jboss.logging.Logger;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.com.box.interceptor.InterceptadorExcecaoController;
import br.com.box.model.Cidade;
import br.com.box.model.Endereco;
import br.com.box.model.Estado;
import br.com.box.model.EstadoCivil;
import br.com.box.model.ExperienciaProfissional;
import br.com.box.model.Pessoa;
import br.com.box.model.Sexo;
import br.com.box.model.SituacaoCurso;
import br.com.box.model.TipoCurso;
import br.com.box.model.TipoEmail;
import br.com.box.model.TipoTelefone;
import br.com.box.service.CidadeService;
import br.com.box.service.PessoaService;
import br.com.box.util.FacesUtil;

import com.google.common.base.Strings;

@RequestScoped
@Named
@Interceptors(InterceptadorExcecaoController.class)
public class PessoaController {
    private static final Logger LOGGER = Logger.getLogger(PessoaController.class);
    private static final long serialVersionUID = 8301865434469950945L;

    @Inject
    private PessoaService pessoaService;

    @Inject
    private CidadeService cidadeService;

    private List<Pessoa> pessoas;
    private Pessoa pessoa;
    private List<Cidade> cidades;

    private UploadedFile file;

    @PostConstruct
    public void init() {
        if (isAlteracao()) {
            alterarPessoa();
        } else {
            novaPessoa();
        }
    }

    private boolean isAlteracao() {
        String idPessoa = FacesUtil.getRequestParameter("idPessoa");
        return !Strings.isNullOrEmpty(idPessoa);
    }

    private void alterarPessoa() {
        Long id = Long.valueOf(FacesUtil.getRequestParameter("idPessoa"));
        pessoa = pessoaService.buscar(id);
    }

    private void novaPessoa() {
        pessoa = new Pessoa();
    }

    public void excluir(Pessoa p) {
        pessoaService.excluir(p);
        atualizarPessoas();
        FacesUtil.mostrarMensagemSucesso("pessoa.excluida.sucesso");
    }

    private void atualizarPessoas() {
        setPessoas(pessoaService.listar());
    }

    public String salvar() throws IOException {
        pessoa.setNomeArquivo(file.getFileName());
        pessoaService.salvar(pessoa, file.getInputstream());
        FacesUtil.mostrarMensagemSucesso("msg.cadastro.sucesso");
        return "/paginas/pessoa/listarPessoa";
    }

    public String atualizar() throws IOException {
        pessoa.setNomeArquivo(file.getFileName());
        pessoaService.atualizar(pessoa, file.getInputstream());
        FacesUtil.mostrarMensagemSucesso("pessoa.alterar.sucesso");
        return "/paginas/pessoa/listarPessoa";
    }

    public boolean verificaImagem(boolean readonly) {
        if (readonly && pessoa.possuiImagem()) {
            return true;
        }
        return false;
    }

    public StreamedContent getImagemPessoa() {
        StreamedContent content = null;
        if (pessoa.possuiImagem()) {
            // content =
            // ArquivoUtil.getStreamedContent(pessoa.getCaminhoImagem());
        }
        return content;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<Pessoa> getPessoas() {
        if (pessoas == null) {
            atualizarPessoas();
        }
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public void excluirExperiencia(ExperienciaProfissional experiencia) {
        this.pessoa.removerExperienciaProfissional(experiencia);
        FacesUtil.mostrarMensagemSucesso("experiencia.excluida.sucesso");
    }

    public void excluirEndereco(Endereco endereco) {
        this.pessoa.removerEndereco(endereco);
        FacesUtil.mostrarMensagemSucesso("endereco.excluida.sucesso");
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public Sexo[] getSexos() {
        return Sexo.values();
    }

    public EstadoCivil[] getEstadoCivil() {
        return EstadoCivil.values();
    }

    public Estado[] getEstados() {
        return Estado.values();
    }

    public void buscarCidades(Estado estado) {
        this.cidades = cidadeService.recuperarCidades(estado.getUnidadeFederacao());
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }

    public TipoEmail[] getTipoEmail() {
        return TipoEmail.values();
    }

    public TipoTelefone[] getTipoTelefone() {
        return TipoTelefone.values();
    }

    public TipoCurso[] getTipoCurso() {
        return TipoCurso.values();
    }

    public SituacaoCurso[] getSituacaoCurso() {
        return SituacaoCurso.values();
    }

    public String getMensagem(String chave) {
        return FacesUtil.getMessage(chave);
    }
}
