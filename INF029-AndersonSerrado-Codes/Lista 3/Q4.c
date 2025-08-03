/*
4) Faça uma função recursiva que permita somar os elementos de um vetor de inteiros.
*/

#include <stdio.h>

void somarVetor (int vetor[], int inicio, int final, int *resultado) {
    if (inicio > final) {
        return;
    }

    *resultado += vetor[inicio];
    somarVetor(vetor, inicio + 1, final, resultado);
}


int main () {
    int vetor[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    int tam = sizeof(vetor) / sizeof(vetor[0]);
    int resultado = 0;

    somarVetor(vetor, 0, tam - 1, &resultado);
    printf("Resultado = %d\n", resultado);

    return 0;
}
