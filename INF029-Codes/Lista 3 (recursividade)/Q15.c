/*
15) Faça uma função recursiva que receba um número inteiro positivo par N e imprima todos os números pares de 0 até N em ordem decrescente.
*/

#include <stdio.h>

void impPares(int n) {
    if (n < 0) {
        return;
    }

    if (n % 2 == 0) {
        printf("%d ", n);
    }

    impPares(n - 1);
}


int main() {
    int n = 12;
    impPares(n);
    printf("\n");

    return 0;
}