/*
9) Escreva uma classe Conjunto, que represente um conjunto de tamanho variável (crescimento de array por demanda) de elementos do tipo String. Escreva os seguintes membros para a classe:

a) Um método que permita adicionar um elemento para o conjunto (o elemento não pode existir no conjunto);

b) Um método que permita verificar se um dado elemento pertence ao conjunto;

c) Um método uniao, que retorne um novo conjunto de acordo com a semântica da operação união entre conjuntos (um novo conjunto, sem elementos repetidos, com a combinação dos elementos dos dois conjuntos originais: o que recebeu a mensagem e o que foi passado como parâmetro);

d) Um método inter, que retorne um novo conjunto de acordo com a semântica da operação interseção entre conjuntos (um novo conjunto, sem elementos repetidos, com os elementos que estejam nos dois conjuntos originais: o que recebeu a mensagem e o que foi passado como parâmetro);

e) Um método menos, que retorne um novo conjunto de acordo com a semântica da operação subtração entre conjuntos (um novo conjunto, sem elementos repetidos, com os elementos do conjunto que recebeu a mensagem, e que não existam no conjunto passado como parâmetro).
*/

class Set {
    // Private attributes
    #list = []

    constructor(list = []) {
        this.#list = [...list]
    }

    // Public methods
    // Add a new element to the set
    addElement(element) {
        if(typeof element !== "string") {
            console.log("The element must be a string type.")
            return
        }

        if(this.hasElement(element)) {
            console.log("The element is already in the set.")
            return
        }

        this.#list.push(element)
        console.log("Element added.")
    }

    // Check if the element is in the set
    hasElement(element) {
        return this.#list.includes(element)
    }

    // Union between two sets
    union(set) {
        if(!this.#isSet(set)) {
            console.log("The argument's type must be 'Set'.")
            return
        }

        // Create an independent copy of the list
        const newList = [...this.#list]

        for(const element of set.#list) {
            if(!newList.includes(element)) {
                newList.push(element)
            }
        }

        return new Set(newList)
    }

    // Intersection between two sets
    inter(set) {
        if(!this.#isSet(set)) {
            console.log("The argument's type must be 'Set'.")
            return
        }

        const newList = []

        for(const element of this.#list) {
            if(set.#list.includes(element)) {
                newList.push(element)
            }
        }

        return new Set(newList)
    }

    // Difference between two sets
    diff(set) {
        if(!this.#isSet(set)) {
            console.log("The argument's type must be 'Set'.")
            return
        }

        const newList = []

        for(const element of this.#list) {
            if(!set.#list.includes(element)) {
                newList.push(element)
            }
        }

        return new Set(newList)
    }

    // Print 
    display() {
        console.log(this.#list)
    }

    // Getters
    getElements() {
        return [...this.#list]
    }

    // Auxiliar methods
    #isSet(arg) {
        return arg instanceof Set
    }
}

export default Set