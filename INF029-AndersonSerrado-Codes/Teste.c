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
    char genero[3]; //+1 para o terminador nulo
    data nascimento;
} pessoa;

pessoa aluno[tamAlunos];
pessoa professor[tamAlunos];

// Cadastro das disciplinas
typedef struct {
    char *matricula; // Alocação dinâmica
    char *codigo; // Alocação dinâmica
    int semestre;
} listaDisciplinas;

listaDisciplinas disciplina[tamDisciplinas];

/*------------------------------------------------------------------------------------------------------------*/

// Valida o nome
int validarNome(char entrada_nome[], char texto[]) {
    
    // entrada_nome: vetor que aponta para o endereço de memória do vetor "nome"
    // "nome" é o vetor pertencente à função que chamou validarNome

    // Zerando a variável temporária
    entrada_nome[0] = '\0';

    // Entrada de dados
    printf("Informe o nome do %s: ", texto);
    if (fgets(entrada_nome, tamNome, stdin) == NULL) {
        printf("Erro na leitura do nome.\n");
        return 1;
    }

    // Remove a quebra de linha
    entrada_nome[strcspn(entrada_nome, "\n")] = '\0';

    // Comprimento
    int len = strlen(entrada_nome);

    // Verifica se o nome não está vazio
    if (len == 0) {
        printf("Erro: o nome não pode estar vazio.\n");
        return 1;
    }

    // Verifica se o nome contém caracteres inválidos
    for (int i = 0; i < len; i++){
        // Permite letras, espaços e caracteres acentuados
        if (!(isalpha(entrada_nome[i]) || entrada_nome[i] == ' ')) {
            printf("Erro: o nome contém caracteres inválidos.\n");
            return 1;
        }
    }
    
    return 0; // Nome válido
}

/*------------------------------------------------------------------------------------------------------------*/

int main() {
    
    int contAluno = 0; // Para fins de testes

    // Variáveis auxiliares
    int flagNome = 0;
    char nome[tamNome]; // Variável temporária
    char texto[] = "aluno";
    
    // Recebe e valida o nome
    do {
        flagNome = validarNome(nome, texto);
        //printf("%d", flagNome);

        if (flagNome != 0)
            printf("\n");
    } while (flagNome != 0);
    
    // Armazena o nome
    strcpy(aluno[contAluno].nome, nome);

    printf("Nome: %s\n", nome); // Para fins de testes
    
    contAluno++; // Para fins de testes

}