package com.br.edu.ifba.email_ms.dtos;

import com.br.edu.ifba.email_ms.model.Email;
import com.br.edu.ifba.email_ms.model.EmailStatus;

public record EmailDto(
    String mailFrom,
    String mailTo,
    String mailSubject,
    String mailText,
    EmailStatus status
) {

    public EmailDto() {
        this(null, null, null, null, null);
    }

    public EmailDto(Email email) {
        this(
            email.getMailFrom(),
            email.getMailTo(),
            email.getMailSubject(),
            email.getMailText(),
            email.getStatus()
        );
    }
}
