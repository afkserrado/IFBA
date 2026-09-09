package br.edu.ifba.usuarios_ms.dto;

import br.edu.ifba.usuarios_ms.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// DTO para requisições de criação de usuário
public class UsuarioRequestDTO {

    @NotBlank(message = "O CPF é obrigatório.")
    @Size(
        min = 11,
        max = 11,
        message = "O CPF deve conter exatamente 11 dígitos."
    )
    private String cpf;

    @NotBlank(message = "O nome é obrigatório.")
    @Size(
        min = 2,
        max = 100,
        message = "O nome deve ter entre 2 e 100 caracteres."
    )
    private String nome;

    @NotBlank(message = "O email é obrigatório.")
    @Email(message = "O email deve ser válido.")
    private String email;

    @NotBlank(message = "A senha é obrigatória.")
    @Size(
        min = 6,
        message = "A senha deve ter no mínimo 6 caracteres."
    )
    private String senha;

    @NotNull(message = "O papel (role) do usuário é obrigatório.")
    private Role role;

    // Construtor utilizado pelo Jackson
    // para construir um objeto a partir dos dados de um JSON
    public UsuarioRequestDTO() {}

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}