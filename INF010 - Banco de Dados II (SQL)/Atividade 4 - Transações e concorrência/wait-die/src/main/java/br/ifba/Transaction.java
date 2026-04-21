package br.ifba;

// Representa as transações do sistema, que disputarão os recursos compartilhados
class Transaction {
    final int id;
    final long timestamp; // Define a ordem de prioridade

    public Transaction(int id, long timestamp) {
        this.id = id;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "T" + id + "(TS=" + timestamp + ")";
        // Exemplo: T1(TS=1)
    }
}
