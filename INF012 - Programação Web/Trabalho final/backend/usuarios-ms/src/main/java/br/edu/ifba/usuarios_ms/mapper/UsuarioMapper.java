package br.edu.ifba.usuarios_ms.mapper;

import java.util.Objects;

import org.springframework.lang.NonNull;

import br.edu.ifba.usuarios_ms.dto.UsuarioRequestDTO;
import br.edu.ifba.usuarios_ms.dto.UsuarioResponseDTO;
import br.edu.ifba.usuarios_ms.dto.UsuarioUpdateRequestDTO;
import br.edu.ifba.usuarios_ms.entity.Usuario;

public final class UsuarioMapper {

    private UsuarioMapper() {}

    // Opcional para resolver "erro" do Visual Code
    // Indicação explícita de que o método não devolve null
    @NonNull
    public static Usuario converterDtoParaEntidade(UsuarioRequestDTO dto) {
        Objects.requireNonNull(dto, "O DTO não pode ser nulo.");

        return new Usuario(
            dto.getCpf(),
            dto.getNome(),
            dto.getEmail(),
            dto.getSenha(),
            dto.getRole()
        );
    }

    @NonNull
    public static Usuario converterUpdateDtoParaEntidade(
        Usuario usuario,
        UsuarioUpdateRequestDTO dto
    ) {
        Objects.requireNonNull(usuario, "A entidade Usuario não pode ser nula.");
        Objects.requireNonNull(dto, "O DTO de atualização não pode ser nulo.");

        if (dto.getCpf() != null) {
            usuario.setCpf(dto.getCpf());
        }
        if (dto.getNome() != null) {
            usuario.setNome(dto.getNome());
        }
        if (dto.getEmail() != null) {
            usuario.setEmail(dto.getEmail());
        }
        if (dto.getSenha() != null) {
            usuario.setSenha(dto.getSenha());
        }
        if (dto.getRole() != null) {
            usuario.setRole(dto.getRole());
        }

        return usuario;
    }

    @NonNull
    public static UsuarioResponseDTO converterEntidadeParaDto(Usuario usuario) {
        Objects.requireNonNull(usuario, "A entidade Usuario não pode ser nula.");
        return new UsuarioResponseDTO(usuario);
    }
}