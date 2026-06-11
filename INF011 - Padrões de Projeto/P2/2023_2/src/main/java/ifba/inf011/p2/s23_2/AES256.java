package ifba.inf011.p2.s23_2;

import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

// Service/Adaptee do Adapter
public class AES256 {
    
    // Guarda a chave usada pelo algoritmo
    private SecretKey secretKey;

    // Inicializa o AES com uma chave aleatória de 256 bits
    public void init() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256); // AES-256
            this.secretKey = keyGenerator.generateKey();
        } 
        
        catch (Exception e) {
            throw new RuntimeException("Erro ao inicializar AES256 com chave aleatória.", e);
        }
    }

    // Inicializa o AES com uma chave fornecida explicitamente
    public void init(byte[] chave) {
        try {
            
            // Garante que a chave terá 32 bytes (256 bits)
            // Cria um novo array com o conteúdo de 'chave'
                // Se 'chave' tiver mais de 32 bytes, ele pega apenas os primeiros 32 bytes
                // Se 'chave' tiver menos de 32 bytes, preenche o restante com zeros
            byte[] key = Arrays.copyOf(chave, 32);

            this.secretKey = new SecretKeySpec(key, "AES");
        } 
        
        catch (Exception e) {
            throw new RuntimeException("Erro ao inicializar AES256 com chave fornecida.", e);
        }
    }

    // Criptografa a mensagem usando a chave previamente inicializada
    public byte[] doFinal(byte[] message) throws SecurityException {
        
        // Se ninguém chamou init antes, lança exceção
        if (this.secretKey == null) {
            throw new SecurityException("AES256 não foi inicializado com chave.");
        }

        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, this.secretKey);
            return cipher.doFinal(message);
        } 
        
        catch (Exception e) {
            throw new RuntimeException("Erro ao criptografar mensagem com AES256.", e);
        }
    }
}
