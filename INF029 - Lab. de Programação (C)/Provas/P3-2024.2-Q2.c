#include <stdio.h>

void fb(int *a){
    *a = *a + 1;
    if (*a > 1) {
        printf("a1: %d\n", *a);
    }
    printf("a2: %d\n", *a);
}

void fa(int *b){ 
    int *c = b;
    int *d;
    
    if (*c > 0){
        *c = *c - 1;
        fa(c);
        *b = *b - 1;
        fa(b);
        d = c;
        if (*d % 2 != 0){
            fb(d);
            printf("d: %d\n", *d);
        }
        *d = *d - 1;
        fa(d);
        printf("c: %d\n", *c);
        printf("b: %d\n", *b);
    }
}

int main(){
    int i = 3;
    int *a = &i;
    fa(a);
}