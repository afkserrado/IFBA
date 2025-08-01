/*
1) (Valor 2) Faça uma função recursiva que receba um número inteiro negativo par N e imprima todos os números pares de N até 0 em ordem crescente.
*/

#include <stdio.h>

void cresc(int N) {

    // Caso base
    if (N > 0) {
        return;
    }

    // Se N for ímpar, pega próximo par
    if (N % 2 != 0) {
        N += 1; 
    }

    printf("N = %d\n", N);
    cresc(N + 2);
}

int main () {

    int N = -7;
    cresc(N);

    return 0;
}