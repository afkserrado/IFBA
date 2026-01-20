package br.ifba.databaseapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseApp {

    // Credenciais
    // Formato do pgJDBC: jdbc:postgresql://host:port/database (host=localhost, port=5432 por padrão)
    // espaços viram %20
    private static final String URL = "jdbc:postgresql://localhost:5432/BD%201%20-%20L5";
    private static final String USER = System.getenv("DB_USER");
    private static final String PASSWORD = System.getenv("DB_PASSWORD");
    
    // Método para criar um objeto "Properties", que encapsula as credenciais
    private static Properties props() {
        Properties props = new Properties();
        props.setProperty("user", USER);
        props.setProperty("password", PASSWORD);
        return props;
    }

    public static void main(String[] args) {

        // Comando SQL para inserção
        String sqlInsert = "INSERT INTO TB_EDITORA (COD_EDITORA, DESCRICAO, ENDERECO) VALUES (?, ?, ?)";

        // Comando SQL para consulta
        String sqlSelect = "SELECT COD_EDITORA, DESCRICAO, ENDERECO FROM TB_EDITORA ORDER BY COD_EDITORA";

        // É necessária uma forma de tratar SQLException (por exemplo, falha de conexão) e de fechar os recursos abertos. O try-with-resources com catch soluciona ambos os problemas
        try (Connection conn = DriverManager.getConnection(URL, props())) {

            // 1) INSERT
            // Tenta criar um objeto "PreparedStatement", que é um comando SQL, e inserir os dados na base
            try (PreparedStatement ps = conn.prepareStatement(sqlInsert)) {
                ps.setInt(1, 10);
                ps.setString(2, "Novatec");
                ps.setString(3, "Av. ACM");

                int linhas = ps.executeUpdate();
                System.out.println("Linhas inseridas: " + linhas);
            }

            // 2) SELECT (listar tudo)
            // Tenta criar um objeto "PreparedStatement", que é um comando SQL, e consultar a base
            try (PreparedStatement ps = conn.prepareStatement(sqlSelect);
                 ResultSet rs = ps.executeQuery()) {

                System.out.println("\nConteúdo de TB_EDITORA:");
                while (rs.next()) { // percorre linha a linha
                    int cod = rs.getInt("COD_EDITORA");
                    String descricao = rs.getString("DESCRICAO");
                    String endereco = rs.getString("ENDERECO"); // pode ser null

                    System.out.printf("cod=%d, descricao=%s, endereco=%s%n", cod, descricao, endereco);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro JDBC: " + e.getMessage());
        }
    }
}
