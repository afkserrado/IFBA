package ifba.inf011.chain_of_responsibility.domain;

// Request
public class AccessRequest {

    private final String email;
    private final String password;
    private final Role userRole;
    private final Role requiredRole;
    private final int requestsPerMinute;
    private final String payload;
    private final String resource;

    public AccessRequest(
            String email,
            String password,
            Role userRole,
            Role requiredRole,
            int requestsPerMinute,
            String payload,
            String resource) {

        this.email = email;
        this.password = password;
        this.userRole = userRole;
        this.requiredRole = requiredRole;
        this.requestsPerMinute = requestsPerMinute;
        this.payload = payload;
        this.resource = resource;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getUserRole() {
        return userRole;
    }

    public Role getRequiredRole() {
        return requiredRole;
    }

    public int getRequestsPerMinute() {
        return requestsPerMinute;
    }

    public String getPayload() {
        return payload;
    }

    public String getResource() {
        return resource;
    }
}
