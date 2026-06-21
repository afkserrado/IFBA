package ifba.inf011.chain_of_responsibility.canonical;

import ifba.inf011.chain_of_responsibility.domain.AccessRequest;

// Handler
public interface AccessHandler {
    void setNext(AccessHandler next);
    boolean handle(AccessRequest request);
}
