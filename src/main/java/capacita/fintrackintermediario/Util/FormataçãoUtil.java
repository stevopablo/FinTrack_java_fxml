package capacita.fintrackintermediario.Util;

import jdk.jshell.execution.LocalExecutionControlProvider;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class FormataçãoUtil {
    private static final NumberFormat FORMATO_MOEDA_BR =
            NumberFormat.getCurrencyInstance(
                    Locale.of("pt","BR"));

    public static String formatarMoedas(BigDecimal valor){
        if (valor == null){
            return "R$: 0,00";
        }

        return FORMATO_MOEDA_BR.format(valor);
    }
}


