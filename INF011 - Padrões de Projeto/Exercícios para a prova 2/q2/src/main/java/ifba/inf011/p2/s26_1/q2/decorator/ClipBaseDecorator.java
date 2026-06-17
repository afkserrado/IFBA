package ifba.inf011.p2.s26_1.q2.decorator;

import java.util.List;

import ifba.inf011.p2.s26_1.q2.composite.ClipComponent;

// Base Decorator
public abstract class ClipBaseDecorator implements ClipComponent {

    protected ClipComponent inner;

    public ClipBaseDecorator(ClipComponent inner) {
        this.inner = inner;
    }

    @Override
    public String render() {
        return this.inner.render();
    }

    @Override
    public int getDuracao() {
        return this.inner.getDuracao();
    }

    @Override
    public void adicionar(ClipComponent clip) {
        this.inner.adicionar(clip);
    }

    @Override
    public void remover(ClipComponent clip) {
        this.inner.remover(clip);
    }

    @Override
    public List<ClipComponent> getFilhos() {
        return this.inner.getFilhos();
    }
}