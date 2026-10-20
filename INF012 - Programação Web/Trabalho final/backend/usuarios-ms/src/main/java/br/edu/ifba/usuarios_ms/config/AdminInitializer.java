package br.edu.ifba.usuarios_ms.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.edu.ifba.usuarios_ms.entity.Usuario;
import br.edu.ifba.usuarios_ms.enums.Role;
import br.edu.ifba.usuarios_ms.repository.UsuarioRepository;

/**
 * Garante a existência de um usuário ADMIN inicial na inicialização do usuarios-ms.
 *
 * O usuário é criado apenas quando ainda não existe um ADMIN com o e-mail
 * configurado. Dessa forma, o sistema permanece funcional mesmo após a criação
 * de um banco de dados novo.
 */
@Configuration
public class AdminInitializer {

    // Repositório responsável pelo acesso aos usuários no banco de dados
    private final UsuarioRepository usuarioRepository;

    // Codificador utilizado para gerar o hash BCrypt da senha inicial
    private final PasswordEncoder passwordEncoder;

    // Dados do ADMIN inicial, carregados das propriedades da aplicação
    private final String cpf;
    private final String nome;
    private final String email;
    private final String senha;

    public AdminInitializer(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            @Value("${app.admin.cpf}") String cpf,
            @Value("${app.admin.nome}") String nome,
            @Value("${app.admin.email}") String email,
            @Value("${app.admin.senha}") String senha
    ) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    /**
     * Executa a verificação durante a inicialização da aplicação.
     *
     * Se já existir um usuário ADMIN com o e-mail configurado,
     * nenhuma alteração é realizada.
     */
    @Bean
    CommandLineRunner criarAdminInicial() {

        // Classe anônima
        return new CommandLineRunner() {

            @Override
            public void run(String... args) {

                boolean adminExistente = usuarioRepository.existsByEmail(email);

                // Encerra somente a execução de run quando o e-mail já existe.
                if (adminExistente) {
                    return;
                }

                // Cria o ADMIN com a senha armazenada como hash BCrypt.
                Usuario admin = new Usuario(
                    cpf,
                    nome,
                    email,
                    passwordEncoder.encode(senha),
                    Role.ADMIN
                );

                usuarioRepository.save(admin);
            }
        };
    }
}