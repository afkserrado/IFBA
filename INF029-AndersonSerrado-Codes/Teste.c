#include <stdio.h>
#include <stdlib.h>
#include <string.h>


// Regras gerais para trabalhar com strings

#define tam_max 10

// Limpa o buffer
void limparBuffer() {
    int c;
    while ((c = getchar()) != '\n' && c != EOF) {
        // Loop intencionalmente vazio para consumir caracteres
    }
}

int main () {
	
	/* REGRAS:
     1. O vetor deve ter tamanho n+1 para armazenar n caracteres + '\0'
     2. fgets() sempre insere '\0' após os caracteres lidos
     3. fgets() inclui o '\n' se houver espaço no buffer
     4. Se a entrada for maior que o buffer, o '\n' pode ficar fora
     5. O fgets remove o '\n', mas é importante removê-lo manualmente para consistência
     6. Se houver a possibilidade do usuário inserir mais digítos do que o necessário, importante considerar a limpeza de buffer, evitando que os caracteres excedentes sejam consumidos em leituras futuras
   */
  
	char string[tam_max + 1]; //n caracteres + '\0'
	
	printf("Digite um texto: ");
	
	// Leitura
	if (fgets(string, tam_max + 1, stdin) == NULL) {
        return 1; // Trata erro de leitura
    }
    
    // Verifica truncamento e remove \n
    if (strchr(string, '\n') == NULL) {
        limparBuffer(); // Limpa o excesso de caracteres
        printf("Erro: excesso de caracteres (máx. %d caracteres)\n", tam_max);
        return 1; // Input truncado = inválido
    } else {
        // Substitui a quebra de linha \n pelo terminador nulo \0
        string[strcspn(string, "\n")] = '\0';
    }
    
    printf("Texto: %s\n", string);
}