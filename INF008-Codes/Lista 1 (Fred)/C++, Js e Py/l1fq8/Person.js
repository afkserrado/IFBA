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

class Person {
    // Private attributes
    #name
    #father
    #mother
    
    // Constructor
    constructor(name, father = null, mother = null) {
        this.#setName(name)
        this.#setFather(father)
        this.#setMother(mother)
    }

    // Public methods
    // Checks if another Person object is semantically equal to this one
    areEquals(otherIndividual) {        
        // Check if the argument is a Person object
        if(!this.#isPerson(otherIndividual)) return false
        
        return this.getName() === otherIndividual.getName() && this.getMother() === otherIndividual.getMother()
    }

    // Checks if another Person object is a sibling of this one
    areSiblings(otherIndividual) {
        // Check if the argument is a Person object
        if(!this.#isPerson(otherIndividual)) return false

        // Check if this object and otherIndividual are the same
        if(this === otherIndividual) return false
        
        return this.getFather() === otherIndividual.getFather() || this.getMother() === otherIndividual.getMother()
    }

    // Checks if another Person object is an ancestor of this one
    isAncestor(otherIndividual) {
        // Check if the argument is a Person object
        if(!this.#isPerson(otherIndividual)) return false

        // Check father's ancestry recursively
        if (this.#father) {
            if(this.#father === otherIndividual) return true
            if(this.#father.isAncestor(otherIndividual)) return true
        }

        // Check mother's ancestry recursively
        if (this.#mother) {
            if(this.#mother === otherIndividual) return true
            if(this.#mother.isAncestor(otherIndividual)) return true
        }

        // If not found, return false
        return false
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
        
        this.#name = name
    }

    #setFather(father) {
        if (!this.#isPerson(father) && father !== null) {
            throw new Error("Father's type must be 'Person'.")
        }
        this.#father = father // father is an object
    }

    #setMother(mother) {
        if (!this.#isPerson(mother) && mother !== null) {
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

