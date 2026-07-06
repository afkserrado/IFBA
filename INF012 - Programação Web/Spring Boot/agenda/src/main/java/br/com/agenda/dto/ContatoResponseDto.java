package br.com.agenda.dto;

import java.util.List;

import org.springframework.data.domain.Page;

import br.com.agenda.entity.Contato;

// Para resposta de GET, GET por ID e PUT
public class ContatoResponseDto {
    
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private boolean favorito;

    public ContatoResponseDto(Contato contato) {
        this.id = contato.getId();
        this.nome = contato.getNome();
        this.telefone = contato.getTelefone();
        this.email = contato.getEmail();
        this.favorito = contato.isFavorito();
    }

    public static List<ContatoResponseDto> converterListaParaContatoDto(List<Contato> contatos) {
        return contatos.stream().map(ContatoResponseDto::new).toList();
    }

    public static Page<ContatoResponseDto> converterListaParaContatoDto(Page<Contato> contatos) {
        return contatos.map(ContatoResponseDto::new);
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public boolean isFavorito() {
        return favorito;
    }
}
