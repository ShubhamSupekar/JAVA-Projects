package com.example.snakeladder;

import javafx.application.Application;
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

import java.io.IOException;

public class SnakeLadder extends Application {

    public static final int tileSize=40, width=10, height=10;
    public  static final int buttonLine = height*tileSize+30,infoline=buttonLine-30;
    private static Dice dice = new Dice();
    private Player playerOne,playerTwo;
    private boolean gameStart = false, playerOneTurn=false,playerTowTurn=false;
    private Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(width*tileSize,height*tileSize+100);
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                Tile tile = new Tile(tileSize);
                tile.setTranslateX(j*tileSize);
                tile.setTranslateY(i*tileSize);
                root.getChildren().add(tile);
            }
        }

        Image img = new Image("https://media.istockphoto.com/id/531466314/es/vector/snakes-and-ladders.jpg?s=1024x1024&w=is&k=20&c=sk8Z8yU8y5Evy19mRDPDB1R30fruBit-8o6kIFF98bE=");
        ImageView board  = new ImageView();
        board.setImage(img);
        board.setFitHeight(height*tileSize);
        board.setFitWidth(width*tileSize);

        //buttons
        Button playerOneButton = new Button("Player One");
        Button playerTwoButton = new Button("Player Two");
        Button startButton = new Button("Start");
        playerOneButton.setTranslateY(buttonLine);
        playerOneButton.setTranslateX(20);
        playerOneButton.setDisable(true);
        playerTwoButton.setTranslateY(buttonLine);
        playerTwoButton.setTranslateX(300);
        playerTwoButton.setDisable(true);
        startButton.setTranslateY(buttonLine);
        startButton.setTranslateX(180);

        //Info display
        Label playerOneLabel = new Label("");
        Label playerTwoLabel = new Label("");
        Label diceLabel = new Label("Start the Game");

        playerOneLabel.setTranslateY(infoline);
        playerOneLabel.setTranslateX(10);
        playerTwoLabel.setTranslateY(infoline);
        playerTwoLabel.setTranslateX(290);
        diceLabel.setTranslateY(infoline);
        diceLabel.setTranslateX(160);

        playerOne = new Player(tileSize, Color.BLACK,"Shubham");
        playerTwo = new Player(tileSize-5,Color.SNOW,"Soham");

        //Player Action
        playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStart){
                    if(playerOneTurn){
                        int diceValue = dice.getRolledDice();
                        diceLabel.setText("Dice value: "+diceValue);
                        playerOne.movePlayer(diceValue);
                        if(playerOne.isWinner()){
                            diceLabel.setText("Winner is: "+playerOne.getName());
                            playerTowTurn = false;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");

                            playerOneTurn = false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");

                            startButton.setDisable(false);
                            startButton.setText("Restart");
                            gameStart=false;
                        }else {
                            playerOneTurn = false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");

                            playerTowTurn = true;
                            playerTwoButton.setDisable(false);
                            playerTwoLabel.setText("Your Turn " + playerTwo.getName());
                        }
                    }
                }
            }
        });

        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStart){
                    if(playerTowTurn){
                        int diceValue = dice.getRolledDice();
                        diceLabel.setText("Dice value: "+diceValue);
                        playerTwo.movePlayer(diceValue);
                        //winning condition
                        if(playerTwo.isWinner()){
                               diceLabel.setText("Winner is: "+playerTwo.getName());
                            playerTowTurn = false;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");

                            playerOneTurn = false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");

                            startButton.setDisable(false);
                            startButton.setText("Restart");
                            gameStart=false;
                        }else {
                            playerTowTurn = false;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");

                            playerOneTurn = true;
                            playerOneButton.setDisable(false);
                            playerOneLabel.setText("Your Turn "+playerOne.getName());
                        }
                    }
                }
            }
        });

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStart=true;
                diceLabel.setText("Game Started!");
                startButton.setDisable(true);

                playerOneTurn=true;
                playerOneLabel.setText("Your Turn "+playerOne.getName());
                playerOneButton.setDisable(false);
                playerOne.startingPosition();

                playerTowTurn=false;
                playerTwoLabel.setText("");
                playerTwoButton.setDisable(true);
                playerTwo.startingPosition();
            }
        });

        root.getChildren().addAll(board,playerOneButton,playerTwoButton,startButton,
                    playerOneLabel,playerTwoLabel,diceLabel,playerOne.getCoin(),playerTwo.getCoin()
                );

        return  root;
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake & Ladder !");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}