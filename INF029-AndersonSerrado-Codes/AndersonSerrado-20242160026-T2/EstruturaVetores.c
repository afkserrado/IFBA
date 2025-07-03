// #########################################################################//
// BIBLIOTECAS

#include <stdio.h>
#include <stdlib.h>
#include <limits.h>
#include <stdbool.h>
#include "EstruturaVetores.h"

// #########################################################################//
// ESTRUTURAS DE DADOS PRINCIPAL E AUXILIARES

// Guarda o endereço das estruturas auxiliares
#define TAM 10
int *vetorPrincipal[TAM] = {NULL}; 

// Guarda o tamanho e a quantidade de itens das estruturas auxiliares
int tamAuxiliar[TAM] = {0};
int contAuxiliar[TAM] = {0};

// #########################################################################//
// FUNÇÕES AUXILIARES

void imprimeVetor(int v[]) {
    printf("[");
    int primeiro = 0;
    for (int i = 0; i < TAM; i++){
        if (primeiro == 0){
            printf("%d", v[i]);
            primeiro = 1;
        }
        else 
            printf(", %d", v[i]);
    }
    printf("]");
}

// Verifica se a posição é um número válido (entre 1 e 10)
int ehPosicaoValida(int posicao) { // OK
    int retorno = 0;
    if (posicao < 1 || posicao > 10) {
        retorno = POSICAO_INVALIDA;
    }
    else {retorno = SUCESSO;}
    return retorno;
}

// Verifica se existe estrutura auxiliar
int ehEstruturaAuxiliarExistente(int index) { // OK
    int retorno = 0;
    if (vetorPrincipal[index] == NULL) {
        retorno = SEM_ESTRUTURA_AUXILIAR;
    }
    else {retorno = JA_TEM_ESTRUTURA_AUXILIAR;}
    return retorno;
}

// Verifica se a estrutura auxiliar tem espaço
int estruturaAuxiliarTemEspaco(int index) { // OK
    int retorno = 0;
    if (contAuxiliar[index] >= tamAuxiliar[index]) {
        retorno = SEM_ESPACO;
    }
    else {retorno = SUCESSO;}
    return retorno;
}

// Exclusão lógica com Shift
int shift(int valor, int index) {
    
    int *estAuxiliar = vetorPrincipal[index];
    int cont = contAuxiliar[index]; // Quantidade de elementos

    // Busca linear para vetores desordenados
    for (int i = 0; i < cont; i++) {
        
        // Número encontrado
        if (valor == estAuxiliar[i]) {
            
            // Shift
            for (int k = i; k < cont - 1; k++) {
                estAuxiliar[k] = estAuxiliar[k+1];
            }

            contAuxiliar[index] -= 1; // Decrementa a quantidade de elementos
            return SUCESSO; // Encerra a busca 
        }
    }

    // Número não encontrado
    return NUMERO_INEXISTENTE;
}

// Ordenação de elementos com Insertion Sort
void insertionSort(int vetorAux[], int cont) {

    int i, j, temp;
    for (j = 1; j < cont; j++){
        temp = vetorAux[j];
        i = j - 1;
        
        while (i >= 0 && vetorAux[i] > temp){
            vetorAux[i + 1] = vetorAux[i];
            i--;
        }
        vetorAux[i + 1] = temp;
    }
}

// #########################################################################//
// LISTA DUPLAMENTE LIGADA LINEAR (LDLL)

// Define a estrutura de uma LDLL
typedef struct ldll {
    No *cabeca;
    No *cauda;
} ldll;

// Cria e inicializa um novo nó
No *init_No(int chave) {
    No *No_novo = malloc(sizeof(No)); // Aloca memória para o novo nó
    
    // Falha de alocação de memória
    if (No_novo == NULL) {
        printf("Erro ao alocar memória para o nó.\n");
        return NULL;
    }

    No_novo->chave = chave;
    No_novo->ante = NULL;
    No_novo->prox = NULL;
    return No_novo;
}

// Declaração da lista
ldll *lista = NULL;

