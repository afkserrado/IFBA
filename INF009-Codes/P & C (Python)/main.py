import threading
from semaphores import Semaphores
from buffer import Buffer
from producer import Producer
from consumer import Consumer

def main():
    # Size of the buffer
    maxSize = 10 

    # Initializing semaphores
    semaphores = Semaphores(maxSize) 

    # Initializing buffer
    buffer = Buffer(maxSize, semaphores)

    # Initializing producer and consumer
    producer = Producer(buffer)
    consumer = Consumer(buffer)

    # Creating threads
    producer_thread = threading.Thread(target = producer.produce)
    consumer_thread = threading.Thread(target = consumer.consume)

    # Starting threads
    producer_thread.start()
    consumer_thread.start()

    # Keeping main alive
    producer_thread.join()
    consumer_thread.join()

# Execute main only if the archive is executed
if __name__ == "__main__":
    main()