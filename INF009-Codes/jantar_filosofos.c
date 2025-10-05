// Algoritmo do problema do jantar dos filósofos
// Problema de sincronização em sistemas concorrentes
// Garantir que não haja deadlock (bloqueio de todos os filósofos) e starvation (algum filósofo não consegue comer)

#include <stdio.h>

// Quantidade de filósofos
#define N 5                         

// Vizinho da esquerda
int left(int i) {
    return (i + N - 1) % N;         
}

// Vizinho da direita
int right(int i) {
    return (i + 1) % N;
}

// Estados dos filósofos
#define thinking 0                  // Não precisa de garfos
#define hungry 1                    // Precisa de garfos
#define eating 2                    // Está utilizando os garfos

// Tipo especial de int para controlar a concorrência
// 
// Semáforo <= 0 (🔴): recurso compartilhado bloqueado
// Semáforo > 0 (🟢): recurso compartilhado liberado
typedef int semaphore;

int state[N]; // Array para controlar os estados (um por filósofo)

// Semáforo para garantir exclusão mútua nas regiões críticas
// Garante que apenas um filósofo altere o seu estado ou de um outro filósofo por vez
// Evita problemas de sincronização em função do acesso simultâneo
// Ou seja, mesmo que dois filósofos possam comer ao mesmo tempo, apenas um pode modificar o estado de um filósofo por vez
semaphore mutex = 1; 

// Array de semáforos (um por filósofo)
// Controla o acesso aos garfos (recurso compartilhado)
semaphore s[N]; 

// Funções auxiliares
void down(semaphore *s) { // Recebe o semáforo de um filósofo
    while(*s <= 0) {} // Filósofo esperando a liberação dos garfos
    (*s)--; // Decrementa o semáforo, isto é, recurso bloqueado. Logo, filósofo não pode comer
}

void up(semaphore *s) { // Recebe o semáforo de um filósofo
    (*s)++; // Incrementa o semáforo, isto é, recurso liberado. Logo, filósofo pode comer
}

void think(int i) {
    printf("O filósofo %d está pensando.\n", i);
}

void eat(int i) {
    printf("O filósofo %d está comendo.\n", i);
}

// Verifica se o filósofo pode comer
void test(int i) {
    if (state[i] == hungry && state[left(i)] != eating && state[right(i)] != eating) {
        up(&s[i]); // Libera o semáforo do filósofo
        state[i] = eating; // Filósofo está comendo
    }
}

// Pega os garfos
void take_forks(int i) {
    down(&mutex); // Entra na região crítica
    state[i] = hungry; // Filósofo quer comer
    test(i); // Tenta pegar os garfos
    up(&mutex); // Sai da região crítica
    
    // Bloqueia o filósofo
    // Se acessou os recursos, permite que os vizinhos acessem para evitar inanição
    // Se não acessou, é porque os recursos não estão liberados
    down(&s[i]); 
}

// Devolve os garfos
void put_forks(int i) {
    down(&mutex); // Entra na região crítica
    state[i] = thinking; // Filósofo acabou de comer
    test(left(i)); // Verifica se o vizinho da esquerda pode comer
    test(right(i)); // Verifica se o vizinho da direita pode comer
    up(&mutex); // Sai da região crítica
}

// Cria o filósofo (processo), onde i, de 0 a N-1, é o número do filósofo
void philosopher(int i) {
    // Loop infinito
    while(1) {
        think(i);       // O filósofo está pensando
        take_forks(i); // Pega dois garfos ou bloqueia
        eat(i);         // O filósofo está comendo (garfos bloquados)
        put_forks(i);  // O filósofo devolveu os garfos
    }
}

