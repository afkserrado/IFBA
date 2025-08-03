/*
19) O hiperfatorial de um número N, escrito H(n), é definido por ...
Faça uma função recursiva que receba um número inteiro positivo N e retorne o hiperfatorial desse número.
*/

#include <stdio.h>

// Calcula a potência
long long int pot(int n, int m) {
    if (m <= 0) {
        return 1;
    }

    return (long long int) n * pot(n, m - 1);
}

// Calcula o hiperfatorial
long long int hf(int n) {
    if (n <= 1) {
        return 1;
    }

    return pot(n, n) * hf(n - 1);
}

int main() {
    int n = 7;
    long long int r = hf(n);
    printf("r = %lld\n", r);
    return 0;
}