package io.github.coletapi.apicoleta.repository;

import io.github.coletapi.apicoleta.model.PontoColeta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PontoColetaRepository extends JpaRepository<PontoColeta, UUID> {

    List<PontoColeta> findByNome(String nome);

    List<PontoColeta> findByNomeContainingIgnoreCase(String nome);


}
