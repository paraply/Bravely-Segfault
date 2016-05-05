package com.games.monaden.control;

import com.games.monaden.model.gameObjects.Character;
import com.games.monaden.model.World;
import com.games.monaden.model.Point;
import com.games.monaden.view.Render;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;

/**
 * Created by paraply on 2016-04-13.
 */
public class GameLoop extends AnimationTimer {

    // Could probably use inspiration from
    // https://carlfx.wordpress.com/2012/04/09/javafx-2-gametutorial-part-2/

    public final static int FREQUENCY = 16;
    private int counting_down = FREQUENCY;

    private World world;
    CharacterController playerCharacter;

    public void initialize_game(){
        world = new World("src/main/resources/parseTests/start.xml" );
        Render.getInstance().setWorld(world);
        playerCharacter = new CharacterController();
    }

    @Override
    public void handle(long now) {
        Render.getInstance().redraw();

        if (counting_down > 0){  // used to add a delay (better than sleep) to user movement
            counting_down--;
        }else{
            UserInput user_input = UserInput.getInstance();
            KeyCode latest_movement_request = user_input.getLatestMovementKey();
            if (latest_movement_request != null) {
                playerCharacter.handleMovement(latest_movement_request, world);
            }

            KeyCode latest_function_request = user_input.getLatestFunctionKey();
            if (latest_function_request != null) {
                playerCharacter.handleInteractions(latest_function_request, world);
            }
            counting_down = FREQUENCY;
        }
    }
}
