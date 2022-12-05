package com.example.gui2022;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;

public class HelloApplication extends Application {
    Stage window;
    static Scene scene;
    static HashSet<String> currentlyActiveKeys;

    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        scene = new Scene(fxmlLoader.load(), 800, 600);
        window = primaryStage;
        window.setTitle("Hello World");
        window.setScene(scene);
        prepareActionHandlers();

        window.show();
    }

    private static void prepareActionHandlers() {
        currentlyActiveKeys = new HashSet<String>();
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                currentlyActiveKeys.add(event.getCode().toString());

            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                currentlyActiveKeys.remove(event.getCode().toString());

            }
        });
    }


    public static File openSaveDialog() {
        File recordsDir = new File(System.getProperty("user.home"), "/DungeonJavaFX/records");
        if (!recordsDir.exists()) {
            recordsDir.mkdirs();
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(recordsDir);
        fileChooser.setTitle("Save");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All File", "*.*"));
        File file = fileChooser.showSaveDialog(stage);
        return file;
    }

    public static File openLoadedDialog() {
        File recordsDir = new File(System.getProperty("user.home"), "/DungeonJavaFX/records");
        if (!recordsDir.exists()) {
            recordsDir.mkdirs();
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(recordsDir);
        fileChooser.setTitle("Open");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All File", "*.*"));
        File file = fileChooser.showOpenDialog(stage);
        return file;
    }





    public static void main(String[] args) {
        launch();
    }
}