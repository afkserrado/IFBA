package ifba.inf011.p2.s26_1.q3.proxy;

public class AccessDeniedException extends RuntimeException {

    public AccessDeniedException(String message) {
        super(message);
    }
}