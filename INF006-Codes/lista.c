#include<stdio.h>
#include<stdlib.h>

// Lista duplamente encadeada não ordenada

// Define a estrutura de um nó da lista
typedef struct node{
    int chave;
    struct node *ante;
    struct node *prox;
} node;

// Define a estrutura de uma lista duplamente encadeada
typedef struct lista_dup{
    node *cabeca;
} lista_dup;

// Cria e inicializa um novo nó e configura seus ponteiros para NULL
node *init_node (int chave) {
    node *node_st = malloc(sizeof(node)); // Aloca memória para o primeiro nó
    node_st->chave = chave;
    node_st->ante = NULL;
    node_st->prox = NULL;
    return node_st;
}

/*
Obs. 1: a variável "novo" é como se fosse um "array" heterogêneo, porque ela precisa armazenar dados diferentes (neste caso, um inteiro e dois endereços).
Ao alocar memória com malloc, estamos definindo o tamanho desse "array", e ao mesmo tempo garantindo espaço suficiente na memória para esse nó caso a struct "node" seja modificada no futuro.
Obs. 2: "init_node" é um ponteiro para "node", pois retorna uma variável do tipo ponteiro para node.
*/

// Cria e inicializa uma nova lista, configurando o head para NULL (lista vazia)
lista_dup *init_lista () {
    lista_dup *lista = malloc(sizeof(lista_dup));
    lista->cabeca = NULL;
    return lista;
}

// Insere um novo nó no início da lista
void inserir_node (lista_dup *lista, node *node_nv) {
    if (lista->cabeca == NULL) { // Lista vazia
        lista->cabeca = node_nv; // Insere o novo nó na cabeça da lista
    }
    else { // Lista não vazia
        // Insere o novo nó no início da lista
        node_nv->prox = lista->cabeca; // "prox" do novo nó aponta para o atual "cabeca"
        lista->cabeca->ante = node_nv; // "ante" do atual "cabeca" aponta para o novo nó
        lista->cabeca = node_nv; //  "cabeca" recebe o novo nó
    }
}

// Remover um nó qualquer da lista
void remover_node (lista_dup *lista, int chave) {
    node *x = lista->cabeca;
    while (x != NULL && x->chave != chave) { // Procurando a chave na lista
        x = x->prox;
    }

    if (x != NULL) { // Se a chave for encontrada
        if (x == lista->cabeca) { // Se for o primeiro nó
            if (x->prox == NULL) { // Se a lista só possuir um nó
                lista->cabeca = NULL;
            }
            else { // Se a lista possuir mais de um nó
                lista->cabeca = x->prox;
                lista->cabeca->ante = NULL;
            }
        }
        else { // Se não for o primeiro nó
            if (x->ante != NULL) {
                x->ante->prox = x->prox; // O "ante" do atual aponta para o "prox" do atual
            }
            if (x->prox != NULL) {
                x->prox->ante = x->ante; // O "prox" do atual aponta para o "ante" do atual
            }
        }
        free(x);
    }
}

// Imprime a lista
void imprimir_lista (lista_dup *lista) {
    node *x = lista->cabeca; // Inicializa x com a "cabeca" da lista
    printf("\n(NULL)"); // Início da lista
    while (x != NULL) {
        printf("<- (%d) ->", x->chave);
        x = x->prox;
    }
    printf(" (NULL)\n\n"); // Fim da lista
}

int main(){
    lista_dup *idades = init_lista();

    inserir_node(idades, init_node(20));
    inserir_node(idades, init_node(37));
    inserir_node(idades, init_node(18));
    inserir_node(idades, init_node(40));
    inserir_node(idades, init_node(18));
    inserir_node(idades, init_node(-1));

    imprimir_lista(idades);
    remover_node(idades, -1);
    printf("Depois\n");
    imprimir_lista(idades);
    remover_node(idades, 40);
    printf("Depois\n");
    imprimir_lista(idades);
    remover_node(idades, 20);
    printf("Depois\n");
    imprimir_lista(idades);
    remover_node(idades, 100);
    printf("Depois\n");
    imprimir_lista(idades);
}