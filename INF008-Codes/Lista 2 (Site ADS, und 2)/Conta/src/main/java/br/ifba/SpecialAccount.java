package br.ifba;

// ### Concrete extended class
public class SpecialAccount extends CheckingAccount {
    
    // ### Added fields
    private double limit;

    // ### Constructor
    public SpecialAccount(double balance, double limit) {
        super(balance); // Calling superclass' constructor
        this.limit = limit;
    }

    // ### Public methods
    //
    // Withdraw money from the account
    @Override
    public void withdraw(double amount) {
        double balance = getBalance();
        if((limit + balance) < amount) {
            System.out.println("Insufficient funds. Available balance + limit exceeded.");
            return;
        }

        setBalance(balance -= amount);
    }
}
