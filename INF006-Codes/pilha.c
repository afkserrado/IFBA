#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <limits.h>

// Pilha

// Cria a estrutura de uma pilha
typedef struct pilha {
    int *itens; // Ponteiro para o array que armazena os elementos da pilha
    int tamanho; // Tamanho máximo da pilha
    int topo; // Índice do topo da pilha (inicialmente -1, porque começa vazia)
} pilha;

// Cria e inicializa uma pilha chamada pilha_nv
void inicializa_pilha (pilha *pilha_nv, int tamanho) {
    pilha_nv->itens = malloc(tamanho * sizeof(int)); // Aloca memória para o array da pilha
    pilha_nv->tamanho = tamanho; // Define o tamanho máximo da pilha
    pilha_nv->topo = -1; // Inicializa o topo como -1 (pilha vazia)
}

bool pilha_cheia (pilha *pilha_nv) {
    return pilha_nv->topo == pilha_nv->tamanho - 1; // Retorna true se a pilha estiver cheia
}

bool pilha_vazia (pilha *pilha_nv) {
    return pilha_nv->topo == -1; // Retorna true se o topo for -1 (pilha vazia)
}

// Insere elementos na pilha
void push (pilha *pilha_nv, int item) {
    if (!pilha_cheia(pilha_nv)) { // Se a pilha estiver vazia
        pilha_nv->topo = pilha_nv->topo + 1; // Incrementa o topo, adicionando o item na pilha_nv
        pilha_nv->itens[pilha_nv->topo] = item; // Insere o item no topo da pilha
    }
    else {
        printf("Pilha cheia.\n");
    }
}

// Remove elementos da pilha
int pop (pilha *pilha_nv) {
    if (!pilha_vazia(pilha_nv)) { // Se a pilha não estiver vazia
        pilha_nv->topo = pilha_nv->topo - 1; // Decrementa o topo, removendo o item da pilha_nv
        return pilha_nv->itens[pilha_nv->topo + 1]; // Retorna o elemento removido
    }
    else {
        printf("Pilha vazia.\n");
        return INT_MAX; // Retorna um valor de erro se a pilha estiver vazia
    }
}

// Retorna o último item inserido na pilha
int top (pilha *pilha_nv) {
    if (!pilha_vazia(pilha_nv)) {
        return pilha_nv->itens[pilha_nv->topo]; // Retorna o item do topo
    } 
    else {
        printf("Pilha vazia.\n");
        return INT_MAX; // Retorna um valor de erro se a pilha estiver vazia
    }
}

// Retorna a quantidade de itens na pilha
int size (pilha *pilha_nv) {
    return pilha_nv->topo + 1; // topo é um índice de base 0
}

// Limpa todos os itens da pilha sem destrui-la
void clear (pilha *pilha_nv) {
    pilha_nv->topo = -1; // Reseta o topo
}

void imprimir_pilha (pilha *pilha_nv) {
    printf("<< \n");
    for (int i = pilha_nv->topo; i >= 0; i--) {
        if (i == pilha_nv->topo) {
            printf("topo ->  %d\n", pilha_nv->itens[i]); // Marca o topo
        } 
        else
            printf("\t %d\n", pilha_nv->itens[i]); // Imprime os demais elementos
    }
    printf(">>\n");
}

int main() {
    pilha *pilha_nv = malloc(sizeof(pilha));
    inicializa_pilha(pilha_nv, 10);
    push(pilha_nv, 10);
    push(pilha_nv, 5);
    push(pilha_nv, 15);
    imprimir_pilha(pilha_nv);
    printf("Topo: %d\n", top(pilha_nv));
    printf("Tamanho: %d\n", size(pilha_nv));
    clear(pilha_nv);
    imprimir_pilha(pilha_nv);

    // Liberação de memória
    free(pilha_nv->itens);  // Libera a memória alocada para o array de itens
    free(pilha_nv);  // Libera a memória alocada para a estrutura da pilha
}