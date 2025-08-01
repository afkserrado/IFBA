/*
4) (valor 2) Crie um programa em C que receba um vetor de números reais com 100 elementos. Escreva uma função recursiva que inverta ordem dos elementos presentes no vetor.
*/

#include <stdio.h>

void imprimir (int vetor[], int tam) {
    for (int i = 0; i < tam; i++) {
        printf("v[%d] = %d\n", i, vetor[i]);
    }
}

void inverter (int vetor[], int inicio, int fim) {
    // Caso base
    if (inicio >= fim) {
        return;
    }

    // Inverte
    int temp = vetor[fim];
    vetor[fim] = vetor [inicio];
    vetor [inicio] = temp;

    // Chamada recursiva
    inverter(vetor, inicio + 1, fim - 1);
}

int main () {

    int tam = 10;
    int vetor[tam];

    // Preenche o vetor
    for (int i = 0; i < tam; i++) {
        vetor[i] = i + 1;
    }

    // Imprime
    imprimir(vetor, tam);
    printf("\n");

    // Inverte e imprime
    inverter(vetor, 0, tam - 1);
    imprimir(vetor, tam);
}