package ifba.inf011.chain_of_responsibility.requestobject;

import ifba.inf011.chain_of_responsibility.domain.Role;

// Concrete Handler
public class CreateOrderCommandHandler extends BaseSecurityCommandHandler {

    @Override
    public boolean handle(SecurityCommand command) {
        if (command.getType() == SecurityCommandType.CREATE_ORDER) {
            if (command.getRole().ordinal() >= Role.USER.ordinal()) {
                System.out.println("CreateOrderCommandHandler: Pedido criado.");
                return true;
            }

            System.out.println("CreateOrderCommandHandler: Visitante não pode criar pedido.");
            return false;
        }

        return super.handle(command);
    }
}
