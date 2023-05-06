package com.example.snakeandladder;

import javafx.util.Pair;

import java.util.ArrayList;

public class Board{
    ArrayList<Pair<Integer,Integer>> positionCoordinates; //indices of array will be used as our numbers
    //On each index we put their x & y coordinates

    ArrayList<Integer> snakeLadderPosition;


    //Constructor for board
    public Board(){
        positionCoordinates = new ArrayList<>(); //initializing the object
        populatePositionCoordinates();
        populateSnakeLadder();
    }
    private void populatePositionCoordinates(){ //method to populate our array
        positionCoordinates.add(new Pair<>(0,0));// for zeroth index we give any dummy value (like 0,0)
        for (int i = 0; i < snakeAndLadder.height; i++) {
            for (int j = 0; j < snakeAndLadder.width; j++) {
                //Now, We need to generate 'x' coordinate as well as 'y' coordinate
                //x coordinate
                int xCord = 0;
                if(i%2==0){ // x will increase if i is even
                    xCord = j*snakeAndLadder.tileSize + snakeAndLadder.tileSize/2;
                }
                else{ // x will decrease i is odd
                    xCord = snakeAndLadder.tileSize*snakeAndLadder.height - (j*snakeAndLadder.tileSize)
                            - snakeAndLadder.tileSize/2;
                }
                //y coordinate
                int yCord = snakeAndLadder.tileSize*snakeAndLadder.height - (i*snakeAndLadder.tileSize)
                     - snakeAndLadder.tileSize/2; //we reached till bottom end, and now we want to decrease the y value
                  // as we move up on the numbers
                // now add this thing to array
                positionCoordinates.add(new Pair<>(xCord,yCord));
            }
        }
    }

    //Considering Snakes & Ladders on the board
    private void populateSnakeLadder(){
        snakeLadderPosition = new ArrayList<>();
        //Adding all the nos.first
        for (int i = 0; i < 101; i++) {
            snakeLadderPosition.add(i);
        }
        //Mapping snakes and ladders
        //LADDERS
        snakeLadderPosition.set(2,23);
        snakeLadderPosition.set(6,45);
        snakeLadderPosition.set(20,59);
        snakeLadderPosition.set(52,72);
        snakeLadderPosition.set(57,96);
        snakeLadderPosition.set(71,92);

        //SNAKES
        snakeLadderPosition.set(43,17);
        snakeLadderPosition.set(50,5);
        snakeLadderPosition.set(56,8);
        snakeLadderPosition.set(73,15);
        snakeLadderPosition.set(84,58);
        snakeLadderPosition.set(87,49);
        snakeLadderPosition.set(98,40);


    }

    public int getNewPosition(int currentPostion){ //New position tells if snake/ladder comes then wht will be the new position
        if(currentPostion > 0 && currentPostion <= 100){
            return snakeLadderPosition.get(currentPostion);
        }
        //it will be same position if there is not any snake/Ladder
        return -1;
    }
    //getPosition for X coordinate
    public int getXCoordinate(int position){
        if(position >= 1 && position <= 100){
            return positionCoordinates.get(position).getKey();
        }
        return -1;
    }
    //Similarly for Y coordinate..... and linking it with player since we need coordinates to move
    public int getYCoordinate(int position){
        if(position >= 1 && position <= 100){
            return positionCoordinates.get(position).getValue();
        }
        return -1;
    }

//    public static void main(String[] args) { //Writing main to test our above function properly and dont get any errors
//        Board board = new Board();
//        for (int i = 0; i < board.positionCoordinates.size(); i++) {
//            System.out.println(i+" $  x : "+board.positionCoordinates.get(i).getKey()+
//                    "   y : "+board.positionCoordinates.get(i).getValue()
//            );
//        }
//    }
}
