package capacita.fintrackintermediario.DAO;

import capacita.fintrackintermediario.Database.Conexao;
import capacita.fintrackintermediario.Enum.TipoTransacao;
import capacita.fintrackintermediario.Model.TransacaoModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransacaoDAO {

    public void salvar(TransacaoModel transacao) {

        String sql = """
                INSERT INTO transacoes
                (descricao, valor, tipo, data)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection conn = Conexao.conexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, transacao.getDescricao());
            stmt.setBigDecimal(2, transacao.getValor());
            stmt.setString(3, transacao.getTipo().name());
            stmt.setString(4, transacao.getData().toString());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<TransacaoModel> listar() {

        String sql = """
                SELECT * FROM transacoes
                """;

        List<TransacaoModel> transacoes = new ArrayList<>();

        try (Connection conn = Conexao.conexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Integer id = rs.getInt("id");

                String descricao = rs.getString("descricao");

                var valor = rs.getBigDecimal("valor");

                TipoTransacao tipo =
                        TipoTransacao.valueOf(rs.getString("tipo"));

                LocalDate data =
                        LocalDate.parse(rs.getString("data"));

                TransacaoModel transacao =
                        new TransacaoModel(valor, tipo, descricao);

                transacao.setId(id);
                transacao.setData(data);

                transacoes.add(transacao);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return transacoes;
    }

    public void remover(Integer id) {

        String sql = """
                DELETE FROM transacoes
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizar(TransacaoModel transacao) {

        String sql = """
                UPDATE transacoes
                SET descricao = ?,
                    valor = ?,
                    tipo = ?,
                    data = ?
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, transacao.getDescricao());
            stmt.setBigDecimal(2, transacao.getValor());
            stmt.setString(3, transacao.getTipo().name());
            stmt.setString(4, transacao.getData().toString());
            stmt.setInt(5, transacao.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}