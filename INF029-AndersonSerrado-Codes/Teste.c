// Online C compiler to run C program online
#include <stdio.h>

int main() {
    
    int mes;
    
    do {
        // Verifica se a entrada é válida (um número inteiro)
        printf("Informe um mês (1 a 12): ");
        if (scanf("%d", &mes) != 1) {
            // Caso não seja um número válido, limpa o buffer e exibe uma mensagem de erro
            printf("Entrada inválida. Por favor, insira um número.\n\n");
            // Limpa o buffer de entrada
            while (getchar() != '\n');
            continue;
        }

        if (mes < 1 || mes > 12) printf("Mês inválido.\n\n");

    } while (mes < 1 || mes > 12);
    
    printf("\nMês: %d\n", mes);
}