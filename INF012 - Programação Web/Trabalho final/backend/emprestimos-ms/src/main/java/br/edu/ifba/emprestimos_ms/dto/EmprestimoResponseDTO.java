package br.edu.ifba.emprestimos_ms.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import br.edu.ifba.emprestimos_ms.entity.Emprestimo;
import br.edu.ifba.emprestimos_ms.enums.StatusEmprestimo;

// DTO para retorno de dados de empréstimo nas respostas da API
public class EmprestimoResponseDTO {

    private Long id;
    private Long usuarioId;
    private Long livroId;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolucao;
    private StatusEmprestimo status;
    private BigDecimal valorMulta;
    private Boolean multaPaga;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    public EmprestimoResponseDTO() {}

    public EmprestimoResponseDTO(Emprestimo emprestimo) {
        this.id = emprestimo.getId();
        this.usuarioId = emprestimo.getUsuarioId();
        this.livroId = emprestimo.getLivroId();
        this.dataEmprestimo = emprestimo.getDataEmprestimo();
        this.dataPrevistaDevolucao = emprestimo.getDataPrevistaDevolucao();
        this.dataDevolucao = emprestimo.getDataDevolucao();
        this.status = emprestimo.getStatus();
        this.valorMulta = emprestimo.getValorMulta();
        this.multaPaga = emprestimo.getMultaPaga();
        this.dataCriacao = emprestimo.getDataCriacao();
        this.dataAtualizacao = emprestimo.getDataAtualizacao();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getLivroId() {
        return livroId;
    }

    public void setLivroId(Long livroId) {
        this.livroId = livroId;
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

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public StatusEmprestimo getStatus() {
        return status;
    }

    public void setStatus(StatusEmprestimo status) {
        this.status = status;
    }

    public BigDecimal getValorMulta() {
        return valorMulta;
    }

    public void setValorMulta(BigDecimal valorMulta) {
        this.valorMulta = valorMulta;
    }

    public Boolean getMultaPaga() {
        return multaPaga;
    }

    public void setMultaPaga(Boolean multaPaga) {
        this.multaPaga = multaPaga;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}