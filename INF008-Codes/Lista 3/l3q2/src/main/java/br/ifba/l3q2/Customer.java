package br.ifba.l3q2;

public class Customer {
    
    //
    // Fields
    //
    private String CPF;
    private String name;

    //
    // Constructors
    //
    public Customer(String CPF, String name) {
        this.CPF = CPF.replaceAll("\\D", "");
        this.name = name;
    }

    //
    // Methods
    //
    // Overloading toString method
    @Override
    public String toString() {
        return name + " (CPF: " + CPF + ")";
    }
}
