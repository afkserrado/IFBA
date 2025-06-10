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

/*
'itens' é como se fosse um vetor de inteiros. 'malloc' separa um bloco contíguo de memória para armazenar os inteiros. Esse bloco terá tamanho * 4 bytes. 'itens', portanto, contém o endereço de memória do primeiro byte desse bloco.
Quando fazemos itens[1], estamos acessando o segundo inteiro desse bloco, que ocupa os bytes 5 a 8.
*/

/*
'pilha_nv' é um ponteiro para 'pilha'. Ou seja, ela aponta para um bloco de memória que contém dados do tipo 'pilha'.

Sendo assim, 'pilha_nv' tem um total de 16 bytes:
int *itens = 8 bytes (qualquer endereço de memória precisa de 8 bytes na arquitetura x64)
int tamanho = 4 bytes
int topo = 4 bytes

Mas '*itens' é um ponteiro que aponta para um bloco de memória onde de fato estão os itens da pilha. 'pilha_nv' apenas armazena o endereço desse bloco, e não o bloco.

Se o 'tamanho' for 5, então o bloco apontado por 'itens' teria 4 * 5 = 20 bytes, pois são necessários 4 bytes para cada número armazenado na pilha.
*/

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

    /*
    'malloc' separa um espaço dentro da memória para armazenar a pilha e retorna o endereço desse espaço para a variável 'pilha_nv'.

    Ou seja, 'pilha_nv' é declarada como uma variável do tipo ponteiro para pilha. Como ela armazena o endereço de um espaço de memória, precisamos saber quem é esse endereço. O 'malloc', então, separa um espaço na memória para armazenar uma todos os campos de uma 'pilha' e retorna esse endereço para 'pilha_nv'.

    Sendo assim, 'pilha_nv' aponta para um espaço na memória que possui 16 bytes: 8 do int *itens + 4 de int tamanho + 4 de int topo.

    'itens' também é um ponteiro, que aponta para outro espaço de memória, mas esse espaço é variável, pois depende do tamanho. Isto é, o espaço de memória destinado aos itens da pilha possui 4 bytes para cada item da pilha. Se a pilha tiver 10 itens, então 'itens' apontará para um espaço de memória com 40 bytes.
    */

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
