/*
5) Crie uma função recursiva que receba um número inteiro positivo N e calcule o somatório dos números de 1 a N.
*/

#include <stdio.h>

void somar (int i, int n, int *soma) {
    if (i > n) {
        return;
    }

    *soma += i;
    somar(i + 1, n, soma);
}


int main () {
    int n = 10;
    int soma = 0;
    somar(1, n, &soma);

    printf("Soma = %d\n", soma);

    return 0;
}
