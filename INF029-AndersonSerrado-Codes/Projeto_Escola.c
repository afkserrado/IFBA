/* Observações e ideias:
Obs. 1: validar todas as entradas (quantidade de caracteres e tipo de caractere);
Obs. 2: No cadastro das disciplinas, listar todos professores já cadastrados com um número relacionado a ele (1, 2, 3 etc.). Solicitar que o usuário digite o número referente ao professor. Isso evita cadastrar na disciplina um professor que não exista.
Obs. 3: uma alternativa à Obs. 2 é validar o nome no cadastro da disciplina. Se não existir, o cadastro de disciplina chama a função de cadastro de professor.
*/


#include <stdio.h>
#include <string.h>

//Tamanho dos vetores
#define tamMatricula 11
#define tamNome 100
#define tamData 10
#define tamCPF 11
#define tamCodigo 6

//Structs
//Cadastro dos alunos
typedef struct {
    char matricula[tamMatricula];
    char nome[tamNome];
    char sexo;
    char data[tamData];
    char CPF[tamCPF];
} cadastroAlunos;

cadastroAlunos aluno;

//Cadastro dos professores
typedef struct {
    char matricula[tamMatricula];
    char nome[tamNome];
    char sexo;
    char data[tamData];
    char CPF[tamCPF];
} cadastroProfessores;

cadastroProfessores professor;

//Cadastro das disciplinas
typedef struct {
    char nome[tamNome];
    char codigo[tamCodigo];
    char professor[tamNome];
    int semestre;
} cadastroDisciplinas;

cadastroDisciplinas disciplina;

//Função principal
int main (){

    //Declarações
    int opcao;

    //while 1
    while (opcao != 0){
        
        //Menu de opções
        printf("\n0 - Sair");
        printf("\n1 - Alunos");
        printf("\n2 - Professores");
        printf("\n3 - Disciplinas\n");
    
        //Entrada de dados: opcão
        scanf("%d",&opcao);

        switch (opcao) {

            case 0: {
                printf("\nPrograma encerrado.");
                break;
            }
            
            case 1: {
                printf("\nModulo Alunos:\n");
                printf("\n1 - Listar Alunos");
                printf("\n2 - Listar Alunos por sexo (Masculino/Feminino)");
                printf("\n3 - Listar Alunos ordenados por Nome");
                printf("\n4 - Listar Alunos ordenados por data de nascimento");
                printf("\n5 - Aniversariantes do mês");
                printf("\n6 - Lista de alunos matriculados em menos de 3 disciplinas");
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
                printf("\nOpcao invalida.");
                break;
            }
        }
    } //Fim do while 1
} //Fim do programa