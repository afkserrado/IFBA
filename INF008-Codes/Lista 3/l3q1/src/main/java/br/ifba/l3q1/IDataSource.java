package br.ifba.l3q1;

// 'Data' as abstract class to avoid instantiation
// 'T' is a generic type parameter that represents the type of the stored data
public abstract class IDataSource {
    private String data;

    // Constructor
    public IDataSource(String data) {
        // No need to validate the data type. Any incorrect type is caught at compile time
        this.data = data;
    }

    // Methods

    // Returns the stored data of type T
    public String getData() {
        return data;
    }
}
