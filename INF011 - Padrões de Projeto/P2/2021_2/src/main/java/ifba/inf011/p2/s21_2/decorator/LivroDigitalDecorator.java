package ifba.inf011.p2.s21_2.decorator;

public class LivroDigitalDecorator extends LivroBaseDecorator {
    
    public LivroDigitalDecorator(LivroComponent inner) {
        super(inner);
    }

    @Override
    public double getPreco() {
        double preco = super.getPreco();
        return preco * 0.85;
    }
}
