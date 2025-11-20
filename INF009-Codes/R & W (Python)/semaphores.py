# Libraries
from threading import Lock, Semaphore

class Semaphore:

    ##### Constructors #####
    def __init__(self):
        self.__mutex = Lock()       # Controls the access to critical region
        self.__db = Semaphore(0)    # Controls the access to data base

    ##### Getters #####

    def getMutex(self):
        return self.__mutex
    
    def getDb(self):
        return self.__db