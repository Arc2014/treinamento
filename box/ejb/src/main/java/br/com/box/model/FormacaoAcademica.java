package br.com.box.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "formacao_academica")
public class FormacaoAcademica extends EntidadeComum implements Serializable {

    private static final long serialVersionUID = -5226429356535760759L;

    @Column(name = "tipo")
    @Enumerated(EnumType.STRING)
    private TipoCurso tipoCurso;

    @Column(name = "instituicao")
    private String instituicao;

    @Column(name = "curso")
    private String curso;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "dt_inicio")
    private Date dataInicio;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "dt_fim")
    private Date dataFim;

    @Column(name = "situacao")
    @Enumerated(EnumType.STRING)
    private SituacaoCurso situacao;

    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    public FormacaoAcademica() {
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
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

    public SituacaoCurso getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoCurso situacao) {
        this.situacao = situacao;
    }

    @XmlTransient
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public TipoCurso getTipoCurso() {
        return tipoCurso;
    }

    public void setTipoCurso(TipoCurso tipoCurso) {
        this.tipoCurso = tipoCurso;
    }

}
