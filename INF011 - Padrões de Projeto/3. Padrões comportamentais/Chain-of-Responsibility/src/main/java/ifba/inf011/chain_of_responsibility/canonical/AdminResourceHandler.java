package ifba.inf011.chain_of_responsibility.canonical;

import ifba.inf011.chain_of_responsibility.domain.AccessRequest;
import ifba.inf011.chain_of_responsibility.domain.Role;

// Concrete Handler
public class AdminResourceHandler extends BaseAccessHandler {

    @Override
    public boolean handle(AccessRequest request) {
        if (request.getRequiredRole() == Role.ADMIN) {
            boolean allowed = request.getUserRole() == Role.ADMIN;

            if (allowed) {
                System.out.println("AdminResourceHandler: Recurso administrativo liberado.");
            } else {
                System.out.println("AdminResourceHandler: Apenas administradores podem acessar.");
            }

            return allowed;
        }

        return super.handle(request);
    }
}
