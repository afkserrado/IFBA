package br.edu.ifba.emprestimos_ms.dto;

import jakarta.validation.constraints.NotNull;

// DTO para requisições de criação de empréstimo
public class EmprestimoRequestDTO {

    @NotNull(message = "O ID do usuário é obrigatório")
    private Long usuarioId;

    @NotNull(message = "O ID do livro é obrigatório")
    private Long livroId;

    // Construtor utilizado pelo Jackson
    // para construir um objeto a partir dos dados de um JSON
    public EmprestimoRequestDTO() {}

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getLivroId() {
        return livroId;
    }

    public void setLivroId(Long livroId) {
        this.livroId = livroId;
    }
}