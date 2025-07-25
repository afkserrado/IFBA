/*
4) Apresente um algoritmo que percorra uma árvore binária e conte quantos nós são folha.
*/

void cont_folhas (arvore *arv, no *x, int *folhas) {
    // x inicialmente é a raiz da árvore
    
    // Árvore inexistente ou vazia
    if (arv == NULL || x == NULL) {
        return;
    }
    
    // Caso base: nó não tem filhos
    if (x->esq == NULL && x->dir == NULL) {
        (*folhas)++;
    }    
    
    // Percorre recurssivamente a árvore
    cont_folhas(arv, x->esq, folhas);
    cont_folhas(arv, x->dir, folhas);
}