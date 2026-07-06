package br.com.biblioteca.dto;

import br.com.biblioteca.entity.Livro;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// Utilizado para cadastro e resposta do POST
public class LivroRequestDto {

    private Long id;

    @NotBlank
    @Size(
        min = 3,
        message = "O título deve ter pelo menos 3 caracteres."
    )
    private String titulo;

    @NotBlank
    private String autor;

    @NotBlank
    private String isbn;

    @NotNull
    @Min(0)
    private Integer quantidadeDisponivel; 

    public LivroRequestDto() {}

    public LivroRequestDto(Livro livro) {
        this.id = livro.getId();
        this.titulo = livro.getTitulo();
        this.autor = livro.getAutor();
        this.isbn = livro.getIsbn();
        this.quantidadeDisponivel = livro.getQuantidadeDisponivel();
    }

    public Livro converterDtoParaEntidade() {
        return new Livro(titulo, autor, isbn, quantidadeDisponivel);
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getIsbn() {
        return isbn;
    }  

    public Integer getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }
}
