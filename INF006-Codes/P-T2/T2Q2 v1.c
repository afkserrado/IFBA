/*
Instituto Federal da Bahia (IFBA)
Tecnólogo em Análise e Desenvolvimento de Sistemas (ADS)
Semestre 2025.1
INF006 - Estrutura de Dados e Algoritmos
Professor: José Dihego
Aluno: Anderson Serrado
T2Q2

Implemente uma pilha de texto todos os elementos estão sempre ordenados alfabeticamente sem ferir a politica LIFO (las-in first-out).

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
// ESTRUTURAS DE DADOS: PILHA

// Cria a estrutura de uma pilha
typedef struct pilha {
    char **nomes; // Ponteiro para o array de ponteiros de strings
    int tamanho; // Tamanho máximo da pilha
    int topo; // Índice do topo da pilha (inicialmente -1, porque começa vazia)
} pilha;

/*
'nomes' é como um vetor de ponteiros. Cada i aponta para um outro endereço. Ou seja, 'nomes' contém um endereço correspondente a um bloco contíguo de memória. Cada índice desse bloco contém um outro endereço, que aponta para o início da string. 

Por exemplo, se a pilha contiver os nomes Anderson, Kleyson e Serrado:
nomes[0] > contém o endereço de memória onde 'A', primeira letra do primeiro nome, está armazenada.
nomes[2] > da mesma forma, aponta para o endereço de memória de 'S', que é um bloco contíguo, contendo o nome "Serrado".

Portanto, int *itens é como um vetor de inteiros.
Por outro lado, char **nomes é como um vetor de ponteiros, ou como uma matriz de char.
*/

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

// Cria e inicializa uma pilha
int tamPilha = ceil((float)tamLinha / 2);
void init_pilha (pilha *pilha_itens) {
    // Aloca memória para o array de ponteiros de strings
    pilha_itens->nomes = malloc(tamPilha * sizeof(char*));

    pilha_itens->tamanho = tamPilha; // Define o tamanho máximo da pilha
    pilha_itens->topo = -1; // Inicializa o topo como -1 (pilha vazia)
}

// Verifica se a pilha está cheia
bool pilha_cheia (pilha *pilha_nomes) {
    return pilha_nomes->topo == pilha_nomes->tamanho - 1; // Retorna true se a pilha estiver cheia
}

// Insere elementos na pilha
void push (pilha *pilha_nomes, char *token) {
    if (!pilha_cheia(pilha_nomes)) { // Se a pilha estiver vazia
        pilha_nomes->topo++; // Incrementa o topo, adicionando o item na pilha_nomes
        
        // Aloca memória para a string
        pilha_nomes->nomes[pilha_nomes->topo] = malloc(strlen(token) + 1); 

        // Guarda a string na pilha
        strcpy(pilha_nomes->nomes[pilha_nomes->topo], token);
    }
}

// Verifica se a pilha está vazia
bool pilha_vazia (pilha *pilha_nomes) {
    return pilha_nomes->topo == -1; // Retorna true se o topo for -1 (pilha vazia)
}

// Remove elementos da pilha
char* pop (pilha *pilha_nomes) {
    if (!pilha_vazia(pilha_nomes)) { // Se a pilha não estiver vazia
        pilha_nomes->topo--; // Decrementa o topo, removendo o item da pilha_nomes
        return pilha_nomes->nomes[pilha_nomes->topo + 1]; // Retorna o elemento removido
    }
}

void processar_token (pilha *pilha_nomes, char *token, pilha *pilha_aux, FILE *arqSaida) {
    int pops = 0;
    char *nome;
    
    if (!pilha_vazia(pilha_nomes)) { // Se a pilha não estiver vazia
        while (pilha_nomes->topo >= 0 && strcmp(token, pilha_nomes->nomes[pilha_nomes->topo]) < 0) {
            nome = pop(pilha_nomes);
            push(pilha_aux, nome);
            pops++;
        }

        if (pops > 0) {
            fprintf(arqSaida, " %dx−pop", pops);
        }
        
        // Guarda o token na pilha na posição ordenada
        push(pilha_nomes, token);
        fprintf(arqSaida, " push−%s", pilha_nomes->nomes[pilha_nomes->topo]);

        // Realoca os elementos removidos, caso haja, na pilha_nomes
        if (pops > 0) {
            while (!pilha_vazia(pilha_aux)) {
                nome = pop(pilha_aux);
                push(pilha_nomes, nome);
                fprintf(arqSaida, " push−%s", pilha_nomes->nomes[pilha_nomes->topo]);
            }
        }
        
    }
    else { // Se a pilha estiver vazia
        push(pilha_nomes, token); // Guarda o token no topo da pilha
        fprintf(arqSaida, "push−%s", pilha_nomes->nomes[pilha_nomes->topo]);
    }
}

// Libera os blocos de memória alocados para a pilha, vetor de ponteiros e strings
void liberar_pilha(pilha *p) {
    // Libera cada string armazenada
    for (int i = 0; i <= p->topo; i++) {
        free(p->nomes[i]);
    }

    // Libera o vetor de ponteiros
    free(p->nomes);

    // Libera a estrutura pilha em si
    free(p);
}

// Libera os blocos de memória alocados para as strings
void reinicializar_pilha(pilha *p) {
    // Libera cada string armazenada
    for (int i = 0; i <= p->topo; i++) {
        free(p->nomes[i]);
    }

    p->topo = -1; // Reseta o topo
}

// ##################################################### //
// MAIN

int main () {

    // Aloca memória para a pilha
    pilha *pilha_nomes = malloc(sizeof(pilha));
    pilha *pilha_aux = malloc(sizeof(pilha));

    // Inicializa as pilhas a cada nova linha
    init_pilha(pilha_nomes);
    init_pilha(pilha_aux);

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
    int flag = 0;
    int pushes;

    // Lê o arquivo de entrada até o fim, quando fgets retorna NULL
    // Percorre o arquivo
    while (fgets(linha, tamLinha, arqEntrada) != NULL) { 

        if (flag == 1) {
            // Pula uma linha após o primeiro loop e evita pular após o último
            fprintf(arqSaida, "\n");

            // Reinicializa a pilha, liberando as strings e fazendo topo = -1
            reinicializar_pilha(pilha_nomes);
            reinicializar_pilha(pilha_aux);
        }
        
        linha[strcspn(linha, "\n")] = '\0'; // Remove o \n (caso exista)
        pushes = 0; // Reset

        token = strtok(linha, delimitador); // Pega o primeiro nome da linha

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
            processar_token(pilha_nomes, token, pilha_aux, arqSaida);
            pushes++; // Contabiliza a quantidade de pushes (nomes da linha)
            token = strtok(NULL, delimitador); // Busca o próximo nome

            /*
            Obs.: 'strtok' recebe NULL para buscar o próximo token na mesma string, mas após o token anterior
            */
        }

        // Verifica se houve algum erro no processamento da linha
        if (pushes == 0) {
            fprintf(arqSaida, "Erro: não foi possível processar a linha.");
            flag = 1;
            continue; // Pula para a próxima linha caso não haja nomes válidos
        }

        // Impede a quebra de linha após a última linha do arquivo
        // Reinicializa a pilha após a primeira linha
        flag = 1;
    }

    // Libera a memória alocada para as pilhas
    liberar_pilha(pilha_nomes);
    liberar_pilha(pilha_aux);

    fclose(arqEntrada); // Fecha o arquivo e libera a memória
    fclose(arqSaida); // Fecha o arquivo e libera a memória
    return EXIT_SUCCESS;
}