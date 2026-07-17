package br.edu.ifba.usuarios_ms.service;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.edu.ifba.usuarios_ms.entity.Usuario;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class TokenService {

    @Value("${JWT_SECRET:minha-chave-secreta-super-protegida-com-mais-de-trinta-e-dois-bytes}")
    private String secret;

    private SecretKey getSigningKey() {
        // O JJWT 0.12.x exige uma SecretKey segura baseada em bytes com tamanho adequado (mínimo 256 bits)
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String gerarToken(Usuario usuario) {
        try {
            long tempoExpiracaoMillis = 7200000; // 2 horas em milissegundos
            Date agora = new Date();
            Date dataExpiracao = new Date(agora.getTime() + tempoExpiracaoMillis);

            return Jwts.builder()
                    .issuer("auth-api")
                    .subject(usuario.getEmail())
                    .claim("role", usuario.getRole())
                    .issuedAt(agora)
                    .expiration(dataExpiracao)
                    .signWith(getSigningKey()) // Usa a SecretKey tipada da nova API
                    .compact();
        } catch (JwtException exception) {
            throw new RuntimeException("Erro ao gerar token JWT", exception);
        }
    }

    public String validarToken(String token) {
        try {
            // Nova sintaxe fluída para parsing e validação no JJWT 0.12.x
            return Jwts.parser()
                    .verifyWith(getSigningKey()) // Define a chave de verificação
                    .build()
                    .parseSignedClaims(token) // Valida a assinatura e expiração
                    .getPayload()
                    .getSubject(); // Retorna o e-mail do usuário
        } catch (JwtException exception) {
            return ""; // Token corrompido, falso ou expirado retorna vazio para o filtro barrar
        }
    }
}
