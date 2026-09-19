package br.edu.ifba.emprestimos_ms.entity;

import java.time.LocalDateTime;

import br.edu.ifba.emprestimos_ms.enums.StatusEventoOutbox;
import br.edu.ifba.emprestimos_ms.enums.TipoEventoOutbox;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "outbox_eventos")
public class OutboxEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private TipoEventoOutbox tipoEvento;

    @Column(nullable = false, length = 100)
    private String routingKey;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String payload;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusEventoOutbox status;

    @Column(nullable = false)
    private Integer tentativas;

    @Column(length = 2000)
    private String ultimoErro;

    @Column(nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    private LocalDateTime enviadoEm;

    public OutboxEvent() {
    }

    public OutboxEvent(
            TipoEventoOutbox tipoEvento,
            String routingKey,
            String payload
    ) {
        this.tipoEvento = tipoEvento;
        this.routingKey = routingKey;
        this.payload = payload;
        this.status = StatusEventoOutbox.PENDENTE;
        this.tentativas = 0;
        this.criadoEm = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public TipoEventoOutbox getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(TipoEventoOutbox tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public StatusEventoOutbox getStatus() {
        return status;
    }

    public void setStatus(StatusEventoOutbox status) {
        this.status = status;
    }

    public Integer getTentativas() {
        return tentativas;
    }

    public void setTentativas(Integer tentativas) {
        this.tentativas = tentativas;
    }

    public String getUltimoErro() {
        return ultimoErro;
    }

    public void setUltimoErro(String ultimoErro) {
        this.ultimoErro = ultimoErro;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public LocalDateTime getEnviadoEm() {
        return enviadoEm;
    }

    public void setEnviadoEm(LocalDateTime enviadoEm) {
        this.enviadoEm = enviadoEm;
    }
}