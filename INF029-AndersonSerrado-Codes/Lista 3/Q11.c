#include <stdio.h>

// Q11

void contar(int n, int k, int *contk) {
	// Caso base
	if (n == 0) {
		return;
	}
	
	//printf("n % 10 = %d\n", temp);
	
	if (n % 10 == k) {
		(*contk)++;
	}
	
	//printf("n / 10 = %d\n", n / 10);
	
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