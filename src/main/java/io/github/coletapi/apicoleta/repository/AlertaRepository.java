package io.github.coletapi.apicoleta.repository;

import io.github.coletapi.apicoleta.model.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AlertaRepository extends JpaRepository<Alerta, UUID> {
}
