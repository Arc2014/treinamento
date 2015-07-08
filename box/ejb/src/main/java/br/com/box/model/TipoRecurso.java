package br.com.box.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import br.com.box.enuns.Cor;

@Entity
@Table(name = "tb_tipo_recurso")
public class TipoRecurso extends EntidadeComum implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;

    public TipoRecurso() {

    }

    public TipoRecurso(Long id, String nome, Cor cor) {
        this.id = id;
        this.nome = nome;
        this.cor = cor;
    }

    @Enumerated(EnumType.STRING)
    private Cor cor;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }
}
