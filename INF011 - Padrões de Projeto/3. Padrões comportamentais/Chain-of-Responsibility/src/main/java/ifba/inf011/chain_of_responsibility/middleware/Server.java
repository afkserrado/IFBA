package ifba.inf011.chain_of_responsibility.middleware;

import java.util.HashMap;
import java.util.Map;

import ifba.inf011.chain_of_responsibility.domain.Role;

// Service
public class Server {

    private final Map<String, String> users;

    public Server() {
        this.users = new HashMap<>();
    }

    public void register(String email, String password) {
        this.users.put(email, password);
    }

    public boolean hasEmail(String email) {
        return this.users.containsKey(email);
    }

    public boolean isValidPassword(String email, String password) {
        return password != null && password.equals(this.users.get(email));
    }
}
