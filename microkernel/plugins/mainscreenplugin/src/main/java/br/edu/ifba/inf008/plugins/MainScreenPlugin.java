package br.edu.ifba.inf008.plugins;

import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.IDatabaseController;
import br.edu.ifba.inf008.interfaces.IPlugin;
import br.edu.ifba.inf008.interfaces.IUIController;
import br.edu.ifba.inf008.interfaces.IVehicleTypes;
import br.edu.ifba.inf008.plugins.DTO.FuelType;
import br.edu.ifba.inf008.plugins.DTO.Transmission;
import br.edu.ifba.inf008.plugins.DTO.VehicleColumns;
import br.edu.ifba.inf008.plugins.DTO.VehicleTableItem;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Date;

import javafx.util.StringConverter;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Spinner;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.time.LocalDate;

// Rever o fechamento da conexão
// Refatorar: um método para cada elemento visual

public class MainScreenPlugin implements IPlugin {

    // ========== ATRIBUTOS ==========

    private IDatabaseController db;

    // ========== CONSTANTES ==========
    
    // Querys
    // customer_id: entrada para a inserção na tabela rentals
    // email: conteúdo a ser exibido na tela
    private static final String CUSTOMERS_QUERY = 
        "SELECT customer_id, email " +
        "FROM customers";

    // type_id: entrada para a query que retornará os dados a serem exibidos na tabela
    // type_name: conteúdo a ser exibido na tela
    // additional_fees: utilizado para o cálculo do valor total da locação
    private static final String VEHICLE_TYPES_QUERY = 
        "SELECT type_id, type_name, additional_fees " + 
        "FROM vehicle_types";    

    // vehicle_id: entrada para a inserção na tabela rentals
    // demais itens: conteúdo a ser exibido na tela
    private static final String VEHICLES_BY_TYPE_QUERY = 
        "SELECT vehicle_id, make, model, year, fuel_type, transmission, mileage " + 
        "FROM vehicles " +
        "WHERE status = 'AVAILABLE' AND type_id = ";

    // Tamanhos de fonte
    private static final double LABEL_FONT_SIZE = 24.0;

    // ========== MÉTODOS ==========

    // Obtém as instâncias do UIController e DatabaseController
    // Gerencia a conexão com o banco de dados
    // Adiciona os elementos visuais à tela principal
    @Override
    public boolean init() {
        
        // Obtém a instância do controlador da interface gráfica
        IUIController uiController = ICore.getInstance().getUIController();

        // Inicializa a conexão
        Connection conn = null;

        // Abre a conexão com o banco de dados
        try {
            // Obtém a instância do controlador da base de dados
            db = ICore.getInstance().getDatabaseController();
            conn = db.getConnectionReadOnly();
            System.out.println("Conexão estabelecida: " + conn);
        } 
        
        // Conexão falhou
        catch (Exception ex) {
            System.err.println("Erro ao conectar com banco: " + ex.getMessage());
            ex.printStackTrace();
        }

        // Inicializa a lista de nós
        List<Node> mainNodes = new LinkedList<>();

        createContent(conn, mainNodes);
        uiController.addMainNodes(mainNodes);

        // Fecha a conexão
        //db.closeConnection(conn);
        //System.out.println("Conexão fechada com sucesso!");

        return true;
    }

