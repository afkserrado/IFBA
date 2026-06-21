package ifba.inf011.chain_of_responsibility.requestobject;

// Handler
public interface SecurityCommandHandler {
    void setNext(SecurityCommandHandler next);
    boolean handle(SecurityCommand command);
}
