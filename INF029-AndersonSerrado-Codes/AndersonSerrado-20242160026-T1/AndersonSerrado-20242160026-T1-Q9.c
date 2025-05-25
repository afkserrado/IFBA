#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

// Constantes
#define qtdBarcos 5
#define tam 10

// Variáveis globais
int tamBarcos[] = {4,3,1};
int tiposBarcos = sizeof(tamBarcos) / sizeof(tamBarcos[0]);

// Preenche a matriz com espaços (' ')
void iniciaMatriz (char matriz[tam][tam]) {
    for (int i = 0; i < tam; i++){
        for (int j = 0; j < tam; j++){
            matriz[i][j] = ' ';
        }
    }
}

// Exibe o jogo da velha
void exibeMatriz (char matriz[tam][tam]) {
    char idLin = 'A';
    printf("\n");

    // Identificador das colunas
    printf("    ");  // Desloca espaços da borda esquerda
    for (int j = 0; j < tam; j++) {
        if (j < tam - 1) {printf(" %3d  ", j + 1);} // Desloca espaços entre as colunas
        else {printf(" %3d", j + 1);}
    }
    printf("\n");

    // Primeira linha de separadores horizontais
    printf("    ");
    // Separadores horizontais
    for (int k = 0; k < tam; k++) {
        printf("|-----");
    }
    printf("|\n");

    for (int i = 0; i < tam; i++) {
        // Identificador da linha
        printf("%c   |", idLin);
        idLin += 1;

        printf("  "); // Desloca espaços do 1o separador vertical
        for (int j = 0; j < tam; j++) {
            // Tabuleirov
            if (j < tam) {printf("%c  |  ", matriz[i][j]);}
            else {printf("%c", matriz[i][j]);}
        }
        printf("\n");

        // Separadores horizontais (exceto na última linha)
        if (i < tam - 1) {
            {printf("    |");} // Desloca espaços da borda esquerda
            for (int k = 0; k < tam; k++) {
                printf("-----");
                if (k < tam) printf("|");
            }
            printf("\n");
        }
    }

    // Última linha de separadores horizontais
    printf("    ");
    // Separadores horizontais
    for (int k = 0; k < tam; k++) {
        printf("|-----");
    }
    printf("|\n");
    printf("\n");
}

int validaBarco (int barco, int barcos[], char direcao) {
    
    int sentinela = 0;
    
    // Valida tamanho do barco
    int i;
    for (i = 0; i < tiposBarcos; i++) {
        if (barco == tamBarcos[i]) {sentinela = 1; break;} // Conserva o i
    }

    if (sentinela == 0) {
        printf("\nBarco inválido. Tente novamente...");
        return -1;
    }

    // Valida a quantidade de barcos
    if (barcos[i] == 0) {
        printf("\nO jogador não possui mais barcos de tamanho %d disponíveis. Tente novamente...", barco);
        return -1;
    }
    
    // Valida a direção do barco
    if (barco > 1 && direcao != 'H' && direcao != 'V') {
        printf("\nDireção inválida. Tente novamente...");
        return -1;
    }

    return 0;
}

// Converte de char para int e verifica se os valores são válidos
int validaPosicao (char celula[], int *idLin, int *idCol, int barco, char direcao) {
    
    // Valida o comprimento da célula
    int lenCelula = strlen(celula);
    if (lenCelula < 2 || lenCelula > 3) {
        printf("\nCélula inválida. Tente novamente...");
        return -1;
    }
    
    // Valida a célula
    // Primeiro caractere
    int flag = 0;
    celula[0] = toupper(celula[0]);
    if (celula[0] < 'A' || celula[0] > 'J') {
        printf("\nCélula inválida. A linha deve ser uma letra de A a J. Tente novamente...");
        flag = -1;
    }
    // Terceiro caractere
    if (celula[2] != '\n') {
        if (!(celula[1] == '1' && celula[2] == '0')) {
            printf("\nCélula inválida. A coluna deve ser um número de 1 a 10. Tente novamente...");
            flag = -1;
        }
    }
    // Segundo caractere
    else if (celula[1] < '1' || celula[1] > '9') {
        printf("\nCélula inválida. A coluna deve ser um número de 1 a 10. Tente novamente...");
        flag = -1; 
    }
    if (flag == -1) {return -1;}
    
    // Converte a célula de char para int e retorna um índice na base 0
    *idLin = celula[0] - 'A';
    if (celula[2] == '\n') {*idCol = celula[1] - '1';}
    else {*idCol = 9;}

    if (direcao == 'H') {
        int limiteh = tam - barco;
        if (*idCol > limiteh) {
            printf("\nCélula inválida. O barco extrapola os limites do tabuleiro. Tente novamente...");
            return -1;
        }
    }
    else {
        int limitev = tam - barco;
        if (*idLin > limitev) {
            printf("\nCélula inválida. O barco extrapola os limites do tabuleiro. Tente novamente...");
            return -1;
        }
    }
    
    return 0;
}

