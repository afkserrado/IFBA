/*
2) (2 pontos) Crie uma função recursiva imprimeInvertido (…) que recebe uma string e imprime ela de trás para frente. Exemplo, quando enviar “ola”, será impresso “alo”.
*/

#include <stdio.h>
#include <string.h>

void imprimeInvertido (char str[], int inicio, int fim) {
    // Caso base
    if (fim < inicio) {
        return;
    }

    printf("%c", str[fim]);
    
    // Chamada recursiva
    imprimeInvertido(str, inicio, fim - 1);
}

int main () {

    char str[100] = "Testando impressao invertida";
    int len = strlen(str);
    imprimeInvertido(str, 0, len - 1);
    printf("\n");

    return 0;
}