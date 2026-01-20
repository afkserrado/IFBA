package br.ifba.l3q1.DataSource;

public class APIDataSource implements IDataSource {

    private final String data;

    // Constructor
    public APIDataSource(String data) {
        this.data = data;
    }

    @Override 
    public String getData() {
        return data;
    }
}
