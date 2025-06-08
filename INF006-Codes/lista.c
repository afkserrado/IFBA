#include<stdio.h>
#include<stdlib.h>

// Estrutura que representa um nó da lista
typedef struct {
    int chave;
    struct node *ante;
    struct node *prox;
} node;

// Estrutura da lista duplamente encadeada
typedef struct {
    node *cabeca;
} lista_dup;

// Cria e inicializa um novo nó e configura seus ponteiros para NULL
node *init_node (int chave) {
    node *novo = malloc(sizeof(node)); // Aloca memória para o novo nó
    novo->chave = chave;
    novo->ante = NULL;
    novo->prox = NULL;
    return novo;
}

// Cria e inicializa uma nova lista, configurando o head para NULL (lista vazia)
lista *

/*
node * init_node(int chave){
    node * node = malloc(sizeof(node));
    node->chave = chave;
    node->ante = NULL;
    node->prox = NULL;
    return node;
}

List_if * init_list(){
    List_if * list = malloc(sizeof(List_if));
    list->head = NULL;
    return list;
}

void print_list(List_if * list){
    node * x = list->head;
    printf("\n(NULL)");
    while(x!=NULL){
        printf("<- (%d) ->", x->chave);
        x = x->prox;
    }
    printf(" (NULL)\n\n");
}

void insert_list(List_if * list, node * new){
    if(list->head==NULL){
        list->head = new;
    }
    else{
        //list->head = new;
        new->prox = list->head;
        list->head->ante = new;
        list->head = new;
        
    }
}
void remove_list(List_if * list, int chave){
    node * x = list->head;
    while(x!=NULL && x->chave!=chave){
        x = x->prox;
    }
    if(x!=NULL){
        if(x==list->head){
            if(x->prox==NULL) {
                list->head = NULL;
            }
            else{
                list->head = x->prox;
                list->head->ante = NULL;
            }
        }
        else{
            if(x->ante != NULL){
                x->ante->prox = x->prox;
            }
            if(x->prox!=NULL){
                x->prox->ante = x->ante;
            }
        }
        free(x);
    }
        
}
*/


int main(){
    List_if * idades = init_list();

    insert_list(idades, init_node(20));
    insert_list(idades, init_node(37));
    insert_list(idades, init_node(18));

    insert_list(idades, init_node(40));
    insert_list(idades, init_node(18));
    insert_list(idades, init_node(-1));

    /*
    node *  no_joao = init_node(20);
    idades->head = no_joao;free(x);

    node * no_rodrigo = init_node(37);
    no_joao->prox = no_rodrigo;
    no_rodrigo->ante = no_joao;

    node * no_julio = init_node(18);
    no_rodrigo->prox = no_julio;
    no_julio->ante = no_rodrigo;
  */
    print_list(idades);
    remove_list(idades, -1);
    printf("Depois\n");
    print_list(idades);
    remove_list(idades, 40);
    printf("Depois\n");
    print_list(idades);
     remove_list(idades, 20);
    printf("Depois\n");
    print_list(idades);
     remove_list(idades, 100);
    printf("Depois\n");
    print_list(idades);
}