package br.edu.ifba.security.jwt;

import java.nio.charset.StandardCharsets;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtService {

    private final SecretKey key;

    public JwtService(
            @Value("${jwt.secret}") String secret) {

        this.key = Keys.hmacShaKeyFor(
                secret.getBytes(StandardCharsets.UTF_8));
    }

    public Claims validarToken(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getEmail(Claims claims) {

        return claims.getSubject();

    }

    public Long getUserId(Claims claims) {
        return claims.get("id", Long.class);
    }

    public String getRole(Claims claims) {

        return claims.get(
                "role",
                String.class);

    }

}