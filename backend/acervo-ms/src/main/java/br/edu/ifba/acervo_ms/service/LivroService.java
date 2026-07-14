package br.edu.ifba.acervo_ms.service;

import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.edu.ifba.acervo_ms.dto.LivroRequestDTO;
import br.edu.ifba.acervo_ms.dto.LivroResponseDTO;
import br.edu.ifba.acervo_ms.dto.LivroResumoResponseDTO;
import br.edu.ifba.acervo_ms.entity.Livro;
import br.edu.ifba.acervo_ms.exception.OperacaoNaoPermitidaException;
import br.edu.ifba.acervo_ms.mapper.LivroMapper;
import br.edu.ifba.acervo_ms.repository.LivroRepository;

@Service
public class LivroService {
    
    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public LivroResponseDTO cadastrarLivro(LivroRequestDTO dto) {

        if(livroRepository.existsByIsbn(dto.getIsbn())) {
            throw new OperacaoNaoPermitidaException(
                "Já existe um livro com esse ISBN");
        }

        Livro livro = Objects.requireNonNull(
            LivroMapper.converterDtoParaEntidade(dto),
            "Erro ao converter DTO para entidade Livro."
        );

        Livro livroSalvo = livroRepository.save(livro);
        return new LivroResponseDTO(livroSalvo);
    }

    // Adicionar split do sort

    // public List<LivroResumoResponseDTO> buscarLivros(String sort) {

    //     List<Livro> livros;

    //     if (sort == null || sort.isBlank()) {
    //         livros = livroRepository.findAll();
    //     }
        
    //     else if (sort.equals("titulo") || sort.equals("autor")) {
    //         livros = livroRepository.findAll(Sort.by(sort));
    //     }
        
    //     else {
    //         throw new OperacaoNaoPermitidaException("Ordenação permitida apenas por título ou autor.");
    //     }

    //     return LivroMapper.converterEntidadesParaDtoResumido(livros);
    // }

    // public Page<LivroResumoResponseDTO> buscarLivros(Pageable pageable, String sort) {

    //     Page<Livro> livros;

    //     if (sort == null || sort.isBlank()) {
    //         livros = livroRepository.findAll(pageable);
    //     }
        
    //     else if (sort.equals("titulo") || sort.equals("autor")) {
    //         Pageable pageableComSort = PageRequest.of(
    //             pageable.getPageNumber(),
    //             pageable.getPageSize(),
    //             Sort.by(sort)
    //         );

    //         livros = livroRepository.findAll(pageableComSort);
    //     }
        
    //     else {
    //         throw new OperacaoNaoPermitidaException("Ordenação permitida apenas por título ou autor.");
    //     }

    //     return LivroResponseDto.converterEntidadesParaDto(livros);
    // }
}
