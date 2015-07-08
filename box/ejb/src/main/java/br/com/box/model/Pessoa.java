package br.com.box.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.joda.time.DateTime;
import org.joda.time.Years;

import br.com.box.util.ArquivoUtil;

import com.google.common.base.Strings;

@XmlRootElement
@Entity
@Table(name = "tb_pessoa")
public class Pessoa extends EntidadeComum implements Serializable {

    private static final long serialVersionUID = -440601348647801047L;

    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    private String nome;
    private String nomeArquivo;
    private String cpf;
    private String rg;
    private String nacionalidade;
    private String naturalidade;

    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExperienciaProfissional> experienciaProfissionais = new ArrayList<ExperienciaProfissional>();

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> enderecos = new ArrayList<Endereco>();

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Email> emails = new ArrayList<Email>();

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Telefone> telefones = new ArrayList<Telefone>();

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FormacaoAcademica> formacoesAcademica = new ArrayList<FormacaoAcademica>();

    public Pessoa() {

    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<ExperienciaProfissional> getExperienciaProfissionais() {
        return experienciaProfissionais;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public List<FormacaoAcademica> getFormacoesAcademica() {
        return formacoesAcademica;
    }

    public void setFormacoesAcademica(List<FormacaoAcademica> formacoesAcademica) {
        this.formacoesAcademica = formacoesAcademica;
    }

    public void setExperienciaProfissionais(
            List<ExperienciaProfissional> experienciaProfissionais) {
        this.experienciaProfissionais = experienciaProfissionais;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public void adicionarNovaExperienciaProfissional() {
        adicionarExperienciaProfissional(new ExperienciaProfissional());
    }

    public void adicionarExperienciaProfissional(
            ExperienciaProfissional experienciaProfissional) {
        this.experienciaProfissionais.add(experienciaProfissional);
    }

    public void removerExperienciaProfissional(
            ExperienciaProfissional experienciaProfissional) {
        this.experienciaProfissionais.remove(experienciaProfissional);
    }

    public void adicionarNovoEndereco() {
        adicionarEndereco(new Endereco());
    }

    public void adicionarNovoEmail() {
        adicionarEmail(new Email());
    }

    public void adicionarEndereco(Endereco endereco) {
        this.enderecos.add(endereco);
    }

    public void removerEndereco(Endereco endereco) {
        this.enderecos.remove(endereco);
    }

    public void adicionarEmail(Email email) {
        email.setPessoa(this);
        this.emails.add(email);
    }

    public void removerEmail(Email email) {
        this.emails.remove(email);
    }

    public void adicionarNovoTelefone() {
        adicionarTelefone(new Telefone());
    }

    public void adicionarTelefone(Telefone telefone) {
        telefone.setPessoa(this);
        this.telefones.add(telefone);
    }

    public void removerTelefone(Telefone telefone) {
        this.telefones.remove(telefone);
    }

    public void adicionarNovaFormacaoAcademica() {
        adicionarFormacaoAcademica(new FormacaoAcademica());
    }

    public void adicionarFormacaoAcademica(FormacaoAcademica formacaoAcademica) {
        formacaoAcademica.setPessoa(this);
        this.formacoesAcademica.add(formacaoAcademica);
    }

    public void removerFormacaoAcademica(FormacaoAcademica formacaoAcademica) {
        this.formacoesAcademica.remove(formacaoAcademica);
    }

    public int getIdade() {
        return Years.yearsBetween(new DateTime(getDataNascimento()),
                new DateTime()).getYears();
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public boolean possuiImagem() {
        return !Strings.isNullOrEmpty(getNomeArquivo());
    }

    public String getCaminhoImagem() {
        String caminhoImagem = null;
        if (possuiImagem()) {
            String nomeImagem = getNomeImagemArmazenado();
            caminhoImagem = ArquivoUtil.montarCaminho(
                    "parametro.caminho.foto.perfil", nomeImagem);
        }

        return caminhoImagem;
    }

    private String getNomeImagemArmazenado() {
        String extensao = ArquivoUtil.recuperarExtensao(getNomeArquivo());
        return String.format("%s.%s", getId(), extensao);
    }
}
