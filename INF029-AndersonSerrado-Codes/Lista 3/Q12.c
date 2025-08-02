/*
12. Faça uma função recursiva que receba um número inteiro positivo N e imprima todos os números natruais de 0 até N em ordem crescente.
*/

#include <stdio.h>
#include <stdlib.h>

void impCre (int N) {
	if (N < 0) {
		return;
	}
	
	if (N > 0) {impCre(N - 1);}
	printf("N = %d\n", N);
}

int main () {
	int N;
	printf("Informe N: ");
	scanf("%d", &N);
	impCre(N);
}