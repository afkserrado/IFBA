/*
6) Suponha que temos duas pilhas ordenadas de forma crescente, P1 e P2. Desempilhe os elementos destas pilhas e empilhe em P3, de modo que os elementos fiquem ordenados de forma decrescente. 

Exemplo: P1 = {1, 4, 8} e P2 = {2, 6, 7, 9}, onde o topo de P1 é 1 e o topo de P2 é 2.  P3 será {9, 8, 7, 6, 4, 2, 1}
*/

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
typedef struct estPilha {
    node *topo; 
} estPilha;

// Cria e inicializa um novo nó, configurando seus ponteiros para NULL
node *init_node (int chave) {
    node *novo = malloc(sizeof(node)); // Aloca memória para o nó
    
    if (novo == NULL) { // Memória não alocada
        printf("Erro ao alocar memória para o nó.\n");
        return NULL;
    }

    novo->chave = chave;
    novo->prox = NULL;
    return novo;
}

// Cria e inicializa uma pilha
estPilha *init_pilha () {
    estPilha *pilha = malloc(sizeof(estPilha)); // Aloca memória para a estrutura da pilha
    
    if (pilha == NULL) { // Memória não alocada
        printf("Falha ao alocar memória para a estrutura da pilha.\n");
        return NULL;
    }

    pilha->topo = NULL;
    return pilha;
}

// Insere elementos no topo da pilha (início da lista)
void push (estPilha *pilha, node *novo) {
    if (pilha == NULL || novo == NULL) { // Memória não alocada
        printf("Não é possível fazer o push.\n");
        return;
    }

    novo->prox = pilha->topo;
    pilha->topo = novo;  
}

// Remove elementos do topo da pilha (início da lista)
int pop (estPilha *pilha) {
    
    // Pilha vazia ou não alocada
    if (pilha == NULL || pilha->topo == NULL) {
        printf("Pilha vazia.\n");
        return '\0';
    }

    node *temp = pilha->topo;
    pilha->topo = temp->prox;
    int valor = temp->chave; // Armazena a chave para retorno
    
    free(temp);
    return valor;
}

// Imprime a pilha
void imprimir_pilha (estPilha *pilha) {
    
    // Pilha vazia
    if (pilha == NULL || pilha->topo == NULL) {
        printf("Pilha vazia.\n");
        return;
    }

    node *x = pilha->topo; // Inicializa no topo da pilha
    
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
void libera_pilha (estPilha *pilha) {
    
    // Pilha não alocada
    if (pilha == NULL) {
        return;
    }

    node *x = pilha->topo; // Inicializa no topo da pilha

    // Libera os elementos da pilha
    while (x != NULL) {
        node *temp = x;
        x = x->prox; // Avança
        free(temp); // Libera memória do nó
    }

    free(pilha); // Libera memória alocada para a estrutura da pilha
}

void mergePilha (estPilha *pilha1, estPilha *pilha2, estPilha *pilha3) {
    
    while (pilha1->topo != NULL && pilha2->topo != NULL) {
        
        // Compara as chaves nos topos das pilhas
        // Retira do topo da pilha1 e coloca no topo da pilha3
        if (pilha1->topo->chave <= pilha2->topo->chave) {
            node *novo = init_node(pop(pilha1));
            push(pilha3, novo);
        }

        // Retira do topo da pilha2 e coloca no topo da pilha3
        else {
            node *novo = init_node(pop(pilha2));
            push(pilha3, novo);
        }
    } 

    // Caso ainda haja elementos em pilha1, transfere para pilha3
    while (pilha1->topo != NULL) {
        push(pilha3, init_node(pop(pilha1)));
    }

    // Caso ainda haja elementos em pilha2, transfere para pilha3
    while (pilha2->topo != NULL) {
        push(pilha3, init_node(pop(pilha2)));
    }
}

int main() {
    estPilha *pilha1 = init_pilha();
    estPilha *pilha2 = init_pilha();
    estPilha *pilha3 = init_pilha();

    // Pushes
    push(pilha1, init_node(8));
    push(pilha1, init_node(4));
    push(pilha1, init_node(1));

    imprimir_pilha(pilha1);
    printf("\n");

    push(pilha2, init_node(9));
    push(pilha2, init_node(7));
    push(pilha2, init_node(6));
    push(pilha2, init_node(2));

    imprimir_pilha(pilha2);
    printf("\n");

    mergePilha(pilha1, pilha2, pilha3);
    imprimir_pilha(pilha3);

    // Libera a memória alocada para a estPilha e seus itens
    libera_pilha(pilha1);
    libera_pilha(pilha2);
    libera_pilha(pilha3);
}