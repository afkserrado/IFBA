package br.ifba.l3q1;

interface IDataAnalyzer {
    // Public and abstract methods by default in interfaces

    // Receives a IDataSource object and returns a IDataSource object
    IDataSource analyze(IDataSource data);
}
