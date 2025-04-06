/*Algoritmo Insertion Sort*/

#include <stdio.h>
#define tam 10

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

    //Declarações
    int vetor[tam], i;

    //Entrada de dados
    for (i = 0; i < tam; i++){
        printf("\nInforme o %do elemento: ", i+1);
        scanf("%d", &vetor[i]);
    }

    //Exibindo o vetor desordenado
    printf("\nVetor inicial: ");
    printVetor (vetor, tam);
    //Ordenando o vetor
    insertionSort (vetor, tam);
    printf("\nVetor ordenado: ");
    //Exibindo o vetor ordenado
    printVetor (vetor, tam);
}