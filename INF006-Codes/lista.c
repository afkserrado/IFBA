#include<stdio.h>
#include<stdlib.h>

// Lista duplamente encadeada não ordenada

// Define a estrutura de um nó da lista
typedef struct node {
    int chave;
    struct node *ante; 
    struct node *prox;
} node;

/*
Uma instância do tipo node possui 24 bytes:

- 4 bytes para o int chave
- 4 bytes de padding (adicionados pelo compilador)
- 8 bytes para o ponteiro ante
- 8 bytes para o ponteiro prox

O que é o "padding"?
O padding é uma técnica utilizada pelo compilador que adiciona bytes extras à estrutura de dados. Isso é feito para garantir que os endereços de memória dos ponteiros (que ocupam 8 bytes) estejam alinhados a múltiplos de 8 bytes. O alinhamento de memória é uma prática importante, especialmente em arquiteturas de 64 bits, onde a CPU lê os dados de 8 em 8 bytes (64 bits), garantindo que o acesso à memória seja otimizado.

Por que o padding é necessário?
Em uma arquitetura x64, a CPU trabalha de forma mais eficiente quando os dados estão alinhados de acordo com seu tamanho, especialmente para tipos como ponteiros (8 bytes). Se a estrutura node fosse armazenada sem o preenchimento (padding), o ponteiro ante poderia não estar alinhado a 8 bytes, o que faria com que a CPU precisasse realizar acessos adicionais à memória, prejudicando a performance.

Ao adicionar 4 bytes de padding entre o int chave e o ponteiro ante, o compilador assegura que o ponteiro comece em um endereço múltiplo de 8 bytes, permitindo que a CPU faça o acesso em blocos de 8 bytes e melhor aproveite os recursos de memória da arquitetura de 64 bits.

Obs. 1: lembrando que a palavra de um processador x64 é 64 bits. Por isso ele lê de 8 em 8 bytes.
Obs. 2: os bytes de padding não são utilizados para armazenar dados, apenas para alinhamento.
*/

// Define a estrutura de uma lista duplamente encadeada
typedef struct lista_dup {
    node *cabeca;
    node *cauda;
} lista_dup;

// Cria e inicializa um novo nó e configura seus ponteiros para NULL
node *init_node (int chave) {
    node *node_novo = malloc(sizeof(node)); // Aloca memória para o primeiro nó
    node_novo->chave = chave;
    node_novo->ante = NULL;
    node_novo->prox = NULL;
    return node_novo;
}

/*
Obs. 1: a variável node_novo é um ponteiro para uma estrutura node, que contém dados de tipos diferentes (neste caso, um int e dois ponteiros). A alocação de memória com malloc garante espaço suficiente para armazenar todos os campos dessa estrutura.

Obs. 2: a função init_node retorna um ponteiro para node, que aponta para a memória alocada para o nó recém-criado.
*/

// Cria e inicializa uma nova lista, configurando o head para NULL (lista vazia)
lista_dup *init_lista () {
    lista_dup *lista = malloc(sizeof(lista_dup));
    lista->cabeca = NULL;
    lista->cauda = NULL;
    return lista;
}

// Insere um novo nó no início da lista
void inserir_node (lista_dup *lista, node *node_novo) {
    if (lista->cabeca == NULL) { // Lista vazia
        lista->cabeca = node_novo; // Insere o novo nó na cabeça da lista
        lista->cauda = node_novo;
    }
    /*
    Obs. 1: "node_novo->ante" e "node_novo->prox" já apontam para NULL, porque isso foi definido no momento da inicialização do node_novo com init_node.
    
    Obs. 2: ao fazer "lista->cauda = node_novo" garantimos que "lista->cauda" sempre seja o último elemento da lista, pois a inserção está ocorrendo sempre na cabeça.
    */ 

    else { // Lista não vazia  
        // Insere o novo nó no início da lista
        node_novo->prox = lista->cabeca; // "prox" do novo nó aponta para o atual "cabeca"
        lista->cabeca->ante = node_novo; // "ante" do atual "cabeca" aponta para o novo nó
        lista->cabeca = node_novo; //  "cabeca" recebe o novo nó
    }
}

