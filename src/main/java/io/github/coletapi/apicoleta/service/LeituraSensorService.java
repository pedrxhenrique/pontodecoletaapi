package io.github.coletapi.apicoleta.service;

import io.github.coletapi.apicoleta.model.LeituraSensor;
import io.github.coletapi.apicoleta.model.PontoColeta;
import io.github.coletapi.apicoleta.repository.LeituraSensorRepository;
import io.github.coletapi.apicoleta.repository.PontoColetaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LeituraSensorService {

    private final LeituraSensorRepository leituraSensorRepository;
    private final PontoColetaRepository pontoColetaRepository;

    public LeituraSensor salvar(LeituraSensor leituraSensor) {
        UUID idPontoColeta = leituraSensor.getIdPontoColeta().getId();
        PontoColeta pontoColeta = pontoColetaRepository.findById(idPontoColeta).orElseThrow(() -> new RuntimeException("Ponto Coleta inexistente"));

        leituraSensor.setIdPontoColeta(pontoColeta);
        return leituraSensorRepository.save(leituraSensor);
    }


    public List<LeituraSensor> listarPorData(LocalDateTime dataInicio, LocalDateTime dataFim){
        return leituraSensorRepository.findByDataHoraBetween(dataInicio, dataFim);
    }
}
