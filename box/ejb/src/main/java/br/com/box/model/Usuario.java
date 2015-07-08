package br.com.box.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.box.util.CriptografiaUtil;

@Entity
@Table(name = "tb_usuario")
public class Usuario extends EntidadeComum implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String senha;

    private Boolean habilitado;

    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.REFRESH })
    @JoinColumn(name = "pessoa")
    private Pessoa pessoa;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_usuario_grupo", joinColumns = @JoinColumn(name = "usuario"), inverseJoinColumns = @JoinColumn(name = "grupo"))
    private List<Grupo> grupos;

    // Getters and Setters
    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getHabilitadoFormatado() {
        if (getHabilitado()) {
            return Habilitado.SIM.valor;
        } else {
            return Habilitado.NAO.valor;
        }
    }

    public void criptografarSenha() {
        this.senha = getSenhaCriptografada();
    }

    public String getSenhaCriptografada() {
        return CriptografiaUtil.criptografarMD5(this.getSenha());
    }

    public enum Habilitado {
        SIM("SIM"), NAO("NÂO");

        private String valor;

        Habilitado(String v) {
            valor = v;
        }

    }

    public boolean isAdmin() {
        for (Grupo grupo : grupos) {
            if (grupo.isAdmin()) {
                return true;
            }
        }
        return false;
    }

}
