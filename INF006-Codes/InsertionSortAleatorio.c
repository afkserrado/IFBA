/*Algoritmo Insertion Sort*/

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

//Subfunção
//Gerando um vetor de valores aleatórios e tamanho dinâmico
int *gerando_vetor_aleatorio (int tam){
    
    //Reservando espaços na memória para o vetorAleatorio
    int *vetorAleatorio = malloc(tam * sizeof(int)); 
    
    //Preenchendo o vetor
    for (int i = 0; i < tam; i++){
        vetorAleatorio[i] = rand()%500;
    }

    /*
    'rand' é uma função que gera valores aleatórios.
    Ao fazer rand()%500, geramos um valor aleatório que esteja entre 0 e 499.
    */

    return vetorAleatorio;

    /*Retorna para a 'main', função que a chamou, o endereço de vetorAleatorio. Ou seja, a variável de 'main' que chamou a função 'gerando_vetor_aleatorio' vai apontar para o endereço de 'vetorAleatorio'.*/
}

//Subfunção
//Insertion sort: algoritmo de ordenação de elementos
void insertionSort (int *A, int len){

    int i, j, temp;
    for (j = 1; j < len; j++){
        temp = A[j];
        i = j - 1;
        
        while (i >= 0 && A[i] > temp){
            A[i + 1] = A[i];
            i--;
        }
        A[i + 1] = temp;
    }
}

//Subfunção
//Exibindo o vetor
void printVetor (int *A, int len){
    int i, primeiro = 0;
    printf("[");

    for (i = 0; i < len; i++){
        if (primeiro == 0){
            printf("%d", A[i]);
            primeiro = 1;
        }
        else
            printf(" %d", A[i]);
    }
    printf("]");
}

//Função principal
int main () {

    //Inicializando rand
    srand(time(NULL));

    /*
    A função 'rand' não gera valores verdadeiramente aleatórios. Na verdade, ela gera valores que correspondem a um valor que a ela foi passado. Se usarmos 'rand' sem 'srand', normalmente 'rand' trabalhará com a sequência de número 1, ou seja rand(1). Então, o programa sempre gerará os mesmos n valores aleatórios. 

    'srand' inicializa o valor de 'rand'.
    'time' conta o tempo em segundos transcorrido entre 1º de janeiro de 1970 até uma data limite. Quando usamos 'time(NULL)', essa data limite é o horário de execução do programa.

    Assim, 'srand(time(NULL))' sempre vai gerar uma sequência aleatório diferente, pois os tempo só aumenta.
    */

    int tamanho;
    printf("Informe o tamanho do array:");
    scanf("%d",&tamanho);

    //Faz a variável 'vetor' apontar para o endereço da variável 'vetorAleatorio' da função 'gerando_vetor_aleatorio'
    int *vetor = gerando_vetor_aleatorio(tamanho);

    //Exibindo o vetor desordenado
    printf("\nVetor inicial: ");
    printVetor (vetor, tamanho);
    //Ordenando o vetor
    insertionSort (vetor, tamanho);
    printf("\nVetor ordenado: ");
    //Exibindo o vetor ordenado
    printVetor (vetor, tamanho);

    //Desaloca a memória
    free(vetor);
}