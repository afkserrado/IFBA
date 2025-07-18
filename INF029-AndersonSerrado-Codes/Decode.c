#include <stdio.h>
#include <ctype.h>
#include <string.h>

#define tamLinha 100
char vogais[] = {'A', 'E', 'I', 'O', 'U'};
char traducao[] = {'Z', 'Y', 'X', 'W', '*'};

void decodifica(char linha[]) {
    int len = strlen(linha);
    for (int i = 0; i < len; i++) { // Percorre a linha
        char c = toupper(linha[i]);

        for (int j = 0; j < 5; j++) { // Percorre as vogais
            if (c == vogais[j]) { // Traduz a linha
                if (islower(linha[i])) {linha[i] = tolower(traducao[j]);}
                else {linha[i] = traducao[j];}
                break;
            }
        }
    }
}

int main() {

    FILE *arqEntrada, *arqSaida;
    char linha[tamLinha];
    
    // Abre os arquivos
    arqEntrada = fopen("input.txt", "r");
    arqSaida = fopen("output.txt", "w");

    if (!arqEntrada || !arqSaida) {
        printf("Erro ao abrir arquivos.\n");
        return 1;
    }

    // Percorre o arquivo de entrada até o final
    while (fgets(linha, sizeof(linha), arqEntrada) != NULL) {
        linha[strcspn(linha, "\n")] = '\0';
        decodifica(linha);
        fprintf(arqSaida, "%s\n", linha);
    }

    fclose(arqEntrada);
    fclose(arqSaida);

    return 0;
}
