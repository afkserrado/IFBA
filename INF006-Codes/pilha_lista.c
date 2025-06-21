#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <limits.h>

// Pilha com lista simplesmente encadeada

// Define a estrutura de um nó da lista
typedef struct node {
    int chave;
    struct node *prox;
} node;

// Cria a estrutura de uma pilha baseada em lista
typedef struct pilha {
    node *topo; 
} pilha;

// Cria e inicializa um novo nó, configurando seus ponteiros para NULL
node *init_node (int chave) {
    node *nNode = malloc(sizeof(node)); // Aloca memória para o nó
    if (nNode == NULL) { // Memória não alocada
        printf("Erro ao alocar memória para o nó.\n");
        return NULL;
    }
    nNode->chave = chave;
    nNode->prox = NULL;
    return nNode;
}

// Cria e inicializa uma pilha
pilha *init_pilha () {
    pilha *nPilha = malloc(sizeof(pilha)); // Aloca memória para a estrutura da pilha
    if (nPilha == NULL) { // Memória não alocada
        printf("Falha ao alocar memória para a estrutura da pilha.\n");
        return NULL;
    }
    nPilha->topo = NULL;
    return nPilha;
}

// Insere elementos no topo da pilha (início da lista)
void push (pilha *nPilha, node *nNode) {
    if (nPilha == NULL || nNode == NULL) { // Memória não alocada
        printf("Não é possível fazer o push.\n");
        return;
    }

    nNode->prox = nPilha->topo;
    nPilha->topo = nNode;  
}

// Remove elementos do topo da pilha (início da lista)
int pop (pilha *nPilha) {
    if (nPilha->topo == NULL) {
        printf("Pilha vazia.\n");
        return INT_MAX;
    }

    node *temp = nPilha->topo;
    nPilha->topo = temp->prox;
    int valor = temp->chave; // Armazena a chave para retorno
    
    free(temp);
    return valor;
}

// Retorna o último item inserido na pilha
int top (pilha *nPilha) {
    if (nPilha->topo != NULL) {
        return nPilha->topo->chave; // Retorna o item do topo
    } 
    else {
        printf("Pilha vazia.\n");
        return INT_MAX; // Retorna um valor de erro se a pilha estiver vazia
    }
}

// Limpa todos os itens da pilha sem destrui-la
void clear (pilha *nPilha) {
    node *x = nPilha->topo; // Inicializa no topo da pilha
    // Imprime os demais elementos
    while (x != NULL) {
        node *temp = x;
        x = x->prox; // Avança
        free(temp); // Libera memória do nó
    }
    nPilha->topo = NULL;
}

// Imprime a pilha
void imprimir_pilha (pilha *nPilha) {
    node *x = nPilha->topo; // Inicializa no topo da pilha
    
    // Imprime o topo
    if (x != NULL) { // Se a pilha não estiver vazia
        printf("topo ->  %d\n", x->chave); 
        x = x->prox; // Avança
    }
    
    // Imprime os demais elementos
    while (x != NULL) {
        printf("\t %d\n", x->chave); 
        x = x->prox; // Avança
    }
}

// Libera a memória alocada para os nós e a estrutura da pilha
void libera_pilha (pilha *nPilha) {
    node *x = nPilha->topo; // Inicializa no topo da pilha
    // Libera os elementos da pilha
    while (x != NULL) {
        node *temp = x;
        x = x->prox; // Avança
        free(temp); // Libera memória do nó
    }
    free(nPilha); // Libera memória alocada para a estrutura da pilha
}

int main() {
    pilha *nPilha = init_pilha();

    // Push
    push(nPilha, init_node(10));
    push(nPilha, init_node(5));
    push(nPilha, init_node(15));
    imprimir_pilha(nPilha);
    printf("\nTopo: %d\n", top(nPilha));
    clear(nPilha);
    imprimir_pilha(nPilha);
    printf("\n");

    // Libera a memória alocada para a pilha e seus itens
    libera_pilha(nPilha);
}