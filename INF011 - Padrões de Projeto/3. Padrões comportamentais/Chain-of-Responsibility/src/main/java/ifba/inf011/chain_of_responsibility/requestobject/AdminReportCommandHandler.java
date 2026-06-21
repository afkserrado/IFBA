package ifba.inf011.chain_of_responsibility.requestobject;

import ifba.inf011.chain_of_responsibility.domain.Role;

// Concrete Handler
public class AdminReportCommandHandler extends BaseSecurityCommandHandler {

    @Override
    public boolean handle(SecurityCommand command) {
        if (command.getType() == SecurityCommandType.ADMIN_REPORT) {
            if (command.getRole() == Role.ADMIN) {
                System.out.println("AdminReportCommandHandler: Relatório administrativo liberado.");
                return true;
            }

            System.out.println("AdminReportCommandHandler: Acesso administrativo negado.");
            return false;
        }

        return super.handle(command);
    }
}
