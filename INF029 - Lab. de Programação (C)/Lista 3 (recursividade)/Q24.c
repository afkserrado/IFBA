/*
24) Os números de Pell são definidos pela seguinte recursão

p(n) = 
    0, se n = 0
    1, se n = 1
    2p(n-1) + p(n-2), se n >= 2

Alguns números desta sequência são: 0, 1, 2, 5, 12, 29, 70, 169, 408, 985...
Faça uma função recursiva que receba um número N e retorne o N-ésimo número de
Pell.
*/

#include <stdio.h>

unsigned long long pell(int n) {
    if (n == 0) {
        return 0;
    }

    if (n == 1) {
        return 1;
    }

    return 2 * pell(n - 1) + pell(n - 2);
}

int main () {
    int n = 10;
    unsigned long long r = pell(n);
    printf("r = %llu\n", r);

    return 0;
}