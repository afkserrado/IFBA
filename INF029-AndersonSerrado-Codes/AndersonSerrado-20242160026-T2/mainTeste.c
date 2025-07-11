#include <stdio.h>
#include <stdlib.h>
#include "EstruturaVetores.h"

void show_log(char *str);

void testeInserirSemNada(); // TESTE OK
void testeCriarEstrutura(); // TESTE OK
void testeInserirComEstrutura(); // TESTE OK
void testeExcluir(); // TESTE OK
void testeExcluirNumeroEspecifico(); // TESTE OK
void testeListar(); // TESTE OK
void testeRetornarTodosNumeros(); // TESTE OK
void testeMudarTamanhoEstrutura(); // TESTE OK
void testeListaEncadeada(); // TESTE OK

void exibirVetor() {
    for (int i = 1; i <= 10; i++) {
        int v[10] = {0};
        getDadosEstruturaAuxiliar(i, v);
        printf("V[%d]: ", i);
        imprimeVetor(v);
        printf("\n");
    }
}

int main()
{
    inicializar(); // TESTE OK
    testeInserirSemNada(); // TESTE OK
    testeCriarEstrutura(); // TESTE OK
    testeInserirComEstrutura(); // TESTE OK
    testeExcluir(); // TESTE OK
    testeExcluirNumeroEspecifico(); // TESTE OK
    testeListar(); // TESTE OK
    testeRetornarTodosNumeros(); // TESTE OK
    testeMudarTamanhoEstrutura(); // TESTE OK
    testeListaEncadeada(); // TESTE OK
    finalizar(); // TESTE OK
}

int ligado = 0;
void show_log(char *str)
{
    if (ligado)
        printf("###%s###\n", str);
}

void testeInserirSemNada() // TESTE OK
{
    show_log("testeInserirSemNada()");
    printf("testeInserirSemNada\n");
    printf("%d\n", inserirNumeroEmEstrutura(2, 2) == SEM_ESTRUTURA_AUXILIAR);
    printf("%d\n", inserirNumeroEmEstrutura(-2, 2) == POSICAO_INVALIDA);
    printf("%d\n", inserirNumeroEmEstrutura(0, 2) == POSICAO_INVALIDA);
    printf("%d\n", inserirNumeroEmEstrutura(11, 2) == POSICAO_INVALIDA);
    
    //exibirVetor();

    printf("\n");
}

void testeCriarEstrutura() // TESTE OK
{
    show_log("testeCriarEstrutura()");
    printf("testeCriarEstrutura\n");
    printf("%d\n", criarEstruturaAuxiliar(-2, 5) == POSICAO_INVALIDA);
    printf("%d\n", criarEstruturaAuxiliar(0, 5) == POSICAO_INVALIDA);
    printf("%d\n", criarEstruturaAuxiliar(11, 5) == POSICAO_INVALIDA);
    printf("%d\n", criarEstruturaAuxiliar(2, -5) == TAMANHO_INVALIDO);
    printf("%d\n", criarEstruturaAuxiliar(2, 0) == TAMANHO_INVALIDO);
    printf("%d\n", criarEstruturaAuxiliar(2, 3) == SUCESSO);
    printf("%d\n", criarEstruturaAuxiliar(2, 6) == JA_TEM_ESTRUTURA_AUXILIAR);
    
    //exibirVetor();
    
    printf("\n");
}
/*
2 [ , , ]
*/

void testeInserirComEstrutura() // TESTE OK
{
    show_log("testeInserirComEstrutura()");
    //###  int inserirNumeroEmEstrutura(int valor, int posicao); ###
    printf("testeInserirComEstrutura\n");
    printf("%d\n", inserirNumeroEmEstrutura(2, 4) == SUCESSO);
    printf("%d\n", inserirNumeroEmEstrutura(2, -2) == SUCESSO);
    printf("%d\n", inserirNumeroEmEstrutura(2, 6) == SUCESSO);
    printf("%d\n", inserirNumeroEmEstrutura(2, 5) == SEM_ESPACO);
    
    //exibirVetor();
    
    printf("\n");
}
/*
2 [4,-2,6]
*/

