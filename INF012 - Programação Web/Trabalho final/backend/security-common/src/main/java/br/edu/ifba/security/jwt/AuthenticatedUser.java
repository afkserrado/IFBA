package br.edu.ifba.security.jwt;

public class AuthenticatedUser {

    private final Long id;
    private final String email;

    public AuthenticatedUser(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}