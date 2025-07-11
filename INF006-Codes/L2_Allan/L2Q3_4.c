/*
3) A figura abaixo mostra uma lista simplesmente encadeada, onde cada nó possui o ponteiro para o próximo e um valor. O ponteiro inicio indica o inicio da lista (aponta para NULO se a lista for vazia). Apresente os algoritmos corretos para:
a) Inserir um novo nó;
b) Buscar um elemento com um dado valor val retornando o mesmo ou indicar que o elemento não existe na lista;
c) Apagar um nó existente.
*/

#include <stdio.h>
#include <stdlib.h>

// Lista simplesmente ligada linear

// Define a estrutura de um nó da lista
typedef struct node {
    int chave; 
    struct node *prox;
} node;

// Define a estrutura de uma lista duplamente encadeada
typedef struct lsll {
    node *cabeca;
    node *cauda;
} lsll;

// Cria e inicializa um novo nó, configurando seus ponteiros para NULL
node *init_node (int chave) {
    node *novo = malloc(sizeof(node)); // Aloca memória para o primeiro nó

    // Falha de alocação
    if (novo == NULL) {
        printf("Erro ao alocar memória para o nó.\n");
        return NULL;
    }

    novo->chave = chave;
    novo->prox = NULL;
    return novo;
}

// Cria e inicializa uma nova lista, configurando seus ponteiros para NULL (lista vazia)
lsll *init_lista () {
    lsll *lista = malloc(sizeof(lsll));

    // Falha de alocação
    if (lista == NULL) {
        printf("Erro ao alocar memória para a estrutura da lista.\n");
        return NULL;
    }

    lista->cabeca = NULL;
    lista->cauda = NULL;
    return lista;
}

// Insere um novo nó no início da lista
void inserir_node (lsll *lista, node *novo) {
    
    // Lista vazia
    if (lista->cabeca == NULL) { 
        lista->cabeca = novo;
        lista->cauda = novo;
    }

    // Lista não vazia 
    else {  
        novo->prox = lista->cabeca; // "prox" do novo nó aponta para o atual "cabeca"
        lista->cabeca = novo; //  "cabeca" recebe o novo nó
    }
}

// Insere um novo nó na posição ordenada
void inserir_node_ordenado (lsll *lista, node *novo) {
    
    // Lista vazia
    if (lista->cabeca == NULL) { 
        lista->cabeca = novo;
        lista->cauda = novo;
    }

    // Lista não vazia
    else { 
        
        node *atual = lista->cabeca;
        node *anterior = NULL;
        int chave = novo->chave;

        // Percorre a lista
        while (atual != NULL && atual->chave < chave) {
            anterior = atual;
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
            novo->prox = atual;  
            anterior->prox = novo;   
        }
    }
}

// Remover um nó qualquer da lista
void remover_node (lsll *lista, int chave) {
    
    if (lista->cabeca == NULL) { // Lista vazia
        return;
    }
    
    node *atual = lista->cabeca;
    node *anterior = NULL;

    // Percorre a lista
    while (atual != NULL && atual->chave != chave) {
        anterior = atual;
        atual = atual->prox;
    }

    // Chave encontrada
    if (atual != NULL) {
        
        // Caso 1: chave é a cabeça da lista
        if (atual == lista->cabeca) {
            
            // Caso 1.1: lista unitária
            if (atual->prox == NULL) {
                lista->cabeca = NULL;
                lista->cauda = NULL;
            }

            // Caso 1.2: lista com múltiplos elementos
            else {
                lista->cabeca = atual->prox;
            }
        }

        // Caso 2: chave é a cauda da lista
        else if (atual == lista->cauda) {
            anterior->prox = NULL;
            lista->cauda = anterior;
        }

        // Caso 3: chave está numa posição intermediária da lista
        else {
            anterior->prox = atual->prox;
        }

        free(atual);
    }
}

// Busca sequencial
void buscaSequencial (lsll *lista, int chave) {

    // Lista vazia
    if (lista->cabeca == NULL) { 
        printf("Lista vazia.\n");
        return;
    }

    // Busca a chave
    node *atual = lista->cabeca;
    while (atual != NULL) {
        // Chave encontrada
        if (atual->chave == chave) {
            printf("Chave: %d\n", atual->chave);
            return;
        }

        atual = atual->prox;
    }

    // Chave não encontrada
    printf("Chave não encontrada.\n");
}

// Busca sequencial ordenada
void buscaSequencialOrdenada (lsll *lista, int chave) {

    // Lista vazia
    if (lista->cabeca == NULL) { 
        printf("Lista vazia.\n");
        return;
    }

    // Busca a chave
    node *atual = lista->cabeca;
    while (atual != NULL && chave >= atual->chave) {
        // Chave encontrada
        if (atual->chave == chave) {
            printf("Chave: %d\n", atual->chave);
            return;
        }
        atual = atual->prox;
    }

    printf("Chave não encontrada.\n");
}

// Imprime a lista
void imprimir_lista (lsll *lista) {
    node *x = lista->cabeca; // Inicializa x com a "cabeca" da lista
    printf("\n(NULL)"); // Início da lista
    while (x != NULL) {
        printf("<- (%d) ->", x->chave);
        x = x->prox;
    }
    printf(" (NULL)\n\n"); // Fim da lista
}

// Função para liberar todos os nós da lista
void liberar_lista(lsll *lista) {
    node *x = lista->cabeca;
    while (x != NULL) {
        node *temp = x;
        x = x->prox;
        free(temp); // Libera a memória de cada nó
    }
    free(lista); // Libera a memória da lista
}

int main(){
    
    lsll *lista1 = init_lista();
    lsll *lista2 = init_lista();

    printf("Lista 1:\n");
    inserir_node(lista1, init_node(20));
    inserir_node(lista1, init_node(37));
    inserir_node(lista1, init_node(18));
    inserir_node(lista1, init_node(40));
    inserir_node(lista1, init_node(18));
    inserir_node(lista1, init_node(-1));
    imprimir_lista(lista1);

    remover_node(lista1, -1);
    printf("Depois\n");
    imprimir_lista(lista1);
    remover_node(lista1, 40);
    printf("Depois\n");
    imprimir_lista(lista1);
    remover_node(lista1, 20);
    printf("Depois\n");
    imprimir_lista(lista1);
    remover_node(lista1, 100);
    printf("Depois\n");
    imprimir_lista(lista1);

    buscaSequencial(lista1, 18);
    buscaSequencial(lista1, 37);
    buscaSequencial(lista1, 40);

    printf("\nLista 2:\n");
    inserir_node_ordenado(lista2, init_node(20));
    inserir_node_ordenado(lista2, init_node(37));
    inserir_node_ordenado(lista2, init_node(18));
    inserir_node_ordenado(lista2, init_node(40));
    inserir_node_ordenado(lista2, init_node(18));
    inserir_node_ordenado(lista2, init_node(-1));
    imprimir_lista(lista2);

    remover_node(lista2, -1);
    printf("Depois\n");
    imprimir_lista(lista2);
    remover_node(lista2, 40);
    printf("Depois\n");
    imprimir_lista(lista2);
    remover_node(lista2, 20);
    printf("Depois\n");
    imprimir_lista(lista2);
    remover_node(lista2, 100);
    printf("Depois\n");
    imprimir_lista(lista2);

    buscaSequencialOrdenada(lista2, 18);
    buscaSequencialOrdenada(lista2, 37);
    buscaSequencialOrdenada(lista2, 40);

    // Libera a memória alocada para todos os nós e para as listas
    liberar_lista(lista1);
    liberar_lista(lista2);
    lista1 = NULL;
    lista2 = NULL;
}