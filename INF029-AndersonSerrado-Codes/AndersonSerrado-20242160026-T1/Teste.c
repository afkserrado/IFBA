#include <stdio.h>
#include <math.h>

int q5(int num) {
    
    #define tam 10
    int vetor[tam] = {0};
    int cnum = num;
    int flag = 1;
    
    // Converte para inteiro positivo
    if (num < 0) { 
        cnum = num * -1;
        flag = 0;
    }

    int i;
    for (i = 0; cnum != 0; i++) {
        vetor[i] = cnum % 10;
        cnum = cnum / 10;
    }

    num = 0; // Reset
    
    // Solução 1
    for (int j = 0; j < i; j++) {
        num = num * 10 + vetor[j];
    }
    
    /* Solução 2
    int k = 0;
    for (int j = i - 1; j >= 0; j--) {
        num += vetor[k] * pow(10, j);
        k++;
    }*/
    
    if (flag == 0) {num = num * -1;}

    return num;
}

int main () {

    printf("%d\n", q5(-345));
    printf("%d\n", q5(78));
    printf("%d\n", q5(3));
    printf("%d\n", q5(456));
    printf("%d\n", q5(430));
    printf("%d\n", q5(100));
}