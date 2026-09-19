package br.edu.ifba.emprestimos_ms.messaging;

import java.time.LocalDateTime;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.edu.ifba.emprestimos_ms.config.RabbitMQConfig;
import br.edu.ifba.emprestimos_ms.dto.EmprestimoCriadoEvent;
import br.edu.ifba.emprestimos_ms.dto.EmprestimoDevolvidoEvent;
import br.edu.ifba.emprestimos_ms.entity.OutboxEvent;
import br.edu.ifba.emprestimos_ms.enums.StatusEventoOutbox;
import br.edu.ifba.emprestimos_ms.repository.OutboxEventRepository;

@Component
public class OutboxProducer {

    private final OutboxEventRepository outboxEventRepository;
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public OutboxProducer(
            OutboxEventRepository outboxEventRepository,
            RabbitTemplate rabbitTemplate,
            ObjectMapper objectMapper
    ) {
        this.outboxEventRepository = outboxEventRepository;
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    @Scheduled(fixedDelayString = "${outbox.producer.delay-ms:5000}")
    public void publicarEventosPendentes() {
        var pendentes = outboxEventRepository
                .findTop50ByStatusOrderByCriadoEmAsc(StatusEventoOutbox.PENDENTE);

        for (OutboxEvent evento : pendentes) {
            publicarEvento(evento);
        }
    }

    @Transactional
    private void publicarEvento(OutboxEvent evento) {
        try {
            Object payload = reconstruirPayload(evento);

            rabbitTemplate.convertAndSend(
                    RabbitMQConfig.EXCHANGE_EMPRESTIMO,
                    evento.getRoutingKey(),
                    payload
            );

            evento.setStatus(StatusEventoOutbox.ENVIADO);
            evento.setEnviadoEm(LocalDateTime.now());
            evento.setTentativas(evento.getTentativas() + 1);
            evento.setUltimoErro(null);

            outboxEventRepository.save(evento);
        }
        
        catch (Exception ex) {
            evento.setTentativas(evento.getTentativas() + 1);
            evento.setUltimoErro(truncarErro(ex));
            // Mantém status = PENDENTE para nova tentativa
            outboxEventRepository.save(evento);
        }
    }

    private Object reconstruirPayload(OutboxEvent evento) throws JsonProcessingException {
        return switch (evento.getTipoEvento()) {
            case EMPRESTIMO_CRIADO ->
                objectMapper.readValue(
                        evento.getPayload(),
                        EmprestimoCriadoEvent.class
                );
            case EMPRESTIMO_DEVOLVIDO ->
                objectMapper.readValue(
                        evento.getPayload(),
                        EmprestimoDevolvidoEvent.class
                );
        };
    }

    private String truncarErro(Exception ex) {
        String mensagem = ex.toString();
        return mensagem.length() <= 2000 ? mensagem : mensagem.substring(0, 2000);
    }
}