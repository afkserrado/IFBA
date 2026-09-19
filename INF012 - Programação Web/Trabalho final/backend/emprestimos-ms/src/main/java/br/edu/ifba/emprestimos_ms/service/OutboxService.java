package br.edu.ifba.emprestimos_ms.service;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.edu.ifba.emprestimos_ms.config.RabbitMQConfig;
import br.edu.ifba.emprestimos_ms.dto.EmprestimoCriadoEvent;
import br.edu.ifba.emprestimos_ms.dto.EmprestimoDevolvidoEvent;
import br.edu.ifba.emprestimos_ms.entity.OutboxEvent;
import br.edu.ifba.emprestimos_ms.enums.TipoEventoOutbox;
import br.edu.ifba.emprestimos_ms.repository.OutboxEventRepository;

// Converte cada evento em JSON e persiste um OutboxEvent com status PENDENTE
@Service
public class OutboxService {

    private final OutboxEventRepository outboxEventRepository;
    private final ObjectMapper objectMapper;

    public OutboxService(
            OutboxEventRepository outboxEventRepository,
            ObjectMapper objectMapper
    ) {
        this.outboxEventRepository = outboxEventRepository;
        this.objectMapper = objectMapper;
    }

    public void registrarEmprestimoCriado(EmprestimoCriadoEvent evento) {
        registrarEvento(
                TipoEventoOutbox.EMPRESTIMO_CRIADO,
                RabbitMQConfig.ROUTING_KEY_EMPRESTIMO_CRIADO,
                evento
        );
    }

    public void registrarEmprestimoDevolvido(EmprestimoDevolvidoEvent evento) {
        registrarEvento(
                TipoEventoOutbox.EMPRESTIMO_DEVOLVIDO,
                RabbitMQConfig.ROUTING_KEY_EMPRESTIMO_DEVOLVIDO,
                evento
        );
    }

    private void registrarEvento(
            TipoEventoOutbox tipoEvento,
            String routingKey,
            Object evento
    ) {
        try {
            String payload = objectMapper.writeValueAsString(evento);

            OutboxEvent outboxEvent = new OutboxEvent(
                    tipoEvento,
                    routingKey,
                    payload
            );

            outboxEventRepository.save(outboxEvent);
        }
        
        catch (JsonProcessingException ex) {
            throw new IllegalStateException(
                    "Não foi possível serializar o evento de empréstimo.",
                    ex
            );
        }
    }
}