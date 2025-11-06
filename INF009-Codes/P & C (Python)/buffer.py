# Libraries
from semaphores import Semaphores

class Buffer:

    ##### Private fields ##### 
    
    __in = 0        # Index of inserting items
    __out = 0       # Index of removing items

     ##### Constructors #####

    def __init__(self, maxSize = 100, semaphores = None):
        # Checking invalid argument
        if not isinstance(maxSize, int) or maxSize <= 0:
            raise TypeError("The argument 'maxSize' must be a positive integer")
        
        if semaphores is None:
            raise ValueError("A 'Semaphores' object must be provided.")
        
        # Creating the object
        self.__maxSize = maxSize
        self.__buffer = [None] * maxSize
        self.__semaphores = semaphores

    ##### Public methods #####  

    # Inserting an item in the buffer: for the producer
    def insert(self, item):
        # Checking invalid argument
        if not isinstance(item, int):
            raise TypeError("The argument 'item' must has an integer value.")
        
        # Blocks the producer if there aren't any empty slots
        self.__semaphores.getEmpty().acquire() # Decrement the number of empty slots

        # Enters the critical region or blocks the producer if another process is already in it
        # 'with' calls acquire and released for the mutex automatically
        with self.__semaphores.getMutex():
            self.__buffer[self.__in] = item # Insert the item into the buffer
            self.__in = (self.__in + 1) % self.__maxSize # Circular increment

        # Wakes up a consumer if there is one blocked trying to consume from the shared buffer when it was empty
        self.__semaphores.getFull().release() # Increment the number of filled slots    

    # Removing an item from the buffer: for the consumer
    def remove(self):
        
        # Blocks the consumer if there aren't any filled slots
        self.__semaphores.getFull().acquire()
        
        # Enters the critical region or blocks the consumer if another process is already in it
        with self.__semaphores.getMutex():
            item = self.__buffer[self.__out] # Remove the item
            self.__out = (self.__out + 1) % self.__maxSize # Circular increment
        
        # Wakes up a producer if there is one blocked trying to produce into the shared buffer when it was full
        self.__semaphores.getEmpty().release()

        return item