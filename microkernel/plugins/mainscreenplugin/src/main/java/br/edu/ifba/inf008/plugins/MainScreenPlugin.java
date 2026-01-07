package br.edu.ifba.inf008.plugins;

import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.IDatabaseController;
import br.edu.ifba.inf008.interfaces.IPlugin;
import br.edu.ifba.inf008.interfaces.IUIController;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.SQLException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MainScreenPlugin implements IPlugin {

    // ========== ATRIBUTOS ==========

    private IDatabaseController db;

    // ========== CONSTANTES ==========
    
    // Querys
    // customer_id: entrada para a inserção na tabela rentals
    // email: conteúdo a ser exibido na tela
    private static final String CUSTOMERS_QUERY = "SELECT customer_id, email FROM customers";

    // vehicle_id: entrada para a inserção na tabela rentals
    // demais itens: conteúdo a ser exibido na tela
    private static final String VEHICLES_QUERY = "SELECT vehicle_id, make, model, year, fuel_type, transmission, mileage FROM vehicles";

    // type_id: entrada para a query que retornará os dados a serem exibidos na tabela
    // type_name: conteúdo a ser exibido na tela
    // additional_fees: utilizado para o cálculo do valor total da locação
    private static final String VEHICLE_TYPES_QUERY = "SELECT type_id, type_name, additional_fees FROM vehicle_types";

    // Tamanhos de fonte
    private static final double LABEL_FONT_SIZE = 36.0;

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
        db.closeConnection(conn);
        System.out.println("Conexão fechada com sucesso!");

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
        List<Map<String, Object>> vehiclesData = new ArrayList<>();
        List<Map<String, Object>> vehicleTypesData = new ArrayList<>();

        try {
            customersData = db.loadQuery(conn, CUSTOMERS_QUERY);
            vehiclesData = db.loadQuery(conn, VEHICLES_QUERY);
            vehicleTypesData = db.loadQuery(conn, VEHICLE_TYPES_QUERY);
        } 
        
        catch (SQLException e) {
            System.err.println("Erro ao carregar dados.");
            e.printStackTrace();
        }

        // ========== ELEMENTOS VISUAIS ==========

        // ========== E-MAILS ==========

        Label lbEmail = new Label("Email:");
        lbEmail.setStyle(
            "-fx-font-size: " + LABEL_FONT_SIZE + "px;" + 
            "-fx-text-fill: black;"
        );
        
        ComboBox<Map<String, Object>> cbEmail = new ComboBox<>();
        cbEmail.setStyle(
            "-fx-font-size: " + LABEL_FONT_SIZE + "px;"
        );

        cbEmail.getItems().addAll(customersData);
        MainScreenPlugin.configureComboBoxDisplayColumn(cbEmail, "email");

        // Cria um contêiner para agrupar label e combobox
        HBox hbEmail = new HBox(5, lbEmail, cbEmail);
        hbEmail.setAlignment(Pos.CENTER_LEFT);
        VBox.setMargin(hbEmail, new Insets(20, 0, 0, 20));

        // ========== TIPO DE VEÍCULOS ==========

        Label lbVehicleType = new Label("Tipo de veículo:");
        lbVehicleType.setStyle(
            "-fx-font-size: " + LABEL_FONT_SIZE + "px;" + 
            "-fx-text-fill: black;"
        );
        
        ComboBox<Map<String, Object>> cbVehicleType = new ComboBox<>();
        cbVehicleType.setStyle(
            "-fx-font-size: " + LABEL_FONT_SIZE + "px;"
        );

        cbVehicleType.getItems().addAll(vehicleTypesData);
        MainScreenPlugin.configureComboBoxDisplayColumn(cbVehicleType, "type_name");
        
        // Cria um contêiner para agrupar label e combobox
        HBox hbVehicleType = new HBox(5, lbVehicleType, cbVehicleType);
        hbVehicleType.setAlignment(Pos.CENTER_LEFT);
        VBox.setMargin(hbVehicleType, new Insets(40, 0, 0, 20));

        // ========== VEÍCULOS ==========
    
                
        // Adiciona os elementos visuais à lista de nodes
        mainNodes.addAll(List.of(
            hbEmail, 
            hbVehicleType
        ));
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
