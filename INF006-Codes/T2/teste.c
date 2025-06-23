/*
Instituto Federal da Bahia (IFBA)
Tecnólogo em Análise e Desenvolvimento de Sistemas (ADS)
Semestre 2025.1
INF006 - Estrutura de Dados e Algoritmos
Professor: José Dihego
Aluno: Anderson Serrado
T2Q3
*/

// ##################################################### //
// BIBLIOTECAS

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>
#include <math.h>

// ##################################################### //
// CONSTANTES

#define dimLinha 3001 // 3000 caracteres + 1 para o \0

// ##################################################### //
// LISTA DUPLAMENTE ENCADEADA LINEAR: LE (INTEIROS)

// Define a estrutura de um nó da lista de inteiros (LE)
typedef struct iNode {
    int chave;
    struct iNode *ante; 
    struct iNode *prox;
} iNode;

// Define a estrutura da lista duplamente encadeada de inteiros
typedef struct iLista {
    iNode *cabeca;
    iNode *cauda;
} iLista;

// Cria e inicializa um novo nó na lista de inteiros, configurando seus ponteiros para NULL
iNode *init_iNode (int chave) {
    iNode *node_novo = malloc(sizeof(iNode)); // Aloca memória para o primeiro nó
    node_novo->chave = chave;
    node_novo->ante = NULL;
    node_novo->prox = NULL;
    return node_novo;
}

// Cria e inicializa a lista de inteiros, configurando o head para NULL (lista vazia)
iLista *init_iLista () {
    iLista *lista = malloc(sizeof(iLista));
    lista->cabeca = NULL;
    lista->cauda = NULL;
    return lista;
}

// Insere um nó na lista de inteiros, na posição ordenada (ordem crescente)
void inserir_iNode_ordenado (iLista *LE, iNode *node_novo) {
    // LE vazia
    if (LE->cabeca == NULL) {
        LE->cabeca = node_novo;
        LE->cauda = node_novo;
    }
    // LE não vazia
    else {
        
        // Variáveis temporárias
        iNode *x = LE->cabeca;
        int chave = node_novo->chave;

        // Encontra a posição correta
        while (x != NULL && x->chave < chave) {
            x = x->prox;
        }

        // Caso 1: o node_novo é o primeiro item da LE
        if (x == LE->cabeca) {
            node_novo->prox = LE->cabeca;
            node_novo->ante = NULL;
            LE->cabeca->ante = node_novo;
            LE->cabeca = node_novo;
        }
        // Caso 2: o node_novo é o último item da LE
        else if (x == NULL) {
            node_novo->prox = NULL;
            node_novo->ante = LE->cauda;
            LE->cauda->prox = node_novo;
            LE->cauda = node_novo;
        }
        // Caso 3: o node_novo ocupa qualquer posição intermediária da LE
        else {
            node_novo->ante = x->ante;
            node_novo->prox = x;
            x->ante->prox = node_novo;
            x->ante = node_novo;      
        }
    }
}

void imprimir_iLista_arq (iLista *LE, FILE *arqSaida) {
    iNode *x = LE->cabeca; // Inicializa x com a "cabeca" da lista
    fprintf(arqSaida, "LE");
    while (x != NULL) {
        fprintf(arqSaida, " %d", x->chave);     
        x = x->prox;
    }
}

void imprimir_iLista (iLista *LE) {
    iNode *x = LE->cabeca; // Inicializa x com a "cabeca" da lista
    printf("LE: ");
    while (x != NULL) {
        printf("%d ", x->chave);     
        x = x->prox;
    }
    printf("\n");
}

// Função para liberar todos os nós da lista principal
void liberar_iLista(iLista *lista) {
    iNode *x = lista->cabeca;
    while (x != NULL) {
        iNode *temp = x;
        x = x->prox;
        free(temp); // Libera a memória de cada nó
    }
    free(lista); // Libera a memória da lista
}

// ##################################################### //
// LISTA SIMPLESMENTE LIGADA CIRCULAR: LI (REAIS)

