#include <stdio.h>

int divisao(int *result, float a, float b) {
    int ret = 0;
    if (b != 0) { // Caso tudo ocorra bem, retornará 0, e *result será a divisão
        *result = a / b;
    } else { // Caso tente dividir por 0, retorna um código de erro (-1)
        ret = -1;
    }
    return ret;
}

int main() {
    float a = 10.0, b = 2.0;
    int resultado;
    int status = divisao(&resultado, a, b); // Recebe o valor de ret da função divisao

    if (status == 0) {
        printf("Resultado da divisão: %d\n", resultado); // Exibe o resultado da divisão
    } else {
        printf("Erro: divisão por zero!\n"); // Exibe mensagem de erro se a divisão for por zero
    }

    return 0;
}