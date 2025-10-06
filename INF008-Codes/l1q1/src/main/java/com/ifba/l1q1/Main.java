package com.ifba.l1q1;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Contact contato1 = new Contact("Anderson", "(71) 91234-5678", "anderson@gmail.com");
        contato1.getName();
        contato1.displayInfo();
        contato1.updateContact("(75) 91234-0000", "kleyson@gmail.com");
        contato1.displayInfo();
    }
}