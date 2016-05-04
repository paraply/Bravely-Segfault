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
    private Character player;

    public void initialize_game(){
        world = new World("src/main/resources/parseTests/start.xml" );
        player = new Character(new Point(5,14), "cat.png", 32,32);
        Render.getInstance().setWorld(world);
        Render.getInstance().setPlayerCharacter(player);
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
                World.MovementDirection dir = World.MovementDirection.UP;
                switch (latest_movement_request) {
                    case UP:
                        dir = World.MovementDirection.UP;
                        System.out.println("MOVE UP");
                        break;
                    case DOWN:
                        dir = World.MovementDirection.DOWN;
                        System.out.println("MOVE DOWN");
                        break;
                    case LEFT:
                        dir = World.MovementDirection.LEFT;
                        System.out.println("MOVE LEFT");
                        break;
                    case RIGHT:
                        dir = World.MovementDirection.RIGHT;
                        System.out.println("MOVE RIGHT");
                        break;
                }
                player.setPosition(world.CheckMovement(player.getPosition(), dir));
                player.setDirection(dir);
            }

            KeyCode latest_function_request = user_input.getLatestFunctionKey();
            if (latest_function_request != null) {
                switch (latest_function_request) {
                    case ESCAPE:
                        System.out.println("ESCAPE");
                        System.exit(0);
                        break;
                    case SPACE:
                        //Temporarily a string since no dialogue system written yet
                        String temp = world.CheckInteraction(player.getPosition(), player.getDirection());
                        System.out.println(temp);
                        break;
                }
            }


            counting_down = FREQUENCY;
        }
    }
}
