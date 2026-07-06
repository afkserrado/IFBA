package br.com.agenda.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.agenda.dto.ContatoDto;
import br.com.agenda.dto.ContatoResponseDto;
import br.com.agenda.entity.Contato;
import br.com.agenda.repository.ContatoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ContatoService {
    
    private ContatoRepository contatoRepository;

    public ContatoService(ContatoRepository contatoRepository) {
        this.contatoRepository = contatoRepository;
    }

    public ContatoDto criarContato(ContatoDto dto) {
        Contato contato = dto.converterParaContato();
        Contato contatoSalvo = contatoRepository.save(contato); // save() já retorna a entidade já salva
        return new ContatoDto(contatoSalvo);
    }

    public List<ContatoResponseDto> listarContatos(String sort) {
        List<Contato> contatos;
        
        if(sort == null || sort.isBlank()) {
            contatos = contatoRepository.findAll();
        }
        else {
            String[] partes = sort.split(",");
            String campo = partes[0];
            String direcao = partes.length > 1 ? partes[1] : "asc";

            Sort ordenacao = direcao.equalsIgnoreCase("desc")
                ? Sort.by(campo).descending()
                : Sort.by(campo).ascending();

            contatos = contatoRepository.findAll(ordenacao);
        }
        
        return ContatoResponseDto.converterListaParaContatoDto(contatos);
    }

    public Page<ContatoResponseDto> listarContatos(Pageable pageable, String sort) {
        Page<Contato> contatos;

        if(sort == null || sort.isBlank()) {
            contatos = contatoRepository.findAll(pageable);
        }
        else {
            String[] partes = sort.split(",");
            String campo = partes[0];
            String direcao = partes.length > 1 ? partes[1] : "asc";

            Sort ordenacao = direcao.equalsIgnoreCase("desc")
                ? Sort.by(campo).descending()
                : Sort.by(campo).ascending();

            Pageable pageableComSort = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                ordenacao
            );

            contatos = contatoRepository.findAll(pageableComSort);
        }

        return ContatoResponseDto.converterListaParaContatoDto(contatos);
    }

    public ContatoResponseDto buscarContatoPorId(Long id) {
        Contato contato = contatoRepository
                            .findById(id)
                            .orElseThrow(EntityNotFoundException::new);

        return new ContatoResponseDto(contato);
    }

    public List<ContatoResponseDto> buscarContatoPorNome(String nome) {
        List<Contato> contatos = contatoRepository
                            .findByNomeContainingIgnoreCase(nome);

        return ContatoResponseDto.converterListaParaContatoDto(contatos);
    }

    public List<ContatoResponseDto> buscarContatoFavoritos() {
        List<Contato> contatos = contatoRepository
                            .findByFavorito(true);

        return ContatoResponseDto.converterListaParaContatoDto(contatos);
    }

    public ContatoResponseDto atualizarContato(Long id, ContatoDto dto) {
        Contato contato = contatoRepository
                            .findById(id)
                            .orElseThrow(EntityNotFoundException::new);

        contato.setNome(dto.getNome());
        contato.setTelefone(dto.getTelefone());
        contato.setEmail(dto.getEmail());
        contato.setFavorito(dto.isFavorito());

        return new ContatoResponseDto(contato);
    }

    public void apagarContato(Long id) {
        Contato contato = contatoRepository
                            .findById(id)
                            .orElseThrow(EntityNotFoundException::new);

        contatoRepository.delete(contato);
    }
}
