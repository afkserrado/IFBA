/* #################################################
//  Instituto Federal da Bahia
//  Salvador - BA
//  Curso de Análise e Desenvolvimento de Sistemas http://ads.ifba.edu.br
//  Disciplina: INF029 - Laboratório de Programação
//  Professor: Renato Novais - renato@ifba.edu.br

//  ----- Orientações gerais -----
//  Descrição: esse arquivo deve conter as questões do trabalho do aluno.
//  Cada aluno deve renomear esse arquivo para Aluno<MATRICULA>.c
//  O aluno deve preencher seus dados abaixo, e implementar as questões do trabalho

//  ----- Dados do Aluno -----
//  Nome: Anderson Kleyson Serrado de Jesus
//  email: akserrado@gmail.com
//  Matrícula: 20242160026
//  Semestre: 2º

//  Copyright © 2016 Renato Novais. All rights reserved.
//  Última atualização: 07/05/2021 - 19/08/2016

// #################################################
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <locale.h> // Contém o setlocale
#include <wchar.h> // Contém funções para trabalhar com caracteres multibytes
#include "AndersonSerrado-20242160026-T1.h"

DataQuebrada quebraData(char data[]);

/*
## função utilizada para testes  ##

 somar = somar dois valores
@objetivo
    Somar dois valores x e y e retonar o resultado da soma
@entrada
    dois inteiros x e y
@saida
    resultado da soma (x + y)
 */
int somar(int x, int y)
{
    int soma;
    soma = x + y;
    return soma;
}

/*
## função utilizada para testes  ##

 fatorial = fatorial de um número
@objetivo
    calcular o fatorial de um número
@entrada
    um inteiro x
@saida
    fatorial de x -> x!
 */
int fatorial(int x)
{ //função utilizada para testes
  int i, fat = 1;
    
  for (i = x; i > 1; i--)
    fat = fat * i;
    
  return fat;
}

int teste(int a)
{
    int val;
    if (a == 2)
        val = 3;
    else
        val = 4;

    return val;
}

// Verifica se o ano é bissexto
int bissexto (int ano) {
    // Ano é bissexto
    if(((ano % 4 == 0) && (ano % 100 != 0)) || (ano % 400 == 0)) {
        return 1; 
    }
    return 0; // Não é bissexto
}

/*
 Q1 = validar data
@objetivo
    Validar uma data
@entrada
    uma string data. Formatos que devem ser aceitos: dd/mm/aaaa, onde dd = dia, mm = mês, e aaaa, igual ao ano. dd em mm podem ter apenas um digito, e aaaa podem ter apenas dois digitos.
@saida
    0 -> se data inválida
    1 -> se data válida
 @restrições
    Não utilizar funções próprias de string (ex: strtok)   
    pode utilizar strlen para pegar o tamanho da string
 */
int q1(char data[]) {

    // Declarações
    int diasMes[] = {31,28,31,30,31,30,31,31,30,31,30,31};
    
    // Chama a função quebraData
    // quebraData divide a string data em strings sDia, sMes, sAno
    DataQuebrada dataEntrada = quebraData(data); // Recebe dq, do escopo de quebraData

    // Verifica se o formato de entrada está correto
    if (dataEntrada.valido == 0) {
        return 0; // Inválido
    }

    // Verifica se o ano é bissexto
    if (bissexto(dataEntrada.iAno) == 1) {diasMes[1] = 29;} // Atualiza fevereiro
    
    // Valida o dia
    if (dataEntrada.iDia <= 0 || dataEntrada.iDia > diasMes[dataEntrada.iMes - 1]) {
        return 0; // Inválido
    }

    // Valida o mês
    if (dataEntrada.iMes <= 0 || dataEntrada.iMes >= 13) {
        return 0; // Inválido
    }

    // Valida o ano
    if (dataEntrada.iAno <= 0) {
        return 0; // Inválido 
    }

    //printf("Data válida: %s\n", data);

    return 1; // Válido
}

/*
 Q2 = diferença entre duas datas
 @objetivo
    Calcular a diferença em anos, meses e dias entre duas datas
 @entrada
    uma string datainicial, uma string datafinal. 
 @saida
    Retorna um tipo DiasMesesAnos. No atributo retorno, deve ter os possíveis valores abaixo
    1 -> cálculo de diferença realizado com sucesso
    2 -> datainicial inválida
    3 -> datafinal inválida
    4 -> datainicial > datafinal
    Caso o cálculo esteja correto, os atributos qtdDias, qtdMeses e qtdAnos devem ser preenchidos com os valores correspondentes.
 */

