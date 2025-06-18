#include <stdio.h>
#include <stdlib.h>

// ##################################################### //
// LISTA SIMPLESMENTE LIGADA CIRCULAR (LSLC)

// Define a estrutura de um nó da lista
typedef struct node {
    int chave;
    struct node *prox;
} node;

// Define a estrutura da lista
typedef struct lslc {
    node *cabeca;
    node *cauda;
} lslc;

// Cria e inicializa um novo nó na lista, configurando seus ponteiros para NULL
node *init_node (int chave) {
    node *node_novo = malloc(sizeof(node)); // Aloca memória para o primeiro nó
    if (node_novo == NULL) {return NULL;}
    node_novo->chave = chave;
    node_novo->prox = NULL;
    return node_novo;
}

// Cria e inicializa a lista, configurando seus ponteiros para NULL (lista vazia)
lslc *init_lista () {
    lslc *lista = malloc(sizeof(lslc));
    if (lista == NULL) {return NULL;}
    lista->cabeca = NULL;
    lista->cauda = NULL;
    return lista;
}

// Insere um novo nó no início da lista
void inserir_node (lslc *lista, node *node_novo) {
    // Lista vazia
    if (lista->cabeca == NULL) {
        lista->cabeca = node_novo;
        lista->cauda = node_novo;
        node_novo->prox = lista->cabeca; // O novo nó aponta para ele mesmo, fechando o círculo
    } 
    // Lista não vazia
    else {
        // Adiciona o novo nó no início da lista
        node_novo->prox = lista->cabeca;
        lista->cabeca = node_novo;
        lista->cauda->prox = lista->cabeca;
    }
}

// Insere um novo nó na lista, na posição ordenada (ordem crescente)
void inserir_node_ordenado (lslc *lista, node *node_novo) {
    // Lista vazia
    if (lista->cabeca == NULL) {
        lista->cabeca = node_novo;
        lista->cauda = node_novo;
        node_novo->prox = lista->cabeca; // O novo nó aponta para ele mesmo, fechando o círculo
    }
    // Lista não vazia
    else {
        // Variáveis temporárias
        node *posterior = lista->cabeca; // Marca a chave imediatamente posterior ao node_novo
        node *anterior = NULL; // Marca a chave imediatamente anterior ao node_novo
        int chave = node_novo->chave;

        // Encontra a posição correta
        do {
            if (posterior->chave > chave) {break;}
            anterior = posterior;
            posterior = posterior->prox;
        } while (posterior != lista->cabeca);

        // Caso 1: o node_novo é o primeiro item da lista
        if (posterior == lista->cabeca && anterior == NULL) {
            node_novo->prox = lista->cabeca;
            lista->cabeca = node_novo;
            lista->cauda->prox = lista->cabeca; // Fecha o círculo
        }
        // Caso 2: o node_novo é o último item da lista
        else if (posterior == lista->cabeca && anterior != NULL) {
            node_novo->prox = lista->cabeca;
            lista->cauda->prox = node_novo;
            lista->cauda = node_novo;
        }
        // Caso 3: o node_novo ocupa qualquer posição intermediária da lista
        else {
            node_novo->prox = posterior;
            anterior->prox = node_novo;     
        }
    }
}

// Remover um nó qualquer da lista de reais
void remover_node (lslc *lista, int chave) {
    // Lista vazia
    if (lista->cabeca == NULL) { 
        return;
    }
    
    // Inicializa as variáveis temporárias
    node *atual = lista->cabeca; 
    node *anterior = lista->cauda; // Nó anterior ao atual

    // Pesquisa a chave
    do { 
        if (atual->chave == chave) {
            break;
        }
        anterior = atual;
        atual = atual->prox; // Avança para o próximo nó
    } while (atual != lista->cabeca);

    // Remove o nó encontrado
    if (atual->chave == chave) { // Chave encontrada
        
        // Lista unitária
        // Caso 1: remoção da cabeça
        if (atual == lista->cabeca && atual->prox == lista->cabeca) { 
            lista->cabeca = NULL;
            lista->cauda = NULL;
        }
        
        // Lista com múltiplos nós
        // Caso 2: remoção da cabeça
        else if (atual == lista->cabeca){
            lista->cabeca = atual->prox;
            lista->cauda->prox = lista->cabeca; // Fecha o círculo
        }
        // Caso 3: remoção da cauda
        else if (atual == lista->cauda) {
            lista->cauda = anterior;
            lista->cauda->prox = lista->cabeca; // Fecha o círculo
        }
        // Caso 4: remoção de um elemento qualquer
        else {
            anterior->prox = atual->prox;
        }
        
        // Libera a memória do nó removido
        free(atual); 
    }
}

