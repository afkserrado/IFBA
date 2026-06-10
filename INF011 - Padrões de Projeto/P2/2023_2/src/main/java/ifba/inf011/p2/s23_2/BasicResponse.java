package ifba.inf011.p2.s23_2;

// Concrete Component do Decorator
public class BasicResponse extends AbstractComponent {
 
    public BasicResponse(String content) {
        super(content);
    }

    @Override
    public byte[] getContent() {
        System.out.println("BasicResponse: retornando mensagem em formato de texto puro...");
        return this.content;
    }
}
