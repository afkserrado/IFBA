/*
17) O fatorial quádruplo de um número N é dado por (2n)!/n!. Faça uma função recursiva que receba um número inteiro positivo N e retorne o fatorial quádruplo desse número.
*/

#include <stdio.h>

int fatQuad(int n) {
    // n deve ser positivo
    if (n <= 0) {
        return 1;
    }
    
    // Caso base
    if (n == 0) {
        return 1;
    }

    // Chamada recursiva
    return fatQuad(n - 1) * 2 * (2 * n - 1);
}

int main() {
    int n = 4;
    int r = fatQuad(n);
    printf("r = %d\n", r);
    return 0;
}