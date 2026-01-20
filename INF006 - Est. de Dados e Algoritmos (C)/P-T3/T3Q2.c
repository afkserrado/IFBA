/*
Instituto Federal da Bahia (IFBA)
Tecnólogo em Análise e Desenvolvimento de Sistemas (ADS)
Semestre 2025.1
INF006 - Estrutura de Dados e Algoritmos
Professor: José Dihego
Aluno: Anderson Serrado
T3Q1
*/


// ##################################################### //
// OBSERVAÇÕES
// - As entradas são números inteiros.
// - Cada linha do arquivo de entrada é uma entrada.
// - A árvore não admite nós duplicados.
// - Saída em pré-ordem.

// ##################################################### //
// BIBLIOTECAS

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>

// ##################################################### //
// CONSTANTES

#define dimLinha 801 // 800 caracteres + 1 para o \0

// ##################################################### //
// ÁRVORE BINÁRIA DE BUSCA (ABB)

// Estrutura do nó da árvore
typedef struct no {
    int chave;
    int sEsq; // Soma das chaves dos nós da subárvore esquerda
    int sDir; // Soma das chaves dos nós da subárvore direita
    struct no *esq;
    struct no *dir;
    struct no *mae;
} no;

// Estrutura da árvore
typedef struct abb {
    no *raiz;
} abb;

// Inicialização do nó da árvore
no *init_no (int chave) {
    // Alocação de memória
    no *novo = (no *)malloc(sizeof(no));
    
    // Falha de alocação
    if (novo == NULL) {
        printf("Falha de alocação de memória para o nó.\n");
        return NULL;
    }

    // Inicializações
    novo->chave = chave;
    novo->esq = NULL;
    novo->dir = NULL;
    novo->mae = NULL;

    // Retorno
    return novo;
}

// Inicialização da árvore
abb *init_arvore () {
    // Alocação de memória
    abb *arv = (abb *)malloc(sizeof(abb));
    
    // Falha de alocação
    if (arv == NULL) {
        printf("Falha de alocação de memória para a árvore.\n");
        return NULL;
    }

    // Inicializações
    arv->raiz = NULL;

    // Retorno
    return arv;
}

// Insere um novo nó na árvore
void inserir_no (abb *arv, no *novo) {
    // Inicializações
    no *mae = NULL;
    no *atual = arv->raiz;

    // Busca a posição do novo nó na árvore
    while (atual != NULL) {
        mae = atual; // Salva a mãe

        // Caminha para a esquerda
        if (novo->chave < atual->chave) {
            atual = atual->esq;
        }
        // Caminha para a direita
        else if (novo->chave > atual->chave) {
            atual = atual->dir;
        }
        // Número duplicado
        else {
            free(novo); // Desaloca memória, pois o nó não será inserido
            return; 
        }
    }

    // Vincula a mãe ao filho
    novo->mae = mae;

    // Árvore vazia
    if (mae == NULL) {
        arv->raiz = novo;
    }
    // O novo é o filho da esquerda
    else if (novo->chave < mae->chave) {
        mae->esq = novo;
    }
    // O novo é o filho da direita
    else {
        mae->dir = novo;
    }
}

// Calcula a soma das subárvores à direita e esquerda
int soma_subarvore (no *x) {
    if (x == NULL) {return 0;}
    return x->chave + soma_subarvore(x->esq) + soma_subarvore(x->dir);
}

// Calcula a soma das subárvores de todos os nós
void calc_somas (no *x) {
    if (x == NULL) {return;}

    x->sEsq = soma_subarvore(x->esq);
    x->sDir = soma_subarvore(x->dir);

    calc_somas(x->esq);
    calc_somas(x->dir);
}

// Impressão dos nós em pré-ordem: raiz, esquerda, direita
void imprimir_preordem (no *x, FILE *arqSaida) {
    if (x != NULL) {
        // Delimitador
        if (x->mae != NULL) {fprintf(arqSaida, " ");}

        // Imprime em pré-ordem
        fprintf(arqSaida, "%d (%d)", x->chave, x->sDir - x->sEsq);
        imprimir_preordem(x->esq, arqSaida);
        imprimir_preordem(x->dir, arqSaida);
    }
}

// Libera a memória dos nós
void liberar_no (no *x) {
    if (x != NULL) {
        liberar_no(x->esq);
        liberar_no(x->dir);
        free(x);
    }
}

// Libera a memória dos nós e atribui NULL à raiz
void destruir_arvore (abb *arv) {
    if (arv != NULL) {
        liberar_no(arv->raiz);
        arv->raiz = NULL;
    }
}

// ##################################################### //
// MAIN

int main () {

    // Abre o arquivo e retorna um endereço de memória
    FILE *arqEntrada = fopen("L2Q2.in", "r"); // Ponteiro para o tipo FILE
    FILE *arqSaida = fopen("L2Q2.out", "w"); // Cria o arquivo se não existir

    // Se o arquivo não puder ser aberto, fopen retorna NULL
    if (arqEntrada == NULL || arqSaida == NULL) {
        printf("Os arquivos não podem ser abertos. Verifique se os arquivos, o código-fonte e o executável estão na mesma pasta.\n");
        return EXIT_FAILURE;
    }

    // Declarações
    char linha[dimLinha], *token;
    char del1[] = " ";
    int flag = 0;

    // Lê o arquivo de entrada até o fim, quando fgets retorna NULL
    // Percorre o arquivo
    while (fgets(linha, dimLinha, arqEntrada) != NULL) { 

        // Inicialização da árvore
        abb *arvore = init_arvore();

        // Verificar se a alocação de memória falhou
        if (arvore == NULL) {
            fprintf(arqSaida, "Erro ao alocar memória para a árvore. Pulando para a próxima linha.\n");
            continue; // Passa para a próxima linha sem parar o programa
        }

        if (flag == 1) {
            // Pula uma linha após o primeiro loop e evita pular após o último
            fprintf(arqSaida, "\n");
        }
        
        // Remove o \n (caso exista)
        linha[strcspn(linha, "\n")] = '\0';

        // Pega o primeiro token
        token = strtok(linha, del1);

        // Verifica se o primeiro token está vazio
        if (token == NULL) {  // Caso a linha esteja vazia ou com formato incorreto
            fprintf(arqSaida, "Erro: linha vazia ou inválida.");
            flag = 1;
            continue; // Pula para a próxima linha
        }

        // Lê a linha até encontrar até encontrar o '\0'
        // Percorre uma linha
        while (token != NULL) {
            int chave = atoi(token);
            no *novo = init_no(chave);

            // Falha de alocação
            if (novo == NULL) {
                continue; // Passa para o próximo número
            }

            // Insere o nó
            inserir_no(arvore, novo);

            // Pega o próximo número
            token = strtok(NULL, del1);
        }

        if (arvore->raiz != NULL) {
            calc_somas(arvore->raiz); // Calcula a soma das subárvores à esquerda e direita
            imprimir_preordem(arvore->raiz, arqSaida); 
        }        
    
        // Impede a quebra de linha após a última linha do arquivo
        flag = 1;

        // Libera a memória alocada para as listas após cada linha
        destruir_arvore(arvore);
        arvore = NULL;
    }

    fclose(arqEntrada); // Fecha o arquivo e libera a memória
    fclose(arqSaida); // Fecha o arquivo e libera a memória

    return EXIT_SUCCESS;
}