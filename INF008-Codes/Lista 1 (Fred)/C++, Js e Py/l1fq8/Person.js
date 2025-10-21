/*
8) Escreva uma classe Pessoa que representa uma pessoa numa árvore genealógica.
A pessoa possui um nome, um pai e uma mãe (que também são pessoas).

Forneça os seguintes membros para a classe:

a) Construtores sobrecarregados que:
  i) inicializem o nome da pessoa, bem como seus antecessores (pai e mãe);
  ii) inicializem o nome da pessoa e coloquem seus antecessores como null.

b) Um método que verifique a igualdade semântica entre duas pessoas
(as pessoas são iguais se possuem o mesmo nome e a mesma mãe).

c) Um método que verifique se duas pessoas são irmãs.

d) Um método que verifique se uma pessoa é antecessora da pessoa que recebeu a mensagem
(ou seja, é seu pai, sua mãe, ou antecessor do pai ou da mãe).
*/

// Revisar
// Verificar se otherIndividual é Person

class Person {
    // Atributos privados
    #name
    #father
    #mother
    
    constructor(name, father = null, mother = null) {
        this.#setName(name)
        this.#setFather(father)
        this.#setMother(mother)
    }

    areEquals(otherIndividual) {
        if (this.getName() === otherIndividual.getName() && this.getMother() === otherIndividual.getMother()) {
            console.log("Both individuals are the same.")
        }
        else {console.log("The individuals are differents.")}
    }

    areSiblings(otherIndividual) {
        if (this.getFather() === otherIndividual.getFather() || this.getMother() === otherIndividual.getMother()) {
            console.log("The individuals are siblings.")
        }
        else {console.log("The individuals aren't siblings.")}
    }

    isAncestor(otherIndividual) {
        if (
            this.getFather().getName() === otherIndividual.getName() ||             // Father
            this.getMother().getName() === otherIndividual.getName() ||             // Mother
            this.getFather().getFather().getName() === otherIndividual.getName() || // Grandfather
            this.getMother().getMother().getName() === otherIndividual.getName()    // Grandmother
        ) {
            console.log(`${otherIndividual.getName()} is ancestor of ${this.getName()}`)
        }
        
    }

    // Getters
    getName() {
        return this.#name
    }

    getFather() {
        return this.#father // father is an object
    }

    getMother() {
        return this.#mother // mother is an object
    }

    // Setters
    #setName(name) {
        // Remove consecutive spaces
        name = name.trim().replace(/\s+/g, " ")

        if (!this.#validateName(name)) {
            throw new Error("The name must be a string and can only have alphabetical characters and spaces.")
        }
        
        this.name = name
    }

    #setFather(father) {
        if (!this.#isPerson(father) || father !== null) {
            throw new Error("Father's type must be 'Person'.")
        }
        this.#father = father // father is an object
    }

    #setMother(mother) {
        if (!this.#isPerson(mother) || mother !== null) {
            throw new Error("Mother's type must be 'Person'.")
        }
        this.#mother = mother // mother is an object
    }

    // Auxiliar methods
    // Check if a name contains only alphabetical characters or non-consecutive spaces
    #validateName(name) {
        if (typeof name !== "string" || !this.#justAlphabetical(name)) {
            return false
        }
        return true
    }

    // Check if a name contains only alphabetical characters
    #justAlphabetical(name) {
        return /^[A-Za-zÀ-ÖØ-öø-ÿ\s]+$/.test(name);
    }

    #isPerson(person) {
        return person instanceof Person
    }
}

