package br.com.box.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "tb_email")
public class Email extends EntidadeComum implements Serializable {

    private static final long serialVersionUID = 2468704265999049790L;

    @Enumerated(EnumType.STRING)
    private TipoEmail tipoEmail;

    private String email;

    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    public Email() {
    }

    public TipoEmail getTipoEmail() {
        return tipoEmail;
    }

    public void setTipoEmail(TipoEmail tipoEmail) {
        this.tipoEmail = tipoEmail;
    }

    @XmlTransient
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
