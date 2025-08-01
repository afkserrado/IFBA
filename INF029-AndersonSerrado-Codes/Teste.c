/*
3. (Valor 3.0) Considere uma função recursiva que recebe um arquivo como parâmetro. Esse arquivo contém um número por linha. A cada iteração da função ela deve ler uma linha. Implemente a função.
*/

#include <stdio.h>
#define tamLin 10
#define tamArq 10

typedef struct dado {
    char linha[tamLin];
} dado;

void lerArquivo (FILE *entrada, dado linhas[], int i) {
    // Caso base: fim do arquivo
    if (fgets(linhas[i].linha, tamLin, entrada) == NULL) {
        return;
    }

    // Imprime a linha lida
    printf("Dado: %s", linhas[i].linha);

    // Chamada recursiva
    lerArquivo(entrada, linhas, i + 1);
}

int main () {
    FILE *entrada;
    dado linhas[tamArq];

    // Abre e testa o arquivo
    if ((entrada = fopen("P3-2023.1-Q3-Resposta-Dados.txt", "r")) == NULL) {
        printf("Erro ao abrir arquivo.\n");
        return 1;
    }

    lerArquivo(entrada, linhas, 0); // Lê o arquivo
    fclose(entrada); // Fecha o arquivo

    return 0;
}
