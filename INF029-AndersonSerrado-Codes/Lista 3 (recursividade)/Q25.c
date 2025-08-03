/*
25) Os números de Catalan são definidos pela seguinte recursão

C(n) = 
    1, se n = 0
    (2(2n - 1) * C * (n - 1)) / (n + 1), se n > 0

Alguns números desta sequência são: 1, 1, 2, 5, 14, 42, 132, 429, 1430, 4862, 16796, 58786...
Faça uma função recursiva que receba um número N e retorne o N-ésimo número de Catalan.
*/

#include <stdio.h>

unsigned long long catalan(int n) {
    if (n == 0) {
        return 1;
    }

    return (2 * (2 * n - 1) * catalan(n - 1)) / (n + 1);
}

int main () {
    int n = 3;
    unsigned long long r = catalan(n);
    printf("r = %llu\n", r);

    return 0;
}