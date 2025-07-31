/*
4) (Valor 2) Escreva uma função recursiva que determine quantas vezes um dígito K ocorre em um número natural N. Por exemplo, o dígito 2 ocorre 3 vezes em 762021192.
*/

#include <stdio.h>

void contark (int K, int N, int *cont) {
    
    // Caso base
    if (N == 0) {
        return;
    }

    // Verifica se o último dígito é o dígito buscado (K)
    if (N % 10 == K) {
        (*cont)++;
    }

    // Chamada recursiva
    contark(K, N / 10, cont);
}

int main () {

    int N = 11111;
    int K = 1;
    int cont = 0;

    contark(K, N, &cont);
    printf("\n");
    printf("O número %d aparece %d vezes no número %d.\n", K, cont, N);

    return 0;
}