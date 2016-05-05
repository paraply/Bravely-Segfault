package com.games.monaden;

import com.games.monaden.control.GameLoop;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by paraply on 2016-04-13.
 */

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("window.fxml")); // The JavaFX window
        primaryStage.setTitle("Bravely Segfault");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> System.out.println("USER CLOSED APPLICATION WINDOW"));
        GameLoop gameLoop = new GameLoop();
        gameLoop.initializeGame();
        gameLoop.start(); // starts the animation timer that calls handle() continuously
    }

    public static void main(String[] args) {
        launch(args);
    }
}