// Define a estrutura de um nó da lista de reais (LI)
typedef struct rNode {
    float chave;
    struct rNode *prox;
} rNode;

// Define a estrutura da lista de reais
typedef struct rLista {
    rNode *cabeca;
    rNode *cauda;
} rLista;

// Cria e inicializa um novo nó na lista de reais, configurando seus ponteiros para NULL
rNode *init_rNode (float chave) {
    rNode *node_novo = malloc(sizeof(rNode)); // Aloca memória para o primeiro nó
    node_novo->chave = chave;
    node_novo->prox = NULL;
    return node_novo;
}

// Cria e inicializa a lista de reais, configurando o head para NULL (lista vazia)
rLista *init_rLista () {
    rLista *LI = malloc(sizeof(rLista));
    LI->cabeca = NULL;
    LI->cauda = NULL;
    return LI;
}

// Insere um novo nó na lista de reais, na posição ordenada (ordem decrescente)
void inserir_rNode_ordenado (rLista *LI, rNode *node_novo) {
    // Lista vazia
    if (LI->cabeca == NULL) {
        LI->cabeca = node_novo;
        LI->cauda = node_novo;
        node_novo->prox = LI->cabeca; // O novo nó aponta para ele mesmo, fechando o círculo
    }
    // Lista não vazia
    else {
        
        // Variáveis temporárias
        rNode *x = LI->cabeca; // Marca a chave imediatamente posterior o node_novo
        rNode *y = NULL; // Marca a chave imediatamente anterior ao node_novo
        float chave = node_novo->chave;

        // Encontra a posição correta
        while (x != NULL && x->chave > chave) {
            y = x;
            x = x->prox;
        }

        // Caso 1: o node_novo é o primeiro item da lista
        if (x == LI->cabeca) {
            node_novo->prox = LI->cabeca;
            LI->cabeca = node_novo;
            LI->cauda->prox = LI->cabeca; // Fecha o círculo
        }
        // Caso 2: o node_novo é o último item da lista
        else if (x == NULL) {
            node_novo->prox = LI->cabeca;
            LI->cauda->prox = node_novo;
            LI->cauda = node_novo;
        }
        // Caso 3: o node_novo ocupa qualquer posição intermediária da lista
        else {
            node_novo->prox = x;
            y->prox = node_novo;     
        }
    }
}

// Remover um nó qualquer da lista de reais
void remover_rNode (rLista *LI, float chave) {
    
    if (LI->cabeca == NULL) { // Lista vazia
        return;
    }
    
    // Inicializa as variáveis temporárias
    rNode *atual = LI->cabeca; 
    rNode *anterior = LI->cauda; // Nó anterior ao atual

    // Pesquisa a chave
    do { 
        if (atual->chave == chave) {
            break;
        }
        anterior = atual;
        atual = atual->prox;
    } while (atual != LI->cabeca);

    // Remove o nó encontrado
    if (atual->chave == chave) { // Chave encontrada
        if (atual == LI->cabeca) { // Se for o primeiro nó
            if (atual->prox == atual) { // Se a lista só possuir um nó
                LI->cabeca = NULL;
                LI->cauda = NULL;
            }
            else { // Se a lista possuir mais de um nó
                LI->cabeca = atual->prox;
                LI->cauda->prox = LI->cabeca; // Fecha o círculo
            }
        }
        else { // Se não for o primeiro nó
            if (atual == LI->cauda) { // Se for o último nó
                LI->cauda = anterior;
                LI->cauda->prox = LI->cabeca; // Fecha o círculo
            }
            else { // Elemento intermediário
                anterior->prox = atual->prox;
            }
        }
        free(atual); // Libera a memória do nó removido
    }
}

void imprimir_rLista_arq (rLista *LI, FILE *arqSaida) {
    rNode *x = LI->cabeca; // Inicializa x com a "cabeca" da lista
    fprintf(arqSaida, "LI");
    do {
        fprintf(arqSaida, " %.2f", x->chave);     
        x = x->prox;
    } while (x != LI->cabeca);
}

