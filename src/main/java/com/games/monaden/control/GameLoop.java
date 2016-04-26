package com.games.monaden.control;

import com.games.monaden.model.gameObjects.Character;
import com.games.monaden.model.gameObjects.GameObject;
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
    private GameObject tree;

    public void initialize_game(){
        world = new World();
        tree = new GameObject(new Point(4,8));
        player = new Character(new Point(1,1), world);
        Render.getInstance().setWorld(world);
        Render.getInstance().addCat(player);
        Render.getInstance().addTree(tree);
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
                switch (latest_movement_request) {
                    case UP:
                        player.Move(World.MovementDirection.UP);
                        System.out.println("MOVE UP");
                        break;
                    case DOWN:
                        player.Move(World.MovementDirection.DOWN);
                        System.out.println("MOVE DOWN");
                        break;
                    case LEFT:
                        player.Move(World.MovementDirection.LEFT);
                        System.out.println("MOVE LEFT");
                        break;
                    case RIGHT:
                        player.Move(World.MovementDirection.RIGHT);
                        System.out.println("MOVE RIGHT");
                        break;
                }
            }

            KeyCode latest_function_request = user_input.getLatestFunctionKey();
            if (latest_function_request != null) {
                switch (latest_function_request) {
                    case ESCAPE:
                        System.out.println("ESCAPE");
                        System.exit(0);
                        break;
                }
            }


            counting_down = FREQUENCY;
        }
    }
}
