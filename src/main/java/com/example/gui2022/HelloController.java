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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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
    int x = 100, y = 100;

    @FXML
    private Button saveCharacterButton, strengthRollButton, dexterityRollButton, intelligenceRollButton, constitutionRollButton, charismaRollButton, wisdomRollButton;
    @FXML
    private Label strengthValueLabel, dexterityValueLabel, intelligenceValueLabel, constitutionValueLabel, charismaValueLabel, wisdomValueLabel;

    int count = 0;
    boolean changeName = false;

    boolean characterCreated = false;

    private File characterFile;

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

        characterFile = new File("character.deg");
        if(!characterFile.exists()) {
            try {
                characterFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
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
                    ImageView imageView = new ImageView(image);
                    //imageView.setOnKeyPressed();




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
                    constitutionValueLabel.getText(), charismaValueLabel.getText(), wisdomValueLabel.getText());
            output.close();
        }
    }

    @FXML
    protected void onLoadingDialogue() throws FileNotFoundException {
        File file = HelloApplication.openLoadedDialog();
        if (file != null) {
            ArrayList<String> listOfAttributes = new ArrayList<>();
            try (Scanner scanner = new Scanner(file)) {

                while (scanner.hasNext()) {
                    listOfAttributes.add(scanner.next());
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println(listOfAttributes);
            for (int i = 0; i < listOfAttributes.size(); i++) {
                if (listOfAttributes.get(i).contains(","))
                    removeCommas(listOfAttributes.get(i));
            }
            nameLabel.setText(listOfAttributes.get(0));
            nameField.setText(listOfAttributes.get(0));
            strengthValueLabel.setText(listOfAttributes.get(1));
            dexterityValueLabel.setText(listOfAttributes.get(2));
            intelligenceValueLabel.setText(listOfAttributes.get(3));
            constitutionValueLabel.setText(listOfAttributes.get(4));
            charismaValueLabel.setText(listOfAttributes.get(5));
            wisdomValueLabel.setText(listOfAttributes.get(6));
            //find way to draw image

        }



    }

    private String removeCommas(String s) {
        return s.substring(0, s.indexOf(","));
    }



    @FXML
    protected void movement(){
        if(HelloApplication.currentlyActiveKeys.contains(KeyCode.A.toString())){
            moveLeft();
        }
        if (HelloApplication.currentlyActiveKeys.contains(KeyCode.D.toString())) {
            moveRight();
        }
        if (HelloApplication.currentlyActiveKeys.contains(KeyCode.S.toString())) {
            moveDown();
        }
        if (HelloApplication.currentlyActiveKeys.contains(KeyCode.W.toString())) {
            moveUp();
        }
    }

    private void moveUp(){
        y -= 1;
    }

    private void moveDown(){
        y += 1;
    }

    private void moveRight(){
        x += 1;
    }

    private void moveLeft(){
        x -= 1;
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