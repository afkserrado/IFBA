package ifba.inf011.p2.s21_2.decorator;

public abstract class LivroBaseDecorator implements LivroComponent {
    
    protected LivroComponent inner;

    public LivroBaseDecorator(LivroComponent inner) {
        this.inner = inner;
    }

    // Herda de Precificavel
    @Override
    public double getPreco() {
        return this.inner.getPreco();
    }
}
