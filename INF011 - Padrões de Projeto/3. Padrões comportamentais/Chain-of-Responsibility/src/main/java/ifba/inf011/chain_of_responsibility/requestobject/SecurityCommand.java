package ifba.inf011.chain_of_responsibility.requestobject;

import ifba.inf011.chain_of_responsibility.domain.Role;

// Request
public class SecurityCommand {

    private final SecurityCommandType type;
    private final String email;
    private final Role role;

    public SecurityCommand(SecurityCommandType type, String email, Role role) {
        this.type = type;
        this.email = email;
        this.role = role;
    }

    public SecurityCommandType getType() {
        return type;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }
}
