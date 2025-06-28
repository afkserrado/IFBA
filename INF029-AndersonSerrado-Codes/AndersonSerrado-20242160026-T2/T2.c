/* #################################################
//  Instituto Federal da Bahia
//  Salvador - BA
//  Curso de Análise e Desenvolvimento de Sistemas http://ads.ifba.edu.br
//  Disciplina: INF029 - Laboratório de Programação
//  Professor: Renato Novais - renato@ifba.edu.br

//  ----- Orientações gerais -----
//  Descrição: esse arquivo deve conter as questões do trabalho do aluno.
//  Cada aluno deve renomear esse arquivo para Aluno<MATRICULA>.c
//  O aluno deve preencher seus dados abaixo, e implementar as questões do trabalho

//  ----- Dados do Aluno -----
//  Nome: Anderson Kleyson Serrado de Jesus
//  email: akserrado@gmail.com
//  Matrícula: 20242160026
//  Semestre: 2º

//  Copyright © 2016 Renato Novais. All rights reserved.
//  Última atualização: 07/05/2021 - 19/08/2016

// #################################################
*/

/*
Glossário

EDP: Estrutura de Dados Principal
EDA: Estrutura de Dados Auxiliar
ldll: Lista Duplamente Ligada Linear
nv: Novo
*/

#include <stdio.h>
#include <stdlib.h>

// ############################################################################## //
// CONFIGURAÇÕES

// Limpa o buffer
void limparBuffer() {
    int c;
    while ((c = getchar()) != '\n' && c != EOF) {
        // Loop intencionalmente vazio para consumir caracteres
    }
    return;
}

// Limpa a tela
void limparTela() {
    #if defined(_WIN32) || defined(_WIN64) // Windows
        system("cls");

    #elif defined(__linux__) || defined(__unix__) || defined(__APPLE__) || defined(__MACH__) // Linux e macOS
        system("clear");
        
    #endif

    return;
}

//Pausa a tela
void pausarTela() {
    #if defined(_WIN32) || defined(_WIN64) // Windows
        system("pause");

    #elif defined(__linux__) || defined(__unix__) || defined(__APPLE__) || defined(__MACH__) // Linux e macOS
        printf("Pressione Enter para continuar...");
        fflush(stdout);  // Força a exibição imediata
        
        limparBuffer();

    #endif

    return;
}

// CONFIGURAÇÕES
// ############################################################################## //

// ############################################################################## //
// ESTRUTURA AUXILIAR: LISTA DUPLAMENTE LIGADA LINEAR (LDLL)

// Define a estrutura de um nó da lista
typedef struct node {
    int chave; // Dado inserido pelo usuário
    int index; // Índice correspondente à EDP
    struct node *ante; 
    struct node *prox;
} node;

// Define a estrutura de uma ldll
typedef struct ldll {
    node *cabeca;
    node *cauda;
} ldll;

// Cria e inicializa um novo nó, configurando seus ponteiros para NULL
node *init_node (int chave, int index) {
    node *nvNode = malloc(sizeof(node)); // Aloca memória para o novo nó
    
    // Verifica se a alocação dinâmica de memória falhou
    if (nvNode == NULL) {
        printf("Erro ao alocar memória para o nó.\n");
        return NULL;
    }

    // Inicializa o novo nó
    nvNode->chave = chave;
    nvNode->index = index;
    nvNode->ante = NULL;
    nvNode->prox = NULL;
    return nvNode;
}

// Cria e inicializa uma nova lista, configurando seus ponteiros para NULL (lista vazia)
ldll *init_lista () {
    ldll *lista = malloc(sizeof(ldll)); // Aloca memória para a lista
    
    // Verifica se a alocação dinâmica de memória falhou
    if (lista == NULL) {
        printf("Erro ao alocar memória para a estrutura da lista.\n");
        return NULL;
    }

    // Inicializa a lista
    lista->cabeca = NULL;
    lista->cauda = NULL;
    return lista;
}

