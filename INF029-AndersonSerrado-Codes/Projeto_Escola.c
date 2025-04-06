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

    while (opcao != 0){
        
        //Menu de opções
        printf("1 - Alunos");
        printf("2 - Professores");
        printf("3 - Disciplinas");





    }











}