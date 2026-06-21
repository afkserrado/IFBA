package ifba.inf011.chain_of_responsibility.middleware;

import ifba.inf011.chain_of_responsibility.domain.AccessRequest;

// Concrete Handler
public class AuthenticationMiddleware extends Middleware {

    private final Server server;

    public AuthenticationMiddleware(Server server) {
        this.server = server;
    }

    @Override
    public boolean check(AccessRequest request) {
        if (!server.hasEmail(request.getEmail())) {
            System.out.println("Authentication: Usuário não cadastrado.");
            return false;
        }

        if (!server.isValidPassword(request.getEmail(), request.getPassword())) {
            System.out.println("Authentication: Senha inválida.");
            return false;
        }

        System.out.println("Authentication: Usuário autenticado.");
        return checkNext(request);
    }
}
