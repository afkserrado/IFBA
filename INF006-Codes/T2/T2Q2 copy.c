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
#include <limits.h>
#include <stdbool.h>
#include <math.h>

// ##################################################### //
// CONSTANTES

#define tamLinha 3001 // 3000 caracteres + 1 para o \0

// ##################################################### //
// ESTRUTURAS DE DADOS

// Cria a estrutura de uma pilha
typedef struct pilha {
    char *nomes; // Ponteiro para o array que armazena os elementos da pilha
    int tamanho; // Tamanho máximo da pilha
    int topo; // Índice do topo da pilha (inicialmente -1, porque começa vazia)
} pilha;

/*
Dimensionando o tamanho da pilha:

Pior caso: a linha estaria preenchida até o final, mas cada nome seria apenas uma letra

caracteres = nomes + espaços

Ex.: A B C
7 caracteres
3 nomes
2 espaços

Logo, espaços = nomes - 1

Substituindo, caracteres = nomes + (nomes - 1) = 2nomes - 1
3000 = 2nomes - 1 > nomes = 3001/2 = 1500,5
Como não existe meio nome: nomes = 1501

espaços = 1501 - 1 = 1500
Como a linha é terminada com um nome, não com espaço: espaços = 1500 - 1 = 1499

Portanto, no pior caso, a pilha teria 1501 nomes.

tamPilha = ceil(((tamLinha - 1) + 1) / 2);
*/

// Cria e inicializa uma pilha chamada pilha_nomes
int tamPilha = ceil((float)tamLinha / 2);
void init_pilha (pilha *pilha_nomes) {
    pilha_nomes->nomes = malloc(tamPilha * sizeof(char)); // Aloca memória para o array da pilha
    pilha_nomes->tamanho = tamPilha; // Define o tamanho máximo da pilha
    pilha_nomes->topo = -1; // Inicializa o topo como -1 (pilha vazia)
}

bool pilha_cheia (pilha *pilha_nomes) {
    return pilha_nomes->topo == pilha_nomes->tamanho - 1; // Retorna true se a pilha estiver cheia
}

bool pilha_vazia (pilha *pilha_nomes) {
    return pilha_nomes->topo == -1; // Retorna true se o topo for -1 (pilha vazia)
}

// Insere elementos na pilha
void push (pilha *pilha_nomes, int item) {
    if (!pilha_cheia(pilha_nomes)) { // Se a pilha estiver vazia
        pilha_nomes->topo = pilha_nomes->topo + 1; // Incrementa o topo, adicionando o item na pilha_nomes
        pilha_nomes->nomes[pilha_nomes->topo] = item; // Insere o item no topo da pilha
    }
    // !!!!!!!! AJUSTAR
    else {
        printf("Pilha cheia.\n");
    }
}

// Remove elementos da pilha
int pop (pilha *pilha_nomes) {
    if (!pilha_vazia(pilha_nomes)) { // Se a pilha não estiver vazia
        pilha_nomes->topo = pilha_nomes->topo - 1; // Decrementa o topo, removendo o item da pilha_nomes
        return pilha_nomes->nomes[pilha_nomes->topo + 1]; // Retorna o elemento removido
    }
    // !!!!!!!! AJUSTAR
    else {
        printf("Pilha vazia.\n");
        return INT_MAX; // Retorna um valor de erro se a pilha estiver vazia
    }
}

void imprimir_pilha (pilha *pilha_nomes) {
    printf("<< \n");
    for (int i = pilha_nomes->topo; i >= 0; i--) {
        if (i == pilha_nomes->topo) {
            printf("topo ->  %d\n", pilha_nomes->nomes[i]); // Marca o topo
        } 
        else
            printf("\t %d\n", pilha_nomes->nomes[i]); // Imprime os demais elementos
    }
    printf(">>\n");
}

// ##################################################### //
// FUNÇÕES

// ##################################################### //
// MAIN

int main () {

    //printf("tamPilha = %d\n", tamPilha);
    pilha *pilha_nomes = malloc(sizeof(pilha));

    // Abre o arquivo e retorna um endereço de memória
    FILE *arqEntrada = fopen("L1Q2.in", "r"); // Ponteiro para o tipo FILE
    FILE *arqSaida = fopen("L1Q2.out", "w"); // Cria o arquivo se não existir

    // Se o arquivo não puder ser aberto, fopen retorna NULL
    if (arqEntrada == NULL || arqSaida == NULL) {
        printf("Os arquivos não podem ser abertos. Verifique se os arquivos e o executável estão na mesma pasta.\n");
        return EXIT_FAILURE;
    }

    // Declarações
    char linha[tamLinha];
    char *token;
    char delimitador[] = " ";
    char nomes[tamLinha];
    int flag = 0;
    int cont;

    // Lê o arquivo de entrada até o fim, quando fgets retorna NULL
    // Percorre o arquivo
    while (fgets(linha, tamLinha, arqEntrada) != NULL) { 

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

        // Verifica se o primeiro token está vazio
        if (token == NULL) {  // Caso a linha esteja vazia ou com formato incorreto
            fprintf(arqSaida, "Erro: linha vazia ou inválida.");
            flag = 1;
            continue; // Pula para a próxima linha
        }

        // Lê a linha até o fim, quando strtok retorna NULL, e separa a string
        // Percorre uma linha
        while (token != NULL) { 
            
            /*// Copia o nome para o array 'nomes'
            for (int i = 0; token[i] != '\0'; i++) {
                nomes[cont] = token[i];
                cont++;
            }*/

            push(pilha_nomes, *token);

            /*// Coloca um espaço entre os nomes
            nomes[cont] = ' ';
            cont++;
            */

            // Busca o próximo nome
            token = strtok(NULL, delimitador);

            /*
            Obs.: 'strtok' recebe NULL para buscar o próximo token na mesma string, mas após o token anterior
            */
        }

        // Corrige a última posição para remover o último ' ' adicionado
        if (cont > 0) {
            cont--;
        }

        // Verifica se houve algum erro no processamento da linha
        if (cont == 0) {
            fprintf(arqSaida, "Erro: não foi possível processar a linha.");
            flag = 1;
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