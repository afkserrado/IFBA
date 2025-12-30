package br.edu.ifba.inf008.interfaces;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// Classe responsável por controlar o acesso à base de dados
public abstract class IDatabaseController {
    
    // Credenciais
    private static String subprotocol;
    private static String port;
    private static String database;
    private static String url; // Formato: jdbc:SUBPROTOCOLO://HOST:PORTA/BANCO
    private static String user;
    private static String password;

    // Configura as credenciais
    public static void init(String subprotocol, String port, String database, String user, String password) {
        IDatabaseController.subprotocol = subprotocol;
        IDatabaseController.port = port;
        IDatabaseController.database = database;
        IDatabaseController.user = user;
        IDatabaseController.password = password;

        createURL();
    }
    
    // Cria a URL
    private static String createURL() {
        return url = "jdbc:" + subprotocol + "://localhost:" + port + "/" + database;
    }

    // Cria um objeto "Properties", que encapsula as credenciais
    private static Properties createProps() {
        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);
        return props;
    }

    // Estabelece conexão com a base de dados
    // Não-estático para garantir que operação passe obrigatoriamente pelo Core
    public Connection getConnection() throws SQLException {
        try {
            Connection connection = DriverManager.getConnection(url, createProps());
            System.out.println("Conexão estabelecida com o " + subprotocol + "!");
            return connection;
        }
        catch (SQLException e) {
            System.err.println("Não foi possível estabelecer a conexão com o banco de dados: " + e.getMessage());
            throw e; // Relança a SQLException
        }
    }

    // Libera os recursos (base de dados)
    // Não-estático para garantir que operação passe obrigatoriamente pelo Core
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