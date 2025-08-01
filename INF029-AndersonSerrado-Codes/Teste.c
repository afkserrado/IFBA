#include <stdio.h>
#include <stdlib.h>

void lerArquivo(FILE *entrada) {
    char *linha = NULL;
    size_t len = 0;
    ssize_t read;

    // Lê o arquivo linha por linha
    while ((read = getline(&linha, &len, entrada)) != -1) {
        printf("Dado: %s", linha);
    }

    // Libera a memória alocada
    free(linha);
}

int main() {
    FILE *entrada;

    // Abre o arquivo para leitura
    if ((entrada = fopen("P3-2023.1-Q3-Resposta-Dados.txt", "r")) == NULL) {
        printf("Erro ao abrir arquivo.\n");
        return 1;
    }

    // Chama a função recursiva para começar a ler o arquivo
    //lerArquivo(entrada);

    // Fecha o arquivo após a leitura
    fclose(entrada);

    return 0;
}