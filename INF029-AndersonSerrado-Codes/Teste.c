#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

// Limpa a tela
void limparTela() {
    #if defined(_WIN32) || defined(_WIN64) // Windows
        system("cls");

    #elif defined(__linux__) || defined(__unix__) || defined(__APPLE__) || defined(__MACH__) // Linux e macOS
        system("clear");
        
    #endif
}

//Pausa a tela
void pausarTela() {
    #if defined(_WIN32) || defined(_WIN64) // Windows
        system("pause");

    #elif defined(__linux__) || defined(__unix__) || defined(__APPLE__) || defined(__MACH__) // Linux e macOS
        printf("Pressione qualquer tecla para continuar...");

        // Limpa o buffer de entrada antes do getchar()
        int c;
        while ((c = getchar()) != '\n' && c != EOF);  // Limpa caracteres pendentes
        getchar();  // Aguarda o Enter
        
    #endif
}

int main() {
    
    int tam;
    printf("Informe tam: ");
    scanf("%d", &tam);

    pausarTela();
    limparTela();  

}