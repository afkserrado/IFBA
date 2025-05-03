/*
Instituto Federal da Bahia (IFBA)
Tecnólogo em Análise e Desenvolvimento de Sistemas (ADS)
Semestre 2025.1
INF006 - Estrutura de Dados e Algoritmos
Professor: José Dihego
Aluno: Anderson Serrado
*/

// ##################################################### //
// BIBLIOTECAS

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <math.h>

// ##################################################### //
// CONSTANTES

#define tamEntrada 501

// ##################################################### //
// STRCUTS

typedef struct {
    int x;
    int y;
    float distancia;
} ponto;

// ##################################################### //
// FUNÇÕES

// Elimina letras e parênteses da linha
void limparLinha(char linha[tamEntrada]) {
    int i, j = 0;  
    int len = strlen(linha); // Calcula o comprimento da linha
    for (i = 0; i < len; i++) {
        if (isdigit(linha[i]) || linha[i] == ',' || linha[i] == ' ' || linha[i] == '-') {
            // Shift
            linha[j] = linha[i];
            j++;
        }
    }
    linha[j] = '\0'; // Insere o terminador nulo
}

// Calcula as distâncias dos pontos até a origem
float calculaDistancia (int x, int y) {
    float distancia = sqrt(x*x + y*y);
    return distancia;
}

//Insertion sort
void insertionSort (ponto A[], int len){

    int i, j;
    ponto temp;
    for (j = 1; j < len; j++){
        temp = A[j];
        i = j - 1;
        
        while (i >= 0 && A[i].distancia > temp.distancia){
            A[i + 1] = A[i];
            i--;
        }
        A[i + 1] = temp;
    }
}

int main () {

    system("clear");

    //Abre o arquivo e retorna um endereço de memória
    FILE *arqEntrada = fopen("L0Q1.in", "r"); // Ponteiro para o tipo FILE
    FILE *arqSaida = fopen("L0Q1.out", "w");

    // Se o arquivo não puder ser aberto, fopen retorna NULL
    if (arqEntrada == NULL || arqSaida == NULL) {
        printf("Os arquivos não podem ser abertos. Verifique se os arquivos e o executável estão na mesma pasta.\n");
        return EXIT_FAILURE;
    }

    // Declarações
    char linha[tamEntrada];
    char *token;
    char delimitador[] = ", ";
    int cont; // Marca a posição do ponto no vetor de pontos
    ponto pontos[100]; // Supondo um máximo de 100 pontos

    // Lê o arquivo de entrada até o fim, quando fgets retorna NULL
    // Percorre o arquivo
    while (fgets(linha, tamEntrada, arqEntrada) != NULL) { 

        limparLinha(linha);

        cont = 0; // Reseta o contador para cada linha
        token = strtok(linha, delimitador); // Pega o primeiro par

        // Lê a linha até o fim, quando strtok retorna NULL, e separa a string
        // Percorre uma linha
        while (token != NULL) { 
            pontos[cont].x = atoi(token); // Coordenada x do par atual
            token = strtok(NULL, delimitador);
            pontos[cont].y = atoi(token); // Coordenada y do par atual

            // Guarda a distância do ponto até a origem
            pontos[cont].distancia = calculaDistancia(pontos[cont].x, pontos[cont].y);            
            
            cont++;

            token = strtok(NULL, delimitador); // Busca os próximos pares
        }

        insertionSort(pontos, cont);

        // Escreve as coordenadas no arquivo de saída
        fprintf(arqSaida, "Coordenadas:\n");
        for (int i = 0; i < cont; i++) {
            fprintf(arqSaida, "Ponto %d: (%d,%d) | Distância: %.2f\n", i + 1, pontos[i].x, pontos[i].y, pontos[i].distancia);
        }
        fprintf(arqSaida, "\n");  // Adiciona uma linha em branco para separar as entradas
    }

    fclose(arqEntrada); // Fecha o arquivo e libera a memória
    return EXIT_SUCCESS;
}