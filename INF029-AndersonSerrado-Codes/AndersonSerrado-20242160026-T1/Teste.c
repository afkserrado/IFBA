/*
 Q6 = ocorrência de um número em outro
 @objetivo
    Verificar quantidade de vezes da ocorrência de um número em outro
 @entrada
    Um número base (numerobase) e um número de busca (numerobusca).
 @saida
    Quantidade de vezes que número de busca ocorre em número base
 */

#include <stdio.h>

int separaNumero(int num, int vetor[]) {
    int i;
    for (i = 0; num != 0; i++) {
        vetor[i] = num % 10;
        num = num / 10;
    }
    return i;
}

int q6(int numerobase, int numerobusca) {
    
    #define tam 10
    int qtdOcorrencias = 0;
    int vetor[tam] = {0};

    int i = separaNumero(numerobase, vetor);
    

    // Conta as ocorrências
    for (int j = 0; j < i; j++) {
        if (numerobusca == vetor[j]) {
            qtdOcorrencias++;
        }
    }

    return qtdOcorrencias;
}

int main () {

    printf("%d\n", q6(34567368, 3));
    printf("%d\n", q6(34567368, 4576));
    printf("%d\n", q6(3539343, 3));
    printf("%d\n", q6(3539343, 39));
}