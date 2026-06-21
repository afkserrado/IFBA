package ifba.inf011.chain_of_responsibility.middleware;

import ifba.inf011.chain_of_responsibility.domain.AccessRequest;

// Concrete Handler
public class BusinessLogicMiddleware extends Middleware {

    @Override
    public boolean check(AccessRequest request) {
        System.out.println("BusinessLogic: Pedido processado em " + request.getResource() + ".");
        return checkNext(request);
    }
}
