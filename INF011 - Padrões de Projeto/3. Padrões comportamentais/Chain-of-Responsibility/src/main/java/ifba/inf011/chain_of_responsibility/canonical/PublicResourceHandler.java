package ifba.inf011.chain_of_responsibility.canonical;

import ifba.inf011.chain_of_responsibility.domain.AccessRequest;
import ifba.inf011.chain_of_responsibility.domain.Role;

// Concrete Handler
public class PublicResourceHandler extends BaseAccessHandler {

    @Override
    public boolean handle(AccessRequest request) {
        if (request.getRequiredRole() == Role.GUEST) {
            System.out.println("PublicResourceHandler: Recurso público liberado.");
            return true;
        }

        return super.handle(request);
    }
}
