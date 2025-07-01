package io.github.coletapi.apicoleta.service;

import io.github.coletapi.apicoleta.Exceptions.OperacaoNaoPermitida;
import io.github.coletapi.apicoleta.model.PontoColeta;
import io.github.coletapi.apicoleta.repository.PontoColetaRepository;
import io.github.coletapi.apicoleta.validator.PontoColetaValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public void deletar(UUID id) {
        if(!pontoColetaRepository.existsById(id)){
            throw new OperacaoNaoPermitida("Ponto de Coleta com o ID informado não existe.");
        }
        pontoColetaRepository.deleteById(id);
    }

    public PontoColeta atualizar(UUID id, PontoColeta pontoColeta) {
        PontoColeta pontoExiste = pontoColetaRepository.findById(id).orElseThrow(() -> new OperacaoNaoPermitida("Ponto não encontrado"));
        pontoExiste.setNome(pontoColeta.getNome());
        pontoExiste.setDescricao(pontoColeta.getDescricao());
        return pontoColetaRepository.save(pontoExiste);
    }

    public Optional<PontoColeta> buscarPorId(UUID id) {
        return pontoColetaRepository.findById(id);
    }

}