// Limpar lista
void limpar_lista (lslc *lista) {
    // Lista vazia
    if (lista->cabeca == NULL) { 
        printf("A lista está vazia.\n");
        return;
    }

    node *atual = lista->cabeca;
    do {
        node *temp = atual;
        atual = atual->prox;
        free(temp);
    } while (atual != lista->cabeca);

    lista->cabeca = NULL;
    lista->cauda = NULL;
}

// Imprimir lista
void imprimir_lista (lslc *lista) {
    // Lista vazia
    if (lista->cabeca == NULL) { 
        printf("A lista está vazia.\n");
        return;
    }

    node *x = lista->cabeca; // Inicializa x com a cabeça da lista
    printf("head->");
    do {
        printf("(%d)->", x->chave);
        x = x->prox;
    } while (x != lista->cabeca);
    printf("(%d)<-head\n\n", lista->cabeca->chave); // Fim da lista
}

// Liberar a memória alocada para os nós e a lista
void liberar_lista(lslc *lista) {
    // Lista vazia
    if (lista == NULL) { 
        printf("A lista já está vazia.\n");
        return;
    }
    
    // Libera a memória dos nós da lista
    if (lista->cabeca != NULL) {
        node *x = lista->cabeca;
        do {
            node *temp = x;
            x = x->prox;
            free(temp); // listabera a memória de cada nó
        } while (x != lista->cabeca);
    }

    // Libera a memória da lista, mesmo que ela esteja vazia
    free(lista);
}

int main () {

    lslc *idades = init_lista();
    int num;

    printf("Inserção aleatória:\n");
    inserir_node(idades, init_node(20));
    inserir_node(idades, init_node(37));
    inserir_node(idades, init_node(18));
    inserir_node(idades, init_node(40));
    inserir_node(idades, init_node(18));
    inserir_node(idades, init_node(-1));

    imprimir_lista(idades);
    num = -1;
    remover_node(idades, num);
    printf("Removeu %d:\n", num);
    imprimir_lista(idades);
    num = 40;
    remover_node(idades, num);
    printf("Removeu %d:\n", num);
    imprimir_lista(idades);
    num = 20;
    remover_node(idades, num);
    printf("Removeu %d:\n", num);
    imprimir_lista(idades);
    num = 100;
    remover_node(idades, num);
    printf("Removeu %d:\n", num);
    imprimir_lista(idades);

    limpar_lista(idades);

    printf("Inserção ordenada:\n");
    inserir_node_ordenado(idades, init_node(20));
    inserir_node_ordenado(idades, init_node(37));
    inserir_node_ordenado(idades, init_node(18));
    inserir_node_ordenado(idades, init_node(40));
    inserir_node_ordenado(idades, init_node(18));
    inserir_node_ordenado(idades, init_node(-1));

    imprimir_lista(idades);
    num = -1;
    remover_node(idades, num);
    printf("Removeu %d:\n", num);
    imprimir_lista(idades);
    num = 40;
    remover_node(idades, num);
    printf("Removeu %d:\n", num);
    imprimir_lista(idades);
    num = 20;
    remover_node(idades, num);
    printf("Removeu %d:\n", num);
    imprimir_lista(idades);
    num = 100;
    remover_node(idades, num);
    printf("Removeu %d:\n", num);
    imprimir_lista(idades);

    // Libera a memória alocada para todos os nós e para a lista
    liberar_lista(idades);
}