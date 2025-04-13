//Bibliotecas
#include <stdio.h>
#include <string.h>
#include <locale.h>

/*------------------------------------------------------------------------------------------------------------*/
//Constantes

#define tamNome 100
#define tamData 10
#define tamCPF 11
#define tamAlunos 10000
#define tamProfessores 100
#define tamDisciplinas 1000

/*------------------------------------------------------------------------------------------------------------*/
//Structs

//Cadastro dos alunos
typedef struct {
    char *matricula; //Alocação dinâmica
    char nome[tamNome];
    char data[tamData];
    char CPF[tamCPF];
    char sexo;
} listaAlunos;

listaAlunos aluno[tamAlunos];

//Cadastro dos professores
typedef struct {
    char *matricula; //Alocação dinâmica
    char nome[tamNome];
    char data[tamData];
    char CPF[tamCPF];
    char sexo;
} listaProfessores;

listaProfessores professor[tamProfessores];

//Cadastro das disciplinas
typedef struct {
    char *matricula; //Alocação dinâmica
    char *codigo; //Alocação dinâmica
    int semestre;
} listaDisciplinas;

listaDisciplinas disciplina[tamDisciplinas];

/*------------------------------------------------------------------------------------------------------------*/
//Variáveis globais

int contAluno = 0, contProfessor = 0, contDisciplina = 0;

/*------------------------------------------------------------------------------------------------------------*/
//Subfunção
//Limpa a tela
void limparTela() {
    #ifdef _WIN32
        system("cls"); //Windows
    #else
        system("clear"); //Linux
    #endif
}

/*------------------------------------------------------------------------------------------------------------*/
//Função principal
int main (){

    //Configurando a codificação de caracteres
    setlocale(LC_ALL, "Portuguese"); //Windows
    //setlocale(LC_ALL, "pt_BR.UTF-8"); //Linux

    //Declarações
    int n, opcao;
    char nomeTemp[tamNome];

    //Solicita ao usuário o tamanho da matrícula
    //Considerando que a matrícula de aluno e professor tem o mesmo tamanho
    printf("Quantos caracteres tem um número de matrícula?\n");
    scanf("%d", &n);

    //Alocando dinamicamente o tamanho do vetor matricula
    //Alunos
    for (int i = 0; i < tamAlunos; i++){
        aluno[i].matricula = malloc(n * sizeof(char));
    }
    //Professores
    for (int i = 0; i < tamProfessores; i++){
        professor[i].matricula = malloc(n * sizeof(char));
    }
    //Disciplinas
    for (int i = 0; i < tamProfessores; i++){
        disciplina[i].matricula = malloc(n * sizeof(char));
    }

    //Limpa n
    n = 0;

    //Solicita ao usuário o tamanho padrão do código de uma disciplina
    printf("Quantos caracteres tem o código de uma disciplina?\n");
    scanf("%d", &n);

    //Alocando dinamicamente o tamanho do vetor código
    for (int i = 0; i < tamDisciplinas; i++){
        disciplina[i].codigo = malloc(n * sizeof(char));
    }

    limparTela();

    do {
        //Menu de opções
        printf("Informe o número da opção desejada: ");
        printf("\n0 - Sair");
        printf("\n1 - Alunos");
        printf("\n2 - Professores");
        printf("\n3 - Disciplinas\n");
    
        //Entrada de dados: opcão
        scanf("%d",&opcao);

        limparTela();

        switch (opcao){

            case 0: {
                printf("\nPrograma encerrado.");
                return 1;
            }
            
            //Alunos
            case 1: {
                int opcaoAluno;
                
                //Menu de opções
                printf("\n### Módulo Alunos ###\n");
                printf("\nInforme o número da opção desejada: ");
                printf("\n0 - Voltar ao menu anterior");
                printf("\n1 - Cadastrar aluno");
                printf("\n2 - Listar aluno");
                printf("\n3 - Atualizar aluno");
                printf("\n4 - Excluir aluno");
                printf("\n");

                //Entrada de dados: opcão do módulo de alunos
                scanf("%d",&opcaoAluno);

                limparTela();

                printf("\n### Módulo Alunos - Inserir aluno ###");
                printf("\nInforme o nome do aluno: ");

                //Flags
                int achou = 0;
                
                switch(opcao) {

                    //Inserir
                    case 1: {
                        getchar(); //Limpar o buffer do teclado
                        //Entrada de dados: matrícula
                        scanf("%s", &matricula);
                        
                        //Verifica se a matrícula já está cadastrada
                        for (int i = 0; i < qtd; i++){
                            if (strcmp(pessoa[i].matricula, matricula) == 0){ //Matrícula já cadastrada
                                printf("\nNome já cadastrado no sistema.");
                                break;
                            }
                            else{


                            }
                        }

                        break;
                    } //Fim do case 1

                    //Listar aluno
                    case 2: {

                        break;
                    } //Fim do case 2

                    //Atualizar aluno
                    case 3: {

                        break;
                    } //Fim do case 3

                    //Excluir aluno
                    case 4: {

                        break;
                    } //Fim do case 3

                    default: {
                        printf("\nOpcão inválida.");
                        break;
                    }
                } //Fim do switch

                break;
            } //Fim do case 1
            
            //Professores
            case 2: {
                printf("\nModulo Professores:\n");
                break;
            } //Fim do case 2

            //Disciplinas
            case 3: {
                printf("\nModulo Disciplinas:\n");
                break;
            } //Fim do case 3

            default: {
                printf("\nOpcão inválida.");
                break;
            }
        }
    } while (opcao != 0);
} //Fim do programa