// Insere um novo nó, ordenando pelo index
void inserir_node_ordenado (ldll *lista, node *nvNode) {
    // Lista vazia
    if (lista->cabeca == NULL) {
        lista->cabeca = nvNode;
        lista->cauda = nvNode;
    }

    // Lista não vazia
    else { 
        
        // Variáveis temporárias
        node *x = lista->cabeca;
        int index = nvNode->index;

        // Percorre a lista até encontrar a primeira chave do index buscado
        while (x != NULL && x->index < index) {
            x = x->prox;
        }

        // Caso 1: o nvNode é o primeiro item da lista
        if (x == lista->cabeca) {
            nvNode->prox = lista->cabeca;
            nvNode->ante = NULL;
            lista->cabeca->ante = nvNode;
            lista->cabeca = nvNode;
        }
        // Caso 2: o nvNode é o último item da lista
        else if (x == NULL) {
            nvNode->prox = NULL;
            nvNode->ante = lista->cauda;
            lista->cauda->prox = nvNode;
            lista->cauda = nvNode;
        }
        // Caso 3: o nvNode ocupa qualquer posição intermediária da lista
        else {
            nvNode->ante = x->ante;
            nvNode->prox = x;
            x->ante->prox = nvNode;
            x->ante = nvNode;      
        }
    }
}

