package br.edu.ifba.usuarios_ms.security;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import br.edu.ifba.usuarios_ms.entity.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

/*
 * Serviço responsável apenas por GERAR tokens JWT.
 *
 * Ele não valida tokens recebidos e não protege endpoints.
 * A validação do JWT será feita depois pelo Spring Security,
 * configurando o projeto como OAuth 2.0 Resource Server.
 */
@Service
public class JwtService {

    /*
     * Chave secreta usada para assinar os JWTs com HS256,
     * isto é, criar o "signature" do JWT.
     *
     * HS256 é um algoritmo simétrico:
     * a mesma chave é usada para assinar o token no usuarios-ms
     * e para validar a assinatura nos microsserviços que receberem o token.
     */
    private final SecretKey chaveAssinatura;

    /*
     * Identificador do emissor do token.
     *
     * Esse valor será gravado na claim "iss" do JWT.
     * Exemplo: "usuarios-ms".
     */
    private final String emissor;

    /*
     * Tempo de validade do token, em minutos.
     *
     * Esse valor vem de:
     * app.security.jwt.expiracao-minutos=60
     */
    private final long expiracaoMinutos;

    /*
     * Construtor com injeção de dependências.
     *
     * O Spring lê os valores do application.properties/.env
     * e os entrega automaticamente para este construtor.
     */
    public JwtService(
        @Value("${app.security.jwt.secret}") String segredo,
        @Value("${app.security.jwt.issuer}") String emissor,
        @Value("${app.security.jwt.expiracao-minutos}") long expiracaoMinutos
    ) {
        /*
         * Converte a chave secreta, que está como texto, em bytes UTF-8.
         *
         * Em seguida, Keys.hmacShaKeyFor() cria uma SecretKey
         * adequada para algoritmos HMAC, como o HS256.
         *
         * A chave precisa ter pelo menos 32 bytes (256 bits)
         * para ser aceita pelo HS256.
         */
        this.chaveAssinatura = Keys.hmacShaKeyFor(
            segredo.getBytes(StandardCharsets.UTF_8)
        );

        // Guarda o emissor configurado, por exemplo: "usuarios-ms"
        this.emissor = emissor;

        // Guarda o tempo de expiração configurado, em minutos
        this.expiracaoMinutos = expiracaoMinutos;
    }

    /*
     * Gera um JWT para o usuário autenticado.
     *
     * Fluxo:
     * Usuario autenticado
     *     ↓
     * Claims são adicionadas ao token
     *     ↓
     * Token é assinado com HS256
     *     ↓
     * String do JWT é retornada
     */
    public String gerarToken(Usuario usuario) {

        /*
         * Captura o momento atual em UTC.
         *
         * Instant é preferível a LocalDateTime para operações
         * relacionadas a tokens, porque não depende de fuso horário.
         */
        Instant agora = Instant.now();

        /*
         * Calcula o momento de expiração do token.
         *
         * Exemplo:
         * agora = 10:00
         * expiracaoMinutos = 60
         * expiracao = 11:00
         */
        Instant expiracao = agora.plusSeconds(expiracaoMinutos * 60);

        /*
         * Constrói e assina o JWT.
         *
         * O resultado é uma String no formato:
         * header.payload.signature
         */
        return Jwts.builder()

            /*
             * Define a claim "sub" (subject).
             *
             * Aqui estamos usando o e-mail como identificador principal
             * do usuário dentro do token.
             */
            .subject(usuario.getEmail())

            /*
             * Adiciona uma claim personalizada chamada "usuarioId".
             *
             * Ela permite que outros microsserviços identifiquem
             * o ID do usuário sem precisar consultar o banco
             * apenas para descobrir quem é o dono do token.
             */
            .claim("usuarioId", usuario.getId())

            /*
             * Adiciona uma claim personalizada chamada "role".
             *
             * O valor será "USER" ou "ADMIN".
             * Posteriormente, o Spring Security poderá converter
             * essa claim em uma autoridade, como ROLE_USER ou ROLE_ADMIN.
             */
            .claim("role", usuario.getRole().name())

            /*
             * Define a claim "iss" (issuer).
             *
             * Ela identifica quem emitiu o token.
             * Neste projeto, o emissor é o usuarios-ms.
             */
            .issuer(emissor)

            /*
             * Define a claim "iat" (issued at).
             *
             * Ela registra o momento em que o token foi criado.
             */
            .issuedAt(Date.from(agora))

            /*
             * Define a claim "exp" (expiration).
             *
             * Depois desse momento, o token não deve mais ser aceito.
             */
            .expiration(Date.from(expiracao))

            /*
             * Assina o token usando HS256.
             *
             * HS256 significa HMAC com SHA-256.
             * A assinatura permite detectar qualquer alteração
             * no header ou no payload do token.
             */
            .signWith(chaveAssinatura, Jwts.SIG.HS256)

            /*
             * Finaliza a construção e retorna o JWT
             * como uma String compacta.
             */
            .compact();
    }
}