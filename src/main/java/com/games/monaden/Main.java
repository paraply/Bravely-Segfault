package com.games.monaden;

import com.games.monaden.control.GameLoop;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

/**
 Main entry point of the program
 */

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        URL windowURL = getClass().getClassLoader().getResource("window.fxml");
        if (windowURL != null){
            Parent root = FXMLLoader.load( windowURL); // The JavaFX window
            primaryStage.setTitle("Bravely Segfault");
            primaryStage.setScene(new Scene(root, 800, 600));
            primaryStage.show();
            primaryStage.setOnCloseRequest(event -> System.out.println("USER CLOSED APPLICATION WINDOW"));
            GameLoop gameLoop = new GameLoop();
            gameLoop.initializeGame();
            gameLoop.start(); // starts the animation timer that calls handle() continuously
        }else{
            System.err.println("Main : Start, missing window.fxml in resources");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
