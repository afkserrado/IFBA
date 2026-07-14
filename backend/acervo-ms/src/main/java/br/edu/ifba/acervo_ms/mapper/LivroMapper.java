package br.edu.ifba.acervo_ms.mapper;

import java.util.List;

import org.springframework.data.domain.Page;

import br.edu.ifba.acervo_ms.dto.LivroRequestDTO;
import br.edu.ifba.acervo_ms.dto.LivroResponseDTO;
import br.edu.ifba.acervo_ms.dto.LivroResumoResponseDTO;
import br.edu.ifba.acervo_ms.entity.Livro;

public class LivroMapper {
    
    // Método utilizado pelo service correspondente
    // para construir um objeto a partir dos dados de um dto
    public static Livro converterDtoParaEntidade(LivroRequestDTO dto) {      
        return new Livro(
            dto.getTitulo(),
            dto.getAutor(),
            dto.getIsbn(),
            dto.getQuantidadeTotal()
        );
    }

    // Método utilizado pelo service correspondente
    // para retornar a resposta de uma entidade criada
    public static LivroResponseDTO converterEntidadeParaDto(Livro livro) {
        return new LivroResponseDTO(livro);
    }

    // Método utilizado pelo service correspondente
    // para retornar a resposta de uma consulta ou atualização de entidade
    public static LivroResumoResponseDTO converterEntidadeParaDtoResumido(Livro livro) {
        return new LivroResumoResponseDTO(livro);
    }

    // Método utilizado pelo service correspondente
    // para retornar a resposta de uma consulta ao banco
    public static List<LivroResumoResponseDTO> converterEntidadesParaDtoResumido(List<Livro> livros) {
        return livros.stream().map(LivroResumoResponseDTO::new).toList();
    }

    // Método utilizado pelo service correspondente
    // para retornar a resposta de uma consulta ao banco
    public static Page<LivroResumoResponseDTO> converterEntidadesParaDtoResumido(Page<Livro> livros) {
        return livros.map(LivroResumoResponseDTO::new);
    }
}
