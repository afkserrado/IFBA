/*
9) Implemente 2 filas, uma de menor e uma de maior prioridade. Ao inserir um valor deve ser perguntado qual fila utilizar. Ao pedir a retirada de um valor, retirar da fila de maior prioridade, caso esta esteja vazia, então deve-se utilizar a de menor prioridade.
*/

#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

// Fila circular com lista simplesmente ligada circular

// Define a estrutura de um nó lista
typedef struct node {
    int chave;
    struct node *prox;
} node;

// Cria a estrutura de uma fila baseada em lista
typedef struct estFila {
    node *inicio;
    node *fim; 
} estFila;

// Cria e inicializa um novo nó, configurando seus ponteiros para NULL
node *init_node (int chave) {
    node *novo = malloc(sizeof(node)); // Aloca memória para o nó
    
    // Falha de alocação de memória
    if (novo == NULL) {
        printf("Erro ao alocar memória para o nó.\n");
        return NULL;
    }

    novo->chave = chave;
    novo->prox = NULL;
    return novo;
}

// Cria e inicializa uma fila
estFila *init_fila () {
    estFila *fila = malloc(sizeof(estFila)); // Aloca memória para a fila
    
    // Falha de alocação de memória
    if (fila == NULL) {
        printf("Falha ao alocar memória para a estrutura da fila.\n");
        return NULL;
    }

    fila->inicio = NULL;
    fila->fim = NULL;
    return fila;
}

// Enfileirar: inserir elementos na fila
void enqueue (estFila *fila, node *novo) {
    
    // Falha de alocação para a fila ou fila inexistente
    if (fila == NULL) {
        printf("Não há memória alocada para a estrutura da fila.\n");
        return;
    }
    
    // Falha de alocação para o nó
    if (novo == NULL) {
        printf("Não há memória alocada para o novo nó.\n");
        return;
    }

    // Fila vazia
    if (fila->inicio == NULL) {
        fila->inicio = novo;
        fila->fim = novo;
        novo->prox = novo; // Fecha o círculo
        return;
    }

    // Fila não vazia
    fila->fim->prox = novo; // Fim atual aponta para o novo
    novo->prox = fila->inicio; // Novo aponta para o início atual da fila
    fila->fim = novo; // Fim recebe o novo
}

// Desinfileirar: retirar elementos da fila
int dequeue (estFila *fila) {
    
    // Falha de alocação para a fila ou fila inexistente
    if (fila == NULL) {
        printf("Não há memória alocada para a estrutura da fila.\n");
        return INT_MAX;
    }
    
    // Fila vazia
    if (fila->inicio == NULL) {
        printf("Fila vazia.\n");
        return INT_MAX;
    }

    node *temp = fila->inicio; // Guarda o início da fila
    int valor = temp->chave;

    // Fila com elemento único
    if (fila->inicio == fila->fim) {
        fila->inicio = NULL;
        fila->fim = NULL;
    }

    // Fila com mais de um elemento
    else {
        fila->fim->prox = fila->inicio->prox; 
        fila->inicio = fila->inicio->prox; // Atualiza o início da fila
    }
          
    free(temp);
    return valor;
}

// Exibe os elementos da fila
void imprimirFila (estFila *fila) {
    
    // Falha de alocação para a fila ou fila inexistente
    if (fila == NULL) {
        printf("Não há memória alocada para a estrutura da fila.\n");
        return;
    }
    
    // Fila vazia
    if (fila->inicio == NULL) {
        printf("Fila vazia.\n");
        return;
    }

    node *x = fila->inicio; // Guarda o início dafila

    // Percorre a fila
    do {
        if (x != fila->inicio) {printf(" ");}
        printf("%d", x->chave);
        x = x->prox;
    } while (x != fila->inicio);
    printf("\n");
}

// Libera a memória da fila e dos nós da lista
void liberarFila (estFila *fila) {
    
    // Falha de alocação para a fila ou fila inexistente
    if (fila == NULL) {
        printf("Não há memória alocada para a estrutura da fila.\n");
        return;
    }
    
    // Fila existente, mas vazia
    if (fila->inicio == NULL) {
        free(fila);
        return;
    }

    node *x = fila->inicio; // Guarda o início da fila

    // Percorre a fila, liberando os nós da lista
    do {
        node *temp = x;
        x = x->prox;
        free(temp);
    } while (x != fila->inicio);

    // Libera a estrutura da fila
    free(fila);
}

int main() {

    // Inicializa as filas
    estFila *filaMenor = init_fila();
    estFila *filaMaior = init_fila();
  
    // Inserir elementos
    int op = 0;
    while (1) {
        printf("Inserir na fila menor (1), maior (2) ou encerrar (0)? ");
        scanf("%d", &op);

        if (op == 0) {
            if (filaMenor->inicio == NULL && filaMaior->inicio == NULL) {
                printf("Programa encerrado. Nenhuma entrada registrada.\n");
                return 0; // Encerra o programa
            }
            else {
                printf("Entradas registradas.\n");
                break;
            }
        }

        if (op != 0 && op != 1 && op != 2) {
            printf("Opção inválida.\n");
            continue;
        }

        int entrada;
        printf("Informe um número inteiro: ");
        scanf("%d", &entrada);

        if (op == 1) {
            enqueue(filaMenor, init_node(entrada));
        }
        else {
            enqueue(filaMaior, init_node(entrada));
        }
    }

    printf("\nFila menor: ");
    imprimirFila(filaMenor);
    printf("Fila maior: ");
    imprimirFila(filaMaior);

    printf("\n");

    // Remover elementos
    int valor;
    while (1) {
        printf("Deseja remover? (1) Sim (0) Não: ");
        scanf("%d", &op);

        if (op == 0) {
            break;
        }

        if (op != 0 && op != 1) {
            printf("Opção inválida.\n");
            continue;
        }

        if (op == 1) {
            if (filaMaior->inicio != NULL) {
                valor = dequeue(filaMaior);
                printf("Valor removido: %d\n", valor);
            }
            else if (filaMenor->inicio != NULL){
                valor = dequeue(filaMenor);
                printf("Valor removido: %d\n", valor);
            }
            else {
                printf("As filas estão vazias.\n");
                break;
            }
        }
    };

    printf("\nFila menor: ");
    imprimirFila(filaMenor);
    printf("Fila maior: ");
    imprimirFila(filaMaior);

    liberarFila(filaMenor);
    liberarFila(filaMaior);
       
    return 0;
}