DiasMesesAnos q2(char datainicial[], char datafinal[]) {
    
     // Calcule os dados e armazene nas três variáveis a seguir
    DiasMesesAnos dma;

    // ### Data inicial inválida ###
    if (q1(datainicial) == 0) {
        dma.retorno = 2;
        dma.qtdAnos = 0;
        dma.qtdMeses = 0;
        dma.qtdDias = 0;
        return dma;
    }

    // ### Data final inválida ###
    else if (q1(datafinal) == 0) { // Inválida
        dma.retorno = 3;
        dma.qtdAnos = 0;
        dma.qtdMeses = 0;
        dma.qtdDias = 0;
        return dma;
    }
    
    // ### Datas válidas ###
    else {
        
        // Divide as strings datainicial e datafinal em strings sDia, sMes, sAno
        DataQuebrada dtqInicial = quebraData(datainicial);
        DataQuebrada dtqFinal = quebraData(datafinal);

        // Caso o ano tenha apenas 2 dígitos, corrige para 4
        // Neste trabalho, convecionou-se que anos com 2 dígitos são 2000+
        if (dtqInicial.iAno < 100) {dtqInicial.iAno += 2000;}
        if (dtqFinal.iAno < 100) {dtqFinal.iAno += 2000;}

        // ### Verifica se a data inicial é posterior à final ###
        // Converte a data em um único número no formato: aaaammdd
        // Este trabalho só considera datas entre 1000 e 9999
        int idataInvertida = dtqInicial.iAno * 10000 + dtqInicial.iMes * 100 + dtqInicial.iDia;
        int fdataInvertida = dtqFinal.iAno * 10000 + dtqFinal.iMes * 100 + dtqFinal.iDia;

        // Compara as datas
        if (idataInvertida > fdataInvertida) {
            dma.retorno = 4;
            dma.qtdAnos = 0;
            dma.qtdMeses = 0;
            dma.qtdDias = 0;
            return dma;
        }

        // ### Calcula o tempo passado entre as duas datas ###
        int difDia = dtqFinal.iDia - dtqInicial.iDia;
        int difMes = dtqFinal.iMes - dtqInicial.iMes;
        int difAno = dtqFinal.iAno - dtqInicial.iAno;
        int diasMes[] = {31,28,31,30,31,30,31,31,30,31,30,31};
        int anoParaBissexto;

        // Se difMes > 0 -> virou o ano
        // Se difMes < 0 -> não virou o ano
        // Se difMes = 0 -> só vira o ano se difDia >= 0
        if (difMes < 0 || (difMes == 0 && difDia < 0)) {
            difAno--;
            difMes += 12;
        }

        if (difDia < 0) {
            difMes--; // Corrige o mês

            // Guarda o mês anterior ao mês final
            int mesAnterior = dtqFinal.iMes - 1;
            
            // Se o mês final for janeiro, corrige o mês anterior para dezembro
            if (mesAnterior == 0) {
                mesAnterior = 12; // Corrige o mês anterior
            }

            if (dtqFinal.iMes >= 3) {anoParaBissexto = dtqFinal.iAno;} // Ano final
            else {anoParaBissexto = dtqFinal.iAno - 1;} // Ano anterior ao ano final   

            // Ajusta fevereiro em ano bissexto
            if (mesAnterior == 2) {
                if (bissexto(anoParaBissexto)) {
                    diasMes[1] = 29;
                } else {
                    diasMes[1] = 28;
                }
            }
            
            difDia = diasMes[mesAnterior - 1] - dtqInicial.iDia + dtqFinal.iDia;
        }

        // Anos bissextos
        // Caso 1: 28/02/2016 a 28/02/2017 (366d) ou 29/02/2016 a 01/03/2017 (366d) -> 1a0m0d (regra geral)
        // Caso 2: 29/02/2016 a 28/02/2020 -> 3a11m30d (regral geral)
        // Caso 3: 29/02/2016 a 29/02/2020 -> 4a0m0d (regral geral)
        // Caso 4: DD/MM (01 OU 02)/AAAA bissexto a DD-1/MM (01 OU 02)/AAAA não bissexto -> 1a0m0d
        if (bissexto(dtqInicial.iAno) && bissexto(dtqFinal.iAno) != 1 && difMes == 11 && difDia == 30) {
            //printf("\nEntrou");
            difAno += 1;
            difMes = 0;
            difDia = 0;
        }

        dma.qtdAnos = difAno;
        dma.qtdMeses = difMes;
        dma.qtdDias = difDia;

        // Se tudo der certo
        dma.retorno = 1;
        return dma;
    }
}

