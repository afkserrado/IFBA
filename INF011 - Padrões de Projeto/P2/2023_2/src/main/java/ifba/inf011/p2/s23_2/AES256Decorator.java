package ifba.inf011.p2.s23_2;

// Adapter do Adapter
public class AES256Decorator extends BaseDecorator implements ICipher {
    
    private AES256 aes;

    public AES256Decorator(Component inner, AES256 aes) {
        super(inner);
        this.aes = aes;
        this.aes.init();
    }

    @Override
    public byte[] getContent() {
        byte[] content = super.getContent();
        byte[] contentEncrypted = encrypt(content);

        System.out.println("AES256Decorator: retornando mensagem criptografada com AES256...");
        return contentEncrypted;
    }

    @Override
    public byte[] encrypt(byte[] content) {
        return this.aes.doFinal(content);
    }
}
