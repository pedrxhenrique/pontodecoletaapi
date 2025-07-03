package io.github.coletapi.apicoleta.controller;

import io.github.coletapi.apicoleta.model.Alerta;
import io.github.coletapi.apicoleta.model.PontoColeta;
import io.github.coletapi.apicoleta.service.AlertaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/alertas")
@RequiredArgsConstructor
public class AlertaController {

    private final AlertaService alertaService;

    @GetMapping
    public ResponseEntity<List<Alerta>> listarAlertas() {
        return ResponseEntity.ok(alertaService.listarAlertas());
    }
}
