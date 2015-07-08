package br.com.box.model;


public enum Sexo {
    M("pessoa.sexo.masculino"), F("pessoa.sexo.feminino");

    private String chaveMensagem;

    Sexo(String chaveMensagem) {
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
