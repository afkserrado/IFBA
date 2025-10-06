package com.ifba.l1q1;

class AdressBook {
    // Array para armazenar os contatos
    Contact[] contacts = new Contact[10];

    // Contador de contatos
    private int count = 0;

    // Construtor padrão vazio
    public AdressBook() {}

    // Adicionar contato
    public void addContact(Contact contact) {
        if(count < 10) {
            this.contacts[count] = contact;
            count++;
        }
        else {
            System.out.println("A lista de contatos está cheia.");
        }
    }

    // Buscar contato por nome
    public void findContact(String name) {
        for(int i = 0; i < contacts.length; i++) {
            // Verifica se é vazio
            if (contacts[i] == null) break;
            
            // Compara duas strings, ignorando capitalização
            if(contacts[i].getName().equalsIgnoreCase(name)) {
                contacts[i].displayInfo();
                return;
            }
        }
        System.out.println("Contato não encontrado."); // Não encontrado
    }

    // Listar contatos
    public void listContacts() {
        for(int i = 0; i < contacts.length; i++) {
            if (contacts[i] == null) break;

            System.out.println("Nome: " + contacts[i].getName() + " | Telefone: " + contacts[i].getPhone() + " | E-mail: " + contacts[i].getEmail());
        }
    }
}
