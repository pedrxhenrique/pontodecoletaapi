package io.github.coletapi.apicoleta.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "leitura_sensor")
@EntityListeners(AuditingEntityListener.class)
public class LeituraSensor {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "ponto_coleta_id")
    private PontoColeta idPontoColeta;

    @CreatedDate
    @Column(name = "data_hora")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dataHora;

    @Column(name = "pH")
    private Double pH;

    @Column(name = "temperatura")
    private Double temperatura;

    @Column(name = "turbidez")
    private Double turbidez;

    @Column(name = "contaminantes")
    private String contaminantes;
}
