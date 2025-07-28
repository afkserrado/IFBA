#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Estrutura do nó da lista principal
typedef struct no1 {
    int posicao;
    struct no1 *endereco;
    struct no1 *prox;
} no1;

// Estrutura do nó da lista secundária
typedef struct no2 {
    int chave;
    struct no2 *ante; // Ponteiro para o nó anterior ao atual
    struct no2 *prox; // Ponteiro para o nó posterior ao atual
} no2;

// Estrutura da lista principal
typedef struct l1 {
    struct l1 *cabeca; // Ponteiro para o primeiro elemento da lista
} l1;

// Estrutura da lista secundária
typedef struct l2 {
    struct l2 *cabeca; // Ponteiro para o primeiro elemento da lista
} l2;

// Cria um nó do tipo no1
no1 *init_no1 (int posicao) {
    no1 *novo = (no1 *)malloc(sizeof(no1)); // Aloca memória para o nó

    if (novo == NULL) { // Verifica se houve falha ao alocar memória
        printf("Falha de alocação.\n");
        return NULL;
    }

    // Inicialização
    novo->posicao = posicao;
    novo->endereco = NULL;
    novo->prox = NULL;

    // Retorno
    return novo;
}

// Cria um nó do tipo no2
no2 *init_no2 (int chave) {
    no2 *novo = (no2 *)malloc(sizeof(no2)); // Aloca memória para o nó

    if (novo == NULL) { // Verifica se houve falha ao alocar memória
        printf("Falha de alocação.\n");
        return NULL;
    }

    // Inicialização
    novo->chave = chave;
    novo->ante = NULL;
    novo->prox = NULL;

    return novo; // Retorna o nó
}

// Cria uma lista encada simples l1
l1 *init_l1 () {
    l1 *lista = (l1 *)malloc(sizeof(l1)); // Aloca memória para a lista

    if (lista == NULL) { // Verifica se houve falha de alocação
        printf("Falha de alocação.\n");
        return NULL;
    }

    lista->cabeca = NULL; // Inicialização da lista

    return lista; // Retorna a lista
}

// Cria uma lista encada simples l2
l2 *init_l2 () {
    l2 *lista = (l2 *)malloc(sizeof(l2)); // Aloca memória para a lista

    if (lista == NULL) { // Verifica se houve falha de alocação
        printf("Falha de alocação.\n");
        return NULL;
    }

    lista->cabeca = NULL; // Inicialização da lista

    return lista; // Retorna a lista
}

int main () {
    l1 *lista1 = init_l1();
    l2 *lista2 = init_l2();
}

void lerArquivo (FILE *arqEntrada, int *vetor[100]) {
    
    int tamLinha = 100;
    char linha[tamLinha];

    linha = fgets(linha, tamLinha, arqEntrada);
    linha[strcspn(linha, "\n")] = '\0';
}