package ifba.inf011.p3_2026_1.avaliacao3.composite;

import ifba.inf011.p3_2026_1.avaliacao3.util.ValidadorUtil;

public abstract class AbstractProdutoComponent implements ProdutoComponent {
    
    private static final String MSG_TITULO_INVALIDO =
        "O título não pode ser nulo ou em branco.";

    private String titulo;

    public AbstractProdutoComponent(String titulo) {
        ValidadorUtil.validarTexto(titulo, MSG_TITULO_INVALIDO);
        this.titulo = titulo;
    }

    @Override
    public String getTitulo() {
        return this.titulo;
    }

    protected void setTitulo(String titulo) {
        ValidadorUtil.validarTexto(titulo, MSG_TITULO_INVALIDO);
        this.titulo = titulo;
    }
}