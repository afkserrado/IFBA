/*
1) Apresente a implementação de duas filas circulares em um vetor de 20 posições, de 0 a 19. A primeira fila deve utilizar as posições de 0 a 9, a segunda fila deve utilizar as posições de 10 a 19. Cada fila circular deve ter as operações incluir na lista, pesquisar na lista e excluir da lista um valor pesquisado.
*/

#include <stdio.h>
#include <stdlib.h>

// Fila circular com vetor com contador

#define tamFila 10
#define tamVetor 20
#define cabeca1 0
#define cabeca2 10
#define cauda1 9
#define cauda2 19

// Estrutura da fila
typedef struct estFila {
    int *itens; // Ponteiro para o array de elementos da fila
    int tam; // Quantidade máxima de elementos da fila
    int qtd; // Quantidade de elementos da fila
    int ini; // Índice do início da fila
    int fim; // Índice do fim da fila
} estFila;

// Inicializa a fila
void init_fila (estFila *fila, int *vetor, int cabeca) {
    fila->itens = &vetor[cabeca];
    fila->tam = tamFila;
    fila->qtd = 0;
    fila->ini = cabeca;
    fila->fim = cabeca;
}

// Incrementa circularmente um índice dentro dos limites [cabeca, cabeca + tamFila - 1]
int proximoIndice(int atual, int cabeca, int cauda) {
    return (atual == cauda) ? cabeca : atual + 1;
}

// Enfileirar: inserir elementos na fila
void enqueue (estFila *fila, int cabeca, int cauda, int novo) {
    // Fila cheia
    if (fila->qtd == fila->tam) {
        printf("Fila cheia.\n");
        return;
    }

    // Fila não cheia
    fila->itens[fila->fim] = novo; // Acrescenta no final da fila
    fila->fim = proximoIndice(fila->fim, cabeca, cauda);
    fila->qtd += 1; // Incrementa quantidade
}

// Desinfileirar: retirar elementos da fila
void dequeue (estFila *fila, int cabeca, int cauda, int valor) {
    // Verifica se a fila está vazia
    if (fila->qtd == 0) {
        printf("Fila vazia.\n");
        return;
    }
    
    int i = fila->ini;
    int cont = 0;
    int achou = 0;

    while (cont < fila->qtd) {
        
        // Valor encontrado
        if (fila->itens[i - cabeca] == valor) {

            // Valor está no início da fila
            if (i == fila->ini) {
                fila->ini = proximoIndice(fila->ini, cabeca, cauda);      
                achou = 1;          
            }

            // Valor está no final da fila
            else if (i == fila->fim - 1) {
                fila->fim = (fila->fim == cabeca) ? cauda : fila->fim - 1;
                achou = 1;
            }

            // Valor está no meio da fila
            else {
                int j = i;
                int next = proximoIndice(j, cabeca, cauda); // Posição do próximo elemento

                // Shift circular para mover os elementos
                while (next != fila->fim) {
                    fila->itens[j - cabeca] = fila->itens[next - cabeca]; // Move o elemento
                    j = next;
                    next = proximoIndice(j, cabeca, cauda);
                }

                // Atualiza o índice do fim da fila
                fila->fim = (fila->fim == cabeca) ? cauda : fila->fim - 1;
                achou = 1;
            }
            break; // Encerra a busca
        }

        i = proximoIndice(i, cabeca, cauda);
        cont++;
    }

    if (achou == 1) {fila->qtd -= 1;} // Atualiza a quantidade
    else {printf("Valor %d não encontrado na fila.\n", valor);}
}

// Busca um valor na fila
void busca (estFila *fila, int cabeca, int cauda, int valor) {
    int i = fila->ini;
    for (int cont = 0; cont < fila->qtd; cont++) {
        if (fila->itens[i - cabeca] == valor) {
            int posFila = (i - cabeca + tamFila) % tamFila + cabeca;
            printf("Valor encontrado na posição %d.\n", posFila);
            return;
        }

        i = proximoIndice(i, cabeca, cauda);
    }  
    // Valor não encontrado
    printf("Valor %d não encontrado na fila.\n", valor);
}

