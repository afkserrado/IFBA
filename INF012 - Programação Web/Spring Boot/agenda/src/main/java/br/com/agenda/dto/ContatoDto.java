package br.com.agenda.dto;

import java.time.LocalDateTime;

import br.com.agenda.entity.Contato;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

// Para resposta de POST
public class ContatoDto {
    
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

    private boolean favorito;
    private LocalDateTime dataCriacao;

    public ContatoDto() {}

    public ContatoDto(Contato contato) {
        this.id = contato.getId();
        this.nome = contato.getNome();
        this.telefone = contato.getTelefone();
        this.email = contato.getEmail();
        this.favorito = contato.isFavorito();
        this.dataCriacao = contato.getDataCriacao();
    }

    public Contato converterParaContato() {
        return new Contato(nome, telefone, email, favorito);
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
}
