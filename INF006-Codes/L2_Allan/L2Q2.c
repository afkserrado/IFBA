/*
2) A figura abaixo mostra uma lista duplamente encadeada circular, onde cada nó possui o ponteiro para o anterior, o ponteiro para o próximo e um valor. O ponteiro inicio indica o inicio da lista (aponta para NULO se a lista for vazia). Apresente, ou em C, ou em pseudo-linguagem, um passo a passo adequado para:

a) Inserir um novo nó, de forma ordenada (ex: o nó de valor 5 tem que ficar antes do de valor 6 e depois do de valor 4);
b) Buscar um elemento com um dado valor val retornando o mesmo ou indicar que o elemento não existe na lista.
*/

#include <stdio.h>
#include <stdlib.h>

// Lista duplamente encadeada circular

// Define a estrutura de um nó da lista
typedef struct node {
    int chave;
    struct node *ante; 
    struct node *prox;
} node;

// Define a estrutura de uma lista duplamente encadeada
typedef struct ldlc {
    node *cabeca;
    node *cauda;
} ldlc;

// Cria e inicializa um novo nó, configurando seus ponteiros para NULL
node *init_node (int chave) {
    node *novo = malloc(sizeof(node)); // Aloca memória para o primeiro nó
    
    // Falha de alocação
    if (novo == NULL) {
        printf("Erro ao alocar memória para o nó.\n");
        return NULL;
    }

    novo->chave = chave;
    novo->ante = NULL;
    novo->prox = NULL;
    return novo;
}

// Cria e inicializa uma nova lista, configurando seus ponteiros para NULL (lista vazia)
ldlc *init_lista () {
    ldlc *lista = malloc(sizeof(ldlc));
    
    // Falha de alocação
    if (lista == NULL) {
        printf("Erro ao alocar memória para a estrutura da lista.\n");
        return NULL;
    }

    lista->cabeca = NULL;
    lista->cauda = NULL;
    return lista;
}

// Insere um novo nó no início da lista
void inserir_node (ldlc *lista, node *novo) {
    
    // Lista vazia
    if (lista->cabeca == NULL) { 
        lista->cabeca = novo;
        lista->cauda = novo;
        novo->prox = lista->cabeca; // Circularidade
        novo->ante = lista->cauda; // Circularidade
    }

    // Lista não vazia
    else {   
        novo->prox = lista->cabeca; // O prox do novo aponta para a cabeça atual
        novo->ante = lista->cauda; // O ante do novo aponta para a cauda atual
        lista->cabeca->ante = novo; // O ante da cabeça atual aponta para o novo
        lista->cauda->prox = novo; // O prox da cauda atual aponta para o novo
        lista->cabeca = novo; // Cabeça recebe o novo
    }
}

// Insere um novo nó na posição ordenada
void inserir_node_ordenado (ldlc *lista, node *novo) {
    
    // Lista vazia
    if (lista->cabeca == NULL) { 
        lista->cabeca = novo;
        lista->cauda = novo;
        novo->prox = lista->cabeca; // Circularidade
        novo->ante = lista->cauda; // Circularidade
    }

    // Lista não vazia
    else { 
        
        // Variáveis temporárias
        node *atual = lista->cabeca;
        node *anterior = NULL;
        int chave = novo->chave;

        // Busca a posição correta do elemento na lista
        do {
            if (atual->chave >= chave) {
                break;
            }
            anterior = atual;
            atual = atual->prox;
        } while (atual != lista->cabeca);
  
        // Caso 1: o novo é o primeiro item da lista
        if (atual == lista->cabeca && anterior == NULL) {
            novo->prox = lista->cabeca; // O próximo do novo aponta para a cabeça atual
            novo->ante = lista->cauda; // O anterior do novo aponta para a cauda atual
            lista->cabeca->ante = novo; // O anterior da cabeça atual aponta para o novo
            lista->cauda->prox = novo; // O próximo da cauda atual aponta para o novo
            lista->cabeca = novo; // Cabeça recebe o novo
        }
        // Caso 2: o novo é o último item da lista
        else if (atual == lista->cabeca && anterior == lista->cauda) {
            novo->prox = lista->cabeca;  // O pŕoximo do novo aponta para a cabeça atual
            novo->ante = lista->cauda; // O anterior do novo aponta para a cauda atual
            lista->cauda->prox = novo; // O próximo da cauda atual aponta para o novo
            lista->cabeca->ante = novo; // O anterior da cabeça atual aponta para o novo
            lista->cauda = novo; // Cauda recebe o novo
        }
        // Caso 3: o novo ocupa qualquer posição intermediária da lista
        else {
            novo->prox = atual;
            novo->ante = anterior;
            anterior->prox = novo;
            atual->ante = novo;   
        }
    }
}

