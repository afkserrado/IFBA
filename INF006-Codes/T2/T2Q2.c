/*
Instituto Federal da Bahia (IFBA)
Tecnólogo em Análise e Desenvolvimento de Sistemas (ADS)
Semestre 2025.1
INF006 - Estrutura de Dados e Algoritmos
Professor: José Dihego
Aluno: Anderson Serrado
T2Q2

Implemente uma pilha de texto todos os elementos est˜ao sempre ordenados alfabeticamente sem ferir a politica LIFO (las-in first-out).

Obs. 1: entende-se que os nomes são todos simples.
Obs. 2: entende-se que os nomes começam com uma letra capital.
*/

// ##################################################### //
// BIBLIOTECAS

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// ##################################################### //
// CONSTANTES

#define tamEntrada 3001 // 3000 caracteres + 1 para o \0

// ##################################################### //
// FUNÇÕES

// ##################################################### //
// MAIN

int main () {

    // Abre o arquivo e retorna um endereço de memória
    FILE *arqEntrada = fopen("L1Q2.in", "r"); // Ponteiro para o tipo FILE
    FILE *arqSaida = fopen("L1Q2.out", "w"); // Cria o arquivo se não existir

    // Se o arquivo não puder ser aberto, fopen retorna NULL
    if (arqEntrada == NULL || arqSaida == NULL) {
        printf("Os arquivos não podem ser abertos. Verifique se os arquivos e o executável estão na mesma pasta.\n");
        return EXIT_FAILURE;
    }

    // Declarações
    char linha[tamEntrada];
    char *token;
    char delimitador[] = " ";
    char nomes[tamEntrada];
    int cont = 0;
    int flag = 0;

    // Lê o arquivo de entrada até o fim, quando fgets retorna NULL
    // Percorre o arquivo
    while (fgets(linha, tamEntrada, arqEntrada) != NULL) { 

        // Pula a linha após o primeiro loop e evita pular após o último
        if (flag == 1) {
            fprintf(arqSaida, "\n");
        }
        
        // Remove o \n (caso exista)
        linha[strcspn(linha, "\n")] = '\0';

        // Resets
        cont = 0;

        // Pega o primeiro nome da linha
        token = strtok(linha, delimitador);

        /*
        Obs. 1: como a linha sempre tem pelo menos um nome, o primeiro nome nunca é NULL.
        Obs. 2: 'strtok' já insere o terminador nulo ('\0') ao final da string.
        */

        // Tratamento de erros
        if (token == NULL) {  // Caso a linha esteja vazia ou com formato incorreto
            fprintf(stderr, "Erro: linha vazia ou inválida.\n");
            continue; // Pula para a próxima linha
        }

        // Lê a linha até o fim, quando strtok retorna NULL, e separa a string
        // Percorre uma linha
        while (token != NULL) { 
            
            // Copia o nome para o array 'nomes'
            for (int i = 0; token[i] != '\0'; i++) {
                nomes[cont] = token[i];
                cont++;
            }

            // Coloca um espaço entre os nomes
            nomes[cont] = ' ';
            cont++;

            // 'strtok' recebe NULL para buscar o próximo token na mesma string, mas após o token anterior
            token = strtok(NULL, delimitador); // Busca o próximo nome
        }

        // Corrige a última posição para remover o último ' ' adicionado
        if (cont > 0) {
            cont--;
        }

        // Verifica se houve algum erro no processamento da linha
        if (cont == 0) {
            fprintf(arqSaida, "Erro: não foi possível processar a linha.\n");
            continue; // Pula para a próxima linha caso não haja nomes válidos
        }

        // Escreve os resultados no arquivo de saída
        for (int i = 0; i < cont; i++) {
            fprintf(arqSaida, "%c", nomes[i]);
        }
        
        flag = 1;
    }

    fclose(arqEntrada); // Fecha o arquivo e libera a memória
    fclose(arqSaida); // Fecha o arquivo e libera a memória
    return EXIT_SUCCESS;
}