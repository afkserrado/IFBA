#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

// Constantes
#define qtdBarcos 5
#define tam 10
#define maxNs 10
#define largura 1

// Variáveis globais
int tamBarcos[] = {4,3,1};
int tiposBarcos = sizeof(tamBarcos) / sizeof(tamBarcos[0]);

// Limpa a tela
void limparTela() {
    #if defined(_WIN32) || defined(_WIN64) // Windows
        system("cls");

    #elif defined(__linux__) || defined(__unix__) || defined(__APPLE__) || defined(__MACH__) // Linux e macOS
        system("clear");

    #endif
}

// Limpa o buffer
void limparBuffer() {
    int c;
    // Loop intencionalmente vazio para consumir caracteres
    while ((c = getchar()) != '\n' && c != EOF) {}  
}

//Pausa a tela
void pausarTela() {
    #if defined(_WIN32) || defined(_WIN64) // Windows
        system("pause");

    #elif defined(__linux__) || defined(__unix__) || defined(__APPLE__) || defined(__MACH__) // Linux e macOS
        printf("Pressione Enter para continuar...");
        fflush(stdout);  // Força a exibição imediata
        limparBuffer();

    #endif
}

void regrasJogo () {
    printf("### REGRAS DO JOGO 🚢 ###\n");
    printf("O objetivo do jogo é afundar todos os navios do adversário. Para isso, cada jogador realiza na sua vez um disparo, escolhendo um local no tabuleiro.\n\n");

    printf("### COLOCANDO AS UNIDADES 🚢 ###\n");
    printf("Para iniciar o jogo, você debe posicionar os 5 navios no tabuleiro. Cada jogador dispõe de um porta-aviões (de 4 casas de comprimento), um submarino (de 3 casas) e três botes (de 1 casa).\n\n");
    printf("Ao posicionar seus barcos, você verá que eles serão representados no tabuleiro pelo símbolo [ N ], enquanto o símbolo [ L ] representará o contorno do navio, sobre o qual você não poderá colocar outros barcos.\n\n");

    printf("### COMEÇANDO A PARTIDA 🚢 ###\n");
    printf("Uma vez posicionados os 5 navios de cada jogador, cada adversário poderá disparar sobre uma posição do tabuleiro oponente. Caso seja bem sucedido, um [ O ] indicará que um navio (ou parte dele) foi encontrado. Tiros na água serão identificados pelo símbolo [ X ].\n\n");

    printf("Vence o jogador que afundar todos os barcos adversários primeiro.\n\n");

    printf("### LEGENDA 🚢 ###\n");
    printf("N: seu navio;\n");
    printf("L: limite do seu navio;\n");
    printf("O: alvo encontrado;\n");
    printf("X: disparo no mar.\n\n");

    printf("Let the games begin! 😈\n\n");
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
        printf("\nBarco inválido. Tente novamente...\n");
        return -1;
    }

    // Valida a quantidade de barcos
    if (barcos[i] == 0) {
        printf("\nO jogador não possui mais barcos de tamanho %d disponíveis. Tente novamente...\n", barco);
        return -1;
    }
    
    // Valida a direção do barco
    if (barco > 1 && direcao != 'H' && direcao != 'V') {
        printf("\nDireção inválida. Tente novamente...\n");
        return -1;
    }

    return 0;
}

