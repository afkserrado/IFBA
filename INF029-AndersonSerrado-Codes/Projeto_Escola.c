//Bibliotecas
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <time.h>
#include <locale.h>

/*------------------------------------------------------------------------------------------------------------*/
//Constantes

#define tamNome 100
#define tamData 10
#define tamCPF 11
#define tamGenero 3
#define tamAlunos 10000
#define tamProfessores 100
#define tamDisciplinas 1000

/*------------------------------------------------------------------------------------------------------------*/
//Structs

//Data
typedef struct {
    int dia;
    int mes;
    int ano;
} data;

//Cadastro dos alunos
typedef struct {
    long long int matricula;
    char nome[tamNome];
    char CPF[tamCPF + 1]; //+1 para o terminador nulo
    char genero[3]; //+1 para o terminador nulo
    data nascimento;
} listaAlunos;

listaAlunos aluno[tamAlunos];

//Cadastro dos professores
typedef struct {
    long long int matricula;
    char nome[tamNome];
    char CPF[tamCPF + 1]; //+1 para o terminador nulo
    char genero[3]; //+1 para o terminador nulo
    data nascimento;
} listaProfessores;

listaProfessores professor[tamProfessores];

//Cadastro das disciplinas
typedef struct {
    long long int matricula;
    char *codigo; //Alocação dinâmica
    int semestre;
} listaDisciplinas;

listaDisciplinas disciplina[tamDisciplinas];

/*------------------------------------------------------------------------------------------------------------*/
//Subfunções

//Limpa a tela
void limparTela() {
    #ifdef _WIN32
        system("cls"); //Windows
    #else
        system("clear"); //Linux
    #endif
}

//Pausa a tela
void pausarTela() {
    #ifdef _WIN32
        pausarTela(); //Windows
    #else
        printf("Pressione Enter para continuar...");
        getchar();
    #endif
}

//Valida a matrícula
int validarMatricula (long long int matricula, long long int maxMatricula, int tamMatricula) {
    long long int auxMatricula = matricula;
    int digMatricula = 0, flagMatricula = 0;

    //Calcula a quantidade de dígitos da matrícula informada
    while (auxMatricula > 0){
        auxMatricula /= 10; //Remove o último dígito
        digMatricula++; //Qtd de dígitos da matrícula informada
    }

    //Validação
    if (!(matricula >= 0 && matricula <= maxMatricula && digMatricula == tamMatricula)){
        flagMatricula = 1;
    }
    return flagMatricula;
}

//Valida o nome
int validarNome(char nome[tamNome]) {
    int len = strlen(nome);
    int flagNome = 0;

    // Verifica se o nome não está vazio
    if (len == 0) {
        printf("Nome não pode estar vazio.\n");
        return 1;  //Retorna 1 para indicar erro
    }

    for (int i = 0; i < len; i++){
        //Permite letras, espaços e caracteres acentuados
        if (!(isalpha(nome[i]) || nome[i] == ' ')) {
            flagNome = 1; //Nome inválido
            break;
        }
    }
    return flagNome;
}

//Verifica se o ano é bissexto
int bissexto(int ano) {
    if(((ano % 4 == 0) && (ano % 100 != 0)) || (ano % 400 == 0)){
        return 1; //é bissexto
    }
    else {
        return 0; //Não é bissexto
    }
}

