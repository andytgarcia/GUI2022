package com.example.gui2022;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Player {
    private int x, y;
    Image image;

    Item testItem;

    public Player(String path, int x, int y) {
        this.x = x;
        this.y = y;
        createTestItem();

        //load and/or create character data file
        File imageFile = new File(path);
        if(!imageFile.exists()) {
            try {
                imageFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        //load external resources
        try {
            image = new Image(new FileInputStream("src/main/resources/com/example/gui2022/guy.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Could not load player Image");
        }
    }

    public void moveUp(){
        y -= 2;
    }

    public void moveDown(){
        y += 2;
    }

    public void moveRight(){
        x += 2;
    }

    public void moveLeft(){
        x -= 2;
    }

    public void testCollision(GraphicsContext gc) {
        Rectangle2D myHitBox = new Rectangle2D(x, y, 50, 50);
        if (testItem.colliding(myHitBox)) {
            gc.setFill(Paint.valueOf("RED"));
            gc.fillText("Hello", 300, 300);
        }
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(image,x,y);
        testItem.draw(gc);
    }

    public void createTestItem() {
        testItem = new Item("guy.png", 100, 100, false, "Test Description");
    }


}