#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define tam 3
char matriz[tam][tam];

// Preenche a matriz com espaços (' ')
void iniciaMatriz () {
    for (int i = 0; i < tam; i++){
        for (int j = 0; j < tam; j++){
            matriz[i][j] = ' ';
        }
    }
}

// Exibe o jogo da velha
void exibeMatriz () {
    
    char idLin = 'A';

    printf("\n");

    // Identificador das colunas
    printf("   ");  // Espaço para alinhar com os identificadores das linhas
    for (int j = 0; j < tam; j++) {
        printf("  %d", j + 1);
        if (j < tam - 1) {printf(" ");}
    }
    printf("\n");

    for (int i = 0; i < tam; i++) {
        // Identificador da linha
        printf(" %c  ", idLin);
        idLin += 1;

        // Elementos da matriz com separadores verticais
        for (int j = 0; j < tam; j++) {
            printf(" %c ", matriz[i][j]);
            if (j < tam - 1) printf("|");
        }
        printf("\n");

        // Separadores horizontais (exceto na última linha)
        if (i < tam - 1) {
            printf("    ");  // Alinhar com identificador da linha
            for (int k = 0; k < tam; k++) {
                printf("---");
                if (k < tam - 1) printf("|");
            }
            printf("\n");
        }
    }
}

// Busca uma trinca na direção horizontal
int proch() {

    int flag;

    for (int i = 0; i < tam; i++) {
        flag = 1; // Inicialização
    
        if (matriz[i][0] == ' ') {continue;}
        else {
            int temp = matriz[i][0];
            for (int j = 1; j < tam; j++) {
                if (temp == matriz[i][j]) {
                    flag++;
                }
                else {break;} // Sai do loop j
            }
        }
        // Vitória
        if (flag == 3) {return flag;} // Encerra a função antecipadamente
    }
    return -1;
}

// Busca uma trinca na vertical
int procv() {

    int flag;

    for (int j = 0; j < tam; j++) {
        flag = 1; // Inicialização
    
        if (matriz[0][j] == ' ') {continue;}
        else {
            int temp = matriz[0][j];
            for (int i = 1; i < tam; i++) {
                if (temp == matriz[i][j]) {
                    flag++;
                }
                else {break;} // Sai do loop j
            }
        }
        // Vitória
        if (flag == 3) {return flag;} // Encerra a função antecipadamente
    }
    return -1;
}

// Busca uma trinca na direção da diagonal principal
int procdp() {

    int flag = 1;
    int temp = matriz[0][0];

    if (temp == ' ') {return -1;}

    for (int k = 1; k < tam; k++) {
        if (matriz[k][k] == ' ') {return -1;} // Célula em branco
        if (temp == matriz[k][k]) {
                flag++;
            }
        else {return -1;} // Não fez a trinca
    }
    return flag; // Fez a trinca
}

// Busca uma trinca na direção diagonal secundária
int procds() {

    int flag = 1;
    int temp = matriz[0][2];

    if (temp == ' ') {return -1;}

    for (int k = 1; k < tam; k++) {
        if (matriz[k][tam - 1 - k] == ' ') {return -1;} // Célula em branco
        if (temp == matriz[k][tam - 1 - k]) {
                flag++;
            }
        else {return -1;} // Não fez a trinca
    }
    return flag; // Fez a trinca
}

// Lê e valida o símbolo do jogador
void validaSimbolo(char idJogador[]) {
    char simbolo;
    int flagSimbolo = 1;
    while (flagSimbolo == 1) {
        printf("\nO jogador 1 será X ou O? ");
        simbolo = getchar();
        while(getchar() != '\n'); // Limpa o buffer
        
        if (simbolo != 'X' && simbolo != 'O') {
                printf("Símbolo inválido. O símbolo deve ser uma letra (X ou O). Tente novamente...\n");
            }
        else {flagSimbolo = 0;}
    }

    // Guardando o id dos jogadores
    if (simbolo == 'O') {
        idJogador[0] = 'O';
        idJogador[1] = 'X';
    }
}

// Converte de char para int e verifica se os valores são válidos
int validaCelula (char celula[], int *idLin, int *idCol) {

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
    int idLin, idCol;
    char idJogador[2] = {'X', 'O'};

    // Preenche a matriz com espaços em branco
    iniciaMatriz();

    printf("Para jogar, utilize apenas letras capitalizadas:");
    printf("\nA, B ou C para definir as linhas.");

    // Lê, valida e guarda os símbolos dos jogadores
    validaSimbolo(idJogador);

    // Declarações
    int jogador = 1;
    int jogadorAnterior;
    int jogadas = 0;
    int venceu = 0;

    while (jogadas < 9) {
        
        exibeMatriz();

        // Verifica se alguém já venceu apenas a partir da 3a jogada
        if (jogadas > 2) {
            
            // Vitória horizontal
            if (proch() == 3) {
                printf("\nFim de jogo. O jogador %d venceu. Parabéns!\n", jogadorAnterior);
                venceu = 1;
                break;
            }
            
            // Vitória vertical
            if (procv() == 3) {
                printf("\nFim de jogo. O jogador %d venceu. Parabéns!\n", jogadorAnterior);
                venceu = 1;
                break;
            }

            // Vitória diagonal principal
            if (procdp() == 3) {
                printf("\nFim de jogo. O jogador %d venceu. Parabéns!\n", jogadorAnterior);
                venceu = 1;
                break;
            }

            // Vitória diagonal secundária
            if (procds() == 3) {
                printf("\nFim de jogo. O jogador %d venceu. Parabéns!\n", jogadorAnterior);
                venceu = 1;
                break;
            }
        }
        
        // Entrada de dados: célula
        printf("\nVez do jogador %d (%c)", jogador, idJogador[jogador - 1]);
        printf("\nInforme a célula (ex.: A1): ");
        fgets(celula, sizeof(celula), stdin);
        celula[strcspn(celula, "\n")] = '\0';
        
        // Jogadas válidas
        if (validaCelula(celula, &idLin, &idCol) == 1) { // Valida a célula e o valor
            
            if (jogador == 1) {matriz[idLin][idCol] = idJogador[0];}
            else {matriz[idLin][idCol] = idJogador[1];}

            jogadorAnterior = jogador;
            jogadas++;

            // Muda o jogador
            if (jogador == 1) {jogador++;}
            else {jogador--;}
        }
        // Jogadas inválidas
        else {
            printf("\n");
        }

    } // Fim do while

    exibeMatriz();
    if (venceu == 0) {printf("\nDeu velha!\n");}
    else {printf("\n");}

} // Fim da main
