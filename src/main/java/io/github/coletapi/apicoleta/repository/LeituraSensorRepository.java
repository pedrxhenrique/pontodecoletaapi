package io.github.coletapi.apicoleta.repository;

import io.github.coletapi.apicoleta.model.LeituraSensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface LeituraSensorRepository extends JpaRepository<LeituraSensor, UUID> {

    List<LeituraSensor> findByContaminantes(String contaminante);

    List<LeituraSensor> findByDataHoraBetween(LocalDateTime dataInicio, LocalDateTime dataFim);

    List<LeituraSensor> findByDataHoraAfter(LocalDateTime hora);
}
