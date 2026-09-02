package br.edu.ifba.acervo_ms.enums;

import org.springframework.data.domain.Sort;

public enum OrdenacaoLivro {
    
    TITULO_ASC("titulo_asc", Sort.by("titulo").ascending()),
    TITULO_DESC("titulo_desc", Sort.by("titulo").descending()),
    AUTOR_ASC("autor_asc", Sort.by("autor").ascending()),
    AUTOR_DESC("autor_desc", Sort.by("autor").descending());

    private final String valor;
    private final Sort sort;

    OrdenacaoLivro(String valor, Sort sort) {
        this.valor = valor;
        this.sort = sort;
    }

    public String getValor() {
        return valor;
    }

    public Sort getSort() {
        return sort;
    }

    public static Sort resolverSort(String valor) {
        
        if (valor == null || valor.isBlank()) {
            return TITULO_ASC.getSort();
        }

        for (OrdenacaoLivro ordenacao : values()) {
            if (ordenacao.valor.equalsIgnoreCase(valor)) {
                return ordenacao.getSort();
            }
        }

        return TITULO_ASC.getSort();
    }
}
