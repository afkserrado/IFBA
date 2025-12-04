package br.ifba.l3q1;

public class DatabaseDataSource implements IDataSource {

    private final String data;

    // Constructor
    public DatabaseDataSource(String data) {
        this.data = data;
    }

    @Override 
    public String getData() {
        return data;
    }
}
