package br.edu.ifba.inf008.interfaces;

// Importanto bibliotecas do Java
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

// Classe responsável por controlar o acesso à base de dados
public abstract class IDatabaseController {
    
    // Credenciais
    private static String subprotocol;
    private static String port;
    private static String database;
    private static String url;
    private static String user;
    private static String password;

    // Configura as credenciais
    // Evita que as credenciais precisem ser passadas via construtor no Core da aplicação
    public static void init(String subprotocol, String port, String database, String user, String password) {
        IDatabaseController.subprotocol = subprotocol;
        IDatabaseController.port = port;
        IDatabaseController.database = database;
        IDatabaseController.user = user;
        IDatabaseController.password = password;
        url = "jdbc:" + subprotocol + "://localhost:" + port + "/" + database;
    }
    
    // Cria a URL completa
    // Formato: jdbc:subprotocolo://host:porta/banco
    public static String getURL() {
        return url;
    }

    // Cria um objeto "Properties" que encapsula as credenciais
    public static Properties createProps() {
        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);
        return props;
    }

    // Estabelece uma conexão read-only com a base de dados
    // Não-estático para garantir que operação passe obrigatoriamente pelo Core
    public abstract Connection getConnectionReadOnly() throws SQLException;

    // Estabelece uma conexão read-write com a base de dados
    // Não-estático para garantir que operação passe obrigatoriamente pelo Core
    public abstract Connection getConnectionReadWrite() throws SQLException;

    // Libera os recursos (base de dados)
    // Não-estático para garantir que operação passe obrigatoriamente pelo Core
    public abstract void closeConnection(Connection connection);

    // Executa uma query para buscar dados do banco
    public abstract ResultSet executeQuery(Connection conn, String sql) throws SQLException;
}