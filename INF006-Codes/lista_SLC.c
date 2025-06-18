#include<stdio.h>
#include<stdlib.h>

// Lista duplamente encadeada circular

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