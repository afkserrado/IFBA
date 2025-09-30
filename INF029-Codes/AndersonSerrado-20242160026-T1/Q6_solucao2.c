/*
 Q6 = ocorrência de um número em outro
 @objetivo
    Verificar quantidade de vezes da ocorrência de um número em outro
 @entrada
    Um número base (numerobase) e um número de busca (numerobusca).
 @saida
    Quantidade de vezes que número de busca ocorre em número base
 */

 /*
 gcc Q6_solucao2.c -o Q6_solucao2 && ./Q6_solucao2
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Conta as ocorrências do número
int contaOcorrencias(char *numerobase, char *numerobusca) {
    
    // Comprimentos
    int baLen = strlen(numerobase);
    int buLen = strlen(numerobusca);

    int ocorrencias = 0; // Controla as ocorrências
    char *resultado; // Posição onde o número buscado foi encontrado

    // Percorre o numerobase
    do {
        // Aponta para o ínicio da numeroBusca no tnumerobase ou retorna NULL
        resultado = strstr(numerobase, numerobusca);
        
        if (resultado != NULL) {
            ocorrencias++;
            // Move o ponteiro texto para o novo índice
            numerobase = resultado + 1;
            baLen = strlen(numerobase);
        }

    } while (resultado != NULL && baLen >= buLen);

    return ocorrencias;
}

int q6(int numerobase, int numerobusca) {
    
    #define tam 10
    int qtdOcorrencias = 0;
    char cVetor_base[tam];
    char cVetor_busca[tam];

    sprintf(cVetor_base, "%d", numerobase);
    sprintf(cVetor_busca, "%d", numerobusca);
    
    // Cria ponteiros para "andar" dentro do vetor
    char *pnumerobase = cVetor_base;
    char *pnumerobusca = cVetor_busca;

    qtdOcorrencias = contaOcorrencias(pnumerobase, pnumerobusca);

    return qtdOcorrencias;
}

int main () {

    printf("%d\n", q6(34567368, 3));
    printf("%d\n", q6(34567368, 4576));
    printf("%d\n", q6(3539343, 3));
    printf("%d\n", q6(3539343, 39));
}