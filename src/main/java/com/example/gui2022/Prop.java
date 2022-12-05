package com.example.gui2022;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Prop {
    private int x, y;
    private Image image;
    private boolean canCollide;

    private Rectangle2D hitBox;

    public Prop(String path, int x, int y, boolean canCollide) {
        this.x = x;
        this.y = y;
        this.canCollide = canCollide;
        hitBox = new Rectangle2D(x,y, 50, 50);


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
            throw new RuntimeException("Could not load prop Image");
        }


    }

    public boolean colliding(Rectangle2D other) {
        return hitBox.intersects(other);
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(image, x, y);
    }
}
