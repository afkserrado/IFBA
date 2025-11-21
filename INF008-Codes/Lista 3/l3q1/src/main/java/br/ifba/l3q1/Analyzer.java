package br.ifba.l3q1;

interface Analyzer {
    // Public and abstract methods by default in interfaces

    // Receives a Data<?> object and returns a Data<?> object
    Data<?> analyze(Data<?> data);
}
