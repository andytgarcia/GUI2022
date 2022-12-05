package com.example.gui2022;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Item extends Prop{
    private String description;

    public Item(String path, int x, int y, boolean canCollide, String description) {
        super(path, x, y, canCollide);
        this.description = description;
    }

    public void inspect(GraphicsContext gc) {
        gc.fillText("Hello", 100, 100);
    }
}
