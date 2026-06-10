package ifba.inf011.p2.s23_2;

// Concrete Component do Decorator
public class BasicRequest extends AbstractComponent {
 
    public BasicRequest(String content) {
        super(content);
    }

    @Override
    public byte[] getContent() {
        System.out.println("BasicRequest: retornando mensagem em formato de texto puro...");
        return this.content;
    }
}
