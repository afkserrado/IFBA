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
    if (node_novo == NULL) {return NULL;}
    node_novo->chave = chave;
    node_novo->ante = NULL;
    node_novo->prox = NULL;
    return node_novo;
}

// Cria e inicializa a lista de inteiros, configurando o head para NULL (lista vazia)
iLista *init_iLista () {
    iLista *LE = malloc(sizeof(iLista));
    if (LE == NULL) {return NULL;}
    LE->cabeca = NULL;
    LE->cauda = NULL;
    return LE;
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
    int status;
} rNode;

// Define a estrutura da lista de reais
typedef struct rLista {
    rNode *cabeca;
    rNode *cauda;
} rLista;

// Cria e inicializa um novo nó na lista de reais, configurando seus ponteiros para NULL
rNode *init_rNode (float chave) {
    rNode *node_novo = malloc(sizeof(rNode)); // Aloca memória para o primeiro nó
    if (node_novo == NULL) {return NULL;}
    node_novo->chave = chave;
    node_novo->prox = NULL;
    node_novo->status = 0;
    return node_novo;
}

// Cria e inicializa a lista de reais, configurando o head para NULL (lista vazia)
rLista *init_rLista () {
    rLista *LI = malloc(sizeof(rLista));
    if (LI == NULL) {return NULL;}
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
        rNode *posterior = LI->cabeca; // Marca a chave imediatamente posterior o node_novo
        rNode *anterior = NULL; // Marca a chave imediatamente anterior ao node_novo
        float chave = node_novo->chave;

        // Encontra a posição correta
        do {
            if (posterior->chave <= chave) {break;}
            anterior = posterior;
            posterior = posterior->prox;
        } while (posterior != LI->cabeca);

        // Caso 1: o node_novo é o primeiro item da lista
        if (posterior == LI->cabeca && anterior == NULL) {
            node_novo->prox = LI->cabeca;
            LI->cabeca = node_novo;
            LI->cauda->prox = LI->cabeca; // Fecha o círculo
        }
        // Caso 2: o node_novo é o último item da lista
        else if (posterior == LI->cabeca && anterior != NULL) {
            node_novo->prox = LI->cabeca;
            LI->cauda->prox = node_novo;
            LI->cauda = node_novo;
        }
        // Caso 3: o node_novo ocupa qualquer posição intermediária da lista
        else {
            node_novo->prox = posterior;
            anterior->prox = node_novo;     
        }
    }
}

// Função para liberar todos os nós da lista principal
void liberar_rLista(rLista *LI) {
    if (LI == NULL) { // Lista vazia
        return;
    }
    
    // Libera memória dos nós da lista
    if (LI->cabeca != NULL) {
        rNode *x = LI->cabeca;
        do {
            rNode *temp = x;
            x = x->prox;
            free(temp); // Libera a memória de cada nó
        } while (x != LI->cabeca);
    }

    free(LI); // Libera a memória da lista, mesmo que ela esteja vazia
}

// ##################################################### //
// FUNÇÕES

void imprimir_lista (iLista *LE, rLista *LI, FILE *arqSaida) {
    
    // Declarações e inicializações
    iNode *x = LE->cabeca; // Inicializa x com a "cabeca" da lista LE
    rNode *inicio = LI->cabeca; // Inicializa y com a "cabeca" da lista LI
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
                // Variáveis auxiliares
                dif = fabs(y->chave - x->chave);

                // Imprime uma chave real da lista LI
                if (dif < 1.0f && y->status == 0) {
                    // Imprime "->" apenas antes de um número real LI
                    if (flag == 1) {fprintf(arqSaida, "->");}
                    // Imprime um número real da lista LI
                    fprintf(arqSaida, "%.2f", y->chave);
                    flag = 1; // Controla a impressão do "->"
                    y->status = 1; // Atualiza o status para evitar repetição
                }
                y = y->prox; // Avança
            } while (y != inicio);
        }

        fprintf(arqSaida, ")");
        x = x->prox; // Avança
        if (x != NULL) {fprintf(arqSaida, "->");}
    }
    fprintf(arqSaida, "]");
}

