package br.edu.ifba.inf008.shell;

// Importanto bibliotecas internas
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import br.edu.ifba.inf008.interfaces.IDatabaseController;

// Classe responsável por controlar o acesso à base de dados
public class DatabaseController extends IDatabaseController {
    
    public DatabaseController() {
        IDatabaseController.init("mariadb", "3307", "car_rental_system", "root", "root");
    }

    // Estabelece conexão com a base de dados
    // Não-estático para garantir que operação passe obrigatoriamente pelo Core
    @Override
    public Connection getConnection() throws SQLException {
        try {
            String url = IDatabaseController.getURL();
            Properties props = IDatabaseController.createProps();

            Connection connection = DriverManager.getConnection(url, props);
            System.out.println("Conexão estabelecida com sucesso!");
            return connection;
        }
        catch (SQLException e) {
            System.err.println("Não foi possível estabelecer a conexão com o banco de dados: " + e.getMessage());
            throw e; // Relança a SQLException
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
}