// Cria e inicializa uma nova lista, configurando seus ponteiros para NULL (lista vazia)
ldll *init_lista() {
    lista = malloc(sizeof(ldll));
    
    // Falha de alocação de memória
    if (lista == NULL) {
        printf("Erro ao alocar memória para a estrutura da lista.\n");
        return NULL;
    }

    lista->cabeca = NULL;
    lista->cauda = NULL;
    return lista;
}

// Insere um novo nó no início da lista
void inserir_No (No *novo) {
    if (lista->cabeca == NULL) { // Lista vazia
        lista->cabeca = novo; // Insere o primeiro elemento na cabeça da lista
        lista->cauda = novo; // Insere o primeiro elemento na cauda da lista
    }

    else { // Lista não vazia  
        // Insere o novo nó no final da lista
        lista->cauda->prox = novo;
        novo->ante = lista->cauda;
        lista->cauda = novo;
    }
}

// Imprime a lista
void imprimir_lista () {

    if (lista == NULL) {
        printf("Lista vazia\n");
        return;
    }

    No *x = lista->cabeca; // Inicializa x com a "cabeca" da lista
    printf("\n(NULL)"); // Início da lista
    while (x != NULL) {
        printf("<- (%d) ->", x->chave);
        x = x->prox;
    }
    printf(" (NULL)\n\n"); // Fim da lista
}

// #########################################################################//
// FUNÇÕES PRINCIPAIS

/*
Objetivo: inserir número 'valor' em estrutura auxiliar da posição 'posicao'
Rertono (int)
    SUCESSO - inserido com sucesso
    SEM_ESPACO - não tem espaço
    SEM_ESTRUTURA_AUXILIAR - Não tem estrutura auxiliar
    POSICAO_INVALIDA - Posição inválida para estrutura auxiliar
CONSTANTES
*/
int inserirNumeroEmEstrutura(int posicao, int valor) { // OK

    int retorno = 0; // OK
    int existeEstruturaAuxiliar = 0; // OK
    int temEspaco = 0; // OK
    int posicao_invalida = 0; // OK

    // Posição inválida
    posicao_invalida = ehPosicaoValida(posicao);
    if (posicao_invalida == POSICAO_INVALIDA) {
        return POSICAO_INVALIDA; // Retorno imediato
    }

    // Posição válida
    else {
        // Converte a posição para base 0
        int index = posicao - 1;

        // Verifica se existe estrutura auxiliar na posição informada
        existeEstruturaAuxiliar = ehEstruturaAuxiliarExistente(index);

        // Estrutura auxiliar existente
        if (existeEstruturaAuxiliar == JA_TEM_ESTRUTURA_AUXILIAR) {
            
            // Verifica se tem espaço na estrutura auxiliar informada
            temEspaco = estruturaAuxiliarTemEspaco(index);
            
            // Existe espaço na estrutura auxiliar
            if (temEspaco == SUCESSO) {
                int *estAuxiliar = vetorPrincipal[index];
                
                // Insere o valor no vetor auxiliar
                int i = contAuxiliar[index];
                estAuxiliar[i] = valor;
                contAuxiliar[index] += 1;

                /*
                printf("contAuxiliar: ");
                imprimeVetor(contAuxiliar);
                printf("\n");
                */

                retorno = SUCESSO;
            }

            // Não existe espaço
            else {
                retorno = SEM_ESPACO;
            }
        }

        // Não existe estrutura auxiliar
        else {
            retorno = SEM_ESTRUTURA_AUXILIAR;
        }
    }
    return retorno;
}

