package com.example.gui2022;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Canvas gameCanvas;

    @FXML
    private Button editNameButton;
    @FXML
    private TextField nameField;

    @FXML
    private Label nameLabel;
    int x = 100, y = 100;

    @FXML
    private Button saveCharacterButton, strengthRollButton, dexterityRollButton, intelligenceRollButton, constitutionRollButton, charismaRollButton, wisdomRollButton;
    @FXML
    private Label strengthValueLabel, dexterityValueLabel, intelligenceValueLabel, constitutionValueLabel, charismaValueLabel, wisdomValueLabel;

    int count = 0;
    boolean changeName = false;

    boolean characterCreated = false;

    @FXML
    public void initialize() throws FileNotFoundException {
        GraphicsContext gc = gameCanvas.getGraphicsContext2D();
        Font font = Font.font("Times New Roman", FontWeight.BOLD, 48);
        gc.setFont(font);
        nameField.setEditable(false);
        nameField.setVisible(false);
        saveCharacterButton.setVisible(false);

        final Image image = new Image(new FileInputStream("src/main/resources/com/example/gui2022/guy.png"));



        AnimationTimer animTime = new AnimationTimer() {

            @Override
            public void handle(long l) {
                //gc.clearRect(0, 0, 800, 600);
                gc.setFill(Color.DARKSLATEGRAY);
                gc.fillRect(0, 0, 800, 600);
                if(!characterCreated) {
                    gc.setFill(Color.RED);
                    gc.setStroke(Color.BLACK);
                    gc.setLineWidth(2);

                    gc.fillText("No Character", x, y);
                    gc.fillText("Create Character", x, y + 100);

                }
                else {
                    gc.drawImage(image, 100, 100);
                }
            }
        };

        animTime.start();



    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void setEditNameButton() {
        String buttonText = editNameButton.getText();
        if (buttonText.equals("Edit")) {
            editNameButton.setText("Save");
            nameLabel.setVisible(false);
            nameField.setVisible(true);
            nameField.setEditable(true);

        }
        else if (buttonText.equals("Save")) {
            editNameButton.setText("Edit");
            nameLabel.setVisible(true);
            nameField.setVisible(false);
            nameField.setEditable(false);
            nameLabel.setText(nameField.getText());
            if (!changeName)
                count++;
            changeName = true;
        }
    }
    @FXML
    protected void onSaveButtonClicked() {
        saveCharacterButton.setVisible(false);
        strengthRollButton.setVisible(false);
        dexterityRollButton.setVisible(false);
        intelligenceRollButton.setVisible(false);
        constitutionRollButton.setVisible(false);
        charismaRollButton.setVisible(false);
        wisdomRollButton.setVisible(false);
        characterCreated = true;
    }

    protected int rolld20() {
        return (int)(1 + Math.random() * 20);
    }



    @FXML
    protected void onRollButton(ActionEvent event) {
        Button b = (Button)event.getSource();

        if(b == strengthRollButton && b.isVisible()) {
            strengthValueLabel.setText("" + rolld20());
            if (b.getText().equals("Roll Again..."))
                b.setVisible(false);
            else {
                b.setText("Roll Again...");
                b.setPrefSize(120, 30);
                count++;
            }
        }
        else if(b == dexterityRollButton) {
            dexterityValueLabel.setText("" + rolld20());
            if (b.getText().equals("Roll Again..."))
                b.setVisible(false);
            else {
                b.setText("Roll Again...");
                b.setPrefSize(120, 30);
                count++;
            }
        }
        else if(b == constitutionRollButton) {
            constitutionValueLabel.setText("" + rolld20());
            if (b.getText().equals("Roll Again..."))
                b.setVisible(false);
            else {
                b.setText("Roll Again...");
                b.setPrefSize(120, 30);
                count++;
            }
        }
        else if(b == intelligenceRollButton) {
            intelligenceValueLabel.setText("" + rolld20());
            if (b.getText().equals("Roll Again..."))
                b.setVisible(false);
            else {
                b.setText("Roll Again...");
                b.setPrefSize(120, 30);
                count++;
            }
        }
        else if(b == wisdomRollButton) {
            wisdomValueLabel.setText("" + rolld20());
            if (b.getText().equals("Roll Again..."))
                b.setVisible(false);
            else {
                b.setText("Roll Again...");
                b.setPrefSize(120, 30);
                count++;
            }
        }
        else if(b == charismaRollButton) {
            charismaValueLabel.setText("" + rolld20());
            if (b.getText().equals("Roll Again..."))
                b.setVisible(false);
            else {
                b.setText("Roll Again");
                b.setPrefSize(120, 30);
                count++;
            }
        }

        if (count >= 7)
            saveCharacterButton.setVisible(true);
    }//end onRollButton



}