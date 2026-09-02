package br.edu.ifba.acervo_ms.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

// DTO para requisições de criação e atualização de livro
public class LivroRequestDTO {

    @NotBlank
    @Size(
        min = 3,
        message = "O título deve ter pelo menos 3 caracteres."
    )
    private String titulo;

    @NotBlank
    @Size(
        min = 3,
        message = "O nome do autor deve ter pelo menos 3 caracteres."
    )
    private String autor;

    // Validação simples, sem considerar o dígito verificador
    @NotBlank
    @Pattern(
        regexp = "^(\\d{10}|\\d{13})$", 
        message = "ISBN deve conter 10 ou 13 dígitos."
    )
    private String isbn;

    @NotNull
    @Min(0)
    private Integer quantidadeTotal;

    // Construtor utilizado pelo Jackson
    // para construir um objeto a partir dos dados de um JSON
    public LivroRequestDTO() {}

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getQuantidadeTotal() {
        return quantidadeTotal;
    }

    public void setQuantidadeTotal(Integer quantidadeTotal) {
        this.quantidadeTotal = quantidadeTotal;
    }
}
