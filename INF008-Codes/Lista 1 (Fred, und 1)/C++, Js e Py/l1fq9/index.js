import Set from './Set.js'

// ===== Criando conjuntos =====
const setA = new Set(['apple', 'banana', 'cherry'])
const setB = new Set(['banana', 'dragonfruit', 'elderberry'])

// ===== Testando addElement =====
console.log("=== Testando addElement ===")
setA.addElement('fig')         // válido
setA.addElement('banana')      // duplicado, deve avisar
setA.addElement(123)           // tipo errado, deve avisar

console.log("setA elements:", setA.getElements())  // mostrar elementos corretamente

// ===== Testando hasElement =====
console.log("=== Testando hasElement ===")
console.log("setA has 'apple':", setA.hasElement('apple'))   // true
console.log("setA has 'grape':", setA.hasElement('grape'))   // false

// ===== Testando union =====
console.log("=== Testando union ===")
const unionSet = setA.union(setB)
console.log("unionSet elements:", unionSet.getElements())

// ===== Testando inter (interseção) =====
console.log("=== Testando inter ===")
const interSet = setA.inter(setB)
console.log("interSet elements:", interSet.getElements())

// ===== Testando diff (diferença) =====
console.log("=== Testando diff ===")
const diffSet = setA.diff(setB)
console.log("diffSet elements:", diffSet.getElements())

// ===== Testando erros =====
console.log("=== Testando casos de erro ===")
console.log("Passando valor não-Set para union:")
setA.union('not a set')   // deve avisar
console.log("Passando valor não-Set para inter:")
setA.inter(123)           // deve avisar
console.log("Passando valor não-Set para diff:")
setA.diff({})             // deve avisar

// ===== Testando independência de conjuntos =====
console.log("=== Testando independência de conjuntos ===")
const setC = new Set(['apple', 'banana'])
const setD = setC.union(new Set(['banana', 'kiwi']))
console.log("setC elements (original):", setC.getElements()) // deve ser ['apple', 'banana']
console.log("setD elements (novo):", setD.getElements())     // deve incluir 'kiwi'