/*
 Q3 = encontrar caracter em texto
 @objetivo
    Pesquisar quantas vezes um determinado caracter ocorre em um texto
 @entrada
    uma string texto, um caracter c e um inteiro que informa se é uma pesquisa Case Sensitive ou não. Se isCaseSensitive = 1, a pesquisa deve considerar diferenças entre maiúsculos e minúsculos.
        Se isCaseSensitive != 1, a pesquisa não deve  considerar diferenças entre maiúsculos e minúsculos.
 @saida
    Um número n >= 0.
 */

int q3(char *texto, char c, int isCaseSensitive) {
     
    // Declarações
     int qtdOcorrencias = 0;
     char textoCopia[250];
     
     // Copia o texto para evitar alterar a variável original
     char caractereCopia = c;
     strcpy(textoCopia, texto);
     int len = strlen(textoCopia); // Comprimento da string
 
     // Case-insensitive: converte "caractereCopia" para minúscula
     if (isCaseSensitive == 0) {caractereCopia = tolower(caractereCopia);} 
 
     // Percorre o texto
     for (int k = 0; k < len; k++) {

         // Case-insensitive: converte "textoCopia" para minúscula
         if (isCaseSensitive == 0) {
             textoCopia[k] = tolower(textoCopia[k]);
         }
 
         // Conta as ocorrências
         if (textoCopia[k] == caractereCopia) {
             qtdOcorrencias++;
         }
     }
     
     /*
     printf("caractereCopia: %c\n", c);
     printf("textoCopia: %s\n", texto);
     printf("isCaseSensitive: %d\n", isCaseSensitive);
     */
 
     return qtdOcorrencias;
}

/*
 Q4 = encontrar palavra em texto
 @objetivo
    Pesquisar todas as ocorrências de uma palavra em um texto
 @entrada
    uma string texto base (strTexto), uma string strBusca e um vetor de inteiros (posicoes) que irá guardar as posições de início e fim de cada ocorrência da palavra (strBusca) no texto base (texto).
 @saida
    Um número n >= 0 correspondente a quantidade de ocorrências encontradas.
    O vetor posicoes deve ser preenchido com cada entrada e saída correspondente. Por exemplo, se tiver uma única ocorrência, a posição 0 do vetor deve ser preenchido com o índice de início do texto, e na posição 1, deve ser preenchido com o índice de fim da ocorrencias. Se tiver duas ocorrências, a segunda ocorrência será amazenado nas posições 2 e 3, e assim consecutivamente. Suponha a string "Instituto Federal da Bahia", e palavra de busca "dera". Como há uma ocorrência da palavra de busca no texto, deve-se armazenar no vetor, da seguinte forma:
        posicoes[0] = 13;
        posicoes[1] = 16;
        Observe que o índice da posição no texto deve começar ser contado a partir de 1.
        O retorno da função, n, nesse caso seria 1;
 */

