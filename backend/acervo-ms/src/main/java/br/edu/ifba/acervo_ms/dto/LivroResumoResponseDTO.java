package br.edu.ifba.acervo_ms.dto;

import java.time.LocalDateTime;

import br.edu.ifba.acervo_ms.entity.Livro;

// DTO para retorno de dados de livro nas respostas da API
public class LivroResumoResponseDTO {

    private String titulo;
    private String autor;
    private String isbn;
    private Integer quantidadeDisponivel;
    private LocalDateTime dataAtualizacao;

    public LivroResumoResponseDTO() {}

    public LivroResumoResponseDTO(Livro livro) {
        this.titulo = livro.getTitulo();
        this.autor = livro.getAutor();
        this.isbn = livro.getIsbn();
        this.quantidadeDisponivel = livro.getQuantidadeDisponivel();
        this.dataAtualizacao = livro.getDataAtualizacao();
    }

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

    public Integer getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(Integer quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}
