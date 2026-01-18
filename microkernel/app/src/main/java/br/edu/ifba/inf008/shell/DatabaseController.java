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

/**
 * Controlador responsável por intermediar o acesso à base de dados via JDBC.
 *
 * <p>Esta classe implementa as operações definidas por {@link IDatabaseController}, oferecendo:</p>
 * <ul>
 *   <li>Criação de conexões somente-leitura e leitura/escrita.</li>
 *   <li>Execução de queries de leitura com carregamento do resultado em memória.</li>
 *   <li>Operação transacional para registrar locações e atualizar o status do veículo.</li>
 * </ul>
 *
 * <p>A inicialização dos parâmetros de conexão (URL/credenciais) ocorre por meio dos métodos estáticos de {@link IDatabaseController}.</p>
 */
public class DatabaseController extends IDatabaseController {
    
    /**
     * Constrói o controlador e inicializa os parâmetros de conexão com o banco.
     *
     * <p>O construtor é package-private para incentivar que o acesso ao controlador ocorra via core, evitando que plug-ins instanciem este controller diretamente.</p>
     */
    DatabaseController() {
        IDatabaseController.init("mariadb", "3307", "car_rental_system", "root", "root");
    }

    /**
     * Obtém uma conexão JDBC configurada para operações de leitura.
     *
     * <p>Além de abrir a conexão, o método:</p>
     * <ul>
     *   <li>Habilita {@code autoCommit} para encerrar automaticamente cada operação.</li>
     *   <li>Configura {@code readOnly=true} como uma dica/barreira lógica para impedir escrita.</li>
     * </ul>
     *
     * @return conexão JDBC em modo somente leitura
     * @throws SQLException se ocorrer erro ao abrir/configurar a conexão
     */
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

    /**
     * Obtém uma conexão JDBC configurada para operações de leitura e escrita.
     *
     * <p>Por padrão, a conexão é criada com {@code autoCommit=true}, o que faz cada comando SQL ser confirmado automaticamente, a menos que o modo transacional seja explicitamente alterado (ver {@link #insertRentalsData(RentalsInsertDTO)}).</p> 
     *
     * @return conexão JDBC em modo leitura/escrita
     * @throws SQLException se ocorrer erro ao abrir/configurar a conexão
     */
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

    /**
     * Executa uma query SQL de leitura e carrega o resultado em memória.
     *
     * <p>O retorno é representado como uma lista de mapas, onde:</p>
     * <ul>
     *   <li>Cada item da lista representa uma linha (tupla) retornada.</li>
     *   <li>O mapa associa o alias/nome da coluna ({@link ResultSetMetaData#getColumnLabel(int)})
     *       ao respectivo valor ({@link ResultSet#getObject(int)}).</li>
     * </ul>
     *
     * <p>Utiliza {@code try-with-resources} para garantir fechamento automático dos recursos
     * JDBC associados ao statement e ao result set.</p>
     *
     * @param sql comando SQL a ser executado (não pode ser {@code null} ou vazio)
     * @return lista de linhas do resultado, preservando a ordem das colunas
     * @throws IllegalArgumentException se {@code sql} for {@code null} ou vazio
     * @throws SQLException se ocorrer erro de acesso ao banco durante a execução da query
     */
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

    /**
     * Insere uma nova locação na tabela {@code rentals} e atualiza o status do veículo para {@code RENTED}.
     *
     * <p>As duas operações (insert + update) são executadas dentro de uma única transação JDBC:
     * {@code autoCommit} é desabilitado, e ao final ocorre {@link Connection#commit()} ou, em caso de erro,
     * {@link Connection#rollback()}.</p>
     *
     * @param rentals DTO contendo os dados necessários para inserção da locação
     * @throws IllegalArgumentException se {@code rentals} for {@code null}
     * @throws SQLException se ocorrer erro durante inserção/atualização; nesse caso a transação é revertida
     */
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
