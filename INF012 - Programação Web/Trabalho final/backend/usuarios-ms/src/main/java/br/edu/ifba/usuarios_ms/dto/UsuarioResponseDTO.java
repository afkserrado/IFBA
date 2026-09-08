package br.edu.ifba.usuarios_ms.dto;

import java.time.LocalDateTime;

import br.edu.ifba.usuarios_ms.entity.Usuario;
import br.edu.ifba.usuarios_ms.enums.Role;

// DTO para retorno de dados de usuário nas respostas da API
public class UsuarioResponseDTO {

    private Long id;
    private String cpf;
    private String nome;
    private String email;
    private Role role;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    public UsuarioResponseDTO() {}

    public UsuarioResponseDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.cpf = usuario.getCpf();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.role = usuario.getRole();
        this.dataCriacao = usuario.getDataCriacao();
        this.dataAtualizacao = usuario.getDataAtualizacao();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}