/*
5) Uma palavra é um palíndromo se tem a mesma seqüência de letras, quer seja lida da esquerda para a direita ou da direita para a esquerda (exemplo: raiar). Utilizando uma pilha, proponha um algoritmo que teste se uma palavra é palíndromo (dicas: uma parte da palavra será empilhada e comparada a outra parte da palavra, ainda, observar que palavras com tamanho par tem tratamento um pouco diferente de palavras de tamanho 
impar).
*/

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <ctype.h>
#include <limits.h>

// Pilha com lista simplesmente encadeada

// Define a estrutura de um nó da lista
typedef struct node {
    char chave;
    struct node *prox;
} node;

// Cria a estrutura de uma pilha baseada em lista
typedef struct estPilha {
    node *topo; 
} estPilha;

// Cria e inicializa um novo nó, configurando seus ponteiros para NULL
node *init_node (char chave) {
    node *novo = malloc(sizeof(node)); // Aloca memória para o nó
    
    // Falha de alocação
    if (novo == NULL) {
        printf("Erro ao alocar memória para o nó.\n");
        return NULL;
    }

    novo->chave = chave;
    novo->prox = NULL;
    return novo;
}

// Cria e inicializa uma pilha
estPilha *init_pilha () {
    estPilha *pilha = malloc(sizeof(estPilha)); // Aloca memória para a estrutura da pilha
    
    // Falha de alocação
    if (pilha == NULL) {
        printf("Falha ao alocar memória para a estrutura da pilha.\n");
        return NULL;
    }

    pilha->topo = NULL;
    return pilha;
}

// Insere elementos no topo da pilha (início da lista)
void push (estPilha *pilha, node *novo) {
    
    // Memória não alocada
    if (pilha == NULL || novo == NULL) { 
        printf("Não é possível fazer o push.\n");
        return;
    }

    novo->prox = pilha->topo;
    pilha->topo = novo;
}

// Remove elementos do topo da pilha (início da lista)
char pop (estPilha *pilha) {
    
    // Pilha vazia ou não alocada
    if (pilha == NULL || pilha->topo == NULL) {
        printf("Pilha vazia.\n");
        return '\0';
    }

    node *temp = pilha->topo;
    pilha->topo = temp->prox;
    char valor = temp->chave; // Armazena a chave para retorno
    
    free(temp);
    return valor;
}

// Imprime a pilha
void imprimir_pilha (estPilha *pilha) {
   
    // Pilha vazia
    if (pilha == NULL || pilha->topo == NULL) {
        printf("Pilha vazia.\n");
        return;
    }

    node *x = pilha->topo; // Inicializa no topo da pilha
    
    // Imprime o topo
    if (x != NULL) { // Se a pilha não estiver vazia
        printf("topo ->  %c\n", x->chave); 
        x = x->prox; // Avança
    }
    
    // Imprime os demais elementos
    while (x != NULL) {
        printf("\t %c\n", x->chave); 
        x = x->prox; // Avança
    }
}

// Libera a memória alocada para os nós e a estrutura da pilha
void liberar_pilha (estPilha *pilha) {
    
    // Pilha não alocada
    if (pilha == NULL) {
        return;
    }
    
    node *x = pilha->topo; // Inicializa no topo da pilha
    
    // Libera os elementos da pilha
    while (x != NULL) {
        node *temp = x;
        x = x->prox; // Avança
        free(temp); // Libera memória do nó
    }

    free(pilha); // Libera memória alocada para a estrutura da pilha
}

void ehPalindromo (char palavra[]) {
    
    estPilha *pilha = init_pilha();
    int len = strlen(palavra);
    char palavra_minus[len + 1];

    // Falha de alocação
    if (pilha == NULL) {
        printf("Erro: não foi possível criar a pilha.\n");
        return;
    }

    // Empilha a palavra
    for (int i = 0; i < len; i++) {
        palavra_minus[i] = tolower(palavra[i]);
        node *novo = init_node(palavra_minus[i]);

        // Falha de alocação
        if (novo == NULL) {
            printf("Erro: falha ao criar nó.\n");
            liberar_pilha(pilha);
            return;
        }

        push(pilha, novo);
    }

    // Insere o terminador nulo
    palavra_minus[len] = '\0';

    // Desempilha a palavra, invertendo a ordem
    char pInvertida[len + 1];
    for (int i = 0; i < len; i++) {
        pInvertida[i] = pop(pilha);
    }

    // Insere o terminador nulo
    pInvertida[len] = '\0';

    printf("Palavra original: %s\n", palavra);
    printf("Palavra invertida: %s\n", pInvertida);

    if (strcmp(palavra_minus, pInvertida) == 0) {
        printf("A palavra %s é um palíndromo.\n", palavra);
    }
    else {
        printf("A palavra %s não é um palíndromo.\n", palavra);
    }

    // Libera a memória alocada para a pilha e seus itens
    liberar_pilha(pilha);
}

int main() {
    //Entrada
    char palavra[] = "arara arara";
    ehPalindromo(palavra);
}