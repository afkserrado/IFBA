#include <stdio.h>
#include <stdlib.h>

/*
Lista de exercícios - Recursão
12. Faça uma função recursiva que receba um número inteiro positivo N e imprima todos os números natruais de 0 até N em ordem crescente.
*/

void impDec (int N) {
	if (N < 0) {
		return;
	}
	if (N > 0) {impDec(N - 1);}
	printf("N = %d\n", N);
}

int main () {
	int N;
	printf("Informe N: ");
	scanf("%d", &N);
	impDec(N);
}