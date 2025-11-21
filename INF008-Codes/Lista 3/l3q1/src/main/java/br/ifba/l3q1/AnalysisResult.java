package br.ifba.l3q1;

public class AnalysisResult {
    
    // 'processedData' stores the output of the analysis
    // It is final because the analysis result should be immutable 
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
