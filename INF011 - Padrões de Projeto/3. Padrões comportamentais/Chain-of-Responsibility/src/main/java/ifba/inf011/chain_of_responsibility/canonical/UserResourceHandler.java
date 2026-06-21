package ifba.inf011.chain_of_responsibility.canonical;

import ifba.inf011.chain_of_responsibility.domain.AccessRequest;
import ifba.inf011.chain_of_responsibility.domain.Role;

// Concrete Handler
public class UserResourceHandler extends BaseAccessHandler {

    @Override
    public boolean handle(AccessRequest request) {
        if (request.getRequiredRole() == Role.USER) {
            boolean allowed = request.getUserRole().ordinal() >= Role.USER.ordinal();

            if (allowed) {
                System.out.println("UserResourceHandler: Recurso de usuário liberado.");
            } else {
                System.out.println("UserResourceHandler: Usuário sem permissão.");
            }

            return allowed;
        }

        return super.handle(request);
    }
}
