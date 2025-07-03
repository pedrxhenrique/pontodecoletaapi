package io.github.coletapi.apicoleta.exceptions;

public class OperacaoNaoPermitida extends RuntimeException {
    public OperacaoNaoPermitida(String msg) {
        super(msg);
    }
}
