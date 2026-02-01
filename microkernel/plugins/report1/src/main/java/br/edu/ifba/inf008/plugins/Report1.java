package br.edu.ifba.inf008.plugins;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.IDatabaseController;
import br.edu.ifba.inf008.interfaces.IPlugin;
import br.edu.ifba.inf008.interfaces.IUIController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Report1 implements IPlugin {

    // ========== ATRIBUTOS ==========

    private IDatabaseController db;
    private IUIController uiController;
    private VBox root;

    // ========== CONSTANTES ==========

    private static final String REPORT_QUERY = 
        """
        SELECT
            v.fuel_type,
            COUNT(*) AS vehicle_count,
            SUM(CASE WHEN v.status = 'AVAILABLE' THEN 1 ELSE 0 END) AS available_count,
            SUM(CASE WHEN v.status = 'RENTED' THEN 1 ELSE 0 END) AS rented_count,
            ROUND(COUNT(*) * 100.0 / SUM(COUNT(*)) OVER (), 2) AS fleet_percentage,
            CASE v.fuel_type
                WHEN 'GASOLINE' THEN '#FF6B6B'
                WHEN 'DIESEL' THEN '#4ECDC4'
                WHEN 'ELECTRIC' THEN '#45B7D1'
                WHEN 'HYBRID' THEN '#96CEB4'
                WHEN 'CNG' THEN '#FFEAA7'
                ELSE '#D9D9D9'
            END AS chart_color
        FROM vehicles v
        GROUP BY v.fuel_type
        ORDER BY vehicle_count DESC;
        """;

    @Override
    public boolean init() {

        // Obtém a instância do controlador da interface gráfica
        uiController = ICore.getInstance().getUIController();

        // Obtém a instância do controlador da base de dados
        db = ICore.getInstance().getDatabaseController();

        createReportTab();

        return true;
    }

    // Cria a estrutura visual da aba
    public void createReportTab() {
        
        Button btRefresh = new Button("Atualizar");
        btRefresh.setStyle("-fx-font-size: 24px;");

        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        PieChart chart = new PieChart(data);
        chart.setTitle("Distribuição de veículos por combustível");
        chart.setLegendVisible(false);

        Label statusLabel = new Label("Dados indisponíveis.\nClique em Atualizar para tentar novamente.");
        statusLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: #b00020;");
        statusLabel.setAlignment(Pos.CENTER);
        statusLabel.setMaxWidth(Double.MAX_VALUE);
        statusLabel.setVisible(false);   // começa escondido
        statusLabel.setManaged(false);   // não ocupa espaço quando escondido

        root = new VBox(10, btRefresh, chart, statusLabel);
        root.setPadding(new Insets(10));

        uiController.createTab("Relatório 1", root);

        btRefresh.setOnAction(e -> reloadChart(chart, data, statusLabel));
        reloadChart(chart, data, statusLabel);
    }

    // Carrega os dados do gráfico
    public void reloadChart(PieChart chart, ObservableList<PieChart.Data> data, Label statusLabel) {

        try {
            List<Map<String, Object>> rows = db.loadQuery(REPORT_QUERY);

            data.clear();

            for(Map<String, Object> row : rows) {
                String fuelType = String.valueOf(row.get("fuel_type"));
                Number count = (Number) row.get("vehicle_count");
                Number pct = (Number) row.get("fleet_percentage");

                String label = fuelType + " (" + pct.doubleValue() + "%)";
                data.add(new PieChart.Data(label, count.doubleValue()));
            }

            Platform.runLater(() -> applySliceColors(rows, data));
        } 

        catch (SQLException e) {

            data.clear();
            chart.setVisible(false);
            chart.setManaged(false);

            statusLabel.setVisible(true);
            statusLabel.setManaged(true);
        }
    }

    // Define as cores das fatias do gráfico
    private void applySliceColors(List<Map<String, Object>> rows, ObservableList<PieChart.Data> data) {
        
        for (int i = 0; i < rows.size() && i < data.size(); i++) {
            
            Object colorObj = rows.get(i).get("chart_color");
            if (colorObj == null) continue;

            String color = String.valueOf(colorObj);
            if (data.get(i).getNode() != null) {
                data.get(i).getNode().setStyle("-fx-pie-color: " + color + ";");
            }
        }
    }
}
