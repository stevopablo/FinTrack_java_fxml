package capacita.fintrackintermediario.Controller;

import capacita.fintrackintermediario.Enum.TipoTransacao;
import capacita.fintrackintermediario.Model.TransacaoModel;
import capacita.fintrackintermediario.Services.TransacaoServices;
import capacita.fintrackintermediario.Util.FormataçãoUtil;
import capacita.fintrackintermediario.Util.TelaUtil;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class PrincipalController {

    @FXML
    private Label lblSaldo;

    @FXML
    private TableView<TransacaoModel> tabelaTransacoes;

    @FXML
    private TableColumn<TransacaoModel, String> colDescricao;

    @FXML
    private TableColumn<TransacaoModel, BigDecimal> colValor;

    @FXML
    private TableColumn<TransacaoModel, TipoTransacao> colTipo;

    @FXML
    private TableColumn<TransacaoModel, LocalDate> colData;

    private final TransacaoServices services;

    public PrincipalController() {
        services = new TransacaoServices();
    }

    @FXML
    public void initialize() {

        colDescricao.setCellValueFactory(
                new PropertyValueFactory<>("descricao")
        );

        colValor.setCellValueFactory(
                new PropertyValueFactory<>("valor")
        );

        colTipo.setCellValueFactory(
                new PropertyValueFactory<>("tipo")
        );

        colData.setCellValueFactory(
                new PropertyValueFactory<>("data")
        );

        carregarTabela();
    }

    private void carregarTabela() {

        tabelaTransacoes.setItems(
                FXCollections.observableArrayList(
                        services.listar()
                )
        );

        atualizarSaldo();
    }

    private void atualizarSaldo() {

        BigDecimal saldo = BigDecimal.ZERO;

        for (TransacaoModel transacao : services.listar()) {

            if (transacao.getTipo() == TipoTransacao.RECEITA) {
                saldo = saldo.add(transacao.getValor());

            } else if (transacao.getTipo() == TipoTransacao.DESPESA) {
                saldo = saldo.subtract(transacao.getValor());
            }
        }

        lblSaldo.setText(
                "Saldo: R$ " + FormataçãoUtil.formatarMoedas(saldo)
        );
    }

    @FXML
    private void abrirNovaTransacao() throws IOException {

        Stage stage = (Stage) tabelaTransacoes.getScene().getWindow();

        TelaUtil.abrirTela(
                "/fxml/novaTransacao.fxml",
                stage
        );
    }

    @FXML
    private void removerTransacao() {

        TransacaoModel transacaoModel = tabelaTransacoes
                .getSelectionModel()
                .getSelectedItem();

        if(transacaoModel == null){
            Alert alerta = new Alert(
                    Alert.AlertType.WARNING
            );

            alerta.setTitle("Atenção");
            alerta.setHeaderText(null);
            alerta.setContentText(
                    "Selecione uma transação remover."
            );

            alerta.showAndWait();
            return;
        }

        Alert confirmacao = new Alert(
                Alert.AlertType.CONFIRMATION
        );

        confirmacao.setTitle("Confirmar exclusão");
        confirmacao.setHeaderText(
                "Excluir transação?"
        );

        confirmacao.setContentText(
                "Descrição: " +
                        transacaoModel.getDescricao() +
                        "\nValor: " +
                        FormataçãoUtil.formatarMoedas(
                                transacaoModel.getValor()
                        )
        );

        Optional<ButtonType> resultado =
                confirmacao.showAndWait();

        if (resultado.isPresent()
                && resultado.get() == ButtonType.OK) {

            services.remover(transacaoModel);

            carregarTabela();

        }
    }

    @FXML
    private void atualizarTabela() {

        carregarTabela();

        System.out.println("Tabela atualizada");
    }

    @FXML
    private void abrirRelatorio() throws IOException {

        Stage stage = (Stage) tabelaTransacoes.getScene().getWindow();

        TelaUtil.abrirTela(
                "/fxml/relatorio.fxml",
                stage
        );
    }
}