package br.edu.ifba.usuarios_ms.service;

import java.util.List;
import java.util.Objects;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifba.usuarios_ms.client.EmprestimoClient;
import br.edu.ifba.usuarios_ms.dto.UsuarioRequestDTO;
import br.edu.ifba.usuarios_ms.dto.UsuarioResponseDTO;
import br.edu.ifba.usuarios_ms.dto.UsuarioUpdateRequestDTO;
import br.edu.ifba.usuarios_ms.entity.Usuario;
import br.edu.ifba.usuarios_ms.enums.Role;
import br.edu.ifba.usuarios_ms.exception.OperacaoNaoPermitidaException;
import br.edu.ifba.usuarios_ms.exception.UsuarioNaoEncontradoException;
import br.edu.ifba.usuarios_ms.mapper.UsuarioMapper;
import br.edu.ifba.usuarios_ms.messaging.UsuarioProducer;
import br.edu.ifba.usuarios_ms.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioProducer usuarioProducer;
    private final EmprestimoClient emprestimoClient;

    public UsuarioService(
        UsuarioRepository usuarioRepository,
        UsuarioProducer usuarioProducer,
        EmprestimoClient emprestimoClient
    ) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioProducer = usuarioProducer;
        this.emprestimoClient = emprestimoClient;
    }

    @Transactional
    public UsuarioResponseDTO criarUsuario(UsuarioRequestDTO dto) {

        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new OperacaoNaoPermitidaException("E-mail já cadastrado.");
        }

        if (usuarioRepository.existsByCpf(dto.getCpf())) {
            throw new OperacaoNaoPermitidaException("CPF já cadastrado.");
        }

        // Força o papel como USER, ignorando o role enviado no DTO
        dto.setRole(Role.USER);

        Usuario usuario = UsuarioMapper.converterDtoParaEntidade(dto);

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        usuarioProducer.dispararUsuarioCriado(
            usuarioSalvo.getId(),
            usuarioSalvo.getNome(),
            usuarioSalvo.getEmail(),
            usuarioSalvo.getRole()
        );

        return UsuarioMapper.converterEntidadeParaDto(usuarioSalvo);
    }

    @Transactional(readOnly = true)
    public UsuarioResponseDTO buscarUsuarioPorId(@NonNull Long id) {
        Usuario usuario = obterUsuario(id);

        return UsuarioMapper.converterEntidadeParaDto(usuario);
    }

    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> buscarUsuarios() {
        return usuarioRepository.findAll().stream()
            .map(UsuarioMapper::converterEntidadeParaDto)
            .toList();
    }

    @Transactional
    public UsuarioResponseDTO atualizarUsuario(@NonNull Long id, UsuarioUpdateRequestDTO dto) {
        
        Usuario usuario = obterUsuario(id);

        if (!usuario.getEmail().equals(dto.getEmail()) && usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new OperacaoNaoPermitidaException("E-mail já está em uso por outro usuário.");
        }

        Usuario usuarioAtualizado = UsuarioMapper.converterUpdateDtoParaEntidade(usuario, dto);
        usuarioAtualizado.setDataAtualizacao(java.time.LocalDateTime.now());

        Usuario usuarioSalvo = usuarioRepository.save(usuarioAtualizado);

        return UsuarioMapper.converterEntidadeParaDto(usuarioSalvo);
    }

    @Transactional
    public void removerUsuario(@NonNull Long id) {
        
        Usuario usuario = obterUsuario(id);

        // Valida se há pendências em empréstimos antes de apagar
        if (emprestimoClient.possuiEmprestimosAtivos(id)) {
            throw new OperacaoNaoPermitidaException(
                "Não é possível excluir a conta: existem empréstimos ativos."
            );
        }

        if (emprestimoClient.possuiMultasPendentes(id)) {
            throw new OperacaoNaoPermitidaException(
                "Não é possível excluir a conta: existem multas financeiras pendentes."
            );
        }

        usuarioRepository.delete(usuario);

        usuarioProducer.dispararUsuarioDeletado(usuario.getId());
    }

    @Transactional(readOnly = true)
    public UsuarioResponseDTO buscarUsuarioPorEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new UsuarioNaoEncontradoException(
                "Usuário não encontrado com o e-mail: " + email
            ));

        return UsuarioMapper.converterEntidadeParaDto(usuario);
    }

    public boolean existeUsuario(@NonNull Long id) {
        return usuarioRepository.existsById(id);
    }

    @NonNull
    private Usuario obterUsuario(@NonNull Long id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new UsuarioNaoEncontradoException(
                "Usuário não encontrado com o ID: " + id
            ));

        return Objects.requireNonNull(usuario);
    }
}