#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

// Constantes
#define qtdBarcos 2
#define tam 10

// Variáveis globais
int tamBarcos[] = {4,3,1};
int tiposBarcos = sizeof(tamBarcos) / sizeof(tamBarcos[0]);
int maxNs = 10;
int largura = 1;

// Limpa a tela
void limparTela () {
    #ifdef _WIN32
        system("cls");   
    #else
        system("clear"); 
    #endif
}  

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

// Verifica o tamanho do barco é válido (1, 3 ou 4)
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
int validaPosicao (char celula[], int *idLin, int *idCol, int barco, char direcao, int tipo) {
    
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

    // Para a função posicionarBarcos
    if (tipo == 0) {
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
    }
    
    return 0;
}

// Cria um indexador único para uma célula da matriz
int concatenaPosicao (int idLin, int idCol) {
    return idLin * tam + idCol;
}

// Converte um indexador único de uma célula para linha e coluna
void separaPosicao () {
    // idLin = idxCel / tam;
    // idCol = idxCel % tam;
}

// Preenche a matriz com N para o barco e L para o seu contorno
int marcaBarco (char matriz[tam][tam], int idLin, int idCol, int barco, int barcos[], char direcao, int p[], int *cont) {
    
    int sentinela = 0;
    int iniLin = idLin, iniCol = idCol;

    // Procura o índice do barco
    int k;
    for (k = 0; k < tiposBarcos; k++) {
        if (barco == tamBarcos[k]) {break;} // Conserva o valor de i
    }

    // Inicializa as variáveis auxiliares
    int dLin = 0, dCol = 0;
    if (direcao == 'H') {dCol = 1;} // Horizontal
    else {dLin = 1;} // Vertical

    // Marca o barco na matriz e no vetor de posições
    int i;
    for (i = 0; i < barco; i++) {
        if (matriz[idLin][idCol] == ' ') {
            matriz[idLin][idCol] = 'N';
            p[*cont] = concatenaPosicao(idLin, idCol);
            //printf("cont = %d | p = %d\n", *cont, p[*cont]);

            // Incrementos
            idLin += dLin;
            idCol += dCol;
            (*cont)++;
            //printf("idLin = %d | idCol = %d\n", idLin, idCol);
        }
        else {sentinela = 1; break;} // Conserva i
    }

    // Barco marcado em posição inválida (N ou L)
    if (sentinela == 1) {
        
        // Retorna os índices para a última posição marcada
        idLin -= dLin;
        idCol -= dCol;
        (*cont)--;
        
        // Desfaz as alterações na matriz
        for (int j = i; j >= 1; j--) {
            matriz[idLin][idCol] = ' ';
            p[*cont] = 0;

            // Decrementos
            idLin -= dLin;
            idCol -= dCol;
            (*cont)--;
        }
        printf("\nJá existe um barco marcado nessa posição ou ela faz parte dos limites de um barco. Tente novamente...");
        return -1;
    }

    // Marca o início do contorno (quinta superior esquerda)
    iniLin--;
    iniCol--;

    // Problema de borda
    // Se iniLin ou iniCol for 0, as linhas anteriores decrementam para -1
    // Corrige iniLin e iniCol, incrementando para 0
    if (iniLin < 0) {iniLin++;}
    if (iniCol < 0) {iniCol++;}

    // Determina os limites do contorno
    int limLin = 0, limCol = 0;
    if (direcao == 'H') {
        limLin = iniLin + largura + 1;
        limCol = iniCol + barco + 1;
    }
    else {
        limLin = iniLin + barco + 1;
        limCol = iniCol + largura + 1;
    }

    // Problema de borda
    // Evita que limLin e limCol ultrapasse as bordas do tabuleiro
    if (limLin >= tam) limLin = tam - 1;
    if (limCol >= tam) limCol = tam - 1;

    // Problema de borda
    // Se iniLin ou iniCol for 0, limLin ou limCol fica a 2 quadrados de distância de N
    // Corrige limLin e limCol, mantendo a distância de 1 quadrado
    if (iniLin == 0) {limLin--;}
    if (iniCol == 0) {limCol--;}

    //printf("iniLin = %d | iniCol = %d | limLin = %d | limCol = %d", iniLin, iniCol, limLin, limCol);

    // Marca o contorno do barco
    for (int lin = iniLin; lin <= limLin; lin++) {
        for (int col = iniCol; col <= limCol; col++) {
            if (matriz[lin][col] == ' ') {
                matriz[lin][col] = 'L';
            }
        }
    }
    
    // Atualiza a quantidade de barcos do jogador
    barcos[k]--;

    return 0;
}

