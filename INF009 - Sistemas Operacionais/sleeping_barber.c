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
// Words
#define TRUE 1
#define CHAIRS 5 // Maximum number of waiting chairs
#define BARBERS 5
#define CUSTOMERS 10

// Semaphores
sem_t customers;    // Number of customers waiting to be served
sem_t barbers;      // Number of barbers available to serve
sem_t mutex;        // Mutual exclusion
int waiting = 0;    // Number of customers currently waiting

// Headers
void cut_hair(void);
void customer_arrived(void);
void get_haircut(void);
void giveup_haircut(void);

// Barber thread function
void* barber(void* arg) {
    while(TRUE) {
        sem_wait(&customers);   // Wait to serve a customer or block if there are none waiting
        sem_wait(&mutex);       // Enter the critical region or block
        waiting--;              // Decrement the number of waiting customers
        sem_post(&barbers);     // Wake up a costumer if there is one blocked waiting for an available barber
        sem_post(&mutex);       // Leave the critical region and wake up another process if there is one blocked for trying to enter the CR
        cut_hair();
    }
}

void* customer(void* arg) {
    while(TRUE) {
        sem_wait(&mutex);           // Enter the critical region or get blocked
        if(waiting < CHAIRS) {      // Check if there is a free chair
            customer_arrived();
            waiting++;              // Increment the number of waiting customers
            sem_post(&customers);   // Wake up a barber if there is one blocked waiting for a customer
            sem_post(&mutex);       // Leave the critical region and wake up another process if there is one blocked for trying to enter the CR
            sem_wait(&barbers);     // Try to get served by a barber or block if none are available
            get_haircut();        
        }
        else {
            sem_post(&mutex);       // Leave the critical region and wake up another process if there is one blocked for trying to enter the CR
            giveup_haircut();       // Leave the barbershop
        }
    }
}

void cut_hair(void) {
    printf("The barber is cutting a customer's hair.\n");
    fflush(stdout);
    usleep(2000000); // 2 s
}

void customer_arrived(void) {
    printf("A customer has just entered the barbershop.\n");
    fflush(stdout);
    usleep(500000); // 0,5 s
}

void get_haircut(void) {
    printf("The customer is having their hair cut.\n");
    fflush(stdout);
    usleep(2000000); // 2 s
}

void giveup_haircut(void) {
    printf("The customer left the store. There are no available chairs.\n");
    fflush(stdout);
    usleep(1000000); // 1 s
}

int main() {
    // Declaring threads
    pthread_t t_barber[BARBERS];
    pthread_t t_customer[CUSTOMERS];

    // Initializing semaphores
    sem_init(&mutex, 0, 1);
    sem_init(&barbers, 0, 0);
    sem_init(&customers, 0, 0);

    // Creating threads
    for (int i = 0; i < BARBERS; i++) {
        pthread_create(&t_barber[i], NULL, barber, NULL);
    }
    
    for (int i = 0; i < CUSTOMERS; i++) {
        pthread_create(&t_customer[i], NULL, customer, NULL);
    }

    for (int i = 0; i < BARBERS; i++) {
        pthread_join(t_barber[i], NULL);
    }

    for (int i = 0; i < CUSTOMERS; i++) {
        pthread_join(t_customer[i], NULL);
    }

    return 0;
}