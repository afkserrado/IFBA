/*
Questão 1: Sistema de Análise de Dados com Múltiplos Algoritmos e Fontes 

Implemente um framework de análise de dados que suporte diferentes algoritmos e fontes de dados através de hierarquias independentes. O sistema deve permitir combinar algoritmos e fontes dinamicamente. Novas implementações devem ser suportadas sem alterações nos métodos executeAnalysis(), executeBatchAnalysis() e generateComparativeReport().
*/

package br.ifba.l3q1;
import java.util.Map;

// Imports de subpacotes do projeto
import br.ifba.l3q1.DataAnalyzer.*;
import br.ifba.l3q1.DataSource.*;
import br.ifba.l3q1.Utilities.*;

// Imports Java padrão
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Arrays;

public class DataAnalyticsApp {
    public static void main(String[] args) {
        AnalyticsPipeline pipeline = new AnalyticsPipeline();
        
        // Diferentes combinações de algoritmos e fontes
        AnalysisResult result1 = pipeline.executeAnalysis(
            new MLRegressionAnalyzer(),
            new DatabaseDataSource("sales_db")
        );
        
        AnalysisResult result2 = pipeline.executeAnalysis(
            new StatisticalAnalyzer(),
            new FileDataSource("data.csv")
        );
        
        AnalysisResult result3 = pipeline.executeAnalysis(
            new NeuralNetworkAnalyzer(),
            new APIDataSource("https://api.data.com")
        );
        
        // Processamento em lote com múltiplas combinações
        Map<IDataAnalyzer, IDataSource> analysisJobs = new LinkedHashMap<>();
        analysisJobs.put(
            new MLRegressionAnalyzer(),
            new DatabaseDataSource("db1")
        );
        analysisJobs.put(
            new StatisticalAnalyzer(),
            new FileDataSource("file1.csv")
        );
        
        List<AnalysisResult> batchResults = pipeline.executeBatchAnalysis(analysisJobs);
        
        // Análise comparativa
        ComparativeReport report = pipeline.generateComparativeReport(
            Arrays.asList(
                new MLRegressionAnalyzer(),
                new StatisticalAnalyzer()
            ),
            new DatabaseDataSource("comparison_db")
        );
    }
}