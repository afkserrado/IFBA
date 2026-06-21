package ifba.inf011.chain_of_responsibility.canonical;

import ifba.inf011.chain_of_responsibility.domain.AccessRequest;

// Base Handler
public abstract class BaseAccessHandler implements AccessHandler {

    private AccessHandler next;

    @Override
    public void setNext(AccessHandler next) {
        this.next = next;
    }

    @Override
    public boolean handle(AccessRequest request) {
        if (this.next == null) {
            System.out.println("Nenhum handler conseguiu tratar a requisição.");
            return false;
        }

        return this.next.handle(request);
    }
}
