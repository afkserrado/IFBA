package br.edu.ifba.emprestimos_ms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifba.emprestimos_ms.entity.OutboxEvent;
import br.edu.ifba.emprestimos_ms.enums.StatusEventoOutbox;

public interface OutboxEventRepository extends JpaRepository<OutboxEvent, Long> {

    List<OutboxEvent> findTop50ByStatusOrderByCriadoEmAsc(
            StatusEventoOutbox status
    );
}