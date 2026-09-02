package ifba.inf011.p3_2026_1.avaliacao3.util;

import java.util.Collection;
import java.util.Objects;

public final class ValidadorUtil {

    private ValidadorUtil() {}

    public static void validarObjeto(Object objeto, String mensagem) {
        Objects.requireNonNull(objeto, resolverMensagem(mensagem, "O objeto não pode ser nulo."));
    }

    public static void validarTexto(String texto, String mensagem) {
        validarObjeto(texto, resolverMensagem(mensagem, "O texto não pode ser nulo."));

        if (texto.isBlank()) {
            throw new IllegalArgumentException(
                resolverMensagem(mensagem, "O texto não pode estar em branco.")
            );
        }
    }

    public static void validarNaoNegativo(Double numero, String mensagem) {
        validarObjeto(numero, resolverMensagem(mensagem, "O número não pode ser nulo."));

        if (numero < 0) {
            throw new IllegalArgumentException(
                resolverMensagem(mensagem, "O número não pode ser negativo.")
            );
        }
    }

    public static void validarNaoNegativo(Integer numero, String mensagem) {
        validarObjeto(numero, resolverMensagem(mensagem, "O número não pode ser nulo."));

        if (numero < 0) {
            throw new IllegalArgumentException(
                resolverMensagem(mensagem, "O número não pode ser negativo.")
            );
        }
    }

    public static void validarColecao(Collection<?> colecao, String mensagem) {
        validarObjeto(colecao, resolverMensagem(mensagem, "A coleção não pode ser nula."));

        for (Object elemento : colecao) {
            validarObjeto(elemento, "A coleção não pode conter elementos nulos.");
        }
    }

    public static void validarArray(Object[] array, String mensagem) {
        validarObjeto(array, resolverMensagem(mensagem, "O array não pode ser nulo."));

        for (Object elemento : array) {
            validarObjeto(elemento, "O array não pode conter elementos nulos.");
        }
    }

    private static String resolverMensagem(String mensagem, String mensagemPadrao) {
        return mensagem == null || mensagem.isBlank()
            ? mensagemPadrao
            : mensagem;
    }
}