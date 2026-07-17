package br.edu.ifba.acervo_ms.service;

import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifba.acervo_ms.client.EmprestimoClient;
import br.edu.ifba.acervo_ms.dto.LivroRequestDTO;
import br.edu.ifba.acervo_ms.dto.LivroResponseDTO;
import br.edu.ifba.acervo_ms.dto.LivroResumoResponseDTO;
import br.edu.ifba.acervo_ms.entity.Livro;
import br.edu.ifba.acervo_ms.enums.OrdenacaoLivro;
import br.edu.ifba.acervo_ms.exception.LivroNaoEncontradoException;
import br.edu.ifba.acervo_ms.exception.OperacaoNaoPermitidaException;
import br.edu.ifba.acervo_ms.mapper.LivroMapper;
import br.edu.ifba.acervo_ms.repository.LivroRepository;

@Service
public class LivroService {
    
    private final LivroRepository livroRepository;
    private final EmprestimoClient emprestimoClient;

    public LivroService(LivroRepository livroRepository, EmprestimoClient emprestimoClient) {
        this.livroRepository = livroRepository;
        this.emprestimoClient = emprestimoClient;
    }

    @Transactional
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
        return LivroMapper.converterEntidadeParaDto(livroSalvo);
    }

    public Page<LivroResumoResponseDTO> buscarLivros(String ordenacao, Pageable pageable) {
        Pageable pageableComSort = criarPageableComOrdenacao(pageable, ordenacao);
        Page<Livro> livros = livroRepository.findAll(pageableComSort);
        return LivroMapper.converterEntidadesParaDtoResumido(livros);
    }

    public LivroResumoResponseDTO buscarLivroPorIsbn(String isbn) {
        
        Livro livro = livroRepository.findByIsbn(isbn)
            .orElseThrow(LivroNaoEncontradoException::new);

        return LivroMapper.converterEntidadeParaDtoResumido(livro);
    }

    public Page<LivroResumoResponseDTO> buscarLivrosPorAutor(String autor, String ordenacao, Pageable pageable) {
        Pageable pageableComSort = criarPageableComOrdenacao(pageable, ordenacao);
        Page<Livro> livros = livroRepository.findByAutorContainingIgnoreCase(autor, pageableComSort);
        return LivroMapper.converterEntidadesParaDtoResumido(livros);
    }

    public Page<LivroResumoResponseDTO> buscarLivrosPorTitulo(String titulo, String ordenacao, Pageable pageable) {
        Pageable pageableComSort = criarPageableComOrdenacao(pageable, ordenacao);
        Page<Livro> livros = livroRepository.findByTituloContainingIgnoreCase(titulo, pageableComSort);
        return LivroMapper.converterEntidadesParaDtoResumido(livros);
    }

    @Transactional
    public LivroResumoResponseDTO atualizarLivro(Long id, LivroRequestDTO dto) {

        if(livroRepository.existsByIsbnAndIdNot(dto.getIsbn(), id)) {
            throw new OperacaoNaoPermitidaException(
            "Já existe um livro com esse ISBN");
        }

        Livro livro = obterLivro(id);

        int emprestados = livro.getQuantidadeTotal() - livro.getQuantidadeDisponivel();

        if(dto.getQuantidadeTotal() < emprestados) { 
            throw new OperacaoNaoPermitidaException("A quantidade total não pode ser menor que a quantidade de exemplares emprestados.");
        }

        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setIsbn(dto.getIsbn());
        livro.setQuantidadeTotal(dto.getQuantidadeTotal());
        livro.setQuantidadeDisponivel(dto.getQuantidadeTotal() - emprestados);

        return LivroMapper.converterEntidadeParaDtoResumido(livro);
    }

    @Transactional
    public void removerLivro(Long id) {
        
        Livro livro = obterLivro(id);

        try {

            Boolean possuiEmprestimosAtivos = emprestimoClient.existeEmprestimoAtivoPorLivro(id);

            if(Boolean.TRUE.equals(possuiEmprestimosAtivos)) {
                throw new OperacaoNaoPermitidaException(
                "O livro não pode ser excluído, pois possui empréstimos ativos."
                );  
            }
        } 

        catch (OperacaoNaoPermitidaException ex) {
            throw ex;
        }

        // Falha de conexão com emprestimos-ms
        catch(Exception ex) {
            throw new OperacaoNaoPermitidaException(
            "Não foi possível validar os empréstimos ativos do livro no momento."
            );
        }

        livroRepository.delete(livro);
    }

    public boolean estaDisponivel(Long id) {
        Livro livro = obterLivro(id);
        return livro.getQuantidadeDisponivel() > 0;
    }

    @Transactional
    public void reduzirEstoque(Long id) {
        
        Livro livro = obterLivro(id);

        if (livro.getQuantidadeDisponivel() <= 0) {
            throw new OperacaoNaoPermitidaException(
                "O livro não possui exemplares disponíveis para empréstimo."
            );
        }

        livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel() - 1);
    }

    @Transactional
    public void aumentarEstoque(Long id) {
        
        Livro livro = obterLivro(id);
        
        if (livro.getQuantidadeDisponivel() >= livro.getQuantidadeTotal()) {
            throw new OperacaoNaoPermitidaException(
                "A quantidade disponível não pode ser maior que a quantidade total."
            );
        }

        livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel() + 1);
    }

    private Pageable criarPageableComOrdenacao(Pageable pageable, String ordenacao) {
        
        Sort sort = OrdenacaoLivro.resolverSort(ordenacao);

        return PageRequest.of(
            pageable.getPageNumber(),
            pageable.getPageSize(),
            sort
        );
    }

    private Livro obterLivro(Long id) {
        return livroRepository.findById(id)
            .orElseThrow(LivroNaoEncontradoException::new);
    }
}
