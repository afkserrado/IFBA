package ifba.inf011.p2.s23_2;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

// Concrete Decorator do Decorator
public class AESDecorator extends BaseDecorator implements ICipher {
    
    private final byte[] key;

    public AESDecorator(Component inner) {
        super(inner);

        // Transforma a string 'chave-secreta-16' em uma sequência de 16 bytes
        this.key = Arrays.copyOf("chave-secreta-16".getBytes(StandardCharsets.UTF_8), 16
        ); // 16 bytes = AES-128
    }

    @Override
    public byte[] getContent() {
        byte[] content = super.getContent();
        byte[] contentEncrypted = encrypt(content);

        System.out.println("AesDecorator: retornando mensagem criptorafada com AES...");
        return contentEncrypted;
    }

    @Override
    public byte[] encrypt(byte[] content) {
        
        try {
            // Cria uma instância da classe de criptografia
            // AES: padrão de criptografia simétrica
            // Modo: ECB
            // PKCS5Padding: garante que o tamanho dos dados seja múltiplo de 16 bytes
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

            // Define o array de bytes como uma chave AES
            SecretKeySpec secretKey = new SecretKeySpec(this.key, "AES");

            // Inicial a criptografia
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            System.out.println("Criptografando dados, utilizando AES...");

            // Gera a criptografia e retorna
            return cipher.doFinal(content);
        } 
        
        catch (Exception e) {
            throw new RuntimeException("Erro ao criptografar com AES", e);
        }
    }
}
