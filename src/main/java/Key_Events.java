import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Created by paraply on 2016-04-13.
 */
public class Key_Events implements EventHandler<Event> {
    private static Key_Events key_events;
    private KeyCode movement_key;
    private KeyCode function_key;

    private Key_Events(){}

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

    public KeyCode getLatestMovementRequest(){
        KeyCode key = movement_key;
        movement_key = null;
        return key;
    }

    public KeyCode getLatestFunctionRequest(){
        KeyCode key = function_key;
        function_key = null;
        return key;
    }

    public static synchronized Key_Events getInstance(){
        if (key_events == null){
            key_events  = new Key_Events();
        }
        return key_events;
    }
}
