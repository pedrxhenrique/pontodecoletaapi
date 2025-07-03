package io.github.coletapi.apicoleta.validator;

import io.github.coletapi.apicoleta.exceptions.OperacaoNaoPermitida;
import io.github.coletapi.apicoleta.model.PontoColeta;
import io.github.coletapi.apicoleta.repository.PontoColetaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PontoColetaValidator {

    private PontoColetaRepository pontoColetaRepository;

    public PontoColetaValidator(PontoColetaRepository pontoColetaRepository) {
        this.pontoColetaRepository = pontoColetaRepository;
    }

   public void validarPontoColeta(PontoColeta pontoColeta) {
        if(existePontoColeta(pontoColeta)) {
            throw new OperacaoNaoPermitida("O Ponto já está cadastrado, tente outro nome.");
        }
   }

    public boolean existePontoColeta(PontoColeta pontoColeta) {
        List<PontoColeta> pontosEncontrados = pontoColetaRepository.findByNome(pontoColeta.getNome());

        if (pontosEncontrados.isEmpty()) {
            return false;
        }
        if (pontoColeta.getId() == null) {
            return true;
        }
        return pontosEncontrados.stream()
                .anyMatch(p -> !p.getId().equals(pontoColeta.getId()));
    }
}
