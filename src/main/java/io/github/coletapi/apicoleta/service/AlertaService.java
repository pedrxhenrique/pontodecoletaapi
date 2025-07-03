package io.github.coletapi.apicoleta.service;

import io.github.coletapi.apicoleta.model.Alerta;
import io.github.coletapi.apicoleta.model.LeituraSensor;
import io.github.coletapi.apicoleta.model.Status;
import io.github.coletapi.apicoleta.repository.AlertaRepository;
import io.github.coletapi.apicoleta.repository.LeituraSensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AlertaService {

    private final LeituraSensorRepository leituraSensorRepository;
    private final AlertaRepository alertaRepository;
    private final EmailService emailService;

    @Value("${monitoramento.qualidadeAgua.phMin}")
    private Double  PH_MIN;

    @Value("${monitoramento.qualidadeAgua.phMax}")
    private Double  PH_MAX;

    @Value("${monitoramento.qualidadeAgua.tempMax}")
    private Double  TEMP_MAX;

    @Value("${monitoramento.qualidadeAgua.turbidezMax}")
    private Double  TURBIDEZ_MAX;

    @Value("${monitoramento.email.destinatario}")
    private String destino;

    @Scheduled(fixedRate = 3600000)
    public void verificarLeiturasRecentes(){
        LocalDateTime minAtras = LocalDateTime.now().minusMinutes(60);
        List<LeituraSensor> leiturasRecentes = leituraSensorRepository.findByDataHoraAfter(minAtras);

        for(LeituraSensor leituraSensor : leiturasRecentes){
            System.out.println("Processando leitura do ponto: " + leituraSensor.getIdPontoColeta().getNome());
            List<String> problemas = new ArrayList<>();

            if(leituraSensor.getPH() < PH_MIN || leituraSensor.getPH() > PH_MAX){
                problemas.add("pH fora do intervalo seguro");
            }
            if(leituraSensor.getTemperatura() > TEMP_MAX){
                problemas.add("Temperatura elevada");
            }
            if(leituraSensor.getTurbidez() > TURBIDEZ_MAX){
                problemas.add("Turbidez elevada");
            }

            if(!problemas.isEmpty()){
                Alerta alerta = new Alerta();
                alerta.setPontoColeta(leituraSensor.getIdPontoColeta());
                alerta.setDataHora(LocalDateTime.now());
                alerta.setMensagem(String.join("; ", problemas));
                alerta.setStatus(Status.enviado);
                alertaRepository.save(alerta);

                String assunto = "Alerta de qualidade da água";
                String corpo = "Olá,\n\n"
                        + "Foi detectado um problema na qualidade da água no ponto de coleta: "
                        + leituraSensor.getIdPontoColeta().getNome() + ".\n"
                        + "Detalhes do alerta: " + alerta.getMensagem() + "\n\n"
                        + "Por favor, verifique o mais breve possível.\n\n"
                        + "Atenciosamente,\n"
                        + "Equipe de Monitoramento da Qualidade da Água";
                emailService.enviarAlerta(destino, assunto, corpo);
            }
            }
        }

        public List<Alerta> listarAlertas(){
        return alertaRepository.findAll();
        }
    }

