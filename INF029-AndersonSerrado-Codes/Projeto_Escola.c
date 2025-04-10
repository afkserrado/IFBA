/* Observações e ideias:
Obs. 1: validar todas as entradas (quantidade de caracteres e tipo de caractere);
Obs. 2: No cadastro das disciplinas, listar todos professores já cadastrados com um número relacionado a ele (1, 2, 3 etc.). Solicitar que o usuário digite o número referente ao professor. Isso evita cadastrar na disciplina um professor que não exista.
Obs. 3: uma alternativa à Obs. 2 é validar o nome no cadastro da disciplina. Se não existir, o cadastro de disciplina chama a função de cadastro de professor.
*/

//Bibliotecas
#include <stdio.h>
#include <string.h>
#include <locale.h>

//Tamanho dos vetores
#define tamNome 100
#define tamData 10
#define tamCPF 11
#define qtdAlunos 1000
#define qtdProfessores 1000
#define qtdDisciplinas 1000

/*------------------------------------------------------------------------------------------------------------*/

//Structs
//Cadastro dos alunos
typedef struct {
    char *matricula; //Alocação dinâmica
    char nome[tamNome];
    char data[tamData];
    char CPF[tamCPF];
    char sexo;
} cadastroAlunos;

cadastroAlunos aluno[qtdAlunos];

//Cadastro dos professores
typedef struct {
    char *matricula; //Alocação dinâmica
    char nome[tamNome];
    char data[tamData];
    char CPF[tamCPF];
    char sexo;
} cadastroProfessores;

cadastroProfessores professor[qtdProfessores];

//Cadastro das disciplinas
typedef struct {
    char *codigo; //Alocação dinâmica
    char nome[tamNome];
    char professor[tamNome];
    int semestre;
} cadastroDisciplinas;

cadastroDisciplinas disciplina[qtdDisciplinas];

/*------------------------------------------------------------------------------------------------------------*/

//Subfunção
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
    printf("Quantos caracteres tem uma matrícula?\n");
    scanf("%d", &n);

    //Alocando dinamicamente o tamanho do vetor matricula
    for (int i = 0; i < qtdAlunos; i++){
        aluno[i].matricula = malloc(n * sizeof(char));
    }

    for (int i = 0; i < qtdProfessores; i++){
        professor[i].matricula = malloc(n * sizeof(char));
    }

    //Limpa n
    n = 0;

    //Solicita ao usuário o tamanho padrão do código de uma disciplina
    printf("Quantos caracteres tem o código de uma disciplina?\n");
    scanf("%d", &n);

    //Alocando dinamicamente o tamanho do vetor código
    for (int i = 0; i < qtdDisciplinas; i++){
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

        switch (opcao) {

            case 0: {
                printf("\nPrograma encerrado.");
                return 1;
            }
            
            case 1: {
                //Menu de opções
                printf("\n### Módulo Alunos ###\n");
                printf("\nInforme o número da opção desejada: ");
                printf("\n1 - Inserir aluno");
                printf("\n2 - Atualizar aluno");
                printf("\n3 - Excluir aluno");
                printf("\n4 - Listar alunos");
                printf("\n5 - Listar alunos por sexo (Masculino/Feminino)");
                printf("\n6 - Listar alunos ordenados por Nome");
                printf("\n7 - Listar alunos ordenados por data de nascimento");
                printf("\n8 - Aniversariantes do mês");
                printf("\n9 - Lista de alunos matriculados em menos de 3 disciplinas");
                printf("\n0 - Voltar ao menu anterior");
                printf("\n");

                //Entrada de dados: opcão do módulo de alunos
                scanf("%d",&opcao);

                limparTela();

                switch(opcao) {

                    case 1: {
                        printf("\n### Módulo Alunos - Inserir aluno ###");
                        printf("\nInforme o nome do aluno: ");
                        getchar(); //Limpar o buffer do teclado
                        fgets(nomeTemp, sizeof(nomeTemp), stdin);
                        nomeTemp[strcspn(nomeTemp, "\n")] = '\0';
                        
                        //Verifica se o aluno já está cadastrado
                        for (int i = 0; i < qtdAlunos; i++){
                            if (strcmp(aluno[i].nome, nomeTemp) == 0){ //Nome já cadastrado
                                printf("\nNome já cadastrado no sistema.");
                                
                            } 


                        }
                        
                        fgets(aluno.nome, sizeof(aluno.nome), stdin);
                        aluno.nome[strcspn(aluno.nome, "\n")] = '\0';

                        break;
                    }
                }
                break;
            }

            case 2: {
                printf("\nModulo Professores:\n");
                break;
            }

            case 3: {
                printf("\nModulo Disciplinas:\n");
                break;
            }

            default: {
                printf("\nOpcão inválida.");
                break;
            }
        }
    } while (opcao != 0);
} //Fim do programa