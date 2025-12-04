package com.ifba.l1q1;

// Declaração da classe acessível apenas no package de origem
class Contact {
    // Atributos privados
    private String name;
    private String phone;
    private String email;

    // Construtores sobrecarregados
    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public Contact(String name, String phone, String email) {
        /*this.name = name;
        this.phone = phone;
        this.email = email;*/

        setName(name);
        setPhone(phone);
        setEmail(email);
    }

    // Getters: retorna o valor do atributo
    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    // Setters: atribui um valor ao atributo
    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Método displayInfo 1
    public void displayInfo() {
        System.out.println("Nome: " + name + " | Telefone: " + phone + " | E-mail: " + email);
    }

    // Método displayInfo 2
    public String displayInfo2() {
        return String.format("Nome: %s | Telefone: %s | E-mail: %s", name, phone, email);
    }

    // Método displayInfo 3
    public void displayInfo3() {
        System.out.printf("Nome: %s | Telefone: %s | E-mail: %s%n", name, phone, email);
    }

    public void updateContact(String phone, String email) {
        this.phone = phone;
        this.email = email;
    }
}
