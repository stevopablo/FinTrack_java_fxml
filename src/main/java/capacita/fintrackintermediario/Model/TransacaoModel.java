package capacita.fintrackintermediario.Model;

import capacita.fintrackintermediario.Enum.TipoTransacao;

import java.math.BigDecimal;
import java.time.LocalDate;
public class TransacaoModel {

    private Integer id;
    private String descricao;
    private BigDecimal valor;  // Big decimal é mais adequado
    private TipoTransacao tipo;
    private LocalDate data;

    public TransacaoModel(BigDecimal valor, TipoTransacao tipo, String descricao) {
        this.valor = valor;
        this.tipo = tipo;
        this.descricao = descricao;
        this.data = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

    public LocalDate getData() {
        return data;
    }

    @Override
    public String toString() {
        return "TransacaoModel{" +
                "descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", entrada=" + tipo +
                ", data=" + data +
                '}';
    }

    public void setData(LocalDate data) {

    }
}
