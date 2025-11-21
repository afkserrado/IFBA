package br.ifba.l3q1;

public class AnalysisResult {
    
    // 'processedData' stores the output of the analysis.
    // It is final because the analysis result should be immutable 
    private final Data<?> processedData;

    // Constructor
    public AnalysisResult(Data<?> processedData) {
        this.processedData = processedData;
    }

    // Getters

    // Returns the processed data
    public Data<?> getProcessedData() {
        return processedData;
    }
}
