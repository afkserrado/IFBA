/*
14) Faça uma função recursiva que receba um número inteiro positivo par N e imprima todos os números pares de 0 até N em ordem crescente.
*/

#include <stdio.h>

void impPares(int n) {
    if (n < 0) {
        return;
    }

    impPares(n - 1);
    
    if (n % 2 == 0) {
        printf("%d ", n);
    }
}


int main() {
    int n = 12;
    impPares(n);
    printf("\n");

    return 0;
}