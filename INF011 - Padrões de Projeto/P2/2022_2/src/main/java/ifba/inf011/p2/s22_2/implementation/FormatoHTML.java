package ifba.inf011.p2.s22_2.implementation;

// Concrete Implementation do Bridge
public class FormatoHTML extends AbstractFormato {
    
    @Override
    public void comentario(String texto) {
        foiIniciado();
        
        this.relatorio
            .append("<!-- ")
            .append(texto)
            .append(" -->\n");
    }

    @Override
    public String negrito(String texto) {
        return "<bold>" + texto + "</bold>";
    }

    @Override
    public String italico(String texto) {
        return "<italic>" + texto + "</italic>";
    }

    @Override
    public void texto(String texto) {
        foiIniciado();
        
        this.relatorio
            .append(texto)
            .append("\n");
    }

    @Override
    public void inicioDocumento() {
        this.relatorio = new StringBuilder(); // Reset
        setIniciado(true);
        this.relatorio.append("<HTML>\n");
        this.relatorio.append("<HEAD></HEAD>\n");
        this.relatorio.append("<BODY>\n");
    }

    @Override public String fimDocumento() {
        foiIniciado();
        
        this.relatorio.append("</BODY>");
        return this.relatorio.toString();
    }
}
