// src/main/java/br/edu/ifba/notificacoes_ms/service/ServiceTokenService.java
package br.edu.ifba.notificacoes_ms.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import br.edu.ifba.notificacoes_ms.client.UsuariosClient;
import br.edu.ifba.notificacoes_ms.dto.LoginRequestDTO;
import br.edu.ifba.notificacoes_ms.dto.LoginResponseDTO;

/*
 * Serviço responsável por obter e gerenciar o JWT utilizado
 * pelo notificacoes-ms para se autenticar no email-ms.
 *
 * O notificacoes-ms não valida tokens nem conhece a chave secreta.
 * Ele apenas realiza login no usuarios-ms, recebe um JWT
 * e o apresenta ao email-ms nas chamadas protegidas.
 */
@Service
public class ServiceTokenService {

    private final UsuariosClient usuariosClient;
    private final String serviceEmail;
    private final String servicePassword;

    // JWT em memória.
    // É reutilizado entre chamadas para evitar um novo login
    // a cada envio de notificação.
    private String token;

    /*
     * Construtor com injeção de dependências.
     *
     * O Spring injeta o UsuariosClient e os valores configurados
     * no application.properties, que podem vir de variáveis de ambiente.
     */
    public ServiceTokenService(
            UsuariosClient usuariosClient,
            @Value("${notificacoes.service.email}") String serviceEmail,
            @Value("${notificacoes.service.password}") String servicePassword
    ) {
        this.usuariosClient = usuariosClient;
        this.serviceEmail = serviceEmail;
        this.servicePassword = servicePassword;
    }

    /*
     * Retorna o JWT atual do serviço.
     *
     * Se ainda não existir um token em memória, realiza login
     * no usuarios-ms para obtê-lo.
     *
     * O método é synchronized para evitar que múltiplas threads,
     * consumindo eventos do RabbitMQ simultaneamente, façam login
     * ao mesmo tempo.
     */
    public synchronized String getToken() {

        // Realiza login apenas quando não há token disponível
        if (token == null || token.isBlank()) {
            token = realizarLogin();
        }

        return token;
    }

    /*
     * Remove o token atual da memória.
     *
     * Deve ser chamado quando o email-ms indicar que o token
     * está inválido ou expirado. Assim, a próxima chamada fará
     * um novo login e obterá outro JWT.
     */
    public synchronized void invalidateToken() {
        token = null;
    }

    /*
     * Realiza login no usuarios-ms utilizando as credenciais
     * da conta de serviço configurada.
     *
     * Retorna apenas o JWT contido na resposta.
     */
    private String realizarLogin() {

        // Envia e-mail e senha ao endpoint /api/v1/auth/login
        LoginResponseDTO response = usuariosClient.login(
                new LoginRequestDTO(serviceEmail, servicePassword)
        );

        // Garante que o usuarios-ms retornou um token utilizável
        if (response == null
                || response.token() == null
                || response.token().isBlank()) {


            throw new IllegalStateException(
                    "O usuarios-ms não retornou um token válido para o notificacoes-ms."
            );
        }

        return response.token();
    }
}