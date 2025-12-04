package br.ifba.l3q1;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;

// Concrete class
public class AnalyticsPipeline {
    // Default constructor
    
    // Executes a single analysis
    public AnalysisResult executeAnalysis(IDataAnalyzer analyzer, IDataSource data) {
        
        // Returns processed data
        IDataSource processed = analyzer.analyze(data);

        // Wraps the processed Data in an AnalysisResult instance
        return new AnalysisResult(processed);
    }

    // Executes a batch analysis
    public List<AnalysisResult> executeBatchAnalysis(Map<IDataAnalyzer, IDataSource> jobs) {
        List<AnalysisResult> results = new ArrayList<>();

        // Go through the Map and analyze each analyzer-data pair
        for(Map.Entry<IDataAnalyzer, IDataSource> entry: jobs.entrySet()) {
            results.add(executeAnalysis(entry.getKey(), entry.getValue()));
        }

        // Returns the list of results
        return results;
    }

    
    // Generates a comparative report for a list of analysis algorithms using the same data source
    public ComparativeReport generateComparativeReport(List<IDataAnalyzer> analysisList, IDataSource data) {
        // Create a map to pair each analyzer with the data source
        Map<IDataAnalyzer, IDataSource> analysisJobs = new LinkedHashMap<>();

        // Add each analyzer-data pair to the map
        for (IDataAnalyzer analysis : analysisList) {
            analysisJobs.put(analysis, data);
        }

        // Execute batch analysis for all analyzer-data pairs
        List<AnalysisResult> results = executeBatchAnalysis(analysisJobs);

        // Returns a ComparativeReport containing all results
        return new ComparativeReport(results);
    }
}