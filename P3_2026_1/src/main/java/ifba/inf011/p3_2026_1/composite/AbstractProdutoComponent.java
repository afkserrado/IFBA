package ifba.inf011.p3_2026_1.composite;

// Component abstrato do Composite
public abstract class AbstractProdutoComponent implements ProdutoComponent {
    
    private String titulo;

    public AbstractProdutoComponent(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String getTitulo() {
        return this.titulo;
    }

}
