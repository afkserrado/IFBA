package ifba.inf011.chain_of_responsibility.middleware;

import ifba.inf011.chain_of_responsibility.domain.AccessRequest;

// Handler
public abstract class Middleware {

    private Middleware next;

    public static Middleware link(Middleware first, Middleware... chain) {
        Middleware current = first;

        for (Middleware nextInChain : chain) {
            current.next = nextInChain;
            current = nextInChain;
        }

        return first;
    }

    public abstract boolean check(AccessRequest request);

    protected boolean checkNext(AccessRequest request) {
        if (this.next == null) {
            return true;
        }

        return this.next.check(request);
    }
}
