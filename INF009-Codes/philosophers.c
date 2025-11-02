// Libraries
#include <stdio.h>      // For terminal input/output (printf, etc.)
#include <stdlib.h>     // General functions (malloc, rand, etc.)
#include <pthread.h>    // For creating and managing threads
#include <semaphore.h>  // For semaphore creation and management
#include <unistd.h>     // For sleep/usleep functions

/*
pthread allows more than one threads working asynchronously inside the same process
They share resources, like the critical region, but they are also independent

Standard thread function signature used by pthreads:
void* thread_function(void* arg);

    void* is a generic pointer type. Using it allows the function to receive or return any type of data via pointer.

Standard semaphore function signature:
int sem_init(sem_t *sem, int pshared, unsigned int value);

    Pointer to the semaphore 
    pshared:    0 for shared semaphore between threads of the same process 
                1 for shared semaphore between different process
    initial value of the semaphore

Standard thread creation function signature:
int pthread_create(pthread_t *thread, const pthread_attr_t *attr, void *(*start_routine)(void*), void *arg);

    thread: pointer to a pthread_t variable that will store the ID of the created thread
    attr: pointer to a pthread_attr_t structure specifying thread attributes; 
        use NULL for default attributes
    start_routine: pointer to the function that the thread will execute;
        must have signature void* func(void*)
    arg: pointer to the argument to pass to start_routine;
        can be NULL if no argument is needed

Standard thread join function signature:
int pthread_join(pthread_t thread, void **retval);

    thread: the ID of the thread to wait for (pthread_t variable)
    retval: pointer to a void* variable where the thread's return value will be stored;
            can be NULL if you don't need the return value

    The calling thread will block until the specified thread terminates.
*/

// Global constants and variables
// General
#define TRUE 1
#define N 5     // Number of philosophers
#define UNBLOCKED 1
#define BLOCKED 0

// Philosophers' states
#define THINKING 0      // Philosopher is thinking          
#define HUNGRY 1        // Philosopher wants to want, trying to pick up forks
#define EATING 2        // Philosopher is eating

int state[N];

// Neighbor circular index
#define LEFT(i) ((i + N - 1) % N)   // Left neighbour
#define RIGHT(i) ((i + 1) % N)      // Right neighbour

// Semaphores
sem_t s[N];     // Blocking and unblocking semaphores
sem_t mutex;    // Mutual exclusion

// Headers
void* philosopher(void* arg); // Thread function for each philosopher
void take_forks(int i);       // Philosopher tries to pick both forks
void put_forks(int i);        // Philosopher returns forks after eating
void test(int i);             // Checks whether philosopher i can start eating

void think(int i);             // Simulates the thinking process
void eat(int i);               // Simulates the eating process

void chageState(int i, char* state) {
    printf("Change of state: philosopher %d is now %s\n", i, state);
    fflush(stdout);
}

void action(int i, char* action) {
    printf("Philosopher %d is %s\n", i, action);
    fflush(stdout);
}

// Philosopher thread function
void* philosopher(void* arg) {
    int i = *(int*)arg; // Convert arg to integer pointer and get philosopher ID
    
    while(TRUE) {
        think(i);            // Non-critical region: philosopher thinking
        take_forks(i);      // Try to acquire both forks or block the philosopher
        eat(i);              // Eating (executing the process)
        put_forks(i);       // Return both forks and wakes-up a neighbor or block the philosopher
    }

    return NULL;
}

// Take the forks
void take_forks(int i) {
    
    int value;
    sem_getvalue(&mutex, &value);

    if(value == 0) {
        action(i, "BLOCKED (MUTEX, TAKE_FORKS)");
    }
    
    sem_wait(&mutex);   // Try to enter the critical region or block the philosopher if another process is already in it

    action(i, "IN THE CRITICAL REGION (TAKE_FORKS)");

    state[i] = HUNGRY;  // Change philosopher's state
    chageState(i, "HUNGRY");
    test(i);            // Try to acquire both forks

    action(i, "OUT THE CRITICAL REGION (TAKE_FORKS)");

    sem_post(&mutex);   // Leave the critical region and wake up a process if there is one blocked waiting to enter the CR

    if(state[i] != EATING) {
        action(i, "BLOCKED (FORKS, TAKE_FORKS)");
    }

    sem_wait(&s[i]);    // Block the philosopher if forks were not acquired
}

// Return the forks
void put_forks(int i) {
    
    int value;
    sem_getvalue(&mutex, &value);

    if(value == 0) {
        action(i, "BLOCKED (MUTEX, PUT_FORKS)");
    }
    
    sem_wait(&mutex);       // Try to enter the critical region or block the philosopher if another process is already in it
    
    action(i, "IN THE CRITICAL REGION (PUT_FORKS)");

    state[i] = THINKING;    // Change philosopher's state
    chageState(i, "THINKING");
    test(LEFT(i));          // Try to wake-up the left neighbor
    test(RIGHT(i));         // Try to wake-up the right neighbor

    action(i, "OUT THE CRITICAL REGION (PUT_FORKS)");
    
    sem_post(&mutex);       // Leave the critical region and wake up a process if there is one blocked waiting to enter the CR
}

// Check if philosopher i can start eating
void test(int i) {
    if(state[i] == HUNGRY && state[LEFT(i)] != EATING && state[RIGHT(i)] != EATING) {
        state[i] = EATING;  // Philosopher took the forks and is eating
        chageState(i, "EATING");
        sem_post(&s[i]);    // In take_forks, avoid to block a philosopher. In put_forks, wake-up the blocked neighbor
    }
}

// Simulate eating
void eat(int i) {
    action(i, "EATING");
    fflush(stdout);  // Forces the print immediately
    usleep(1000000); // Simulate eating time (1 s)
}

// Simulate thinking
void think(int i) {
    action(i, "THINKING");
    fflush(stdout); // Forces the print immediately
    usleep(500000); // Simulate thinking time (0.5 s)
}

int main() {
    pthread_t threads[N];
    int ids[N];

    // Initialize semaphores and states and create threads
    sem_init(&mutex, 0, 1);
    for(int i = 0; i < N; i++) {
        sem_init(&s[i], 0, 0); 
        state[i] = 0;

        ids[i] = i;
        pthread_create(&threads[i], NULL, philosopher, &ids[i]);
    }

    // Wait for all threads to finish (they never end, but this keeps main alive)
    for (int i = 0; i < N; i++) {
        pthread_join(threads[i], NULL);
    }

    return 0;
}