package br.com.biblioteca.service;

import java.util.List;

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
        
        Livro livro = dto.converterDtoParaEntidade();
        Livro livroSalvo = livroRepository.save(livro);
        return new LivroRequestDto(livroSalvo);
    }

    public List<LivroResponseDto> buscarLivros() {
        return LivroResponseDto.converterEntidadesParaDto(
            livroRepository.findAll()
        );
    }

    public LivroResponseDto buscarLivroPorId(Long id) {
        Livro livro = livroRepository
            .findById(id)
            .orElseThrow(LivroNaoEncontradoException::new);

        return new LivroResponseDto(livro);
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
