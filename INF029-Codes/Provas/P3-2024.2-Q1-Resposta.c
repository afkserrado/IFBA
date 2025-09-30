#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Estrutura do nó da lista secundária
typedef struct no2 {
    int chave;
    struct no2 *ante; // Ponteiro para o nó anterior ao atual
    struct no2 *prox; // Ponteiro para o nó posterior ao atual
} no2;

// Estrutura do nó da lista principal
typedef struct no1 {
    int posicao;
    int tamanho;
    struct no2 *endereco;
    struct no1 *prox;
} no1;

// Estrutura da lista principal
typedef struct l1 {
    struct no1 *cabeca; // Ponteiro para o primeiro elemento da lista
    struct no1 *cauda;
} l1;

// Estrutura da lista secundária
typedef struct l2 {
    struct no2 *cabeca; // Ponteiro para o primeiro elemento da lista
    struct no2 *cauda;
} l2;

// Cria um nó do tipo no1
no1 *init_no1 (int posicao, int tamanho) {
    no1 *novo = (no1 *)malloc(sizeof(no1)); // Aloca memória para o nó

    if (novo == NULL) { // Verifica se houve falha ao alocar memória
        printf("Falha de alocação.\n");
        return NULL;
    }

    // Inicialização
    novo->posicao = posicao;
    novo->tamanho = tamanho;
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
    lista->cauda = NULL;

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
    lista->cauda = NULL;

    return lista; // Retorna a lista
}

typedef struct dados {
    int posicao;
    int tam;
    int qtd;
    int numeros[100];
} dados;

#define tamLinha 100

void lerArquivo (FILE *arqEntrada, dados entrada[10], int *indice) {
    
    if (*indice >= 10) {
        printf("Limite de subestruturas alcançado.\n");
        return;
    }
    
    char linha[tamLinha];
    
    // Lê uma linha do arquivo
    if (fgets(linha, tamLinha, arqEntrada) == NULL) {
        return; // Condição de parada da recursão: fim do arquivo
    }

    // Remove o caractere de quebra de linha, se houver
    linha[strcspn(linha, "\n")] = '\0';

    sscanf(linha, "%d;%d", &entrada[*indice].posicao, &entrada[*indice].tam);

    char *token;
    char del[] = ";"; // Delimitador
    int i = 0;
    entrada[*indice].qtd = 0; // Inicializa a quantidade de números

    token = strtok(linha, del); // Lê o primeiro token (posição) e ignora
    token = strtok(NULL, del); // Lê o segundo token (tamanho) e ignora
    token = strtok(NULL, del); // Lê o primeiro elemento da subestrutura

    while (token != NULL && i < entrada[*indice].tam) { // Percorre a linha
        entrada[*indice].numeros[i++] = atoi(token);
        entrada[*indice].qtd++;
        token = strtok(NULL, ";");
    }

    (*indice)++;

    // Recursão para percorrer o arquivo
    lerArquivo(arqEntrada, entrada, indice);
}

void gerarEstruturaNova (dados entrada[10], l1 *lista1, l2 *lista2, int indice) {
    
    // Percorre o array de structs
    for (int i = 0; i < indice; i++) {
        no1 *x = init_no1(entrada[i].posicao, entrada[i].tam); // Cria um nó
        
        // Verifica se a alocação foi bem-sucedida
        if (x == NULL) {
            printf("Erro ao alocar memória para o nó da lista principal.\n");
            return;
        }

        // Insere o novo nó na lista principal
        // Lista vazia
        if (lista1->cabeca == NULL) { 
            // Inicializa a lista principal
            lista1->cabeca = x;
            lista1->cauda = x;          
        }
        // Lista principal não vazia
        else { 
            // Insere o novo nó no final
            lista1->cauda->prox = x;
            lista1->cauda = x;
        }

        // Verifica se o arquivo de entrada possui dados para a subestrutura
        if (entrada[i].qtd > 0) {
            // Cria o primeiro nó da lista auxiliar
            int j = 0;
            no2 *y = init_no2(entrada[i].numeros[j++]);

            // Verifica se a alocação foi bem-sucedida
            if (y == NULL) {
                printf("Erro ao alocar memória para o nó da lista auxiliar.\n");
                return;
            }
            
            // Inicializa a lista auxiliar
            lista2->cabeca = y;
            lista2->cauda = y;

            // Vincula as listas principal e auxiliar
            x->endereco = lista2->cabeca;

            // Cria os demais nós: insere no final
            for (; j < entrada[i].qtd; j++) {
                
                // Evita que ultrapasse o limite de 100 números na lista auxiliar
                if (j >= 100) {  
                    printf("Erro: quantidade de números excede o limite da lista auxiliar.\n");
                    return;
                }

                no2 *z = init_no2(entrada[i].numeros[j]); // Cria um nó

                // Verifica se a alocação foi bem-sucedida
                if (z == NULL) {
                    printf("Erro ao alocar memória para o nó da lista auxiliar.\n");
                    return;
                }

                // Insere o novo nó no final
                lista2->cauda->prox = z;
                z->ante = lista2->cauda;
                lista2->cauda = z;
            }
        }     
    }
}

void exibirListasAuxiliares(l1 *lista1) {
    no1 *atual1 = lista1->cabeca;  // Percorre os nós da lista principal (l1)

    // Percorre cada nó da lista principal
    while (atual1 != NULL) {
        printf("Lista auxiliar do nó com posicao %d e tamanho %d:\n", atual1->posicao, atual1->tamanho);

        no2 *atual2 = atual1->endereco;  // A lista auxiliar associada a este nó (armazenada em 'endereco')
        
        // Percorre os nós da lista auxiliar (l2) e exibe seus valores
        while (atual2 != NULL) {
            printf("  Chave: %d\n", atual2->chave);
            atual2 = atual2->prox;
        }
        printf("\n");  // Linha em branco para separar as listas auxiliares de diferentes nós

        atual1 = atual1->prox;  // Avança para o próximo nó da lista principal
    }
}

// Função para liberar a memória de uma lista
void liberarListas(l1 *lista1, l2 *lista2) {

    // Libera os nós da lista principal
    no1 *atual1 = lista1->cabeca;
    no1 *temp1;
    
    while (atual1 != NULL) {
        temp1 = atual1;
        atual1 = atual1->prox;
        free(temp1); // Libera o nó atual
    }

    lista1->cabeca = NULL;
    lista1->cauda = NULL;
        
    // Libera os nós da lista auxiliar
    no2 *atual2 = lista2->cabeca;
    no2 *temp2;
    
    while (atual2 != NULL) {
        temp2 = atual2;
        atual2 = atual2->prox;
        free(temp2); // Libera o nó atual
    }

    lista2->cabeca = NULL;
    lista2->cauda = NULL;
}

int main () {
    // Cria as variáveis necessárias
    l1 *lista1 = init_l1();
    l2 *lista2 = init_l2();
    FILE *arqEntrada;
    dados entrada[10];
    int indice = 0;

    if ((arqEntrada = fopen("dados.txt", "r")) == NULL) {
        printf("Erro ao abrir arquivo.\n");
    }

    // Obtém os dados do arquivo
    lerArquivo(arqEntrada, entrada, &indice);

    // Cria as listas principal e auxiliar
    gerarEstruturaNova(entrada, lista1, lista2, indice);

    // Exibe o conteúdo das listas
    exibirListasAuxiliares(lista1);

    // Fecha o arquivo
    fclose(arqEntrada);

    // Libera a memória
    liberarListas(lista1, lista2);
    free(lista1);
    free(lista2);
}