#include <stdio.h>

// Q9

int somatorio (int i, int n) {
	if (i > n) {
		return 0;
	}
	
	return i + somatorio(i + 1, n);
}


int main () {
	
	int n = 55;
	int resultado = somatorio(0, n);
	
	printf("resultado = %d", resultado);
	
	return 0;
}