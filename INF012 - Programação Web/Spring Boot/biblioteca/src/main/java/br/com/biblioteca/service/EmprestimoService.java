package br.com.biblioteca.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.biblioteca.dto.EmprestimoRequestDto;
import br.com.biblioteca.dto.EmprestimoResponseDto;
import br.com.biblioteca.entity.Emprestimo;
import br.com.biblioteca.entity.Livro;
import br.com.biblioteca.exception.EmprestimoNaoEncontradoException;
import br.com.biblioteca.exception.LivroIndisponivelException;
import br.com.biblioteca.exception.LivroNaoEncontradoException;
import br.com.biblioteca.exception.OperacaoNaoPermitidaException;
import br.com.biblioteca.repository.EmprestimoRepository;
import br.com.biblioteca.repository.LivroRepository;
import jakarta.transaction.Transactional;

@Service
public class EmprestimoService {
    
    private EmprestimoRepository emprestimoRepository;
    private LivroRepository livroRepository;

    public EmprestimoService(EmprestimoRepository emprestimoRepository, LivroRepository livroRepository) {
        this.emprestimoRepository = emprestimoRepository;
        this.livroRepository = livroRepository;
    }

    @Transactional
    public EmprestimoRequestDto registrarEmprestimo(EmprestimoRequestDto dto) {
        
        Livro livro = livroRepository
            .findById(dto.getLivroId())
            .orElseThrow(LivroNaoEncontradoException::new);

        Integer qtdExemplares = livro.getQuantidadeDisponivel();

        if(qtdExemplares <= 0) {
            throw new LivroIndisponivelException();
        }

        livro.setQuantidadeDisponivel(qtdExemplares - 1);

        Emprestimo emprestimo = dto.converterDtoParaEntidade(livro);
        Emprestimo emprestimoSalvo = emprestimoRepository.save(emprestimo);

        return new EmprestimoRequestDto(emprestimoSalvo);
    }

    public List<EmprestimoResponseDto> listarEmprestimos() {
        return EmprestimoResponseDto
                .converterEntidadesParaDto(emprestimoRepository.findAll());
    }

    public EmprestimoResponseDto buscarEmprestimoPorId(Long id) {
        
        Emprestimo emprestimo = emprestimoRepository
                                    .findById(id)
                                    .orElseThrow(EmprestimoNaoEncontradoException::new);

        return new EmprestimoResponseDto(emprestimo);
    }

    public List<EmprestimoResponseDto> listarEmprestimosEmAtraso() {
        List<Emprestimo> emprestimos = emprestimoRepository
            .findByDevolvidoFalseAndDataPrevistaDevolucaoBefore(LocalDate.now());

        return EmprestimoResponseDto.converterEntidadesParaDto(emprestimos);
    }

    @Transactional
    public EmprestimoResponseDto registrarDevolucao(Long id) {

        Emprestimo emprestimo = emprestimoRepository
                                    .findById(id)
                                    .orElseThrow(EmprestimoNaoEncontradoException::new);

        if(emprestimo.isDevolvido()) {
            throw new OperacaoNaoPermitidaException("Não é possível devolver um livro já devolvido.");
        }

        emprestimo.setDevolvido(true);
        
        Livro livro = emprestimo.getLivro();
        livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel() + 1);

        return new EmprestimoResponseDto(emprestimo);
    }
}
