package br.edu.ifba.inf008.shell;

// Importanto bibliotecas internas
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

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
            System.out.println("Conexão estabelecida com sucesso!");

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
            System.out.println("Conexão estabelecida com sucesso!");
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

    // Executa uma query para buscar dados do banco
    @Override
    public ResultSet executeQuery(Connection conn, String sql) throws SQLException {
        if(!conn.isReadOnly()) {
            throw new SQLException("Possível tentativa de comando não permitido em transação read-only.");
        }

        PreparedStatement ps = conn.prepareStatement(sql);
        return ps.executeQuery();
    }
}
