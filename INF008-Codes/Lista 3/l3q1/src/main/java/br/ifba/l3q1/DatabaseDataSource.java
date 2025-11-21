package br.ifba.l3q1;

public class DatabaseDataSource extends Data<String> {
    
    // Constructor
    public DatabaseDataSource(String data) {
        super(data);
    }

    // Overrided methods
    @Override
    public String getData() {
        return this.data;
    }
}
