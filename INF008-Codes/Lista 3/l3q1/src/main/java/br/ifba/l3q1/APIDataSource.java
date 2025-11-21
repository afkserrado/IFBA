package br.ifba.l3q1;

public class APIDataSource extends Data<String> {
    
    // Constructor
    public APIDataSource(String data) {
        super(data);
    }

    // Overrided methods
    @Override
    public String getData() {
        return this.data;
    }
}
