package br.edu.ifba.acervo_ms.mapper;

import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;

import br.edu.ifba.acervo_ms.dto.LivroRequestDTO;
import br.edu.ifba.acervo_ms.dto.LivroResponseDTO;
import br.edu.ifba.acervo_ms.dto.LivroResumoResponseDTO;
import br.edu.ifba.acervo_ms.entity.Livro;

public final class LivroMapper {

    private LivroMapper() {}

    // Opcional para resolver "erro" do Visual Code
    // Indicação explícita de que o método não devolve null
    @NonNull 
    public static Livro converterDtoParaEntidade(LivroRequestDTO dto) {
        Objects.requireNonNull(dto, "O DTO não pode ser nulo.");
        
        return new Livro(
            dto.getTitulo(),
            dto.getAutor(),
            dto.getIsbn(),
            dto.getQuantidadeTotal()
        );
    }

    @NonNull
    public static LivroResponseDTO converterEntidadeParaDto(Livro livro) {
        Objects.requireNonNull(livro, "A entidade Livro não pode ser nula.");
        return new LivroResponseDTO(livro);
    }

    @NonNull
    public static LivroResumoResponseDTO converterEntidadeParaDtoResumido(Livro livro) {
        Objects.requireNonNull(livro, "A entidade Livro não pode ser nula.");
        return new LivroResumoResponseDTO(livro);
    }

    @NonNull
    public static Page<LivroResumoResponseDTO> converterEntidadesParaDtoResumido(Page<Livro> livros) {
        Objects.requireNonNull(livros, "A página de Livros não pode ser nula.");
        return livros.map(LivroResumoResponseDTO::new);
    }
}