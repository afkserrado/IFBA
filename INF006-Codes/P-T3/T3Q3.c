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
// - Saída em ordem.

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

// Busca recursiva na ABB
no *buscar_no (no *raiz, int chave) {
    // Árvore vazia ou apenas com a raiz
    if (raiz == NULL || raiz->chave == chave) {
        return raiz;
    }

    if (chave < raiz->chave) { // Anda para a esquerda da raiz
        return buscar_no(raiz->esq, chave);
    }
    else {
        return buscar_no(raiz->dir, chave); // Anda para a direita da raiz
    }
}

// Encontra o nó com o menor valor em uma subárvore
no *minimo (no *min) {
    while (min->esq != NULL) {
        min = min->esq;
    }
    return min;
}

// Exclui um nó da árvore
void remover_no (abb *arvore, int chave) {
    no *alvo = buscar_no(arvore->raiz, chave); // Busca o nó a remover

    // Chave não encontrada
    if (alvo == NULL) {
        inserir_no(arvore, init_no(chave));
        return;
    }

    // Chave encontrada
    no *no_removido; // Aponta para o nó que de fato será removido

    // Casos 1 e 2: alvo não tem filho ou tem apenas um filho
    if (alvo->esq == NULL || alvo->dir == NULL) {
        no_removido = alvo;
    // Caso 3: alvo tem dois filhos
    } else {
        // Encontra o sucessor em ordem (menor chave da subárvore à direita do alvo)
        no_removido = minimo(alvo->dir);
    }

    no *filho_do_removido; // Aponta para o filho de no_removido (nó a ser removido) ou para NULL
    if (no_removido->esq != NULL) {
        // Caso 2: no_removido tem apenas filho à esquerda → filho_do_removido será no_removido->esq
        filho_do_removido = no_removido->esq;
    } else {
        // Caso 1: no_removido não tem filhos → no_removido->dir == NULL → filho_do_removido será NULL
        // Caso 2: no_removido tem apenas filho à direita → filho_do_removido será no_removido->dir
        // Caso 3: no_removido (sucessor em ordem) não tem filhos ou tem apenas filho à direita → filho_do_removido será no_removido->dir
        filho_do_removido = no_removido->dir;
    }

    // Se o filho existe, atualiza a sua mãe
    if (filho_do_removido != NULL) {
        filho_do_removido->mae = no_removido->mae;
    }

    // Se o nó a remover for a raiz da árvore
    if (no_removido->mae == NULL) {
        arvore->raiz = filho_do_removido;
    }
    // Se ele for filho da esquerda
    else if (no_removido == no_removido->mae->esq) {
        no_removido->mae->esq = filho_do_removido;
    }
    // Se for filho da direita
    else {
        no_removido->mae->dir = filho_do_removido;
    }

    // Se o nó removido for o sucessor em ordem, copia sua chave no lugar do alvo
    if (no_removido != alvo) {
        alvo->chave = no_removido->chave;
    }

    // Libera memória do nó removido
    free(no_removido);
}

// Calcula o nível (profundidade) dos nós
void calc_nivel (no *x, int nivel) {
    if (x != NULL) {
        x->nivel = nivel;
        calc_nivel(x->esq, nivel + 1);
        calc_nivel(x->dir, nivel + 1);
    }
}

// Impressão dos nós em ordem
void imprimir_ordem (no *x, FILE *arqSaida, int *flagImp) {
    if (x != NULL) {
        // Imprime em pré-ordem
        imprimir_ordem(x->esq, arqSaida, flagImp);

        // Delimitador
        if (*flagImp == 1) {fprintf(arqSaida, " ");}
        fprintf(arqSaida, "%d (%d)", x->chave, x->nivel);
        *flagImp = 1;

        imprimir_ordem(x->dir, arqSaida, flagImp);
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
    FILE *arqEntrada = fopen("L2Q3.in", "r"); // Ponteiro para o tipo FILE
    FILE *arqSaida = fopen("L2Q3.out", "w"); // Cria o arquivo se não existir

    // Se o arquivo não puder ser aberto, fopen retorna NULL
    if (arqEntrada == NULL || arqSaida == NULL) {
        printf("Os arquivos não podem ser abertos. Verifique se os arquivos, o código-fonte e o executável estão na mesma pasta.\n");
        return EXIT_FAILURE;
    }

    // Declarações
    char linha[dimLinha], *op, *num;
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

        // Pega os dois primeiros tokens (operação e número)
        op = strtok(linha, del1);
        num = strtok(NULL, del1);

        // Linhga vazia ou com formato incorreto
        if (op == NULL || num == NULL) {
            fprintf(arqSaida, "Erro: linha vazia ou inválida.");
            flag = 1;
            continue; // Pula para a próxima linha
        }

        // Lê a linha até encontrar até encontrar o '\0'
        // Percorre uma linha
        while (op != NULL && num != NULL) {
            int chave = atoi(num);
            no *novo = init_no(chave);

            // Falha de alocação
            if (novo == NULL) {
                continue; // Passa para o próximo número
            }

            // Insere o nó
            if (strcmp(op, "a") == 0) { // Operação de adição
                inserir_no(arvore, novo);
            }
            else {
                remover_no(arvore, chave);
            }        

            // Pega o próximo número
            op = strtok(NULL, del1);
            num = strtok(NULL, del1);
        }

        if (arvore->raiz != NULL) {
            int flagImp = 0;
            calc_nivel(arvore->raiz, 0);
            imprimir_ordem(arvore->raiz, arqSaida, &flagImp); 
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