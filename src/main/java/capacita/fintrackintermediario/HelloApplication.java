package capacita.fintrackintermediario;

import capacita.fintrackintermediario.Database.Conexao;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch();
        System.out.println(LocalDate.now().toString());

        try {
            Connection conn = Conexao.conexao();
            System.out.println("Conectado");
        }catch (SQLException e){
            System.out.println(e);
        }

    }

}