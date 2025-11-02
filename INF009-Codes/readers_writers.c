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
#define READER "READER"
#define READING "READING"
#define USING "USING"
#define WRITER "WRITER"
#define WRITING "WRITING"

// Semaphores
sem_t mutex;    // Controls the access to the critical region
sem_t db;       // Controls the access to the data base

int rc = 0;     // Count readers reading the data base

// Headers
void reading_dataBase(void);
void using_dataBase(void);
void think_update_dataBase(void);
void writing_dataBase(void);
void printAction(char *owner, char *action);
void checkingSemaphore(sem_t *semaphore, int *val);

// Reader thread function
void* reader(void *arg) {
    while(TRUE) {
        // The reader tries to enter the data base

        // Checking if the CR is blocked
        int val;
        checkingSemaphore(&mutex, &val);

        sem_wait(&mutex); // Enter the critical region or get blocked
        printAction(READER, "in the CR");

        rc += 1; // Increment readers 
        if(rc == 1) {
            checkingSemaphore(&db, &val);
            sem_wait(&db); // Block writers
            printAction(READER, "blocking writers, db unavailable");
        }
        
        printAction(READER, "out the CR");
        sem_post(&mutex); // Leave the critical region and wake up another reader if there is one blocked for trying to enter the CR

        // The reader reads the data base
        reading_dataBase();

        // The reader tries to leave the data base

        // Checking if the CR is blocked
        checkingSemaphore(&mutex, &val);

        sem_wait(&mutex); // Enter the critical region or get blocked
        printAction(READER, "in the CR");

        rc -= 1; // Decrement readers 
        if(rc == 0) {
            checkingSemaphore(&db, &val);
            sem_post(&db); // Leave the data base and wake up a writer if there is one blocked for trying to modify the data base
            printAction(READER, "not blocking writers anymore, db available");
        }
        
        printAction(READER, "out the CR");
        sem_post(&mutex); // Leave the critical region and wake up another reader if there is one blocked for trying to enter the CR

        using_dataBase();
    }
    return NULL;
}

// Write thread function
void* writer(void *arg) {
    while(TRUE) {
        think_update_dataBase();

        // Checking if the CR is blocked
        int val;
        checkingSemaphore(&mutex, &val);

        sem_wait(&db); // Enter the data base
        printAction(WRITER, "blocking readers, db unavailable");

        writing_dataBase(); // Modify the data base

        printAction(WRITER, "not blocking readers anymore, db unavailable");
        sem_post(&db); // Leave the data base and wake up a reader if there is one blocked for trying to read the data base
    }
    return NULL;
}

void checkingSemaphore(sem_t *semaphore, int *val) {
    sem_getvalue(semaphore, val);
    if(val == 0) printAction(READER, "blocked, someones is already in the CR");
}

// Simulating reading
void reading_dataBase(void) {
    printAction(READER, READING);
    usleep(1000000); // 1 s 
}

// Simulating using
void using_dataBase(void) {
    printAction(READER, USING);
    usleep(1000000); // 1 s 
}

void think_update_dataBase(void) {
    printAction(WRITER, "interested to access the data base");
    usleep(500000); // 0,5 s
}

// Simulating writing
void writing_dataBase(void) {
    printAction(WRITER, WRITING);
    usleep(1000000); // 1 s 
}

void printAction(char* owner, char* action) {
    printf("The %s is %s\n", owner, action);
    fflush(stdout);
}

int main() {
    // Declaring threads
    pthread_t t_reader;
    pthread_t t_writer;

    // Initializing semaphores
    sem_init(&mutex, 0, 1);
    sem_init(&db, 0, 1);

    // Creating threads
    pthread_create(&t_reader, NULL, reader, NULL);
    pthread_create(&t_writer, NULL, writer, NULL);

    pthread_join(t_reader, NULL);
    pthread_join(t_writer, NULL);

    return 0;
}
