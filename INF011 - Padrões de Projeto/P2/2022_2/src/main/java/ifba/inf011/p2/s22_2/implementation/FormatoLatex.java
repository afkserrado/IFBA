package ifba.inf011.p2.s22_2.implementation;

// Concrete Implementation do Bridge
public class FormatoLatex extends AbstractFormato {
    
    @Override
    public void comentario(String texto) {
        foiIniciado();
        
        this.relatorio
            .append("% ")
            .append(texto)
            .append("\n");
    }

    @Override
    public String negrito(String texto) {
        return "\\textbf{" + texto + "}";
    }

    @Override
    public String italico(String texto) {
        return "\\emph{" + texto + "}";
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
    }

    @Override public String fimDocumento() {
        foiIniciado();
        return this.relatorio.toString();
    }
}
