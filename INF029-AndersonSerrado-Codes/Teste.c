// Bibliotecas
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <time.h>
#include <locale.h>
#include <limits.h>

/*------------------------------------------------------------------------------------------------------------*/
// Constantes globais

#define tamNome 100
#define tamData 10
#define tamCPF 11
#define tamSexo 2
#define tamAlunos 10000
#define tamProfessores 100
#define tamDisciplinas 1000

/*------------------------------------------------------------------------------------------------------------*/

// Structs globais

// Data
typedef struct {
    int dia;
    int mes;
    int ano;
} data;

// Cadastro de pessoas
typedef struct {
    char *matricula; // Alocação dinâmica
    char nome[tamNome];
    char CPF[tamCPF + 1]; //+1 para o terminador nulo
    char sexo[2]; //+1 para o terminador nulo
    data nascimento;
} pessoa;

pessoa aluno[tamAlunos];

/*------------------------------------------------------------------------------------------------------------*/

// Limpa o buffer
void limparBuffer() {
    int c;
    while ((c = getchar()) != '\n' && c != EOF) {
        // Loop intencionalmente vazio para consumir caracteres
    }
}

// Valida o sexo
int validarSexo(char entrada_sexo[], char texto_pessoa[]) {
    
    // entrada_sexo: ponteiro que aponta para o endereço de memória do vetor sexo
    // "sexo" é um vetor pertencente à função que chamou validarNome
    
    // Entrada de dados
    printf("Informe o sexo (M ou F) do %s: ", texto_pessoa);
    if (fgets(entrada_sexo, tamSexo, stdin) == NULL) {
        return 1; // Trata erro de leitura
    }

    // Remove a quebra de linha
    entrada_sexo[strcspn(entrada_sexo, "\n")] = '\0';
    
    // Converte os caracteres para maiúsculo
    entrada_sexo[0] = toupper(entrada_sexo[0]);
    
    if (entrada_sexo[0] != 'M' && entrada_sexo[0] != 'F') {
        printf("Erro: o sexo só pode ser m, M, f ou F.\n");
        return 1;
    }

    return 0; // Sexo válido
}

/*------------------------------------------------------------------------------------------------------------*/

int main() {
    
    char texto_pessoa[] = "aluno";
    int contAluno = 0; // Para fins de testes
    

    int flagSexo = 0;
    char sexo[tamSexo]; // Variável temporária
    
    // Recebe e valida o sexo
    do {
    
        flagSexo = validarSexo(sexo, texto_pessoa);

        limparBuffer();
    
        if (flagSexo != 0)
            printf("\n");
    } while (flagSexo != 0);
    
    strcpy(aluno[contAluno].sexo, sexo);
    
    printf("\n");
    printf("Sexo: %s\n", aluno[contAluno].sexo); // Para fins de testes
    
    contAluno++; // Para fins de testes

}