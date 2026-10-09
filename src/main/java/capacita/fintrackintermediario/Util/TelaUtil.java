package capacita.fintrackintermediario.Util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TelaUtil {

    public static void abrirTela(String caminho, Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader(
                TelaUtil.class.getResource(caminho)
        );

        Parent root = loader.load();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
}