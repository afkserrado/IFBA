/*
6) Crie um programa em C, que contenha uma função recursiva que receba dois inteiros positivos k e n e calcule k^n. Utilize apenas multiplicações. O programa principal deve solicitar ao usuário os valores de k e n e imprimir o resultado da chamada da função.
*/

#include <stdio.h>

int pot (int k, int n) {
    // Caso base
    if (n == 0) {
        return 1;
    }

    // Chamada recursiva
    return k * pot(k, n - 1); 
}

int main () {

    int k, n;

    printf("Informe os valores de k e n: ");
    scanf("%d %d", &k, &n);

    if (k < 0 || n < 0) {
        printf("Os números k e n devem ser inteiros positivos.\n");
        return 1;
    }

    int r = pot(k, n);
    printf("r = %d\n", r);

    return 0;
}