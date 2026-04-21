package br.ifba;

// Representa uma transação sendo executada de forma concorrente no sistema (thread)
class TransactionWorker implements Runnable {
    private final LockManager lm;
    private final Transaction t;

    // Limite de tentativas de acesso
    // Evita loop infinito e controla inanição
    private static final int MAX_RETRIES = 5; 

    public TransactionWorker(LockManager lm, Transaction t) {
        this.lm = lm;
        this.t = t;
    }

    // Método executado pela thread 
    @Override
    public void run() {
        int attempts = 0;

        while (attempts < MAX_RETRIES && !Thread.currentThread().isInterrupted()) {
            
            // Caso 1: lock bem-sucedido
            try {
                execute();
                System.out.println(t + " COMMIT");
                return;

            // Caso 2: abort (wait-die)
            // Transação mais nova tenta acessar recurso de uma mais antiga → é abortada e reiniciada
            } catch (AbortException e) {
                attempts++;
                System.out.println(t + " RESTART (" + attempts + ")");

                lm.unlockAll(t); // cleanup obrigatório

                // Thread que representa a transação mais nova é colocada para dormir temporariamente
                try {
                    Thread.sleep(200L * attempts); // backoff progressivo

                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    return;
                }

            // Caso 3: preserva o sinal de interrupção e encerra
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        System.out.println(t + " FINALMENTE ABORTADA (starvation control)");
    }

    private void execute() throws InterruptedException {
        boolean lockX = false;
        boolean lockY = false;

        try {
            lm.lock("X", t);
            lockX = true;

            // Simula tempo de processamento entre locks
            Thread.sleep(300);

            lm.lock("Y", t);
            lockY = true;

            Thread.sleep(300);

        } finally {
            if (lockY) lm.unlock("Y", t);
            if (lockX) lm.unlock("X", t);
        }
    }
}

// Explicando o InterruptedException: 
    // Uma thread pode entrar em wait() quando é uma transação mais antiga aguardando um recurso.
    // Ela será acordada por notifyAll() quando o recurso for liberado.

    // Uma thread pode entrar em sleep() após ser abortada, para implementar backoff antes de tentar novamente.

    // Tanto wait() quanto sleep() podem lançar InterruptedException quando outra thread chama interrupt().

    // Inicialmente, o sinal interrupted = false (estado padrão da thread).
    // Estar em wait() ou sleep() não depende do valor desse sinal.

    // O interrupt() não “mata” a thread: ele é apenas um sinal de que a thread deve parar.
    // O interrupt() muda o interrupted para true.

    // Se a thread estiver em wait() ou sleep(), o interrupt() faz com que ela seja acordada por meio do lançamento de InterruptedException.

    // Quando a thread está bloqueada (em wait() ou sleep()) e recebe um interrupt(), o Java:
    // - acorda a thread
    // - lança InterruptedException
    // - limpa o sinal de interrupção (de true para false)

    // Por isso, ao capturar InterruptedException, chamamos:
        // Thread.currentThread().interrupt();
        // para restaurar o sinal de interrupção (de false para true),
        // garantindo que o pedido de interrupção não seja perdido
        // e que código externo ainda possa detectá-lo