int marcaBarco (char matriz[tam][tam], int idLin, int idCol, int barco, int barcos[], char direcao) {
    
    int sentinela = 0;

    // Procura o índice do barco
    int k;
    for (k = 0; k < tiposBarcos; k++) {
        if (barco == tamBarcos[k]) {break;} // Conserva o valor de i
    }

    // Inicializa as variáveis auxiliares
    int dLin = 0, dCol = 0;
    if (direcao == 'H') {dCol = 1;} // Horizontal
    else {dLin = 1;} // Vertical

    // Marca o barco
    int i;
    for (i = 0; i < barco; i++) {
        if (matriz[idLin][idCol] == ' ') {
            matriz[idLin][idCol] = 'N';
            idLin += dLin;
            idCol += dCol;
        }
        else {sentinela = 1; break;} // Conserva i
    }

    // Sobreposição de barcos
    if (sentinela == 1) {
        
        // Retorna os índices para a última posição marcada
        idLin -= dLin;
        idCol -= dCol;
        
        // Desfaz as alterações na matriz
        for (int j = i; j >= 1; j--) {
            matriz[idLin][idCol] = ' ';
            idLin -= dLin;
            idCol -= dCol;
        }
        printf("\nJá existe um barco marcado nessa posição. Tente novamente...");
        return -1;
    }

    // Marca o contorno do barco

    
    // Atualiza a quantidade de barcos do jogador
    barcos[k]--;

    return 0;
}

void posicionarBarcos(char matriz[tam][tam], int jogador, int barcos[]) {
    
    // Declarações
    char celula[4]; // +1 para o \0
    int idLin = 0, idCol = 0;
    
    printf("Jogador %d: sua vez de posicionar seus barcos.\n", jogador);

    for (int i = 0; i < qtdBarcos; i++) {
        int flagBarco = -1;
        int flagCelula = -1;
        int flagMarca = -1;
        int barco = 0;
        char direcao = 0;

        while (flagCelula != 0 || flagBarco != 0 || flagMarca != 0) {  
            exibeMatriz(matriz);

            printf("Informe o tamanho do barco %d (1, 3 ou 4): ", i + 1);
            scanf("%d", &barco);

            if (barco > 1) {
                printf("Informe a direção do barco %d (H ou V): ", i + 1);
                scanf(" %c", &direcao);
            }

            while(getchar() != '\n'); // Limpa o buffer

            direcao = toupper(direcao);
            flagBarco = validaBarco(barco, barcos, direcao);

            // Barco inválido
            if (flagBarco == -1) {
                continue;
                printf("\n");
            }

            printf("Informe a posição do barco %d: ", i + 1);
            fgets(celula, sizeof(celula), stdin);
            flagCelula = validaPosicao(celula, &idLin, &idCol, barco, direcao);

            // Posição e barco válidos
            if (flagCelula == 0) { 
                // Verificar retorno de marcaBarco
                flagMarca = marcaBarco(matriz, idLin, idCol, barco, barcos, direcao); 
                if (flagMarca != 0) {printf("\n");}               
            }
            // Posição inválida
            else {printf("\n");}

        } // Fim do while
    } // Fim do for
}

int main () {

    // Limpa a tela
    #ifdef _WIN32
        system("cls");
    #else
        system("clear");
    #endif

    char m1[tam][tam];
    char m2[tam][tam];
    int barcos1[] = {1,1,3}; // 4, 3 e 1
    int barcos2[] = {1,1,3}; // 4, 3 e 1

    // Tamanho dos barcos
    // Pedir a direção do barco: V ou H
    // Validar posição: contorno

    iniciaMatriz(m1);
    iniciaMatriz(m2);

    posicionarBarcos(m1, 1, barcos1);
    posicionarBarcos(m2, 2, barcos2);
    
} // Fim da main
