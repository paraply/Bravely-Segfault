package control;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


/**
 * Created by paraply on 2016-04-13.
 */
public class User_Input implements EventHandler<Event> {
    private static User_Input user_input;
    private KeyCode movement_key;
    private KeyCode function_key;

    private User_Input(){}

    @Override
    public void handle(Event event) {
        KeyEvent e = (KeyEvent) event;
        switch (e.getCode()){
            case UP:
                movement_key = KeyCode.UP;
                break;
            case DOWN:
                movement_key = KeyCode.DOWN;
                break;
            case LEFT:
                movement_key = KeyCode.LEFT;
                break;
            case RIGHT:
                movement_key = KeyCode.RIGHT;
                break;
            case ESCAPE:
                function_key = KeyCode.ESCAPE;
                break;
            case ENTER:
                function_key = KeyCode.ENTER;
                break;
        }
    }

    public KeyCode getLatestMovementKey(){
        KeyCode key = movement_key;
        movement_key = null;
        return key;
    }

    public KeyCode getLatestFunctionKey(){
        KeyCode key = function_key;
        function_key = null;
        return key;
    }

    public static synchronized User_Input getInstance(){
        if (user_input == null){
            user_input = new User_Input();
        }
        return user_input;
    }
}