//Valida a data de nascimento
int validarData(int dia, int mes, int ano) {
    int flagBissexto = bissexto(ano);
    int diasMes[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    
    //Valida o ano
    if (ano < 1900 || ano > 2100) {
        return 1; //Ano inválido
    }

    //Valida o mês
    if (mes < 1 || mes > 12) {
        return 1; //Mês inválido
    }

    //Atualiza a qtd de dias em fevereiro se o ano for bissexto
    if (flagBissexto == 1) {
        diasMes[1] = 29; //Fevereiro
    }

    //Valida o dia
    if (dia < 1 || dia > diasMes[mes - 1]) {
        return 1; //Dia inválido
    }

    return 0; //Data válida
}

//Valida o CPF
//#######################Rever, meu CPF está dando inválido
int validarCPF (char CPF[tamCPF + 1]) { //+1 para o terminador nulo
    int digCPF = 0;

    //Quantidade de dígitos do CPF
    digCPF = strlen(CPF);

    //Verifica se o CPF só contém números
    for (int i = 0; i < tamCPF; i++) {
        if (!(CPF[i] >= '0' && CPF[i] <= '9')) {
            return 1; //CPF inválido (não contém apenas números)
        }
    }

    //Verifica se o CPF tem 11 dígitos
    if (digCPF != 11) {
        return 1; //CPF inválido
    }  
    
    //Verifica se todos os dígitos são iguais
    int todosIguais = 1;
    for (int i = 1; i < 11; i++) {
        if (CPF[i] != CPF[0]) {
            todosIguais = 0;
            break;
        }
    }
    if (todosIguais == 1) return 1;

    //Verifica o 10o dígito
    int soma1 = 0;
    for (int i = 0; i < 9; i++) { //Percorre do 1o ao 9o dígito
        soma1 += (CPF[i] - '0') * (10 - i);
    }
    int resto1 = soma1 % 11;

    if (!((resto1 < 2 && CPF[9] == '0') || (CPF[9] == (11 - resto1))))
        return 1; //CPF inválido

    //Verifica o 11o dígito
    int soma2 = 0;
    for (int i = 0; i < 10; i++) { //Percorre do 1o ao 10o dígito
        soma2 += (CPF[i] - '0') * (11 - i);
    }
    int resto2 = soma2%11;

    if (!((resto2 < 2 && CPF[10] == '0') || (CPF[10] == (11 - resto2)))) {
        return 1; //CPF inválido
    }
        
    return 0; //CPF válido
}

//Valida a identidade de gênero
int validarGenero(char genero[tamGenero]) {
    if (!(strcmp(genero, "M") == 0 || strcmp(genero, "F") == 0 || strcmp(genero, "NB") == 0 || strcmp(genero, "ND") == 0)) {
        return 1; //Gênero inválido
    }
    return 0; //Gênero válido
}

/*------------------------------------------------------------------------------------------------------------*/
//Função principal
int main (){

    //Configurando a codificação de caracteres
    setlocale(LC_ALL, "Portuguese"); //Windows
    //setlocale(LC_ALL, "pt_BR.UTF-8"); //Linux

    //Declarações
    int opcao;
    int tamCodigo = 0;
    long long int maxMatricula; //Maior número que a matrícula pode ter (Ex.: 99999999999)
    int contAluno = 0, contProfessor = 0, contDisciplina = 0;

    //Solicita ao usuário a maior matrícula possível
    printf("\nQual a maior matrícula possível para uma pessoa? (Ex.: 9999999)\n");
    scanf("%lld", &maxMatricula);

    //Calcula a quantidade de dígitos da matrícula padrão
    long long int auxMatricula = maxMatricula;
    int tamMatricula = 0; //Qtd de dígitos
    while (auxMatricula > 0){
        auxMatricula /= 10; //Remove o último dígito
        tamMatricula++; //Qtd de dígitos da matrícula padrão
    }

    //Solicita ao usuário o tamanho padrão do código de uma disciplina
    /*printf("Quantos caracteres tem o código de uma disciplina?\n");
    scanf("%d", &tamCodigo);*/

    //Alocando dinamicamente o tamanho do vetor código
    for (int i = 0; i < tamDisciplinas; i++){
        //"(char*)" converte o ponteiro do tipo void*, retornado por malloc, para um ponteiro tipo char, mesmo tipo do campo "codigo"
        disciplina[i].codigo = (char*)malloc(tamCodigo * sizeof(char));
    }

    limparTela();

    do {
        //Menu de opções
        printf("### Menu principal ###");
        printf("\nInforme o número da opção desejada: ");
        printf("\n0 - Sair");
        printf("\n1 - Alunos");
        printf("\n2 - Professores");
        printf("\n3 - Disciplinas\n");
    
        //Entrada de dados: Opção
        scanf("%d",&opcao);

        limparTela();

        //Switch 1
        switch (opcao){

            case 0: {
                printf("Programa encerrado.");
                return 1;
            }
            
            //Alunos
            case 1: {
                int opcaoAluno;
                
                do{
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
                    
                    limparTela();
    
                    //Switch 2
                    switch(opcaoAluno){

                        //Voltar ao menu anterior
                        case 0: {
                            limparTela();
                            break;
                        } //Fim do switch 2, case 0: voltar ao menu anteior

                        //Inserir aluno
                        case 1: {
                            
                            int achou = 0; //Flag
                            
                            printf("### Módulo Alunos - Inserir aluno ###\n");

                            int flagMatricula = 0;
                            long long int matricula;
                            //Recebe e valida a matrícula
                            do {
                                printf("Informe a matrícula do aluno: ");
                                scanf("%lld", &matricula);
                                flagMatricula = validarMatricula(matricula, maxMatricula, tamMatricula);
                                //printf("%d", flagMatricula);

                                if (flagMatricula != 0)
                                    printf("\nMatrícula inválida.\n");
                            } while (flagMatricula != 0);
                            
                            //Lista de alunos cheia
                            if (contAluno > tamAlunos){
                                printf("\nCadastro de alunos cheio.\n");
                                pausarTela();
                                limparTela();
                            }
                            
                            //Lista de alunos Não cheia
                            else{
                                //Verifica se a matrícula já está cadastrada
                                for (int i = 0; i < contAluno; i++){
                                    if (matricula == aluno[i].matricula){ //matrícula já cadastrada
                                        printf("\nAluno já cadastrado no sistema.\n");
                                        achou = 1;
                                        pausarTela();
                                        limparTela();
                                        break; //Sai do for
                                    }
                                }
                                
                                //Matrícula ainda não cadastrada
                                if (achou == 0){
                                    //Armazena matrícula
                                    aluno[contAluno].matricula = matricula;
                                                                    
                                    int flagNome = 0;
                                    char nome[tamNome];
                                    //Recebe e valida o nome
                                    do {
                                        printf("Informe o nome do aluno: ");
                                        scanf(" %[^\n]", nome);
                                        flagNome = validarNome(nome);
                                        //printf("%d", flagNome);

                                        if (flagNome != 0)
                                            printf("\nNome inválido. Apenas letras, com ou sem acento, e espaços são permitidas.\n");
                                    } while (flagNome != 0);
                                    
                                    //Armazena o nome
                                    if (flagNome == 0){ //if desnecessário. Apenas por precaução
                                        strcpy(aluno[contAluno].nome, nome);
                                    }

                                    int flagData = 0;
                                    int dia, mes, ano;
                                    //Recebe e valida a data
                                    do {
                                        printf("Informe a de nascimento do aluno (DD/MM/AAAA): ");
                                        scanf("%d/%d/%d", &dia, &mes, &ano);
                                        flagData = validarData(dia, mes, ano);
                                        //printf("%d", flagData);

                                        if (flagData != 0)
                                            printf("\nData inválida.\n");
                                    } while (flagData != 0);

                                    int flagCPF = 0;
                                    char CPF[tamCPF + 1]; //+1 para o terminador nulo
                                    //Recebe e valida o CPF
                                    do {
                                        printf("Informe o CPF (apenas números): ");
                                        scanf(" %s", CPF);
                                        flagCPF = validarCPF(CPF);
                                        //printf("%d", flagCPF);

                                        if (flagCPF != 0)
                                            printf("\nCPF inválido.\n");
                                    } while (flagCPF != 0);

                                    int flagGenero = 0;
                                    char genero[tamGenero];
                                    //Recebe e valida a identidade de gênero
                                    do {
                                        printf("Informe a identidade de gênero: ");
                                        printf("\nM - Masculino");
                                        printf("\nF - Feminino");
                                        printf("\nNB - Não binário");
                                        printf("\nND - Não declarado\n");
                                        scanf(" %s", genero);

                                        //Converte os caracteres para maiúsculo
                                        for (int i = 0; i <= strlen(genero) - 1; i++) {
                                            genero[i] = toupper(genero[i]);
                                        }

                                        flagGenero = validarGenero(genero);
                                        //printf("%d", flagGenero);

                                        if (flagGenero != 0)
                                            printf("\nIdentidade de gênero inválida.\n");
                                    } while (flagGenero != 0);
                                    
                                    contAluno++; //Incrementa a quantidade de alunos

                                    printf("\nAluno cadastrado com sucesso!\n");
                                    pausarTela();
                                    limparTela();
                                }
                            } //Fim do else
                            break; //Sai do case 1
                        } //Fim do switch 2, case 1: Inserir aluno
                        
                        //Listar aluno
                        case 2: {
                            printf("### Módulo Alunos - Listar alunos ###");
                            break; //Sai do case 2
                        } //Fim do switch 2, case 2: Listar aluno
        
                        //Atualizar aluno
                        case 3: {
                            printf("### Módulo Alunos - Atualizar aluno ###");
                            break; //Sai do case 3
                        } //Fim do switch 2, case 3: Atualizar aluno
        
                        //Excluir aluno
                        case 4: {
                            printf("### Módulo Alunos - Excluir aluno ###");
                            break; //Sai do case 4 
                        } //Fim do switch 2, case 4: Excluir aluno
                                
                        //opção inválida
                        default: {
                            printf("\nOpção inválida.\n");
                            pausarTela();
                            limparTela();
                            break; //Sai do default
                        } //Fim do switch 2, default: opção inválida
                    } //Fim do switch 2

                } while(opcaoAluno != 0);

                break;
            } //Fim do case 1

            //Professores
            case 2: {
                printf("\nMódulo Professores:\n");
                break;
            } //Fim do case 2

            //Disciplinas
            case 3: {
                printf("\nMódulo Disciplinas:\n");
                break;
            } //Fim do case 3

            default: {
                printf("\nOpção inválida.");
                break;
            }
        } //Fim do switch 1
    } while (opcao != 0);
} //Fim do programa