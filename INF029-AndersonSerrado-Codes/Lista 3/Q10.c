/*
10) Escreva uma função recursiva que determine quantas vezes um dígito K ocorre em um número natural N. Por exemplo, o dígito 2 ocorre 3 vezes em 762021192.
*/

#include <stdio.h>

void contar(int n, int k, int *contk) {
	// Caso base
	if (n == 0) {
		return;
	}
	
	if (n % 10 == k) {
		(*contk)++;
	}
	
	contar(n / 10, k, contk);
}

int main () {
	int n = 111111111;
	int k = 1;
	int contk = 0;
	
	contar(n, k, &contk);
	printf("contk = %d\n", contk);
	
	return 0;
}