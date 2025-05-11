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

void converteVogais (char *caractere, const char vogais[5][5]) {
    
    int achou = 0;
    for (int i = 0; i < 5; i++) { // Percorre as linhas
        for (int j = 1; j < 5; j++) { // Percorre as colunas
            if (*caractere == vogais[i][j]) {
                *caractere = vogais[i][0];
                achou = 1;
                break;
            }
        }
        if (achou) {break;} // achou = 1: sai da matriz
    }

    return;
}

 int q3(char *texto, char c, int isCaseSensitive)
{
    // Declarações
    int qtdOcorrencias = 0;
    char textoCopia[250];
    char vogais[5][5] = {
        {'a', 'á', 'à', 'ã', 'â'},
        {'e', 'é', 'è', 'ẽ', 'ê'},
        {'i', 'í', 'ì', 'ĩ', 'î'},
        {'o', 'ó', 'ò', 'õ', 'ô'},
        {'u', 'ú', 'ù', 'ũ', 'û'}
    };
    
    // Copia o texto para evitar alterar a variável original
    char cCopia = c;
    strcpy(textoCopia, texto);
    int len = strlen(textoCopia); // Comprimento da string

    // Converte "cCopia" para vogal sem acento
    converteVogais(&cCopia, vogais);

    // Case-insensitive: converte "cCopia" para minúscula
    if (isCaseSensitive == 0) {cCopia = tolower(cCopia);} 

    // Percorre o texto
    for (int k = 0; k < len; k++) {
        // Converte "textoCopia" para vogais sem acento
        converteVogais(&textoCopia[k], vogais);

        // Case-insensitive: converte "textoCopia" para minúscula
        if (isCaseSensitive == 0) {
            textoCopia[k] = tolower(textoCopia[k]);
        }

        // Conta as ocorrências
        if (textoCopia[k] == cCopia) {
            qtdOcorrencias++;
        }
    }
    printf("cCopia: %c\n", cCopia);
    printf("textoCopia: %s\n", textoCopia);

    return qtdOcorrencias;
}

int main () {

    char str[250];
    strcpy(str, "Renáto Limá Nõvais");
    printf("Nome: %s\n", str);
    printf("á = %d | 0\n", q3(str, 'á', 0));
    printf("a = %d | 0\n", q3(str, 'a', 0));
    printf("l = %d | 1\n", q3(str, 'l', 1));
    printf("l = %d | 0\n", q3(str, 'l', 0));
    printf("L = %d | 0\n", q3(str, 'L', 0));
    printf("O = %d | 0\n", q3(str, 'O', 0));
}