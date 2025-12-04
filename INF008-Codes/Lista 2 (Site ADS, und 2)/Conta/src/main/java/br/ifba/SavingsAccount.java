package br.ifba;

// ### Concrete extended class 
public class SavingsAccount extends CheckingAccount {
    
    // ### Constructor
    public SavingsAccount(double balance) {
        super(balance);
    }

    // ### Public methods
    //
    // Updating the balance based on a given tax
    @Override
    public void updateBalance(double tax) {
        super.updateBalance(tax * 2); // Calling superclass' method
    }
}
