package br.com.biblioteca.dto;

import java.util.List;

import org.springframework.data.domain.Page;

import br.com.biblioteca.entity.Livro;

// Utilizado como resposta para os métodos GET e PUT
public class LivroResponseDto {

    private String titulo;
    private String autor;
    private String isbn;
    private Integer quantidadeDisponivel; 

    public LivroResponseDto() {}

    public LivroResponseDto(Livro livro) {
        this.titulo = livro.getTitulo();
        this.autor = livro.getAutor();
        this.isbn = livro.getIsbn();
        this.quantidadeDisponivel = livro.getQuantidadeDisponivel();
    }
    
    public static List<LivroResponseDto> converterEntidadesParaDto(List<Livro> livros) {
        return livros.stream().map(LivroResponseDto::new).toList();
    }

    public static Page<LivroResponseDto> converterEntidadesParaDto(Page<Livro> livros) {
        return livros.map(LivroResponseDto::new);
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
