package com.ifba.l1q1;

public class Main {
    public static void main(String[] args) {

        // Testando a classe Contact
        /*
        Contact contato1 = new Contact("Anderson", "(71) 91234-5678", "anderson@gmail.com");
        contato1.getName();
        contato1.displayInfo();
        contato1.updateContact("(75) 91234-0000", "kleyson@gmail.com");
        contato1.displayInfo();
        */

        // Testando AdressBook
        // Criando contatos
        Contact contato1 = new Contact("Ana", "99999-0001", "ana@email.com");
        Contact contato2 = new Contact("Bruno", "99999-0002", "bruno@email.com");
        Contact contato3 = new Contact("Carlos", "99999-0003", "carlos@email.com");

        AdressBook contatos = new AdressBook();
        contatos.addContact(contato1);
        contatos.addContact(contato2);
        contatos.addContact(contato3);

        System.out.println("Buscando contatos: ");
        contatos.findContact("Ana");
        contatos.findContact("Thiago");

        System.out.println("\nListando contatos: ");
        contatos.listContacts();
    }
}