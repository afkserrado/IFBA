/*
5 ) Duas árvores binárias são similares se elas são vazias ou se elas não são vazias e as subárvores esquerdas são similares entre si e as subárvores direitas são similares entre si. Escreva um algoritmo para determinar se duas árvores binárias são similares.
*/

int comparaArvores (no *x1, no *x2) {
    
    // Caso base: ambos são NULL
    if (x1 == NULL && x2 == NULL) {
        return 1; // Árvores similares
    }
    
    // Um nó é NULL e o outro não
    if (x1 == NULL || x2 == NULL) {
        return 0; // Árvores diferentes
    }
    
    // Chaves diferentes
    if (x1->chave != x2->chave) {
        return 0; // Árvores diferentes
    }
    
    // Recurssão
    int esq = comparaArvores(x1->esq, x2->esq); // Subárvore esquerda
    int dir = comparaArvores(x1->dir, x2->dir); // Subárvore direita
    
    // Retorna 1 se as subárvores são iguais e 0 se diferentes
    return esq && dir;
}