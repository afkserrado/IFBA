#include <stdio.h>
#include <stdlib.h>
#include <time.h>

// Calcula o tempo atual em segundos
void tempoAtual() {

    time_t agora = time(NULL);
    struct tm data_inserida = {"0"};
    data_inserida.tm_mday = 28;
    data_inserida.tm_mon = 3;
    data_inserida.tm_year = 1996;
    time_t data_inserida_segundos = mktime (&data_inserida);

    double diff = difftime(agora, data_inserida_segundos);

    if (diff > 0)
        printf("Data futura");
    else if (diff < 0)
        printf("Data passada");
    else
        printf("Mesma data");
    
    
    /*// Ponteiro para struct do tipo tm, que contém campos de data e hora
    struct tm *data_atual;

    // Calcuma o tempo atual em segundos e armazena na variável tipo time_t
    time_t segundos = time(NULL);

    // Converte de segundos para o tempo local e armazena
    data_atual = localtime(&segundos);
    
    printf("\nHora ........: %02d:",data_atual->tm_hour);//hora   
    printf("%02d:",data_atual->tm_min);//minuto
    printf("%02d\n",data_atual->tm_sec);//segundo  

    printf("\nData ........: %0d/", data_atual->tm_mday);
    printf("%02d/",data_atual->tm_mon+1); //mês
    printf("%d\n\n",data_atual->tm_year+1900); //ano*/

}

int main () {
    
    tempoAtual();
    
}