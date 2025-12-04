// index.js
import Person from './Person.js'

// --- Criando pessoas ---

// Avós
const grandFather = new Person("João")
const grandMother = new Person("Maria")

// Pais
const father = new Person("Carlos", grandFather, grandMother)
const mother = new Person("Ana")

// Filhos
const child1 = new Person("Pedro", father, mother)
const child2 = new Person("Paula", father, mother)
const child3 = new Person("Pedro", father, mother) // Mesmo nome que child1

// --- Testando areEquals ---
console.log("child1 areEquals child2:", child1.areEquals(child2)) // false
console.log("child1 areEquals child3:", child1.areEquals(child3)) // true (mesmo nome e mãe)

// --- Testando areSiblings ---
console.log("child1 areSiblings child2:", child1.areSiblings(child2)) // true
console.log("child1 areSiblings child3:", child1.areSiblings(child3)) // true (mesmo pai e mãe)
console.log("child1 areSiblings father:", child1.areSiblings(father))   // false

// --- Testando isAncestor ---
console.log("grandFather isAncestor child1:", child1.isAncestor(grandFather)) // true
console.log("grandMother isAncestor child1:", child1.isAncestor(grandMother)) // true
console.log("father isAncestor child1:", child1.isAncestor(father))           // true
console.log("mother isAncestor child1:", child1.isAncestor(mother))           // true
console.log("child1 isAncestor grandFather:", grandFather.isAncestor(child1)) // false
console.log("child2 isAncestor child3:", child2.isAncestor(child3))           // false
