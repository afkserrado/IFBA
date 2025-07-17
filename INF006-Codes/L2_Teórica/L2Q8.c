/*
8) Implemente duas pilhas em um único vetor de 100 posições. Da posição 0 até a posição 
49 é a pilha A, da posição 50 até a posição 99 é a pilha B. Apresente as operações de 
push e pop para ambas as pilhas.
*/

#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

// Pilha com vetor

#define tamVetor 10
#define tamPilha (tamVetor / 2)
#define min1 0
#define min2 tamPilha

// Cria a estrutura de uma pilha
typedef struct estPilha {
    int *itens; // Ponteiro para o array que armazena os elementos da pilha
    int tam; // Tamanho máximo da pilha
    int topo; // Índice do topo da pilha (inicialmente -1, porque começa vazia)
} estPilha;

// Cria e inicializa uma pilha chamada pilha
void init_pilha (estPilha *pilha, int *vetor) {
    pilha->itens = vetor; // Referencia o subvetor
    pilha->tam = tamPilha; // Define o tamanho máximo da pilha
    pilha->topo = -1; // Inicializa o topo
}

// Insere elementos na pilha
void push (estPilha *pilha, int novo) {
    
    // Pilha cheia
    if (pilha->topo == pilha->tam - 1) {
        printf("Pilha cheia.\n");
        return;
    }
    
    // Pilha não cheia
    pilha->topo = pilha->topo + 1; // Incrementa o topo, adicionando o novo na pilha
    pilha->itens[pilha->topo] = novo; // Insere o novo no topo da pilha
}

// Insere elementos ordenadamente na pilha (crescente da base para o topo)
void push_ordenado (estPilha *pilha, int novo) {
    
    // Pilha cheia
    if (pilha->topo == pilha->tam - 1) {
        printf("Pilha cheia.\n");
        return;
    }

    // Pilha não cheia
    int atual = pilha->topo; // Topo atual
    pilha->topo += 1; // Incrementa o topo
    
    // Encontra a posição correta do novo
    while (atual >= 0 && pilha->itens[atual] > novo) {
        pilha->itens[atual + 1] = pilha->itens[atual];
        atual--;
    }

    // Insere o novo na posição correta
    pilha->itens[atual + 1] = novo;

 }

// Remove elementos da pilha
int pop (estPilha *pilha) {
    
    // Pilha vazia
    if (pilha->topo == -1) {
        printf("Pilha vazia.\n");
        return INT_MAX;
    }

    pilha->topo -= 1; // Decrementa o topo, removendo o item da pilha
    return pilha->itens[pilha->topo + 1]; // Retorna o elemento removido
}

void imprimir_pilha (estPilha *pilha) {
    for (int i = pilha->topo; i >= 0; i--) {
        if (i == pilha->topo) {
            printf("topo ->  %d\n", pilha->itens[i]); // Marca o topo
        } 
        else
            printf("\t %d\n", pilha->itens[i]); // Imprime os demais elementos
    }
}

void imprimirVetor (int *v) {
    printf("Vetor: [");
    int primeiro = 0;
    for (int i = 0; i < tamVetor; i++){
        if (primeiro == 0){
            printf("%d", v[i]);
            primeiro = 1;
        }
        else 
            printf(", %d", v[i]);
    }
    printf("]");
}

// Limpa todos os itens da pilha sem destrui-la
void clear (estPilha *pilha) {
    pilha->topo = -1; // Reseta o topo
}

int main() {
    
    estPilha *pilha1 = malloc(sizeof(estPilha));
    estPilha *pilha2 = malloc(sizeof(estPilha));
    int *vetor = (int *)malloc(tamVetor * sizeof(int));

    // Push
    init_pilha(pilha1, &vetor[min1]);
    init_pilha(pilha2, &vetor[min2]);

    printf("\nPilha desordenada: \n");

    pop(pilha1); // Pilha vazia
    printf("\n");

    push(pilha1, 10);
    push(pilha1, 5);
    push(pilha1, 15);
    push(pilha1, 3);
    push(pilha1, 50);
    imprimir_pilha(pilha1); // 50, 3, 15, 5, 10
    printf("\n");

    push(pilha1, 50); // Pilha cheia
    printf("\n");

    pop(pilha2); // Pilha vazia
    printf("\n");

    push(pilha2, 40);
    push(pilha2, 64);
    push(pilha2, 22);
    push(pilha2, 11);
    push(pilha2, 98);
    imprimir_pilha(pilha2); // 98, 11, 22, 64, 40
    printf("\n");

    push(pilha2, 98); // Pilha cheia
    printf("\n");

    imprimirVetor(vetor); //10, 5, 15, 3, 50, 40, 64, 22, 11, 98
    printf("\n");

    clear(pilha1);
    clear(pilha2);

    // Push ordenado
    printf("\nPilha ordenada: \n");

    pop(pilha1); // Pilha vazia
    printf("\n");

    push_ordenado(pilha1, 10);
    push_ordenado(pilha1, 5);
    push_ordenado(pilha1, 15);
    push_ordenado(pilha1, 3);
    push_ordenado(pilha1, 50);
    imprimir_pilha(pilha1); // 50, 3, 15, 5, 10
    printf("\n");

    push_ordenado(pilha1, 50); // Pilha cheia
    printf("\n");

    pop(pilha2); // Pilha vazia
    printf("\n");

    push_ordenado(pilha2, 40);
    push_ordenado(pilha2, 64);
    push_ordenado(pilha2, 22);
    push_ordenado(pilha2, 11);
    push_ordenado(pilha2, 98);
    imprimir_pilha(pilha2); // 98, 11, 22, 64, 40
    printf("\n");

    push_ordenado(pilha2, 98); // Pilha cheia
    printf("\n");

    imprimirVetor(vetor); //10, 5, 15, 3, 50, 40, 64, 22, 11, 98
    printf("\n");

    // Libera a memória alocada para a pilha e seus itens
    free(pilha1);  // Libera a memória alocada para a estrutura da pilha
    free(pilha2);  // Libera a memória alocada para a estrutura da pilha
    free(vetor);
}
