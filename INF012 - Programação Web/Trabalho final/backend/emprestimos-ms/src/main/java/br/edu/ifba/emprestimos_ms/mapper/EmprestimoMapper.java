package br.edu.ifba.emprestimos_ms.mapper;

import java.util.Objects;

import org.springframework.lang.NonNull;

import br.edu.ifba.emprestimos_ms.dto.EmprestimoRequestDTO;
import br.edu.ifba.emprestimos_ms.dto.EmprestimoResponseDTO;
import br.edu.ifba.emprestimos_ms.entity.Emprestimo;

public final class EmprestimoMapper {

    private EmprestimoMapper() {}

    // Opcional para resolver "erro" do Visual Code
    // Indicação explícita de que o método não devolve null
    @NonNull
    public static Emprestimo converterDtoParaEntidade(EmprestimoRequestDTO dto) {
        Objects.requireNonNull(dto, "O DTO não pode ser nulo.");

        return new Emprestimo(
            dto.getUsuarioId(),
            dto.getLivroId()
        );
    }

    @NonNull
    public static EmprestimoResponseDTO converterEntidadeParaDto(Emprestimo emprestimo) {
        Objects.requireNonNull(emprestimo, "A entidade Emprestimo não pode ser nula.");
        return new EmprestimoResponseDTO(emprestimo);
    }
}