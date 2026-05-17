package ifba.inf011.s23_2.interfaces;

// Abstract Factory
public interface Fabrica {
    public Request createRequest();
    public Response createResponse();
    public Protocolo createProtocolo(Integer porta, String host);
}
