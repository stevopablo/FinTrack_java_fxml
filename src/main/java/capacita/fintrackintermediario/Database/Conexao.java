package capacita.fintrackintermediario.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {
    private static final String URL = "jdbc:mysql://localhost:3306/   "; // Adiciona o nome do banco de dados
    private static final String USER = ""; // Colocar a usuario
    private static final String password = ""; // Colocar a senha

    public static Connection conexao() throws SQLException {
        return DriverManager.getConnection(URL, USER, password);
    }


    public void createTable(Connection conn){
        String sql = """
                    CREATE TABLE IF NOT EXISTS Transacoes (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    descricao TEXT NOT NULL,
                    valor DECIMAL(10,2) NOT NULL,
                    data TEXT NOT NULL
                    )
                """;

        try(Statement stmt = conn.createStatement()){
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
