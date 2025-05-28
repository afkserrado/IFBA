#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <locale.h> // Contém o setlocale
#include <wchar.h> // Contém funções para trabalhar com caracteres multibytes

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

    // Comprimento das strings multibytes
    int slenTexto = strlen(strTexto);
    int slenBusca = strlen(strBusca);

    // Aloca memória para as strings
    char *texto = (char *)malloc((slenTexto + 1) * sizeof(char));  // +1 para o '\0' 

    // Salva o endereço original do bloco de memória alocado para texto, permitindo a sua liberação posteriormente
    char *textoOriginal = texto;
    
    // Comprimentos das strings
    strcpy(texto, strTexto);
    int lenTexto = strlen(texto);

    // Percorre o texto
    int i = 0;
    int ocorrencias = 0, index = 0;
    char *resultado;
    do {
        // Aponta para o ínicio da palavra no texto ou retorna NULL
        resultado = strstr(texto, strBusca);
        
        if (resultado != NULL) {
            ocorrencias++;

            // Operação aritmética de endereços
            index = resultado - textoOriginal; // Retorna um índice baseado em 0

            posicoes[i++] = index + 1; // Início da substring (baseado em 1)
            posicoes[i++] = index + slenBusca; // Final da substring (baseado em 1)
            
            // Move o ponteiro texto para o novo índice
            texto = resultado + 1; 
            lenTexto = strlen(texto);   

            // Obs.:
            // posicoes[i++] usa "i" e depois incrementa;
            // index + 1 porque desejamos o índice baseado em 1;
            // index + wlenPalavra porque desejamos o índice baseado em 1. Se quiséssemos o índice baseado em 0, teríamos que subtrair 1.
            // texto = resultado + 1 permite encontrar ocorrências sobrepostas. Por exemplo, "ara" ocorre 2x em "arara"
        }

    } while (resultado != NULL && lenTexto >= slenBusca);
    
    // Libera a memória alocada
    free(textoOriginal);

    return ocorrencias;
}

int main () {

    char strTexto[250];
    char strBusca[50];
    int posicoes[30];
    int i;
    
    // Inicializando o vetor posições
    for (i = 0; i < 30; i++) {
        posicoes[i] = -1;
    }

    strcpy(strTexto, "araras são azuis");
    strcpy(strBusca, "ara");
    printf("Texto: %s | Busca: %s\n", strTexto, strBusca);
    printf("%d\n", q4(strTexto, strBusca, posicoes) == 2);
    printf("%d\n", posicoes[0] == 1);
    printf("%d\n", posicoes[1] == 3);
    printf("%d\n", posicoes[2] == 3);
    printf("%d\n", posicoes[3] == 5);
    printf("\n");

    // Inicializando o vetor posições
    for (i = 0; i < 30; i++) {
        posicoes[i] = -1;
    }

    strcpy(strTexto, "Laboratorio de programacao: para ratos de programação");
    strcpy(strBusca, "rato");
    printf("Texto: %s | Busca: %s\n", strTexto, strBusca);
    printf("%d\n", q4(strTexto, strBusca, posicoes) == 2);
    printf("%d\n", posicoes[0] == 5);
    printf("%d\n", posicoes[1] == 8);
    printf("%d\n", posicoes[2] == 34);
    printf("%d\n", posicoes[3] == 37);
    printf("\n");

    // Inicializando o vetor posições
    for (i = 0; i < 30; i++) {
        posicoes[i] = -1;
    }

    strcpy(strTexto, "Olá, o mundo é muito grande. Tem muitas pessoas, e muitos problemas");
    strcpy(strBusca, "mui");
    printf("Texto: %s | Busca: %s\n", strTexto, strBusca);
    printf("%d\n", q4(strTexto, strBusca, posicoes) == 3);
    printf("%d\n", posicoes[0] == 16);
    printf("%d\n", posicoes[1] == 18);
    printf("%d\n", posicoes[2] == 34);
    printf("%d\n", posicoes[3] == 36);
    printf("%d\n", posicoes[4] == 52);
    printf("%d\n", posicoes[5] == 54);
}