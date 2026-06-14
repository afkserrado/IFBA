package ifba.inf011.p2.s21_2.decorator;

public class LivroCapaDuraDecorator extends LivroBaseDecorator {
    
    public LivroCapaDuraDecorator(LivroComponent inner) {
        super(inner);
    }

    @Override
    public double getPreco() {
        double preco = super.getPreco();
        return preco * 1.20;
    }
}
