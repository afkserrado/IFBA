# Libraries
from buffer import Buffer
from time import sleep

class Consumer:

    ##### Constructors #####
    def __init__(self, buffer = None):
        if buffer is None:
            raise ValueError("A 'Buffer' object must be provided.")
        
        # Creating the object
        self.__buffer = buffer

    ##### Public methods #####

    def consume(self):
        while True:
            item = self.__buffer.remove() # Remove the item from the buffer
            print(f"Product consumed: {item}")

            # Simulating consumption time
            sleep(1) # Pause the consumer for 1 s