/*
Objetivo: criar estrutura auxiliar na posição 'posicao'.
com tamanho 'tamanho'

Rertono (int)
    SUCESSO - criado com sucesso
    JA_TEM_ESTRUTURA_AUXILIAR - já tem estrutura na posição
    POSICAO_INVALIDA - Posição inválida para estrutura auxiliar
    SEM_ESPACO_DE_MEMORIA - Sem espaço de memória
    TAMANHO_INVALIDO - o tamanho deve ser maior ou igual a 1
*/
int criarEstruturaAuxiliar(int posicao, int tamanho) { // OK

    // Posição inválida
    if (ehPosicaoValida(posicao) == POSICAO_INVALIDA) {
        return POSICAO_INVALIDA;
    }

    // Converte a posição para base 0
    int index = posicao - 1;

    // Posição já possui uma estrutura auxiliar
    if (ehEstruturaAuxiliarExistente(index) == JA_TEM_ESTRUTURA_AUXILIAR) {
        return JA_TEM_ESTRUTURA_AUXILIAR;
    }

    // O tamanho é menor que 1
    if (tamanho < 1) {
        return TAMANHO_INVALIDO;
    }

    // Criar um vetor auxiliar, alocando memória dinamicamente
    int *estAuxiliar;
    estAuxiliar = (int *)malloc(tamanho * sizeof(int));

    // Falha na alocação de memória
    if (estAuxiliar == NULL) {
        /*
        Inclui:
        - Falta de memória;
        - Tentar alocar mais memória do que o sistema pode endereçar;
        - Tamanho maior que INT_MAX ou o limite do sistema.
        */
        return SEM_ESPACO_DE_MEMORIA;
    }

    // Cria a estrutura auxiliar
    if (estAuxiliar != NULL) {
        vetorPrincipal[index] = estAuxiliar;
        tamAuxiliar[index] = tamanho;
        
        /*printf("tamAuxiliar: ");
        imprimeVetor(tamAuxiliar);
        printf("\n");*/
    }
    return SUCESSO;
}

/*
Objetivo: excluir o numero 'valor' da estrutura auxiliar no final da estrutura.
ex: suponha os valores [3, 8, 7, 9,  ,  ]. Após excluir, a estrutura deve ficar da seguinte forma [3, 8, 7,  ,  ,  ].
Obs. Esta é uma exclusão lógica

Rertono (int)
    SUCESSO - excluido com sucesso
    ESTRUTURA_AUXILIAR_VAZIA - estrutura vazia
    SEM_ESTRUTURA_AUXILIAR - Não tem estrutura auxiliar
    POSICAO_INVALIDA - Posição inválida para estrutura auxiliar
*/
int excluirNumeroDoFinaldaEstrutura(int posicao) { // OK

    // Converte a posição para base 0
    int index = posicao - 1;
    
    // Posição inválida
    if (ehPosicaoValida(posicao) == POSICAO_INVALIDA) {
        return POSICAO_INVALIDA; // Retorno imediato
    }

    // Estrutura auxiliar não existe
    if (ehEstruturaAuxiliarExistente(index) == SEM_ESTRUTURA_AUXILIAR) {
        return SEM_ESTRUTURA_AUXILIAR; // Retorno imediato
    }

    // Estrutura auxiliar vazia
    if (contAuxiliar[index] == 0) {
        return ESTRUTURA_AUXILIAR_VAZIA; // Retorno imediato
    }

    // Remove o último elemento da estrutura auxiliar (exclusão lógica)
    
    /*printf("contAuxiliar: ");
    imprimeVetor(contAuxiliar);
    printf("\n");*/

    contAuxiliar[index] -= 1; // Decrementa a quantidade de elementos da estrutura
    
    /*printf("contAuxiliar: ");
    imprimeVetor(contAuxiliar);
    printf("\n");*/

    return SUCESSO;
}

/*
Objetivo: excluir o numero 'valor' da estrutura auxiliar da posição 'posicao'.
Caso seja excluido, os números posteriores devem ser movidos para as posições anteriores
ex: suponha os valores [3, 8, 7, 9,  ,  ] onde deve ser excluido o valor 8. A estrutura deve ficar da seguinte forma [3, 7, 9,  ,  ,  ]
Obs. Esta é uma exclusão lógica
Retorno (int)
    SUCESSO - excluido com sucesso 'valor' da estrutura na posição 'posicao'
    ESTRUTURA_AUXILIAR_VAZIA - estrutura vazia
    SEM_ESTRUTURA_AUXILIAR - Não tem estrutura auxiliar
    NUMERO_INEXISTENTE - Número não existe
    POSICAO_INVALIDA - Posição inválida para estrutura auxiliar
*/
int excluirNumeroEspecificoDeEstrutura(int posicao, int valor) { // OK

    // Converte a posição para base 0
    int index = posicao - 1;
    
    // Posição inválida
    if (ehPosicaoValida(posicao) == POSICAO_INVALIDA) {
        return POSICAO_INVALIDA; // Retorno imediato
    }

    // Estrutura auxiliar não existe
    if (ehEstruturaAuxiliarExistente(index) == SEM_ESTRUTURA_AUXILIAR) {
        return SEM_ESTRUTURA_AUXILIAR; // Retorno imediato
    }

    // Estrutura auxiliar vazia
    if (contAuxiliar[index] == 0) {
        return ESTRUTURA_AUXILIAR_VAZIA; // Retorno imediato
    }

    // Número inexistente
    if (shift(valor, index) == NUMERO_INEXISTENTE) {
        return NUMERO_INEXISTENTE;
    }

    // Número encontrado
    else {return SUCESSO;}
}

