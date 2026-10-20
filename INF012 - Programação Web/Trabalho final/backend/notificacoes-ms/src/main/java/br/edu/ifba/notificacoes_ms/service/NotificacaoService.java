package br.edu.ifba.notificacoes_ms.service;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.edu.ifba.notificacoes_ms.client.EmailClient;
import br.edu.ifba.notificacoes_ms.dto.EmailRequest;
import br.edu.ifba.notificacoes_ms.dto.EmprestimoCriadoEvent;
import br.edu.ifba.notificacoes_ms.dto.EmprestimoDevolvidoEvent;
import br.edu.ifba.notificacoes_ms.dto.LivroCadastradoEvent;
import br.edu.ifba.notificacoes_ms.dto.LivroDeletadoEvent;
import br.edu.ifba.notificacoes_ms.dto.UsuarioCriadoEvent;
import br.edu.ifba.notificacoes_ms.dto.UsuarioDeletadoEvent;
import feign.FeignException;

@Service
public class NotificacaoService {

    private static final DateTimeFormatter FORMATADOR_DATA_HORA =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private final EmailClient emailClient;

    // Serviço responsável por obter, reutilizar e invalidar o JWT
    // utilizado nas chamadas ao email-ms.
    private final ServiceTokenService serviceTokenService;

    private final String destinatario;

    public NotificacaoService(
            EmailClient emailClient,
            ServiceTokenService serviceTokenService,
            @Value("${email.destinatario}") String destinatario
    ) {
        this.emailClient = emailClient;
        this.serviceTokenService = serviceTokenService;
        this.destinatario = destinatario;
    }

    public void notificarLivroCadastrado(LivroCadastradoEvent event) {

        EmailRequest email = new EmailRequest(
                destinatario,
                "Novo livro cadastrado",
                """
                Um novo livro foi cadastrado no acervo.

                Título: %s
                Autor: %s
                ISBN: %s
                """.formatted(
                        event.titulo(),
                        event.autor(),
                        event.isbn()
                )
        );

        enviar(email);
    }

    public void notificarLivroDeletado(LivroDeletadoEvent event) {

        EmailRequest email = new EmailRequest(
                destinatario,
                "Livro removido do acervo",
                """
                Um livro foi removido do acervo.

                ID do livro: %d
                """.formatted(event.id())
        );

        enviar(email);
    }

    public void notificarUsuarioCriado(UsuarioCriadoEvent event) {

        EmailRequest email = new EmailRequest(
                destinatario,
                "Novo usuário cadastrado",
                """
                Um novo usuário foi cadastrado.

                Nome: %s
                E-mail: %s
                """.formatted(
                        event.nome(),
                        event.email()
                )
        );

        enviar(email);
    }

    public void notificarUsuarioDeletado(UsuarioDeletadoEvent event) {

        EmailRequest email = new EmailRequest(
                destinatario,
                "Usuário removido",
                """
                Um usuário foi removido.

                ID do usuário: %d
                """.formatted(event.usuarioId())
        );

        enviar(email);
    }

    public void notificarEmprestimoCriado(EmprestimoCriadoEvent event) {

        EmailRequest email = new EmailRequest(
                event.emailUsuario(),
                "Empréstimo realizado com sucesso",
                """
                Seu empréstimo foi realizado com sucesso.

                Empréstimo ID: %d
                Data do empréstimo: %s
                Data prevista para devolução: %s
                """.formatted(
                        event.emprestimoId(),
                        event.dataEmprestimo().format(FORMATADOR_DATA_HORA),
                        event.dataPrevistaDevolucao().format(FORMATADOR_DATA_HORA)
                )
        );

        enviar(email);
    }

    public void notificarEmprestimoDevolvido(EmprestimoDevolvidoEvent event) {

        EmailRequest email = new EmailRequest(
                event.emailUsuario(),
                "Devolução registrada",
                """
                A devolução do seu empréstimo foi registrada.

                Empréstimo ID: %d
                Data da devolução: %s
                """.formatted(
                        event.emprestimoId(),
                        event.dataDevolucao().format(FORMATADOR_DATA_HORA)
                )
        );

        enviar(email);
    }

     /*
     * Envia o e-mail por meio do email-ms.
     *
     * O cabeçalho Authorization é adicionado automaticamente
     * pelo EmailFeignInterceptor.
     *
     * Se o email-ms retornar 401 Unauthorized, o token atual
     * é invalidado. Assim, a próxima notificação fará login
     * novamente no usuarios-ms e obterá um novo JWT.
     */
    private void enviar(EmailRequest email) {

        try {
            emailClient.enviarEmail(email);
        }

        // O email-ms rejeitou o token: ele pode estar expirado ou inválido
        catch (FeignException.Unauthorized e) {

            serviceTokenService.invalidateToken();

            throw new RuntimeException(
                    "Token do notificacoes-ms inválido ou expirado ao chamar o email-ms", e
            );
        }

        catch (Exception e) {
            throw new RuntimeException(
                    "Erro ao enviar notificação por e-mail", e
            );
        }
    }
}