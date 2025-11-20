# Libraries
from semaphores import Semaphores

class DataBase:

    ##### Private attribute #####
    __rc = 0 # Count readers reading the data base

    ##### Constructors #####
    def __init__(self, semaphores = None):
        if semaphores is None:
            raise ValueError("A 'Semaphores' object must be provided.")
        
    


