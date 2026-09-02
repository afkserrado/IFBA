package br.edu.ifba.email_ms.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.edu.ifba.email_ms.dto.EmailRequest;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void enviarEmail(EmailRequest request) {

        SimpleMailMessage mensagem = new SimpleMailMessage();

        mensagem.setTo(request.destinatario());
        mensagem.setSubject(request.assunto());
        mensagem.setText(request.mensagem());

        try {
            mailSender.send(mensagem);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar e-mail", e);
        }
    }
}