// Insere um novo nó na posição ordenada
void inserir_node_ordenado (lista_dup *lista, node *node_novo) {
    if (lista->cabeca == NULL) { // Lista vazia
        lista->cabeca = node_novo;
        lista->cauda = node_novo;
    }
    else { // Lista não vazia
        
        // Variáveis temporárias
        node *x = lista->cabeca;
        int chave = node_novo->chave;

        while (x != NULL && x->chave < chave) {
            x = x->prox;
        }
        /*
        Quando o while é interrompido, 'x' guarda a posição do elemento imediatamente após à posição na qual a chave deve ser inserida. Por exemplo, se a lista contém 5, 7 e 9, 'x' possuíra o endereço de 9.
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

// Remover um nó qualquer da lista
void remover_node (lista_dup *lista, int chave) {
    
    if (lista->cabeca == NULL) { // Lista vazia
        return;
    }
    
    node *x = lista->cabeca;

    /*
    É o mesmo que fazer:
    node *x;
    x = lista->cabeca
    */

    while (x != NULL && x->chave != chave) { // Procurando a chave na lista
        x = x->prox;
    }

    if (x != NULL) { // Se a chave for encontrada
        if (x == lista->cabeca) { // Se for o primeiro nó
            if (x->prox == NULL) { // Se a lista só possuir um nó
                lista->cabeca = NULL;
                lista->cauda = NULL;
            }
            else { // Se a lista possuir mais de um nó
                lista->cabeca = x->prox;
                lista->cabeca->ante = NULL;
            }
        }
        else { // Se não for o primeiro nó
            if (x == lista->cauda) { // Se for o último nó
                lista->cauda = x->ante;
                lista->cauda->prox = NULL;
            }
            else { // Elemento intermediário
                x->ante->prox = x->prox; // O "ante" do atual aponta para o "prox" do atual
                x->prox->ante = x->ante; // O "prox" do atual aponta para o "ante" do atual
            }
        }
        free(x);
    }
}

// Imprime a lista
void imprimir_lista (lista_dup *lista) {
    node *x = lista->cabeca; // Inicializa x com a "cabeca" da lista
    printf("\n(NULL)"); // Início da lista
    while (x != NULL) {
        printf("<- (%d) ->", x->chave);
        x = x->prox;
    }
    printf(" (NULL)\n\n"); // Fim da lista
}

// Função para liberar todos os nós da lista
void liberar_lista(lista_dup *lista) {
    node *x = lista->cabeca;
    while (x != NULL) {
        node *temp = x;
        x = x->prox;
        free(temp); // Libera a memória de cada nó
    }
    free(lista); // Libera a memória da lista
}

int main(){
    lista_dup *idades = init_lista();

    /*
    inserir_node(idades, init_node(20));
    inserir_node(idades, init_node(37));
    inserir_node(idades, init_node(18));
    inserir_node(idades, init_node(40));
    inserir_node(idades, init_node(18));
    inserir_node(idades, init_node(-1));

    imprimir_lista(idades);
    remover_node(idades, -1);
    printf("Depois\n");
    imprimir_lista(idades);
    remover_node(idades, 40);
    printf("Depois\n");
    imprimir_lista(idades);
    remover_node(idades, 20);
    printf("Depois\n");
    imprimir_lista(idades);
    remover_node(idades, 100);
    printf("Depois\n");
    imprimir_lista(idades);
    */

    inserir_node_ordenado(idades, init_node(20));
    inserir_node_ordenado(idades, init_node(37));
    inserir_node_ordenado(idades, init_node(18));
    inserir_node_ordenado(idades, init_node(40));
    inserir_node_ordenado(idades, init_node(18));
    inserir_node_ordenado(idades, init_node(-1));

    imprimir_lista(idades);
    remover_node(idades, -1);
    printf("Depois\n");
    imprimir_lista(idades);
    remover_node(idades, 40);
    printf("Depois\n");
    imprimir_lista(idades);
    remover_node(idades, 20);
    printf("Depois\n");
    imprimir_lista(idades);
    remover_node(idades, 100);
    printf("Depois\n");
    imprimir_lista(idades);

    // Libera a memória alocada para todos os nós e para a lista
    liberar_lista(idades);
}