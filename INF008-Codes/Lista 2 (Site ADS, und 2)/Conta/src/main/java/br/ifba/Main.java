/*
1) Escreva em java uma classe ContaCorrente, que armazene o saldo da conta.
a) Forneça um construtor que inicialize o saldo inicial da Conta.
b) Forneça métodos para consultar o saldo, depositar e sacar. 
c) Forneça um método que permita atualizar o valor do saldo de acordo com uma taxa informada.

2) Crie duas subclasses da classe ContaCorrente: ContaEspecial e ContaPoupanca.
Para a classe ContaEspecial acrescente um atributo que represente o limite desta conta. 
a) Forneça um construtor que inicialize além do saldo, inicial o limite da Conta.
b) Reescreva o método sacar, de modos a permitir que a ContaEspecial possa ficar com saldo negativo, desde que dentro do limite.

Para a classe ContaPoupanca. 

a) Forneça um construtor que inicialize saldo inicial da Conta.
b) Reescreva o método que permite atualizar o valor do saldo de forma a atualização ser feita com o dobro do valor da taxa informada.

3) Escreva uma classe (representando um cenário de uma aplicação) que crie objeto conta de cada tipo e os armazene no mesmo vetor. Inicialize as contas com 100 e o limite da especial com 50. Deposite 200 em cada uma, corrija o saldo com 10% e saque 310 reais. Imprima o saldo de cada uma das contas e avalie se o valor corresponde ao esperado.
*/

package br.ifba;

// ### Imports
import java.util.ArrayList;

public class Main {
   
    public static void main(String[] args) {
    
        // Creating polymorphic reference, pointing to an initialized empty array list
        ArrayList<CheckingAccount> accounts = new ArrayList<>();

        // Adding accounts to the accounts array list field
        accounts.add(new CheckingAccount(100));
        accounts.add(new SpecialAccount(100, 50));
        accounts.add(new SavingsAccount(100));

        // Manipulating each account's balance by late binding
        for(CheckingAccount account : accounts) {
            account.deposit(200);
            account.updateBalance(10);
            account.withdraw(310);
            System.out.println(account.getClass().getSimpleName() + " = " + account.getBalance());
        }   
        
        /*
        Saída:
        CheckingAccount = 20.0
        SpecialAccount = 20.0
        SavingsAccount = 50.0
        */
    }
}
