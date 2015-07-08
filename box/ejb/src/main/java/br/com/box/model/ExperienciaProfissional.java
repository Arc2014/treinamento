package br.com.box.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "experiencia_profissional")
public class ExperienciaProfissional extends EntidadeComum implements
        Serializable {

    private static final long serialVersionUID = -956738393964376699L;

    private String nomeEmpresa;

    private String cargo;
    private String localidade;
    private String descricao;

    @Temporal(value = TemporalType.DATE)
    private Date dataInicio;

    @Temporal(value = TemporalType.DATE)
    private Date dataFim;

    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public ExperienciaProfissional() {

    }

    public String getEmpresa() {
        return nomeEmpresa;
    }

    public void setEmpresa(String empresa) {
        this.nomeEmpresa = empresa;
    }

    public String getAtividade() {
        return localidade;
    }

    public void setAtividade(String atividade) {
        this.localidade = atividade;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
