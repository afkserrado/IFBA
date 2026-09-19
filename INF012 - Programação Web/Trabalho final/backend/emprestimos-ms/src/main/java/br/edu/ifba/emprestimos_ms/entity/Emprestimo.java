package br.edu.ifba.emprestimos_ms.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.edu.ifba.emprestimos_ms.enums.StatusEmprestimo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Representa a entidade "emprestimos" no banco de dados
@Entity
@Table(name = "emprestimos")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long usuarioId;

    @Column(nullable = false)
    private Long livroId;

    @Column(nullable = false)
    private LocalDateTime dataEmprestimo;

    @Column(nullable = false)
    private LocalDateTime dataPrevistaDevolucao;

    private LocalDateTime dataDevolucao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusEmprestimo status;

    @Column(nullable = false)
    private BigDecimal valorMulta;

    @Column(nullable = false)
    private Boolean multaPaga;

    @Column(
        nullable = false,
        updatable = false
    )
    private LocalDateTime dataCriacao;

    @Column(nullable = false)
    private LocalDateTime dataAtualizacao;

    // Construtor utilizado pelo JPA/Hibernate para reconstruir
    // a partir dos dados do banco
    public Emprestimo() {}

    // Construtor utilizado pelo mapper correspondente
    // para construir um objeto a partir dos dados de um dto
    public Emprestimo(Long usuarioId, Long livroId) {
        this.usuarioId = usuarioId;
        this.livroId = livroId;
        this.dataEmprestimo = LocalDateTime.now();
        this.dataPrevistaDevolucao = this.dataEmprestimo.plusDays(7);
        this.status = StatusEmprestimo.ATIVO;
        this.valorMulta = BigDecimal.ZERO;
        this.multaPaga = false;
        this.dataCriacao = LocalDateTime.now();
        this.dataAtualizacao = LocalDateTime.now();
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

    public LocalDateTime getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDateTime dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDateTime getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public void setDataPrevistaDevolucao(LocalDateTime dataPrevistaDevolucao) {
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
    }

    public LocalDateTime getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDateTime dataDevolucao) {
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