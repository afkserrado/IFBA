/*
9) Crie uma função recursiva que receba um número inteiro positivo N e calcule o somatório dos números de 1 a N.
*/

#include <stdio.h>

int somatorio (int i, int n) {
	if (i > n) {
		return 0;
	}
	return i + somatorio(i + 1, n);
}

int main () {
	int n = 55;
	int resultado = somatorio(1, n);
	
	printf("resultado = %d\n", resultado);
	
	return 0;
}