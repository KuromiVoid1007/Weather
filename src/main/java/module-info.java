module com.example.launcher {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens com.example.launcher to javafx.fxml;
    exports com.example.launcher;
}