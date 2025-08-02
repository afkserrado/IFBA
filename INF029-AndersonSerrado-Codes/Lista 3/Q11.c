/*
11) A multiplicação de dois números inteiros pode ser feita através de somas sucessivas. Proponha um algoritmo recursivo Multip_Rec(n1,n2) que calcule a multiplicação de dois inteiros.
*/

#include <stdio.h>

int mult(int n1, int n2) {
	if (n2 == 0) {
		return 0;
	}
	
	return n1 + mult(n1, n2 - 1);	
}

int main () {
	int n1 = 5;
	int n2 = 3;
	
	int resultado = mult(n1, n2);
	
	printf("Resultado = %d\n", resultado);
	
	return 0;
}