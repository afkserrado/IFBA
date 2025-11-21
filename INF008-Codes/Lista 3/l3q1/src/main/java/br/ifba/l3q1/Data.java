package br.ifba.l3q1;

// 'Data' as abstract class to avoid instantiation
// 'T' is a generic type parameter that represents the type of the stored data
abstract class Data<T> {
    protected T data;

    // Constructor
    public Data(T data) {
        this.data = data;
    }

    // Methods

    // Returns the stored data of type T
    protected T getData() {
        return data;
    }
}
