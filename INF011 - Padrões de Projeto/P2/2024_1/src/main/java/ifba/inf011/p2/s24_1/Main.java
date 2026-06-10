package ifba.inf011.p2.s24_1;

import ifba.inf011.p2.s24_1.model.Credencial;

public class Main {
    public static void main(String[] args) {

        Pasta raiz = new Pasta("raiz");
        Pasta documentos = new Pasta("documentos");
        Pasta imagens = new Pasta("imagens");

        Arquivo arq1 = new Arquivo("trabalho.pdf", 100L);
        Arquivo arq2 = new Arquivo("foto.png", 200L);
        ArquivoProtegido arq3 = new ArquivoProtegido("prova.docx", 300L);

        raiz.adicionar(documentos);
        raiz.adicionar(imagens);

        documentos.adicionar(arq1);
        documentos.adicionar(arq3);
        imagens.adicionar(arq2);

        Credencial c1 = new Credencial();
        c1.setId("alice");

        Credencial c2 = new Credencial();
        c2.setId("bob");

        System.out.println("Tamanho da pasta documentos: " + documentos.getTamanho());
        System.out.println("Tamanho da pasta imagens: " + imagens.getTamanho());
        System.out.println("Tamanho da pasta raiz: " + raiz.getTamanho());

        arq3.ler(c1);
        arq3.ler(c1);
        arq3.ler(c2);

        System.out.println("Leituras do arquivo protegido realizadas.");
        System.out.println("Tamanho da pasta raiz após leituras: " + raiz.getTamanho());

        System.out.println("Leituras: " + arq3.getLog());
    }
}