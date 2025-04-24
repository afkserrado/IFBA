/*
Instituto Federal da Bahia (IFBA)
Tecnólogo em Análise e Desenvolvimento de Sistemas (ADS)
Semestre 2025.1
INF029 - Laboratório de Programação
Professor: Renato Novais
Aluno: Anderson Serrado
*/

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

/*--------------------------------------------------------------------------------------------------*/
// Macros

// Constantes

#define tamNome 102 // n caracteres + \n + \0
#define tamMatricula 5 // n caracteres + \n + \0 // Mudar para 13
#define tamCPF 13 // n caracteres + \n + \0
#define tamSexo 3 // n caracteres + \n + \0
#define tamAlunos 3 // Mudar para 10000
#define tamProfessores 3 // Mudar para 100
#define tamDisciplinas 3 // Mudar para 1000
#define tamCodigo 8 // n caracteres + \n + \0
#define max_alunosMatriculados 50

// Textos  
#define txtAluno_ALS "aluno"
#define txtAluno_ALP "alunos"
#define txtAluno_FUS "Aluno"
#define txtAluno_FUP "Alunos"
#define txtProfessor_ALS "professor"
#define txtProfessor_ALP "professores"
#define txtProfessor_FUS "Professor"
#define txtProfessor_FUP "Professores"

/*--------------------------------------------------------------------------------------------------*/
// Structs globais

// Data
typedef struct {
    int dia;
    int mes;
    int ano;
} data;

// Alunos matriculados em disciplinas
typedef struct {
    char matriculaAluno[tamMatricula];
} disciplina_alunos;

// Cadastro de pessoas
typedef struct {
    char matricula[tamMatricula];
    char nome[tamNome];
    char CPF[tamCPF];
    char sexo[tamSexo];
    data nascimento;
} pessoa;

// Cadastro das disciplinas
typedef struct {
    char codigo[tamCodigo];
    char nome[tamNome];
    int semestre;
    char matriculaProfessor[tamMatricula];
    int qtd_alunosMatriculados;
    disciplina_alunos alunosMatriculados[max_alunosMatriculados];
} disciplina;

// Variáveis do tipo array de structs

pessoa alunos[tamAlunos];
pessoa professores[tamProfessores];
disciplina listaDisciplinas[tamDisciplinas];

/*--------------------------------------------------------------------------------------------------*/
// Subfunções

