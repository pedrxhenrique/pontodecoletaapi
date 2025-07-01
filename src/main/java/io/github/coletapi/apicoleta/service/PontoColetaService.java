package io.github.coletapi.apicoleta.service;

import io.github.coletapi.apicoleta.model.PontoColeta;
import io.github.coletapi.apicoleta.repository.PontoColetaRepository;
import io.github.coletapi.apicoleta.validator.PontoColetaValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PontoColetaService {

    private final PontoColetaRepository pontoColetaRepository;
    private final PontoColetaValidator validator;

    public PontoColeta salvar(PontoColeta pontoColeta) {
        validator.validarPontoColeta(pontoColeta);
        return pontoColetaRepository.save(pontoColeta);
    }

    public List<PontoColeta> listarTodos() {
        return pontoColetaRepository.findAll();
    }

    public List<PontoColeta> pesquisar(String nome) {
        if(nome != null && !nome.trim().isEmpty()){
            return pontoColetaRepository.findByNomeContainingIgnoreCase(nome);
        }
        return pontoColetaRepository.findAll();
    }



}
