/*
6) Duas árvores binárias são equivalentes se elas são vazias; ou, se elas não são vazias e possuem o mesmo valor de chave associado a raiz, as subárvores esquerdas são equivalentes entre si e as subárvores direitas são equivalentes entre si. Escreva um algoritmo para determinar se duas árvores binárias são equivalentes.
*/

/*
Duas árvores são equivalentes se:
- São vazias;
- Se não são vazias;
    - Possuem a mesma raiz;
    - Subárvores esquerdas são equivalentes;
    - Subárvores direitas são equivalentes.
*/

int comparaArvores (no *x1, no *x2) {
    
    // Caso base: ambos são NULL
    if (x1 == NULL && x2 == NULL) {
        return 1; // Árvores equivalentes
    }

    // Um nó é NULL e o outro não
    if (x1 == NULL || x2 == NULL) {
        return 0; // Árvores diferentes
    }

    // Compara o valor dos nós atuais, incluindo a raiz das árvores e a raiz de cada subárvore
    if (x1->chave != x2->chave) {
        return 0; // Árvores diferentes
    }
        
    // Recursão
    int esq = comparaArvores(x1->esq, x2->esq); // Subárvore esquerda
    int dir = comparaArvores(x1->dir, x2->dir); // Subárvore direita
    
    // Retorna 1 se as subárvores são iguais e 0 se diferentes
    return esq && dir;
}