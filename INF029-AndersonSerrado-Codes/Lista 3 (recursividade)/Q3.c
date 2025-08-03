/*
3) Faça uma função recursiva que permita inverter um número inteiro N. Ex: 123 - 321
*/

#include <stdio.h>

void inverte (int n, int *inv);

int main () {
    int n = 123456789;
    int inv = 0;
    inverte(n, &inv);
    printf("Resultado = %d\n", inv);

    return 0;
}

void inverte (int n, int *inv) {
    if (n == 0) {
        return;
    }

    int temp = n % 10; // Obtém o último dígito
    n /= 10; // Remove o último dígito
    *inv = *inv * 10 + temp;

    inverte(n, inv);
}
