package br.edu.ifba.usuarios_ms.service;

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

    private final SecretKey key;

    public TokenService(
        @Value("${security.jwt-secret}") String secret
    ){
        this.key = Keys.hmacShaKeyFor(
            secret.getBytes(StandardCharsets.UTF_8)
        );
    }


    public String gerarToken(Usuario usuario){

        Date agora = new Date();

        Date expiracao =
            new Date(
                agora.getTime() + 7200000
            );


        return Jwts.builder()
                .issuer("auth-api")
                .subject(usuario.getEmail())
                .claim("role", usuario.getRole())
                .issuedAt(agora)
                .expiration(expiracao)
                .signWith(key)
                .compact();
    }
}