/*
Objetivo: retorna os números da estrutura auxiliar da posição 'posicao (1..10)'.
os números devem ser armazenados em vetorAux

Retorno (int)
    SUCESSO - recuperado com sucesso os valores da estrutura na posição 'posicao'
    SEM_ESTRUTURA_AUXILIAR - Não tem estrutura auxiliar
    POSICAO_INVALIDA - Posição inválida para estrutura auxiliar
*/
int getDadosEstruturaAuxiliar(int posicao, int vetorAux[]) { // OK

    // Converte a posição para base 0
    int index = posicao - 1;

    // Posição inválida
    if (ehPosicaoValida(posicao) == POSICAO_INVALIDA) {
        return POSICAO_INVALIDA; // Retorno imediato
    }

    // Estrutura auxiliar não existe
    if (ehEstruturaAuxiliarExistente(index) == SEM_ESTRUTURA_AUXILIAR) {
        return SEM_ESTRUTURA_AUXILIAR; // Retorno imediato
    }

    // Estrutura auxiliar vazia
    int cont = contAuxiliar[index]; // Quantidade de elementos
    if (cont == 0) {
        return SUCESSO; // Estrutura vazia, mas evita copiar dados
    }

    // Copia os dados para o vetorAux
    int *x = vetorPrincipal[index];
    for (int i = 0; i < cont; i++) {
        vetorAux[i] = x[i];
    }

    return SUCESSO;
}

/*
Objetivo: retorna os números ordenados da estrutura auxiliar da posição 'posicao (1..10)'.
os números devem ser armazenados em vetorAux

Rertono (int)
    SUCESSO - recuperado com sucesso os valores da estrutura na posição 'posicao (1..10)'
    SEM_ESTRUTURA_AUXILIAR - Não tem estrutura auxiliar
    POSICAO_INVALIDA - Posição inválida para estrutura auxiliar
*/
int getDadosOrdenadosEstruturaAuxiliar(int posicao, int vetorAux[]) { // OK

    // Converte a posição para base 0
    int index = posicao - 1;
    
    // Copia os números para o vetorAux
    int retorno = getDadosEstruturaAuxiliar(posicao, vetorAux);

    // Ordena o vetorAux
    int cont = contAuxiliar[index];
    if (retorno == SUCESSO) {insertionSort(vetorAux, cont);}
    
    return retorno;
}

/*
Objetivo: retorna os números de todas as estruturas auxiliares.
os números devem ser armazenados em vetorAux

Retorno (int)
    SUCESSO - recuperado com sucesso os valores da estrutura na posição 'posicao'
    TODAS_ESTRUTURAS_AUXILIARES_VAZIAS - todas as estruturas auxiliares estão vazias
*/
int getDadosDeTodasEstruturasAuxiliares(int vetorAux[]) { // OK

    bool estAuxVazias = true; // Assumindo que todas as est. aux. estão vazias
    int k = 0; // Para percorrer o vetorAux

    // Verifica se todas as estruturas estão vazias
    for (int i = 0; i < TAM; i++) { // Percorre o vetorPrincipal e o contAuxiliar
        
        // Estrutura auxiliar não vazia
        if (contAuxiliar[i] != 0) {
            estAuxVazias = false;
            int *estAuxiliar = vetorPrincipal[i];
            int cont = contAuxiliar[i];
            
            for (int j = 0; j < cont; j++) { // Percorre a estrutura auxiliar
                vetorAux[k] = estAuxiliar[j];
                k++;
            }
        }
    }

    if (estAuxVazias == true) {
        return TODAS_ESTRUTURAS_AUXILIARES_VAZIAS;
    }
    else {return SUCESSO;}
}

