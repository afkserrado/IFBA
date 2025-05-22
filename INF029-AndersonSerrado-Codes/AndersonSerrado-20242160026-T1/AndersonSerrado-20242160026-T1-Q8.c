#include <stdio.h>
#include <stdlib.h>

void iniciaMatriz (int lin, int col, char matriz[lin][col]) {
    for (int i = 0; i < lin; i++){
        for (int j = 0; j < col; j++){
            matriz[i][j] = ' ';
        }
    }
}

void exibeMatriz (int lin, int col, char matriz[lin][col]) {
    
    char idLin = 'A';

    printf("\n");

    // Identificador das colunas
    printf("   ");  // Espaço para alinhar com os identificadores das linhas
    for (int j = 0; j < col; j++) {
        printf("  %d", j + 1);
        if (j < col - 1) {printf(" ");}
    }
    printf("\n");

    for (int i = 0; i < lin; i++) {
        // Identificador da linha
        printf(" %c  ", idLin);
        idLin += 1;

        // Elementos da matriz com separadores verticais
        for (int j = 0; j < col; j++) {
            printf(" %c ", matriz[i][j]);
            if (j < col - 1) printf("|");
        }
        printf("\n");

        // Separadores horizontais (exceto na última linha)
        if (i < lin - 1) {
            printf("    ");  // Alinhar com identificador da linha
            for (int k = 0; k < col; k++) {
                printf("---");
                if (k < col - 1) printf("|");
            }
            printf("\n");
        }
    }
    printf("\n");
}

int main () {

    int lin = 3;
    int col = 3;
    char matriz[lin][col];
    char celula[3]; // +1 para o \0

    iniciaMatriz(lin, col, matriz);

    while (1) {
        printf("\nInforme a célula: ");
        fgets(celula, sizeof(celula), stdin);
        celula[strcspn(celula, "\n")] = '\0';
    }

    exibeMatriz(lin, col, matriz);
}