    // Executa querys SQL
    // Cria elementos visuais
    // Popula os elementos visuais com os dados do banco
    public void createContent(Connection conn, List<Node> mainNodes) {

        if (conn == null) {
            System.err.println("Connection conn não pode ser null.");
            return;
        }

        // ========== DADOS ==========

        List<Map<String, Object>> customersData = new ArrayList<>();
        List<Map<String, Object>> vehicleTypesData = new ArrayList<>();

        try {
            customersData = db.loadQuery(conn, CUSTOMERS_QUERY);
            vehicleTypesData = db.loadQuery(conn, VEHICLE_TYPES_QUERY);
        } 
        
        catch (SQLException e) {
            System.err.println("Erro ao carregar dados.");
            e.printStackTrace();
        }

        // ========== CONTROLES ========== 
        // Define os elementos visuais com os quais o usuário interagirá e a partir dos quais serão obtidos os dados de inserção

        ComboBox<Map<String, Object>> cbEmail = new ComboBox<>();
        ComboBox<Map<String, Object>> cbVehicleTypes = new ComboBox<>();
        TableView<VehicleTableItem> tbVehicles = new TableView<>();
        DatePicker dpStartDate = new DatePicker();
        DatePicker dpEndDate = new DatePicker();
        TextField tfPickupLocation = new TextField();
        Button btCalculate = createButton("Calcular");
        Label lbTotalAmount = createLabel("Valor total: ");
        Button btConfirm = createButton("Confirmar");
        
        // Cria um Spinner, definindo o valor mínimo, valor máximo, valor inicial e incremento
        Spinner<Double> spBaseRate = new Spinner<>(0.0, 1_000_000.0, 0.0, 0.10);
        Spinner<Double> spInsuranceFee = new Spinner<>(0.0, 1_000_000.0, 0.0, 0.10);

        // ========== NÓS ==========
        // Define os nós (contêineres) que serão enviados para a interface, incluindo os controles

        HBox hbEmail = createComboBoxNode("E-mail", cbEmail, customersData, "email");
        HBox hbVehicleTypes = createComboBoxNode("Tipo de veículo", cbVehicleTypes, vehicleTypesData, "type_name");
        VBox vbVehicles = createTableViewNode("Veículos disponíveis", tbVehicles);
        HBox hbStartDate = createConteinerNode("Início da locação", dpStartDate);
        HBox hbEndDate = createConteinerNode("Fim da locação", dpEndDate);
        HBox hbPickupLocation = createConteinerNode("Local de retirada", tfPickupLocation);
        HBox hbBaseRate = createSpinnerNode("Valor da diária", spBaseRate);
        HBox hbInsuranceFee = createSpinnerNode("Valor do seguro", spInsuranceFee);

        // ========== WIRE EVENTS ==========
        // Define os eventos que acontecerão quando o usuário interagir com a interface
        ListAvailableVehicles(conn, cbVehicleTypes, tbVehicles);
        showTotalAmount(btCalculate, cbEmail, cbVehicleTypes, tbVehicles, dpStartDate, dpEndDate, tfPickupLocation, spBaseRate, spInsuranceFee, lbTotalAmount 
        );

        // ========== LAYOUT ==========
        GridPane grid = new GridPane();
        grid.setHgap(40); // Espaçamento entre colunas
        grid.setVgap(25); // Espaçamento entre linhas
        grid.setPadding(new Insets(20)); // Margens internas
        
        // Adiciona os elementos (coluna x linha)
        grid.add(hbEmail,           0, 0);
        grid.add(hbVehicleTypes,    1, 0);
        grid.add(hbPickupLocation,  2, 0);
        grid.add(hbBaseRate,        3, 0);
        grid.add(hbInsuranceFee,    4, 0);
        grid.add(hbStartDate,       0, 1);
        grid.add(hbEndDate,         1, 1);
        grid.add(vbVehicles,        0, 2);
        grid.add(lbTotalAmount,     0, 3);
        grid.add(btCalculate,       1, 3); 
        grid.add(btConfirm,         0, 5);
        
        // Define a quantidade de colunas ocupadas pela tabela ("mescla" colunas)
        GridPane.setColumnSpan(vbVehicles, GridPane.REMAINING); 

        // Adiciona os elementos visuais à lista de nodes
        mainNodes.addAll(List.of(grid));
    }

