package com.example.snakeandladder;

public class Dice {

    //Here we'll generate a random no b/w 1 to 6 and return it
    public static int getRolledDiceValue(){
        return (int)(Math.random()*6 + 1);
    }
}
