package com.example.launcher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {


    @Override
    public void start(Stage primaryStage) throws IOException {
        // Загружаем FXML
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/launcher/hello-view.fxml"));


        Scene scene = new Scene(root);

        // Устанавливаем сцену
        primaryStage.setTitle("Launcher");
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}