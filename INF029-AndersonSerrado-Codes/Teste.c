// Bibliotecas

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <time.h>
#include <locale.h>
#include <limits.h>
#include <wctype.h> // Funções para trabalhar com caracteres com múltiplos bytes, como letras acentuadas e outros do Unicode
#include <wchar.h>  // Funções para manipular strings de caracteres com múltiplos bytes (wchar_t)

/*------------------------------------------------------------------------------------------------------------*/
// Constantes globais

#define tamNome 100
#define tamCPF 11
#define tamSexo 1
#define tamAlunos 10000
#define tamProfessores 100
#define tamDisciplinas 1000

/*------------------------------------------------------------------------------------------------------------*/

// Subfunções

//Configurando a codificação de caracteres
void configurarLocale () {
    #if defined(_WIN32) || defined(_WIN64) // Windows
        setlocale(LC_ALL, "Portuguese");

    #elif defined(__linux__) || defined(__unix__) || defined(__APPLE__) || defined(__MACH__) // Linux e macOS
        setlocale(LC_ALL, "pt_BR.UTF-8");  
        
    #endif
}

// Limpa o buffer
void limparBuffer() {
    int c;
    while ((c = getchar()) != '\n' && c != EOF) {
        // Loop intencionalmente vazio para consumir caracteres
    }
}

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

// Valida o nome
int validarNome(char entrada_nome[tamNome + 2], char texto[]) {
    
    // entrada_nome: ponteiro que aponta para o endereço de memória do vetor "nome"
    // "nome" é o vetor pertencente à função que chamou validarNome
    // tamNome + 2: tamNome caracteres úteis + \n + \0. Garante que o usuário consiga digitar até tamNome caracteres

    // Entrada de dados
    printf("Informe o nome do %s: ", texto);
    if (fgets(entrada_nome, tamNome + 2, stdin) == NULL) {
        printf("Erro ao ler o nome.\n");
        return 1;
    }

    // Verifica truncamento e remove \n
    if (strchr(entrada_nome, '\n') == NULL) {
        limparBuffer(); // Limpa o excesso de caracteres
        printf("Erro: excesso de caracteres (máx. %d caracteres)\n", tamNome);
        return 1; // Input truncado = inválido
    } else {
        // Substitui a quebra de linha \n pelo terminador nulo \0
        entrada_nome[strcspn(entrada_nome, "\n")] = '\0';
    }    

    // Comprimento
    int len = strlen(entrada_nome);
    
    // Verifica se o nome está vazio
    if (len == 0) {
        printf("Erro: o nome não pode estar vazio.\n");
        return 1;
    }

    // wchar_t: equivalente a char, mas para caracteres multibyte
    wchar_t wentrada_nome[len + 1]; //Vetor de tamanho len + 1, pois strlen não conta o \0
    mbstowcs(wentrada_nome, entrada_nome, len + 1); // Converte de char para wchar_t e guarda

    // Verifica se o nome contém caracteres inválidos
    for (int i = 0; wentrada_nome[i] != L'\0'; i++){
        wchar_t c = wentrada_nome[i];
        
        // Permite letras, caracteres acentuados, ç, apóstrofo ('), hífen (-) e espaços
        if (!(iswalpha(c) || c == L' ' || c == L'-' || c == L'\'')) {
            printf("Erro: o nome contém caracteres inválidos.\n");
            return 1;
        }

        // Verifica se o nome contém espaços consecutivos
        if (i < len - 1 && wentrada_nome[i] == L' ' && wentrada_nome[i + 1] == L' ') {
            printf("Erro: o nome contém espaços consecutivos.\n");
            return 1;
        }

        // Verifica se o nome começa ou termina com caracteres inválidos
        if ((i == 0 || i == len - 1) && (c == L' ' || c == L'-' || c == L'\'')) {
            printf("Erro: o nome não pode começar ou terminar com espaço, hífen ou apóstrofo.\n");
            return 1;
        }
    }

    // Padronizando o formato do nome
    for (int i = 0; wentrada_nome[i] != L'\0'; i++){
        wchar_t c = wentrada_nome[i];
        
        // Capitaliza a primeira letra do nome
        if (i == 0) {
            wentrada_nome[i] = towupper(c);
        }
        // Capitaliza a primeira letra dos n nomes e sobrenomes
        else if (iswalpha(c) && wentrada_nome[i - 1] == ' ') {
            wentrada_nome[i] = towupper(c);
        }
        // Converte as demais letras para minúsculas
        else {
            wentrada_nome[i] = towlower(c);
        }
    }

    wcstombs(entrada_nome, wentrada_nome, len + 1);
    entrada_nome[len] = '\0';
    
    return 0; // Nome válido
}

int main() {

    configurarLocale();

    char texto_pessoa[] = "aluno";
    int contAluno = 0;

    // ### INÍCIO - NOME ###
    // Variáveis auxiliares
    int flagNome = 0;
    char nome[tamNome]; // Variável temporária
    
    // Recebe e valida o nome
    do {
        flagNome = validarNome(nome, texto_pessoa);

        if (flagNome != 0)
            printf("\n");
    } while (flagNome != 0);
    
    // Armazena o nome
    strcpy(aluno[contAluno].nome, nome);
    printf("Nome: %s\n", aluno[contAluno].nome);

    contAluno++;

    // ### FIM - NOME ###
}