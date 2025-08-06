/*
2) Considere o exemplo abaixo de rotação simples a direita, apresente um trecho de código que dado o nó com FB=-2 efetue uma rotação simples a direita deste nó.
*/

void rot_dir (arvore *arv, no *v) {
    if (v == NULL) {return;}

    no *u = v->esq;
    no *t = u->dir;
    no *mae_v = v->mae;

    // Rotação
    u->dir = v;
    v->esq = t;
    v->mae = u;

    if (t != NULL) {
        t->mae = v;
    }

    u->mae = mae_v;

    if (mae_v == NULL) {
        arv->raiz = u;
    }
    else if (mae_v->esq == v) {
        mae_v->esq = u;
    }
    else {
        mae_v->dir = u;
    }
}