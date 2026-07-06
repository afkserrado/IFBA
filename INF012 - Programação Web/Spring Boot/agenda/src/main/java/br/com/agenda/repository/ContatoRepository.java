package br.com.agenda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.agenda.entity.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long>{
    
    public List<Contato> findByNomeContainingIgnoreCase(String nome);
    public List<Contato> findByFavorito(boolean favorito);
}
