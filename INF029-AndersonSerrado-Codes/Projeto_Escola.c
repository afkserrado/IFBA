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
    long long matricula;
    char nome[tamNome];
    char data[tamData];
    char CPF[tamCPF];
    char sexo;
} listaAlunos;

listaAlunos aluno[tamAlunos];

//Cadastro dos professores
typedef struct {
    long long matricula;
    char nome[tamNome];
    char data[tamData];
    char CPF[tamCPF];
    char sexo;
} listaProfessores;

listaProfessores professor[tamProfessores];

//Cadastro das disciplinas
typedef struct {
    long long matricula;
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

/*------------------------------------------------------------------------------------------------------------*/
//Função principal
int main (){

    //Configurando a codificação de caracteres
    setlocale(LC_ALL, "Portuguese"); //Windows
    //setlocale(LC_ALL, "pt_BR.UTF-8"); //Linux

    //Declarações
    int opcao;
    int tamCodigo;
    long long matricula;
    long long maxMatricula; //Maior número que a matrícula pode ter (Ex.: 99999999999)
    int tamMatricula = 0; //Maior quantidade de dígitos que a matrícula pode ter (Ex.: 11)
    int contAluno = 0, contProfessor = 0, contDisciplina = 0;

    //Solicita ao usuário a maior matrícula possível
    printf("\nQual a maior matrícula possível para uma pessoa? (Ex.: 9999999)\n");
    scanf("%lld", &maxMatricula);

    //Calcula a quantidade de dígitos da matrícula padrão
    long long auxMatricula;
    auxMatricula = maxMatricula;
    while (auxMatricula > 0){
        auxMatricula /= 10; //Remove o último dígito
        tamMatricula++; //Qtd de dígitos da matrícula padrão
    }

    //Solicita ao usuário o tamanho padrão do código de uma disciplina
    printf("Quantos caracteres tem o código de uma disciplina?\n");
    scanf("%d", &tamCodigo);

    //Alocando dinamicamente o tamanho do vetor código
    for (int i = 0; i < tamDisciplinas; i++){
        disciplina[i].codigo = malloc(tamCodigo * sizeof(char));
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
                printf("### Módulo Alunos ###\n");
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
                printf("### Módulo Alunos - Inserir aluno ###");

                switch(opcaoAluno){
                    
                    //Flag
                    int achou = 0;

                    //Inserir aluno
                    case 1: {
                        //Entrada de dados: matrícula
                        printf("\nInforme a matrícula do aluno: ");
                        scanf("%lld", &matricula);

                        //Calcula a quantidade de dígitos da matrícula informada
                        int digMatricula = 0;
                        auxMatricula = matricula;
                        while (auxMatricula > 0){
                            auxMatricula /= 10; //Remove o último dígito
                            digMatricula++; //Qtd de dígitos da mátricula informada
                        }

                        //Validação 1: formato da matrícula
                        if (!(matricula >= 0 && matricula <= maxMatricula && digMatricula == tamMatricula)){
                            printf("\nMatrícula inválida.");
                            printf("\n");
                            break;
                        }
                        
                        if (contAluno > tamAlunos){
                            printf("\nCadastro de alunos cheio.");
                        }
                        else{
                            //Verifica se a matrícula já está cadastrada
                            //Não está funcionando ################################
                            for (int i = 0; i < contAluno; i++){
                                if (matricula == aluno[i].matricula){ //Matrícula já cadastrada
                                    printf("\nAluno já cadastrado no sistema.");
                                    achou = 1;
                                    break; //Sai do for
                                }
                            }
    
                            if (!achou){ //Se achou diferente de 1
                                aluno[contAluno].matricula = matricula;
                                contAluno++;
                                printf("\nAluno cadastrado com sucesso!");
                            }
                        }

                        break;
                    } //Fim do case 1: Inserir aluno

                    limparTela();

                    //Listar aluno
                    case 2: {

                        break;
                    } //Fim do case 2: Listar aluno

                    //Atualizar aluno
                    case 3: {

                        break;
                    } //Fim do case 3: Atualizar aluno

                    //Excluir aluno
                    case 4: {

                        break;
                    } //Fim do case 4: Excluir aluno

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