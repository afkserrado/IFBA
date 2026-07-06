package br.com.biblioteca.dto;

import java.time.LocalDate;

import br.com.biblioteca.entity.Emprestimo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// Utilizado para cadastro e resposta do POST
public class EmprestimoRequestDto {
    
    private Long id;

    @NotBlank
    private String nomeLeitor;

    @NotNull
    private LocalDate dataEmprestimo;

    @NotNull
    private LocalDate dataPrevistaDevolucao;
    
    @NotNull
    private Long livroId;

    public EmprestimoRequestDto() {}

    public EmprestimoRequestDto(Emprestimo emprestimo) {
        this.id = emprestimo.getId();
        this.nomeLeitor = emprestimo.getNomeLeitor();
        this.dataEmprestimo = emprestimo.getDataEmprestimo();
        this.dataPrevistaDevolucao = emprestimo.getDataPrevistaDevolucao();
        this.livroId = emprestimo.getLivro().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeLeitor() {
        return nomeLeitor;
    }

    public void setNomeLeitor(String nomeLeitor) {
        this.nomeLeitor = nomeLeitor;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public void setDataPrevistaDevolucao(LocalDate dataPrevistaDevolucao) {
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
    }

    public Long getLivroId() {
        return livroId;
    }

    public void setLivroId(Long livro) {
        this.livroId = livro;
    }
}
