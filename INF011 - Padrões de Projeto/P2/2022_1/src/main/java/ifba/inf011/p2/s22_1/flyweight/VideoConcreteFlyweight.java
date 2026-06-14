package ifba.inf011.p2.s22_1.flyweight;

// Concrete Flyweight
// Armazena o estado intrínseco
public class VideoConcreteFlyweight implements VideoFlyweight {

    private final String descricao;

    public VideoConcreteFlyweight(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public void exibir() {
        System.out.println("[VÍDEO DO EXERCÍCIO " + descricao + "]");
    }
}