void testeExcluir() // TESTE OK
{
    show_log("testeExcluir()");
    //###  int excluirNumeroDoFinaldaEstrutura(int posicao); ###
    printf("testeExcluir\n");
    printf("%d\n", excluirNumeroDoFinaldaEstrutura(2) == SUCESSO);
    printf("%d\n", excluirNumeroDoFinaldaEstrutura(2) == SUCESSO);
    printf("%d\n", excluirNumeroDoFinaldaEstrutura(0) == POSICAO_INVALIDA);
    printf("%d\n", excluirNumeroDoFinaldaEstrutura(1) == SEM_ESTRUTURA_AUXILIAR);
    printf("%d\n", excluirNumeroDoFinaldaEstrutura(2) == SUCESSO);
    printf("%d\n", excluirNumeroDoFinaldaEstrutura(2) == ESTRUTURA_AUXILIAR_VAZIA);
    
    //exibirVetor();

    printf("\n");
}
/*
2 [ , , ]
*/

void testeExcluirNumeroEspecifico() // TESTE OK
{
    show_log("testeExcluirNumeroEspecifico()");
    //###  int excluirNumeroEspecificoDeEstrutura(int posicao, int valor); ###
    printf("testeExcluirNumeroEspecifico\n");
    printf("%d\n", criarEstruturaAuxiliar(9, 3) == SUCESSO);
    printf("%d\n", inserirNumeroEmEstrutura(9, 7) == SUCESSO);
    printf("%d\n", inserirNumeroEmEstrutura(9, 4) == SUCESSO);

    //exibirVetor();

    printf("%d\n", excluirNumeroEspecificoDeEstrutura(9, 12) == NUMERO_INEXISTENTE);
    printf("%d\n", excluirNumeroEspecificoDeEstrutura(9, 7) == SUCESSO);
    printf("%d\n", excluirNumeroEspecificoDeEstrutura(9, 4) == SUCESSO);
    printf("%d\n", excluirNumeroEspecificoDeEstrutura(1, 2) == SEM_ESTRUTURA_AUXILIAR);

    //exibirVetor();

    printf("\n");
}
/*
9 [ 4, , ] 
*/

void testeListar() // TESTE OK
{
    show_log("testeListar()");
    //###  int getDadosEstruturaAuxiliar(int posicao, int vetorAux[]); ###
    printf("testeListar\n");
    printf("inserirNumeroEmEstrutura\n");
    printf("%d\n", inserirNumeroEmEstrutura(2, 7) == SUCESSO);
    printf("%d\n", inserirNumeroEmEstrutura(2, -9) == SUCESSO);
    printf("\n");

    int vet[2];

    printf("getDadosEstruturaAuxiliar\n");
    printf("%d\n", getDadosEstruturaAuxiliar(1, vet) == SEM_ESTRUTURA_AUXILIAR);
    printf("%d\n", getDadosEstruturaAuxiliar(11, vet) == POSICAO_INVALIDA);
    printf("%d\n", getDadosEstruturaAuxiliar(2, vet) == SUCESSO);

    printf("%d\n", vet[0] == 7);
    printf("%d\n", vet[1] == -9);

    //exibirVetor();
    printf("\n");

    printf("getDadosOrdenadosEstruturaAuxiliar\n");
    printf("%d\n", getDadosOrdenadosEstruturaAuxiliar(1, vet) == SEM_ESTRUTURA_AUXILIAR);
    printf("%d\n", getDadosOrdenadosEstruturaAuxiliar(-1, vet) == POSICAO_INVALIDA);
    printf("%d\n", getDadosOrdenadosEstruturaAuxiliar(2, vet) == SUCESSO);

    printf("%d\n", vet[0] == -9);
    printf("%d\n", vet[1] == 7);

    //exibirVetor();
    printf("\n");

    printf("getDadosEstruturaAuxiliar\n");
    printf("%d\n", getDadosEstruturaAuxiliar(2, vet) == SUCESSO);

    printf("%d\n", vet[0] == 7);
    printf("%d\n", vet[1] == -9);

    //exibirVetor();
    printf("\n");

    printf("excluirNumeroDoFinaldaEstrutura\n");
    printf("%d\n", excluirNumeroDoFinaldaEstrutura(2) == SUCESSO);
    printf("%d\n", excluirNumeroDoFinaldaEstrutura(2) == SUCESSO);
    //exibirVetor();
    printf("\n");
}
/*
2 [ , , ]
*/

