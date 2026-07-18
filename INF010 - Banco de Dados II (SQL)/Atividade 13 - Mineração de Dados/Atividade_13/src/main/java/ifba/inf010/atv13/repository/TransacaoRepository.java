package ifba.inf010.atv13.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ifba.inf010.atv13.database.ConnectionFactory;
import ifba.inf010.atv13.model.Transacao;

// Classe que acessa o banco e contém o método para obter os dados
public class TransacaoRepository {
    
    public List<Transacao> buscarTodas() {

        Map<Integer, Set<String>> mapa = new LinkedHashMap<>();

        String sql = """
                SELECT tid, item
                FROM itens_transacao
                ORDER BY tid
                """;

        try(
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
        ) {

            while(rs.next()) {

                Integer tid = rs.getInt("tid");
                String item = rs.getString("item");

                mapa
                    .computeIfAbsent(tid, k -> new HashSet<>())
                    .add(item);
            }
        }

        catch(SQLException ex) {
            throw new RuntimeException();
        }

        return mapa
                .entrySet()
                .stream()
                .map(
                    entry -> new Transacao(entry.getKey(), entry.getValue())
                )
                .toList();
    }
}
