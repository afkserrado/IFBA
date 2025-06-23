/*
Instituto Federal da Bahia (IFBA)
Tecnólogo em Análise e Desenvolvimento de Sistemas (ADS)
Semestre 2025.1
INF006 - Estrutura de Dados e Algoritmos
Professor: José Dihego
Aluno: Anderson Serrado
T1Q2
*/

// ##################################################### //
// BIBLIOTECAS

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <math.h>

// ##################################################### //
// CONSTANTES

// Tamanho máximo da linha
#define tamEntrada 501 // +1 do \0

// Tamanho dos vetores que guardarão os tokens
// +1 por precaução
#define tamTexto 251 // Pior caso: linha com 250 letras e 249 espaços
#define tamInt 251 // Pior caso: linha com 250 números de 1 dígito e 249 espaços
#define tamFloat 126 // Pior caso: 0.0, i.e., 125 números com 1 casa decimal e 124 espaços
#define tamPonto 84 // Pior caso: (0,0), i.e., 83 coordenadas e 82 espaços 

// ##################################################### //
// STRCUTS

typedef struct {
    char txt[tamEntrada]; // Pior caso: uma linha com 1 texto de 500 letras
} texto;

typedef struct {
    float x;
    float y;
    float distancia;
    int casasDecX;
    int casasDecY;
} ponto;

typedef struct {
    float num;
    int casasDecimais;    
} decimal;

// ##################################################### //
// FUNÇÕES

// Elimina parênteses da linha
void limparLinha(char linha[tamEntrada]) {
    int i, j = 0;  
    int len = strlen(linha); // Calcula o comprimento da linha
    for (i = 0; i < len; i++) {
        if (linha[i] != '(' && linha[i] != ')') {
            // Shift
            linha[j] = linha[i];
            j++;
        }
    }
    linha[j] = '\0'; // Insere o terminador nulo
}

// Calcula a distância entre dois pontos
float calculaDistancia (const float x1, const float y1, const float x2, const float y2) {
    float distancia = sqrt(pow(x2 - x1, 2) + pow(y2 - y1, 2));
    return distancia;
}

// Conta quantas casas decimais tem o número
int contaCasasDecimais (char string[]) {
    
    // Aponta para o separador decimal
    char *separador = strchr(string, '.');
    
    int casasDecimais = 0;

    // Conta as casas decimais
    if (separador != NULL) {
        casasDecimais = strlen(separador + 1);
    }
    return casasDecimais;
}

//Insertion sort: strings
void insertionSortTexto (texto A[], int cont){
    int i, j;
    texto temp;
    for (j = 1; j < cont; j++){
        temp = A[j];
        i = j - 1;
        
        while (i >= 0 && strcmp(A[i].txt, temp.txt) > 0){
            A[i + 1] = A[i];
            i--;
        }
        A[i + 1] = temp;
    }
}

//Insertion sort: inteiros
void insertionSortInt (int A[], int cont){
    int i, j;
    int temp;
    for (j = 1; j < cont; j++){
        temp = A[j];
        i = j - 1;
        
        while (i >= 0 && A[i] > temp){
            A[i + 1] = A[i];
            i--;
        }
        A[i + 1] = temp;
    }
}

//Insertion sort: decimais
void insertionSortDec (decimal A[], int cont){
    int i, j;
    decimal temp;
    for (j = 1; j < cont; j++){
        temp = A[j];
        i = j - 1;
        
        while (i >= 0 && A[i].num > temp.num){
            A[i + 1] = A[i];
            i--;
        }
        A[i + 1] = temp;
    }
}

//Insertion sort: pontos
void insertionSortPonto (ponto A[], int cont){
    int i, j;
    ponto temp;
    for (j = 1; j < cont; j++){
        temp = A[j];
        i = j - 1;
        
        while (i >= 0 && A[i].distancia > temp.distancia){
            A[i + 1] = A[i];
            i--;
        }
        A[i + 1] = temp;
    }
}

