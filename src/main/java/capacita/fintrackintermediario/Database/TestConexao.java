package capacita.fintrackintermediario.Database;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class TestConexao {
    public static void main(String[] args) {
        try {
            Connection conn = Conexao.conexao();
            System.out.println("Banco conectado");

            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
