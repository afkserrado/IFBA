/*
Instituto Federal da Bahia (IFBA)
Tecnólogo em Análise e Desenvolvimento de Sistemas (ADS)
Semestre 2025.1
INF006 - Estrutura de Dados e Algoritmos
Professor: José Dihego
Aluno: Anderson Serrado
T2Q1
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

#define dimLinha 3001 // 3000 caracteres + 1 para o \0

// Lista duplamente encadeada não ordenada

// Define a estrutura de um nó da lista
typedef struct node {
    int marcador; // Marca o início de um start
    int chave;
    struct node *ante; 
    struct node *prox;
} node;

// Define a estrutura de uma lista duplamente encadeada
typedef struct lista_dup {
    node *cabeca;
    node *cauda;
} lista_dup;

// Cria e inicializa um novo nó, configurando seus ponteiros para NULL
node *init_node (int chave) {
    node *node_novo = malloc(sizeof(node)); // Aloca memória para o primeiro nó
    node_novo->chave = chave;
    node_novo->marcador = 0;
    node_novo->ante = NULL;
    node_novo->prox = NULL;
    return node_novo;
}

// Cria e inicializa uma nova lista, configurando o head para NULL (lista vazia)
lista_dup *init_lista () {
    lista_dup *lista = malloc(sizeof(lista_dup));
    lista->cabeca = NULL;
    lista->cauda = NULL;
    return lista;
}

// Insere um novo nó no início da lista
void inserir_node (lista_dup *lista, node *node_novo) {
    if (lista->cabeca == NULL) { // Lista vazia
        lista->cabeca = node_novo; // Insere o novo nó na cabeça da lista
        lista->cauda = node_novo;
    }
    else { // Lista não vazia  
        // Insere o novo nó no início da lista
        node_novo->prox = lista->cabeca; // "prox" do novo nó aponta para o atual "cabeca"
        lista->cabeca->ante = node_novo; // "ante" do atual "cabeca" aponta para o novo nó
        lista->cabeca = node_novo; //  "cabeca" recebe o novo nó
    }
}

// Insere um novo nó na posição ordenada
void inserir_node_ordenado (lista_dup *lista, node *node_novo) {
    if (lista->cabeca == NULL) { // Lista vazia
        lista->cabeca = node_novo;
        lista->cauda = node_novo;
    }
    else { // Lista não vazia
        
        // Variáveis temporárias
        node *x = lista->cabeca;
        int chave = node_novo->chave;

        while (x != NULL && x->chave < chave) {
            x = x->prox;
        }

        // Caso 1: o node_novo é o primeiro item da lista
        if (x == lista->cabeca) {
            node_novo->prox = lista->cabeca;
            node_novo->ante = NULL;
            lista->cabeca->ante = node_novo;
            lista->cabeca = node_novo;
        }
        // Caso 2: o node_novo é o último item da lista
        else if (x == NULL) {
            node_novo->prox = NULL;
            node_novo->ante = lista->cauda;
            lista->cauda->prox = node_novo;
            lista->cauda = node_novo;
        }
        // Caso 3: o node_novo ocupa qualquer posição intermediária da lista
        else {
            node_novo->ante = x->ante;
            node_novo->prox = x;
            x->ante->prox = node_novo;
            x->ante = node_novo;      
        }
    }
}

// Imprime a lista
void imprimir_lista (lista_dup *lista) {
    node *x = lista->cabeca; // Inicializa x com a "cabeca" da lista
    printf("\n(NULL)"); // Início da lista
    while (x != NULL) {
        printf("<- (%d | %d) ->", x->chave, x->marcador);
        x = x->prox;
    }
    printf(" (NULL)\n\n"); // Fim da lista
}

// Função para liberar todos os nós da lista
void liberar_lista(lista_dup *lista) {
    node *x = lista->cabeca;
    while (x != NULL) {
        node *temp = x;
        x = x->prox;
        free(temp); // Libera a memória de cada nó
    }
    free(lista); // Libera a memória da lista
}
 // Converte um token para int e adiciona na lista
void processar_token (char *token, lista_dup *lista) {
    if (token == NULL) {return;}
    
    int chave = atoi(token);
    inserir_node(lista, init_node(chave));
}

int main () {

    // Inicialização das listas
    lista_dup *lsp = init_lista(); // lsp = lista principal
    lista_dup *lss = init_lista(); // lss = lista secundária

    // Abre o arquivo e retorna um endereço de memória
    FILE *arqEntrada = fopen("L1Q1.in", "r"); // Ponteiro para o tipo FILE
    FILE *arqSaida = fopen("L1Q1.out", "w"); // Cria o arquivo se não existir

    // Se o arquivo não puder ser aberto, fopen retorna NULL
    if (arqEntrada == NULL || arqSaida == NULL) {
        printf("Os arquivos não podem ser abertos. Verifique se os arquivos e o executável estão na mesma pasta.\n");
        return EXIT_FAILURE;
    }

    // Declarações
    char linha[dimLinha], *token, *subtoken;
    char *saveptr1, *saveptr2; // Ponteiros para salvar o estado
    char del1[] = "start";
    char del2[] = " ";
    int v[1000], cont = 0;
    int flag = 0;

    // Lê o arquivo de entrada até o fim, quando fgets retorna NULL
    // Percorre o arquivo
    while (fgets(linha, dimLinha, arqEntrada) != NULL) { 

        if (flag == 1) {
            // Pula uma linha após o primeiro loop e evita pular após o último
            fprintf(arqSaida, "\n");
        }
        
        // Remove o \n (caso exista)
        linha[strcspn(linha, "\n")] = '\0';

        // Pega a primeira substring da linha, 
        // Ou seja, os todos os caracteres entre "start " e "start "
        token = strtok_r(linha, del1, &saveptr1);

        // Verifica se o primeiro token (substring) está vazio
        if (token == NULL) {  // Caso a linha esteja vazia ou com formato incorreto
            fprintf(arqSaida, "Erro: linha vazia ou inválida.");
            flag = 1;
            continue; // Pula para a próxima linha
        }

        // Lê a linha até encontrar "start ", separando a substring, ou até o fim da linha
        // Percorre uma linha
        while (token != NULL) {

            // Pega o primeiro número do token (substring)
            subtoken = strtok_r(token, del2, &saveptr2);
            v[cont++] = INT_MAX; // Marca o início da substring

            // Separa os números (subtokens) do token (substring)
            while (subtoken != NULL) {
                v[cont++] = atoi(subtoken);  // Converte e armazena no vetor
                processar_token(subtoken, lss);
                subtoken = strtok_r(NULL, del2, &saveptr2);  // Busca o próximo número
            }
            lss->cabeca->marcador = -1;
            token = strtok_r(NULL, del1, &saveptr1);
        }

        // Impede a quebra de linha após a última linha do arquivo
        // Reinicializa a pilha após a primeira linha
        flag = 1;
    }

    // Impressão final do vetor v[]
    printf("\n| ");
    for (int i = 0; i < cont; i++) {
        if (i == 0) {
            printf("%d", v[i]); // Imprime o primeiro número sem barra vertical antes
        } else {
            printf(" | %d", v[i]); // Imprime os outros números com barra vertical antes
        }
    }
    printf(" |\n");

    // Libera a memória alocada para as listas
    imprimir_lista(lss);
    liberar_lista(lss);
    liberar_lista(lsp);
    
    fclose(arqEntrada); // Fecha o arquivo e libera a memória
    fclose(arqSaida); // Fecha o arquivo e libera a memória
    return EXIT_SUCCESS;
}