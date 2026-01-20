package br.ifba.l3q1.DataSource;

public class FileDataSource implements IDataSource {
    
    private final String data;

    // Constructor
    public FileDataSource(String data) {
        this.data = data;
    }

    @Override 
    public String getData() {
        return data;
    }
}
