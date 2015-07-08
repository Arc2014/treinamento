package br.com.box.model;


public enum SituacaoCurso {
    TRANCADO("pessoa.curso.trancado"),
    CONCLUIDO("pessoa.curso.concluido"),
    ANDAMENTO("pessoa.curso.andamento");

    private String chaveMensagem;

    SituacaoCurso(String chaveMensagem) {
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
