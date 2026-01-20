# Libraries
from threading import Lock, Semaphore

class Semaphores:

    ##### Constructors ##### 

    def __init__(self, maxSize):
        self.__mutex = Lock() # Mutual exclusion, initially unlocked
        self.__empty = Semaphore(maxSize) # Semaphore for empty slots
        self.__full = Semaphore(0) # Semaphore for filled slots

    ##### Getters ##### 
    def getMutex(self):
        return self.__mutex
    
    def getEmpty(self):
        return self.__empty
    
    def getFull(self):
        return self.__full
