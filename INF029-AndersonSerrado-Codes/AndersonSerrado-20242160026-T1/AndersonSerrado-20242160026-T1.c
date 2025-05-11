// #################################################
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
// Última atualização: 07/05/2021 - 19/08/2016

// #################################################

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
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

// Calcula a quantidade de dias passados até uma data
int diasPassados (DataQuebrada data) {

    int diasMes[] = {31,28,31,30,31,30,31,31,30,31,30,31};
    if (bissexto(data.iAno) == 1) {diasMes[1] = 29;} // Atualiza fevereiro
    int qtdDias = 0;

    for (int i = 0; i < data.iMes; i++) {
        // Soma os dias do mês inicial
        if (i == data.iMes - 1) {
            qtdDias += data.iDia - 1; // Não conta o 1º dia
            break;
        }
        // Soma os dias dos meses anteriores ao mês inicial
        qtdDias += diasMes[i]; 
    }
    return qtdDias;
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
    DataQuebrada dataEntrada = quebraData(data); // recebe dq, do escopo de quebraData

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

 // Refazer utilizando o método de unidades completas ou método de aniversário
DiasMesesAnos q2(char datainicial[], char datafinal[]) {
    //calcule os dados e armazene nas três variáveis a seguir
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

        // ### Verifica se a data inicial é posterior à final ###
        // Ano inicial maior
        if (dtqInicial.iAno > dtqFinal.iAno) {
            dma.retorno = 4;
            dma.qtdAnos = 0;
            dma.qtdMeses = 0;
            dma.qtdDias = 0;
            return dma;
        }
        // Anos iguais, mês inicial maior
        else if (dtqInicial.iAno == dtqFinal.iAno && dtqInicial.iMes > dtqFinal.iMes) {
            dma.retorno = 4;
            dma.qtdAnos = 0;
            dma.qtdMeses = 0;
            dma.qtdDias = 0;
            return dma;
        }
        // Anos iguais, meses iguais, dia inicial maior
        else if (dtqInicial.iAno == dtqFinal.iAno && dtqInicial.iMes == dtqFinal.iMes && dtqInicial.iDia > dtqFinal.iDia) {
            dma.retorno = 4;
            dma.qtdAnos = 0;
            dma.qtdMeses = 0;
            dma.qtdDias = 0;
            return dma;
        }

        // ### Calcula a distância entre as datas ###

        /* O método consiste em considerar como data referencial o 1º dia do ano inicial, isto é, 01/01/dtqInicial.iAno.
        
        Após, calcula-se a quantidade de dias passados entre a data inicial e o ano referencial e entre a data final e o ano referencial.

        Por fim, calcula-se a diferença entre esses resultados para encontrar a quantidade de anos, meses e dias passados entre as datas final e inicial.
        */ 

        // 1. Dias desde o início do ano inicial até a data inicial
        int iqtdDias = diasPassados(dtqInicial);

        // 2. Dias desde o início do ano final até a data final
        int fqtdDias = diasPassados(dtqFinal);

        // 3. Anos completos, em dias, desde o início do ano inicial até a data final
        int diasAnosCompletos = 0;
        if (dtqFinal.iAno > dtqInicial.iAno) {
            for (int ano = dtqInicial.iAno; ano < dtqFinal.iAno; ano++) {
                if (bissexto(ano) == 1) {diasAnosCompletos += 366;}
                else {diasAnosCompletos += 365;}
            }
        }

        // 4. Calcula os dias entre a data inicial e a data final
        int diasTotais = (fqtdDias + diasAnosCompletos) - iqtdDias;

        // 5. Calcula a quantidade de anos (não importa se o ano é bissexto)
        dma.qtdAnos = diasTotais / 365;
        int diasResto = diasTotais % 365;

        // Correção do diasResto para anos bissextos
        if (dtqFinal.iAno > dtqInicial.iAno && dma.qtdAnos >= 1) {
            for (int ano = dtqInicial.iAno; ano < dtqFinal.iAno; ano++) {
                if (bissexto(ano) == 1 && diasResto > 0) {diasResto--;}
            }
        }

        // 6. Calcula a quantidade de meses
        int diasMes[] = {31,28,31,30,31,30,31,31,30,31,30,31};
        if (bissexto(dtqFinal.iAno) == 1) {diasMes[1] = 29;} // Atualiza fevereiro
        dma.qtdMeses = 0; // Inicialização

        for (int mes = dtqInicial.iMes - 1;; mes++) {
            if (diasResto > diasMes[mes]) {
                dma.qtdMeses++;
                diasResto -= diasMes[mes];
            }
            else {
                dma.qtdDias = diasResto;
                break;
            }

            if (mes == 11) {mes = -1;} // Reseta após chegar em dezembro
        }

        //se tudo der certo
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

void converteVogais (char *caractere, const char vogais[5][5]) {

    int achou = 0;
    for (int i = 0; i < 5; i++) { // Percorre as linhas
        for (int j = 1; j < 5; j++) { // Percorre as colunas
            if (*caractere == vogais[i][j]) {
                *caractere = vogais[i][0];
                achou = 1;
                break;
            }
        }
        if (achou) {break;} // achou = 1: sai da matriz
    }

    return;
}

int q3(char *texto, char c, int isCaseSensitive) {
     
    // Declarações
     int qtdOcorrencias = 0;
     char textoCopia[250];
     char vogais[5][5] = {
         {'a', 'á', 'à', 'ã', 'â'},
         {'e', 'é', 'è', 'ẽ', 'ê'},
         {'i', 'í', 'ì', 'ĩ', 'î'},
         {'o', 'ó', 'ò', 'õ', 'ô'},
         {'u', 'ú', 'ù', 'ũ', 'û'}
     };
     
     // Copia o texto para evitar alterar a variável original
     char cCopia = c;
     strcpy(textoCopia, texto);
     int len = strlen(textoCopia); // Comprimento da string
 
     // Converte "cCopia" para vogal sem acento
     converteVogais(&cCopia, vogais);
 
     // Case-insensitive: converte "cCopia" para minúscula
     if (isCaseSensitive == 0) {cCopia = tolower(cCopia);} 
 
     // Percorre o texto
     for (int k = 0; k < len; k++) {
         // Converte "textoCopia" para vogais sem acento
         converteVogais(&textoCopia[k], vogais);
 
         // Case-insensitive: converte "textoCopia" para minúscula
         if (isCaseSensitive == 0) {
             textoCopia[k] = tolower(textoCopia[k]);
         }
 
         // Conta as ocorrências
         if (textoCopia[k] == cCopia) {
             qtdOcorrencias++;
         }
     }
     printf("cCopia: %c\n", cCopia);
     printf("textoCopia: %s\n", textoCopia);
 
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
int q4(char *strTexto, char *strBusca, int posicoes[30])
{
    int qtdOcorrencias = -1;

    return qtdOcorrencias;
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

int q5(int num)
{

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

int q6(int numerobase, int numerobusca)
{
    int qtdOcorrencias;
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

 int q7(char matriz[8][10], char palavra[5])
 {
     int achou;
     return achou;
 }

DataQuebrada quebraData(char data[]){
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