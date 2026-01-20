/*
21) Os números tribonacci são definidos pela seguinte recursão 

f(n) = 
  0, se n = 0
  0, se n = 1
  1, se n = 2
  f(n - 1) + f(n - 2) + f(n - 3), se n > 2

Faça uma função recursiva que receba um número N e retorne o N-ésimo termo da sequência de tribonacci.
*/

#include <stdio.h>

int tribonacci(int n) {
    if (n <= 1) {
        return 0;
    }

    if (n == 2) {
        return 1;
    }
    
    return tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3);
}

int main () {
    int n = 6;
    int r = tribonacci(n);
    printf("r = %d\n", r);

    return 0;
}