// ##################################################### //
// MAIN

int main () {

    // Abre o arquivo e retorna um endereço de memória
    FILE *arqEntrada = fopen("L1Q3.in", "r"); // Ponteiro para o tipo FILE
    FILE *arqSaida = fopen("L1Q3.out", "w"); // Cria o arquivo se não existir

    // Se o arquivo não puder ser aberto, fopen retorna NULL
    if (arqEntrada == NULL || arqSaida == NULL) {
        printf("Os arquivos não podem ser abertos. Verifique se os arquivos e o executável estão na mesma pasta.\n");
        return EXIT_FAILURE;
    }

    // Declarações
    char entrada[dimLinha], *token, *subtoken;
    char *saveptr1, *saveptr2; // Ponteiros para salvar o estado
    char del1[] = "LI"; // Delimitador entre "LE" e "LI"
    char del2[] = " "; // Delimitador entre números
    int flag = 0;
    int erroMemoria;

    // Lê o arquivo de entrada até o fim, quando fgets retorna NULL
    // Percorre o arquivo
    while (fgets(entrada, dimLinha, arqEntrada) != NULL) { 

        // Controla erros de alocação de memória
        erroMemoria = 0;

        // Inicialização das listas
        iLista *LE = init_iLista(); // LE = lista externa (inteiros)
        rLista *LI = init_rLista(); // LI = lista interna (reais)

        // Verificar se a alocação de memória falhou
        if (LE == NULL || LI == NULL) {
            fprintf(arqSaida, "Erro ao alocar memória para as listas. Pulando para a próxima linha.\n");
            continue; // Passa para a próxima linha sem parar o programa
        }

        if (flag == 1) {
            // Pula uma linha após o primeiro loop e evita pular após o último
            fprintf(arqSaida, "\n");
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
            fprintf(arqSaida, "Erro: linha vazia ou inválida.");
            liberar_iLista(LE);
            liberar_rLista(LI);
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

            // Falha de alocação de memória
            if (temp == NULL) {
                liberar_iLista(LE);
                liberar_rLista(LI);
                fprintf(arqSaida, "Falha de alocação de memória.");
                flag = 1;
                erroMemoria = 1;
                break; // Sai do while interno e vai para próxima linha
            }

            inserir_iNode_ordenado(LE, temp);

            // Pega o próximo subtoken (inteiro) da lista LE
            subtoken = strtok_r(NULL, del2, &saveptr2);
        }

        if (erroMemoria == 1) {continue;}

        // ##################################################### //
        // LI (REAIS)

        // Pega a lista LI (reais)
        token = strtok_r(NULL, del1, &saveptr1); // Pega o restante após "LI"

        // Verifica se a lista LI está vazia
        if (token == NULL) {  // Caso a LI esteja vazia ou com formato incorreto
            fprintf(arqSaida, "Erro: linha vazia ou inválida.");
            liberar_iLista(LE);
            liberar_rLista(LI);
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

            // Falha de alocação de memória
            if (temp == NULL) {
                liberar_iLista(LE);
                liberar_rLista(LI);
                fprintf(arqSaida, "Falha de alocação de memória.");
                flag = 1;
                erroMemoria = 1;
                break; // Sai do while interno e vai para próxima linha
            }

            inserir_rNode_ordenado(LI, temp);

            // Pega o próximo subtoken (float) da lista LI
            subtoken = strtok_r(NULL, del2, &saveptr2);
        }

        if (erroMemoria == 1) {continue;}
        
        // Impede a quebra de linha após a última linha do arquivo
        flag = 1;

        // Imprime os resultados
        imprimir_lista(LE, LI, arqSaida);

        // Libera a memória alocada para as listas após cada linha
        liberar_iLista(LE);
        liberar_rLista(LI);
    }

    fclose(arqEntrada); // Fecha o arquivo e libera a memória
    fclose(arqSaida); // Fecha o arquivo e libera a memória
    return EXIT_SUCCESS;
}