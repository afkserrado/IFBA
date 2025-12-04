package br.ifba;

// ### Concrete class
public class CheckingAccount {
    
    // ### Private fields
    private double balance;

    // ### Constructor
    public CheckingAccount(double balance) {
        this.balance = balance;
    }

    // ### Public methods
    //
    // Checking the balance
    public double getBalance() {
        return balance;
    }

    // Updating the balance
    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Adding money to the account
    public void deposit(double amount) {
        balance += amount;
    }

    // Withdraw money from the account
    public void withdraw(double amount) {
        // Checking account balance can't be less than 0
        if(balance < amount) {
            System.out.println("Insufficient funds. Available balance exceeded.");
            return;
        }

        balance -= amount;
    }

    // Updating the balance based on a given tax
    public void updateBalance(double tax) {
        balance *= (1 + tax/100);
    }
}


