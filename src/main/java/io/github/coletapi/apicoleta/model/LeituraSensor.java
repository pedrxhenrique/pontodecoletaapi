package io.github.coletapi.apicoleta.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "LeituraSensor")
public class LeituraSensor {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "ponto_coleta_id")
    private PontoColeta pontoColeta;

    @CreatedDate
    @Column(name = "data_hora")
    private LocalDateTime data_hora;

    @Column(name = "pH")
    private Double pH;

    @Column(name = "temperatura")
    private Double temperatura;

    @Column(name = "turbidez")
    private Double turbidez;

    @Column(name = "contaminantes")
    private String contaminantes;
}
