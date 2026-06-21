package ifba.inf011.chain_of_responsibility.middleware;

import ifba.inf011.chain_of_responsibility.domain.AccessRequest;
import ifba.inf011.chain_of_responsibility.domain.Role;

// Concrete Handler
public class AuthorizationMiddleware extends Middleware {

    @Override
    public boolean check(AccessRequest request) {
        if (!hasPermission(request.getUserRole(), request.getRequiredRole())) {
            System.out.println("Authorization: Acesso negado para " + request.getResource() + ".");
            return false;
        }

        System.out.println("Authorization: Acesso autorizado.");
        return checkNext(request);
    }

    private boolean hasPermission(Role userRole, Role requiredRole) {
        return userRole.ordinal() >= requiredRole.ordinal();
    }
}
