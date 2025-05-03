#include <stdio.h>
#include <string.h>
#include <stdlib.h>

// Definindo uma estrutura para armazenar as coordenadas
typedef struct {
    int x;
    int y;
} Coordenada;

int main(void) {
    char entrada[] = "(-2,-1) (4,2) (4,0) (2,2) (8,6) (34,-5)";
    char *token;
    Coordenada coordenadas[50];  // Array para armazenar até 50 coordenadas
    int i = 0;
    char delimitador[] = "(,) ";
    
    // Utiliza vírgula e espaço como delimitadores
    token = strtok(entrada, delimitador);
    
    while (token != NULL) {
        // Armazena o valor de x (primeiro número)
        coordenadas[i].x = atoi(token);
        
        // Obtém o próximo token (o valor de y)
        token = strtok(NULL, delimitador);
        coordenadas[i].y = atoi(token);
        
        // Passa para a próxima coordenada
        i++;
        
        // Continua buscando próximos pares
        token = strtok(NULL, delimitador);
    }
    
    // Imprime as coordenadas armazenadas
    for (int j = 0; j < i; j++) {
        printf("Coordenada %d: (%d, %d)\n", j + 1, coordenadas[j].x, coordenadas[j].y);
    }
    
    return 0;
}
