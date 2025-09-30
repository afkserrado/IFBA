/*
2. Suponha uma função A que recebe um inteiro. Ela decrementa o inteiro e chama a função B. A função B faz o mesmo processo, decrementa o número e chama a função C. A função C também repete o processo, decrementa o numero e chama a função A. Esse processo deve se repetir enquanto o número x for maior que zero. Implemente as três funções.
*/

#include <stdio.h>

void fa(int *n);
void fb(int *n);
void fc(int *n);

int main () {
    int n = 5;

    fa(&n);
}

void fa (int *n) {
    // Caso base
    if (*n <= 0) {
        return;
    }

    printf("fa atual: %d\n", *n);
    *n -= 1; // Decrementa
    printf("fa novo: %d\n", *n);

    // Chamada recursiva indireta
    fb(n);
}

void fb (int *n) {  
    // Caso base
    if (*n <= 0) {
        return;
    }
    
    printf("fb atual: %d\n", *n);
    *n -= 1; // Decrementa
    printf("fb novo: %d\n", *n);

    // Chamada recursiva indireta
    fc(n);
}

void fc (int *n) {   
    // Caso base
    if (*n <= 0) {
        return;
    }
    
    printf("fc atual: %d\n", *n);
    *n -= 1; // Decrementa
    printf("fc novo: %d\n", *n);

    // Chamada recursiva indireta
    fa(n);
}