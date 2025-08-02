#include <stdio.h>

// Q10

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
	
	printf("Resultado = %d", resultado);
	
	return 0;
}