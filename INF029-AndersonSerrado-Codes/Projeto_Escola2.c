//Bibliotecas
#include <stdio.h>
#include <string.h>
#include "Projeto_Escola.h" //Permite reutilizar as constantes definidas no Projeto_Escola

//Cadastro de pessoas
typedef struct {
    char *matricula; //Alocação dinâmica
    char nome[tamNome];
    char data[tamData];
    char CPF[tamCPF];
    char sexo;
} listaPessoas;

listaPessoas pessoa;

void cadastroPessoa(){
    
    int qtd;
    char matricula;

    //Alocando dinamicamente o tamanho do vetor matricula
    for (int i = 0; i < qtdAlunos; i++){
        aluno[i].matricula = malloc(n * sizeof(char));
    }

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
} //Fim do escopo