package br.com.box.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_grupo")
public class Grupo extends EntidadeComum implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String ADMIN_ROLE = "ADMIN";

    @Column(nullable = false)
    private String nome;

    private String descricao;

    // Getters and Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isAdmin() {
        return ADMIN_ROLE.equals(nome);
    }

}
