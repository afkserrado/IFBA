package ifba.inf011.chain_of_responsibility.middleware;

import ifba.inf011.chain_of_responsibility.domain.AccessRequest;

// Concrete Handler
public class PayloadValidationMiddleware extends Middleware {

    @Override
    public boolean check(AccessRequest request) {
        if (request.getPayload() == null || request.getPayload().trim().isEmpty()) {
            System.out.println("Validation: Payload inválido.");
            return false;
        }

        System.out.println("Validation: Payload válido.");
        return checkNext(request);
    }
}