    // Cria um nó contendo um Label e um Node qualquer (ComboBox, DatePicker etc.)
    public HBox createConteinerNode(String label, Node node) {

        if(!label.contains(":")) {
            label = label + ": ";
        }

        Label lb = createLabel(label);
        
        node.setStyle(
            "-fx-font-size: " + LABEL_FONT_SIZE + "px;"
        );

        // Cria um contêiner para agrupar label e combobox
        HBox hb = new HBox(5, lb, node);
        hb.setAlignment(Pos.CENTER_LEFT);

        return hb;
    }

    // Cria um nó contendo um Label e uma ComboBox
    public HBox createComboBoxNode(String label, ComboBox<Map<String, Object>> cb, List<Map<String, Object>> data, String bdColumn) {

        HBox hb = createConteinerNode(label, cb);

        cb.getItems().addAll(data);
        MainScreenPlugin.configureComboBoxDisplayColumn(cb, bdColumn);

        return hb;
    }

    // Cria um nó contendo um Label e uma TableView
    public VBox createTableViewNode(String label, TableView<VehicleTableItem> tbVehicles) {
        
        Label lb = createLabel(label);

        lb.setMaxWidth(Double.MAX_VALUE);
        lb.setAlignment(Pos.CENTER);

        // 'tbVehicles' é uma tabela na qual cada linha é um VehicleTableItem

        // Cria um ObservableList (internamente implementado como um ArrayList) de VehicleTableItem
        // Analogamente, é como uma lista na qual cada elemento é um VehicleTableItem
        // Um ObservableList permite refletir automaticamente na tela quaisquer alterações nos dados
        ObservableList<VehicleTableItem> rows = FXCollections.observableArrayList();

        // Elimina as colunas existentes
        tbVehicles.getColumns().clear();

        for(VehicleColumns c : VehicleColumns.values()) {
            
            // Cria as colunas da tabela
            // TableColumn<S, T> onde,
                // S é o tipo da linha
                // T é o tipo das células da coluna
            TableColumn<VehicleTableItem, Object> col = new TableColumn<>(c.getHeaderText());

            // Obtém o nome do atributo correspondente
            String propertyName = c.getPropertyName();

            // PropertyValueFactory usa reflexão para buscar um getter compatível com o parâmetro passado
            // setCellValueFactory define a função que será chamada para obter o valor que deve aparecer na célula
            // Obs.: os parâmetros passados para o construtor devem corresponder exatamente ao nome dos respectivos atributos da classe VehicleTableItem
            col.setCellValueFactory(new PropertyValueFactory<>(propertyName));

            // Vincula a coluna à tabela
            tbVehicles.getColumns().add(col);
        }

        // Vincula as linhas à tabela
        tbVehicles.setItems(rows);

        // Define o modo de seleção única
        tbVehicles.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        // Autodimensiona as colunas
        tbVehicles.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Cria um contêiner para agrupar label e combobox
        VBox vb = new VBox(5, lb, tbVehicles);
        vb.setAlignment(Pos.CENTER_LEFT);

        return vb;
    }

    // Cria um nó contendo um Label e um Spinner
    public HBox createSpinnerNode(String label, Spinner<Double> sp) {
        
        HBox hb = createConteinerNode(label, sp);
        sp.setEditable(true);
        
        // Como o Spinner está editável (setEditable(true)), o usuário pode digitar no editor (TextField)
        // TextField suporta um TextFormatter, objeto utilizado para interceptar/filtrar qualquer alteração no texto antes de ser aplicada
        sp.getEditor().setTextFormatter(
            // Cria um TextFormatter, passando uma função de filtro
            new TextFormatter<>(
                // Função de filtro
                // A função recebe um "Change" (a mudança proposta) e deve retornar:
                    // - o próprio change (aceita), ou
                    // - null (rejeita)
                change -> {
                    // Texto completo que o controle teria se essa mudança fosse aplicada
                    String t = change.getControlNewText(); 
                    
                    // Verifica se o texto atende ao requisito
                    // Aceita: vazio, "123", "123," e "123,12"
                    return t.matches("\\d*(,\\d*)?") ? change : null;
                }
            )
        );

        return hb;
    }

