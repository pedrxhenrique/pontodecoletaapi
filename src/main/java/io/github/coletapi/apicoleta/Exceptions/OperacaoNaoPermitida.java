package io.github.coletapi.apicoleta.Exceptions;

public class OperacaoNaoPermitida extends RuntimeException {
    public OperacaoNaoPermitida(String msg) {
        super(msg);
    }
}