// Remover um nó qualquer da lista
void remover_node (ldll *lista, int chave) {
    // Lista vazia
    if (lista->cabeca == NULL) { 
        printf("A lista não contém elementos cadastrados.\n");
        return;
    }
    
    // Variáveis temporárias
    node *x = lista->cabeca;

    // Procurando a chave na lista
    while (x != NULL && x->chave != chave) { 
        x = x->prox;
    }

    // Chave encontrada
    if (x != NULL) {
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

// Função para liberar todos os nós da lista
void liberar_lista(ldll *lista) {
    node *x = lista->cabeca;
    while (x != NULL) {
        node *temp = x;
        x = x->prox;
        free(temp); // Libera a memória de cada nó
    }
    free(lista); // Libera a memória da lista
}

// ESTRUTURA AUXILIAR: LISTA DUPLAMENTE LIGADA LINEAR (LDLL)
// ############################################################################## //

// ############################################################################## //
// FUNÇÕES DO PROGRAMA

// Menu de opções
void menu() {
    printf("### Menu principal ###");
    printf("\nInforme o número da opção desejada: ");
    printf("\n1 - Inserir número");
    printf("\n2 - Listar todos os números de todas as estruturas");
    printf("\n3 - Listar os números ordenados para cada estrutura auxiliar");
    printf("\n4 - Listar todos os números de forma ordenada");
    printf("\n5 - Excluir um elemento");
    printf("\n6 - Aumentar o tamanho de uma estrutura auxiliar");
    printf("\n7 - Sair\n");
}

// Case 1: Inserir número
void inserir_numero (node *edp[], int tam[], int cont[], ldll *numeros) {
    
    // Solicitação do index
    int index;
    int posMin = 1;
    int posMax = 10;
    printf("### Inserir número ###");
    printf("\nInforme a posição da estrutura principal: ");

    // index não é um número inteiro
    if (scanf("%d", &index) != 1) {
        printf("\nErro: a posição deve ser um número inteiro. Retornando ao menu inicial.\n");
        return;
    }

    // index > 10 ou index < 1
    if (index > posMax || index < posMin) {
        printf("\nErro: a posição deve ser um número entre 1 e 10. Retornando ao menu inicial.\n");
        return;
    }

    // index válido: 1 <= index <= 10
    index--; // Converte o index para base 0

    // Criação da estrutura auxiliar
    if (tam[index] == 0) {
        printf("Informe o tamanho inicial da estrutura auxiliar: ");
        int tamAux;

        // tamAux não é um número inteiro
        if (scanf("%d", &tamAux) != 1) {
            printf("\nErro: o tamanho deve ser um número inteiro. Retornando ao menu inicial.\n");
            return;
        }

        if (tamAux <= 0) {
            printf("\nErro: o tamanho deve ser um número inteiro maior que 0. Retornando ao menu inicial.\n");
            return;
        }

        // tamAux válido: número inteiro maior que 0
        tam[index] = tamAux;
        printf("Estrutura auxiliar criada com tamanho %d.\n", tam[index]);
    }

    // Solicitação do número
    if (tam[index] > 0) {
        
        // Estrutura auxiliar cheia
        if (cont[index] >= tam[index]) {
            printf("\nEstrutura auxiliar cheia. Retornando ao menu inicial.\n");
            return;
        }

        // Estrutura auxiliar não cheia
        int chave;
        printf("Informe o número a ser inserido: ");
        
        // chave não é um número inteiro
        if (scanf("%d", &chave) != 1) {
            printf("\nErro: o número deve ser um número inteiro. Retornando ao menu inicial.\n");
            return;
        }

        // chave válida: número inteiro
        cont[index]++; // Incrementa o contador

        // Insere o número na estrutura auxiliar
        node *temp = init_node(chave, index);
        inserir_node_ordenado(numeros, temp);
        edp[index] = temp;
        printf("Número %d inserido na estrutura auxiliar de posição %d.\n", chave, index + 1);
    } 
}

// Case 2: Listar todos os números
/*void imprimir_lista (ldll *lista) {
    node *x = lista->cabeca; // Inicializa x com a "cabeca" da lista
    printf("\n(NULL)"); // Início da lista
    while (x != NULL) {
        printf("<- (%d) ->", x->chave);
        x = x->prox;
    }
    printf(" (NULL)\n\n"); // Fim da lista
}*/
void imprimir_lista (ldll *lista) {
    
    printf("### Listar números ###");

    // Verifica se a lista está vazia
    if (lista->cabeca == NULL) {
        printf("\n\nA lista está vazia.\n");
        return;
    }
    
    printf("\n\n");

    node *x = lista->cabeca; // Inicializa x com a "cabeca" da lista

    while (x != NULL) {
        int pos = x->index;
        
        printf("Posição %d: ", pos + 1);

        int primeiro = 0;
        while (x != NULL && x->index == pos) {
            if (primeiro == 0) {
                printf("(%d)", x->chave);
            }
            else {
                printf(" (%d)", x->chave);
            }
            
            x = x->prox;
        }
        printf("\n");
    }
    printf("\n");
}

void imprimir_vetor (int v[]) {
    int tamanho = 10;
    
    printf("[");
    int primeiro = 0;
    for (int i = 0; i < tamanho; i++){
        if (primeiro == 0){
            printf("%d", v[i]);
            primeiro = 1;
        }
        else 
            printf(", %d", v[i]);
    }
    printf("]");
}

// Funções do programa
void funcoes(node *edp[], int tam[], int cont[], ldll *numeros) {

    // Declarações e inicializações
    int opcao;

    limparTela();

    // Funções disponíveis no programa
    do {
        // Imprime o menu de opções
        menu();
    
        // Entrada de dados: Opção
        // Verifica se a entrada é um número inteiro
        if (scanf("%d",&opcao) != 1) {
            printf("\nEntrada inválida. Por favor, insira um número.\n\n");
            opcao = 0; // Atribui um valor inválido, entranto no default do switch
        }

        limparBuffer(); // Consome o enter deixado no buffer pelo scanf
        limparTela(); // Transição de tela

        switch(opcao) {

            // Inserir número
            case 1: {
                inserir_numero(edp, tam, cont, numeros);
                /*printf("Vetor tam: ");
                imprimir_vetor(tam);
                printf("\n");
                printf("Vetor cont: ");
                imprimir_vetor(cont);  
                printf("\n");
                printf("Lista: ");
                imprimir_lista(numeros);
                printf("\n");*/
                                    
                // Transição de tela
                limparBuffer();
                pausarTela();
                limparTela(); 
                break;
            }

            // Listar todos os números
            case 2: {
                imprimir_lista(numeros);

                pausarTela();
                limparTela();
                break;
            }

            // Sair
            case 7: {
                printf("Programa encerrado.\n");
                pausarTela();
                limparTela();
                break;
            }

            default: {
                printf("Opção inválida.\n");

                // Transição de tela
                pausarTela();
                limparTela();
                break;
            } // Fim do default

        } // Fim do switch

    } while (opcao != 7);
}

// FUNÇÕES DO PROGRAMA
// ############################################################################## //

int main() {

    // Inicializando a EDP e o tam
    node *edp[10]; // Guarda os endereços relativos às estruturas auxiliares
    int tam[10] = {0}; // Guarda os tamanhos relativos às estruturas auxiliares
    int cont[10] = {0}; // Guarda a quantidade de números cadastrados nas estruturas auxiliares

    // Inicializando a EDA
    ldll *numeros = init_lista();

    // Funções do programa
    funcoes(edp, tam, cont, numeros);

    // Desaloca memória dos nós e da lista
    liberar_lista(numeros);
}