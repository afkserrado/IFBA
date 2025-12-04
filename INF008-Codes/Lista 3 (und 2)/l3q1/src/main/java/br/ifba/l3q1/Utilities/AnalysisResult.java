package br.ifba.l3q1.Utilities;

import br.ifba.l3q1.DataSource.IDataSource;

public class AnalysisResult {
    
    // Stores the processed data of an analysis 
    private final IDataSource processedData;

    // Constructor
    public AnalysisResult(IDataSource processedData) {
        this.processedData = processedData;
    }

    // Getters

    // Returns the analysis result
    public IDataSource getProcessedData() {
        return processedData;
    }
}
