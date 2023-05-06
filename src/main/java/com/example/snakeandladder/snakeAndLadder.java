package com.example.snakeandladder;

import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class snakeAndLadder extends Application {

    public static final int tileSize=40, width=10, height=10;
    public static final int buttonLine = height*tileSize + 35; //we can adjust place of button by increasing or
    // decreasing the value of buttonLine
    public static final int infoLine = buttonLine - 20;

    private static Dice dice = new Dice();
    private Player playerOne , playerTwo;   //and board is already related with player

    private boolean gameStarted = false, playerOneTurn = true, playerTwoTurn = false;

    private Pane createContent() throws FileNotFoundException {
        Pane root = new Pane();
        root.setPrefSize(width*tileSize , height*tileSize + 100); //giving size of pane

        //loop for creating 100 tiles and put them in pane
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Tile tile = new Tile(tileSize);  //instantiating Tile class
                tile.setTranslateX(j*tileSize);
                tile.setTranslateY(i*tileSize);
                root.getChildren().add(tile); // to add  children (i.e components) in the root (i.e pane)
            }
        }
        //now to bring snakes, ladders and all
        FileInputStream file = null;
            file = new FileInputStream("/Users/sumeetraina/IdeaProjects/snakeAndLadder/src/main/img.png");

        Image img = new  Image(file);
        // loaded our image
        //Now, displaying the image on the UI (ImageView control)
        ImageView board = new ImageView();  //Created imageview control to view image
        board.setImage(img); //setting our image
        board.setFitHeight(height*tileSize);
        board.setFitWidth(width*tileSize);

        //Buttons
        Button playerOneButton = new Button("Player 1");  //player 1 button
        Button playerTwoButton = new Button("Player 2");  //player 2 button
        Button startButton = new Button("Start");  //start button

        //Now, adding these button to the pane
        playerOneButton.setTranslateY(buttonLine);
        playerOneButton.setTranslateX(20);
        playerOneButton.setDisable(true); //initially button will be disabled

        //similarly, for button two and button three
        playerTwoButton.setTranslateY(buttonLine);
        playerTwoButton.setTranslateX(320);
        playerTwoButton.setDisable(true); // initially button will be disabled

        startButton.setTranslateY(buttonLine);
        startButton.setTranslateX(178);

        //Now creating three labels for these buttons and add it

        //Info Display
        Label playerOneLabel = new Label(""); // no label initially before starting the game
        Label playerTwoLabel = new Label(""); //-- do --
        Label diceLabel = new Label("Start the Game");

        //
        playerOneLabel.setTranslateY(infoLine);
        playerOneLabel.setTranslateX(12);

        playerTwoLabel.setTranslateY(infoLine);
        playerTwoLabel.setTranslateX(308);

        diceLabel.setTranslateY(infoLine);
        diceLabel.setTranslateX(160);

        //Initializing player
        playerOne = new Player(tileSize , Color.BLACK, "Ashima");
        playerTwo = new Player(tileSize - 5,Color.WHITE,"Sumit");//sizes are diff so if overlap they can be seen

        //Player Action
        // For Player 1 Button
        playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(playerOneTurn){
                        //PLAYING THE GAME
                        int diceValue = Dice.getRolledDiceValue(); //we rolled the dice
                        diceLabel.setText("Dice value : "+diceValue); //displayed the no. on dice
                        playerOne.movePlayer(diceValue); // moving the player

                        //WINNING CONDITION
                        if(playerOne.isWinner()){
                            diceLabel.setText(playerOne.getName()+" Won !"); //Announce Winner
                            //Now if someone wins, we'll disable both the buttons
                            playerOneTurn = false;
                            playerOneButton.setDisable(true);//disabling the button
                            playerOneLabel.setText(""); //also removing text

                            playerTwoTurn = false;
                            playerTwoButton.setDisable(true); //disabling the button
                            playerTwoLabel.setText(""); //removing text

                            //But we need to enable the Start Button once someone wins
                            startButton.setDisable(false);
                            startButton.setText("Restart Game");
                            gameStarted = false;
                        }
                        else{

                            //DISABLING PLAYER 1 (which has played)
                            //Now after movingonce we'll set plyr 1 turn as false and plyr 2 turn as true
                            playerOneTurn = false;
                            //also text should to be changed after moving the coin and enable plyr 2 button and disable plyr 1 button
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");

                            //ENABLING THE OTHER PLAYER TO PLAY
                            playerTwoTurn = true;
                            playerTwoButton.setDisable(false);
                            playerTwoLabel.setText("Your turn "+playerTwo.getName());
                        }

                    }
                }

            }
        });
        //Similarly for Player Two Button
        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(playerTwoTurn){
                        //PLAYING THE GAME
                        int diceValue = Dice.getRolledDiceValue(); //we rolled the dice
                        diceLabel.setText("Dice value : "+diceValue); //displayed the no. on dice
                        playerTwo.movePlayer(diceValue); //moving the player

                        //WINNING CONDITION
                        if(playerTwo.isWinner()){
                            diceLabel.setText(playerTwo.getName()+" Won !"); //Announce Winner
                            //Now if someone wins, we'll disable both the buttons
                            playerOneTurn = false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText(""); //also removing text

                            playerTwoTurn = false;
                            playerTwoButton.setDisable(true); //disabling the button
                            playerTwoLabel.setText(""); //removing text

                            //But we need to enable the Start Button once someone wins
                            startButton.setDisable(false);
                            startButton.setText("Restart Game");
                        }
                        else{
                            //DISABLING PLAYER 2 (which has played)
                            //Now after moving once we'll set plyr 1 turn as false and plyr 2 turn as true
                            playerOneTurn = true;
                            //also text should to be changed after moving the coin and enable plyr 2 button and disable plyr 1 button
                            playerOneButton.setDisable(false);
                            playerOneLabel.setText("Your turn "+playerOne.getName());

                            //ENABLING THE OTHER PLAYER (player 1) TO PLAY
                            playerTwoTurn = false;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText(" ");
                        }
                    }
                }

            }
        });
        startButton.setOnAction(new EventHandler<ActionEvent>() { //whenever this button is clicked we wnt game to be started
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStarted = true;
                //then we wnt to label there "game Started"
                diceLabel.setText("Game Started");
                startButton.setDisable(true); // it disables the button so that it can't be clicked anymore
                playerOneTurn = true;
                playerOneLabel.setText("Your turn "+playerOne.getName());
                playerOneButton.setDisable(false);
                playerOne.startingPosition();

                playerTwoTurn = false; //set it false
                playerTwoLabel.setText("");//and erase the text on player 2 label coz turn is of player 1
                playerTwoButton.setDisable(true); //also disable plyr 2 button
                playerTwo.startingPosition();
            }
        });


        //adding everything to the root
        root.getChildren().addAll(board,playerOneButton,playerTwoButton,startButton,playerOneLabel,playerTwoLabel,
                diceLabel,playerOne.getCoin(),playerTwo.getCoin());


        return root;
    }

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake & Ladder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
