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
// Constantes globais

#define tamNome 100
#define tamCPF 11
#define tamSexo 1
#define tamAlunos 10000
#define tamProfessores 100
#define tamDisciplinas 1000

/*--------------------------------------------------------------------------------------------------*/
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
pessoa professores[tamAlunos];

// Cadastro das disciplinas
typedef struct {
    char *matricula; // Alocação dinâmica
    char *codigo; // Alocação dinâmica
    int semestre;
} listaDisciplinas;

listaDisciplinas disciplina[tamDisciplinas];

/*--------------------------------------------------------------------------------------------------*/
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

// Valida números inteiros
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
}

// Alocando dinamicamente espaços de memória para cada aluno, professor ou disciplina
void alocarMemoria(int tamMatricula) {
    for (int i = 0; i < tamAlunos; i++) {
        alunos[i].matricula = (char*)malloc(tamMatricula * sizeof(char));
        if (alunos[i].matricula == NULL) {
            printf("Erro ao alocar memória para aluno %d\n", i);
            exit(1);
        }
    }

    for (int i = 0; i < tamProfessores; i++) {
        professores[i].matricula = (char*)malloc(tamMatricula * sizeof(char));
        if (professores[i].matricula == NULL) {
            printf("Erro ao alocar memória para professor %d\n", i);
            exit(1);
        }
    }

    for (int i = 0; i < tamDisciplinas; i++) {
        disciplina[i].matricula = (char*)malloc(tamMatricula * sizeof(char));
        if (disciplina[i].matricula == NULL) {
            printf("Erro ao alocar memória para disciplina %d\n", i);
            exit(1);
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

    if (strcmp(texto, "CPF") == 0) { // Se o texto for CPF
        if (validarCPF(entrada_Mat_CPF) != 0) { // Chama a função validarCPF
            return 1; // CPF inválido 
        }
    }

    return 0; // Matrícula ou CPF válido
}

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

// Valida o sexo
int validarSexo(char entrada_sexo[tamSexo + 2], char texto_pessoa[]) {
    
    // entrada_sexo: ponteiro que aponta para o endereço de memória do vetor sexo
    // "sexo" é um vetor pertencente à função que chamou validarNome
    // tamSexo + 2: tamSexo caracteres úteis + \n + \0. Garante que o usuário consiga digitar até tamSexo caracteres
    
    // Entrada de dados
    printf("Informe o sexo (M ou F) do %s: ", texto_pessoa);
    if (fgets(entrada_sexo, tamSexo + 2, stdin) == NULL) {
        printf("Erro ao ler o sexo.\n");
        return 1; // Trata erro de leitura
    }

    // Verifica truncamento e remove \n
    if (strchr(entrada_sexo, '\n') == NULL) {
        limparBuffer(); // Limpa o excesso de caracteres
        printf("Erro: excesso de caracteres (máx. %d caracteres)\n", tamSexo);
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

// Cadastra um aluno ou professor
void inserirPessoa(int tamMatricula, const char *texto_pessoa, int contPessoa, pessoa *pessoas, int tamPessoas) {
    
    // Estrutura da função: (quant. de dígitos da matrícula, "aluno" ou "professor", índice do aluno ou professor, struct alunos ou professores, tamanho do vetor alunos ou professores)

    //const char* texto_pessoa: garante que a string passada, "aluno" ou "professor", não seja modificada dentro da função

    int achou = 0; // Flag

    char txtPessoa[10];
    strcpy(txtPessoa, texto_pessoa); // Copia "aluno" ou "professor"
    txtPessoa[0] = toupper(txtPessoa[0]); // Capitaliza para "Aluno" ou "Professor"
                            
    printf("### Módulo %s - Inserir %s ###\n", txtPessoa, texto_pessoa);

    // Verifica se a lista de alunos ou professores está cheia
    if (contPessoa > tamPessoas) { // Lista cheia
        printf("\nCadastro cheio. Não é possível inserir outro %s.\n", texto_pessoa);
        pausarTela();
        limparTela();
    }
    
    // Lista de alunos não cheia
    else {
        
        // ############################################################################## //
        // MATRÍCULA
    
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

        // FIM MATRÍCULA
        // ############################################################################## //
        
        // Matrícula ainda não cadastrada
        if (achou == 0) {

            // Armazena matrícula
            strcpy(pessoas[contPessoa].matricula, matricula);
                       
            // ############################################################################## //
            // NOME

            // Variáveis auxiliares
            int flagNome = 0;
            char nome[tamNome]; // Variável temporária
            
            // Recebe e valida o nome
            do {
                flagNome = validarNome(nome, texto_pessoa);
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
                    flagData = validarData(&dia, &mes, &ano, texto_pessoa);
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
            char CPF[tamCPF + 1]; // Variável temporária (+1 do \0)
            char textoCPF[] = "CPF";

            // Recebe e valida o CPF
            do {
                flagCPF = validarMat_CPF(tamCPF, CPF, textoCPF, texto_pessoa);
                if (flagCPF != 0) {
                    printf("\n");
                }                    
            } while (flagCPF != 0);

            //Armazena o CPF
            strcpy(pessoas[contPessoa].CPF, CPF);

            // FIM CPF
            // ############################################################################## //


            // ############################################################################## //
            // SEXO

            // Variáveis auxiliares
            int flagSexo = 0;
            char sexo[tamSexo]; // Variável temporária
            
            // Recebe e valida o sexo
            do {
                flagSexo = validarSexo(sexo, texto_pessoa);
                if (flagSexo != 0) {
                    printf("\n");
                }
            } while (flagSexo != 0);
            
            // Armazena o sexo
            strcpy(pessoas[contPessoa].sexo, sexo);

            // SEXO
            // ############################################################################## //

            printf("\n%s cadastrado com sucesso!\n", txtPessoa);
            pausarTela();
            limparTela();

        } // Fim do if (achou == 0)

    } // Fim do else (lista de alunos não cheia)

} // Fim da função

/*------------------------------------------------------------------------------------------------------------*/
//Função principal
int main (){

    configurarLocale();

    //Declarações
    int opcao;
    int contAluno = 0, contProfessor = 0, contDisciplina = 0;

    // ############################################################################## //
    // DEFINE O PADRÃO PARA O NÚMERO DE MATRÍCULA

    int flagInteiro = 0;
    int tamMatricula = 0;
    
    // Recebe o tamanho da matrícula e valida
    do {
        printf("Quantos dígitos tem uma matrícula (apenas inteiros positivos)? ");
        flagInteiro = validarInteiroPositivo(&tamMatricula);
        printf("\n");
    } while (flagInteiro != 0);
    
    printf("Tamanho da matrícula: %d\n", tamMatricula);
    
    // Alocação dinâmica de espaços para cada aluno, professor e disciplina
    alocarMemoria(tamMatricula);

    // FIM
    // ############################################################################## //

    // Transição de tela
    pausarTela();
    limparTela();

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
                    printf("\n1 - Cadastrar aluno");
                    printf("\n2 - Listar aluno");
                    printf("\n3 - Atualizar aluno");
                    printf("\n4 - Excluir aluno");
                    printf("\n");
                    
                    //Entrada de dados: Opção do Módulo de alunos
                    scanf("%d",&opcaoAluno);
                    limparBuffer();
                    
                    // Transição de tela
                    limparTela();
    
                    //Switch 2
                    switch(opcaoAluno){

                        //Voltar ao menu anterior
                        case 0: {
                            limparTela();
                            break;
                        } // Fim do switch 2, case 0: voltar ao menu anteior


                        // ##################################################################### //
                        // MÓDULO ALUNOS - INSERIR

                        case 1: {
                            printf("### Módulo Alunos - Inserir aluno ###\n");
                            
                            // Chamar função
                            inserirPessoa(tamMatricula, "aluno", contAluno, &alunos, tamAlunos);
                            contAluno++; // Incrementa a contagem de alunos

                            break; // Sai do case 1
                        }

                        // FIM DO MÓDULO ALUNOS - INSERIR
                        // ##################################################################### //
                        

                        // ##################################################################### //
                        // MÓDULO ALUNOS - LISTAR

                        case 2: {
                            printf("### Módulo Alunos - Listar alunos ###");
                            break; // Sai do case 2
                        }

                        // FIM DO MÓDULO ALUNOS - LISTAR
                        // ##################################################################### //
        

                        // ##################################################################### //
                        // MÓDULO ALUNOS - ATUALIZAR

                        case 3: {
                            printf("### Módulo Alunos - Atualizar aluno ###");
                            break; // Sai do case 3
                        }

                        // FIM DO MÓDULO ALUNOS - ATUALIZAR
                        // ##################################################################### //
        

                        // ##################################################################### //
                        // MÓDULO ALUNOS - EXCLUIR
                        case 4: {
                            printf("### Módulo Alunos - Excluir aluno ###");
                            break; // Sai do case 4 
                        }

                        // FIM DO MÓDULO ALUNOS - EXCLUIR
                        // ##################################################################### //

                                
                        // ##################################################################### //
                        // MÓDULO ALUNOS - OPÇÃO INVÁLIDA

                        default: {
                            printf("Opção inválida.\n");
                            pausarTela();
                            limparTela();
                            break; // Sai do default
                        }

                        // FIM DO MÓDULO ALUNOS - OPÇÃO INVÁLIDA
                        // ##################################################################### //

                    } // Fim do switch 2

                } while(opcaoAluno != 0);

                break;
            } // Fim do case 1, Alunos, do módulo geral

            // FIM DO MÓDULO ALUNOS
            // ############################################################################## //

            
            // ############################################################################## //
            // MÓDULO PROFESSORES

            case 2: {
                printf("\nMódulo Professores:\n");
                break;
            } // Fim do case 2

            // FIM DO MÓDULO PROFESSORES
            // ############################################################################## //


            // ############################################################################## //
            // MÓDULO DISCIPLINAS

            case 3: {
                printf("\nMódulo Disciplinas:\n");
                break;
            } // Fim do case 3
            
            // FIM DO MÓDULO DISCIPLINAS
            // ############################################################################## //


            default: {
                printf("Opção inválida.");
                pausarTela();
                limparTela();
                break;
            } // Fim do case default

        } // Fim do switch 1

    } while (opcao != 0);

    // FIM DO MÓDULO GERAL
    // ############################################################################## //
    
    // Libera a memória alocada
    for (int i = 0; i < tamAlunos; i++) {
        free(alunos[i].matricula);
    }

    for (int i = 0; i < tamProfessores; i++) {
        free(professores[i].matricula);
    }

    for (int i = 0; i < tamDisciplinas; i++) {
        free(disciplina[i].matricula);
    }

} // Fim do programa