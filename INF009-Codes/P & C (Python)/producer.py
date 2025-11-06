# Libraries
from buffer import Buffer
from random import randint
from time import sleep

class Producer:

    ##### Constructors #####
    def __init__(self, buffer = None):
        if buffer is None:
            raise ValueError("A 'Buffer' object must be provided.")
        
        # Creating the object
        self.__buffer = buffer

    ##### Public methods #####

    def produce(self):
        while True:
            item = randint(0, 999) # Produce the item
            print(f"Product produced: {item}")
            self.__buffer.insert(item) # Insert the item

            # Simulating production time
            sleep(1) # Pause the producer for 1 s
