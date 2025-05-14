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
#include <stdlib.h>
#include <string.h>

// Guarda os algarismos de um número inteiro em um vetor
int separaNumero (int num, int iVetor[]) {
    int i;
    for (i = 0; num != 0; i++) {
        iVetor[i] = num % 10;
        num = num / 10;
    }
    return i;
}

// Inverte os algarismos separados de um número inteiro em um vetor
void inverteNumero (int iVetor[], int i) {
    int temp;
    for (int j = 0; j < i/2; j++) {
        temp = iVetor[j];
        iVetor[j] = iVetor[i - j - 1];
        iVetor[i - j - 1] = temp;
    }
    return;
}

// Converte os algarismos inteiros de um vetor em caracteres
void itoaArray (char cVetor[], int iVetor[], int i) {
    int j;
    for (j = 0; j < i; j++) {
        cVetor[j] = iVetor[j] + '0';
    }
    cVetor[j] = '\0';
    return;
}

// Exibe um vetor int
void exibeiVetor (int iVetor[], int i) {
    for (int j = 0; j < i; j++) {
        printf("| %d |", iVetor[j]);
    }
    printf("\n");
    return;
}

// Exibe um vetor char
void exibecVetor (char cVetor[], int i) {
    for (int j = 0; j < i; j++) {
        printf("| %c |", cVetor[j]);
    }
    printf("\n");
    return;
}

// Conta as ocorrências do número
int contaOcorrencias(char *numerobase, char *numerobusca) {
    
    // Comprimentos
    int baLen = strlen(numerobase);
    int buLen = strlen(numerobusca);

    // Percorre o numerobase
    int ocorrencias = 0;
    char *resultado;
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
    int iVetor_base[tam] = {0};
    int iVetor_busca[tam] = {0};
    char cVetor_base[tam];
    char cVetor_busca[tam];

    // Separa o número e guarda em um vetor
    int tamBase = separaNumero(numerobase, iVetor_base);
    int tamBusca = separaNumero(numerobusca, iVetor_busca);
    
    /*Debug
    printf("base: %d | busca: %d\n", numerobase, numerobusca);
    exibeiVetor(iVetor_base, tamBase);
    exibeiVetor(iVetor_busca, tamBusca);
    */

    // Coloca na ordem original os algarimos do número separado
    inverteNumero(iVetor_base, tamBase);
    inverteNumero(iVetor_busca, tamBusca);

    /*Debug
    exibeiVetor(iVetor_base, tamBase);
    exibeiVetor(iVetor_busca, tamBusca);
    */

    // Converte de int para char
    itoaArray(cVetor_base, iVetor_base, tamBase);
    itoaArray(cVetor_busca, iVetor_busca, tamBusca);

    /*Debug
    exibecVetor(cVetor_base, tamBase);
    exibecVetor(cVetor_busca, tamBusca);
    */
    
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