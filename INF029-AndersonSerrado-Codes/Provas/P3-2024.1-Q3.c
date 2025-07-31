#include <stdio.h>

void fa(int *b){
    int *c = b;
    if (*c > 0) {
        *c = *c - 1;
        fa(c);
        *b = *b - 1;
        fa(b);
        printf("c: %d\n", *c);
        printf("b: %d\n", *b);
    }
}

int main(){
    int i = 2;
    int *a = &i;
    fa(a);
}
