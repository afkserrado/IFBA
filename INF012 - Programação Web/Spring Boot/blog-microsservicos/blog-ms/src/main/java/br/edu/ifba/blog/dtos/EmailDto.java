package br.edu.ifba.blog.dtos;

// O blog-ms só precisa saber quais campos enviar no JSON. Ele não precisa conhecer a entidade Email nem o enum EmailStatus do outro microsserviço
public record EmailDto(
    String mailFrom,
    String mailTo,
    String mailSubject,
    String mailText
) {}
