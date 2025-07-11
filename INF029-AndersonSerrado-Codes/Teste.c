#include <stdio.h>
#include <stdlib.h>
c
int main() {
    
    system("clear");
    
    char p1[6] = "ABCDE";  
    /* Vetor de caracteres com 6 posições (5 caracteres + '\0' no final)
    p1[0] = 'A', p1[1] = 'B', p1[2] = 'C', p1[3] = 'D', p1[4] = 'E', p1[5] = '\0' 
    */
    
    char *p2;  
    /* 
    Ponteiro para char, p2 vai armazenar o endereço de um único char 
    */

    p2 = p1;  
    /* 
    p2 recebe o endereço de p1, ou seja, p2 agora aponta para o primeiro elemento de p1 
    p2 armazena o endereço de p1[0], ou seja, p2 = &p1[0]
    Exemplo de endereço de p1[0]: p2 = 0x7ffee402c280 (endereço genérico) 
    */
 
    p1[2] = 'X';  
    /* 
    Modifica p1[2] de 'C' para 'X'. Agora, p1 é "ABXDE" 
    A string em p1 se torna "ABXDE"
    */
    
    p2[3] = 'Y';  
    /* 
    Modifica p1[3] de 'D' para 'Y'. Agora, p1 é "ABXYE" 
    A string em p1 se torna "ABXYE"
    */

    char *p3[5];  
    /* 
    p3 é um vetor de 5 ponteiros para char 
    p3[0], p3[1], ..., p3[4] são ponteiros que podem armazenar endereços de memória para char
    */
    
    p3[0] = p1;  
    /* 
    p3[0] recebe o endereço de p1 (p3[0] agora aponta para o início de p1) 
    p3[0] armazena o endereço de p1[0], ou seja, p3[0] = &p1[0] 
    Exemplo de endereço de p1: p3[0] = 0x7ffee402c280 (endereço genérico)
    */

    p3[2] = &p1[3];  
    /* 
    p3[2] recebe o endereço de p1[3], ou seja, p3[2] agora aponta para o caractere p1[3] ('Y') 
    p3[2] armazena o endereço de p1[3], ou seja, p3[2] = &p1[3]
    Exemplo de endereço de p1[3]: p3[2] = 0x7ffee402c285 (endereço genérico)
    */

    char (*p4)[6];  
    /* 
    p4 é um ponteiro para um array de 5 char 
    p4 armazenará o endereço de um vetor de 5 caracteres (array de 5 `char`)
    */
    
    p4 = &p1;  
    /* 
    p4 recebe o endereço de p1, ou seja, p4 agora aponta para o vetor inteiro p1 
    p4 armazena o endereço de p1, que é o endereço do array de 5 elementos do tipo char
    Exemplo de endereço de p1: p4 = 0x7ffee402c280 (endereço genérico)
    */
    
    printf("%s", p1);  

    return 0; 
}