int main () {

    // Declarações: vetores para armazenar os tokens da entrada
    texto textos[tamTexto];
    int inteiros[tamInt];
    decimal decimais[tamFloat];
    ponto pontos[tamPonto];

    // Abre o arquivo e retorna um endereço de memória
    // Ponteiros para o tipo FILE
    FILE *arqEntrada = fopen("L0Q2.in", "r");
    FILE *arqSaida = fopen("L0Q2.out", "w");

    // Tratamento de erros
    // Se o arquivo não puder ser aberto, fopen retorna NULL
    if (arqEntrada == NULL || arqSaida == NULL) {
        printf("Os arquivos não podem ser abertos. Verifique se os arquivos e o executável estão na mesma pasta.\n");
        return EXIT_FAILURE;
    }

    // Declarações
    char linha[tamEntrada];
    char *token;
    char delimitador[] = " ";
    int flag = 0;

    // Lê o arquivo de entrada até o fim, quando fgets retorna NULL
    // Percorre o arquivo
    while (fgets(linha, tamEntrada, arqEntrada) != NULL) { 

        // Pula a linha após o primeiro loop e evita pular após o último
        if (flag == 1) {
            fprintf(arqSaida, "\n"); // Próxima linha
        }

        // Remove o caractere de nova linha (caso exista)
        linha[strcspn(linha, "\n")] = '\0';  // Remove o '\n' se presente

        limparLinha(linha); // Elimina parênteses

        // Contadores: resets
        int contTexto = 0;
        int contInt = 0;
        int contDec = 0;
        int contPonto = 0;        
        int erro = 0;
  
        // Extrai o primeiro token
        token = strtok(linha, delimitador); // strtok já insere o \0
        
        // Lê a linha até o fim, quando strtok retorna NULL, e separa a string
        // Percorre uma linha
        while (token != NULL) {
            // String
            if (isalpha(token[0])) { 
                // Tratamento de erros
                if (contTexto >= tamTexto) {
                    erro = 1;
                    break;
                }
                strcpy(textos[contTexto].txt, token);              
                contTexto++; // Atualiza o contador
            }

            // Float
            else if (strchr(token, '.') != NULL && 
                    strchr(token, '(') == NULL && 
                    strchr(token, ',') == NULL && 
                    strchr(token, ')') == NULL) 
            { 
                if (contDec >= tamFloat) {
                    erro = 1;
                    break;
                }
                decimais[contDec].num = atof(token);
                decimais[contDec].casasDecimais = contaCasasDecimais(token);
                contDec++; // Atualiza o contador
            }

            // Inteiro
            else if (strchr(token, '.') == NULL && strchr(token, ',') == NULL && (isdigit(token[0]) || (token[0] == '-' && isdigit(token[1])))) { 
                // Tratamento de erros
                if (contInt >= tamInt) {
                    erro = 1;
                    break;
                }
                inteiros[contInt] = atoi(token);
                contInt++; // Atualiza o contador
            }
            
            // O problema é o último ponto. Ele está pegando 1 casa a mais
            // Ponto
            else if (strchr(token, ',') != NULL) { 
                // Tratamento de erros
                if (contPonto >= tamPonto) {
                    erro = 1;
                    break;
                }

                // Encontra a posição da vírgula
                char *virgula = strchr(token, ',');

                // Verifica se a vírgula foi encontrada corretamente
                if (virgula == NULL) {
                    erro = 1;
                    break;
                }

                // Separa as coordenadas x e y
                *virgula = '\0';
                
                char *x = token;
                char *y = virgula + 1;

                // Converte as coordenadas para float e conta as casas decimais
                pontos[contPonto].x = atof(x);
                pontos[contPonto].casasDecX = contaCasasDecimais(x);  
                
                pontos[contPonto].y = atof(y);
                pontos[contPonto].casasDecY = contaCasasDecimais(y);
                
                // Calcula a distância do ponto até a origem (0,0)
                pontos[contPonto].distancia = calculaDistancia(0, 0, pontos[contPonto].x, pontos[contPonto].y);

                contPonto++;  // Atualiza o contador de pontos
            }

            // Pega o próximo token
            token = strtok(NULL, delimitador);

        } // Fim do 2º while (linha)

        if (erro) continue; // Linha não lida. Passa para a próxima

        // Escreve os resultados no arquivo de saída
        // Strings
        fprintf(arqSaida, "str:");
        if (contTexto > 0) {
            insertionSortTexto(textos, contTexto);
            for (int i = 0; i < contTexto; i++) {           
                // Remove quebras de linha
                textos[i].txt[strcspn(textos[i].txt, "\n")] = '\0';
                fprintf(arqSaida, "%s", textos[i].txt);
                if (i < contTexto - 1) fprintf(arqSaida, " ");
            }
        }
        fprintf(arqSaida, " ");

        // Inteiros
        fprintf(arqSaida, "int:");
        if (contInt > 0) {
            insertionSortInt(inteiros, contInt);
            for (int i = 0; i < contInt; i++) {
                fprintf(arqSaida, "%d", inteiros[i]);
                if (i < contInt - 1) fprintf(arqSaida, " ");
            }
        }
        fprintf(arqSaida, " ");

        // Decimais
        fprintf(arqSaida, "float:");
        if (contDec > 0) {
            insertionSortDec(decimais, contDec);
            for (int i = 0; i < contDec; i++) {
                fprintf(arqSaida, "%.*f", decimais[i].casasDecimais, decimais[i].num);
                if (i < contDec - 1) fprintf(arqSaida, " ");
            }         
        }
        fprintf(arqSaida, " ");
        
        // Pontos
        fprintf(arqSaida, "p:");
        if (contPonto > 0) {
            insertionSortPonto(pontos, contPonto);
            for (int i = 0; i < contPonto; i++) {
                fprintf(arqSaida, "(%.*f,%.*f)", pontos[i].casasDecX, pontos[i].x, pontos[i].casasDecY, pontos[i].y);
                if (i < contPonto - 1) fprintf(arqSaida, " ");
            }
        }
        
        flag = 1;

    } // Fim do 1º while (arquivo)

    fclose(arqEntrada); // Fecha o arquivo e libera a memória
    fclose(arqSaida); // Fecha o arquivo e libera a memória
    return EXIT_SUCCESS;
} // Fim da main