package ifba.inf011.chain_of_responsibility.middleware;

import ifba.inf011.chain_of_responsibility.domain.AccessRequest;

// Concrete Handler
public class ThrottlingMiddleware extends Middleware {

    private final int requestLimit;

    public ThrottlingMiddleware(int requestLimit) {
        this.requestLimit = requestLimit;
    }

    @Override
    public boolean check(AccessRequest request) {
        if (request.getRequestsPerMinute() > this.requestLimit) {
            System.out.println("Throttling: Limite de requisições excedido.");
            return false;
        }

        System.out.println("Throttling: Requisição dentro do limite.");
        return checkNext(request);
    }
}
