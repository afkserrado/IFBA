// Libraries
#include <stdio.h>      // For terminal input/output (printf, etc.)
#include <stdlib.h>     // General functions (malloc, rand, etc.)
#include <pthread.h>    // For creating and managing threads
#include <semaphore.h>  // For semaphore creation and management
#include <unistd.h>     // For sleep/usleep functions

// Global constants and variables
#define TRUE 1
#define TAM 100     // Size of the shared buffer
int buffer[TAM];    // Shared buffer
sem_t mutex;        // Control the access to the critical region
sem_t empty;        // Counts the number of empty slots in the shared buffer
sem_t full;         // Counts the number of filled slots in the shared buffer
int in = 0;         // Index for inserting items
int out = 0;        // Index for removing items

// Headers
int produce_item(void);
void insert_item(int);
int remove_item(void);
void consume_item(int);

// Standard thread function signature used by pthreads:
// void* thread_function(void* arg);
//
// void* is a generic pointer type. Using it allows the function to receive or return any type of data via pointer.

// Producer thread function
void* producer(void* arg) {
    int item;

    // Infinite loop
    while(TRUE) {
        // Produces the item outside the critical region
        item = produce_item(); 

        // Blocks the producer if there aren't any empty slots
        sem_wait(&empty); 

        // Tries to enter the critical region or blocks the producer if another process is already in it
        sem_wait(&mutex);

        // Inserts the item into the shared buffer (critical region)
        insert_item(item);

        // Leaves the critical region and wakes up a process if there is one blocked waiting to enter the CR
        sem_post(&mutex); 

        // Wakes up a consumer if there is one blocked trying to consume from the shared buffer when it was empty
        sem_post(&full);
        
        // Simulating production time (100 ms)
        usleep(100000);
    }

    return NULL;
}

// Generates a random item to be produced
int produce_item(void) {
    return rand() % 1000; // Generates a random number between 0 and 999
}

// Inserts the item into the circular shared buffer
void insert_item(int item) {
    buffer[in] = item; // Insert the item into the shared buffer
    in = (in + 1) % TAM; // Circular increment
}

// Consumer thread function
void* consumer(void* arg) {
    int item;

    while(TRUE) {
        // Block the consumer if there aren't any filled slots
        sem_wait(&full); 

        // Tries to enter the critical region or blocks the consumer if another process is already in it
        sem_wait(&mutex);

        // Removes the item from the shared buffer (critical region)
        item = remove_item();

        // Leaves the critical region and wakes up a process if there is one blocked waiting to enter the CR
        sem_post(&mutex);

        // Wakes up a producer if there is one blocked trying to produce into the shared buffer when it was full
        sem_post(&empty);

        // Consumes the item outside the critical region
        consume_item(item);

        // Simulating consumption time (1 s)
        usleep(1000000);
    }

    return NULL;
}

// Removes the item from the circular shared buffer
int remove_item(void) {
    int item = buffer[out];
    out = (out + 1) % TAM;
    return item;
}

// Prints the consumed item
void consume_item(int item) {
    printf("Product consumed: %d\n", item);
}

// Standard semaphore function signature:
// int sem_init(sem_t *sem, int pshared, unsigned int value);
//
// Pointer to the semaphore 
// pshared: 0 for shared semaphore between threads of the same process 
//          1 for shared semaphore between different process
// initial value of the semaphore

// Standard thread creation function signature:
// int pthread_create(pthread_t *thread, const pthread_attr_t *attr,
//                    void *(*start_routine)(void*), void *arg);
//
// thread: pointer to a pthread_t variable that will store the ID of the created thread
// attr: pointer to a pthread_attr_t structure specifying thread attributes;
//       use NULL for default attributes
// start_routine: pointer to the function that the thread will execute;
//                must have signature void* func(void*)
// arg: pointer to the argument to pass to start_routine;
//      can be NULL if no argument is needed

// Standard thread join function signature:
// int pthread_join(pthread_t thread, void **retval);
//
// thread: the ID of the thread to wait for (pthread_t variable)
// retval: pointer to a void* variable where the thread's return value will be stored;
//         can be NULL if you don't need the return value
//
// The calling thread will block until the specified thread terminates.

int main() {
    // Thread identifiers
    pthread_t prod_tid, cons_tid;

    // Initialize semaphores
    sem_init(&mutex, 0, 1);     // Mutex starts unlocked
    sem_init(&empty, 0, TAM);   // Buffer initially empty
    sem_init(&full, 0, 0);      // No filled slots initially

    // Create producer thread
    pthread_create(&prod_tid, NULL, producer, NULL);

    // Create consumer thread
    pthread_create(&cons_tid, NULL, consumer, NULL);

    return 0;
}