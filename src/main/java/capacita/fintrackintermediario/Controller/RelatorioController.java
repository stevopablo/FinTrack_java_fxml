package capacita.fintrackintermediario.Controller;

import capacita.fintrackintermediario.Enum.TipoTransacao;
import capacita.fintrackintermediario.Model.TransacaoModel;
import capacita.fintrackintermediario.Services.TransacaoServices;
import capacita.fintrackintermediario.Util.FormataçãoUtil;
import capacita.fintrackintermediario.Util.TelaUtil;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class RelatorioController {

    private final TransacaoServices services =
            new TransacaoServices();

    @FXML
    private Label lblTotalReceitas;

    @FXML
    private Label lblTotalDespesas;

    @FXML
    private Label lblSaldo;

    @FXML
    private PieChart graficoFinanceiro;

    @FXML
    private void voltar() throws IOException {

        Stage stage =
                (Stage) lblSaldo.getScene().getWindow();

        TelaUtil.abrirTela(
                "/fxml/principal.fxml",
                stage
        );
    }

    @FXML
    public void initialize() {
        calcularRelatorio();
    }

    private void calcularRelatorio() {

        List<TransacaoModel> transacoes =
                services.listar();

        BigDecimal totalReceitas =
                BigDecimal.ZERO;

        BigDecimal totalDespesas =
                BigDecimal.ZERO;

        for (TransacaoModel transacao : transacoes) {

            if (transacao.getTipo() == TipoTransacao.RECEITA) {

                totalReceitas =
                        totalReceitas.add(
                                transacao.getValor()
                        );

            } else if (transacao.getTipo() == TipoTransacao.DESPESA) {

                totalDespesas =
                        totalDespesas.add(
                                transacao.getValor()
                        );
            }
        }

        BigDecimal saldo =
                totalReceitas.subtract(totalDespesas);

        // Valores
        lblTotalReceitas.setText(
                "Total de receitas: " +
                        FormataçãoUtil.formatarMoedas(totalReceitas)
        );

        lblTotalDespesas.setText(
                "Total de despesas: " +
                        FormataçãoUtil.formatarMoedas(totalDespesas)
        );

        lblSaldo.setText(
                "Saldo: " +
                        FormataçãoUtil.formatarMoedas(saldo)
        );

        // Gráfico
        graficoFinanceiro.getData().clear();

        if (totalReceitas.compareTo(BigDecimal.ZERO) > 0) {

            graficoFinanceiro.getData().add(
                    new PieChart.Data(
                            "Receitas",
                            totalReceitas.doubleValue()
                    )
            );
        }

        if (totalDespesas.compareTo(BigDecimal.ZERO) > 0) {

            graficoFinanceiro.getData().add(
                    new PieChart.Data(
                            "Despesas",
                            totalDespesas.doubleValue()
                    )
            );
        }
    }
}