void testeRetornarTodosNumeros() // TESTE OK
{
    show_log("testeRetornarTodosNumeros()");
    printf("testeRetornarTodosNumeros\n");
    printf("getDadosDeTodasEstruturasAuxiliares\n");
    int vet1[2];
    printf("%d\n", getDadosDeTodasEstruturasAuxiliares(vet1) == TODAS_ESTRUTURAS_AUXILIARES_VAZIAS);

    //exibirVetor();
    printf("\n");

    printf("getDadosOrdenadosDeTodasEstruturasAuxiliares\n");
    printf("%d\n", getDadosOrdenadosDeTodasEstruturasAuxiliares(vet1) == TODAS_ESTRUTURAS_AUXILIARES_VAZIAS);

    //exibirVetor();
    printf("\n");

    printf("inserirNumeroEmEstrutura\n");
    printf("%d\n", inserirNumeroEmEstrutura(2, 3) == SUCESSO);
    printf("%d\n", inserirNumeroEmEstrutura(2, 8) == SUCESSO);
    printf("%d\n", inserirNumeroEmEstrutura(2, 0) == SUCESSO);
    /*
    2 [3,8,0]
    */

    //exibirVetor();
    printf("\n");

    printf("criarEstruturaAuxiliar\n");
    printf("%d\n", criarEstruturaAuxiliar(5, 10) == SUCESSO);

    printf("inserirNumeroEmEstrutura\n");
    printf("%d\n", inserirNumeroEmEstrutura(5, 1) == SUCESSO);
    printf("%d\n", inserirNumeroEmEstrutura(5, 34) == SUCESSO);
    printf("%d\n", inserirNumeroEmEstrutura(5, 12) == SUCESSO);
    printf("%d\n", inserirNumeroEmEstrutura(5, 6) == SUCESSO);
    printf("%d\n", inserirNumeroEmEstrutura(5, 27) == SUCESSO);
    printf("%d\n", inserirNumeroEmEstrutura(5, -6) == SUCESSO);
    /*
    2 [3,8,0]
    5 [1,34,12,6,27,-6, , , , ]
    */

    //exibirVetor();
    printf("\n");

    printf("getDadosDeTodasEstruturasAuxiliares\n");
    int vet[9];
    printf("%d\n", getDadosDeTodasEstruturasAuxiliares(vet) == SUCESSO);

    printf("%d\n", vet[0] == 3);
    printf("%d\n", vet[1] == 8);
    printf("%d\n", vet[2] == 0);
    printf("%d\n", vet[3] == 1);
    printf("%d\n", vet[4] == 34);
    printf("%d\n", vet[5] == 12);
    printf("%d\n", vet[6] == 6);
    printf("%d\n", vet[7] == 27);
    printf("%d\n", vet[8] == -6);

    printf("\n");

    printf("getDadosOrdenadosDeTodasEstruturasAuxiliares\n");
    int vet2[9];
    printf("%d\n", getDadosOrdenadosDeTodasEstruturasAuxiliares(vet2) == SUCESSO);

    printf("%d\n", vet2[0] == -6);
    printf("%d\n", vet2[1] == 0);
    printf("%d\n", vet2[2] == 1);
    printf("%d\n", vet2[3] == 3);
    printf("%d\n", vet2[4] == 6);
    printf("%d\n", vet2[5] == 8);
    printf("%d\n", vet2[6] == 12);
    printf("%d\n", vet2[7] == 27);
    printf("%d\n", vet2[8] == 34);

    printf("\n");

    printf("getDadosDeTodasEstruturasAuxiliares\n");
    printf("%d\n", getDadosDeTodasEstruturasAuxiliares(vet) == SUCESSO);

    printf("%d\n", vet[0] == 3);
    printf("%d\n", vet[1] == 8);
    printf("%d\n", vet[2] == 0);
    printf("%d\n", vet[3] == 1);
    printf("%d\n", vet[4] == 34);
    printf("%d\n", vet[5] == 12);
    printf("%d\n", vet[6] == 6);
    printf("%d\n", vet[7] == 27);
    printf("%d\n", vet[8] == -6);
    printf("\n");
}
/*
2 [3,8,0]
5 [1,34,12,6,27,-6, , , , ]
*/