void imprimir_rLista (rLista *LI) {
    if (LI == NULL || LI->cabeca == NULL) {
        printf("LI: vazia\n");
        return;
    }
    
    rNode *x = LI->cabeca; // Inicializa x com a "cabeca" da lista
    printf("LI: ");
    do {
        printf("Atual: %.2f ", x->chave);     
        x = x->prox;
        printf("Prox: %.2f |", x->chave); 
    } while (x != LI->cabeca);
    printf("\n");
}

// Função para liberar todos os nós da lista principal
void liberar_rLista(rLista *LI) {   
    if (LI == NULL || LI->cabeca == NULL) {
        printf("LI: vazia\n");
        return;
    }
    
    rNode *x = LI->cabeca;
    do {
        rNode *temp = x;
        x = x->prox;
        free(temp); // Libera a memória de cada nó
    } while (x != LI->cabeca);
    free(LI); // Libera a memória da lista
}

// ##################################################### //
// FUNÇÕES

void imprimir_lista (iLista *LE, rLista *LI, FILE *arqSaida) {
    iNode *x = LE->cabeca; // Inicializa x com a "cabeca" da lista LE
    
    float dif; // Diferença
    int flag;
    
    fprintf(arqSaida, "[");
    while (x != NULL) {
        fprintf(arqSaida, "%d", x->chave);
        fprintf(arqSaida, "(");
        flag = 0;

        rNode *y = LI->cabeca; // Inicializa y com a "cabeca" da lista LI

        // Verifica se a lista não está vazia
        if (y != NULL) {
            do {
                rNode *temp = NULL;
                dif = fabs(y->chave - x->chave);

                // Imprime uma chave real da lista LI
                if (dif < 1.0f) {
                    // Imprime "->" apenas antes de um número real LI
                    if (flag == 1) {fprintf(arqSaida, "->");}
                    // Imprime um número real da lista LI
                    fprintf(arqSaida, "%.2f", y->chave);
                    flag = 1; 
                    temp = y; 
                }

                y = y->prox; // Incrementa

                // Remove uma chave real já impressa da lista LI
                if (temp != NULL) {
                    remover_rNode(LI,10.10);
                    imprimir_rLista(LI);
                }
            } while (y != LI->cabeca && LI->cabeca != NULL);
        }

        fprintf(arqSaida, ")");
        x = x->prox; // Incrementa
        if (x != NULL) {fprintf(arqSaida, "->");}
    }
    fprintf(arqSaida, "]");
}

// ##################################################### //
// MAIN

