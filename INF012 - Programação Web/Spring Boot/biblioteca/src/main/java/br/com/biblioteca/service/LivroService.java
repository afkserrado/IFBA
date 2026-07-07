package br.com.biblioteca.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.biblioteca.dto.LivroRequestDto;
import br.com.biblioteca.dto.LivroResponseDto;
import br.com.biblioteca.entity.Livro;
import br.com.biblioteca.exception.LivroNaoEncontradoException;
import br.com.biblioteca.exception.OperacaoNaoPermitidaException;
import br.com.biblioteca.repository.EmprestimoRepository;
import br.com.biblioteca.repository.LivroRepository;

@Service
public class LivroService {
    
    private final LivroRepository livroRepository;
    private final EmprestimoRepository emprestimoRepository;

    public LivroService(LivroRepository livroRepository, EmprestimoRepository emprestimoRepository) {
        this.livroRepository = livroRepository;
        this.emprestimoRepository = emprestimoRepository;
    }

    @Transactional // Redundância
    public LivroRequestDto cadastrarLivro(LivroRequestDto dto) {
        
        if (livroRepository.existsByIsbn(dto.getIsbn())) {
            throw new OperacaoNaoPermitidaException(
                "Já existe um livro com este ISBN."
            );
        }

        Livro livro = dto.converterDtoParaEntidade();
        Livro livroSalvo = livroRepository.save(livro);
        return new LivroRequestDto(livroSalvo);
    }

    public List<LivroResponseDto> buscarLivros(String sort) {

        List<Livro> livros;

        if (sort == null || sort.isBlank()) {
            livros = livroRepository.findAll();
        } 
        
        else if (sort.equals("titulo") || sort.equals("autor")) {
            livros = livroRepository.findAll(Sort.by(sort));
        }
        
        else {
            throw new OperacaoNaoPermitidaException("Ordenação permitida apenas por título ou autor.");
        }

        return LivroResponseDto.converterEntidadesParaDto(livros);
    }

    public Page<LivroResponseDto> buscarLivros(Pageable pageable, String sort) {

        Page<Livro> livros;

        if (sort == null || sort.isBlank()) {
            livros = livroRepository.findAll(pageable);
        } 
        
        else if (sort.equals("titulo") || sort.equals("autor")) {
            Pageable pageableComSort = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(sort)
            );

            livros = livroRepository.findAll(pageableComSort);
        }
        
        else {
            throw new OperacaoNaoPermitidaException("Ordenação permitida apenas por título ou autor.");
        }

        return LivroResponseDto.converterEntidadesParaDto(livros);
    }

    public LivroResponseDto buscarLivroPorId(Long id) {
        Livro livro = livroRepository
            .findById(id)
            .orElseThrow(LivroNaoEncontradoException::new);

        return new LivroResponseDto(livro);
    }

    public List<LivroResponseDto> buscarLivrosPorAutor(String autor) {
        
        List<Livro> livros = livroRepository.findByAutorContainingIgnoreCase(autor);
        return LivroResponseDto.converterEntidadesParaDto(livros);
    }

    public Page<LivroResponseDto> buscarLivrosPorAutor(String autor, Pageable pageable) {
        
        Page<Livro> livros = livroRepository.findByAutorContainingIgnoreCase(autor, pageable);
        return LivroResponseDto.converterEntidadesParaDto(livros);
    }

    public List<LivroResponseDto> buscarLivrosPorTitulo(String titulo) {
        
        List<Livro> livros = livroRepository.findByTituloContainingIgnoreCase(titulo);
        return LivroResponseDto.converterEntidadesParaDto(livros);
    }

    public Page<LivroResponseDto> buscarLivrosPorTitulo(String titulo, Pageable pageable) {
        
        Page<Livro> livros = livroRepository.findByTituloContainingIgnoreCase(titulo, pageable);
        return LivroResponseDto.converterEntidadesParaDto(livros);
    }

    @Transactional
    public LivroResponseDto atualizarLivro(Long id, LivroRequestDto dto) {
        
        Livro livro = livroRepository
            .findById(id)
            .orElseThrow(LivroNaoEncontradoException::new);

        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setIsbn(dto.getIsbn());
        livro.setQuantidadeDisponivel(dto.getQuantidadeDisponivel());

        livroRepository.save(livro); // Redundância

        return new LivroResponseDto(livro);
    }

    @Transactional
    public void removerLivro(Long id) {
        
        Livro livro = livroRepository
            .findById(id)
            .orElseThrow(LivroNaoEncontradoException::new);

        if(emprestimoRepository.existsByLivroIdAndDevolvidoFalse(id)) {
            throw new OperacaoNaoPermitidaException("O livro não pode ser excluído, pois possui empréstimos ativos.");
        }

        livroRepository.delete(livro);
    }
}
