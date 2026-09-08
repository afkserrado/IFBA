package br.edu.ifba.usuarios_ms.dto;

import br.edu.ifba.usuarios_ms.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

// DTO para requisições de atualização de usuário (campos opcionais)
public class UsuarioUpdateRequestDTO {

    @Size(
        min = 11,
        max = 11,
        message = "O CPF deve conter exatamente 11 dígitos."
    )
    private String cpf;

    @Size(
        min = 2,
        max = 100,
        message = "O nome deve ter entre 2 e 100 caracteres."
    )
    private String nome;

    @Email(message = "O email deve ser válido.")
    private String email;

    @Size(
        min = 6,
        message = "A senha deve ter no mínimo 6 caracteres."
    )
    private String senha;

    private Role role;

    // Construtor utilizado pelo Jackson
    // para construir um objeto a partir dos dados de um JSON
    public UsuarioUpdateRequestDTO() {}

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