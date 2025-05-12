#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

/*
 Q4 = encontrar palavra em texto
 @objetivo
    Pesquisar todas as ocorrências de uma palavra em um texto
 @entrada
    uma string texto base (strTexto), uma string strBusca e um vetor de inteiros (posicoes) que irá guardar as posições de início e fim de cada ocorrência da palavra (strBusca) no texto base (texto).
 @saida
    Um número n >= 0 correspondente a quantidade de ocorrências encontradas.
    O vetor posicoes deve ser preenchido com cada entrada e saída correspondente. Por exemplo, se tiver uma única ocorrência, a posição 0 do vetor deve ser preenchido com o índice de início do texto, e na posição 1, deve ser preenchido com o índice de fim da ocorrencias. Se tiver duas ocorrências, a segunda ocorrência será amazenado nas posições 2 e 3, e assim consecutivamente. Suponha a string "Instituto Federal da Bahia", e palavra de busca "dera". Como há uma ocorrência da palavra de busca no texto, deve-se armazenar no vetor, da seguinte forma:
        posicoes[0] = 13;
        posicoes[1] = 16;
        Observe que o índice da posição no texto deve começar ser contado a partir de 1.
        O retorno da função, n, nesse caso seria 1;
 */

int q4(char *strTexto, char *strBusca, int posicoes[30]) {

    // Aloca memória
    char *texto = (char *)malloc(strlen(strTexto) + 1);  // +1 para o '\0' 
    char *palavra = (char *)malloc(strlen(strBusca) + 1);

    if (texto == NULL || palavra == NULL) {
        // Verificação de falha de alocação
        printf("Erro ao alocar memória..\n");
        return -1;
    }

    // Cópias
    strcpy(texto, strTexto);
    strcpy(palavra, strBusca);

    // Salva o endereço original do bloco de memória alocado para texto, permitindo a sua liberação posteriormente
    char *textoOriginal = texto;
    
    // Comprimentos
    int tLen = strlen(texto);
    int pLen = strlen(palavra);

    // Percorre o texto
    int i = 0;
    int ocorrencias = 0, index = 0;
    char *resultado;
    do {
        // Aponta para o ínicio da palavra no texto ou retorna NULL
        resultado = strstr(texto, palavra);
        
        if (resultado != NULL) {
            ocorrencias++;
            index = resultado - textoOriginal; // Índice baseado em 0
            printf("index: %d\n", index);

            posicoes[i++] = index + 1; // Início da substring (baseado 1)
            posicoes[i++] = index + pLen; // Final da substring (baseado em 1)
            
            // Move o ponteiro texto para o novo índice
            texto = resultado + 1;
            tLen = strlen(texto);   

            // Obs.:
            // posicoes[i++] usa "i" e depois incrementa;
            // index + 1 porque desejamos o índice baseado em 1;
            // index + plen porque desejamos o índice baseado em 1. Se quiséssemos o índice baseado em 0, teríamos que subtrair 1.
        }

    } while (resultado != NULL && tLen >= pLen);
    
    // Libera a memória alocada
    free(textoOriginal);
    free(palavra);

    return ocorrencias;
}

int main () {

    char strTexto[250];
    char strBusca[50];
    int posicoes[30];
    int i;

    /*// Inicializando o vetor posições
    for (i = 0; i < 30; i++) {
        posicoes[i] = -1;
    }

    strcpy(strTexto, "Laboratorio de ratos");
    strcpy(strBusca, "rato");
    printf("texto: %s\n", strTexto);
    printf("palavra: %s\n", strBusca);
    printf("Ocorrencias: %d\n", q4(strTexto, strBusca, posicoes));
    printf("Ocorrencia 1, i: %d\n", posicoes[0]);
    printf("Ocorrencia 1, f: %d\n", posicoes[1]);
    printf("Ocorrencia 2, i: %d\n", posicoes[2]);
    printf("Ocorrencia 2, f: %d\n", posicoes[3]);*/
    
    /*// Inicializando o vetor posições
    for (i = 0; i < 30; i++) {
        posicoes[i] = -1;
    }

    strcpy(strTexto, "Laboratorio de programacao: para ratos de programação");
    strcpy(strBusca, "rato");
    printf("texto: %s\n", strTexto);
    printf("palavra: %s\n", strBusca);
    printf("Ocorrencias: %d\n", q4(strTexto, strBusca, posicoes));
    printf("Ocorrencia 1, i: %d\n", posicoes[0]);
    printf("Ocorrencia 1, f: %d\n", posicoes[1]);
    printf("Ocorrencia 2, i: %d\n", posicoes[2]);
    printf("Ocorrencia 2, f: %d\n", posicoes[3]);*/

    // Inicializando o vetor posições
    for (i = 0; i < 30; i++) {
        posicoes[i] = -1;
    }

    strcpy(strTexto, "Olá, o mundo é muito grande. Tem muitas pessoas, e muitos problemas");
    strcpy(strBusca, "mui");
    printf("texto: %s\n", strTexto);
    printf("palavra: %s\n", strBusca);
    printf("Ocorrencias: %d\n", q4(strTexto, strBusca, posicoes));
    printf("%d\n", posicoes[0]);
    printf("%d\n", posicoes[1]);
    printf("%d\n", posicoes[2]);
    printf("%d\n", posicoes[3]);
    printf("%d\n", posicoes[4]);
    printf("%d\n", posicoes[5]);
}