package br.com.box.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.box.util.DataUtil;

@XmlRootElement(name = "Agenda")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "tb_agenda")
public class Agenda extends EntidadeComum implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "titulo_evento", length = 200)
    private String tituloEvento;

    @Column(name = "desc_evento", length = 1000)
    private String descricaoEvento;

    @Column(name = "dia_todo")
    private boolean diaTodo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_inicio")
    private Date dataInicio;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_fim")
    private Date dataFim;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_notificacao")
    private Date dataNotificacao;

    @ManyToOne
    @JoinColumn(name = "usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "pessoa")
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "recurso")
    private Recurso recurso;

    public String getTituloEvento() {
        return tituloEvento;
    }

    public void setTituloEvento(String tituloEvento) {
        this.tituloEvento = tituloEvento;
    }

    public String getDescricaoEvento() {
        return descricaoEvento;
    }

    public void setDescricaoEvento(String descricaoEvento) {
        this.descricaoEvento = descricaoEvento;
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

    public Date getDataNotificacao() {
        return dataNotificacao;
    }

    public void setDataNotificacao(Date dataNotificacao) {
        this.dataNotificacao = dataNotificacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Recurso getRecurso() {
        return recurso;
    }

    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    public boolean isDiaTodo() {
        return diaTodo;
    }

    public void setDiaTodo(boolean diaTodo) {
        this.diaTodo = diaTodo;
    }

    public String getDataInicioFormatada() {
        return DataUtil.formataDataPtBr(this.dataInicio);
    }

    public String getDataFimFormatada() {
        return DataUtil.formataDataPtBr(this.dataFim);
    }

    public String getHoraInicioFormatada() {
        return DataUtil.formataHoraPtBr(this.dataInicio);
    }

    public String getHoraFimFormatada() {
        return DataUtil.formataHoraPtBr(this.dataFim);
    }

    public String getLabel() {
        return getDescricaoEvento().concat(" - ").concat(
                getRecurso().getDescricao());
    }

    public String getCorEvento() {
        return getRecurso().getTipoRecurso().getCor().getNome();
    }
}
