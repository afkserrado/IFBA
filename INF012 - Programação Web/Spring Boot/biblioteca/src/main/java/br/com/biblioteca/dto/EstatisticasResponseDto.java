package br.com.biblioteca.dto;

public record EstatisticasResponseDto(
    Long totalLivros, // quantidade de títulos/livros cadastrados
    Long emprestimosAtivos, // quantidade de empréstimos não devolvidos (exemplares)
    Long livrosDisponiveis // soma dos exemplares disponíveis
) {}