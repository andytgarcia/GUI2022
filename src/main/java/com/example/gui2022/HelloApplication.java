package com.example.gui2022;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.W) {
                System.out.println("W key was pressed");
            }
            else if (e.getCode() == KeyCode.S) {
                System.out.println("S key was pressed");
            }
            else if (e.getCode() == KeyCode.A) {
                System.out.println("A key was pressed");
            }
            else if (e.getCode() == KeyCode.D) {
                System.out.println("D key was pressed");
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}