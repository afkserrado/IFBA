package br.ifba.l3q1;

// Concrete class
public class AnalyticsPipeline {
    // Default constructor
    //

    // Execute a single analysis
    public AnalysisResult executeAnalysis(Analyzer analyzer, Data<?> data) {
        
        // Returns processed data
        Data<?> processed = analyzer.analyze(data);

        // Wraps the processed Data in an AnalysisResult instance
        return new AnalysisResult(processed);
    }
}