package ifba.inf011.s23_2.interfaces;

public interface Protocolo {
    public void open();
    public void message(Request request, Response response);
    public void close();
}
