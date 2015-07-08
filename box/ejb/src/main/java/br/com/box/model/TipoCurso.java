package br.com.box.model;


public enum TipoCurso {
    TECNICO("pessoa.curso.tecnico"),
    GRADUACAO("pessoa.curso.graduacao"),
    PGRADUACAO("pessoa.curso.pgraduacao"),
    MESTRADO("pessoa.curso.mestrado"),
    DOUTORADO("pessoa.curso.doutorado");

    private String chaveMensagem;

    TipoCurso(String chaveMensagem) {
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
