#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <limits.h>

// Pilha

typedef struct {
    int *itens; // Ponteiro para o array que armazena os elementos da pilha
    int tamanho; // Tamanho máximo da pilha
    int topo; // Índice do topo da pilha (inicialmente -1, porque começa vazia)
} pilha;

void inicializa_pilha (pilha *lista, int tamanho) {
    lista->itens = malloc(tamanho * sizeof(int)); // Aloca memória para o array da pilha
    lista->tamanho = tamanho; // Define o tamanho máximo da pilha
    lista->topo = -1; // Inicializa o topo como -1 (pilha vazia)
}

bool pilha_cheia (pilha *lista) {
    return lista->topo == lista->tamanho - 1; // Retorna true se a pilha estiver cheia
}

bool pilha_vazia (pilha *lista) {
    return lista->topo == -1; // Retorna true se o topo for -1 (pilha vazia)
}

// Insere elementos na pilha
void push (pilha *lista, int item) {
    if (!pilha_cheia(lista)) { // Se a pilha estiver vazia
        lista->topo = lista->topo + 1; // Incrementa o topo, adicionando o item na lista
        lista->itens[lista->topo] = item; // Insere o item no topo da pilha
    }
    else {
        printf("Pilha cheia.\n");
    }
}

// Remove elementos da pilha
int pop (pilha *lista) {
    if (!pilha_vazia(lista)) { // Se a pilha não estiver vazia
        lista->topo = lista->topo - 1; // Decrementa o topo, removendo o item da lista
        return lista->itens[lista->topo + 1]; // Retorna o elemento removido
    }
    else {
        printf("Pilha vazia.\n");
        return INT_MAX; // Retorna um valor de erro se a pilha estiver vazia
    }
}

void imprimir_pilha (pilha *lista) {
    printf("<< \n");
    for (int i = lista->topo; i >= 0; i--) {
        if (i == lista->topo) {
            printf("topo ->  %d\n", lista->itens[i]); // Marca o topo
            printf("\t %d\n", lista->itens[i]); // Imprime o topo
        } 
        else
            printf("\t %d\n", lista->itens[i]); // Imprime os demais elementos
    }
    printf(">>\n");
}

int main() {
    pilha *lista = malloc(sizeof(pilha));
    inicializa_pilha(lista, 10);
    push(lista, 10);
    push(lista, 5);
    push(lista, 15);
    imprimir_pilha(lista);

    // Liberação de memória
    free(lista->itens);  // Libera a memória alocada para o array de itens
    free(lista);  // Libera a memória alocada para a estrutura da pilha
}