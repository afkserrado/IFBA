package br.ifba.l3q1;

import java.util.List;

// Represents a comparative report containing results from multiple analyses
public class ComparativeReport {
    
    private final List<AnalysisResult> analysisList;

    // Constructor
    public ComparativeReport(List<AnalysisResult> analysisList) {
        this.analysisList = analysisList;
    }

    // Getters

    // Returns the analysis results
    public List<AnalysisResult> getComparativeReport() {
        return analysisList;
    }
}
