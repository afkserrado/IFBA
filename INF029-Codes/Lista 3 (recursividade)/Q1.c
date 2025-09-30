/*
1) Faça uma função recursiva que calcule e retorne o fatorial de um número inteiro N.
*/

#include <stdio.h>

int fatorial (int n);

int main () {
    int n = 3;
    int resultado = fatorial(n);
    printf("Resultado = %d\n", resultado);

    return 0;
}

int fatorial (int n) {
    if (n == 0) {
        return 1;
    }

    return n * fatorial(n - 1);
}