/*
Objetivo: retorna os números ordenados de todas as estruturas auxiliares.
os números devem ser armazenados em vetorAux

Retorno (int)
    SUCESSO - recuperado com sucesso os valores da estrutura na posição 'posicao'
    TODAS_ESTRUTURAS_AUXILIARES_VAZIAS - todas as estruturas auxiliares estão vazias
*/
int getDadosOrdenadosDeTodasEstruturasAuxiliares(int vetorAux[]) { // OK
    
    // Copia os números para o vetorAux
    int retorno = getDadosDeTodasEstruturasAuxiliares(vetorAux);

    // Todas as estruturas auxiliares vazias
    if (retorno == TODAS_ESTRUTURAS_AUXILIARES_VAZIAS) {
        return TODAS_ESTRUTURAS_AUXILIARES_VAZIAS; // Retorno imediato
    }

    // Calcula a quantidade total de elementos
    int cont = 0;
    for (int i = 0; i < TAM; i++) {
        cont += contAuxiliar[i];
    }

    // Ordena o vetorAux
    if (retorno == SUCESSO) {insertionSort(vetorAux, cont);}
    
    return SUCESSO;
}

/*
Objetivo: modificar o tamanho da estrutura auxiliar da posição 'posicao' para o novo tamanho 'novoTamanho' + tamanho atual
Suponha o tamanho inicial = x, e novo tamanho = n. O tamanho resultante deve ser x + n. Sendo que x + n deve ser sempre >= 1

Retorno (int)
    SUCESSO - foi modificado corretamente o tamanho da estrutura auxiliar
    SEM_ESTRUTURA_AUXILIAR - Não tem estrutura auxiliar
    POSICAO_INVALIDA - Posição inválida para estrutura auxiliar
    NOVO_TAMANHO_INVALIDO - novo tamanho não pode ser negativo
    SEM_ESPACO_DE_MEMORIA - erro na alocação do novo valor
*/
int modificarTamanhoEstruturaAuxiliar(int posicao, int novoTamanho) {

    // Posição inválida
    if (ehPosicaoValida(posicao) == POSICAO_INVALIDA) {
        return POSICAO_INVALIDA;
    }

    // Converte a posição para base 0
    int index = posicao - 1;

    // Não existe estrutura auxiliar na posição
    if (ehEstruturaAuxiliarExistente(index) == SEM_ESTRUTURA_AUXILIAR) {
        return SEM_ESTRUTURA_AUXILIAR;
    }
    
    // Calcula o novo tamanho
    int tamAtt = tamAuxiliar[index] + novoTamanho;

    // Tamanho atualizado é zero ou negativo
    if (tamAtt < 1) {
        return NOVO_TAMANHO_INVALIDO;
    }
    
    // Modifica o tamanho da estrutura auxiliar
    int *estAuxiliar = vetorPrincipal[index];
    estAuxiliar = realloc(estAuxiliar, tamAtt * sizeof(int));

    // Alocação falhou
    if (estAuxiliar == NULL) {
        /*
        Inclui:
        - Falta de memória;
        - Tentar alocar mais memória do que o sistema pode endereçar;
        - Tamanho maior que INT_MAX ou o limite do sistema.
        */
        return SEM_ESPACO_DE_MEMORIA;
    }

    // Atualiza o endereço e o tamanho da estrutura auxiliar
    vetorPrincipal[index] = estAuxiliar;
    tamAuxiliar[index] = tamAtt;

    // Exclusão lógica dos elementos
    if (tamAuxiliar[index] < contAuxiliar[index]) {
        contAuxiliar[index] = tamAuxiliar[index];
    }

    /*
    printf("tamAuxiliar: ");
    imprimeVetor(tamAuxiliar);
    printf("\n");
    
    printf("contAuxiliar: ");
    imprimeVetor(contAuxiliar);
    printf("\n");
    */
    
    return SUCESSO;
}

