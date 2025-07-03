package io.github.coletapi.apicoleta.controller;

import io.github.coletapi.apicoleta.Exceptions.OperacaoNaoPermitida;
import io.github.coletapi.apicoleta.dto.PontoColetaDTO;
import io.github.coletapi.apicoleta.dto.errors.ErrorCampo;
import io.github.coletapi.apicoleta.dto.errors.ErrorResposta;
import io.github.coletapi.apicoleta.model.PontoColeta;
import io.github.coletapi.apicoleta.service.PontoColetaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pontoscoleta")
@RequiredArgsConstructor
public class PontoColetaController {

    private final PontoColetaService pontoColetaService;

    @PostMapping
    public ResponseEntity<?> salvarPonto(@RequestBody @Valid PontoColetaDTO pontoColetaDTO) {
        try {
            PontoColeta pontoColeta = pontoColetaService.salvar(pontoColetaDTO.mapearPontoColeta());
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pontoColeta.getId()).toUri();
            return ResponseEntity.created(location).body(pontoColeta);
        }catch (OperacaoNaoPermitida e){
            var error = ErrorResposta.conflito(e.getMessage());
            return ResponseEntity.status(error.status()).body(error);
        }
    }

    @GetMapping("listarTodos")
    public ResponseEntity<List<PontoColeta>> listarPontoColeta() {
        return ResponseEntity.ok(pontoColetaService.listarTodos());
    }

    @GetMapping
    public ResponseEntity<?> pesquisaPorNome(@RequestParam(value = "nome", required = false) String nome) {
        List<PontoColeta> pontos = pontoColetaService.pesquisar(nome);
        if(pontos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<PontoColetaDTO> listaDTO = pontos.stream().map(pontoColeta -> new PontoColetaDTO(pontoColeta.getId(), pontoColeta.getNome(), pontoColeta.getLatitude(), pontoColeta.getLongitude(), pontoColeta.getDescricao())).collect(Collectors.toList());
        return ResponseEntity.ok(listaDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> removerPontoColeta(@PathVariable("id") String id) {
        try{
            var idPonto = UUID.fromString(id);
            Optional<PontoColeta> ponto = pontoColetaService.buscarPorId(idPonto);
            if(ponto.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResposta.naoEncontrado("O id informado não foi encontrado"));
            }
            pontoColetaService.deletar(ponto.get().getId());
            return ResponseEntity.noContent().build();
        }catch(OperacaoNaoPermitida e){
            var error = ErrorResposta.conflito(e.getMessage());
            return ResponseEntity.status(error.status()).body(error);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> atualizarPonto(@PathVariable("id") String id, @RequestBody @Valid PontoColetaDTO pontoColetaDTO) {
        var idPonto = UUID.fromString(id);
        Optional<PontoColeta> ponto = pontoColetaService.buscarPorId(idPonto);
        if(ponto.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResposta.naoEncontrado("O Ponto não existe"));
        }
        var pontoEntity = ponto.get();
        pontoEntity.setNome(pontoColetaDTO.nome());
        pontoEntity.setDescricao(pontoColetaDTO.descricao());

        pontoColetaService.salvar(pontoEntity);
        return ResponseEntity.ok().body(pontoEntity);
    }

}
