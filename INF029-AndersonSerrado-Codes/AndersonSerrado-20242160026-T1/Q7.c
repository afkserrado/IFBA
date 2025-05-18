#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

/*
 Q7 = jogo busca palavras
 @objetivo
    Verificar se existe uma string em uma matriz de caracteres em todas as direções e sentidos possíves
 @entrada
    Uma matriz de caracteres e uma string de busca (palavra).
 @saida
    1 se achou 0 se não achou
 */

// Inverte a palavra
void invertePalavra(char palavra[6], char palavraInvertida[6], int len) {
    for (int i = 0; i < len; i++) {
        palavraInvertida[i] = palavra[len - i - 1];
    }
    palavraInvertida[len] = '\0';
}

// Direção horizontal ↔️
void proch(int len, char palavra[len + 1], char matriz[8][10], int *achou) {
    
    for (int i = 0; i < 8; i++) { // Percorre as linhas da matriz
        int k = 0; // Percorre a palavra
        int restoColunas = 10; // Qtd de colunas restantes da matriz
        
        for (int j = 0; j < 10; j++) { // Percorre as colunas da matriz      
            restoColunas = 10 - j;
            if (k == 0 && len > restoColunas) {break;} // Sai do loop j
            
            if (matriz[i][j] == palavra[k]) { // Achou o caractere
                k++;
                if (k == len) { // Achou a palavra
                    *achou = 1;
                    return; // Interrompe a função
                }
            }
            else if (k > 0) { // Achou a palavra parcialmente
                k = 0; // Reinicialização
                j--; // Decrementa o j para continuar a nova busca do mesmo ponto
            }
        } // Fim do loop j
    } // Fim do loop i
}

// Direção vertical ↕️
void procv(int len, char palavra[len + 1], char matriz[8][10], int *achou) {
    
    for (int i = 0; i < 10; i++) { // Percorre as colunas da matriz
        int k = 0; // Percorre a palavra
        int restoLinhas = 8; // Qtd de linhas restantes da matriz
        
        for (int j = 0; j < 8; j++) { // Percorre as linhas da matriz      
            restoLinhas = 8 - j;
            if (k == 0 && len > restoLinhas) {break;} // Sai do loop j
            
            if (matriz[j][i] == palavra[k]) { // Achou o caractere
                k++;
                if (k == len) { // Achou a palavra
                    *achou = 1;
                    return; // Interrompe a função
                }
            }
            else if (k > 0) { // Achou a palavra parcialmente
                k = 0; // Reinicialização
                j--; // Decrementa o j para continuar a nova busca do mesmo ponto
            }
        } // Fim do loop j
    } // Fim do loop i
}

// Direção inclinação principal ↘️↖️
void procip(int len, char palavra[len + 1], char matriz[8][10], int *achou) {
    
    for (int i = 0; i < 8; i++) { // Percorre as linhas da matriz
        int restoLinhas = 8 - i; // Qtd de linhas restantes da matriz
        int restoColunas = 10; // Qtd de colunas restantes da matriz
        
        for (int j = 0; j < 10; j++) { // Percorre as colunas da matriz      
            restoColunas = 10 - j;
            
            if (restoColunas < len) {break;} // Sai do loop j
            if (restoLinhas < len) {return;} // Interrompe o programa, pois não há mais como achar a palavra

            int k = 0; // Percorre a palavra
            int l = i;
            int c = j;
            // Percorre a diagonal de inclinação principal
            while (l <= i + len - 1 && c <= j + len - 1) { 
                if (matriz[l][c] == palavra[k]) {
                    k++;
                    if (k == len) { // Achou a palavra
                        *achou = 1;
                        return; // Interrompe a função
                    }
                }
                else {break;} // Sai do while
                // Incrementa
                l++;
                c++;
            }
        } // Fim do loop j
    } // Fim do loop i
}

// Direção inclinação secundária ↙️↗️
void procis (int len, char palavra[len + 1], char matriz[8][10], int *achou) {
    
    for (int i = 0; i < 8; i++) { // Percorre as linhas da matriz
        int restoLinhas = 8 - i; // Qtd de linhas restantes da matriz
        int restoColunas = 10; // Qtd de colunas restantes da matriz
        
        for (int j = 9; j >= 0; j--) { // Percorre as colunas da matriz      
            restoColunas--;
            
            if (restoColunas < len) {break;} // Sai do loop j
            if (restoLinhas < len) {return;} // Interrompe o programa, pois não há mais como achar a palavra

            int k = 0; // Percorre a palavra
            int l = i;
            int c = j;
            // Percorre a diagonal de inclinação secundária
            while (l <= i + len - 1 && c >= j - len + 1) { 
                if (matriz[l][c] == palavra[k]) {
                    k++;
                    if (k == len) { // Achou a palavra
                        *achou = 1;
                        return; // Interrompe a função
                    }
                }
                else {break;} // Sai do while
                // Incrementa
                l++;
                c--;
            }
        } // Fim do loop j
    } // Fim do loop i
}

int q7(char matriz[8][10], char palavra[6]) {

    int achou = 0;
    int len = strlen(palavra); // Comprimento da palavra buscada

    // Inverte a palavra buscada
    char palavraInvertida[6];
    invertePalavra(palavra, palavraInvertida, len);

    // Procura a palavra na horizontal
    proch(len, palavra, matriz, &achou);
    proch(len, palavraInvertida, matriz, &achou);

    // Procura a palavra na vertical
    procv(len, palavra, matriz, &achou);
    procv(len, palavraInvertida, matriz, &achou);

    // Não achou
    return achou;
}

int main () {

    char matrix[8][10] = {
        { 'Q', 'M', 'J', 'D', 'L', 'A', 'Z', 'F', 'C', 'R' },
        { 'N', 'B', 'Y', 'G', 'P', 'S', 'K', 'H', 'E', 'X' },
        { 'V', 'O', 'W', 'U', 'T', 'I', 'Z', 'A', 'L', 'C' },
        { 'M', 'Q', 'B', 'D', 'N', 'F', 'R', 'J', 'G', 'E' },
        { 'H', 'S', 'K', 'T', 'U', 'X', 'W', 'O', 'P', 'Y' },
        { 'C', 'Z', 'A', 'I', 'L', 'M', 'V', 'G', 'N', 'B' },
        { 'D', 'F', 'E', 'H', 'S', 'K', 'J', 'Q', 'R', 'T' },
        { 'U', 'X', 'Y', 'W', 'V', 'O', 'P', 'N', 'M', 'L' }
    };

    char stringBusca [6] = { 'F', 'E', 'H', 'S', 'K'};
    printf("%d\n", q7(matrix, stringBusca) == 1);
    strcpy(stringBusca, "KJQRT");
    printf("%d\n", q7(matrix, stringBusca) == 1);
    strcpy(stringBusca, "AATOK");
    printf("%d\n", q7(matrix, stringBusca) == 0);
    strcpy(stringBusca, "LTBON");
    printf("%d\n", q7(matrix, stringBusca) == 1);

}