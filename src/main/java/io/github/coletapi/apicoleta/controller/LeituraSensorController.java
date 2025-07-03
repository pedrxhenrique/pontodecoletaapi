package io.github.coletapi.apicoleta.controller;

import io.github.coletapi.apicoleta.dto.LeituraSensorDTO;
import io.github.coletapi.apicoleta.dto.LeituraSensorResponseDTO;
import io.github.coletapi.apicoleta.model.LeituraSensor;
import io.github.coletapi.apicoleta.service.LeituraSensorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/leituras")
@RequiredArgsConstructor
public class LeituraSensorController {

    private final LeituraSensorService leituraSensorService;

    @PostMapping
    public ResponseEntity<LeituraSensorResponseDTO> salvarLeitura(@RequestBody @Valid LeituraSensorDTO leituraSensorDTO) {
        LeituraSensor entidade = leituraSensorService.salvar(leituraSensorDTO.mapearLeitura());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entidade.getId()).toUri();
        return ResponseEntity.created(location).body(LeituraSensorResponseDTO.fromDTO(entidade));
    }

    @GetMapping("buscardata")
    public ResponseEntity<List<LeituraSensorResponseDTO>> buscarData(@RequestParam("dataInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataInicio,
                                                             @RequestParam("dataFim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataFim ) {
        List<LeituraSensor> leituras = leituraSensorService.listarPorData(dataInicio,dataFim);
        List<LeituraSensorResponseDTO> dtoList = leituras.stream().map(LeituraSensorResponseDTO::fromDTO).toList();
        return ResponseEntity.ok(dtoList);
    }
}
