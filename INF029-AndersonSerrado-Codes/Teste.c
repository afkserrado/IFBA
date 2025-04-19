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

/*------------------------------------------------------------------------------------------------------------*/

// Verifica se o ano é bissexto
int bissexto(int ano) {
    if(((ano % 4 == 0) && (ano % 100 != 0)) || (ano % 400 == 0)){
        return 1; // é bissexto
    }
    else {
        return 0; // Não é bissexto
    }
}

// Valida a data de nascimento
int validarData(int* entrada_dia, int* entrada_mes, int* entrada_ano, char texto_pessoa[]) {
    
    // entrada_dia etc.: ponteiros que apontam para o endereço de memória dos vetors "dia" etc.
    // "dia" etc. são vetores pertencentes à função que chamou validarData

    int flagBissexto = bissexto(*entrada_ano);
    int diasMes[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    
    printf("Informe a data de nascimento do %s (DD/MM/AAAA): ", texto_pessoa);
    if(scanf("%d/%d/%d", entrada_dia, entrada_mes, entrada_ano) != 3) {
        while (getchar() != '\n');  // Limpa o buffer até encontrar a quebra de linha

        printf("Erro: a data deve estar no formato dd/mm/aaaa.\n");
        return 1;
    }

    // Valida o ano
    if (*entrada_ano < 1900 || *entrada_ano > 2100) {
        printf("Erro: o ano deve estar entre 1900 e 2100.\n");
        return 1;
    }
    
    // Valida o mês
    if (*entrada_mes < 1 || *entrada_mes > 12) {
        printf("Erro: o mês deve estar entre 1 e 12.\n");
        return 1; // Mês inválido
    }

    // Atualiza a qtd de dias em fevereiro se o ano for bissexto
    if (flagBissexto == 1) {
        diasMes[1] = 29; // Fevereiro
    }

    // Valida o dia
    if (*entrada_dia < 1 || *entrada_dia > diasMes[*entrada_mes - 1]) {
        printf("Erro: o dia deve estar entre 1 e %d.\n", diasMes[*entrada_mes - 1]);
        return 1; // Dia inválido
    }
    
    // Calcula a quantidade de segundos entre a data/horário atual e a data referencial (01/01/1970)
    // e armazena na variável tipo time_z
    time_t agora = time(NULL);

    // Cria e zera a variável nascimento, do tipo tm, que contém campos para data e hora
    struct tm nascimento = {0};

    // Armazena a data de nascimento
    nascimento.tm_mday = *entrada_dia;
    nascimento.tm_mon = *entrada_mes - 1; // O índice de Janeiro é 0
    nascimento.tm_year = *entrada_ano - 1900; // Anos desde 1900
    nascimento.tm_isdst = -1; // Auto-detecta horário de verão

    // Converte a data de nascimento de tm (data/hora em campos separados) para time_t (segundos desde o referencial)
    time_t nasc_seg = mktime(&nascimento);

    // Verifica se a conversão foi bem-sucedida
    if (nasc_seg == (time_t) - 1) {
        printf("Erro: data inválida ou impossível.\n");
        return 1;
    }

    // Verifica se a data é futura
    if (difftime (agora, nasc_seg) < 0) {
        printf("Erro: a data de nascimento é posterior à data atual.\n");
        return 1;
    }

    return 0; // Data válida
}

/*------------------------------------------------------------------------------------------------------------*/

int main() {
    
    char texto_pessoa[] = "aluno";
    int contAluno = 0; // Para fins de testes

// Variáveis auxiliares
int flagData = 0;
int dia, mes, ano; // Variáveis temporárias

    // Recebe e valida a data
    do {
        flagData = validarData(&dia, &mes, &ano, texto_pessoa);

        if (flagData != 0)
            printf("\n");
    } while (flagData != 0);

    printf("Data: %02d/%02d/%02d\n", dia, mes, ano); // Para fins de testes
    
    contAluno++; // Para fins de testes

}