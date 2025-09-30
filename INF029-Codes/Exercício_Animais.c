/*Faça um programa que cadastre até 10 animais com as seguintes informações: idade e sexo.
Ainda, deve ser possível: 
- Cadastrar um a um;
- Listar todos os animais;
- Listar os animais que têm a idade maior ou igual a média de todos os animais.
*/

#include <stdio.h>
#include <string.h>
#include <ctype.h>
#define tam 10
#define tamNome 100
#define tamData 10 //Formato DD/MM/AAAA

//Registro para datas
typedef struct{
    int dia;
    int mes;
    int ano;
} Data;

//Registro para armazenar os dados dos animais
typedef struct{
    int id;
    int idade;
    char sexo;
    char nome[tamNome];
    Data nascimento;
} listaAnimais;

int main(){

    //Variável Animal da struct Animais
    listaAnimais Animal[tam];

    //Declarações
    int i, cont = 0, soma, media, opcao, matricula, incrementadorMatricula = 0;

    //While 1
    while (cont < tam){

        //Menu de opções
        printf("\nInforme 1 para cadastrar um animal. ");
        printf("\nInforme 2 para listar todos os animais.");
        printf("\nInforme 3 para listar todos os animais com idade acima da média.");
        printf("\nInforme 4 para excluir um animal.");
        printf("\nInforme 0 para encerrar.\n");
        scanf("%d", &opcao);

        //Switch 1
        switch(opcao){
            //Cadastrar animais
            case 1:
                if (cont < tam){
                    getchar(); //Limpar o '\n' do buffer
                    
                    //ID
                    Animal[cont].id = ++incrementadorMatricula;
                    
                    //Nome
                    printf("\nInforme o nome do animal: ");
                    fgets(Animal[cont].nome, sizeof(Animal[cont].nome), stdin);
                    Animal[cont].nome[strcspn(Animal[cont].nome, "\n")] = '\0';
                    
                    //Data de nascimento
                    printf("\nInforme a data de nascimento no formato DD/MM/AAAA: ");
                    scanf("%d/%d/%d", &Animal[cont].nascimento.dia, &Animal[cont].nascimento.mes, &Animal[cont].nascimento.ano);
                    
                    //Idade
                    printf("\nInforme a idade do animal: ");
                    scanf("%d", &Animal[cont].idade);
                    
                    //Sexo
                    printf("\nInforme o sexo do animal: ");
                    scanf(" %c", &Animal[cont].sexo);
                    Animal[cont].sexo = toupper(Animal[cont].sexo);
    
                    //Contador
                    cont++;
                }
                else
                    printf("\nPET cheio.");
            break;

            //Listar animais
            case 2:
                for (i = 0; i < cont; i++){
                    printf("\nID: %d ## Nome: %s ## Data de nascimento: %02d/%02d/%04d ## Idade: %d ## Sexo: %c", Animal[i].id, Animal[i].nome, Animal[i].nascimento.dia, Animal[i].nascimento.mes, Animal[i].nascimento.ano, Animal[i].idade, Animal[i].sexo);
                }
                printf("\n");
            break;
            
            //Listar animais com idade acima da média
            case 3:
                //Calculando a média
                soma = 0;
                media = 0;

                if (cont == 0){
                    printf("\nNão há animais cadastrados.");
                    break;
                }
                    
                for (i = 0; i < cont; i++){
                    soma += Animal[i].idade;
                }
                media = soma / cont;

                for (i = 0; i < cont; i++){
                    if (Animal[i].idade > media){
                        printf("\nID: %d ## Nome: %s ## Data de nascimento: %02d/%02d/%04d ## Idade: %d ## Sexo: %c", 
                            Animal[i].id,
                            Animal[i].nome, 
                            Animal[i].nascimento.dia, 
                            Animal[i].nascimento.mes, 
                            Animal[i].nascimento.ano, 
                            Animal[i].idade, 
                            Animal[i].sexo);
                    }
                }
                printf("\n");
            break;

            //Excluir animais
            case 4:
                //Verifica se há animais cadastrados
                if (cont > 0){
                    //Lista os animais cadastrados
                    for (i = 0; i < cont; i++){
                        printf("\nID: %d ## Nome: %s ## Data de nascimento: %02d/%02d/%04d ## Idade: %d ## Sexo: %c", 
                            Animal[i].id, 
                            Animal[i].nome, 
                            Animal[i].nascimento.dia, 
                            Animal[i].nascimento.mes, 
                            Animal[i].nascimento.ano, 
                            Animal[i].idade, 
                            Animal[i].sexo);
                    }
                    printf("\n");
                    
                    //Entrada de dados: matrícula a ser excluída
                    printf("\nInforme o ID do animal a ser excluído: ");
                    scanf("%d", &matricula);
                    int achou = 0; //Flag

                    //Percorrendo o vetor até encontrar uma matrícula coincidente
                    for (i = 0; i < cont; i++){
                        if (matricula == Animal[i].id){
                            achou = 1;
                            
                            //Shift
                            for (int j = i; j < cont; j++){
                                Animal[j] = Animal[j + 1];
                            }
                            printf("\nExclusão realizada com sucesso!");
                            cont--;
                            break;
                        }
                    }
                    if (!achou)
                        printf("\nNão foi encontrado nenhum animal com essa matrícula.");

                    printf("\n");
                }
                else
                    printf("\nNão há animais cadastrados.");
            break;
             
            //Encerrar programa  
            case 0:
                printf("\nPrograma encerrado.");
                return 1;
            break;
            
            //Opções inválidas
            default:
                printf("\nOpcao invalida.");
                printf("\n");
            break;
            
            } //Fim do Switch 1
    } //Fim do While 1
} //Fim do programa