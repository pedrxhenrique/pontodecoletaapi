package io.github.coletapi.apicoleta.dto;

import io.github.coletapi.apicoleta.model.PontoColeta;

import java.util.UUID;

public record PontoColetaDTO(UUID id, String nome, Double latitude, Double longitude, String descricao) {

    public PontoColeta mapearPontoColeta() {
        PontoColeta pontoColeta = new PontoColeta();
        pontoColeta.setId(id);
        pontoColeta.setNome(nome);
        pontoColeta.setLatitude(latitude);
        pontoColeta.setLongitude(longitude);
        pontoColeta.setDescricao(descricao);
        return pontoColeta;
    }
}
