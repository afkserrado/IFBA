package br.ifba.l3q1;

public class FileDataSource extends Data<String> {
    
    // Constructor
    public FileDataSource(String data) {
        super(data);
    }

    // Overrided methods
    @Override
    public String getData() {
        return this.data;
    }
}
