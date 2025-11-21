package br.ifba.l3q1;

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
