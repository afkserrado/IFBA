package ifba.inf011.p2.s22_2.implementation;

// Implementation do Bridge
public interface Formato {
    public void comentario(String texto);
    public String negrito(String texto);
    public String italico(String texto);
    public void texto(String texto);
    public void inicioDocumento();
    public String fimDocumento();
}