    // Cria botão e define sua formatação
    public Button createButton(String button) {

        Button bt = new Button(button);
        bt.setStyle(
            "-fx-font-size: " + LABEL_FONT_SIZE + "px;" + 
            "-fx-text-fill: black;"
        );

        return bt;
    }

    // Cria um label e define sua formatação
    public Label createLabel(String label) {

        Label lb = new Label(label);
        lb.setStyle(
            "-fx-font-size: " + LABEL_FONT_SIZE + "px;" + 
            "-fx-text-fill: black;"
        );

        return lb;
    }

    // Cria uma caixa de diálogo
    public Alert createAlert(AlertType alertType, String header, String msg) {

        Alert al = new Alert(alertType, msg);
        al.setHeaderText(header);

        return al;
    }

    // Lista os veículos disponíveis conforme o tipo selecionado na combobox de tipos de veículos
    public void ListAvailableVehicles(Connection conn, ComboBox<Map<String, Object>> cbVehicleTypes, TableView<VehicleTableItem> tbVehicles) {

        cbVehicleTypes.setOnAction(event -> {

            // Obtém o item selecionado na combobox (Map)
            Map<String, Object> selected = cbVehicleTypes.getValue();

            // Nada selecionado
            if(selected == null) {
                return;
            }

            // Extrai o type_id do map
            int typeId = ((Number) selected.get("type_id")).intValue();

            try {
            
                // Monta a query final
                String sql = VEHICLES_BY_TYPE_QUERY + typeId;

                // Executa a query
                List<Map<String, Object>> filteredVehicles = db.loadQuery(conn, sql);

                // Obtém as linhas da tabela
                ObservableList<VehicleTableItem> rows = tbVehicles.getItems();

                // Limpa a tabela
                rows.clear();

                // Cria as linhas as tabela com o resultado da consulta
                for (Map<String, Object> row : filteredVehicles) {

                    Object yearObj = row.get("year");
                    int yearInt = ((Date) yearObj).toLocalDate().getYear();

                    // DTO
                    VehicleTableItem item = new VehicleTableItem(
                        ((Number) row.get("vehicle_id")).intValue(),
                        (String) row.get("make"),
                        (String) row.get("model"),
                        yearInt,
                        FuelType.valueOf((String) row.get("fuel_type")),
                        Transmission.valueOf((String) row.get("transmission")),
                        ((Number) row.get("mileage")).doubleValue()
                    );

                    rows.add(item);
                }

                // Limpa a seleção atual da tabela
                tbVehicles.getSelectionModel().clearSelection();
            } 
            
            catch(Exception e) {
                e.printStackTrace();
            }
        });
    }

