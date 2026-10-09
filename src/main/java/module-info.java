module capacita.fintrackintermediario {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires org.controlsfx.controls;
    requires java.sql;
    requires java.desktop;
    requires jdk.jshell;

    exports capacita.fintrackintermediario;
    opens capacita.fintrackintermediario.Model to javafx.base;
    opens capacita.fintrackintermediario.Controller to javafx.fxml;
}
