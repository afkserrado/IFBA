package br.ifba;

public class Main {
    public static void main(String[] args) {
        LockManager lm = new LockManager();

        Transaction t1 = new Transaction(1, 1);
        Transaction t2 = new Transaction(2, 2);

        new Thread(new TransactionWorker(lm, t1)).start();
        new Thread(new TransactionWorker(lm, t2)).start();
    }
}