package br.edu.ifba.usuarios_ms.config;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.edu.ifba.usuarios_ms.entity.Usuario;
import br.edu.ifba.usuarios_ms.repository.UsuarioRepository;

@Component
public class AdminInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    private final String adminName;
    private final String adminEmail;
    private final String adminPassword;
    private final String adminCpf;

    public AdminInitializer(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            @Value("${admin.name}") String adminName,
            @Value("${admin.email}") String adminEmail,
            @Value("${admin.password}") String adminPassword,
            @Value("${admin.cpf}") String adminCpf
    ) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.adminName = adminName;
        this.adminEmail = adminEmail;
        this.adminPassword = adminPassword;
        this.adminCpf = adminCpf;
    }

    @Override
    public void run(String... args) {

        if (usuarioRepository.findByEmail(adminEmail).isEmpty()) {

            LocalDateTime agora = LocalDateTime.now();

            Usuario admin = new Usuario();

            admin.setCpf(adminCpf);
            admin.setNome(adminName);
            admin.setEmail(adminEmail);
            admin.setSenha(passwordEncoder.encode(adminPassword));
            admin.setRole("ADMIN");
            admin.setCreatedAt(agora);
            admin.setUpdatedAt(agora);

            usuarioRepository.save(admin);
        }
    }
}