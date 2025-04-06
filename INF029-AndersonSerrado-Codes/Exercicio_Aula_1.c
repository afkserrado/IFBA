/*Faça um programa que cadastre até 10 animais com as seguintes informações: idade e sexo.
Ainda, deve ser possível: 
- Cadastrar um a um;
- Listar todos os animais;
- Listar os animais que têm a idade maior ou igual a média de todos os animais.
*/

#include <stdio.h>
#define tam 10

int main(){
    
    //Registro
    struct Animais{
        int idade[tam];
        char sexo[tam];
    };

    //Variável Animal da struct Animais
    struct Animais Animal;

    //Declarações
    int i, cont = 0, soma = 0, media = 0, opcao;

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
                printf("\nInforme a idade do animal: ");
                scanf("%d", &Animal.idade[cont]);
                printf("\nInforme o sexo do animal: ");
                scanf(" %c", &Animal.sexo[cont]);
                soma += Animal.idade[cont];
                media = soma / (cont + 1);
                cont++;
            break;

            //Listar animais
            case 2:
                for (i = 0; i < cont; i++){
                    printf("\nIdade: %d / Sexo: %c", Animal.idade[i], Animal.sexo[i]);
                }
                printf("\n");
            break;
            
            //Listar animais com idade acima da média
            case 3:
                for (i = 0; i < cont; i++){
                    if (Animal.idade[i] > media){
                        printf("\nIdade: %d / Sexo: %c", Animal.idade[i], Animal.sexo[i]);
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
}