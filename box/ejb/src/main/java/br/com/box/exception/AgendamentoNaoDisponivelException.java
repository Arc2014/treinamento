package br.com.box.exception;

public class AgendamentoNaoDisponivelException extends Exception {

    private static final long serialVersionUID = 6187889668822600998L;

    public AgendamentoNaoDisponivelException(String message) {
        super(message);
    }

}