void imprimirFila (estFila *fila, int cabeca, int cauda) {
    int i = fila->ini;
    for (int cont = 0; cont < fila->qtd; cont++) {
        if (cont != 0) {printf(" ");}
        printf("%d", fila->itens[i]);
        
        i = proximoIndice(i, cabeca, cauda);
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
    init_fila(fila1, vetor, cabeca1);
    init_fila(fila2, vetor, cabeca2);

    // Fila 1
    printf("Fila 1: \n");

    dequeue(fila1, cabeca1, cauda1, 10); // Fila vazia

    enqueue(fila1, cabeca1, cauda1, 10);
    enqueue(fila1, cabeca1, cauda1, 20);
    enqueue(fila1, cabeca1, cauda1, 30);
    enqueue(fila1, cabeca1, cauda1, 40);
    enqueue(fila1, cabeca1, cauda1, 50);
    enqueue(fila1, cabeca1, cauda1, 60);
    enqueue(fila1, cabeca1, cauda1, 70);
    enqueue(fila1, cabeca1, cauda1, 80);
    enqueue(fila1, cabeca1, cauda1, 90);
    enqueue(fila1, cabeca1, cauda1, 100);
    imprimirFila(fila1, cabeca1, cauda1); // 10, 20, 30, 40, 50, 60, 70, 80, 90, 100
    
    dequeue(fila1, cabeca1, cauda1, 10);
    imprimirFila(fila1, cabeca1, cauda1); // 20, 30, 40, 50, 60, 70, 80, 90, 100

    dequeue(fila1, cabeca1, cauda1, 40);
    imprimirFila(fila1, cabeca1, cauda1); // 20, 30, 50, 60, 70, 80, 90, 100

    dequeue(fila1, cabeca1, cauda1, 100);
    imprimirFila(fila1, cabeca1, cauda1); // 20, 30, 50, 60, 70, 80, 90

    enqueue(fila1, cabeca1, cauda1, 100);
    enqueue(fila1, cabeca1, cauda1, 40);
    enqueue(fila1, cabeca1, cauda1, 10);
    imprimirFila(fila1, cabeca1, cauda1); // 20, 30, 50, 60, 70, 80, 90, 100, 40, 10

    enqueue(fila1, cabeca1, cauda1, 50); // Fila cheia

    busca(fila1, cabeca1, cauda1, 20); // 1
    busca(fila1, cabeca1, cauda1, 60); // 4
    busca(fila1, cabeca1, cauda1, 10); // 0
    busca(fila1, cabeca1, cauda1, 200); // Valor 200 não encontrado na fila

    // Fila 2

    printf("\nFila 2: \n");
    dequeue(fila2, cabeca2, cauda2, 10); // Fila vazia

    enqueue(fila2, cabeca2, cauda2, 10);
    enqueue(fila2, cabeca2, cauda2, 20);
    enqueue(fila2, cabeca2, cauda2, 30);
    enqueue(fila2, cabeca2, cauda2, 40);
    enqueue(fila2, cabeca2, cauda2, 50);
    enqueue(fila2, cabeca2, cauda2, 60);
    enqueue(fila2, cabeca2, cauda2, 70);
    enqueue(fila2, cabeca2, cauda2, 80);
    enqueue(fila2, cabeca2, cauda2, 90);
    enqueue(fila2, cabeca2, cauda2, 100);
    imprimirFila(fila2, cabeca2, cauda2); // 10, 20, 30, 40, 50, 60, 70, 80, 90, 100

    dequeue(fila2, cabeca2, cauda2, 10);
    imprimirFila(fila2, cabeca2, cauda2); // 20, 30, 40, 50, 60, 70, 80, 90, 100

    dequeue(fila2, cabeca2, cauda2, 40);
    imprimirFila(fila2, cabeca2, cauda2); // 20, 30, 50, 60, 70, 80, 90, 100

    dequeue(fila2, cabeca2, cauda2, 100);
    imprimirFila(fila2, cabeca2, cauda2); // 20, 30, 50, 60, 70, 80, 90

    enqueue(fila2, cabeca2, cauda2, 100);
    enqueue(fila2, cabeca2, cauda2, 40);
    enqueue(fila2, cabeca2, cauda2, 10);
    imprimirFila(fila2, cabeca2, cauda2); // 20, 30, 50, 60, 70, 80, 90, 100, 40, 10

    enqueue(fila2, cabeca2, cauda2, 50); // Fila cheia

    busca(fila2, cabeca2, cauda2, 20); // 0
    busca(fila2, cabeca2, cauda2, 60); // 3
    busca(fila2, cabeca2, cauda2, 10); // 9
    busca(fila2, cabeca2, cauda2, 200); // Valor 200 não encontrado na fila
    
    // Libera a memória alocada
    free(fila1);
    free(fila2);
    free(vetor);
    fila1 = NULL;
    fila2 = NULL;
    
    return 0;
}