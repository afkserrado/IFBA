package com.br.edu.ifba.email_ms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.edu.ifba.email_ms.model.Email;

public interface EmailRepository extends JpaRepository<Email, Long> {
    
}