// Converte de char para int e verifica se os valores são válidos
int validaPosicao (char celula[], int *idLin, int *idCol, int barco, char direcao, int tipo) {
    
    // Valida o comprimento da célula
    int lenCelula = strlen(celula);
    if (lenCelula < 2 || lenCelula > 3) {
        printf("\nCélula inválida. Tente novamente...\n");
        return -1;
    }
    
    // Valida a célula
    // Primeiro caractere
    int flag = 0;
    celula[0] = toupper(celula[0]);
    if (celula[0] < 'A' || celula[0] > 'J') {
        printf("\nCélula inválida. A linha deve ser uma letra de A a J. Tente novamente...\n");
        flag = -1;
    }
    // Terceiro caractere
    if (celula[2] != '\n') {
        if (!(celula[1] == '1' && celula[2] == '0')) {
            printf("\nCélula inválida. A coluna deve ser um número de 1 a 10. Tente novamente...\n");
            flag = -1;
        }
    }
    // Segundo caractere
    else if (celula[1] < '1' || celula[1] > '9') {
        printf("\nCélula inválida. A coluna deve ser um número de 1 a 10. Tente novamente...\n");
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
                printf("\nCélula inválida. O barco extrapola os limites do tabuleiro. Tente novamente...\n");
                return -1;
            }
        }
        else {
            int limitev = tam - barco;
            if (*idLin > limitev) {
                printf("\nCélula inválida. O barco extrapola os limites do tabuleiro. Tente novamente...\n");
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
        printf("\nJá existe um barco marcado nessa posição ou ela faz parte dos limites de um barco. Tente novamente...\n");
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
    
    printf("### POSICIONAR BARCOS 🚢 ###\n");
    printf("Vez do jogador %d: \n", jogador);

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

            limparBuffer();

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

    // Para consumir o buffer quando a célula é J10
    // pois não estava permitindo a exibição da matriz após a última célula
    if (idCol == 9) {getchar();}

    exibeMatriz(matriz);
}

void atacar (char matriz[tam][tam], char matrizb[tam][tam], int p[], int *sank, int jogador) {

    char celula[4];
    int flagCelula = -1;
    int idLin = -1, idCol = -1;

    while (flagCelula != 0) {  

        printf("### HORA DE ATACAR 🚢 ###\n");
        printf("Vez do jogador %d: \n", jogador);
        exibeMatriz(matrizb);
        printf("\nInforme a célula: ");
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

    // Inicialmente marca X
    matriz[idLin][idCol] = 'X';
    matrizb[idLin][idCol] = 'X';

    // Busca o indexador no vetor de posições
    int i;
    for (i = 0; i < maxNs; i++) {
        // Barco encontrado
        if (idxCel == p[i]) {
            p[i] = -1; // "Elimina" a posição já encontrada
            matriz[idLin][idCol] = 'O'; // Marca a posição acertada
            matrizb[idLin][idCol] = 'O';
            (*sank)++; // Incrementa o número de Ns afundados
            break; // Saída antecipada; Conserva o valor de i
        }
    }
    // Para consumir o buffer quando a célula é J10
    if (idCol == 9) {getchar();}
}

void menu (int jogador, char matriz_jogador[tam][tam], char matriz_oponente[tam][tam], char matrizb_oponente[tam][tam], int p[], int *sank) {
    
    int opcao; 

    do {
        printf("### MENU 🚢 ###\n");
        printf("\nVez do jogador %d:\n", jogador);
        printf("1 - Visualizar meu tabuleiro.\n");
        printf("2 - Visualizar tabuleiro do oponente.\n");
        printf("3 - Atacar o oponente.\n"); 

        printf("Opção: ");
        scanf("%d",&opcao);
        limparBuffer();
        limparTela();

        switch (opcao) {

            case 1: {
                printf("Tabuleiro do jogador %d: \n", jogador);
                exibeMatriz(matriz_jogador);
                pausarTela();
                limparTela();
                break;
            }
            case 2: {
                int oponente = 2;
                if (jogador == 2) {oponente = 1;} 

                printf("Tabuleiro do oponente %d: \n", oponente);
                exibeMatriz(matrizb_oponente);
                pausarTela();
                limparTela();
                break;
            }
            case 3: {
                atacar(matriz_oponente, matrizb_oponente, p, sank, jogador);
                exibeMatriz(matrizb_oponente);
                pausarTela();
                limparTela();
                break;
            }
            default: {
                printf("Opção inválida.\n");
                pausarTela();
                limparTela();
                break;
            }
        }
    } while (opcao != 3);
} 

int main () {

    limparTela();
    printf("BEM VINDO(A) AO BATALHA NAVAL! 🚢\n");
    pausarTela();
    limparTela();

    regrasJogo();
    pausarTela();
    limparTela();

    // Declarações
    // "matriz" é o tabuleiro visível do respectivo jogador
    // "matrizb" é o tabuleiro do oponente, visível ao jogador que ataca
    char m1[tam][tam];
    char m2[tam][tam];
    char m1b[tam][tam];
    char m2b[tam][tam];

    iniciaMatriz(m1);
    iniciaMatriz(m2);
    iniciaMatriz(m1b);
    iniciaMatriz(m2b);

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
    pausarTela();
    limparTela();
    posicionarBarcos(m2, 2, barcos2, p2, &cont2);
    pausarTela();
    limparTela();

    // Declarações
    int sank1 = 0; // Conta o número de Ns afundados
    int sank2 = 0;

    while (sank1 < maxNs && sank2 < maxNs) {
        menu(1, m1, m2, m2b, p2, &sank2); // Jogador 1
        if (sank2 >= maxNs) {break;}
        menu(2, m2, m1, m1b, p1, &sank1); // Jogador 2
    }

    int vencedor;
    if (sank2 == maxNs) {vencedor = 1;}
    else {vencedor = 2;}
    printf("Parabéns, jogador %d 🥇. Você venceu!\n", vencedor);

} // Fim da main
