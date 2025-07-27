/*
1) Apresente a implementação de duas filas circulares em um vetor de 20 posições, de 0 a 19. A primeira fila deve utilizar as posições de 0 a 9, a segunda fila deve utilizar as posições de 10 a 19. Cada fila circular deve ter as operações incluir na lista, pesquisar na lista e excluir da lista um valor pesquisado.
*/

#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

#define tamVetor 20
#define tamFila (tamVetor / 2)
#define min1 0
#define min2 tamFila

// Fila circular com vetor com contador

#include <stdio.h>
#include <stdlib.h>

// ##################################################### //
// FILA CIRCULAR COM VETOR SEM CONTADOR (FCV)

// Estrutura da fila
typedef struct estFila {
    int *itens; // Ponteiro para o array de elementos da fila
    int tam; // Quantidade máxima de elementos da fila
    int ini; // Índice do início da fila
    int fim; // Índice da próxima posição livre, não do último elemento
} estFila;

// Inicializa a fila
void init_fila (estFila *fila, int *vetor) {
    fila->itens = vetor;
    fila->tam = tamFila;
    fila->ini = 0;
    fila->fim = 0;
}

// Enfileirar: inserir elementos na fila
void enqueue (estFila *fila, int novo) {
    
    // Falha de alocação
    if (fila == NULL) {
        printf("Fila não existe.\n");
        return;
    }
    
    // Fila cheia
    // Início = Fim + 1
    if (fila->ini == (fila->fim + 1) % fila->tam) {
        printf("Fila cheia.\n");
        return;
    }

    // Fila não cheia
    fila->itens[fila->fim] = novo;

    // Incrementa o fim, resetando após inserir no último índice, mantendo a circularidade
    fila->fim = (fila->fim + 1) % fila->tam; // Aponta para a próxima posição livre
}

// Desinfileirar: retirar um valor buscado da fila
int dequeue (estFila *fila, int valor) {
    
    // Falha de alocação
    if (fila == NULL) {
        printf("Fila não existe.\n");
        return INT_MAX;
    }
    
    // Fila vazia
    // Início = Fim
    if (fila->ini == fila->fim) {
        printf("Fila vazia.\n");
        return INT_MAX;
    }

    // Buscar o valor na fila
    int i = fila->ini;
    int encontrado = 0;
    do {
        // Valor encontrado
        if (fila->itens[i] == valor) {
            encontrado = 1;
            
            // Shift
            // Loop até o fim (posição livre)
            for (int j = i; j != fila->fim; j = (j + 1) % fila->tam) {
                fila->itens[j] = fila->itens[(j + 1) % fila->tam];
            }

            // Atualiza fim
            fila->fim = (fila->fim - 1 + fila->tam) % fila->tam;  // Mantém a circularidade
            
            printf("O %d foi excluído.\n", valor);
            break;
        }
        i = (i + 1) % fila->tam; // Incrementa, mantendo a circularidade
    } while (i != fila->fim); // Loop até o fim (posição livre)

    if (encontrado == 0) {
        printf("Valor não encontrado.\n");
        return INT_MAX;
    }
    return valor;
}

// Busca um valor na fila
void busca (estFila *fila, int valor) {
    
    // Fila vazia
    // Início = Fim
    if (fila->ini == fila->fim) {
        printf("Fila vazia.\n");
        return;
    }
    
    int i = fila->ini;
    do {
        if (fila->itens[i] == valor) {
            printf("Valor %d\n", fila->itens[i]);
            return;
        }
        i = (i + 1) % fila->tam;
    } while (i != fila->fim); // O fim aponta para a próxima posição livre

    // Valor não encontrado
    printf("Valor %d não encontrado na fila.\n", valor);
}

void imprimirFila (estFila *fila) {
    
    // Falha de alocação
    if (fila == NULL) {
        printf("Fila não existe.\n");
        return;
    }
    
    // Pilha vazia
    if (fila->ini == fila->fim) {
        printf("Fila vazia.\n");
    }
    
    int i = fila->ini;
    while (i != fila->fim) {
        if (i != fila->ini) {printf(" ");}
        printf("%d", fila->itens[i]);
        i = (i + 1) % fila->tam; // Mantém a circularidade
    }    
    printf("\n");
}

