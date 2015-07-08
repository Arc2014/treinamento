package br.com.box.model;


public enum TipoTelefone {
    PESSOAL("pessoa.telefone.pessoal"), COMERCIAL("pessoa.telefone.comercial"), CELULAR("pessoa.telefone.celular");

    private String chaveMensagem;

    TipoTelefone(String chaveMensagem) {
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