/*
int modificarTamanhoEstruturaAuxiliar(int posicao, int novoTamanho);
Objetivo: modificar o tamanho da estrutura auxiliar da posição 'posicao' para o novo tamanho 'novoTamanho' + tamanho atual
Suponha o tamanho inicial = x, e novo tamanho = n. O tamanho resultante deve ser x + n. Sendo que x + n deve ser sempre >= 1

Rertono (int)
    SUCESSO - foi modificado corretamente o tamanho da estrutura auxiliar
    SEM_ESTRUTURA_AUXILIAR - Não tem estrutura auxiliar
    POSICAO_INVALIDA - Posição inválida para estrutura auxiliar
    NOVO_TAMANHO_INVALIDO - novo tamanho não pode ser negativo
    SEM_ESPACO_DE_MEMORIA - erro na alocação do novo valor
*/
void testeMudarTamanhoEstrutura() // TESTE OK
{
    show_log("testeMudarTamanhoEstrutura()");
    int vet[1];
    printf("testeMudarTamanhoEstrutura\n");
    printf("modificarTamanhoEstruturaAuxiliar\n");
    printf("%d\n", modificarTamanhoEstruturaAuxiliar(2, -3) == NOVO_TAMANHO_INVALIDO);
    printf("%d\n", modificarTamanhoEstruturaAuxiliar(2, -4) == NOVO_TAMANHO_INVALIDO);
    printf("%d\n", modificarTamanhoEstruturaAuxiliar(11, 7) == POSICAO_INVALIDA);
    printf("%d\n", modificarTamanhoEstruturaAuxiliar(0, 7) == POSICAO_INVALIDA);
    printf("%d\n", modificarTamanhoEstruturaAuxiliar(1, 7) == SEM_ESTRUTURA_AUXILIAR);

    printf("\n");

    printf("modificarTamanhoEstruturaAuxiliar\n");
    printf("modificar para tamanho de 3 para 1\n");
    //modificar para tamanho de 3 para 1
    printf("%d\n", modificarTamanhoEstruturaAuxiliar(2, -2) == SUCESSO);
    printf("%d\n", getQuantidadeElementosEstruturaAuxiliar(2) == 1);
    printf("%d\n", getDadosEstruturaAuxiliar(2, vet) == SUCESSO);
    printf("%d\n", vet[0] == 3);

    printf("\n");

    printf("modificarTamanhoEstruturaAuxiliar\n");
    printf("modificar para tamanho de 1 para 4\n");
    //modificar para tamanho de 1 para 4
    printf("%d\n", modificarTamanhoEstruturaAuxiliar(2, 3) == SUCESSO);
    printf("%d\n", getQuantidadeElementosEstruturaAuxiliar(2) == 1);
    printf("%d\n", getDadosEstruturaAuxiliar(2, vet) == SUCESSO);
    printf("%d\n", vet[0] == 3);

    printf("\n");

    printf("inserirNumeroEmEstrutura\n");
    printf("%d\n", inserirNumeroEmEstrutura(2, 4) == SUCESSO);
    printf("%d\n", inserirNumeroEmEstrutura(2, -2) == SUCESSO);
    printf("%d\n", inserirNumeroEmEstrutura(2, 6) == SUCESSO);
    printf("%d\n", inserirNumeroEmEstrutura(2, 5) == SEM_ESPACO);
    printf("\n");
}
/*
2 [3,4,-2,6]
5 [1,34,12,6,27,-6, , , , ]
*/

void testeListaEncadeada() // TESTE OK
{
    show_log("testeListaEncadeada()");
    printf("montarListaEncadeadaComCabecote\n");
    int vet[10];
    No *inicio = montarListaEncadeadaComCabecote();
    getDadosListaEncadeadaComCabecote(inicio, vet);

    printf("%d\n", vet[0] == 3);
    printf("%d\n", vet[1] == 4);
    printf("%d\n", vet[2] == -2);
    printf("%d\n", vet[3] == 6);
    printf("%d\n", vet[4] == 1);
    printf("%d\n", vet[5] == 34);
    printf("%d\n", vet[6] == 12);
    printf("%d\n", vet[7] == 6);
    printf("%d\n", vet[8] == 27);
    printf("%d\n", vet[9] == -6);

    printf("\n");

    printf("destruirListaEncadeadaComCabecote\n");
    destruirListaEncadeadaComCabecote(&inicio);

    printf("%d\n", inicio == NULL);
}