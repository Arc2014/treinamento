package br.com.box.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cidade")
public class Cidade extends EntidadeComum implements Serializable {

    private static final long serialVersionUID = -956738393964376699L;

    private String nome;
    private String uf;

    public Cidade() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @Override
    public String toString() {
        return this.nome;
    }

}
