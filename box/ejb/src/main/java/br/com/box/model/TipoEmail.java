package br.com.box.model;

public enum TipoEmail {
    PESSOAL("pessoa.email.pessoal"), COMERCIAL("pessoa.email.comercial");

    private String chaveMensagem;

    TipoEmail(String chaveMensagem) {
        this.chaveMensagem = chaveMensagem;
    }

    public String getLabel() {
        return chaveMensagem;
    }

    @Override
    public String toString() {
        return getLabel();
    }
}
