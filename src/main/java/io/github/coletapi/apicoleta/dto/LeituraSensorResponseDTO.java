package io.github.coletapi.apicoleta.dto;

import io.github.coletapi.apicoleta.model.LeituraSensor;

import java.time.format.DateTimeFormatter;
import java.util.UUID;

public record LeituraSensorResponseDTO(UUID id, UUID pontoColetaID, String dataHora, Double pH, Double temperatura, Double turbidez, String contaminantes) {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public static LeituraSensorResponseDTO fromDTO(LeituraSensor leitura) {
        return new LeituraSensorResponseDTO(
          leitura.getId(),
          leitura.getIdPontoColeta().getId(),
          leitura.getDataHora().format(FORMATTER),
          leitura.getPH(),
          leitura.getTemperatura(),
          leitura.getTurbidez(),
          leitura.getContaminantes()
        );

    }
}
