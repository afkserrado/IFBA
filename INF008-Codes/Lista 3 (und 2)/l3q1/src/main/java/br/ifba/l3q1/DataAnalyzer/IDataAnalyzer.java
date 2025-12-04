package br.ifba.l3q1.DataAnalyzer;

import br.ifba.l3q1.DataSource.IDataSource;

public interface IDataAnalyzer {
    // Public and abstract methods by default in interfaces

    // Receives an IDataSource object and returns a IDataSource object
    IDataSource analyze(IDataSource data);
}
