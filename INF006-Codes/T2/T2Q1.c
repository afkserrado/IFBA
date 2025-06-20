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

// ##################################################### //
// CONSTANTES

#define dimLinha 3001 // 3000 caracteres + 1 para o \0

// ##################################################### //
// LISTAS DUPLAMENTE ENCADEADAS

// Define a estrutura de um nó da lista principal
typedef struct pNode {
    int chave;
    struct pNode *ante; 
    struct pNode *prox;
    struct sNode *ramo;
} pNode;

// Define a estrutura de um nó da lista secundária
typedef struct sNode {
    int chave;
    int id; // Marca o início de um start
    struct sNode *ante; 
    struct sNode *prox;
} sNode;

// Define a estrutura da lista duplamente encadeada principal
typedef struct pLista {
    pNode *cabeca;
    pNode *cauda;
} pLista;

// Define a estrutura da lista duplamente encadeada secundária
typedef struct sLista {
    sNode *cabeca;
    sNode *cauda;
} sLista;

// Cria e inicializa um novo nó na lista principal, configurando seus ponteiros para NULL
pNode *init_pNode (int chave) {
    pNode *node_novo = malloc(sizeof(pNode)); // Aloca memória para o primeiro nó
    node_novo->chave = chave;
    node_novo->ante = NULL;
    node_novo->prox = NULL;
    return node_novo;
}

// Cria e inicializa um novo nó na lista secundária, configurando seus ponteiros para NULL
sNode *init_sNode (int chave) {
    sNode *node_novo = malloc(sizeof(sNode)); // Aloca memória para o primeiro nó
    node_novo->chave = chave;
    node_novo->id = -1;
    node_novo->ante = NULL;
    node_novo->prox = NULL;
    return node_novo;
}

// Cria e inicializa a lista principal, configurando o head para NULL (lista vazia)
pLista *init_pLista () {
    pLista *lista = malloc(sizeof(pLista));
    lista->cabeca = NULL;
    lista->cauda = NULL;
    return lista;
}

// Cria e inicializa a lista secundária, configurando o head para NULL (lista vazia)
sLista *init_sLista () {
    sLista *lista = malloc(sizeof(sLista));
    lista->cabeca = NULL;
    lista->cauda = NULL;
    return lista;
}

// Insere um novo nó na lista principal, na posição ordenada (ordem decrescente)
void inserir_pNode_ordenado (pLista *lista, pNode *node_novo) {
    // Lista vazia
    if (lista->cabeca == NULL) {
        lista->cabeca = node_novo;
        lista->cauda = node_novo;
    }
    // Lista não vazia
    else {
        
        // Variáveis temporárias
        pNode *x = lista->cabeca;
        int chave = node_novo->chave;

        // Encontra a posição correta
        while (x != NULL && x->chave >= chave) {
            x = x->prox;
        }

        /*
        Obs.: o ">=" mantém a ordem relativa dos elementos com a mesma chave (soma). Isso garante que, se existirem n nós consecutivos com a mesma chave na lista principal, apenas o último seja exibido, já que os nós da lista principal já são inseridos de forma decrescente.
        */

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

// Insere um novo nó na lista principal, na posição ordenada (ordem crescente)
void inserir_sNode_ordenado (sLista *lista, sNode *node_novo) {
    // Lista vazia
    if (lista->cabeca == NULL) {
        lista->cabeca = node_novo;
        lista->cauda = node_novo;
    }
    // Lista não vazia
    else {
        
        // Variáveis temporárias
        sNode *x = lista->cabeca;
        int chave = node_novo->chave;
        int id = node_novo->id;

        // Encontra a posição correta
        while (x != NULL && x->chave < chave && x->id == id) {
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

// Imprime as listas
void imprimir_listas (pLista *lsp, sLista *lss, FILE *arqSaida) {
    
    int flag = 0;
    pNode *x = lsp->cabeca; // Inicializa x com a "cabeca" da lista

    while (x != NULL) {
        if (x->prox != NULL && x->chave == x->prox->chave) {
            x = x->prox; // Pula a chave atual
            continue; // Avança o loop
        }

        // Insere um espaço entre as sublistas, exceto no final da linha
        if (flag == 1) {fprintf(arqSaida, " ");}
     
        fprintf(arqSaida, "start");

        sNode *y = x->ramo;
        int id = y->id;
        while (y != NULL && y->id == id) {
            fprintf(arqSaida, " %d", y->chave);
            y = y->prox;
        }
        flag = 1;
        x = x->prox;
    }
}

// Função para liberar todos os nós da lista principal
void liberar_pLista(pLista *lista) {
    pNode *x = lista->cabeca;
    while (x != NULL) {
        pNode *temp = x;
        x = x->prox;
        free(temp); // Libera a memória de cada nó
    }
    free(lista); // Libera a memória da lista
}

// Função para liberar todos os nós da lista
void liberar_sLista(sLista *lista) {
    sNode *x = lista->cabeca;
    while (x != NULL) {
        sNode *temp = x;
        x = x->prox;
        free(temp); // Libera a memória de cada nó
    }
    free(lista); // Libera a memória da lista
}

// ##################################################### //
// MAIN

int main () {

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
    int flag = 0;
    int id = 0;

    // Lê o arquivo de entrada até o fim, quando fgets retorna NULL
    // Percorre o arquivo
    while (fgets(linha, dimLinha, arqEntrada) != NULL) { 

        // Inicialização das listas
        pLista *lsp = init_pLista(); // lsp = lista principal
        sLista *lss = init_sLista(); // lss = lista secundária

        // Verificar se a alocação de memória falhou
        if (lsp == NULL || lss == NULL) {
            fprintf(arqSaida, "Erro ao alocar memória para as listas. Pulando para a próxima linha.\n");
            continue; // Passa para a próxima linha sem parar o programa
        }

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

            // Verifica se o primeiro subtoken (número) está vazio
            if (subtoken == NULL) {  // Caso a linha esteja vazia ou com formato incorreto
                continue; // Pula para o próximo número
            }

            // Inicializa as variáveis auxiliares
            int num = atoi(subtoken);
            int soma = 0;

            // Separa os números (subtokens) do token (substring)
            while (subtoken != NULL) {
                sNode *sTemp = init_sNode(num);
                sTemp->id = id;

                // Tratamento de  erros
                if (sTemp == NULL) {
                    continue;
                }

                inserir_sNode_ordenado(lss, sTemp);

                // Tratamento de erros: overflow da soma
                if (soma > INT_MAX) {soma = INT_MAX;}

                soma += num;

                subtoken = strtok_r(NULL, del2, &saveptr2);  // Busca o próximo número
                if (subtoken != NULL) {num = atoi(subtoken);}
            }
            id++;
            pNode *pTemp = init_pNode(soma);
            pTemp->ramo = lss->cabeca;

            // Tratamento de  erros
            if (pTemp == NULL) {
                continue;
            }

            inserir_pNode_ordenado(lsp, pTemp);
            token = strtok_r(NULL, del1, &saveptr1);
        }

        imprimir_listas(lsp, lss, arqSaida);
        
        // Impede a quebra de linha após a última linha do arquivo
        flag = 1;

        // Libera a memória alocada para as listas após cada linha
        liberar_pLista(lsp);
        liberar_sLista(lss);
    }

    fclose(arqEntrada); // Fecha o arquivo e libera a memória
    fclose(arqSaida); // Fecha o arquivo e libera a memória
    return EXIT_SUCCESS;
}