    // Valida as entradas e calcula o valor total da locação
    public void showTotalAmount(
        Button btCalculate, 
        ComboBox<Map<String, Object>> cbEmail, 
        ComboBox<Map<String, Object>> cbVehicleTypes, 
        TableView<VehicleTableItem> tbVehicles,
        DatePicker dpStartDate,
        DatePicker dpEndDate,
        TextField tfPickupLocation,
        Spinner<Double> spBaseRate,
        Spinner<Double> spInsuranceFee,
        Label lbTotalAmount
    ) {

        btCalculate.setOnAction(event -> {

            // Obtém os valores atuais dos controles
            Map<String, Object> email = cbEmail.getValue();
            Map<String, Object> vehicleType = cbVehicleTypes.getValue();
            VehicleTableItem vehicle = tbVehicles.getSelectionModel().getSelectedItem();
            LocalDate startDate = dpStartDate.getValue();
            LocalDate endDate = dpEndDate.getValue();
            String pickupLocation = tfPickupLocation.getText();
            double baseRate = spBaseRate.getValue();
            double insuranceFee = spInsuranceFee.getValue();

            // Valida os dados de entrada
            if(
                email == null ||
                vehicleType == null ||
                vehicle == null ||
                startDate == null ||
                endDate == null ||
                pickupLocation.isBlank() ||
                baseRate == 0 ||
                baseRate < 0 ||
                insuranceFee < 0
            )
            {
                String msg = 
                    "Existem campos que não foram preenchidos pelo usuário. Verifique se: \n" +
                    "   - Ao menos um e-mail, tipo de veículo e veículo foram informados.\n" +
                    "   - O local da retirada foi informado.\n" +
                    "   - Os valores da diária e do seguro são maiores que zero.\n" +
                    "   - As datas de início e fim da locação foram informadas.";

                createAlert(AlertType.ERROR, "Campos não preenchidos", msg).showAndWait();
                return;
            }

            if(endDate.isBefore(startDate)) {
                createAlert(AlertType.ERROR, "Datas incompatíveis", "A data de fim da locação não pode ser inferior à data de início.").showAndWait();
                return;
            }
    
            // Obtém os dados do tipo de veículo selecionado na combobox
            String typeName = ((String) vehicleType.get("type_name")).trim();
            
            if(typeName.isEmpty()) {
                createAlert(AlertType.ERROR, "Tipo de veículo incorreto", "O tipo de veículo não pode ser vazio.").showAndWait();
                return;
            }

            typeName = typeName.substring(0, 1) + typeName.substring(1).toLowerCase();

            String additionalFees = (String) vehicleType.get("additional_fees");

            // Obtém o plugin correspondente ao tipo de veículo selecionado
            String vehiclePluginName = typeName + "Plugin";
            IPlugin plugin = ICore
                .getInstance()
                .getPluginController()
                .getPlugin(vehiclePluginName);     
                
            if (plugin == null || !(plugin instanceof IVehicleTypes)) {
                createAlert(AlertType.ERROR, "Plugin não encontrado",
                    "O plugin " + vehiclePluginName + " indisponível. Não é possível calcular o valor total da locação."
                ).showAndWait();
                return;
            }

            IVehicleTypes vehiclePlugin = (IVehicleTypes) plugin;
            double total = vehiclePlugin.calculateTotalAmount(baseRate, insuranceFee, startDate, endDate, additionalFees);

            // Carrega o resultado na tela
            lbTotalAmount.setText(String.format("Valor total: R$ %.2f", total));
        });
    }

    /**
     * 
     * @param cb ComboBox
     * @param displayColumn Coluna a ser exibida
     * 
     * Ao optar pela abordagem de inserir um contêiner (Map) na combobox, faz-se 
     * necessário definir o setConverter() da ComboBox, que é o método responsável
     * configurar o que será exibido na tela.
     * 
     * Embora pareça mais verboso, essa abordagem foi escolhida para facilitar
     * a inserção de dados no banco. Isso porque a tabela "rentals" requer os IDs
     * (customer_id, vehicle_id) de outras tabelas para criar um novo registro.
     * Como esses dados já estarão carregados em memória, basta um 
     * data.get("columnID") para obtê-los.
     * 
     * Carregar apenas as strings (como email ou type_name) exigiria percorrer a
     * lista de mapas, comparando o valor da chave correspondente com o valor
     * selecionado na ComboBox. Isso teria um custo computacional maior.
     * 
     */
    public static void configureComboBoxDisplayColumn(ComboBox<Map<String, Object>> cb, String displayColumn) {

        // Define como o JavaFX converte cada item (Map) em String
        cb.setConverter(new StringConverter<Map<String, Object>>() {

            // Define o que será exibido
            @Override
            public String toString(Map<String, Object> row) {

                // Quando não há item selecionado
                if (row == null) {
                    return "";
                }

                Object value = row.get(displayColumn);

                // Evita NullPointerException
                if (value == null) {
                    return "";
                }

                // Converte para texto (String) para mostrar na ComboBox
                return value.toString();
            }

            // Para comboboxes editáveis: converte o texto de volta para um item
            // Sobrescrita necessária para instanciar StringConverter
            @Override
            public Map<String, Object> fromString(String string) {
                return null;
            }
        });
    }
}
