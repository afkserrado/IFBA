package br.com.biblioteca.service;

import org.springframework.stereotype.Service;

import br.com.biblioteca.dto.EstatisticasResponseDto;
import br.com.biblioteca.repository.EmprestimoRepository;
import br.com.biblioteca.repository.LivroRepository;

@Service
public class EstatisticasService {

    private final LivroRepository livroRepository;
    private final EmprestimoRepository emprestimoRepository;

    public EstatisticasService(
        LivroRepository livroRepository,
        EmprestimoRepository emprestimoRepository
    ) {
        this.livroRepository = livroRepository;
        this.emprestimoRepository = emprestimoRepository;
    }

    public EstatisticasResponseDto gerarEstatisticas() {
        Long totalLivros = livroRepository.count();
        Long emprestimosAtivos = emprestimoRepository.countByDevolvidoFalse();
        Long livrosDisponiveis = livroRepository.sumByQuantidadeDisponivel();

        return new EstatisticasResponseDto(
            totalLivros,
            emprestimosAtivos,
            livrosDisponiveis
        );
    }
}