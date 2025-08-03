/*
26) Uma palavra de Fibonacci é definida por

f(n) = 
    b, se n = 0
    a, se n = 1
    f(n - 1) + f(n - 2), se n > 1

Aqui + denota a concatenação de duas strings. Esta sequência inicia com as seguintes
palavras:
b, a, ab, aba, abaab, abaababa, abaababaabaab, ...
Faça uma função recursiva que receba um número N e retorne a N-ésima palavra de
Fibonacci.
*/

#include <stdio.h>
#include <string.h>
#define tam 10000

void pFib(int n, char palavra[]) {
    // Casos base
    if (n == 0) {
        strcpy(palavra, "b");
        return;
    }

    if (n == 1) {
        strcpy(palavra, "a");
        return;
    }

    // Chamada recursiva
    char f1[tam], f2[tam];
    pFib(n - 1, f1);
    pFib(n - 2, f2);

    // pFib(n - 1) + pFib(n - 2)
    strcpy(palavra, f1);
    strcat(palavra, f2);
}

int main () {
    int n = 4;
    char palavra[tam] = "b";
    pFib(n, palavra);
    printf("palavra: %s\n", palavra);

    return 0;
}