package br.ifba.l3q1;

// 'D' is any subclass of Data
interface Analyzer<D extends Data<?>> {
    // Public and static methods by default in interfaces

    // Receives a D (a Data subclass) and returns a D
    D analyze(D data);
}
