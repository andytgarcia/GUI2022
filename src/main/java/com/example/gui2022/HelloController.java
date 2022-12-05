package com.example.gui2022;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Scanner;

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
    int x = 100, y = 100, x1 = 100, y1 = 100;

    @FXML
    private Button saveCharacterButton, strengthRollButton, dexterityRollButton, intelligenceRollButton, constitutionRollButton, charismaRollButton, wisdomRollButton;
    @FXML
    private Label strengthValueLabel, dexterityValueLabel, intelligenceValueLabel, constitutionValueLabel, charismaValueLabel, wisdomValueLabel;

    int count = 0;
    boolean changeName = false;

    boolean characterCreated = false;

    private File characterFile;

    Player player;

    //public GraphicsContext gc = gameCanvas.getGraphicsContext2D();
    //final Image image = new Image(new FileInputStream("src/main/resources/com/example/gui2022/guy.png"));





    @FXML
    public void initialize() throws FileNotFoundException {
        GraphicsContext gc = gameCanvas.getGraphicsContext2D();
        Font font = Font.font("Times New Roman", FontWeight.BOLD, 48);
        gc.setFont(font);
        nameField.setEditable(false);
        nameField.setVisible(false);
        System.out.println("test");

        saveCharacterButton.setVisible(false);

        player = new Player("guy.png", 100, 100);



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
                    movement();
                    player.testCollision(gc);
                    player.draw(gc);
                }
            }

        };

        animTime.start();

    }
    @FXML
    protected void onSaveMenuClicked() throws FileNotFoundException {
        File file = HelloApplication.openSaveDialog();
        if (file != null) {
            file = new File(file.getAbsolutePath() + ".deg");
            Formatter output = new Formatter(file);
            output.format("%s, %s, %s, %s, %s, %s, %s", nameField.getText(), strengthValueLabel.getText(), dexterityValueLabel.getText(), intelligenceValueLabel.getText(),
                    constitutionValueLabel.getText(), wisdomValueLabel.getText(), charismaValueLabel.getText());
            output.close();
        }
    }

    @FXML
    protected void onLoadingDialogue() throws FileNotFoundException {
        File file = HelloApplication.openLoadedDialog();
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        if (file != null) {
            try  {
                bufferedReader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            String content = stringBuilder.toString();
            String[] sep = content.split(",");
            System.out.println(Arrays.toString(sep));


            nameLabel.setText(sep[0]);
            nameField.setText(sep[0]);
            editNameButton.setVisible(false);

            strengthValueLabel.setText(sep[1]);
            strengthRollButton.setVisible(false);

            dexterityValueLabel.setText(sep[2]);
            dexterityRollButton.setVisible(false);

            intelligenceValueLabel.setText(sep[3]);
            intelligenceRollButton.setVisible(false);

            constitutionValueLabel.setText(sep[4]);
            constitutionRollButton.setVisible(false);

            charismaValueLabel.setText(sep[5]);
            charismaRollButton.setVisible(false);

            wisdomValueLabel.setText(sep[6]);
            wisdomRollButton.setVisible(false);

            characterCreated = true;
            //find way to draw image



        }



    }

    @FXML
    protected void movement(){
        if(HelloApplication.currentlyActiveKeys.contains(KeyCode.A.toString())){
            player.moveLeft();
        }
        if (HelloApplication.currentlyActiveKeys.contains(KeyCode.D.toString())) {
            player.moveRight();
        }
        if (HelloApplication.currentlyActiveKeys.contains(KeyCode.S.toString())) {
            player.moveDown();
        }
        if (HelloApplication.currentlyActiveKeys.contains(KeyCode.W.toString())) {
            player.moveUp();
        }
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
                b.setText("Roll Again...");
                b.setPrefSize(120, 30);
                count++;
            }
        }

        if (count >= 7)
            saveCharacterButton.setVisible(true);
    }//end onRollButton





}