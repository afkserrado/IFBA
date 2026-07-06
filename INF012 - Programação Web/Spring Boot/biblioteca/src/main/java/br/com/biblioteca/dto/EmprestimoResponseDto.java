package br.com.biblioteca.dto;

import java.time.LocalDate;

import br.com.biblioteca.entity.Emprestimo;

// Utilizado como resposta para os métodos GET e PUT
public class EmprestimoResponseDto {

    private String nomeLeitor;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private boolean devolvido = false;
    private Long livroId;

    public EmprestimoResponseDto() {}

    public EmprestimoResponseDto(Emprestimo emprestimo) {
        this.nomeLeitor = emprestimo.getNomeLeitor();
        this.dataEmprestimo = emprestimo.getDataEmprestimo();
        this.dataPrevistaDevolucao = emprestimo.getDataPrevistaDevolucao();
        this.devolvido = emprestimo.isDevolvido();
        this.livroId = emprestimo.getLivro().getId();
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

    public boolean isDevolvido() {
        return devolvido;
    }

    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }
}