int q4(char *strTexto, char *strBusca, int posicoes[30]) {
    
    // Garante que as funções da biblioteca <wchar.h> reconheçam corretamente não somente os caracteres ASCII, mas também caracteres Unicode
    setlocale(LC_ALL, ""); 

    // Comprimento das strings multibytes
    int slenTexto = strlen(strTexto);
    int slenBusca = strlen(strBusca);

    // Aloca memória para as strings wide char
    wchar_t *texto = (wchar_t *)malloc((slenTexto + 1) * sizeof(wchar_t));  // +1 para o '\0' 
    wchar_t *palavra = (wchar_t *)malloc((slenBusca + 1) * sizeof(wchar_t));

    if (texto == NULL || palavra == NULL) {
        // Verificação de falha de alocação
        printf("Erro ao alocar memória..\n");
        return -1;
    }

    // Converte as strings multibytes para strings wide char
    mbstowcs(texto, strTexto, slenTexto + 1); // +1 para copiar o '\0'
    mbstowcs(palavra, strBusca, slenBusca + 1);

    // Salva o endereço original do bloco de memória alocado para texto, permitindo a sua liberação posteriormente
    wchar_t *textoOriginal = texto;
    
    // Comprimentos das strings wide char
    int wlenTexto = wcslen(texto);
    int wlenPalavra = wcslen(palavra);

    // Percorre o texto
    int i = 0;
    int ocorrencias = 0, index = 0;
    wchar_t *resultado;
    do {
        // Aponta para o ínicio da palavra no texto ou retorna NULL
        resultado = wcsstr(texto, palavra);
        
        if (resultado != NULL) {
            ocorrencias++;

            // Operação aritmética de endereços
            index = resultado - textoOriginal; // Retorna um índice baseado em 0

            posicoes[i++] = index + 1; // Início da substring (baseado em 1)
            posicoes[i++] = index + wlenPalavra; // Final da substring (baseado em 1)
            
            // Move o ponteiro texto para o novo índice
            texto = resultado + 1; 
            wlenTexto = wcslen(texto);   

            // Obs.:
            // posicoes[i++] usa "i" e depois incrementa;
            // index + 1 porque desejamos o índice baseado em 1;
            // index + wlenPalavra porque desejamos o índice baseado em 1. Se quiséssemos o índice baseado em 0, teríamos que subtrair 1.
            // texto = resultado + 1 permite encontrar ocorrências sobrepostas. Por exemplo, "ara" ocorre 2x em "arara"
        }

    } while (resultado != NULL && wlenTexto >= wlenPalavra);
    
    // Libera a memória alocada
    free(textoOriginal);
    free(palavra);

    return ocorrencias;
}

/*
 Q5 = inverte número
 @objetivo
    Inverter número inteiro
 @entrada
    uma int num.
 @saida
    Número invertido
 */

// Guarda os algarismos de um número inteiro em um vetor
int separaNumero(int num, int vetor[]) {
    int i;
    for (i = 0; num != 0; i++) {
        vetor[i] = num % 10;
        num = num / 10;
    }
    return i;
}

int q5(int num) {

    #define tam 10 // Qtd. máxima de dígitos de um int
    int vetor[tam] = {0};
    int flag = 1;
    
    // Converte para inteiro positivo
    if (num < 0) { 
        num *= -1;
        flag = 0;
    }

    int i = separaNumero(num, vetor);

    // Junta os algarismos e transforma em um número
    // Solução 1
    num = 0; // Reset
    for (int j = 0; j < i; j++) {
        num = num * 10 + vetor[j];
    }
    
    /* Solução 2
    int k = 0;
    for (int j = i - 1; j >= 0; j--) {
        num += vetor[k] * pow(10, j);
        k++;
    }*/
    
    if (flag == 0) {num = num * -1;}

    return num;
}

/*
 Q6 = ocorrência de um número em outro
 @objetivo
    Verificar quantidade de vezes da ocorrência de um número em outro
 @entrada
    Um número base (numerobase) e um número de busca (numerobusca).
 @saida
    Quantidade de vezes que número de busca ocorre em número base
 */

// Inverte os algarismos separados de um número inteiro em um vetor
void inverteNumero (int iVetor[], int i) {
    int temp;
    for (int j = 0; j < i/2; j++) {
        temp = iVetor[j];
        iVetor[j] = iVetor[i - j - 1];
        iVetor[i - j - 1] = temp;
    }
    return;
}

// Converte os algarismos inteiros de um vetor em caracteres
void itoaArray (char cVetor[], int iVetor[], int i) {
    int j;
    for (j = 0; j < i; j++) {
        cVetor[j] = iVetor[j] + '0';
    }
    cVetor[j] = '\0';
    return;
}

// Conta as ocorrências do número
int contaOcorrencias(char *numerobase, char *numerobusca) {
    
    // Comprimentos
    int baLen = strlen(numerobase);
    int buLen = strlen(numerobusca);

    // Percorre o numerobase
    int ocorrencias = 0;
    char *resultado;
    do {
        // Aponta para o ínicio da numeroBusca no tnumerobase ou retorna NULL
        resultado = strstr(numerobase, numerobusca);
        
        if (resultado != NULL) {
            ocorrencias++;
            // Move o ponteiro texto para o novo índice
            numerobase = resultado + 1;
            baLen = strlen(numerobase);
        }

    } while (resultado != NULL && baLen >= buLen);

    return ocorrencias;
}

