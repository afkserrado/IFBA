#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

/*
 Q3 = encontrar caracter em texto
 @objetivo
    Pesquisar quantas vezes um determinado caracter ocorre em um texto
 @entrada
    uma string texto, um caracter c e um inteiro que informa se é uma pesquisa Case Sensitive ou não. Se isCaseSensitive = 1, a pesquisa deve considerar diferenças entre maiúsculos e minúsculos.
        Se isCaseSensitive != 1, a pesquisa não deve  considerar diferenças entre maiúsculos e minúsculos.
 @saida
    Um número n >= 0.
 */

int q3(char *texto, char c, int isCaseSensitive) {
     
    // Declarações
     int qtdOcorrencias = 0;
     char textoCopia[250];
     
     // Copia o texto para evitar alterar a variável original
     char caractereCopia = c;
     strcpy(textoCopia, texto);
     int len = strlen(textoCopia); // Comprimento da string
 
     // Case-insensitive: converte "caractereCopia" para minúscula
     if (isCaseSensitive == 0) {caractereCopia = tolower(caractereCopia);} 
 
     // Percorre o texto
     for (int k = 0; k < len; k++) {

         // Case-insensitive: converte "textoCopia" para minúscula
         if (isCaseSensitive == 0) {
             textoCopia[k] = tolower(textoCopia[k]);
         }
 
         // Conta as ocorrências
         if (textoCopia[k] == caractereCopia) {
             qtdOcorrencias++;
         }
     }
     
     printf("caractereCopia: %c\n", c);
     printf("textoCopia: %s\n", texto);
     printf("isCaseSensitive: %d\n", isCaseSensitive);
 
     return qtdOcorrencias;
}

int main () {

    char str[250];
    strcpy(str, "Renato Lima Novais");
    printf("%d\n\n", q3(str, 'a', 0) == 3);
    printf("%d\n\n", q3(str, 'b', 0) == 0);
    printf("%d\n\n", q3(str, 'l', 1) == 0);
    printf("%d\n\n", q3(str, 'l', 0) == 1);
    printf("%d\n\n", q3(str, 'L', 0) == 1);
}