package ifba.inf011.s21_2.concrete_products;

import ifba.inf011.s21_2.interfaces.Product;

// Concrete product
public class Disciplina extends Product {

    private int cargaHoraria;

    public Disciplina(String codigo, String nome) {
        super(codigo, nome);
        this.cargaHoraria = 0;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    @Override
    public String toString() {
        return "Disciplina{" +
                "codigo='" + codigo + '\'' +
                ", nome='" + nome + '\'' +
                ", cargaHoraria=" + cargaHoraria +
                '}';
    }
}