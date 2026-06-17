package ifba.inf011.p2.s26_1.q2.composite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Composite do Composite
// Concrete Component do Decorator
public class ClipComposto implements ClipComponent {

    private String nome;
    private List<ClipComponent> filhos;

    public ClipComposto(String nome) {
        this.nome = nome;
        this.filhos = new ArrayList<>();
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public void adicionar(ClipComponent clip) {
        this.filhos.add(clip);
    }

    @Override
    public void remover(ClipComponent clip) {
        this.filhos.remove(clip);
    }

    @Override
    public List<ClipComponent> getFilhos() {
        return Collections.unmodifiableList(this.filhos);
    }

    @Override
    public int getDuracao() {
        int total = 0;

        for (ClipComponent filho : this.filhos) {
            total += filho.getDuracao();
        }

        return total;
    }

    @Override
    public String render() {
        StringBuilder sb = new StringBuilder();

        sb.append("[ClipComposto] ")
          .append(this.nome)
          .append(" (")
          .append(this.getDuracao())
          .append("s)")
          .append("\n");

        for (ClipComponent filho : this.filhos) {
            sb.append(filho.render()).append("\n");
        }

        return sb.toString();
    }

    @Override
    public String toString() {
        return this.render();
    }
}