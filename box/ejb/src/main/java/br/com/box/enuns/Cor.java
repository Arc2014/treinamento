package br.com.box.enuns;

import javax.inject.Named;

@Named
public enum Cor {

    VERMELHO("vermelho"),
    PRETO("preto"),
    BRANCO("branco"),
    VERDE("verde"),
    AZUL("azul"),
    AMARELO("amarelo"),
    ROXO("roxo");
    
    private String nome;

    private Cor(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return getNome();
    }

}
