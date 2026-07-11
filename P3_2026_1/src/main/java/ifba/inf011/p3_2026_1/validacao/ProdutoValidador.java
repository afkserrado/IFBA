package ifba.inf011.p3_2026_1.validacao;

import java.util.List;
import java.util.Objects;

import ifba.inf011.p3_2026_1.avaliacao1.timeline.builder.Timeline;
import ifba.inf011.p3_2026_1.composite.ProdutoComponent;

// Classe utilitária para validação dos dados de entrada
// 'final' impede que outra classe herde dela
public final class ProdutoValidador {

    // 'private' impede que a classe seja instanciada externamente
    private ProdutoValidador() {}

    // 'static' permite que os métodos públicos sejam utilizados sem criar instância
    
    public static void validarTitulo(String titulo) {
        validarNonNull(titulo, "O título não pode ser nulo.");
        validarNonBlank(titulo, "O título não pode estar em branco.");
    }

    public static void validarPreco(Double preco) {
        validarNonNull(preco, "O preço não pode ser nulo.");
        validarNaoNegativo(preco, "O preço não pode ser negativo.");
    }

    public static void validarNaoNegativo(Double numero) {
        validarNonNull(numero, "O número não pode ser nulo.");
        validarNaoNegativo(numero, "O número não pode ser negativo.");
    }

    public static void validarNaoNegativo(Integer numero) {
        validarNonNull(numero, "O número não pode ser nulo.");
        validarNaoNegativo(numero, "O número não pode ser negativo.");
    }

    public static void validarTimeline(Timeline timeline) {
        validarNonNull(timeline, "A timeline não pode ser nula.");
    }

    public static void validarLista(List<?> lista) {
        validarNonNull(lista, "A lista não pode ser nula.");

        lista.forEach(elemento -> validarNonNull(
            elemento, 
            "A lista não pode conter elementos nulos."
            )
        );
    }

    public static void validarLista(Object[] array) {
        validarNonNull(array, "O array não pode ser nulo.");

        for(Object elemento : array) {
            validarNonNull(
                elemento, 
                "O array não pode conter elementos nulos."
            );
        }
    }

    public static void validarProduto(ProdutoComponent produto) {
        validarNonNull(produto, "O produto não pode ser nulo.");
    }

    private static void validarNonBlank(String texto, String msg) {
        String mensagem = (msg == null || msg.isBlank())
                ? "O texto não pode estar em branco."
                : msg;

        if(texto.isBlank()) {
            throw new IllegalArgumentException(mensagem);
        }
    }

    private static void validarNonNull(Object objeto, String msg) {
        String mensagem = (msg == null || msg.isBlank())
                ? "O objeto não pode ser nulo."
                : msg;

        Objects.requireNonNull(objeto, mensagem);
    }

    private static void validarNaoNegativo(Double numero, String msg) {
        String mensagem = (msg == null || msg.isBlank())
                ? "O número não pode ser menor que zero."
                : msg;

        if(numero < 0) {
            throw new IllegalArgumentException(mensagem);
        }
    }

    private static void validarNaoNegativo(Integer numero, String msg) {
        String mensagem = (msg == null || msg.isBlank())
                ? "O número não pode ser menor que zero."
                : msg;

        if(numero < 0) {
            throw new IllegalArgumentException(mensagem);
        }
    }
}