// Remover um nó qualquer da lista
void remover_node (ldlc *lista, int chave) {
    
    // Lista vazia
    if (lista->cabeca == NULL) { 
        printf("Lista vazia.\n");
        return;
    }

    node *atual = lista->cabeca;
    node *anterior = NULL;
    int achou = 0;

    // Busca a chave
    do {
        if (atual->chave == chave) {
            achou = 1;
            break;
        }
        anterior = atual;
        atual = atual->prox;
    } while (atual != lista->cabeca);

    // Chave encontrada
    if (achou == 1) {

        // Chave é a cabeça
        if (atual == lista->cabeca && anterior == NULL) {

            // Lista com um único elemento
            if (lista->cabeca->prox == lista->cabeca) {
                lista->cabeca = NULL;
                lista->cauda = NULL;
            }

            // Lista com mais de um elemento
            else {
                atual->prox->ante = lista->cauda;
                lista->cauda->prox = atual->prox;
                lista->cabeca = atual->prox; 
            }
        }

        // Chave é a cauda
        else if (atual == lista->cauda) {
            atual->ante->prox = lista->cabeca;
            lista->cabeca->ante = atual->ante;
            lista->cauda = atual->ante;
        }

        // Chave é qualquer elemento no meio
        else { 
            atual->ante->prox = atual->prox;
            atual->prox->ante = atual->ante;
        }

        free(atual);
    }
}

// Busca sequencial
void buscaSequencial (ldlc *lista, int chave) {

    // Lista vazia
    if (lista->cabeca == NULL) { 
        printf("Lista vazia.\n");
        return;
    }

    // Busca a chave
    node *atual = lista->cabeca;
    do {
        // Chave encontrada
        if (atual->chave == chave) {
            printf("Chave: %d\n", atual->chave);
            return;
        }
        atual = atual->prox;
    } while (atual != lista->cabeca);

    // Chave não encontrada
    printf("Chave não encontrada.\n");
}

// Imprime a lista
void imprimir_lista (ldlc *lista) {
    
    // Lista vazia
    if (lista->cabeca == NULL) { 
        printf("A lista está vazia.\n");
        return;
    }

    node *x = lista->cabeca; // Inicializa x com a cabeça da lista
    printf("head->");
    do {
        printf("(%d)", x->chave);
        x = x->prox;
        if (x != lista->cabeca) {  // Não imprime seta após o último
            printf("->");
        }
    } while (x != lista->cabeca);
    printf("<-tail\n\n"); // Fim da lista
}

// Função para liberar todos os nós da lista
void liberar_lista(ldlc *lista) {
    // Lista não existe
    if (lista == NULL) { 
        printf("Não há lista para liberar.\n");
        return;
    }
    
    // Libera a memória dos nós da lista
    if (lista->cabeca != NULL) {
        node *x = lista->cabeca;
        do {
            node *temp = x;
            x = x->prox;
            free(temp); // Libera a memória de cada nó
        } while (x != lista->cabeca);
    }

    // Libera a memória da lista, mesmo que ela esteja vazia
    free(lista);
}

int main(){
    
    ldlc *lista1 = init_lista();
    ldlc *lista2 = init_lista();

    printf("Lista 1:\n");
    inserir_node(lista1, init_node(20));
    inserir_node(lista1, init_node(37));
    inserir_node(lista1, init_node(18));
    inserir_node(lista1, init_node(40));
    inserir_node(lista1, init_node(18));
    inserir_node(lista1, init_node(-1));
    imprimir_lista(lista1);

    remover_node(lista1, -1);
    printf("Depois\n");
    imprimir_lista(lista1);
    remover_node(lista1, 40);
    printf("Depois\n");
    imprimir_lista(lista1);
    remover_node(lista1, 20);
    printf("Depois\n");
    imprimir_lista(lista1);
    remover_node(lista1, 100);
    printf("Depois\n");
    imprimir_lista(lista1);

    buscaSequencial(lista1, 18);
    buscaSequencial(lista1, 37);
    buscaSequencial(lista1, 40);

    printf("\nLista 2:\n");
    inserir_node_ordenado(lista2, init_node(20));
    inserir_node_ordenado(lista2, init_node(37));
    inserir_node_ordenado(lista2, init_node(18));
    inserir_node_ordenado(lista2, init_node(40));
    inserir_node_ordenado(lista2, init_node(18));
    inserir_node_ordenado(lista2, init_node(-1));
    imprimir_lista(lista2);

    remover_node(lista2, -1);
    printf("Depois\n");
    imprimir_lista(lista2);
    remover_node(lista2, 40);
    printf("Depois\n");
    imprimir_lista(lista2);
    remover_node(lista2, 20);
    printf("Depois\n");
    imprimir_lista(lista2);
    remover_node(lista2, 100);
    printf("Depois\n");
    imprimir_lista(lista2);

    buscaSequencial(lista1, 18);
    buscaSequencial(lista1, 37);
    buscaSequencial(lista1, 40);

    // Libera a memória alocada para todos os nós e para as listas
    liberar_lista(lista1);
    liberar_lista(lista2);
}
