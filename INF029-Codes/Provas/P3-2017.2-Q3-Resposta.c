/*
3) (3 pontos) Faça uma função (chamada de transfere) que leia um arquivo texto (nome notas.txt) que tem os seguintes dados em cada linha: <matrícula>;<média>;<faltas>. A função transfere deve escrever um novo arquivo texto (nome resutaldo.ads), com os seguintes dados em cada linha: <matrícula>;<status>. O status corresponde ao seguinte: “Reprovado por Falta”, se o número de faltas for maior ou igual a 10; “Reprovado por Média”, se tem menos de 10 faltas e média menor que 5; “Aprovado por média”, se tem menos de 10 faltas e média maior ou igual a 5. Faça os devidos tratamentos de erros possíveis. Veja um exemplo na tabela a seguir.
*/

#include <stdio.h>
#include <string.h>
#define tamLinha 30

void transfere (FILE *arqEntrada, FILE *arqSaida) {
    char linha[tamLinha];

    // Caso base: fim do arquivo
    if (fgets(linha, tamLinha, arqEntrada) == NULL) {
        return;
    }

    // Remove a quebra de linha
    linha[strcspn(linha, "\n")] = '\0';

    char mat[11]; // 10 + 1 do \0
    float media;
    int faltas;
    char resultado[22]; // Reprovado por Falta, Reprovado por Média ou Aprovado por Média

    // Obtém os dados separadamente para cada linha
    sscanf(linha, "%[^;];%f;%d", mat, &media, &faltas);

    // Imprime a matrícula no arquivo de saída
    fprintf(arqSaida, "%s;", mat);
    
    if (faltas >= 10) {
        strcpy(resultado, "Reprovado por Falta");
    }
    else if (media < 5) {
        strcpy(resultado, "Reprovado por Média");
    }
    else {
        strcpy(resultado, "Aprovado por Média");
    }

    // Imprime o resultado no arquivo de saída
    fprintf(arqSaida, "%s\n", resultado);

    // Chamada recursiva
    transfere(arqEntrada, arqSaida);
}

int main () {
    // Abre os arquivos
    FILE *arqEntrada = fopen("notas.txt", "r");
    FILE *arqSaida = fopen("resultado.ads", "w");

    if (arqEntrada == NULL || arqSaida == NULL) {
        printf("Erro ao abrir arquivos.\n");
        return 1;
    }

    // Função que trata os dados
    transfere(arqEntrada, arqSaida);

    // Fecha os arquivos
    fclose(arqEntrada);
    fclose(arqSaida);

    return 0;
}