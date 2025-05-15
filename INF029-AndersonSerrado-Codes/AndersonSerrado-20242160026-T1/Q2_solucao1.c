// Não funciona completamente

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

// Structs
typedef struct DQ
{
    int iDia; 
    int iMes;
    int iAno;
    int valido; // 0 se inválido, e 1 se válido 

} DataQuebrada;

typedef struct Qtd
{
    int qtdDias;
    int qtdMeses;
    int qtdAnos;
    int retorno;

} DiasMesesAnos;

// Funções auxiliares
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

int bissexto (int ano) {
    // Ano é bissexto
    if(((ano % 4 == 0) && (ano % 100 != 0)) || (ano % 400 == 0)) {
        return 1; 
    }
    return 0; // Não é bissexto
}

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

        // Caso o ano tenha apenas 2 dígitos, corrige para 4
        // Neste trabalho, estabeleceu-se que anos com 2 dígitos são 2000+
        if (dtqInicial.iAno < 100) {dtqInicial.iAno += 2000;}
        if (dtqFinal.iAno < 100) {dtqFinal.iAno += 2000;}

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
        printf("\niqtdDias = %d", iqtdDias); // OK

        // 2. Dias desde o início do ano final até a data final
        int fqtdDias = diasPassados(dtqFinal);
        printf("\nfqtdDias = %d", fqtdDias); // OK

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
        printf("\ndiasAnosCompletos = %d", diasAnosCompletos);
        printf("\ndiasTotais = %d", diasTotais);

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
            if (diasResto >= diasMes[mes]) {
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

int main () {

    char datainicial[11], datafinal[11];
    DiasMesesAnos dma;

    strcpy(datainicial, "01/2/2016");
    strcpy(datafinal, "28/02/2020");
    dma = q2(datainicial, datafinal);
    printf("\nRetorno: %d\n", dma.retorno);
    printf("Dias: %d\n", dma.qtdDias);
    printf("Meses: %d\n", dma.qtdMeses);
    printf("Anos: %d\n", dma.qtdAnos);
}