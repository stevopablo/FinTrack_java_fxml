package capacita.fintrackintermediario.Controller;

import capacita.fintrackintermediario.Enum.TipoTransacao;
import capacita.fintrackintermediario.Model.TransacaoModel;
import capacita.fintrackintermediario.Services.TransacaoServices;
import capacita.fintrackintermediario.Util.TelaUtil;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

public class NovaTransacaoController {

    @FXML
    private Label lblValidacao;

    @FXML
    private TextField txtDescricao;

    @FXML
    private TextField txtValor;

    @FXML
    private ComboBox<TipoTransacao> comboTipo;

    @FXML
    private DatePicker dateData;

    private final TransacaoServices services = new TransacaoServices();

    @FXML
    private void salvarTransacao() throws IOException {

        lblValidacao.setText("");

        if (txtDescricao.getText().isEmpty()){
            lblValidacao.setText("Descrição não pode ser vazia.");
            return;
        }

        if (txtValor.getText().isBlank()){
            lblValidacao.setText("Valor não pode ser vazio.");
            return;
        }

        if (comboTipo.getValue() == null) {
            lblValidacao.setText("Selecione o tipo da transação.");
            return;
        }

        if (dateData.getValue() == null) {
            lblValidacao.setText("Selecione uma data.");
            return;
        }

        String descricao = txtDescricao.getText();

        BigDecimal valor =
                new BigDecimal(txtValor.getText());

        TipoTransacao tipo =
                comboTipo.getValue();

        LocalDate data =
                dateData.getValue();

        TransacaoModel transacao =
                new TransacaoModel(valor, tipo, descricao);

        transacao.setData(data);


        System.out.println("Descrição: " + transacao.getDescricao());
        System.out.println("Valor: " + transacao.getValor());
        System.out.println("Tipo: " + transacao.getTipo());
        System.out.println("Data: " + transacao.getData());

        services.salvar(transacao);

        System.out.println("===========================================");
        System.out.println("Transação salva com sucesso!");

        cancelar();

    }

    @FXML
    private void cancelar() throws IOException {

        Stage stage =
                (Stage) txtDescricao.getScene().getWindow();

        TelaUtil.abrirTela(
                "/fxml/principal.fxml",
                stage
        );

        System.out.println("Saindo");
    }

    @FXML
    public void initialize() {

        comboTipo.getItems().addAll(
                TipoTransacao.values()
        );

        dateData.setValue(LocalDate.now());
    }
}

