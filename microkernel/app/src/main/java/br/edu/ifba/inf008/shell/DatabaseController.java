package br.edu.ifba.inf008.shell;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import br.edu.ifba.inf008.dtos.Rentals.RentalsInsertDTO;
import br.edu.ifba.inf008.interfaces.IDatabaseController;

// Classe responsável por controlar o acesso à base de dados
public class DatabaseController extends IDatabaseController {
    
    // Visibilidade package-private para forçar os plugins a obterem o DatabaseController via Core
    DatabaseController() {
        IDatabaseController.init("mariadb", "3307", "car_rental_system", "root", "root");
    }

    // Estabelece uma conexão read-only com a base de dados
    // Não-estático para garantir que operação passe obrigatoriamente pelo Core
    @Override
    public Connection getConnectionReadOnly() throws SQLException {
        
        try {
            String url = IDatabaseController.getURL();
            Properties props = IDatabaseController.createProps();

            Connection connection = DriverManager.getConnection(url, props);
            //System.out.println("Conexão estabelecida com sucesso!");

            // Encerra automaticamente cada transação, evitando que uma leitura permença ativa por muito tempo, o que poderia impactar no controle de concorrência pelo SGBD
            connection.setAutoCommit(true); 

            // Impede transações de escrita
            // Obs.: o modo read-only imposto no nível da conexão JDBC é apenas uma barreira lógica. O ideal seria criar um usuário no banco de dados com permissão exclusivamente de leitura
            connection.setReadOnly(true); 

            return connection;
        }

        catch (SQLException e) {
            System.err.println("Não foi possível estabelecer a conexão com o banco de dados: " + e.getMessage());
            throw e; // Relança a SQLException, desviando o tratamento da exceção para o responsável por tentar estabelecer a conexão
        }
    }

    // Estabelece uma conexão read-write com a base de dados
    // Não-estático para garantir que operação passe obrigatoriamente pelo Core
    @Override
    public Connection getConnectionReadWrite() throws SQLException {
        
        try {
            String url = IDatabaseController.getURL();
            Properties props = IDatabaseController.createProps();

            Connection connection = DriverManager.getConnection(url, props);
            //System.out.println("Conexão estabelecida com sucesso!");
            connection.setAutoCommit(true);
            return connection;
        }

        catch (SQLException e) {
            System.err.println("Não foi possível estabelecer a conexão com o banco de dados: " + e.getMessage());
            throw e; 
        }
    }

    // Libera os recursos (base de dados)
    // Não-estático para garantir que operação passe obrigatoriamente pelo Core
    @Override
    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            }
            catch (SQLException e) {
                System.err.println("Não foi possível encerrar a conexão com a base de dados: " + e.getMessage());
            }
        }
    }

    // Executa uma query e carrega o resultado em memória
    @Override
    public List<Map<String, Object>> loadQuery(String sql) throws SQLException {
        
        try (Connection conn = getConnectionReadOnly()) {
 
            if(sql == null || sql.isBlank()) {
                throw new IllegalArgumentException("String sql não pode ser null/vazia.");
            }

            // Lista para representar a "tabela" de dados
            // Cada item da lista representa uma tupla da tabela do banco de dados
            List<Map<String, Object>> rows = new ArrayList<>();

            // try-with-resources
            // Garante o fechamento automático dos recursos utilizados
            try(
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
            ) {

                // Metadados
                ResultSetMetaData meta = rs.getMetaData();
                int columns = meta.getColumnCount();

                // Percorre as tuplas de uma tabela no banco de dados
                while(rs.next()) {

                    // Mapa para representar uma tupla
                    // Mantém a ordem de inserção
                    Map<String, Object> row = new LinkedHashMap<>();

                    // Percorre as colunas dos metadados
                    for(int i = 1; i <= columns; i++) {

                        // Alias/nome da coluna
                        String column = meta.getColumnLabel(i);

                        // Valor da tupla para uma coluna específica
                        Object value = rs.getObject(i);

                        // Armazena a tupla no mapa
                        row.put(column, value);
                    }

                    // Arnazena o mapa na lista
                    rows.add(row);
                }
            }
            return rows;
        } 
    }

    // Executa uma query que insere dados na tabela 'rentals' do banco de dados
    @Override
    public void insertRentalsData(RentalsInsertDTO rentals) throws SQLException {

        try (Connection conn = getConnectionReadWrite()) {
    
            if(rentals == null) {
                throw new IllegalArgumentException("Rentals não pode ser null.");
            }
    
            String sqlInsert = 
                "INSERT INTO rentals (customer_id, vehicle_id, rental_type, start_date, scheduled_end_date, pickup_location, initial_mileage, base_rate, insurance_fee, total_amount) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
            String sqlUpdate = "UPDATE vehicles SET status = 'RENTED' WHERE vehicle_id = ?";
    
            // Desabilita a atualização automática do banco
            conn.setAutoCommit(false);
    
            try(
                PreparedStatement psInsert = conn.prepareStatement(sqlInsert);
                PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate)
            ) {
                psInsert.setInt(1, rentals.getCustomerId());
                psInsert.setInt(2, rentals.getVehicleId());
                psInsert.setString(3, rentals.getRentalType().name());
                psInsert.setTimestamp(4, Timestamp.valueOf(rentals.getStartDate()));
                psInsert.setTimestamp(5, Timestamp.valueOf(rentals.getScheduledEndDate()));
                psInsert.setString(6, rentals.getPickupLocation());
                psInsert.setBigDecimal(7, rentals.getInitialMileage());
                psInsert.setBigDecimal(8, rentals.getBaseRate());
                psInsert.setBigDecimal(9, rentals.getInsuranceFee());
                psInsert.setBigDecimal(10, rentals.getTotalAmount());
                psInsert.executeUpdate();
    
                psUpdate.setInt(1, rentals.getVehicleId());
                psUpdate.executeUpdate();
    
                conn.commit();
            }
            
            catch (SQLException e) {
                conn.rollback();
                throw e;
            }
    
            finally {
                conn.setAutoCommit(true);
            }
        }
    }
}
