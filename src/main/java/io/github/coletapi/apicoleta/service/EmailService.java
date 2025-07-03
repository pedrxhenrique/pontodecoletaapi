package io.github.coletapi.apicoleta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void enviarAlerta(String destino, String assunto, String corpo){
        System.out.println("Enviando email para " + destino);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(destino);
        message.setSubject(assunto);
        message.setText(corpo);
        mailSender.send(message);
        System.out.println("Email enviado!");
    }
}
