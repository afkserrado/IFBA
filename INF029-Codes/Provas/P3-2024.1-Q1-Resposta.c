/*
1) (Valor 2) Faça uma função recursiva que receba um número inteiro negativo par N e imprima todos os números pares de N até 0 em ordem decrescente. Ex: Se N = -7, deve ser impresso: 0, -2, -4, -6.
*/

#include <stdio.h>
#include <stdlib.h>

void par(int N) {
    
    // Se N for positivo 
    if (N > 0) {
        return;
    }

    // Se N for ímpar
    if (N % 2 != 0) {
        N += 1;
    }

    // Caso base
    if (N > 0) {
        return;
    }

    // Chamada recursiva
    par(N + 2);

    // Exibe os números
    printf("N = %d\n", N);
}

int main() {
    int N = -7;
    par(N);
    return 0;
}
