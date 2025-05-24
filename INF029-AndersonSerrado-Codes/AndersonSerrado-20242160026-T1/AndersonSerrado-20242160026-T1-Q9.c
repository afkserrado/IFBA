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
int proch(int linhas[]) {

    int flag;

    for (int i = 0; i < tam; i++) {
        
        //if (linhas[i] == 1) {continue;} // Linha descartada
        if (matriz[i][0] == ' ') {continue;}

        flag = 1; // Inicialização
        char temp = matriz[i][0];
        for (int j = 1; j < tam; j++) {
            if (temp == matriz[i][j]) {
                flag++;
            }
            else { // Sai do loop j
                //linhas[i] = 1; // Descarta a linha, pois não há mais possibilidade de trinca
                break;
            } 
        }

        // Vitória
        if (flag == 3) {return flag;} // Encerra a função antecipadamente
    }
    return -1;
}

// Busca uma trinca na vertical
int procv(int colunas[]) {

    int flag;

    for (int j = 0; j < tam; j++) {
        
        //if (colunas[j] == 1) {continue;} // Coluna descartada
        if (matriz[0][j] == ' ') {continue;}

        flag = 1; // Inicialização
        char temp = matriz[0][j];
        for (int i = 1; i < tam; i++) {
            if (temp == matriz[i][j]) {
                flag++;
            }
            else { // Sai do loop i
                //colunas[j] = 1; // Descarta a coluna, pois não há mais possibilidade de trinca
                break;
            } 
        }

        // Vitória
        if (flag == 3) {return flag;} // Encerra a função antecipadamente
    }
    return -1;
}

// Busca uma trinca na direção da diagonal principal
int procdp(int *diagPrin) {

    //if (*diagPrin == 1) {return -1;} // Diagonal principal descartada
    
    char temp = matriz[0][0];
    if (temp == ' ') {return 0;} // Célula em branco

    int flag = 1;
    for (int k = 1; k < tam; k++) {
        if (matriz[k][k] == ' ') {return 0;} // Célula em branco
        if (temp == matriz[k][k]) {
                flag++;
            }
        else {
            //*diagPrin = 1; // Descarta a diagonal principal, pois não há mais possibilidade de trinca
            return -1; // Não fez a trinca
        }
    }
    return flag; // Fez a trinca
}

// Busca uma trinca na direção diagonal secundária
int procds(int *diagSecn) {

    //if (*diagSecn == 1) {return -1;} // Diagonal secundária descartada

    char temp = matriz[0][2];
    if (temp == ' ') {return 0;} // Célula em branco

    int flag = 1;
    for (int k = 1; k < tam; k++) {
        if (matriz[k][tam - 1 - k] == ' ') {return 0;} // Célula em branco
        if (temp == matriz[k][tam - 1 - k]) {
                flag++;
            }
        else {
            //*diagSecn = 1; // Descarta a diagonal secundária, pois não há mais possibilidade de trinca
            return -1; // Não fez a trinca
        } 
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

    // Limpa a tela
    #ifdef _WIN32
        system("cls");
    #else
        system("clear");
    #endif

    // Declarações
    char celula[3]; // +1 para o \0
    int idLin, idCol;
    char idJogador[2] = {'X', 'O'};
    int linhas[] = {0, 0, 0};
    int colunas[] = {0, 0, 0};
    int diagPrin = 0;
    int diagSecn = 0;

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

    while (jogadas <= 9) {
        
        exibeMatriz();

        for (int i = 0; i < tam; i++) {
            printf("| %d |", linhas[i]);
        }

        // Verificação da vitória
        if (jogadas >= 3) {
            if (proch(linhas) == 3 || procv(colunas) == 3 || 
                procdp(&diagPrin) == 3 || procds(&diagSecn) == 3) {
                venceu = 1;
                break;
            }
        }
        
        // Entrada de dados: célula
        printf("\nVez do jogador %d (%c)", jogador, idJogador[jogador - 1]);
        printf("\nInforme a célula (ex.: A1): ");
        fgets(celula, sizeof(celula), stdin);
        celula[strcspn(celula, "\n")] = '\0';

        // LImpeza do buffer
        while(getchar() != '\n');
        
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
    else {
        printf("\nFim de jogo. O jogador %d (%c) venceu. Parabéns!\n", jogadorAnterior, idJogador[jogadorAnterior - 1]);
        printf("\n");
    }

} // Fim da main
