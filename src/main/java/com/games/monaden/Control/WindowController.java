package com.games.monaden.Control;

/**
 * Created by paraply on 2016-04-13.
 */

import com.games.monaden.View.Render;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;

import java.net.URL;
import java.util.ResourceBundle;

public class WindowController implements Initializable{
    @FXML private Canvas main_canvas; // Our canvas where the game will be drawn

    // Called when JavaFX initialized the window.
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        main_canvas.setFocusTraversable(true); // May be necessary to get key events
        main_canvas.setOnKeyPressed(User_Input.getInstance());
        Render.getInstance().setGraphicsContext(main_canvas.getGraphicsContext2D()); // Get the canvas context and send it to Render
    }

}
