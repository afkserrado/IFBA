#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

// Structs
typedef struct DQ {
    int iDia; 
    int iMes;
    int iAno;
    int valido; // 0 se inválido, e 1 se válido 
} DataQuebrada;

typedef struct Qtd {
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

        // Ajuste para bissextos com 365 dias
        // Caso o ano seja bissexto e tenha 365 dias, contabiliza como 1 ano completo
        /*if (bissexto(dtqInicial.iAno) && difMes == 11 && difDia == 30 && dtqFinal.iDia == dtqInicial.iDia - 1) {
            printf("\nEntrou");
            difAno += 1;
            difMes = 0;
            difDia = 0;
        }*/

        // Anos bissextos
        // Caso 1: 28/02/2016 a 28/02/2017 (366d) ou 29/02/2016 a 01/03/2017 (366d) -> 1a0m0d (regra geral)
        // Caso 2: 29/02/2016 a 28/02/2020 -> 3a11m30d (regral geral)
        // Caso 3: 29/02/2016 a 29/02/2020 -> 4a0m0d (regral geral)
        // Caso 4: DD/MM (01 OU 02)/AAAA bissexto a DD-1/MM (01 OU 02)/AAAA não bissexto -> 1a0m0d
        if (bissexto(dtqInicial.iAno) && bissexto(dtqFinal.iAno) != 1 && difMes == 11 && difDia == 30) {
            printf("\nEntrou");
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

int main () {

    char datainicial[11], datafinal[11];
    DiasMesesAnos dma;

    strcpy(datainicial, "29/02/2016");
    strcpy(datafinal, "01/03/2017");
    dma = q2(datainicial, datafinal);
    printf("\nIntervalo: %s - %s\n", datainicial, datafinal);
    printf("Retorno: %d\n", dma.retorno == 1);
    printf("Anos: %d\n", dma.qtdAnos == 1); 
    printf("Meses: %d\n", dma.qtdMeses == 0);
    printf("Dias: %d\n", dma.qtdDias == 0);

    strcpy(datainicial, "15/01/2016");
    strcpy(datafinal, "14/01/2017");
    dma = q2(datainicial, datafinal);
    printf("\nIntervalo: %s - %s\n", datainicial, datafinal);
    printf("Retorno: %d\n", dma.retorno == 1);
    printf("Anos: %d\n", dma.qtdAnos == 1); 
    printf("Meses: %d\n", dma.qtdMeses == 0);
    printf("Dias: %d\n", dma.qtdDias == 0);

    strcpy(datainicial, "15/01/2016");
    strcpy(datafinal, "15/01/2017");
    dma = q2(datainicial, datafinal);
    printf("\nIntervalo: %s - %s\n", datainicial, datafinal);
    printf("Retorno: %d\n", dma.retorno == 1);
    printf("Anos: %d\n", dma.qtdAnos == 1); 
    printf("Meses: %d\n", dma.qtdMeses == 0);
    printf("Dias: %d\n", dma.qtdDias == 0);

    strcpy(datainicial, "01/06/2016");
    strcpy(datafinal, "01/06/2017");
    dma = q2(datainicial, datafinal);
    printf("\nIntervalo: %s - %s\n", datainicial, datafinal);
    printf("Retorno: %d\n", dma.retorno == 1);
    printf("Anos: %d\n", dma.qtdAnos == 1); 
    printf("Meses: %d\n", dma.qtdMeses == 0);
    printf("Dias: %d\n", dma.qtdDias == 0);

    strcpy(datainicial, "01/06/2016");
    strcpy(datafinal, "31/05/2017");
    dma = q2(datainicial, datafinal);
    printf("\nIntervalo: %s - %s\n", datainicial, datafinal);
    printf("Retorno: %d\n", dma.retorno == 1);
    printf("Anos: %d\n", dma.qtdAnos == 1); 
    printf("Meses: %d\n", dma.qtdMeses == 0);
    printf("Dias: %d\n", dma.qtdDias == 0);
    
    // Testes da lista    
    printf("\nTestes da lista:\n");
    strcpy(datainicial, "06/06/2017");
    strcpy(datafinal, "07/07/2017");
    dma = q2(datainicial, datafinal);
    printf("\nIntervalo: %s - %s\n", datainicial, datafinal);
    printf("Retorno: %d\n", dma.retorno == 1);
    printf("Anos: %d\n", dma.qtdAnos == 0); 
    printf("Meses: %d\n", dma.qtdMeses == 1);
    printf("Dias: %d\n", dma.qtdDias == 1);

    strcpy(datainicial, "06/06/2017");
    strcpy(datafinal, "05/07/2018");
    dma = q2(datainicial, datafinal);
    printf("\nIntervalo: %s - %s\n", datainicial, datafinal);
    printf("Retorno: %d\n", dma.retorno == 1);
    printf("Anos: %d\n", dma.qtdAnos == 1); 
    printf("Meses: %d\n", dma.qtdMeses == 0);
    printf("Dias: %d\n", dma.qtdDias == 29);

    strcpy(datainicial, "26/07/2017");
    strcpy(datafinal, "25/08/2017");
    dma = q2(datainicial, datafinal);
    printf("\nIntervalo: %s - %s\n", datainicial, datafinal);
    printf("Retorno: %d\n", dma.retorno == 1);
    printf("Anos: %d\n", dma.qtdAnos == 0); 
    printf("Meses: %d\n", dma.qtdMeses == 0);
    printf("Dias: %d\n", dma.qtdDias == 30);

    strcpy(datainicial, "26/06/2017");
    strcpy(datafinal, "26/07/2017");
    dma = q2(datainicial, datafinal);
    printf("\nIntervalo: %s - %s\n", datainicial, datafinal);
    printf("Retorno: %d\n", dma.retorno == 1);
    printf("Anos: %d\n", dma.qtdAnos == 0); 
    printf("Meses: %d\n", dma.qtdMeses == 1);
    printf("Dias: %d\n", dma.qtdDias == 0);

    strcpy(datainicial, "27/02/2016");
    strcpy(datafinal, "03/03/2017");
    dma = q2(datainicial, datafinal);
    printf("\nIntervalo: %s - %s\n", datainicial, datafinal);
    printf("Retorno: %d\n", dma.retorno == 1);
    printf("Anos: %d\n", dma.qtdAnos == 1); 
    printf("Meses: %d\n", dma.qtdMeses == 0);
    printf("Dias: %d\n", dma.qtdDias == 4);

    strcpy(datainicial, "27/02/2015");
    strcpy(datafinal, "03/03/2016");
    dma = q2(datainicial, datafinal);
    printf("\nIntervalo: %s - %s\n", datainicial, datafinal);
    printf("Retorno: %d\n", dma.retorno == 1);
    printf("Anos: %d\n", dma.qtdAnos == 1); 
    printf("Meses: %d\n", dma.qtdMeses == 0);
    printf("Dias: %d\n", dma.qtdDias == 5);

    strcpy(datainicial, "28/01/2016");
    strcpy(datafinal, "29/02/2016");
    dma = q2(datainicial, datafinal);
    printf("\nIntervalo: %s - %s\n", datainicial, datafinal);
    printf("Retorno: %d\n", dma.retorno == 1);
    printf("Anos: %d\n", dma.qtdAnos == 0); 
    printf("Meses: %d\n", dma.qtdMeses == 1);
    printf("Dias: %d\n", dma.qtdDias == 1);

    strcpy(datainicial, "28/02/2016");
    strcpy(datafinal, "28/02/2017");
    dma = q2(datainicial, datafinal);
    printf("\nIntervalo: %s - %s\n", datainicial, datafinal);
    printf("Retorno: %d\n", dma.retorno == 1);
    printf("Anos: %d\n", dma.qtdAnos == 1); 
    printf("Meses: %d\n", dma.qtdMeses == 0);
    printf("Dias: %d\n", dma.qtdDias == 0);

    strcpy(datainicial, "29/02/2016");
    strcpy(datafinal, "28/02/2017");
    dma = q2(datainicial, datafinal);
    printf("\nIntervalo: %s - %s\n", datainicial, datafinal);
    printf("Retorno: %d\n", dma.retorno == 1);
    printf("Anos: %d\n", dma.qtdAnos == 1); 
    printf("Meses: %d\n", dma.qtdMeses == 0);
    printf("Dias: %d\n", dma.qtdDias == 0);

    strcpy(datainicial, "29/02/2016");
    strcpy(datafinal, "28/02/2020");
    dma = q2(datainicial, datafinal);
    printf("\nxx Intervalo: %s - %s\n", datainicial, datafinal);
    printf("Retorno: %d\n", dma.retorno == 1);
    printf("Anos: %d\n", dma.qtdAnos == 3); 
    printf("Meses: %d\n", dma.qtdMeses == 11);
    printf("Dias: %d\n", dma.qtdDias == 30);

    strcpy(datainicial, "29/02/2016");
    strcpy(datafinal, "29/02/2020");
    dma = q2(datainicial, datafinal);
    printf("\nxx Intervalo: %s - %s\n", datainicial, datafinal);
    printf("Retorno: %d\n", dma.retorno == 1);
    printf("Anos: %d\n", dma.qtdAnos == 4); 
    printf("Meses: %d\n", dma.qtdMeses == 0);
    printf("Dias: %d\n", dma.qtdDias == 0);

    strcpy(datainicial, "29/02/2016");
    strcpy(datafinal, "28/02/2019");
    dma = q2(datainicial, datafinal);
    printf("\nIntervalo: %s - %s\n", datainicial, datafinal);
    printf("Retorno: %d\n", dma.retorno == 1);
    printf("Anos: %d\n", dma.qtdAnos == 3); 
    printf("Meses: %d\n", dma.qtdMeses == 0);
    printf("Dias: %d\n", dma.qtdDias == 0);

    // Testes do corretor
    printf("\nTestes do corretor:\n");
    strcpy(datainicial, "01/06/2015");
    strcpy(datafinal, "01/06/2016");
    dma = q2(datainicial, datafinal);
    printf("\nIntervalo: %s - %s\n", datainicial, datafinal);
    printf("%d\n", dma.retorno == 1);
    printf("%d\n", dma.qtdDias == 0);
    printf("%d\n", dma.qtdMeses == 0);
    printf("%d\n", dma.qtdAnos == 1);

    strcpy(datainicial, "01/30/2015");
    strcpy(datafinal, "01/06/2016");
    dma = q2(datainicial, datafinal);
    printf("\nIntervalo: %s - %s\n", datainicial, datafinal);
    printf("%d\n", dma.retorno == 2);

    strcpy(datainicial, "01/3/2015");
    strcpy(datafinal, "40/06/2016");
    dma = q2(datainicial, datafinal);
    printf("\nIntervalo: %s - %s\n", datainicial, datafinal);
    printf("%d\n", dma.retorno == 3);

    strcpy(datainicial, "01/06/2016");
    strcpy(datafinal, "01/06/2015");
    dma = q2(datainicial, datafinal);
    printf("\nIntervalo: %s - %s\n", datainicial, datafinal);
    printf("%d\n", dma.retorno == 4);

    strcpy(datainicial, "06/06/2017");
    strcpy(datafinal, "07/07/2017");
    dma = q2(datainicial, datafinal);
    printf("\nIntervalo: %s - %s\n", datainicial, datafinal);
    printf("%d\n", dma.retorno == 1);
    printf("%d\n", dma.qtdDias == 1);
    printf("%d\n", dma.qtdMeses == 1);
    printf("%d\n", dma.qtdAnos == 0);

}