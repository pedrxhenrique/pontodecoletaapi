package io.github.coletapi.apicoleta.dto;

import io.github.coletapi.apicoleta.model.LeituraSensor;
import io.github.coletapi.apicoleta.model.PontoColeta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record LeituraSensorDTO(UUID id, @NotNull(message = "O ID do ponto de coleta é obrigatório")UUID pontoColetaID, @NotNull(message = "O campo pH não pode ser nulo") Double pH, @NotNull(message = "O campo temperatura não pode ser nulo") Double temperatura,  @NotNull(message = "O campo turbidez não pode ser nulo") Double turbidez,@NotBlank(message = "O campo contaminantes não pode ser vazio ou nulo") String contaminantes) {

    public LeituraSensor mapearLeitura(){
        LeituraSensor leituraSensor = new LeituraSensor();

        PontoColeta pontoColeta = new PontoColeta();
        leituraSensor.setId(id);
        pontoColeta.setId(pontoColetaID);
        leituraSensor.setIdPontoColeta(pontoColeta);
        leituraSensor.setPH(pH);
        leituraSensor.setTemperatura(temperatura);
        leituraSensor.setTurbidez(turbidez);
        leituraSensor.setContaminantes(contaminantes);
        return leituraSensor;

    }
}
