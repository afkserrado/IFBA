#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define lin 3
#define col 3
char matriz[lin][col];

void iniciaMatriz () {
    for (int i = 0; i < lin; i++){
        for (int j = 0; j < col; j++){
            matriz[i][j] = ' ';
        }
    }
}

void exibeMatriz () {
    
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
}

// Converte de char para int e verifica se os valores são válidos
int testes (char celula[], int *idLin, int *idCol, char valor) {

    int flag = 0;

    // Valida o comprimento da célula
    if (strlen(celula) != 2) {
        printf("\nA célula deve ter exatamente 2 caracteres. Tente novamente...");
        return -1;
    }
    
    // Valida a célula
    if (celula[0] < 'A' || celula[0] > 'C') {
        printf("\nCélula inválida. A linha deve ser A, B ou C. Tente novamente...");
        flag = 1;
    }
    if (celula[1] < '1' || celula[1] > '3') {
        printf("\nCélula inválida. A coluna deve ser 1, 2 ou 3. Tente novamente...");
        flag = 1; 
    }
    if (flag == 1) {return -1;}

    // Valida o valor
    if (valor != 'X' && valor != 'O') {
        printf("\nValor inválido. O valor deve ser uma letra (X ou O). Tente novamente...");
        return -1;
    }
    
    // Converte a célula de char para int e retorna um índice na base 0
    *idLin = celula[0] - 'A';
    *idCol = celula[1] - '1';

    // Verifica se a célula está vazia
    if (matriz[*idLin][*idCol] != ' ') {
        printf("\nA célula já está preenchida. Tente novamente...");
        return -1;
    }

    return 1;
}

int main () {

    // Limpa o terminal
    system("clear");

    // Declarações
    char celula[3]; // +1 para o \0
    char valor;
    int idLin, idCol;

    // Preenche a matriz com espaços em branco
    iniciaMatriz();

    printf("Para jogar, utilize apenas letras capitalizadas:");
    printf("\nA, B ou C para definir as linhas.");
    printf("\nX ou O para definir um valor.\n");

    int jogador = 1;
    while (1) {
        
        exibeMatriz();
        
        // Entrada de dados: célula
        printf("\nVez do jogador %d", jogador);
        printf("\nInforme a célula (ex.: A1): ");
        fgets(celula, sizeof(celula), stdin);
        celula[strcspn(celula, "\n")] = '\0';
        
        // Limpa o buffer
        while (getchar() != '\n');

        // Entrada de dados: valor
        printf("Informe o valor (X ou O): ");
        valor = getchar();

        // Limpa o buffer
        while (getchar() != '\n');
        
        // Preenche a matriz
        if (testes(celula, &idLin, &idCol, valor) == 1) { // return = 1
            matriz[idLin][idCol] = valor;

            // Muda o jogador
            if (jogador == 1) {jogador++;}
            else {jogador--;}
        }
        else {
            printf("\n");
        }

    }
}
