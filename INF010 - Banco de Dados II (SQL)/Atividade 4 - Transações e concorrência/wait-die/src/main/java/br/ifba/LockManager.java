package br.ifba;

import java.util.HashMap;
import java.util.Map;

// Representa as operações de lock e unlock que uma transação pode fazer em um recurso compartilhado
class LockManager {
    
    // Representa a tabela de bloqueios, listando os recursos bloqueados e as transações correspondentes
    private final Map<String, Transaction> locks = new HashMap<>();

    // synchronized: 
        // Trava o método, garantindo que apenas uma thread por vez execute esse trecho de código
        // Garante exclsuão mútua e elimina condições de corrida
        // Impede que mais de uma thread tente manipular o objeto 'locks' ao mesmo tempo
    public synchronized void lock(String resource, Transaction t) throws InterruptedException {
        while (true) {

            // Transação bloqueante atual (se existir)
            Transaction holder = locks.get(resource);

            // Caso 1: recurso livre
            if (holder == null) {
                // Transação bloqueia o recurso
                locks.put(resource, t);
                System.out.println(t + " bloqueou " + resource);
                return;
            }

            // Caso 2: 't' já detém o lock do recurso
            if (holder == t) {
                return;
            }

            // Caso 3: conflito (concorrência)
            // wait-die
            if (t.timestamp < holder.timestamp) {
                // mais antiga → espera (loop garante rechecagem)
                System.out.println(t + " espera por " + holder + " em " + resource);
                wait(); // Bloqueia a thread até um notifyAll()
            } else {
                // mais nova → aborta
                System.out.println(t + " abortada ao acessar " + resource + " (ocupado por " + holder + ")");
                throw new AbortException();
            }
        }
    }

    public synchronized void unlock(String resource, Transaction t) {
        Transaction holder = locks.get(resource);
        if (holder != null && holder == t) {
            locks.remove(resource);
            System.out.println(t + " liberou " + resource);
            notifyAll();
        }
    }

    // Libera todos os locks de uma transação quando ela é abortada
    public synchronized void unlockAll(Transaction t) {     
        locks.entrySet().removeIf(e -> {
            if (e.getValue().id == t.id) {
                System.out.println(t + " liberou " + e.getKey() + " (cleanup)");
                return true;
            }
            return false;
        });
        notifyAll();
    }
}
