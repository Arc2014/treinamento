package br.com.box.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonIgnore;

import br.com.box.util.PropriedadesUtil;
import br.com.box.util.Util;

import com.google.common.base.Strings;
import com.google.common.io.Files;

@Entity
@Table(name = "tb_recurso")
public class Recurso extends EntidadeComum implements Serializable,
        Comparable<Recurso> {

    private static final long serialVersionUID = 1L;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "num_patrimonio")
    private String numeroPatrimonio;

    @ManyToOne
    @JoinColumn(name = "tipo")
    private TipoRecurso tipoRecurso;

    @Column(name = "ativo")
    private Boolean ativo;

    @Column(name = "descricao_imagem")
    private String descricaoImagem;

    @OneToMany(mappedBy = "recurso")
    private List<Agenda> listaAgendamento;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNumeroPatrimonio() {
        return numeroPatrimonio;
    }

    public void setNumeroPatrimonio(String numeroPatrimonio) {
        this.numeroPatrimonio = numeroPatrimonio;
    }

    public TipoRecurso getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(TipoRecurso tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getDescricaoImagem() {
        return descricaoImagem;
    }

    public String getDescricaoImagemSemExtensao() {
        return Files.getNameWithoutExtension(descricaoImagem);
    }

    public void setDescricaoImagem(String descricaoImagem) {
        this.descricaoImagem = descricaoImagem;
    }

    public String getCaminhoImagem() {
        return PropriedadesUtil.getProperty("caminho.arquivo.servidor") + getImagemId();
    }

    public String getImagemId() {
        if (!Util.objectIsNull(descricaoImagem)) {
            return this.getId() + "." + Files.getFileExtension(descricaoImagem);
        }
        return "";
    }

    @JsonIgnore
    @XmlTransient
    public List<Agenda> getListaAgendamento() {
        return listaAgendamento;
    }

    @JsonIgnore
    public void setListaAgendamento(List<Agenda> listaAgendamento) {
        this.listaAgendamento = listaAgendamento;
    }

    public String getStatusAtivo() {
        if (ativo.equals(Boolean.TRUE)) {
            return "Sim";
        } else {
            return "Não";
        }

    }

    public boolean possuiImagem() {
        return !Strings.isNullOrEmpty(descricaoImagem);
    }

    @Override
    public int compareTo(Recurso outroRecurso) {
        return this.getDescricao().compareTo(outroRecurso.getDescricao());
    }

}
