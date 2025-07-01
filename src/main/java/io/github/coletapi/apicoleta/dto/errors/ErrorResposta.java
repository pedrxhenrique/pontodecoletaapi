package io.github.coletapi.apicoleta.dto.errors;

import java.util.List;

public record ErrorResposta(int status, String mensagme, List<ErrorCampo> errors) {

    public static ErrorResposta conflito(String mensagem){
        return new ErrorResposta(403, mensagem, List.of());
    }

    public static ErrorResposta naoEncontrado(String mensagem){
        return new ErrorResposta(404, mensagem, List.of());
    }
}