int q6(int numerobase, int numerobusca) {
    
    #define tam 10
    int qtdOcorrencias = 0;
    int iVetor_base[tam] = {0};
    int iVetor_busca[tam] = {0};
    char cVetor_base[tam];
    char cVetor_busca[tam];

    // Separa o número e guarda em um vetor
    int tamBase = separaNumero(numerobase, iVetor_base);
    int tamBusca = separaNumero(numerobusca, iVetor_busca);

    // Coloca na ordem original os algarimos do número separado
    inverteNumero(iVetor_base, tamBase);
    inverteNumero(iVetor_busca, tamBusca);

    // Converte de int para char
    itoaArray(cVetor_base, iVetor_base, tamBase);
    itoaArray(cVetor_busca, iVetor_busca, tamBusca);
    
    // Cria ponteiros para "andar" dentro do vetor
    char *pnumerobase = cVetor_base;
    char *pnumerobusca = cVetor_busca;

    qtdOcorrencias = contaOcorrencias(pnumerobase, pnumerobusca);

    return qtdOcorrencias;
}

/*
 Q7 = jogo busca palavras
 @objetivo
    Verificar se existe uma string em uma matriz de caracteres em todas as direções e sentidos possíves
 @entrada
    Uma matriz de caracteres e uma string de busca (palavra).
 @saida
    1 se achou 0 se não achou
 */

// Inverte a palavra
void invertePalavra(char palavra[6], char palavraInvertida[6], int len) {
    for (int i = 0; i < len; i++) {
        palavraInvertida[i] = palavra[len - i - 1];
    }
    palavraInvertida[len] = '\0';
}

// Direção horizontal ↔️
void proch(int len, char palavra[len + 1], char matriz[8][10], int *achou) {
    
    for (int i = 0; i < 8; i++) { // Percorre as linhas da matriz
        int k = 0; // Percorre a palavra
        int restoColunas = 10; // Qtd de colunas restantes da matriz
        
        for (int j = 0; j < 10; j++) { // Percorre as colunas da matriz      
            restoColunas = 10 - j;
            if (k == 0 && len > restoColunas) {break;} // Sai do loop j
            
            if (matriz[i][j] == palavra[k]) { // Achou o caractere
                k++;
                if (k == len) { // Achou a palavra
                    *achou = 1;
                    return; // Interrompe a função
                }
            }
            else if (k > 0) { // Achou a palavra parcialmente
                k = 0; // Reinicialização
                j--; // Decrementa o j para continuar a nova busca do mesmo ponto
            }
        } // Fim do loop j
    } // Fim do loop i
}

// Direção vertical ↕️
void procv(int len, char palavra[len + 1], char matriz[8][10], int *achou) {
    
    for (int i = 0; i < 10; i++) { // Percorre as colunas da matriz
        int k = 0; // Percorre a palavra
        int restoLinhas = 8; // Qtd de linhas restantes da matriz
        
        for (int j = 0; j < 8; j++) { // Percorre as linhas da matriz      
            restoLinhas = 8 - j;
            if (k == 0 && len > restoLinhas) {break;} // Sai do loop j
            
            if (matriz[j][i] == palavra[k]) { // Achou o caractere
                k++;
                if (k == len) { // Achou a palavra
                    *achou = 1;
                    return; // Interrompe a função
                }
            }
            else if (k > 0) { // Achou a palavra parcialmente
                k = 0; // Reinicialização
                j--; // Decrementa o j para continuar a nova busca do mesmo ponto
            }
        } // Fim do loop j
    } // Fim do loop i
}

// Direção inclinação principal ↘️↖️
void procip(int len, char palavra[len + 1], char matriz[8][10], int *achou) {
    
    for (int i = 0; i < 8; i++) { // Percorre as linhas da matriz
        int restoLinhas = 8 - i; // Qtd de linhas restantes da matriz
        int restoColunas = 10; // Qtd de colunas restantes da matriz
        
        for (int j = 0; j < 10; j++) { // Percorre as colunas da matriz      
            restoColunas = 10 - j;
            
            if (restoColunas < len) {break;} // Sai do loop j
            if (restoLinhas < len) {return;} // Interrompe o programa, pois não há mais como achar a palavra

            int k = 0; // Percorre a palavra
            int l = i;
            int c = j;
            // Percorre a diagonal de inclinação principal
            while (l <= i + len - 1 && c <= j + len - 1) { 
                if (matriz[l][c] == palavra[k]) {
                    k++;
                    if (k == len) { // Achou a palavra
                        *achou = 1;
                        return; // Interrompe a função
                    }
                }
                else {break;} // Sai do while
                // Incrementa
                l++;
                c++;
            }
        } // Fim do loop j
    } // Fim do loop i
}

