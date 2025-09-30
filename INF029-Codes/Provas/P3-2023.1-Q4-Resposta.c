/*
4. (Valor 3.0) Considere uma lista encadeada ordenada de forma crescente. Faça uma função recursiva que imprima a lista em ordem decrescente.
*/

#include <stdio.h>
#include <stdlib.h>

// ##################################################### //
// LISTA SIMPLESMENTE LIGADA LINEAR (LSLL)

// Define a estrutura de um nó da lista
typedef struct node {
    int chave;
    struct node *prox;
} node;

// Define a estrutura da lista
typedef struct lsll {
    node *cabeca;
    node *cauda;
} lsll;

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
lsll *init_lista () {
    lsll *lista = (lsll*)malloc(sizeof(lsll)); // Aloca memória para a lista

    // Falha de alocação    
    if (lista == NULL) {
        printf("Erro ao alocar memória para a estrutura da lista.\n");
        return NULL;
    }

    lista->cabeca = NULL;
    lista->cauda = NULL;
    return lista;
}

// Insere um novo nó na lista, na posição ordenada (ordem crescente)
void inserir_node_ordenado (lsll *lista, node *novo) {
    
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
    }

    // Lista não vazia
    else { 
        
        // Variáveis temporárias
        node *ante = NULL;
        node *atual = lista->cabeca;
        int chave = novo->chave;

        while (atual != NULL && chave > atual->chave) {
            ante = atual;
            atual = atual->prox;
        }

        // Caso 1: o novo é o primeiro item da lista
        if (atual == lista->cabeca) {
            novo->prox = lista->cabeca;
            lista->cabeca = novo;
        }
        // Caso 2: o novo é o último item da lista
        else if (atual == NULL) {
            lista->cauda->prox = novo;
            lista->cauda = novo;
        }
        // Caso 3: o novo ocupa qualquer posição intermediária da lista
        else {
            ante->prox = novo;
            novo->prox = atual;     
        }
    }
}

// Imprimir lista iterativamente
void iteImprimir_lista (lsll *lista) {
    
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
    while (x != NULL) {
        if (x->prox != NULL) {
            printf("(%d)->", x->chave);
        }
        else {
            printf("(%d)", x->chave);
        }
        
        x = x->prox;
    }
    printf("\n");
}

// Imprimir lista recursivamente
void recImprimir_lista (lsll *lista, node *x) {
    
    // Caso base: próximo nó é a cabeça
    if (x->prox == NULL) {
        printf("(%d)<-", x->chave);
        return;
    }

    // Chamada recursiva
    recImprimir_lista(lista, x->prox);

    if (x != lista->cabeca) {
        printf("(%d)<-", x->chave);
    }
    else {
        printf("(%d)<-head", x->chave);
    }
}

// Liberar a memória alocada para os nós e a lista
void liberar_lista(lsll *lista) {
    
    // Falha de alocação
    if (lista == NULL) {
        printf("Memória não alocada para a lista.\n");
        return;
    }
    
    // Libera a memória dos nós da lista
    if (lista->cabeca != NULL) {
        node *x = lista->cabeca;
        while (x != NULL) {
            node *temp = x;
            x = x->prox;
            free(temp); // Libera a memória de cada nó
        }
    }

    // Libera a memória da lista, mesmo que ela esteja vazia
    free(lista);
}

int main () {
    lsll *lista = init_lista();

    inserir_node_ordenado(lista, init_node(20));
    inserir_node_ordenado(lista, init_node(37));
    inserir_node_ordenado(lista, init_node(18));
    inserir_node_ordenado(lista, init_node(40));
    inserir_node_ordenado(lista, init_node(18));
    inserir_node_ordenado(lista, init_node(-1));
    
    printf("\n");
    printf("Impressão iterativa em ordem crescente: \n");
    iteImprimir_lista(lista);
    printf("\n");
    printf("Impressão recursiva em ordem decrescente: \n");
    recImprimir_lista(lista, lista->cabeca);
    printf("\n\n");

    // Libera a memória alocada para todos os nós e para a lista
    liberar_lista(lista);
    lista = NULL;
}