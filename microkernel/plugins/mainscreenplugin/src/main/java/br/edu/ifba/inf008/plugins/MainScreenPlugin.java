package br.edu.ifba.inf008.plugins;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.IDatabaseController;
import br.edu.ifba.inf008.interfaces.IPlugin;
import br.edu.ifba.inf008.interfaces.IUIController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainScreenPlugin implements IPlugin {

    private IDatabaseController db;

    // Tamanhos de fonte
    private static final double LABEL_FONT_SIZE = 36.0;

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
            System.err.println("Conexão não inicializada");
            return;
        }

        // ========== QUERYS ==========

        // Inicializações
        ResultSet customers = null;
        ResultSet vehicle_types = null;
        ResultSet vehicles = null;

        // Comandos SQL
        try {
            customers = db.executeQuery(conn,
                "SELECT customer_id, email FROM customers"
            );

            vehicle_types = db.executeQuery(conn,
                "SELECT type_id, type_name, additional_fees FROM vehicle_types"
            );

            vehicles = db.executeQuery(conn,
                "SELECT type_id, vehicle_id, make, model, year, fuel_type, transmission, mileage FROM vehicles"
             );

            // ========== ELEMENTOS VISUAIS ==========

            // Criação dos elementos visuais
            try {
                
                // ========== E-MAILS ==========

                Label lbEmail = new Label("Email:");
                lbEmail.setStyle(
                    "-fx-font-size: " + LABEL_FONT_SIZE + "px;" + 
                    "-fx-text-fill: black;"
                );
                
                ComboBox<String> cbEmail = new ComboBox<>();
                cbEmail.setStyle(
                    "-fx-font-size: " + LABEL_FONT_SIZE + "px;"
                );

                // Popula a combobox de e-mails com o conteúdo do banco
                while(customers.next()) {
                    String email = customers.getString("email");
                    cbEmail.getItems().add(email);
                }
                
                // Cria um contêiner para o título
                HBox hbEmail = new HBox(5, lbEmail, cbEmail);
                hbEmail.setAlignment(Pos.CENTER_LEFT);
                VBox.setMargin(hbEmail, new Insets(20, 0, 0, 20));

                mainNodes.add(hbEmail);
            } 
            
            catch(Exception e) {
                System.err.println("Erro ao criar conteúdos visuais");
                e.printStackTrace();
            }
        } 

        catch (SQLException e) {
            System.err.println("Erro ao executar queries");
            e.printStackTrace();
            return;
        }
 
        // ========== FECHA AS CONEXÕES ==========
        finally {
            try {
                if (customers != null) customers.close();
                if (vehicle_types != null) vehicle_types.close();
                if (vehicles != null) vehicles.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
