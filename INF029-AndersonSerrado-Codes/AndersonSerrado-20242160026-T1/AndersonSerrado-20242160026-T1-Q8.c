#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define lin 3
#define col 3

void iniciaMatriz (char matriz[lin][col]) {
    for (int i = 0; i < lin; i++){
        for (int j = 0; j < col; j++){
            matriz[i][j] = ' ';
        }
    }
}

void exibeMatriz (char matriz[lin][col]) {
    
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

// Converte de char para int e verifica se os valores são válidos
int testes (char celula[], int *idLin, int *idCol, char valor) {

    // Converte de char para int
    *idLin = celula[0] - 'A' + 1;
    *idCol = celula[1] - '0';

    // Valida a célula
    if (*idLin < 1 || *idLin > 3 || *idCol < 1 || *idCol > 3) {
        printf("\nCélula inválida. Tente novamente...");
        return -1;
    }

    // Valida o valor
    if (valor != 'X' && valor != 'O') {
        printf("\nValor inválido. Tente novamente...");
        return -1;
    }

    return 1;
}

int main () {

    char matriz[lin][col];
    char celula[3]; // +1 para o \0
    char valor;
    int idLin, idCol;

    // Preenche a matriz com espaços em branco
    iniciaMatriz(matriz);

    printf("\nPara jogar, utilize apenas letras capitalizadas:");
    printf("\nA, B ou C para identificar as linhas.");
    printf("\nX ou O para identificar atribuir um valor.\n");

    int jogador = 1;
    while (1) {
        // Entrada de dados: célula
        printf("\nVez do jogador %d", jogador);
        printf("\nInforme a célula: ");
        fgets(celula, sizeof(celula), stdin);
        celula[strcspn(celula, "\n")] = '\0';

        // Entrada de dados: valor
        printf("\nInforme o valor (X ou O): ");
        scanf(" %c", &valor);
        
        // Preenche a matriz
        if (testes(celula, &idLin, &idCol, valor)) { // return = 1
            matriz[idLin][idCol] = valor;
            exibeMatriz(matriz);

            // Muda o jogador
            if (jogador == 1) {jogador++;}
            else {jogador--;}
        }
        else {
            printf("\n");
        }

    }
    
    
}
