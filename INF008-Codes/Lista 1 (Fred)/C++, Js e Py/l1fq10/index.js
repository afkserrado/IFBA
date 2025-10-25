import Matrix from './Matrix.js';

function main() {
    // Criando matrizes 3x3
    const A = new Matrix(3, 3);
    const B = new Matrix(3, 3);

    // Preenchendo A
    let counter = 1;
    for (let i = 0; i < 3; i++) {
        for (let j = 0; j < 3; j++) {
            A.setElement(i, j, counter++);
        }
    }

    // Preenchendo B
    counter = 9;
    for (let i = 0; i < 3; i++) {
        for (let j = 0; j < 3; j++) {
            B.setElement(i, j, counter--);
        }
    }

    console.log("Matrix A:");
    A.print();
    console.log()
    console.log("Matrix B:");
    B.print();
    console.log()

    // Comparação
    console.log("A equals B?", A.areEquals(B));
    console.log()

    // Transposta
    console.log("Transpose of A:");
    A.transpose().print();
    console.log()

    // Oposta
    console.log("Opposite of A:");
    A.opposite().print();
    console.log()

    // Matriz nula
    console.log("Null matrix same size as A:");
    A.nullMatrix().print();
    console.log()

    // Identidade
    const I = new Matrix(3, 3);
    for (let i = 0; i < 3; i++) I.setElement(i, i, 1);
    I.print()
    console.log()
    console.log("I is identity?", I.identity());

    // Diagonal
    console.log("I is diagonal?", I.diagonal());

    // Singular
    console.log("I is singular?", I.singular());

    // Simétrica
    console.log("I is symmetric?", I.symmetric());

    // Anti-simétrica
    const anti = new Matrix(3, 3);
    anti.setElement(0, 1, 2);
    anti.setElement(1, 0, -2);
    console.log("Anti is anti-symmetric?", anti.antiSymmetric());
    console.log()

    // Soma
    const A_sum = A.copy();
    A_sum.sum(B);
    console.log("A + B:");
    A_sum.print();
    console.log()

    // Subtração
    const A_sub = A.copy();
    A_sub.sub(B);
    console.log("A - B:");
    A_sub.print();
    console.log()

    // Produto
    const C = A.copy();
    C.prod(B);
    console.log("A * B:");
    C.print();
    console.log()

    // Cópia
    const A_copia = A.copy();
    console.log("Copy of A:");
    A_copia.print();
}

main();
