package br.com.box.model;

public enum EstadoCivil {
    SOLTEIRO("pessoa.estadoCivil.solteiro"), CASADO("pessoa.estadoCivil.casado"), AMAZIADO("pessoa.estadoCivil.amaziado"),
    DIVORCIADO("pessoa.estadoCivil.divorciado"), VIUVO("pessoa.estadoCivil.viuvo");

    private String chaveMensagem;

    EstadoCivil(String chaveMensagem) {
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
