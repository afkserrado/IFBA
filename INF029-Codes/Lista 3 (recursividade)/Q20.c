/*
20) Um fatorial exponencial é um inteiro positivo N elevado à potência de N-1, que por
sua vez é elevado à potência de N-2 e assim em diante. Ou seja,
n^(n-1)^(n-2)...

Faça uma função recursiva que receba um número inteiro positivo N e retorne o fatorial exponencial desse número.
*/

#include <stdio.h>

// Calcula a potência
long long int pot(int base, long long int expoente) {
    if (expoente <= 0) {
        return 1;
    }

    return base * pot(base, expoente - 1);
}

// Calcula o fatorial exponencial
long long int fexp(int n) {
    if (n <= 1) {
        return 1;
    }

    return pot(n, fexp(n - 1));
}

int main() {
    int n = 4;
    long long int r = fexp(n);
    printf("r = %lld\n", r);
    return 0;
}