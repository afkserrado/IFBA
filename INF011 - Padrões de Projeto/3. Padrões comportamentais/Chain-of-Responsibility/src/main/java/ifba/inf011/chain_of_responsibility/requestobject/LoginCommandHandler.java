package ifba.inf011.chain_of_responsibility.requestobject;

// Concrete Handler
public class LoginCommandHandler extends BaseSecurityCommandHandler {

    @Override
    public boolean handle(SecurityCommand command) {
        if (command.getType() == SecurityCommandType.LOGIN) {
            System.out.println("LoginCommandHandler: Login tratado para " + command.getEmail() + ".");
            return true;
        }

        return super.handle(command);
    }
}
