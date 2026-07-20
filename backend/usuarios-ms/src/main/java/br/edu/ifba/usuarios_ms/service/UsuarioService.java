package br.edu.ifba.usuarios_ms.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final BCryptPasswordEncoder passwordEncoder;
    private final UsuarioProducer usuarioProducer;
    private final EmprestimoClient emprestimoClient;

    public UsuarioService(UsuarioRepository usuarioRepository, 
                          BCryptPasswordEncoder passwordEncoder, 
                          UsuarioProducer usuarioProducer, 
                          EmprestimoClient emprestimoClient) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.usuarioProducer = usuarioProducer;
        this.emprestimoClient = emprestimoClient;
    }

    // Pega o usuario autenticado na sessao atual
    private Usuario getUsuarioLogado() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof Usuario) {
            return (Usuario) principal;
        }
        return null; // Rota publica (cadastro inicial)
    }

    @Transactional
    public UsuarioResponseDTO criarUsuario(UsuarioRequestDTO dto) {
        if (usuarioRepository.existsByEmail(dto.email())) {
            throw new IllegalArgumentException("E-mail já cadastrado.");
        }
        if (usuarioRepository.existsByCpf(dto.cpf())) {
            throw new IllegalArgumentException("CPF já cadastrado.");
        }

        Usuario usuarioLogado = getUsuarioLogado();
        String papelDefinido = "USER"; // Padrao comum

        // Apenas admin cria outro admin
        if ("ADMIN".equalsIgnoreCase(dto.role())) {
            if (usuarioLogado == null || !"ADMIN".equalsIgnoreCase(usuarioLogado.getRole())) {
                throw new SecurityException("Apenas administradores podem criar contas do tipo ADMIN.");
            }
            papelDefinido = "ADMIN";
        }

        Usuario usuario = new Usuario();
        usuario.setCpf(dto.cpf());
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(passwordEncoder.encode(dto.senha()));
        usuario.setRole(papelDefinido);
        usuario.setCreatedAt(LocalDateTime.now());
        usuario.setUpdatedAt(LocalDateTime.now());

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        usuarioProducer.dispararUsuarioCriado(usuarioSalvo.getId(), usuarioSalvo.getNome(), usuarioSalvo.getEmail());
        return new UsuarioResponseDTO(usuarioSalvo);
    }

    @Transactional(readOnly = true)
    public UsuarioResponseDTO buscarPorId(Long id) {
        Usuario usuarioLogado = getUsuarioLogado();
        
        // Comum so vê o proprio perfil, admin vê tudo
        if (!usuarioLogado.getId().equals(id) && !"ADMIN".equalsIgnoreCase(usuarioLogado.getRole())) {
            throw new SecurityException("Você não tem permissão para visualizar os dados de outro usuário.");
        }

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o ID: " + id));
        return new UsuarioResponseDTO(usuario);
    }

    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> buscarTodos() {
        Usuario usuarioLogado = getUsuarioLogado();
        
        if (!"ADMIN".equalsIgnoreCase(usuarioLogado.getRole())) {
            throw new SecurityException("Acesso negado. Apenas administradores podem listar os usuários.");
        }

        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioResponseDTO::new)
                .toList();
    }

    @Transactional
    public UsuarioResponseDTO editar(Long id, UsuarioUpdateRequestDTO dto) {
        Usuario usuarioLogado = getUsuarioLogado();
        
        if (!usuarioLogado.getId().equals(id) && !"ADMIN".equalsIgnoreCase(usuarioLogado.getRole())) {
            throw new SecurityException("Você não tem permissão para editar os dados de outro usuário.");
        }

        Usuario usuarioAlvo = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o ID: " + id));

        if (!usuarioAlvo.getEmail().equals(dto.email()) && usuarioRepository.existsByEmail(dto.email())) {
            throw new IllegalArgumentException("E-mail já está em uso por outro usuário.");
        }

        // Bloqueia usuario comum tentando mudar role
        if (!"ADMIN".equalsIgnoreCase(usuarioLogado.getRole()) && !usuarioAlvo.getRole().equalsIgnoreCase(dto.role())) {
            throw new SecurityException("Você não tem permissão para alterar papéis de acesso.");
        }

        usuarioAlvo.setNome(dto.nome());
        usuarioAlvo.setEmail(dto.email());
        
        if ("ADMIN".equalsIgnoreCase(usuarioLogado.getRole())) {
            usuarioAlvo.setRole(dto.role());
        }

        usuarioAlvo.setUpdatedAt(LocalDateTime.now());

        Usuario usuarioAtualizado = usuarioRepository.save(usuarioAlvo);
        return new UsuarioResponseDTO(usuarioAtualizado);
    }

    @Transactional
    public void remover(Long id) {
        Usuario usuarioLogado = getUsuarioLogado();

        if (!usuarioLogado.getId().equals(id) && !"ADMIN".equalsIgnoreCase(usuarioLogado.getRole())) {
            throw new SecurityException("Você não tem permissão para remover o perfil de outro usuário.");
        }

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
        Usuario usuarioLogado = getUsuarioLogado();

        if (!usuarioLogado.getEmail().equals(email) && !"ADMIN".equalsIgnoreCase(usuarioLogado.getRole())) {
            throw new SecurityException("Você não tem permissão para buscar dados de terceiros por e-mail.");
        }

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o e-mail: " + email));
        return new UsuarioResponseDTO(usuario);
    }
}