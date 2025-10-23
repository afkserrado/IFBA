/*
10) Crie uma classe Matriz que represente uma matriz matemática.

Forneça um construtor que permita a inicialização das dimensões da matriz.

Forneça métodos para acesso (leitura/escrita) de cada elemento da matriz.

Forneça os métodos adequados para as seguintes operações com matriz:

a) Comparação semântica da matriz.

b) Retornar a transposta (é aquela onde as linhas se transformam em colunas e as colunas em linhas) da matriz.

c) Retornar a oposta (é aquela onde todos os elementos possuem sinais trocados) da matriz.

d) Gerar uma matriz nula (é aquela onde todos os elementos são iguais a 0).

e) Informar se a matriz é identidade (matriz quadrada onde os elementos da diagonal principal são todos iguais a 1 e os demais 0).

f) Informar se a matriz é diagonal (matriz quadrada onde os elementos fora da diagonal principal são todos iguais a 0).

g) Informar se a matriz é singular (matriz diagonal onde os elementos da diagonal principal são todos iguais).

h) Informar se a matriz é simétrica (uma matriz quadrada é dita simétrica se ela é igual à sua transposta).

i) Informar se a matriz é anti-simétrica (uma matriz quadrada é dita anti-simétrica se sua oposta é igual à sua transposta).

j) Adicionar duas matrizes (alterando o valor da que recebeu a mensagem).

k) Subtrair duas matrizes (alterando o valor da que recebeu a mensagem).

l) Multiplicar duas matrizes (alterando o valor da que recebeu a mensagem).

m) Gerar uma cópia da matriz.
*/

class Matrix {
    // Private attributes
    #lin
    #col
    #matrix

    // Construtores
    constructor(lin, col) {
        this.#setSize(lin, col)
        this.#matrix = Array.from(Array(lin), () => Array(col).fill(0))
    }

    // Public methods
    setElement(i, j, n) {
        if(!this.#validateIndexes(i, j) || !this.#isInteger(n)) {
            throw new Error("Invalid parameters.")
        }

        this.#matrix[i][j] = n
    }

    getElement(i, j) {
        if(!this.#validateIndexes(i, j)) {
            throw new Error("Invalid parameters.")
        }

        return this.#matrix[i][j]
    }

    areEquals(matrix) {
        if(!this.#isMatrix(matrix)) {
            throw new Error("The paramater must be an object from 'Matrix' class.")
        }

        if(this.#lin !== matrix.#lin || this.#col !== matrix.#col) return false

        for(let i = 0; i < this.#lin; i++) {
            for(let j = 0; j < this.#col; j++) {
                if(this.#matrix[i][j] !== matrix.#matrix[i][j]) return false
            }
        }

        return true
    }

    // ✅ Passing a Matrix as a parameter → still inside the class → allowed.
    // 🚫 Creating a new Matrix inside a method → not in lexical scope → not allowed.
    transpose() {
        const transMatrix = new Matrix(this.#col, this.#lin)

        for(let i = 0; i < this.#lin; i++) {
            for(let j = 0; j < this.#col; j++) {
                transMatrix.setElement(j, i, this.#matrix[i][j])
            }
        }

        return transMatrix
    }

    opposite() {
        const oppMatrix = new Matrix(this.#lin, this.#col)

        for(let i = 0; i < this.#lin; i++) {
            for(let j = 0; j < this.#col; j++) {
                oppMatrix.setElement(i, j, -1 * this.#matrix[i][j])
            }
        }

        return oppMatrix
    }

    nullMatrix() {
        return new Matrix(this.#lin, this.#col)
    }

    identity() {
        if(!this.#isSquare()) return false
        
        for(let i = 0; i < this.#lin; i++) {
            for(let j = 0; j < this.#col; j++) {
                if(i === j && this.#matrix[i][j] !== 1) return false // diagonal
                if(i !== j && this.#matrix[i][j] !== 0) return false // off-diagonal
            }
        }

        return true
    }

    #checkDiagonal(strict = false) {
        if(!this.#isSquare()) return false

        const firstDiag = this.#matrix[0][0]

        for(let i = 0; i < this.#lin; i++) {
            for(let j = 0; j < this.#col; j++) {
                if(i !== j && this.#matrix[i][j] !== 0) return false // off-diagonal
                if(strict && i === j && this.#matrix[i][j] !== firstDiag) return false // diagonal
            }
        }

        return true
    }

    diagonal() {
        return this.#checkDiagonal(false) // just off-diagonal = 0
    }

    singular() {
        return this.#checkDiagonal(true) // off-diagonal = 0, diagonal all equal
    }

    symmetric() {
        if(!this.#isSquare()) return false

        const transMatrix = this.transpose()
        return this.areEquals(transMatrix)
    }

    antiSymmetric() {
        if(!this.#isSquare()) return false

        const oppMatrix = this.opposite()
        const transMatrix = this.transpose()
        return oppMatrix.areEquals(transMatrix)
    }

    // ####################################################################
    // Setters
    #setSize(lin, col) {
        if(!this.#validateIndexes(lin, col)) {
            throw new Error("Invalid parameters.")
        }

        this.#lin = lin
        this.#col = col
    }

    // ####################################################################
    // Auxiliar methods
    #validateSize(lin, col) {
        // true if is valid
        return !this.#isNegative(lin, col) && this.#isInteger(lin, col)
    }

    #validateIndexes(i, j) {
        // true if is valid
        return this.#validateSize(i, j) && !this.#isOffLimits(i, j)
    }

    #isNegative(i, j) {
        // true if is negative. We want false
        return i < 0 || j < 0
    }

    #isInteger(...numbers) {
        // true if is integer. We want true
        for (const number of numbers) {
            if(!Number.isInteger(number)) return false
        }

        return true
    }

    #isOffLimits(i, j) {
        // true if is off limits. We want false
        return i >= this.#lin || j >= this.#col
    }

    #isMatrix(matrix) {
        return matrix instanceof Matrix
    }

    #isSquare() {
        return this.#lin === this.#col
    }
}

export default Matrix