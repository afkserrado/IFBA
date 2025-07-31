/*
2) (Valor 3.0) Considere uma lista encadeada simples circular sem cabeçote ordenada de forma decrescente. Faça uma função recursiva que imprima todos em ordem crescente.
*/

#include <stdio.h>
#include <stdlib.h>

// ##################################################### //
// LISTA SIMPLESMENTE LIGADA CIRCULAR (LSLC)

// Define a estrutura de um nó da lista
typedef struct node {
    int chave;
    struct node *prox;
} node;

// Define a estrutura da lista
typedef struct lslc {
    node *cabeca;
    node *cauda;
} lslc;

// Cria e inicializa um novo nó na lista, configurando seus ponteiros para NULL
node *init_node (int chave) {
    node *novo = (node*)malloc(sizeof(node)); // Aloca memória para o nó
    
    // Falha de alocação
    if (novo == NULL) {
        printf("Erro ao alocar memória para o nó.\n");
        return NULL;
    }

    novo->chave = chave;
    novo->prox = NULL;
    return novo;
}

// Cria e inicializa a lista, configurando seus ponteiros para NULL (lista vazia)
lslc *init_lista () {
    lslc *lista = (lslc*)malloc(sizeof(lslc)); // Aloca memória para a lista

    // Falha de alocação    
    if (lista == NULL) {
        printf("Erro ao alocar memória para a estrutura da lista.\n");
        return NULL;
    }

    lista->cabeca = NULL;
    lista->cauda = NULL;
    return lista;
}

// Insere um novo nó na lista, na posição ordenada (ordem decrescente)
void inserir_node_ordenado (lslc *lista, node *novo) {
    
    // Falha de alocação
    if (lista == NULL) {
        printf("Memória não alocada para a lista.\n");
        return;
    }

    if (novo == NULL) {
        printf("Memória não alocada para o nó.\n");
        return;
    }  

    // Lista vazia
    if (lista->cabeca == NULL) {
        lista->cabeca = novo;
        lista->cauda = novo;
        novo->prox = lista->cabeca; // O novo nó aponta para ele mesmo, fechando o círculo
    }
    
    // Lista não vazia
    else {
        
        // Variáveis temporárias
        node *posterior = lista->cabeca; // Marca a chave imediatamente posterior ao novo
        node *anterior = NULL; // Marca a chave imediatamente anterior ao novo
        int chave = novo->chave;

        // Encontra a posição correta
        do {
            if (chave >= posterior->chave) {break;}
            anterior = posterior;
            posterior = posterior->prox;
        } while (posterior != lista->cabeca);

        // Caso 1: o novo é o primeiro item da lista
        if (posterior == lista->cabeca && anterior == NULL) {
            novo->prox = lista->cabeca;
            lista->cabeca = novo;
            lista->cauda->prox = lista->cabeca; // Fecha o círculo
        }

        // Caso 2: o novo é o último item da lista
        else if (anterior == lista->cauda) {
            novo->prox = lista->cabeca;
            lista->cauda->prox = novo;
            lista->cauda = novo;
        }
        
        // Caso 3: o novo ocupa qualquer posição intermediária da lista
        else {
            novo->prox = posterior;
            anterior->prox = novo;     
        }
    }
}

// Imprimir lista
void iteImprimir_lista (lslc *lista) {
    
    // Falha de alocação
    if (lista == NULL) {
        printf("Memória não alocada para a lista.\n");
        return;
    }
    
    // Lista vazia
    if (lista->cabeca == NULL) { 
        printf("A lista está vazia.\n");
        return;
    }

    node *x = lista->cabeca; // Inicializa x com a cabeça da lista
    printf("head->");
    do {
        if (x->prox != lista->cabeca) {
            printf("(%d)->", x->chave);
        }
        else {
            printf("(%d)", x->chave);
        }
        
        x = x->prox;
    } while (x != lista->cabeca);
    printf("\n");
}

void recImprimir_lista (lslc *lista, node *x) {
    
    // Caso base: próximo nó é a cabeça
    if (x->prox == lista->cabeca) {
        printf("(%d)->", x->chave);
        return;
    }

    // Chamada recursiva
    recImprimir_lista(lista, x->prox);

    if (x != lista->cabeca) {
        printf("(%d)->", x->chave);
    }
    else {
        printf("(%d)<-head", x->chave);
    }
}

// Liberar a memória alocada para os nós e a lista
void liberar_lista(lslc *lista) {
    
    // Falha de alocação
    if (lista == NULL) {
        printf("Memória não alocada para a lista.\n");
        return;
    }
    
    // Libera a memória dos nós da lista
    if (lista->cabeca != NULL) {
        node *x = lista->cabeca;
        do {
            node *temp = x;
            x = x->prox;
            free(temp); // Libera a memória de cada nó
        } while (x != lista->cabeca);
    }

    // Libera a memória da lista, mesmo que ela esteja vazia
    free(lista);
}

int main () {
    lslc *lista = init_lista();

    inserir_node_ordenado(lista, init_node(20));
    inserir_node_ordenado(lista, init_node(37));
    inserir_node_ordenado(lista, init_node(18));
    inserir_node_ordenado(lista, init_node(40));
    inserir_node_ordenado(lista, init_node(18));
    inserir_node_ordenado(lista, init_node(-1));
    
    printf("\n");
    printf("Impressão iterativa em ordem decrescente: \n");
    iteImprimir_lista(lista);
    printf("\n");
    printf("Impressão recursiva em ordem crescente: \n");
    recImprimir_lista(lista, lista->cabeca);
    printf("\n\n");

    // Libera a memória alocada para todos os nós e para a lista
    liberar_lista(lista);
    lista = NULL;
}


