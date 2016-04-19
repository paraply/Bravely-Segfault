package Control;

/**
 * Created by paraply on 2016-04-13.
 */
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;

import java.net.URL;
import java.util.ResourceBundle;

public class WindowController implements Initializable{
    @FXML private Canvas main_canvas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        main_canvas.setFocusTraversable(true); // May be necessary to get key events
        main_canvas.setOnKeyPressed(User_Input.getInstance());
    }
}