//Configurando a codificação de caracteres
void configurarLocale() {
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

// Limpa a tela
void limparTela() {
    #if defined(_WIN32) || defined(_WIN64) // Windows
        system("cls");

    #elif defined(__linux__) || defined(__unix__) || defined(__APPLE__) || defined(__MACH__) // Linux e macOS
        system("clear");
        
    #endif
}

//Pausa a tela
void pausarTela() {
    #if defined(_WIN32) || defined(_WIN64) // Windows
        system("pause");

    #elif defined(__linux__) || defined(__unix__) || defined(__APPLE__) || defined(__MACH__) // Linux e macOS
        printf("Pressione Enter para continuar...");
        fflush(stdout);  // Força a exibição imediata
        
        limparBuffer();

    #endif
}

/*// Valida números inteiros positivos
int validarInteiroPositivo(int *endereco) {
    
    long long n;
    int retorno = -1;

    // Retorna 1 se conseguiu coletar uma entrada válida e 0 caso contrário
    retorno = scanf("%lld", &n); 

    // Limpar o buffer e impede o loop infinito
    limparBuffer();
    
    // Verifica se caracteres não numéricos foram digitados
    if (retorno == 0 || retorno == EOF) {
        printf("Erro: caractere não numérico digitado.\n");
        return 1;
    }
    
    // Verifica se o número é maior que INT_MAX
    if (n > INT_MAX) {
        printf("Erro: o número excede o limite de %d.\n", INT_MAX);
        return 1;
    }
    
    // Verifica se o número é negativo
    if (n <= 0) {
        printf("Erro: o número deve ser positivo e maior que 0.\n");
        return 1;
    }
    
    // Converte n para inteiro e armazena no endereço guardado em "endereco"
    *endereco = (int)n;  
    return 0;
}*/

// Lê uma entrada
int lerEntrada (char entrada[], int tamEntrada) {

    // Leitura
    if (fgets(entrada, tamEntrada, stdin) == NULL) {
        return 1; // Trata erro de leitura
    }

    /*if (strcmp(entrada, "-1")) {
        return 1; // Retorna para o menu anterior.
    }*/

    // Verifica truncamento e remove \n
    if (strchr(entrada, '\n') == NULL) {
        limparBuffer(); // Limpa o excesso de caracteres
        printf("Erro: excesso de caracteres. O limite é %d caracteres.\n", tamEntrada - 2);
        return 1; // Input truncado = inválido
    } else {
        // Substitui a quebra de linha \n pelo terminador nulo \0
        entrada[strcspn(entrada, "\n")] = '\0';
    }
    return 0;
}

// Verifica a existência de um número de matrícula
int existeMatricula (char entrada_Matricula[], int contPessoa, int contPessoa2, char texto_pessoa[], int *posicao) {
    
    /*
    contPessoa: é o índice do módulo iniciado. Por exemplo, se o usuário entrou no Módulo Alunos, então contPessoa = contAluno.

    contPessoa2: é o índice do módulo que não foi iniciado. Por exemplo, se o usuário entrou no Módulo Alunos, então contPessoa2 = contProfessor.
    */    

    int contAluno, contProfessor;

    // Define contAluno e contProfessor em função do módulo iniciado
    if (strcmp(texto_pessoa, "aluno") == 0) { // Se texto_pessoa = "aluno"
        contAluno = contPessoa;
        contProfessor = contPessoa2;
    }
    else { // Se texto_pessoa = "professor"
        contAluno = contPessoa2;
        contProfessor = contPessoa;
    }
    
    // Verifica se a matrícula já está cadastrada na lista de alunos
    for (int i = 0; i < contAluno; i++){
        if (strcmp(entrada_Matricula, alunos[i].matricula) == 0){ // Matrícula já cadastrada
            printf("\nA matrícula está cadastrada na lista de alunos.\n");
            if (posicao != NULL) *posicao = i;
            return 1;
        }
    }

    // Verifica se a matrícula já está cadastrada na lista de professores
    for (int i = 0; i < contProfessor; i++){
        if (strcmp(entrada_Matricula, professores[i].matricula) == 0){ // Matrícula já cadastrada
            printf("\nA matrícula está cadastrada na lista de professores.\n");
            if (posicao != NULL) *posicao = i;
            return 2;
        }
    }
    return 0; // Matrícula não cadastrada
}

// Verifica a existência de um CPF
int existeCPF (char entrada_CPF[], int contPessoa, int contPessoa2, char texto_pessoa[]) {
    
    /*
    contPessoa: é o índice do módulo iniciado. Por exemplo, se o usuário entrou no Módulo Alunos, então contPessoa = contAluno.

    contPessoa2: é o índice do módulo que não foi iniciado. Por exemplo, se o usuário entrou no Módulo Alunos, então contPessoa2 = contProfessor.
    */    

    int contAluno, contProfessor;

    // Define contAluno e contProfessor em função do módulo iniciado
    if (strcmp(texto_pessoa, "aluno") == 0) { // Se texto_pessoa = "aluno"
        contAluno = contPessoa;
        contProfessor = contPessoa2;
    }
    else {  // Se texto_pessoa = "professor"
        contAluno = contPessoa2;
        contProfessor = contPessoa;
    }

    // Verifica se o CPF já está cadastrado na lista de alunos
    for (int i = 0; i < contAluno; i++){
        if (strcmp(entrada_CPF, alunos[i].CPF) == 0){ // CPF já cadastrado
            printf("\nO CPF está cadastrado na lista de alunos.\n");
            //pausarTela();
            //limparTela();
            return 1;
        }
    }

    // Verifica se o CPF já está cadastrado na lista de professores
    for (int i = 0; i < contProfessor; i++){
        if (strcmp(entrada_CPF, professores[i].CPF) == 0){ // CPF já cadastrado
            printf("\nO CPF está cadastrado na lista de professores.\n");
            //pausarTela();
            //limparTela();
            return 2;
        }
    }
    return 0; // CPF não cadastrado
}

// Verifica a existência de um código
int existeCodigo (char entrada_codigo[], int contDisciplina) {

    // Verifica se o código já está cadastrado na lista de disciplinas
    for (int i = 0; i < contDisciplina; i++){
        if (strcmp(entrada_codigo, listaDisciplinas[i].codigo) == 0){ // Código já cadastrado
            printf("\nO código já está cadastrado.\n");
            return 1;
        }
    }

    return 0; // Código não cadastrado
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

// Recebe e valida matrícula ou o CPF
int receberMat_CPF (int tamMat_CPF, char entrada_Mat_CPF[tamMat_CPF], char texto[], char texto_pessoa[]) {
    
    // entrada_Mat_CPF: ponteiro que aponta para o endereço de memória do vetor "matricula" ou "CPF"
    // "matricula" e "CPF" são vetores pertencentes à função que chamou receberNome

    // Zerando a variável temporária
    entrada_Mat_CPF[0] = '\0';

    int tamEsperado = tamMat_CPF - 2;

    // Entrada de dados
    printf("Informe o(a) %s do(a) %s(a) (máx. %d dígitos): ", texto, texto_pessoa, tamEsperado);
    if (fgets(entrada_Mat_CPF, tamMat_CPF, stdin) == NULL) {
        printf("Erro ao ler o(a) %s.\n", texto); // Trata erro de leitura
        return 1;
    }

    // Verifica truncamento e remove \n
    if (strchr(entrada_Mat_CPF, '\n') == NULL) {
        limparBuffer(); // Limpa o excesso de caracteres
        printf("Erro: excesso de caracteres (máx. %d caracteres).\n", tamEsperado);
        return 1; // Input truncado = inválido
    } else {
        // Substitui a quebra de linha \n pelo terminador nulo \0
        entrada_Mat_CPF[strcspn(entrada_Mat_CPF, "\n")] = '\0';
    }    

    // Calcula o tamanho da matrícula
    int len = strlen(entrada_Mat_CPF);
    if (entrada_Mat_CPF[0] == '-') tamEsperado++; // Números negativos
    
    // Verificações de erros
    int erro = 0;
    
    // Verifica o tamanho da matrícula
    if (len != tamEsperado) {
        printf("Erro: %s deve conter %d dígitos.\n", texto, tamEsperado);
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
    
    // Verifica se o número é negativo
    if (entrada_Mat_CPF[0] == '-' && isdigit(entrada_Mat_CPF[1])) {
        printf("Erro: %s não pode ser um número negativo.\n", texto);
        
        // Verifica se existem caracteres não numéricos
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

    if (erro) { // Erro = 1
        return 1;
    }

    // Se for CPF, chama a função validarCPF para verificar outras condições
    if (strcmp(texto, "CPF") == 0) { // Se o texto for CPF
        if (validarCPF(entrada_Mat_CPF) != 0) { // Chama a função validarCPF
            return 1; // CPF inválido 
        }
    }

    return 0; // Matrícula ou CPF válido
}

// Recebe e valida o nome
int receberNome (char entrada_nome[]) {
    
    // entrada_nome: ponteiro que aponta para o endereço de memória do vetor "nome"
    // "nome" é o vetor pertencente à função que chamou receberNome

    // Entrada de dados
    printf("Digite um nome: ");
    if (fgets(entrada_nome, tamNome, stdin) == NULL) {
        printf("Erro ao ler o nome.\n");
        return 1;
    }

    // Verifica truncamento e remove \n
    if (strchr(entrada_nome, '\n') == NULL) {
        limparBuffer(); // Limpa o excesso de caracteres
        printf("Erro: excesso de caracteres (máx. %d caracteres).\n", tamNome - 2);
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
        wentrada_nome[i] = towupper(wentrada_nome[i]);
    }

    wcstombs(entrada_nome, wentrada_nome, len + 1);
    entrada_nome[len] = '\0';
    
    return 0; // Nome válido
}

// Recebe e valida o código
int receberCodigo (char entrada_codigo[]) {

    // entrada_codigo: ponteiro que aponta para o endereço de memória do vetor "codigo"
    // "codigo" é o vetor pertencente à função que chamou receberCodigo

    // Padrão: XXX000 | Por exemplo: INF006

    // Entrada de dados
    printf("Informe um código: ");
    if (lerEntrada(entrada_codigo, tamCodigo) == -1) {
        printf("\nOperação cancelada.");
        return 1;
    }
    else (lerEntrada(entrada_codigo, tamCodigo) != 0) {
        return 1;
    }

    // Comprimento
    int len = strlen(entrada_codigo);
    int tamEsperado = tamCodigo - 2;
    
    // Verifica se o nome está vazio
    if (len == 0) {
        printf("Erro: o código não pode estar vazio.\n");
        return 1;
    }
    
    // Verifica o tamanho da matrícula
    if (len != tamEsperado) {
        printf("Erro: o código deve conter %d caracteres.\n", tamEsperado);
        return 1;
    }

    // Verifica se o código contém caracteres inválidos
    for (int i = 0; i < len; i++) {
        if (i <= 2) { // Letras (A-Z ou a-z)
            char c = tolower(entrada_codigo[i]);
            if (c < 'a' || c > 'z') {
                printf("Erro: o código deve conter 3 letras e 3 números (ex.: ADS001).");
                return 1;
            }
        }
        else { // Números
            if (entrada_codigo[i] < '0' || entrada_codigo[i] > '9') {
                printf("OErro: o código deve conter 3 letras e 3 números (ex.: ADS001).");
                return 1;
            }
        }
    }

    // Padroniza o código, capitalizando as letras
    for (int i = 0; i <= 2; i++) {
        entrada_codigo[i] = toupper(entrada_codigo[i]);
    }

    return 0; // Código válido
}

// Verifica se o ano é bissexto
int bissexto (int ano) {
    if(((ano % 4 == 0) && (ano % 100 != 0)) || (ano % 400 == 0)){
        return 1; // é bissexto
    }
    else {
        return 0; // Não é bissexto
    }
}

// Recebe e valida a data de nascimento
int receberData (int* entrada_dia, int* entrada_mes, int* entrada_ano, char texto_pessoa[]) {
    
    // entrada_dia etc.: ponteiros que apontam para o endereço de memória dos vetors "dia" etc.
    // "dia" etc. são vetores pertencentes à função que chamou receberData

    int flagBissexto = bissexto(*entrada_ano);
    int diasMes[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    
    printf("Informe a data de nascimento do %s (DD/MM/AAAA): ", texto_pessoa);
    if(scanf("%d/%d/%d", entrada_dia, entrada_mes, entrada_ano) != 3) {
        limparBuffer();
        //while (getchar() != '\n');  // Limpa o buffer até encontrar a quebra de linha

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

// Recebe e valida o sexo
int receberSexo (char entrada_sexo[tamSexo], char texto_pessoa[]) {
    
    // entrada_sexo: ponteiro que aponta para o endereço de memória do vetor sexo
    // "sexo" é um vetor pertencente à função que chamou receberNome
    
    // Entrada de dados
    printf("Informe o sexo (M ou F) do %s: ", texto_pessoa);
    if (fgets(entrada_sexo, tamSexo, stdin) == NULL) {
        printf("Erro ao ler o sexo.\n");
        return 1; // Trata erro de leitura
    }

    // Verifica truncamento e remove \n
    if (strchr(entrada_sexo, '\n') == NULL) {
        limparBuffer(); // Limpa o excesso de caracteres
        printf("Erro: excesso de caracteres (máx. %d caracteres).\n", tamSexo - 2);
        return 1; // Input truncado = inválido
    } else {
        // Substitui a quebra de linha \n pelo terminador nulo \0
        entrada_sexo[strcspn(entrada_sexo, "\n")] = '\0';
    }
        
    // Converte os caracteres para maiúsculo
    entrada_sexo[0] = toupper(entrada_sexo[0]);
    
    if (entrada_sexo[0] != 'M' && entrada_sexo[0] != 'F') {
        printf("Erro: o sexo só pode ser m, M, f ou F.\n");
        return 1;
    }

    return 0; // Sexo válido
}

// ############################################################################## //
// ALUNOS E PROFESSORES

// Cadastra um aluno ou professor
void inserirPessoa (char txtPessoa_ALS[], int contPessoa, pessoa pessoas[], int tamPessoas, int contPessoa2) {

    // Declarações
    char txtPessoa_FUP[12];
    char txtPessoa_FUS[12];

    // Formatações
    if (strcmp(txtPessoa_ALS, "aluno") == 0) {
        strcpy(txtPessoa_FUP, "Alunos");
        strcpy(txtPessoa_FUS, "Aluno");
    }
    else {
        strcpy(txtPessoa_FUP, "Professores");
        strcpy(txtPessoa_FUS, "Professor");
    }
                            
    printf("### Módulo %s - Inserir %s ###\n", txtPessoa_FUP, txtPessoa_ALS); // FUP + ALS

    // Verifica se a lista de alunos ou professores está cheia
    if (contPessoa >= tamPessoas) { // Lista cheia
        printf("\nCadastro cheio. Não é possível inserir outro(a) %s(a).\n", txtPessoa_ALS);
        pausarTela();
        limparTela();
    }
    
    // Lista não cheia
    else {
        
        // ############################################################################## //
        // MATRÍCULA
    
        // Variáveis auxiliares
        int flagMatricula = 0;
        int flagExisteMat = 0; 
        char matricula[tamMatricula];
        char textoMat[] = "matrícula";

        // Recebe e valida a matrícula
        do {
            flagMatricula = receberMat_CPF(tamMatricula, matricula, textoMat, txtPessoa_ALS);
            flagExisteMat = existeMatricula (matricula, contPessoa, contPessoa2, txtPessoa_ALS, NULL);
            if (flagMatricula != 0 || flagExisteMat != 0) {
                printf("\n");
            }
        } while (flagMatricula != 0 || flagExisteMat != 0);

        // Armazena matrícula
        strcpy(pessoas[contPessoa].matricula, matricula);

        // FIM MATRÍCULA
        // ############################################################################## //
           

        // ############################################################################## //
        // NOME

        // Variáveis auxiliares
        int flagNome = 0;
        char nome[tamNome];
        
        // Recebe e valida o nome
        do {
            flagNome = receberNome(nome);
            if (flagNome != 0) {
                printf("\n");
            }                    
        } while (flagNome != 0);
        
        // Armazena o nome
        strcpy(pessoas[contPessoa].nome, nome);

        // FIM NOME
        // ############################################################################## //
        
        // ############################################################################## //
        // DATA

        // Variáveis auxiliares
        int flagData = 0;
        int dia, mes, ano; // Variáveis temporárias

            // Recebe e valida a data
            do {
                flagData = receberData(&dia, &mes, &ano, txtPessoa_ALS);
                if (flagData != 0) {
                    printf("\n");
                }                        
            } while (flagData != 0);

            //Armazena a data
            pessoas[contPessoa].nascimento.dia = dia;
            pessoas[contPessoa].nascimento.mes = mes;
            pessoas[contPessoa].nascimento.ano = ano;

            limparBuffer(); // Evitar que o \n seja passado para o CPF

        // FIM DATA
        // ############################################################################## //


        // ############################################################################## //
        // CPF

        // Variáveis auxiliares
        int flagCPF = 0;
        int flagExisteCPF = 0;
        char CPF[tamCPF];
        char textoCPF[] = "CPF";

        // Recebe e valida o CPF
        do {
            flagCPF = receberMat_CPF(tamCPF, CPF, textoCPF, txtPessoa_ALS);
            flagExisteCPF = existeCPF (CPF, contPessoa, contPessoa2, txtPessoa_ALS);
            if (flagCPF != 0 || flagExisteCPF != 0) {
                printf("\n");
            }                    
        } while (flagCPF != 0 || flagExisteCPF != 0);

        //Armazena o CPF
        strcpy(pessoas[contPessoa].CPF, CPF);

        // FIM CPF
        // ############################################################################## //


        // ############################################################################## //
        // SEXO

        // Variáveis auxiliares
        int flagSexo = 0;
        char sexo[tamSexo];
        
        // Recebe e valida o sexo
        do {
            flagSexo = receberSexo(sexo, txtPessoa_ALS);
            if (flagSexo != 0) {
                printf("\n");
            }
        } while (flagSexo != 0);
        
        // Armazena o sexo
        strcpy(pessoas[contPessoa].sexo, sexo);

        // SEXO
        // ############################################################################## //

        printf("\n%s(a) cadastrado(a) com sucesso!\n", txtPessoa_FUS);

        // Transição de tela
        pausarTela();
        limparTela();

    } // Fim do else (lista de alunos não cheia)

} // Fim da função

// Lista alunos ou professores
void listarPessoa (int contPessoa, pessoa pessoas[], char txtPessoa_ALS[]) {
    
    //A = all; F = first; L = lowercase; U = all uppercase; S = singular; P = plural

    // Declarações
    char txtPessoa_FUP[12];
    char txtPessoa_ALP[12];

    // Formatações
    if (strcmp(txtPessoa_ALS, "aluno") == 0) {
        strcpy(txtPessoa_FUP, "Alunos");
        strcpy(txtPessoa_ALP, "alunos");
    }
    else {
        strcpy(txtPessoa_FUP, "Professores");
        strcpy(txtPessoa_ALP, "professores");
    }
    
    if (contPessoa == 0) {
        printf("\nNão há %s cadastrados.\n", txtPessoa_ALP); // ALP
    }
    else {
        for (int i = 0; i < contPessoa; i++) {
            printf("\n");
            printf("Matrícula: %s\n", pessoas[i].matricula);
            printf("Nome: %s\n", pessoas[i].nome);
            printf("Data de nascimento: %02d/%02d/%02d\n", pessoas[i].nascimento.dia, pessoas[i].nascimento.mes, pessoas[i].nascimento.ano);
            printf("CPF: %s\n", pessoas[i].CPF);
            printf("Sexo: %s\n", pessoas[i].sexo);
        }
    }
}

// Atualiza o cadastro de um aluno ou professor
void atualizarPessoa (char txtPessoa_ALS[], pessoa pessoas[], int contPessoa, int contPessoa2) {

    //A = all; F = first; L = lowercase; U = all uppercase; S = singular; P = plural

    // Declarações
    char txtPessoa_FUP[12];
    char txtPessoa_ALP[12];

    // Formatações
    if (strcmp(txtPessoa_ALS, "aluno") == 0) {
        strcpy(txtPessoa_FUP, "Alunos");
        strcpy(txtPessoa_ALP, "alunos");
    }
    else {
        strcpy(txtPessoa_FUP, "Professores");
        strcpy(txtPessoa_ALP, "professores");
    }
                            
    printf("### Módulo %s - Atualizar %s ###\n", txtPessoa_FUP, txtPessoa_ALS); // FUP + ALS

    // Declarações e inicializações
    int flagMatricula = 0;
    int achou = 0;
    char matricula[tamMatricula];
    int i;

    if (contPessoa == 0) {
        printf("\nNão há %s cadastrados.\n", txtPessoa_ALP); // ALP
        
        // Transição de tela
        pausarTela();
        limparTela();
        return; // Volta para o menu anterior
    }

    // Verifica se a matrícula está cadastrada
    do {
        
        printf("\nInforme a matrícula do(a) %s(a) (digite -1 para voltar ao menu anterior): ", txtPessoa_ALS);
        
        // Lê a entrada
        lerEntrada(matricula, tamMatricula);
        //printf("\nMatrícula: %s\n", matricula);

        if (strcmp(matricula, "-1") == 0) {
            // Transição de tela
            limparTela();
            return; // Volta ao menu anterior
        }

        flagMatricula = existeMatricula(matricula, contPessoa, contPessoa2, txtPessoa_ALS, &i);

        if (flagMatricula == 0) {
            printf("A matrícula não está cadastrada. Por favor, tente novamente.\n");
        }
        else if (strcmp(txtPessoa_ALS, "aluno") == 0 && flagMatricula == 2) { // flagMatricula = 2
            printf("Por favor, insira a matrícula de um(a) aluno(a).\n");
            flagMatricula = 0; // Reset
        }
        else if (strcmp(txtPessoa_ALS, "professor") == 0 && flagMatricula == 1) { // flagMatricula = 1
            printf("Por favor, insira a matrícula de um(a) professor(a).\n");
            flagMatricula = 0; // Reset
        }
        else { // "aluno" && flagMatricula = 1 || "professor" && flagMatricula = 2
            achou = 1;
        }
    } while (flagMatricula == 0);

    // Matrícula encontrada no módulo em execução
    if (achou == 1) {
        
        int opcaoAtt = 0;

        // ############################################################################## //
        // MATRÍCULA
        
        printf("\nMatrícula atual: %s\n", pessoas[i].matricula);
        printf("Deseja atualizar a matrícula (1 - Sim; 2 - Não)? ");
        scanf("%d", &opcaoAtt);
        limparBuffer();

        switch (opcaoAtt) {
            
            case 1: { // Sim
            // Variáveis auxiliares
            flagMatricula = 0; // Reset
            int flagExiste = 0;
            char textoMat[] = "matrícula";

                // Recebe e valida a matrícula
                do {
                    flagMatricula = receberMat_CPF(tamMatricula, matricula, textoMat, txtPessoa_ALS);
                    flagExiste = existeMatricula (matricula, contPessoa, contPessoa2, txtPessoa_ALS, NULL);
                    if (flagMatricula != 0 || flagExiste != 0) {
                        printf("\n");
                    }
                } while (flagMatricula != 0 || flagExiste != 0);
                printf("Matrícula atualizada.\n");

                // Armazena matrícula
                strcpy(pessoas[i].matricula, matricula);

                break;
            }

            case 2: { // Não
                printf("Matrícula não atualizada.\n");
                break;
            }
            
            default: {
                printf("Opção inválida. Matrícula não atualizada.\n"); // Passar para o próximo campo
                break;
            }
        }

        // FIM - MATRÍCULA
        // ############################################################################## //
        

        // ############################################################################## //
        // NOME
        
        printf("\nNome atual: %s\n", pessoas[i].nome);
        printf("Deseja atualizar o nome (1 - Sim; 2 - Não)? ");
        scanf("%d", &opcaoAtt);
        limparBuffer();

        switch (opcaoAtt) {
            
            case 1: { // Sim
                // Variáveis auxiliares
                int flagNome = 0;
                char nome[tamNome];
                
                // Recebe e valida o nome
                do {
                    flagNome = receberNome(nome);
                    if (flagNome != 0) {
                        printf("\n");
                    }                    
                } while (flagNome != 0);

                // Armazena o nome
                strcpy(pessoas[i].nome, nome);

                printf("Nome atualizado.\n");
                break;
            }

            case 2: { // Não
                printf("Nome não atualizado.\n");
                break;
            }
            
            default: {
                printf("Opção inválida. Nome não atualizado.\n");
                break;
            }
        }

        // FIM - NOME
        // ############################################################################## //


        // ############################################################################## //
        // DATA DE NASCIMENTO
        
        printf("\nData de nascimento atual: %02d/%02d/%02d\n", pessoas[i].nascimento.dia, pessoas[i].nascimento.mes, pessoas[i].nascimento.ano);
        printf("Deseja atualizar a data de nascimento (1 - Sim; 2 - Não)? ");
        scanf("%d", &opcaoAtt);
        limparBuffer();

        switch (opcaoAtt) {
            
            case 1: { // Sim
                // Variáveis auxiliares
                int flagData = 0;
                int dia, mes, ano; // Variáveis temporárias

                // Recebe e valida a data
                do {
                    flagData = receberData(&dia, &mes, &ano, txtPessoa_ALS);
                    if (flagData != 0) {
                        printf("\n");
                    }                        
                } while (flagData != 0);

                //Armazena a data
                pessoas[i].nascimento.dia = dia;
                pessoas[i].nascimento.mes = mes;
                pessoas[i].nascimento.ano = ano;

                printf("Data atualizada.\n");
                break;
            }

            case 2: { // Não
                printf("Data não atualizada.\n");
                break;
            }
            
            default: {
                printf("Opção inválida. Data não atualizada.\n");
                break;
            }
        }

        // FIM - DATA DE NASCIMENTO
        // ############################################################################## //


        // ############################################################################## //
        // CPF
        
        printf("\nCPF atual: %s\n", pessoas[i].CPF);
        printf("Deseja atualizar o CPF (1 - Sim; 2 - Não)? ");
        scanf("%d", &opcaoAtt);
        limparBuffer();

        switch (opcaoAtt) {
            
            case 1: { // Sim
                // Variáveis auxiliares
                int flagCPF = 0;
                int flagExisteCPF = 0;
                char CPF[tamCPF];
                char textoCPF[] = "CPF";

                // Recebe e valida o CPF
                do {
                    flagCPF = receberMat_CPF(tamCPF, CPF, textoCPF, txtPessoa_ALS);
                    flagExisteCPF = existeCPF (CPF, contPessoa, contPessoa2, txtPessoa_ALS);
                    if (flagCPF != 0 || flagExisteCPF != 0) {
                        printf("\n");
                    }                    
                } while (flagCPF != 0 || flagExisteCPF != 0);

                //Armazena o CPF
                strcpy(pessoas[i].CPF, CPF);

                printf("CPF atualizado.\n");
                break;
            }

            case 2: { // Não
                printf("CPF não atualizado.\n");
                break;
            }
            
            default: {
                printf("Opção inválida. CPF não atualizado.\n");
                break;
            }
        }

        // FIM - CPF
        // ############################################################################## //


        // ############################################################################## //
        // SEXO
        
        printf("\nSexo atual: %s\n", pessoas[i].sexo);
        printf("Deseja atualizar o sexo (1 - Sim; 2 - Não)? ");
        scanf("%d", &opcaoAtt);
        limparBuffer();

        switch (opcaoAtt) {
            
            case 1: { // Sim
                // Variáveis auxiliares
                int flagSexo = 0;
                char sexo[tamSexo];
                
                // Recebe e valida o sexo
                do {
                    flagSexo = receberSexo(sexo, txtPessoa_ALS);
                    if (flagSexo != 0) {
                        printf("\n");
                    }
                } while (flagSexo != 0);

                // Armazena o sexo
                strcpy(pessoas[i].sexo, sexo);

                printf("Sexo atualizado.\n");
                break;
            }

            case 2: { // Não
                printf("Sexo não atualizado.\n");
                break;
            }
            
            default: {
                printf("Opção inválida. Sexo não atualizado.\n");
                break;
            }
        }

        // FIM - SEXO
        // ############################################################################## //

    } // Fim do if (achou = 1)

    printf("\n");

    printf("\nAtualização concluída para o(a) %s(a) %s.\n", txtPessoa_ALS, pessoas[i].nome);

    // Transição de tela
    pausarTela();
    limparTela();
}

// Exclui o cadastro de um aluno ou professor
void excluirPessoa (int *contPessoa, pessoa pessoas[], char txtPessoa_ALS[]) {

    char txtPessoa_ALP[12];
    if (strcmp(txtPessoa_ALS, "aluno") == 0) {
        strcpy(txtPessoa_ALP, txtAluno_ALP); // "alunos"
    }
    else {
        strcpy(txtPessoa_ALP, txtProfessor_ALP); // "professores"
    }
    
    // Verifica se há pessoas cadastradas
    if (*contPessoa == 0) {
        printf("\nNão há %s cadastrados.\n", txtPessoa_ALP);
        pausarTela();
        limparTela();
        return;
    }

    listarPessoa(*contPessoa, pessoas, txtPessoa_ALS); // Passando como cópia

    // Lê entrada
    printf("\nInforme a matrícula a ser excluída: ");
    char matricula[tamMatricula];
    lerEntrada(matricula, tamMatricula);

    // Procurando a matrícula
    for (int i = 0; i < *contPessoa; i++) {
        
        if (strcmp(matricula, pessoas[i].matricula) == 0) { // Achou

            // Shift
            for (int j = i; j < *contPessoa; j++) {
                pessoas[j] = pessoas[j + 1];
            }
    
            (*contPessoa)--; // O operador "--" tem prioridade. Portanto, os "()" são necessários
            printf("\nExclusão realizada com sucesso!\n");
            
            // Transição de tela
            pausarTela();
            limparTela();
            return;
        }
    }

    printf("\nNão foi encontrado nenhum %s com essa matrícula.", txtPessoa_ALS);

    printf("\n");

    // Transição de tela
    pausarTela();
    limparTela();
}

// FIM ALUNOS E PROFESSORES
// ############################################################################## //


// ############################################################################## //
// DISCIPLINAS

// Cadastra uma disciplina
void inserirDisciplina (int *contDisciplina, disciplina listaDisciplinas[]) {

    // Verifica se a lista de disciplinas está cheia
    if (*contDisciplina >= tamDisciplinas) { // Lista cheia
        printf("\nCadastro cheio. Não é possível inserir outra disciplina.\n");
        pausarTela();
        limparTela();
        return; // Volta para o menu anterior
    }
    
    // Lista não cheia

    // ############################################################################## //
    // CÓDIGO

    // Variáveis auxiliares
    int flagCodigo = 0;
    int flagExisteCod = 0; 
    char codigo[tamCodigo];

    // Recebe e valida o código
    do {
        flagCodigo = receberCodigo(codigo);
        flagExisteCod = existeCodigo (codigo, *contDisciplina);
        if (flagCodigo != 0 || flagExisteCod != 0) {
            printf("\n");
        }
    } while (flagCodigo != 0 || flagExisteCod != 0);

    // FIM CÓDIGO
    // ############################################################################## //
        

    // ############################################################################## //
    // NOME

    // Variáveis auxiliares
    int flagNome = 0;
    char nome[tamNome];
    
    // Recebe e valida o nome
    do {
        flagNome = receberNome(nome);
        if (flagNome != 0) {
            printf("\n");
        }                    
    } while (flagNome != 0);

    // FIM NOME*/
    // ############################################################################## //
    
    // Guarda os dados na struct
    strcpy(listaDisciplinas[*contDisciplina].codigo, codigo);
    strcpy(listaDisciplinas[*contDisciplina].nome, nome);

    // Incrementa a quantidade de disciplinas
    (*contDisciplina)++; 

    printf("\nDisciplina cadastrada com sucesso!\n");

    // Transição de tela
    pausarTela();
    limparTela();
}

// Lista as disciplinas
void listarDisciplinas (int contDisciplina, disciplina listaDisciplinas[]) {
        
    if (contDisciplina == 0) {
        printf("\nNão há disciplinas cadastrados.\n");
    }
    else {
        for (int i = 0; i < contDisciplina; i++) {
            printf("\n");
            printf("Código: %s\n", listaDisciplinas[i].codigo);
            printf("Nome: %s\n", listaDisciplinas[i].nome);
            printf("Nome: %s\n", listaDisciplinas[i].semestre);
            // Inserir professor
        }
    }

    printf("\n");

    // Transição de tela
    pausarTela();
    limparTela();
}

// FIM DISCIPLINAS
// ############################################################################## //


/*--------------------------------------------------------------------------------------------------*/
//Função principal
int main (){

    configurarLocale();

    limparTela();

    printf("Bem-vindo(a) ao sistema CRUDX.\n\n"); 

    pausarTela();
    limparTela();

    //Declarações
    int opcao;
    int contAluno = 0, contProfessor = 0, contDisciplina = 0;

    do {
        // Menu de opções
        printf("### Menu principal ###");
        printf("\nInforme o número da opção desejada: ");
        printf("\n0 - Sair");
        printf("\n1 - Alunos");
        printf("\n2 - Professores");
        printf("\n3 - Disciplinas\n");
    
        // Entrada de dados: Opção
        scanf("%d",&opcao);
        limparBuffer();

        // Transição de tela
        limparTela();

        // ############################################################################## //
        // MÓDULO GERAL

        switch (opcao){

            case 0: {
                printf("Programa encerrado.");
                return 1;
            }
            
            // ############################################################################## //
            // MÓDULO ALUNOS

            case 1: {
                int opcaoAluno;
                
                do {
                    //Menu de opções
                    printf("### Módulo Alunos ###");
                    printf("\nInforme o número da opção desejada: ");
                    printf("\n0 - Voltar ao menu anterior");
                    printf("\n1 - Inserir aluno");
                    printf("\n2 - Listar aluno");
                    printf("\n3 - Atualizar aluno");
                    printf("\n4 - Excluir aluno");
                    printf("\n");
                    
                    //Entrada de dados: Opção do Módulo de alunos
                    scanf("%d",&opcaoAluno);
                    limparBuffer();
                    
                    // Transição de tela
                    limparTela();
    
                    switch(opcaoAluno){

                        // ##################################################################### //
                        // MÓDULO ALUNOS - VOLTAR AO MENU ANTERIOR

                        case 0: {
                            limparTela();
                            break;
                        }

                        // FIM MÓDULO ALUNOS - VOLTAR AO MENU ANTERIOR
                        // ##################################################################### //
                        

                        // ##################################################################### //
                        // MÓDULO ALUNOS - INSERIR

                        case 1: {
                            inserirPessoa(txtAluno_ALS, contAluno, alunos, tamAlunos, contProfessor);
                            contAluno++; // Incrementa a contagem de alunos

                            break; // Sai do case 1
                        }

                        // FIM DO MÓDULO ALUNOS - INSERIR
                        // ##################################################################### //
                        

                        // ##################################################################### //
                        // MÓDULO ALUNOS - LISTAR

                        case 2: {
                            printf("### Módulo Alunos - Listar aluno ###\n");
                            listarPessoa(contAluno, alunos, txtAluno_ALS);

                            // printf e transição na main, pois a função listarPessoa também é utilizada por excluirPessoa
                            printf("\n");

                            // Transição de tela
                            pausarTela();
                            limparTela();

                            break; // Sai do case 2
                        }

                        // FIM DO MÓDULO ALUNOS - LISTAR
                        // ##################################################################### //
        

                        // ##################################################################### //
                        // MÓDULO ALUNOS - ATUALIZAR

                        case 3: {
                            atualizarPessoa(txtAluno_ALS, alunos, contAluno, contProfessor);

                            break; // Sai do case 3
                        }

                        // FIM DO MÓDULO ALUNOS - ATUALIZAR
                        // ##################################################################### //
        

                        // ##################################################################### //
                        // MÓDULO ALUNOS - EXCLUIR
                        case 4: {
                            printf("### Módulo Alunos - Excluir aluno ###\n");
                            excluirPessoa(&contAluno, alunos, txtAluno_ALS);
                            
                            break; // Sai do case 4 
                        }

                        // FIM DO MÓDULO ALUNOS - EXCLUIR
                        // ##################################################################### //

                                
                        // ##################################################################### //
                        // MÓDULO ALUNOS - OPÇÃO INVÁLIDA

                        default: {
                            printf("Opção inválida.\n");
                            
                            // Transição de tela
                            pausarTela();
                            limparTela();

                            break; // Sai do default
                        }

                        // FIM DO MÓDULO ALUNOS - OPÇÃO INVÁLIDA
                        // ##################################################################### //

                    } // Fim do switch

                } while(opcaoAluno != 0);

                break;
            } // Fim do case 1, Alunos, do módulo geral

            // FIM DO MÓDULO ALUNOS
            // ############################################################################## //

            
            // ############################################################################## //
            // MÓDULO PROFESSORES

            case 2: {
                int opcaoProfessor;
                
                do {
                    //Menu de opções
                    printf("### Módulo Professores ###");
                    printf("\nInforme o número da opção desejada: ");
                    printf("\n0 - Voltar ao menu anterior");
                    printf("\n1 - Inserir professor");
                    printf("\n2 - Listar professor");
                    printf("\n3 - Atualizar professor");
                    printf("\n4 - Excluir professor");
                    printf("\n");
                    
                    //Entrada de dados: Opção do Módulo de professores
                    scanf("%d",&opcaoProfessor);
                    limparBuffer();
                    
                    // Transição de tela
                    limparTela();
    
                    switch(opcaoProfessor){

                        // ##################################################################### //
                        // MÓDULO PROFESSORES - VOLTAR AO MENU ANTERIOR
                        
                        case 0: {
                            limparTela();
                            break;
                        }

                        // FIM MÓDULO PROFESSORES - VOLTAR AO MENU ANTERIOR
                        // ##################################################################### //


                        // ##################################################################### //
                        // MÓDULO PROFESSORES - INSERIR

                        case 1: {
                            inserirPessoa(txtProfessor_ALS, contProfessor, professores, tamProfessores, contAluno);
                            contProfessor++; // Incrementa a contagem de professores

                            break; // Sai do case 1
                        }

                        // FIM DO MÓDULO PROFESSORES - INSERIR
                        // ##################################################################### //
                        

                        // ##################################################################### //
                        // MÓDULO PROFESSORES - LISTAR

                        case 2: {
                            printf("### Módulo Professores - Listar professor ###\n");
                            listarPessoa(contProfessor, professores, txtProfessor_ALS);

                            // printf e transição na main, pois a função listarPessoa também é utilizada por excluirPessoa
                            printf("\n");

                            // Transição de tela
                            pausarTela();
                            limparTela();

                            break; // Sai do case 2
                        }

                        // FIM DO MÓDULO PROFESSORES - LISTAR
                        // ##################################################################### //
        

                        // ##################################################################### //
                        // MÓDULO PROFESSORES - ATUALIZAR

                        case 3: {
                            atualizarPessoa(txtProfessor_ALS, professores, contProfessor, contAluno);

                            break; // Sai do case 3
                        }

                        // FIM DO MÓDULO PROFESSORES - ATUALIZAR
                        // ##################################################################### //
        

                        // ##################################################################### //
                        // MÓDULO PROFESSORES - EXCLUIR
                        case 4: {
                            printf("### Módulo Professores - Excluir professor ###\n");
                            excluirPessoa(&contProfessor, professores, txtProfessor_ALS);

                            break; // Sai do case 4 
                        }

                        // FIM DO MÓDULO PROFESSORES - EXCLUIR
                        // ##################################################################### //

                                
                        // ##################################################################### //
                        // MÓDULO PROFESSORES - OPÇÃO INVÁLIDA

                        default: {
                            printf("Opção inválida.\n");
                            
                            // Transição de tela
                            pausarTela();
                            limparTela();

                            break; // Sai do default
                        }

                        // FIM DO MÓDULO PROFESSORES - OPÇÃO INVÁLIDA
                        // ##################################################################### //

                    } // Fim do switch 2

                } while(opcaoProfessor != 0);

                break;
            } // Fim do case 1, Professores, do módulo geral

            // FIM DO MÓDULO PROFESSORES
            // ############################################################################## //


            // ############################################################################## //
            // MÓDULO DISCIPLINAS

            case 3: {
                int opDisci;
                
                do {
                    //Menu de opções
                    printf("### Módulo Disciplinas ###");
                    printf("\nInforme o número da opção desejada: ");
                    printf("\n0 - Voltar ao menu anterior");
                    printf("\n1 - Inserir disciplina");
                    printf("\n2 - Listar disciplina");
                    printf("\n3 - Atualizar disciplina");
                    printf("\n4 - Excluir disciplina");
                    printf("\n5 - Matricular aluno em uma disciplina");
                    printf("\n6 - Desmatricular aluno de uma disciplina");
                    printf("\n");
                    
                    //Entrada de dados: Opção do Módulo de Disciplinas
                    scanf("%d",&opDisci);
                    limparBuffer();
                    
                    // Transição de tela
                    limparTela();
    
                    switch(opDisci){

                        // ##################################################################### //
                        // MÓDULO DISCIPLINAS - VOLTAR AO MENU ANTERIOR

                        case 0: {
                            limparTela();
                            break;
                        }

                        // FIM MÓDULO DISCIPLINAS - VOLTAR AO MENU ANTERIOR
                        // ##################################################################### //
                        

                        // ##################################################################### //
                        // MÓDULO DISCIPLINAS - INSERIR

                        case 1: {
                            printf("### Módulo Disciplinas - Inserir disciplinas ###\n");
                            inserirDisciplina (&contDisciplina, listaDisciplinas);
                            // contDisciplina incrementa na própria função

                            break; // Sai do case 1
                        }

                        // FIM DO MÓDULO DISCIPLINAS - INSERIR
                        // ##################################################################### //
                        

                        // ##################################################################### //
                        // MÓDULO DISCIPLINAS - LISTAR

                        case 2: {
                            printf("### Módulo Disciplinas - Listar disciplinas ###\n");
                            listarDisciplinas (contDisciplina, listaDisciplinas);

                            break; // Sai do case 2
                        }

                        // FIM DO MÓDULO DISCIPLINAS - LISTAR
                        // ##################################################################### //
        

                        // ##################################################################### //
                        // MÓDULO DISCIPLINAS - ATUALIZAR

                        case 3: {
                            printf("### Módulo Disciplinas - Atualizar disciplina ###\n");

                            break; // Sai do case 3
                        }

                        // FIM DO MÓDULO DISCIPLINAS - ATUALIZAR
                        // ##################################################################### //
        

                        // ##################################################################### //
                        // MÓDULO DISCIPLINAS - EXCLUIR
                        case 4: {
                            printf("### Módulo Disciplinas - Excluir disciplina ###\n");
                            
                            break; // Sai do case 4 
                        }

                        // FIM DO MÓDULO DISCIPLINAS - EXCLUIR
                        // ##################################################################### //


                        // ##################################################################### //
                        // MÓDULO DISCIPLINAS - MATRICULAR ALUNO
                        case 5: {
                            printf("### Módulo Disciplinas - Matricular aluno ###\n");
                            
                            break; // Sai do case 5
                        }

                        // FIM DO MÓDULO DISCIPLINAS - MATRICULAR ALUNO
                        // ##################################################################### //


                        // ##################################################################### //
                        // MÓDULO DISCIPLINAS - DESMATRICULAR ALUNO
                        case6: {
                            printf("### Módulo Disciplinas - Desmatricular aluno ###\n");
                            
                            break; // Sai do case 6
                        }

                        // FIM DO MÓDULO DISCIPLINAS - DESMATRICULAR ALUNO
                        // ##################################################################### //

                                
                        // ##################################################################### //
                        // MÓDULO DISCIPLINAS - OPÇÃO INVÁLIDA

                        default: {
                            printf("Opção inválida.\n");
                            
                            // Transição de tela
                            pausarTela();
                            limparTela();

                            break; // Sai do default
                        }

                        // FIM DO MÓDULO DISCIPLINAS - OPÇÃO INVÁLIDA
                        // ##################################################################### //

                    } // Fim do switch

                } while(opDisci != 0);

                break;
            }
            
            // FIM DO MÓDULO DISCIPLINAS
            // ############################################################################## //


            default: {
                printf("Opção inválida.\n");
                pausarTela();
                limparTela();
                break;
            } // Fim do case default

        } // Fim do switch 1

    } while (opcao != 0);

    // FIM DO MÓDULO GERAL
    // ############################################################################## //

} // Fim do programa
