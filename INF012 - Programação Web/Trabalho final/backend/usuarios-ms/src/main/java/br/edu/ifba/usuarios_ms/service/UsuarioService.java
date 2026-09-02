package br.edu.ifba.usuarios_ms.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifba.usuarios_ms.client.EmprestimoClient;
import br.edu.ifba.usuarios_ms.dto.UsuarioRequestDTO;
import br.edu.ifba.usuarios_ms.dto.UsuarioResponseDTO;
import br.edu.ifba.usuarios_ms.dto.UsuarioUpdateRequestDTO;
import br.edu.ifba.usuarios_ms.entity.Usuario;
import br.edu.ifba.usuarios_ms.exception.ResourceNotFoundException;
import br.edu.ifba.usuarios_ms.messaging.UsuarioProducer;
import br.edu.ifba.usuarios_ms.repository.UsuarioRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioProducer usuarioProducer;
    private final EmprestimoClient emprestimoClient;

    public UsuarioService(UsuarioRepository usuarioRepository,
            UsuarioProducer usuarioProducer,
            EmprestimoClient emprestimoClient) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioProducer = usuarioProducer;
        this.emprestimoClient = emprestimoClient;
    }

    public boolean existeUsuario(Long id) {
        return usuarioRepository.existsById(id);
    }

    @Transactional
    public UsuarioResponseDTO criarUsuario(UsuarioRequestDTO dto) {
        if (usuarioRepository.existsByEmail(dto.email())) {
            throw new IllegalArgumentException("E-mail já cadastrado.");
        }
        if (usuarioRepository.existsByCpf(dto.cpf())) {
            throw new IllegalArgumentException("CPF já cadastrado.");
        }

        String papelDefinido = "USER"; // Padrao comum

        Usuario usuario = new Usuario();
        usuario.setCpf(dto.cpf());
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(dto.senha());
        usuario.setRole(papelDefinido);
        usuario.setCreatedAt(LocalDateTime.now());
        usuario.setUpdatedAt(LocalDateTime.now());

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        usuarioProducer.dispararUsuarioCriado(usuarioSalvo.getId(), usuarioSalvo.getNome(), usuarioSalvo.getEmail());
        return new UsuarioResponseDTO(usuarioSalvo);
    }

    @Transactional(readOnly = true)
    public UsuarioResponseDTO buscarPorId(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o ID: " + id));
        return new UsuarioResponseDTO(usuario);
    }

    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> buscarTodos() {

        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioResponseDTO::new)
                .toList();
    }

    @Transactional
    public UsuarioResponseDTO editar(Long id, UsuarioUpdateRequestDTO dto) {

        Usuario usuarioAlvo = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o ID: " + id));

        if (!usuarioAlvo.getEmail().equals(dto.email()) && usuarioRepository.existsByEmail(dto.email())) {
            throw new IllegalArgumentException("E-mail já está em uso por outro usuário.");
        }

        usuarioAlvo.setNome(dto.nome());
        usuarioAlvo.setEmail(dto.email());
        usuarioAlvo.setRole(dto.role());
        usuarioAlvo.setUpdatedAt(LocalDateTime.now());

        Usuario usuarioAtualizado = usuarioRepository.save(usuarioAlvo);
        return new UsuarioResponseDTO(usuarioAtualizado);
    }

    @Transactional
    public void remover(Long id) {

        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuário não encontrado com o ID: " + id);
        }

        // Valida se ha pendencias em emprestimos antes de apagar
        if (emprestimoClient.possuiEmprestimosAtivos(id)) {
            throw new IllegalStateException("Não é possível excluir a conta: existem empréstimos ativos.");
        }

        if (emprestimoClient.possuiMultasPendentes(id)) {
            throw new IllegalStateException("Não é possível excluir a conta: existem multas financeiras pendentes.");
        }

        usuarioRepository.deleteById(id);
        usuarioProducer.dispararUsuarioDeletado(id);
    }

    @Transactional(readOnly = true)
    public UsuarioResponseDTO buscarPorEmail(String email) {

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o e-mail: " + email));
        return new UsuarioResponseDTO(usuario);
    }
}