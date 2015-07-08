package br.com.box.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_notificacao")
public class Notificacao extends EntidadeComum implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "data_notificar", nullable = false)
    private Date dataNotificar;

    @Column(name = "data_envio")
    private Date dataEnvio;

    @Column(nullable = false)
    private String remetente;

    @Column(nullable = false)
    private String destinatario;

    @Column(nullable = false)
    private String corpo;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private String titulo;

    // Getters and Setters
    public Date getDataNotificar() {
        return dataNotificar;
    }

    public void setDataNotificar(Date dataNotificar) {
        this.dataNotificar = dataNotificar;
    }

    public Date getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(Date dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}
