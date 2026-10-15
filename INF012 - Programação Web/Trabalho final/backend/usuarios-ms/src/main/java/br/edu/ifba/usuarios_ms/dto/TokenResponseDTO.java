package br.edu.ifba.usuarios_ms.dto;

/*
 * DTO devolvido ao cliente após um login bem-sucedido.
 *
 * Ele não expõe dados sensíveis do usuário,
 * apenas o token necessário para acessar endpoints protegidos.
 */
public record TokenResponseDTO(

    /*
     * JWT gerado pelo JwtService.
     *
     * O frontend deverá enviá-lo posteriormente no cabeçalho:
     * Authorization: Bearer <token>
     */
    String token,

    /*
     * Tipo de token utilizado.
     *
     * O projeto trabalha com Bearer Token. Então, o valor será sempre "Bearer".
     */
    String tipo
) {}