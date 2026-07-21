package br.edu.ifba.emprestimos_ms.mapper;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import br.edu.ifba.emprestimos_ms.dto.EmprestimoRequest;
import br.edu.ifba.emprestimos_ms.dto.EmprestimoResponse;
import br.edu.ifba.emprestimos_ms.entity.Emprestimo;
import br.edu.ifba.emprestimos_ms.entity.StatusEmprestimo;

@Component
public class EmprestimoMapper {
	public Emprestimo toEntity(EmprestimoRequest request) {
        Emprestimo entity = new Emprestimo();
        entity.setUsuarioId(request.usuarioId());
        entity.setLivroId(request.livroId());
        entity.setDataEmprestimo(LocalDate.now());
        entity.setDataDevolucao(entity.getDataEmprestimo().plusDays(7));
        entity.setStatus(StatusEmprestimo.ATIVO);
        return entity;
    }

    public EmprestimoResponse toResponse(Emprestimo entity) {
        return new EmprestimoResponse(
            entity.getId(),
            entity.getUsuarioId(),
            entity.getLivroId(),
            entity.getDataEmprestimo(),
            entity.getDataPrevistaDevolucao(),
            entity.getDataDevolucao(),
            entity.getStatus(),
            entity.getValorMulta(),
            entity.getMultaPaga(),
            entity.getDataCriacao(),
            entity.getDataAtualizacao()
        );
    }
}