// Direção inclinação secundária ↙️↗️
void procis (int len, char palavra[len + 1], char matriz[8][10], int *achou) {
    
    for (int i = 0; i < 8; i++) { // Percorre as linhas da matriz
        int restoLinhas = 8 - i; // Qtd de linhas restantes da matriz
        int restoColunas = 10; // Qtd de colunas restantes da matriz
        
        for (int j = 9; j >= 0; j--) { // Percorre as colunas da matriz      
            restoColunas--;
            
            if (restoColunas < len) {break;} // Sai do loop j
            if (restoLinhas < len) {return;} // Interrompe o programa, pois não há mais como achar a palavra

            int k = 0; // Percorre a palavra
            int l = i;
            int c = j;
            // Percorre a diagonal de inclinação secundária
            while (l <= i + len - 1 && c >= j - len + 1) { 
                if (matriz[l][c] == palavra[k]) {
                    k++;
                    if (k == len) { // Achou a palavra
                        *achou = 1;
                        return; // Interrompe a função
                    }
                }
                else {break;} // Sai do while
                // Incrementa
                l++;
                c--;
            }
        } // Fim do loop j
    } // Fim do loop i
}

int q7(char matriz[8][10], char palavra[6]) {

    int achou = 0;
    int len = strlen(palavra); // Comprimento da palavra buscada

    // Inverte a palavra buscada
    char palavraInvertida[6];
    invertePalavra(palavra, palavraInvertida, len);

    // Procura a palavra na horizontal ↔️
    proch(len, palavra, matriz, &achou);

    // Procura a palavra na vertical ↕️
    procv(len, palavra, matriz, &achou);

    // Procura a palavra na direção paralela à diagonal principal ↘️↖️
    procip(len, palavra, matriz, &achou);

    // Procura a palavra na direção paralela à diagonal secundária ↙️↗️
    procis(len, palavra, matriz, &achou);

    // Procura a "palavraInvertida" somente se "palavra" não for palíndromo
    if (strcmp(palavra, palavraInvertida) != 0) {
        proch(len, palavraInvertida, matriz, &achou); // ↔️
        procv(len, palavraInvertida, matriz, &achou); // ↕️
        procip(len, palavraInvertida, matriz, &achou); // ↘️↖️
        procis(len, palavraInvertida, matriz, &achou); // ↙️↗️
    }

    // Não achou
    return achou;
}

DataQuebrada quebraData(char data[]) {
    
    DataQuebrada dq;
    char sDia[3]; //+1 para o \0
	char sMes[3];
	char sAno[5];
	int i; 

    // Guarda o dia
	for (i = 0; data[i] != '/'; i++) {
		if (!(isdigit(data[i]) || data[i] == '/')) { 
            dq.valido = 0;
            return dq;
        }
        sDia[i] = data[i];	
	}
	if (i == 1 || i == 2) { // testa se tem 1 ou dois digitos
		sDia[i] = '\0';  // coloca o barra zero no final
	}
    else {
		dq.valido = 0;
        return dq;
    }  
	
	int j = i + 1; //anda 1 cada para pular a barra
	i = 0;

    // Guarda o mês
	for (; data[j] != '/'; j++) {
		if (!(isdigit(data[j]) || data[j] == '/')) { 
            dq.valido = 0;
            return dq;
        }
        sMes[i] = data[j];
		i++;
	}
	if (i == 1 || i == 2) { // testa se tem 1 ou dois digitos
		sMes[i] = '\0';  // coloca o barra zero no final
	}
    else {
		dq.valido = 0;
        return dq;
    }
	
	j = j + 1; //anda 1 cada para pular a barra
	i = 0;
	
    // Guarda o ano
	for (; data[j] != '\0'; j++){
        if (!(isdigit(data[j]) || data[j] == '/')) { 
            dq.valido = 0;
            return dq;
        }
        sAno[i] = data[j];
	 	i++;
	}
	if (i == 2 || i == 4) { // testa se tem 2 ou 4 digitos
		sAno[i] = '\0';  // coloca o barra zero no final
	}
    else {
		dq.valido = 0;
        return dq;
    }

    // Se a data for válida, guarda em dq
    dq.iDia = atoi(sDia);
    dq.iMes = atoi(sMes);
    dq.iAno = atoi(sAno); 
	dq.valido = 1;
    
  return dq;
}