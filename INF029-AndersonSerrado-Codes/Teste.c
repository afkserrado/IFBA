#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

// Valida a matrícula
int validarMatricula (int tamMatricula) {
    
    char matricula[tamMatricula + 2]; //+2 para o \n e o \0

    // Lê a entrada
    if (fgets(matricula, sizeof(matricula), stdin) == NULL) {
        printf("\nErro ao ler a matrícula.\n");
        return 1;
    }
    
    // Verifica se a entrada ultrapassou o buffer
    if (strchr(matricula, '\n') == NULL) {
        printf("Erro: a matrícula contém mais de %d dígitos.\n", tamMatricula);
        while (getchar() != '\n'); // Limpa o buffer
        return 1;
    }
    
    // Substitui o \n pelo terminador nulo \0
    matricula[strcspn(matricula, "\n")] = '\0';

    // Calcula o tamanho da matrícula
    int len = strlen(matricula);
    int tamEsperado = tamMatricula;
    if (matricula[0] == '-') tamEsperado++;
    
    // Verificações de erros
    int erro = 0;
    
    // Verifica o tamanho da matrícula
    if (len != tamEsperado) {
        printf("Erro: a matrícula deve conter %d dígitos.\n", tamMatricula);
        erro = 1;
    }
    
    // Verifica se é apenas "-"
    if (len == 1 && matricula[0] == '-') {
        printf("Erro: a matrícula não pode conter caracteres não numéricos.\n");
        erro = 1;
    }
    
    // Verifica se todos os dígitos são numéricos
    int erroCaractere = 0; // Apenas números
    int j = (matricula[0] == '-') ? 1 : 0; // Desconsidera o caractere '-'
    for (int i = j; i < len; i++) {
        if (!isdigit(matricula[i])) {
            erroCaractere = 1; // Contém caracteres não numéricos
            break;
        }
    }
    
    // Número negativo
    if (matricula[0] == '-' && isdigit(matricula[1])) {
        printf("Erro: a matrícula não pode ser um número negativo.\n");
        
        // Caracteres não numéricos
        if (erroCaractere == 1) {
            printf("Erro: a matrícula não pode conter caracteres não numéricos.\n");
        }
        erro = 1;
    }
    // Demais casos
    else if (erroCaractere == 1){
        printf("Erro: a matrícula não pode conter caracteres não numéricos.\n");
        erro = 1;
    }

    if (erro) {
        return 1;
    }
    
    printf("Matrícula: %s", matricula);

    return 0; // Matrícula válida
}

int main() {
    
    int flagMatricula = 0;
    int tamMatricula = 11;
    // Recebe e valida a matrícula
    do {
        printf("Informe a matrícula do aluno: ");
        flagMatricula = validarMatricula(tamMatricula);
        //printf("%d", flagMatricula);
        
        if (flagMatricula == 1 && feof(stdin)) { // Detecta EOF
            printf("\nMódulo encerrado.\n");
            break;
        }

        if (flagMatricula != 0)
            printf("\n");
    } while (flagMatricula != 0);
}