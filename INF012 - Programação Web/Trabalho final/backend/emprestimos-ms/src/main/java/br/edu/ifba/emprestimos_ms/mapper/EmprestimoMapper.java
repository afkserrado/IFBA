package br.edu.ifba.emprestimos_ms.mapper;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import br.edu.ifba.emprestimos_ms.dto.EmprestimoRequestDTO;
import br.edu.ifba.emprestimos_ms.dto.EmprestimoResponseDTO;
import br.edu.ifba.emprestimos_ms.entity.Emprestimo;
import br.edu.ifba.emprestimos_ms.enums.StatusEmprestimo;

@Component
public class EmprestimoMapper {
	public Emprestimo toEntity(EmprestimoRequestDTO request) {
        Emprestimo entity = new Emprestimo();
        entity.setUsuarioId(request.usuarioId());
        entity.setLivroId(request.livroId());
        entity.setDataEmprestimo(LocalDate.now());
        entity.setDataPrevistaDevolucao(entity.getDataEmprestimo().plusDays(7));
        entity.setStatus(StatusEmprestimo.ATIVO);
        return entity;
    }

    public EmprestimoResponseDTO toResponse(Emprestimo entity) {
        return new EmprestimoResponseDTO(
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
