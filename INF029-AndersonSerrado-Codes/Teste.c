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

/*-------------------------------------------------------------------------------------------------------*/
// Constantes globais

#define tamNome 100
#define tamCPF 11
#define tamSexo 1
#define tamAlunos 10000
#define tamProfessores 100
#define tamDisciplinas 1000

/*-------------------------------------------------------------------------------------------------------*/
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

pessoa alunos[tamAlunos];
pessoa professores[tamProfessores];

/*-------------------------------------------------------------------------------------------------------*/
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

/*-------------------------------------------------------------------------------------------------------*/
// Valida a existência de um número de matrícula ou CPF
bolean existeMatricula(int tamMat_CPF, char entrada_Mat_CPF[tamMat_CPF + 2]) {
    
    // Verifica se a matrícula já está cadastrada
    for (int i = 0; i < contPessoa; i++){
        if (strcmp(matricula, pessoas[i].matricula) == 0){ // Matrícula já cadastrada
            printf("\nMatrícula já cadastrada no sistema.\n");
            achou = 1;
            pausarTela();
            limparTela();
            break; // Sai do for
        }
    }
}

// Valida condições exclusivas do CPF
int validarCPF (char CPF[tamCPF]) {

    // Verifica se todos os dígitos são iguais
    int todosIguais = 1;
    for (int i = 1; i < tamCPF; i++) {
        if (CPF[i] != CPF[0]) {
            todosIguais = 0;
            break;
        }
    }
    if (todosIguais == 1) {
        printf("Erro: os dígitos do CPF não podem ser todos iguais.\n");
        return 1;
    }

    // Verifica o 10º dígito (1º verificador)
    int soma = 0;
    for (int i = 0; i < 9; i++) { //Percorre do 1º ao 9º dígito
        soma += (CPF[i] - '0') * (10 - i);
    }
    int resto = soma % 11;

    // Calcula o 1º dígito verificador
    int digito1 = (resto < 2) ? 0 : (11 - resto);

    // Verifica se o 10º dígito é igual ao dígito verificador
    if (CPF[9] - '0' != digito1) {
        printf("Erro: o 10º dígito deveria ser %d.\n", digito1);
        return 1;
    }

    // Verifica o 11º dígito (2º verificador)
    soma = 0;
    for (int i = 0; i < 10; i++) { // Percorre do 1º ao 10º dígito
        soma += (CPF[i] - '0') * (11 - i);
    }
    resto = soma % 11;

    // Calcula o 2º dígito verificador
    int digito2 = (resto < 2) ? 0 : (11 - resto);

    // Verifica se o 11º dígito é igual ao dígito verificador
    if (CPF[10] - '0' != digito2) {
        printf("Erro: o 11º dígito deveria ser %d.\n", digito2);
        return 1;
    }
        
    return 0; // CPF válido
}

// Valida um número de matrícula ou CPF
int validarMat_CPF (int tamMat_CPF, char entrada_Mat_CPF[tamMat_CPF + 2], char texto[], char texto_pessoa[]) {
    
    // entrada_Mat_CPF: ponteiro que aponta para o endereço de memória do vetor "matricula" ou "CPF"
    // "matricula" e "CPF" são vetores pertencentes à função que chamou validarNome
    // tamMat_CPF + 2 = +1 do \n e +1 do \0

    // Zerando a variável temporária
    entrada_Mat_CPF[0] = '\0';

    // Entrada de dados
    printf("Informe o(a) %s do(a) %s(a): ", texto, texto_pessoa);
    if (fgets(entrada_Mat_CPF, tamMat_CPF + 2, stdin) == NULL) {
        printf("Erro ao ler o(a) %s.\n", texto); // Trata erro de leitura
        return 1;
    }

    // Verifica truncamento e remove \n
    if (strchr(entrada_Mat_CPF, '\n') == NULL) {
        limparBuffer(); // Limpa o excesso de caracteres
        printf("Erro: excesso de caracteres (máx. %d caracteres)\n", tamMat_CPF);
        return 1; // Input truncado = inválido
    } else {
        // Substitui a quebra de linha \n pelo terminador nulo \0
        entrada_Mat_CPF[strcspn(entrada_Mat_CPF, "\n")] = '\0';
    }    

    // Calcula o tamanho da matrícula
    int len = strlen(entrada_Mat_CPF);
    int tamEsperado = tamMat_CPF;
    if (entrada_Mat_CPF[0] == '-') tamEsperado++;
    
    // Verificações de erros
    int erro = 0;
    
    // Verifica o tamanho da matrícula
    if (len != tamEsperado) {
        printf("Erro: %s deve conter %d dígitos.\n", texto, tamMat_CPF);
        erro = 1;
    }
    
    // Verifica se é apenas "-"
    if (len == 1 && entrada_Mat_CPF[0] == '-') {
        printf("Erro: %s não pode conter caracteres não numéricos.\n", texto);
        erro = 1;
    }
    
    // Verifica se todos os dígitos são numéricos
    int erroCaractere = 0; // Apenas números
    int j = (entrada_Mat_CPF[0] == '-') ? 1 : 0; // Desconsidera o caractere '-'
    for (int i = j; i < len; i++) {
        if (!isdigit(entrada_Mat_CPF[i])) {
            erroCaractere = 1; // Contém caracteres não numéricos
            break;
        }
    }
    
    // Número negativo
    if (entrada_Mat_CPF[0] == '-' && isdigit(entrada_Mat_CPF[1])) {
        printf("Erro: %s não pode ser um número negativo.\n", texto);
        
        // Caracteres não numéricos
        if (erroCaractere == 1) {
            printf("Erro: %s não pode conter caracteres não numéricos.\n", texto);
        }
        erro = 1;
    }
    // Demais casos
    else if (erroCaractere == 1){
        printf("Erro: %s não pode conter caracteres não numéricos.\n", texto);
        erro = 1;
    }

    if (erro) {
        return 1;
    }

    // Verifica se a matrícula já existe
    if (strcmp(texto, "matrícula") == 0) { // Se o texto for matrícula
        if (existeMatricula(tamMat_CPF, entrada_Mat_CPF) != 0) { // Chama a função existeMatricula
            return 1; // Matrícula existente 
        }
    }
    // Verifica se o CPF já existe
    else {
        if (existeCPF(tamMat_CPF, entrada_Mat_CPF) != 0) { // Chama a função existeCPF
            return 1; // CPF existente 
        }
    }

    // Se for CPF, chama a função validarCPF para verificar outras condições
    if (strcmp(texto, "CPF") == 0) { // Se o texto for CPF
        if (validarCPF(entrada_Mat_CPF) != 0) { // Chama a função validarCPF
            return 1; // CPF inválido 
        }
    }

    return 0; // Matrícula ou CPF válido
}

int main() {

    configurarLocale();

    // Para testes
    int contAluno = 0;
    int tamMatricula = 5;
    char texto_pessoa[] = "aluno";


    // Variáveis auxiliares
    int flagMatricula = 0; 
    char matricula[tamMatricula + 1]; // Variável temporária (+1 do \0)
    char textoMat[] = "matricula";

    // Recebe e valida a matrícula

    do {
        flagMatricula = validarMat_CPF(tamMatricula, matricula, textoMat, texto_pessoa);
        if (flagMatricula != 0) {
            printf("\n");
        }
    } while (flagMatricula != 0);

    printf("Matrícula: %s\n", matricula);
    printf("\n");
    contAluno++; // Incrementa a contagem de alunos

}