// Posiciona o barco, com auxílio das funções anteriores
void posicionarBarcos (char matriz[tam][tam], int jogador, int barcos[], int p[], int *cont) {
    
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
            flagCelula = validaPosicao(celula, &idLin, &idCol, barco, direcao, 0);

            // Posição e barco válidos
            if (flagCelula == 0) { 
                // Verificar retorno de marcaBarco
                flagMarca = marcaBarco(matriz, idLin, idCol, barco, barcos, direcao, p, cont); 
                if (flagMarca != 0) {printf("\n");}               
            }
            // Posição inválida
            else {printf("\n");}

        } // Fim do while
    } // Fim do for
}

void atacar (char matriz[tam][tam], int p[], int *sank, int jogador) {

    char celula[4];
    int flagCelula = -1;
    int idLin = -1, idCol = -1;

    while (flagCelula != 0) {  

        printf("\nJogador %d - Sua vez de atacar: ", jogador);
        fgets(celula, sizeof(celula), stdin);
        flagCelula = validaPosicao(celula, &idLin, &idCol, 0, '0', 0);

        // Posição inválida
        if (flagCelula != 0) { 
            printf("\n");
            continue;
        }

        // Verifica se a posição já foi atacada antes
        if (matriz[idLin][idCol] == 'O' || matriz[idLin][idCol] == 'X') {
            printf("\nEssa posição já foi escolhida. Tente novamente...");
            flagCelula = -1; // Reinicia o loop
        }
    }

    // Transforma os índices da célula em um indexador único
    int idxCel = concatenaPosicao(idLin, idCol);    

    // Busca o indexador no vetor de posições
    int i;
    for (i = 0; i < maxNs; i++) {
        // Barco encontrado
        if (idxCel == p[i]) {
            p[i] = -1; // "Elimina" a posição já encontrada
            matriz[idLin][idCol] = 'O'; // Marca a posição acertada
            (*sank)++; // Incrementa o número de Ns afundados
            break; // Saída antecipada; Conserva o valor de i
        }
    }
    // Barco não encontrado
    if (i == maxNs) {matriz[idLin][idCol] = 'X';}
}

int main () {

    limparTela();
    printf("Bem vindo ao Batalha Naval!\n");
    printf("Pressione qualquer tecla para continuar.\n");

    // Declarações
    char m1[tam][tam]; // Tabuleiro
    char m2[tam][tam];

    iniciaMatriz(m1);
    iniciaMatriz(m2);

    // Declarações
    int p1[maxNs]; // Guarda a posição dos barcos
    int p2[maxNs];

    // Inicialização p
    for (int i = 0; i < maxNs; i++) {
        p1[i] = -1;
        p2[i] = -1;
    }

    int cont1 = 0; // Conta o número de Ns marcados
    int cont2 = 0;
    int barcos1[] = {1,1,3}; // 4, 3 e 1
    int barcos2[] = {1,1,3}; // 4, 3 e 1

    posicionarBarcos(m1, 1, barcos1, p1, &cont1);
    posicionarBarcos(m2, 2, barcos2, p2, &cont2);

    limparTela();
    printf("Let the games begin 😈!\n");

    // Declarações
    int sank1 = 0; // Conta o número de Ns afundados
    int sank2 = 0;

    while (sank1 < maxNs && sank2 < maxNs) {
        atacar(m2, p2, &sank2, 1); // Jogador 1
        atacar(m1, p1, &sank1, 2); // Jogador 2
    }
    
} // Fim da main