/*
Objetivo: retorna a quantidade de elementos preenchidos da estrutura auxiliar da posição 'posicao'.

Retorno (int)
    POSICAO_INVALIDA - posição inválida
    SEM_ESTRUTURA_AUXILIAR - sem estrutura auxiliar
    ESTRUTURA_AUXILIAR_VAZIA - estrutura auxiliar vazia
    Um número int > 0 correpondente a quantidade de elementos preenchidos da estrutura
*/
int getQuantidadeElementosEstruturaAuxiliar(int posicao) {

    // Posição inválida
    if (ehPosicaoValida(posicao) == POSICAO_INVALIDA) {
        return POSICAO_INVALIDA;
    }

    // Converte a posição para base 0
    int index = posicao - 1;

    // Não existe estrutura auxiliar na posição
    if (ehEstruturaAuxiliarExistente(index) == SEM_ESTRUTURA_AUXILIAR) {
        return SEM_ESTRUTURA_AUXILIAR;
    }

    // Estrutura auxiliar vazia
    if (contAuxiliar[index] == 0) {
        return ESTRUTURA_AUXILIAR_VAZIA;
    }

    // Estrutura auxiliar não vazia
    return contAuxiliar[index];    
}

/*
Objetivo: montar a lista encadeada com cabeçote com todos os números presentes em todas as estruturas.

Retorno (No*)
    NULL, caso não tenha nenhum número nas listas
    No*, ponteiro para o início da lista com cabeçote
*/
No *montarListaEncadeadaComCabecote() {
  
    // Percorre o vetorPrincipal e o contAuxiliar
    for (int i = 0; i < TAM; i++) { 
        
        // Estrutura auxiliar existe e é não vazia
        if (contAuxiliar[i] != 0) {

            // Copia o endereço e a quant. de elementos da estrutura auxiliar
            int *estAuxiliar = vetorPrincipal[i]; 
            int cont = contAuxiliar[i];
        
            // Percorre a estrutura auxiliar
            for (int j = 0; j < cont; j++) { 
                No *novo = init_No(estAuxiliar[j]); // Inicializa o nó
                inserir_No(novo); // Insere o nó na lista
            }
        }
    }  

    if (lista->cabeca == NULL) {
        return NULL;
    }

    //imprimir_lista();
    return lista->cabeca;
}

/*
Objetivo: retorna os números da lista enceada com cabeçote armazenando em vetorAux.
Retorno void
*/
void getDadosListaEncadeadaComCabecote(No *inicio, int vetorAux[]) {
    No* x = inicio;
    int k = 0;
    while (x != NULL) {
        vetorAux[k] = x->chave;
        k++;
        x = x->prox;
    }
}

/*
Objetivo: Destruir a lista encadeada com cabeçote a partir de início.
O ponteiro inicio deve ficar com NULL.

Retorno 
    void.
*/
void destruirListaEncadeadaComCabecote(No **inicio) {
    // Libera a memória alocada para a lista
    No *x = *inicio; // Copia o conteúdo de inicio, que é a cabeça da lista
    while (x != NULL) {
        No *temp = x;
        x = x->prox;
        free(temp); // Libera a memória de cada nó
    }
    free(lista); // Libera a memória da lista
    lista = NULL;
    *inicio = NULL;

    //imprimir_lista();
}

/*
Objetivo: inicializa o programa. deve ser chamado ao inicio do programa 
*/
void inicializar() {
    // Inicializa a lista
    lista = init_lista();
}

/*
Objetivo: finaliza o programa. deve ser chamado ao final do programa 
para poder liberar todos os espaços de memória das estruturas auxiliares.
*/
void finalizar() {
    
    // Libera a memória alocada para as estruturas auxiliares
    for (int i = 0; i < TAM; i++) {
        if (vetorPrincipal[i] != NULL) {
            free(vetorPrincipal[i]);
            vetorPrincipal[i] = NULL; // Reinicializa
        }
    }
}