package br.edu.ifba.inf008.plugins;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.IDatabaseController;
import br.edu.ifba.inf008.interfaces.IPlugin;
import br.edu.ifba.inf008.interfaces.IUIController;
import br.edu.ifba.inf008.plugins.helpers.RentalsReportReadDTO;
import br.edu.ifba.inf008.plugins.helpers.Report2Columns;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class Report2 implements IPlugin {

    private IDatabaseController db;
    private IUIController uiController;

    private VBox root;
    private TableView<RentalsReportReadDTO> table;
    private ObservableList<RentalsReportReadDTO> rows;

    private static final String REPORT2_QUERY = 
        """
        SELECT 
            r.rental_id,
            COALESCE(c.company_name, CONCAT(c.first_name, ' ', c.last_name)) as customer_name,
            c.customer_type,
            CONCAT(v.make, ' ', v.model) as vehicle,
            vt.type_name as vehicle_type,
            DATE_FORMAT(r.start_date, '%Y-%m-%d') as start_date,
            r.total_amount,
            r.rental_status,
            r.payment_status
        FROM rentals r
        JOIN customers c ON r.customer_id = c.customer_id
        JOIN vehicles v ON r.vehicle_id = v.vehicle_id
        JOIN vehicle_types vt ON v.type_id = vt.type_id
        ORDER BY r.start_date DESC
        LIMIT 100;
        """;

    @Override
    public boolean init() {
        uiController = ICore.getInstance().getUIController();
        db = ICore.getInstance().getDatabaseController();

        createReportTab();
        return true;
    }

    private void createReportTab() {
        Button btRefresh = new Button("Atualizar");
        btRefresh.setStyle("-fx-font-size: 24px;");

        table = new TableView<>();
        rows = FXCollections.observableArrayList();
        table.setItems(rows);

        table.getColumns().clear();
        for (Report2Columns c : Report2Columns.values()) {
            TableColumn<RentalsReportReadDTO, Object> col = new TableColumn<>(c.getHeaderText());
            col.setCellValueFactory(new PropertyValueFactory<>(c.getPropertyName()));
            table.getColumns().add(col);
        }

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setStyle("-fx-font-size: 14pt;");

        Label statusLabel = new Label("Dados indisponíveis.\nClique em Atualizar para tentar novamente.");
        statusLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: #b00020;");
        statusLabel.setAlignment(Pos.CENTER);
        statusLabel.setMaxWidth(Double.MAX_VALUE);
        statusLabel.setVisible(false);
        statusLabel.setManaged(false);

        root = new VBox(10, btRefresh, table, statusLabel);
        root.setPadding(new Insets(10));

        uiController.createTab("Relatório 2", root);

        btRefresh.setOnAction(e -> reloadTable(statusLabel));
        reloadTable(statusLabel);
    }

    private void reloadTable(Label statusLabel) {
        
        try {
            
            List<Map<String, Object>> data = db.loadQuery(REPORT2_QUERY);
            rows.clear();

            for (Map<String, Object> r : data) {
                RentalsReportReadDTO dto = new RentalsReportReadDTO(
                    ((Number) r.get("rental_id")).intValue(),
                    (String) r.get("customer_name"),
                    String.valueOf(r.get("customer_type")),
                    (String) r.get("vehicle"),
                    (String) r.get("vehicle_type"),
                    (String) r.get("start_date"),
                    (BigDecimal) r.get("total_amount"),
                    String.valueOf(r.get("rental_status")),
                    String.valueOf(r.get("payment_status"))
                );
                rows.add(dto);
            }
        } 
        
        catch (SQLException e) {
            statusLabel.setVisible(true);
            statusLabel.setManaged(true);
            e.printStackTrace();
        }
    }
}