int main() {

    // Aloca memória para o vetor
    int *vetor = (int *)malloc(tamVetor * sizeof(int));

    // Aloca memória para a fila
    estFila *fila1 = (estFila *)malloc(sizeof(estFila));
    estFila *fila2 = (estFila *)malloc(sizeof(estFila));
    
    // Inicializa as filas
    init_fila(fila1, &vetor[min1]);
    init_fila(fila2, &vetor[min2]);

    // Fila 1
    printf("Fila 1: \n");

    dequeue(fila1, 10); // Fila vazia

    printf("\nInserções: \n");
    enqueue(fila1, 10);
    enqueue(fila1, 20);
    enqueue(fila1, 30);
    enqueue(fila1, 40);
    enqueue(fila1, 50);
    enqueue(fila1, 60);
    enqueue(fila1, 70);
    enqueue(fila1, 80);
    enqueue(fila1, 90);
    enqueue(fila1, 100); // Fila cheia
    imprimirFila(fila1); // 10, 20, 30, 40, 50, 60, 70, 80, 90

    printf("\nBuscas: \n");
    busca(fila1, 10);
    busca(fila1, 20);
    busca(fila1, 30);
    busca(fila1, 40);
    busca(fila1, 50);
    busca(fila1, 60);
    busca(fila1, 70);
    busca(fila1, 80);
    busca(fila1, 90);
    busca(fila1, 100); // Valor 100 não encontrado na fila.

    printf("\nExclusões: \n");
    dequeue(fila1, 10);
    dequeue(fila1, 20);
    dequeue(fila1, 30);
    dequeue(fila1, 40);
    dequeue(fila1, 50);
    dequeue(fila1, 60);
    dequeue(fila1, 70);
    dequeue(fila1, 80);
    dequeue(fila1, 90);
    dequeue(fila1, 100); // Fila vazia
    imprimirFila(fila1); // Fila vazia

    printf("Outros testes: \n");
    dequeue(fila1, 10);  // Fila vazia
    imprimirFila(fila1); // Fila vazia

    dequeue(fila1, 40);  // Fila vazia
    imprimirFila(fila1); // Fila vazia

    dequeue(fila1, 100); // Fila vazia
    imprimirFila(fila1); // Fila vazia

    enqueue(fila1, 100);
    enqueue(fila1, 40);
    enqueue(fila1, 10);
    imprimirFila(fila1); // 100, 40, 10

    busca(fila1, 20); // Valor 20 não encontrado na fila.
    busca(fila1, 60); // Valor 60 não encontrado na fila.
    busca(fila1, 10); // Valor 10
    busca(fila1, 200); // Valor 200 não encontrado na fila.

    // Fila 2

    printf("\nFila 2: \n");

    dequeue(fila2, 10); // Fila vazia

    enqueue(fila2, 10);
    enqueue(fila2, 20);
    enqueue(fila2, 30);
    enqueue(fila2, 40);
    enqueue(fila2, 50);
    enqueue(fila2, 60);
    enqueue(fila2, 70);
    enqueue(fila2, 80);
    enqueue(fila2, 90);
    enqueue(fila2, 100); // Fila cheia
    imprimirFila(fila2); // 10, 20, 30, 40, 50, 60, 70, 80, 90
    
    dequeue(fila2, 10);
    imprimirFila(fila2); // 20, 30, 40, 50, 60, 70, 80, 90

    dequeue(fila2, 40);
    imprimirFila(fila2); // 20, 30, 50, 60, 70, 80, 90

    dequeue(fila2, 100);
    imprimirFila(fila2); // Valor não encontrado

    enqueue(fila2, 100);
    enqueue(fila2, 40);
    enqueue(fila2, 10); // Fila cheia
    imprimirFila(fila2); // 20, 30, 50, 60, 70, 80, 90, 100, 40

    enqueue(fila2, 50); // Fila cheia

    busca(fila2, 20); // 1
    busca(fila2, 60); // 4
    busca(fila2, 10); // Valor 10 não encontrado na fila.
    busca(fila2, 200); // Valor 200 não encontrado na fila
    
    // Libera a memória alocada
    free(fila1);
    free(fila2);
    free(vetor);
    fila1 = NULL;
    fila2 = NULL;
    
    return 0;
}