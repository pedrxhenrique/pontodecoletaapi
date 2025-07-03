package io.github.coletapi.apicoleta.dto;

import io.github.coletapi.apicoleta.model.PontoColeta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PontoColetaDTO(UUID id, @NotBlank(message = "O campo nome não pode ser vazio ou nulo") String nome, @NotNull(message = "O campo latitude não pode ser nulo") Double latitude, @NotNull(message = "O campo longitude não pode ser nulo") Double longitude, @NotBlank(message = "O campo descricao não pode ser vazio ou nulo") String descricao) {

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
