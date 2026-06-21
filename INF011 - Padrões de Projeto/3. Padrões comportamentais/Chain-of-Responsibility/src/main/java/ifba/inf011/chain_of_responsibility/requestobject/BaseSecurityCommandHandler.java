package ifba.inf011.chain_of_responsibility.requestobject;

// Base Handler
public abstract class BaseSecurityCommandHandler implements SecurityCommandHandler {

    private SecurityCommandHandler next;

    @Override
    public void setNext(SecurityCommandHandler next) {
        this.next = next;
    }

    @Override
    public boolean handle(SecurityCommand command) {
        if (this.next == null) {
            System.out.println("Comando não tratado: " + command.getType());
            return false;
        }

        return this.next.handle(command);
    }
}
