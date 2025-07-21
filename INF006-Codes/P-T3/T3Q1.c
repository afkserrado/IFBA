// ##################################################### //
// OBSERVAÇÕES
// - As entradas são números naturais.
// - Cada linha do arquivo de entrada é uma entrada.
// - O número mı́nimo de arestas de um nó a raiz define a altura deste nó. 

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
    int nivel;
    int index;
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
    novo->nivel = 0;
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

    // Falha de alocação
    if (arv == NULL || novo == NULL) {
        printf("Erro: árvore ou nó inválidos.\n");
        return;
    }

    // Busca a posição do novo nó na árvore
    while (atual != NULL) {
        mae = atual; // Salva a mãe

        if (novo->chave < atual->chave) { // Anda para a esquerda do atual nó
            atual = atual->esq;
        }
        else {
            atual = atual->dir; // Anda para a direita do atual nó
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

    // Calcula o nível do novo nó
    // Nó novo é a raiz
    if (novo->mae == NULL) {
        novo->nivel = 0;
    }
    // Nó novo não é a raiz
    else {
        novo->nivel = novo->mae->nivel + 1;
    }
}

// Busca o nó com maior chave
no *maximo (no *x) {
    if (x == NULL) return NULL;

    while (x->dir != NULL) {
        x = x->dir;
    }
    return x; // Nó correspondente à chave máxima
}

// Libera a memória dos nós
void liberar_no(no *x) {
    if (x != NULL) {
        liberar_no(x->esq);
        liberar_no(x->dir);
        free(x);
    }
}

// Libera a memória dos nós e atribui NULL à raiz
void destruir_arvore(abb *arv) {
    if (arv != NULL) {
        liberar_no(arv->raiz);
        arv->raiz = NULL;
    }
}

// ##################################################### //
// MAIN

int main () {

    // Abre o arquivo e retorna um endereço de memória
    FILE *arqEntrada = fopen("L2Q1.in", "r"); // Ponteiro para o tipo FILE
    FILE *arqSaida = fopen("L2Q1.out", "w"); // Cria o arquivo se não existir

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
        no *novo = NULL;

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
            novo = init_no(chave);

            // Falha de alocação
            if (novo == NULL) {
                fprintf(arqSaida, " ");
                continue; // Passa para o próximo número
            }

            // Insere o nó
            inserir_no(arvore, novo);
            
            // Imprime o nível do nó
            if (novo->mae != NULL) {fprintf(arqSaida, " ");}
            fprintf(arqSaida, "%d", novo->nivel);

            // Pega o próximo número
            token = strtok(NULL, del1);
        }
        
        // Recupera o nó com a chave máxima
        no* max = maximo(arvore->raiz);

        if (max == NULL) {
            fprintf(arqSaida, " max NaN alt NaN pred NaN");
        }
        else {
            // Imprime a chave máxima e seus dados
            fprintf(arqSaida, " max %d alt %d pred ", max->chave, max->nivel);

            if (max->mae == NULL) {
                fprintf(arqSaida, "NaN");
            }
            else {
                fprintf(arqSaida, "%d", max->mae->chave);
            }
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