package io.github.coletapi.apicoleta.controller;

import io.github.coletapi.apicoleta.Exceptions.OperacaoNaoPermitida;
import io.github.coletapi.apicoleta.dto.PontoColetaDTO;
import io.github.coletapi.apicoleta.dto.errors.ErrorCampo;
import io.github.coletapi.apicoleta.dto.errors.ErrorResposta;
import io.github.coletapi.apicoleta.model.PontoColeta;
import io.github.coletapi.apicoleta.service.PontoColetaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pontocoleta")
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
}
