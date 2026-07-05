package com.br.edu.ifba.email_ms.services;

import java.time.LocalDateTime;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.br.edu.ifba.email_ms.dtos.EmailDto;
import com.br.edu.ifba.email_ms.model.Email;
import com.br.edu.ifba.email_ms.model.EmailStatus;

@Service // Define esta classe como um componente de serviço do Spring
public class EmailService {
    
    private JavaMailSender emailSender;

    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public Email sendEmail(EmailDto dto) {

        Email data = new Email();

        data.setMailFrom(dto.mailFrom());
        data.setMailTo(dto.mailTo());
        data.setMailSubject(dto.mailSubject());
        data.setMailText(dto.mailText());
        data.setSendDateEmail(LocalDateTime.now());
        data.setStatus(EmailStatus.SENT);

        // Cria uma mensagem simples de e-mail
        SimpleMailMessage message = new SimpleMailMessage();

        // Define o remetente do e-mail
        message.setFrom(dto.mailFrom());

        // Define o destinatário do e-mail
        message.setTo(dto.mailTo());

        // Define o assunto do e-mail
        message.setSubject(dto.mailSubject());

        // Define o texto do e-mail
        message.setText(dto.mailText());

        // Envia a mensagem
        emailSender.send(message);

        // Retorna os dados do e-mail
        return data;
    }
}