int main () {

    // Abre o arquivo e retorna um endereço de memória
    FILE *arqEntrada = fopen("L1Q3.in", "r"); // Ponteiro para o tipo FILE

    // Se o arquivo não puder ser aberto, fopen retorna NULL
    if (arqEntrada == NULL) {
        printf("Os arquivos não podem ser abertos. Verifique se os arquivos e o executável estão na mesma pasta.\n");
        return EXIT_FAILURE;
    }

    // Declarações
    char entrada[dimLinha], *token, *subtoken;
    char *saveptr1, *saveptr2; // Ponteiros para salvar o estado
    char del1[] = "LI"; // Delimitador entre "LE" e "LI"
    char del2[] = " "; // Delimitador entre números
    int flag = 0;

    // Lê o arquivo de entrada até o fim, quando fgets retorna NULL
    // Percorre o arquivo
    while (fgets(entrada, dimLinha, arqEntrada) != NULL) { 

        // Inicialização das listas
        iLista *LE = init_iLista(); // LE = lista externa (inteiros)
        rLista *LI = init_rLista(); // LI = lista interna (reais)

        // Verificar se a alocação de memória falhou
        if (LE == NULL || LI == NULL) {
            continue; // Passa para a próxima linha sem parar o programa
        }

        if (flag == 1) {
            // Pula uma linha após o primeiro loop e evita pular após o último
        }
        
        // Limpa a entrada
        entrada[strcspn(entrada, "\n")] = '\0'; // Remove o \n (caso exista)
        char *linha = entrada + 3; // Pula o "LE "
        
        // ##################################################### //
        // LE (INTEIROS)

        // Pega a lista LE (inteiros)
        token = strtok_r(linha, del1, &saveptr1); // Até encontrar "LI"

        // Verifica se a lista LE está vazia
        if (token == NULL) {  // Caso a LE esteja vazia ou com formato incorreto
            flag = 1;
            continue; // Pula para a próxima linha
        }

        // Pega o primeiro subtoken (inteiro) da lista LE
        subtoken = strtok_r(token, del2, &saveptr2);

        // Pega os demais subtokens (inteiros) da lista LE 
        while (subtoken != NULL) {
            // Insere um novo nó na lista de inteiros
            int num = atoi(subtoken);
            iNode *temp = init_iNode(num);
            inserir_iNode_ordenado(LE, temp);

            // Pega o próximo subtoken (inteiro) da lista LE
            subtoken = strtok_r(NULL, del2, &saveptr2);
        }

        // ##################################################### //
        // LI (REAIS)

        // Pega a lista LI (reais)
        token = strtok_r(NULL, del1, &saveptr1); // Pega o restante após "LI"

        // Verifica se a lista LI está vazia
        if (token == NULL) {  // Caso a LI esteja vazia ou com formato incorreto
            flag = 1;
            continue; // Pula para a próxima linha
        }
        
        // Pega o primeiro subtoken (real) da lista LI
        subtoken = strtok_r(token, del2, &saveptr2);

        // Pega os demais subtokens (reais) da lista LI
        while (subtoken != NULL) {       
            // Insere um novo nó na lista de reais
            float num = atof(subtoken);
            rNode *temp = init_rNode(num);
            inserir_rNode_ordenado(LI, temp);

            // Pega o próximo subtoken (float) da lista LI
            subtoken = strtok_r(NULL, del2, &saveptr2);
        }
        
        // Impede a quebra de linha após a última linha do arquivo
        flag = 1;

        float num;
        printf("Nova linha: \n");
        imprimir_iLista(LE);
        imprimir_rLista(LI);
        num = 4.99;
        printf("Após remoção: do %.2f\n", num);
        remover_rNode(LI, num);
        imprimir_iLista(LE);
        imprimir_rLista(LI);
        num = 4.33;
        printf("Após remoção: do %.2f\n", num);
        remover_rNode(LI, num);
        imprimir_iLista(LE);
        imprimir_rLista(LI);
        num = 4.11;
        printf("Após remoção: do %.2f\n", num);
        remover_rNode(LI, num);
        imprimir_iLista(LE);
        imprimir_rLista(LI);
        num = 6.88;
        printf("Após remoção: do %.2f\n", num);
        remover_rNode(LI, num);
        imprimir_iLista(LE);
        imprimir_rLista(LI);
        num = 6.15;
        printf("Após remoção: do %.2f\n", num);
        remover_rNode(LI, num);
        imprimir_iLista(LE);
        imprimir_rLista(LI);
        num = 9.30;
        printf("Após remoção: do %.2f\n", num);
        remover_rNode(LI, num);
        imprimir_iLista(LE);
        imprimir_rLista(LI);
        num = 9.20;
        printf("Após remoção: do %.2f\n", num);
        remover_rNode(LI, num);
        imprimir_iLista(LE);
        imprimir_rLista(LI);
        num = 10.10;
        printf("Após remoção: do %.2f\n", num);
        remover_rNode(LI, num);
        imprimir_iLista(LE);
        imprimir_rLista(LI);
        printf("\n");

        // Libera a memória alocada para as listas após cada linha
        liberar_iLista(LE);
        liberar_rLista(LI);
    }

    fclose(arqEntrada); // Fecha o arquivo e libera a memória
    return EXIT_SUCCESS;
}