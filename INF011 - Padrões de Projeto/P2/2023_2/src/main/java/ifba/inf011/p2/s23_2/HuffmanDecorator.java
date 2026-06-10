package ifba.inf011.p2.s23_2;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;

// Concrete Decorator do Decorator
public class HuffmanDecorator extends BaseDecorator implements ICompressor {
    
    public HuffmanDecorator(Component inner) {
        super(inner);
    }

    @Override
    public byte[] getContent() {
        byte[] content = super.getContent();
        byte[] contentEncrypted = compress(content);

        System.out.println("HuffmanDecorator: retornando mensagem compactada com Huffman...");
        return contentEncrypted;
    }

    @Override
    public byte[] compress(byte[] content) {
        
        // Classe que aplica o algoritmo DEFLATE para compactação
        Deflater deflater = new Deflater(); 
        
        // Injeção de dados
        deflater.setInput(content);
        
        // Sinaliza que não haverá mais entrada de dados
        deflater.finish();

        // Array de bytes que cresce dinamicamente
        try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream(content.length)) {
            byte[] buffer = new byte[1024]; // Buffer temporário de 1KB

            while(!deflater.finished()) {

                // Comprime parte dos dados, sobrescreve no buffer temporário
                // e retorna quantos bytes foram gerados
                int count = deflater.deflate(buffer);

                // Pega os bytes do buffer da posição 0 até count
                // Escreve no contêiner depois da última posição preenchida
                outputStream.write(buffer, 0, count);
            }

            System.out.println("Compactando dados, utilizando Huffman...");
            return outputStream.toByteArray();
        } 
        
        catch (IOException e) {
            throw new RuntimeException("Erro ao compactar conteúdo", e);
        }

        finally {
            deflater.end();
        } 
    }
}
