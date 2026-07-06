package br.com.agenda.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity(name = "contatos")
public class Contato {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(
        min = 3,
        max = 100,
        message = "O nome deve possuir de 3 a 100 caracteres."
    )
    private String nome;

    @NotBlank
    @Pattern(
        regexp = "\\d{10,11}",
        message = "O telefone deve conter 10 ou 11 números."
    )
    private String telefone;

    @Email(message = "O e-mail deve estar em um formato válido.")
    private String email;

    private boolean favorito = false;
    private LocalDateTime dataCriacao;

    public Contato() {}

    // Para inserção
    public Contato(String nome, String telefone, String email, boolean favorito) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.favorito = favorito;
        this.dataCriacao = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getTelefone() {
        return telefone;
    }
    
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public boolean isFavorito() {
        return favorito;
    }
    
    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }
    
    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
    
    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
