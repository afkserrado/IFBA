/*
16) A função fatorial duplo é definida como o produto de todos os números naturais
ímpares de 1 até algum número natural ímpar N.
Assim, o fatorial duplo de 5 é
5!! = 1 * 3 * 5 = 15
Faça uma função recursiva que receba um número inteiro positivo impar N e retorne o
fatorial duplo desse número.
*/

#include <stdio.h>

int fatDup(int n) {
    // n deve ser ímpar
    if (n <= 0 || cn % 2 == 0) {
        return -1;
    }
    
    if (n == 1) {
        return 1;
    }

    return n * fatDup(n - 2);
}


int main() {
    int r = fatDup(5);
    printf("r = %d\n", r);
    return 0;
}