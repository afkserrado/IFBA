/*Faça um programa que cadastre até 10 animais com as seguintes informações: idade e sexo.
Ainda, deve ser possível: 
- Cadastrar um a um;
- Listar todos os animais;
- Listar os animais que têm a idade maior ou igual a média de todos os animais.
*/

#include <stdio.h>
#include <string.h>
#define tam 10
#define tamNome 100
#define tamData 10 //Formato DD/MM/AAAA

int main(){
    
    //Registro para datas
    struct Data{
        int dia;
        int mes;
        int ano;
    };
    
    //Registro para armazenar os dados dos animais
    struct Animais{
        int idade[tam];
        char sexo[tam];
        char nome[tam][tamNome];
        struct Data nascimento[tam];
    };

    //Variável Animal da struct Animais
    struct Animais Animal;

    //Declarações
    int i, cont = 0, soma = 0, media = 0, opcao;
    char data_aux[tamData];

    //While 1
    while (cont < tam){

        //Menu de opções
        printf("\nInforme 1 para cadastrar um animal. ");
        printf("\nInforme 2 para listar todos os animais.");
        printf("\nInforme 3 para listar todos os animais com idade acima da média.");
        printf("\nInforme 0 para encerrar.\n");
        scanf("%d", &opcao);

        //Switch 1
        switch(opcao){
            //Cadastrar animais
            case 1:
                getchar(); //Limpar o '\n' do buffer
                
                //Nome
                printf("\nInforme o nome do animal: ");
                fgets(Animal.nome[cont], sizeof(Animal.nome[cont]), stdin);
                Animal.nome[cont][strcspn(Animal.nome[cont], "\n")] = '\0';
                
                //Data de nascimento
                printf("\nInforme a data de nascimento no formato DD/MM/AAAA: ");
                scanf(" %s", data_aux);
                
                //Idade
                printf("\nInforme a idade do animal: ");
                scanf("%d", &Animal.idade[cont]);
                
                //Sexo
                printf("\nInforme o sexo do animal: ");
                scanf(" %c", &Animal.sexo[cont]);
                Animal.sexo[cont] = toupper(Animal.sexo[cont]);

                //Separando a data
                Animal.nascimento[cont].dia = (data_aux[0] - '0') * 10 + (data_aux[1] - '0');
                Animal.nascimento[cont].mes = (data_aux[3] - '0') * 10 + (data_aux[4] - '0');
                Animal.nascimento[cont].ano = (data_aux[6] - '0') * 1000 + (data_aux[7] - '0') * 100 + (data_aux[8] - '0') * 10 + (data_aux[9] - '0');
                
                //Calculando a média
                soma += Animal.idade[cont];
                media = soma / (cont + 1);

                //Contador
                cont++;
            break;

            //Listar animais
            case 2:
                for (i = 0; i < cont; i++){
                    printf("\nNome: %s / Data de nascimento: %02d/%02d/%04d / Idade: %d / Sexo: %c", Animal.nome[i], Animal.nascimento[i].dia, Animal.nascimento[i].mes, Animal.nascimento[i].ano, Animal.idade[i], Animal.sexo[i]);
                }
                printf("\n");
            break;
            
            //Listar animais com idade acima da média
            case 3:
                for (i = 0; i < cont; i++){
                    if (Animal.idade[i] > media){
                        printf("\nNome: %s / Data de nascimento: %02d/%02d/%04d / Idade: %d / Sexo: %c", Animal.nome[i], Animal.nascimento[i].dia, Animal.nascimento[i].mes, Animal.nascimento[i].ano, Animal.idade[i], Animal.sexo[i]);
                    }
                printf("\n");
                }
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