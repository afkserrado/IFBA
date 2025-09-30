/*
7) Crie um programa em C que receba um vetor de números reais com 100 elementos. Escreva uma função recursiva que inverta ordem dos elementos presentes no vetor.
*/

#include <stdio.h>

void imprimir (int vetor[], int tam) {
    for (int i = 0; i < tam; i++) {
        printf("v[%d] = %d\n", i, vetor[i]);
    }
}

void inverter (int vetor[], int i, int tam) {
    // Caso base
    if (i == (tam / 2) - 1) {
        return;
    }

    // Inverte
    int temp = vetor[i];
    vetor[i] = vetor [(tam - i) - 1];
    vetor [(tam - i) - 1] = temp;

    // Chamada recursiva
    inverter(vetor, i - 1, tam);
}

int main () {

    int tam = 11;
    int vetor[tam];

    // Preenche o vetor
    for (int i = 0; i < tam; i++) {
        vetor[i] = i + 1;
    }

    // Imprime
    imprimir(vetor, tam);
    printf("\n");

    // Inverte e imprime
    inverter(vetor, tam, tam);
    imprimir(vetor, tam);
}