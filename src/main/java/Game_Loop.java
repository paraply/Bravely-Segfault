import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;

/**
 * Created by paraply on 2016-04-13.
 */
public class Game_Loop extends AnimationTimer{
    Key_Events key_events = Key_Events.getInstance();
    Player player = Player.getInstance();

    // Could probably use inspiration from
    // https://carlfx.wordpress.com/2012/04/09/javafx-2-gametutorial-part-2/

    final int COUNTDOWN_MAX = 20;
    int counting_down = COUNTDOWN_MAX;


    @Override
    public void handle(long now) {

        if (counting_down > 0){
            counting_down--;
        }else{

            KeyCode latest_movement_request = key_events.getLatestMovementRequest();
            if (latest_movement_request != null) {
                switch (latest_movement_request) {
                    case UP:
                        player.moveUp();
                        break;
                    case DOWN:
                        player.moveDown();
                        break;
                    case LEFT:
                        player.moveLeft();
                        break;
                    case RIGHT:
                        player.moveRight();
                        break;
                }
            }

            KeyCode latest_function_request = key_events.getLatestFunctionRequest();
            if (latest_function_request != null) {
                switch (latest_function_request) {
                    case ESCAPE:
                        System.out.println("ESCAPE");
                        System.exit(0);
                        break;
                }
            }
            counting_down = COUNTDOWN_MAX;
        }
    }
}
