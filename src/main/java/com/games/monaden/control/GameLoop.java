package com.games.monaden.control;

import com.games.monaden.model.World;
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
    private int countDown = FREQUENCY;

    private World world;
    CharacterController playerCharacter;

    public void initializeGame(){
        world = new World("start.xml" );
        Render.getInstance().setWorld(world);
        playerCharacter = new CharacterController();
    }

    @Override
    public void handle(long now) {
        Render.getInstance().redraw();
        if (countDown > 0){  // used to add a delay (better than sleep) to user movement
            countDown--;
        }else{
            UserInput userInput = UserInput.getInstance();
            KeyCode moveReq = userInput.getLatestMovementKey();
            if (moveReq != null) {
                playerCharacter.handleMovement(moveReq, world);
            }

            KeyCode funcReq = userInput.getLatestFunctionKey();
            if (funcReq != null) {
                playerCharacter.handleInteractions(funcReq, world);
            }
            countDown = FREQUENCY;
        }
    }
}
