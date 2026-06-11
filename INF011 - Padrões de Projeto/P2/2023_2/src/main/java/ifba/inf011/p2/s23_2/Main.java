package ifba.inf011.p2.s23_2;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== QUESTÃO 1: DECORATOR (REQUESTS) ===\n");

        // 1. Mensagem limpa (sem decorator)
        Component request1 = new BasicRequest("MENSAGEM LIMPA");
        System.out.println("REQUEST 1:");
        System.out.println(new String(request1.getContent()));
        System.out.println();

        // 2. Mensagem criptografada com AES
        Component request2 = new AESDecorator(
                                new BasicRequest("MENSAGEM CRIPTOGRAFADA AES")
                             );

        System.out.println("REQUEST 2:");
        System.out.println(request2.getContent());
        System.out.println();

        // 3. Mensagem compactada com Huffman (Deflate)
        Component request3 = new HuffmanDecorator(
                                new BasicRequest("MENSAGEM COMPACTADA HUFFMAN")
                             );

        System.out.println("REQUEST 3:");
        System.out.println(request3.getContent());
        System.out.println();

        // 4. Mensagem criptografada (AES) + compactada (Huffman)
        Component request4 = new HuffmanDecorator(
                                new AESDecorator(
                                    new BasicRequest("MSG AES + HUFFMAN")
                                )
                             );

        System.out.println("REQUEST 4:");
        System.out.println(request4.getContent());
        System.out.println();

        System.out.println("=== QUESTÃO 2: ADAPTER ===\n");

        // 5. Exemplo com Response também (simetria do modelo)
        Component response = new AES256Decorator(
                                new HuffmanDecorator(
                                    new BasicResponse("RESPOSTA CRIPTOGRAFADA E COMPACTADA")
                                )
                             , new AES256());

        System.out.println("RESPONSE:");
        System.out.println(response.getContent());
    }
}