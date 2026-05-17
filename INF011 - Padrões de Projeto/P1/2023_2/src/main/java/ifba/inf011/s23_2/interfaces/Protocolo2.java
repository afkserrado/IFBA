package ifba.inf011.s23_2.interfaces;

// Abstract Product
public interface Protocolo2 {
    public void open();
    public void message(String header, String body);
    public void close();
}
