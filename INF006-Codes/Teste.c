#include <stdio.h>
#include <string.h>
#include <stdlib.h>

// Definindo uma estrutura para armazenar as coordenadas
typedef struct {
    int x;
    int y;
} Coordenada;

int main(void) {
    char entrada[] = "11,32 43,43 20,56 26,29 34,-5 17,25 25,20 4,34 6,-2 56,-10 3,-17 37,2 7,-14";
    char *token;
    Coordenada coordenadas[50];  // Array para armazenar até 50 coordenadas
    int i = 0;
    
    // Utiliza vírgula e espaço como delimitadores
    token = strtok(entrada, ", ");
    
    while (token != NULL) {
        // Armazena o valor de x (primeiro número)
        coordenadas[i].x = atoi(token);
        
        // Obtém o próximo token (o valor de y)
        token = strtok(NULL, ", ");
        coordenadas[i].y = atoi(token);
        
        // Passa para a próxima coordenada
        i++;
        
        // Continua buscando próximos pares
        token = strtok(NULL, ", ");
    }
    
    // Imprime as coordenadas armazenadas
    for (int j = 0; j < i; j++) {
        printf("Coordenada %d: (%d, %d)\n", j + 1, coordenadas[j].x, coordenadas[j].y);
    }
    
    return 0;
}
