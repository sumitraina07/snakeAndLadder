package com.example.snakeandladder;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {  // created new 'tile' class & extended rectangle class

    public Tile(int tileSize){
        setWidth(tileSize);
        setHeight(tileSize);
        setFill(Color.AZURE); // to give color to tile
        setStroke(Color.BLACK); // to give border color
    }
}
