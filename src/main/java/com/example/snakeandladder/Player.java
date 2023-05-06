package com.example.snakeandladder;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Player {

    private  Circle coin;
    private  int currentPosition;
    private String name;

    private static Board gameBoard = new Board(); //creating object of Board class to get position of x & y

    //creating constructor
    public Player(int tileSize, Color coinColor, String playerName){
        coin = new Circle(tileSize/2);
        coin.setFill(coinColor);
        currentPosition = 0;
        movePlayer(1);
        name = playerName;
    }
    public void movePlayer(int diceValue){   //method to move player
        if(currentPosition + diceValue <=100){   //will not move if no.goes beyond 100
             currentPosition += diceValue;

             TranslateTransition firstMove = translateAnimation(diceValue), secondMove = null;

             int newPosition = gameBoard.getNewPosition(currentPosition);
             if(newPosition != currentPosition && newPosition != -1){
                 currentPosition = newPosition;
                 secondMove = translateAnimation(6);
                 //so our player will move frm current pos to the tile value and then frm there to the next pos
                 //be it an end of a ladder or a tail of a snake
             }
             if(secondMove == null){
                 firstMove.play(); //if sendMove is null then just play the first move
             }
             else{ //Move the first one then wait for a while and then move the second one
                 SequentialTransition sequentialTransition = new SequentialTransition(firstMove,
                         new PauseTransition(Duration.millis(700)), secondMove);
                 sequentialTransition.play();

             }
        }

        //Moving the player by translating the co-ordinates
//        int x = gameBoard.getXCoordinate(currentPosition);
//        int y = gameBoard.getYCoordinate(currentPosition);
//        //move coin
//        coin.setTranslateX(x);
//        coin.setTranslateY(y);

    }


    // Adding animation to our move of coin
    private  TranslateTransition translateAnimation(int diceValue){
        TranslateTransition animate = new TranslateTransition(Duration.millis(200*diceValue),coin);
        animate.setToX(gameBoard.getXCoordinate(currentPosition));
        animate.setToY(gameBoard.getYCoordinate(currentPosition));
        animate.setAutoReverse(false);
        return animate;
    }

    //Func to bring players to the starting point once any player wins
    //this func we'll call for player 1 as well as player 2
    public void startingPosition(){
        currentPosition = 0;
        movePlayer(1);
    }

    boolean isWinner(){
        if(currentPosition == 100){
            return true;
        }
        return false;
    }

    //Getters generated
    public Circle getCoin() {   //getters
        return coin;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public String getName() {
        return name;
    }
}
