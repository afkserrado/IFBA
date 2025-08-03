/*
 18) O superfatorial de um número N é definida pelo produto dos N primeiros fatoriais
de N. Assim, o superfatorial de 4 é sf(4) = 1! * 2! * 3! * 4! = 288
Faça uma função recursiva que receba um número inteiro positivo N e retorne o superfatorial desse número.
*/

#include <stdio.h>

// Calcula o fatorial
int fat(int n) {
    if (n <= 1) {
        return 1;
    }

    return n * fat(n - 1);
}

// Calcula o superfatorial
int sf(int n) {
    if (n <= 1) {
        return 1;
    }

    return fat(n) * sf(n - 1);
}

int main() {
    int n = 4;
    int r = sf(n);
    printf("r = %d\n", r);
    return 0;
}