package com.games.monaden.control;

import com.games.monaden.model.Point;
import com.games.monaden.model.World;
import com.games.monaden.model.gameObjects.Character;
import com.games.monaden.model.gameObjects.GameObject;
import com.games.monaden.view.Render;
import javafx.scene.input.KeyCode;

/**
 * Created by Anton on 2016-05-05.
 */
public class CharacterController {

    private Character player;
    public CharacterController() {
        player = new Character(new Point(5,14), "cat.png", 32,32);
        Render.getInstance().setPlayerCharacter(player);
    }

    public void handleMovement(KeyCode moveReq, World world) {
        World.MovementDirection dir = World.MovementDirection.UP;
        switch (moveReq) {
            case UP:
                dir = World.MovementDirection.UP;
//                System.out.println("MOVE UP");
                break;
            case DOWN:
                dir = World.MovementDirection.DOWN;
//                System.out.println("MOVE DOWN");
                break;
            case LEFT:
                dir = World.MovementDirection.LEFT;
//                System.out.println("MOVE LEFT");
                break;
            case RIGHT:
                dir = World.MovementDirection.RIGHT;
//                System.out.println("MOVE RIGHT");
                break;
        }
        player.setPosition(world.checkMovement(player.getPosition(), dir));
        player.setDirection(dir);
    }

    public void handleInteractions(KeyCode funcReq, World world){
        switch (funcReq) {
            case ESCAPE:
                System.out.println("ESCAPE");
                System.exit(0);
                break;
            case SPACE:
                //Temporarily a string since no dialogue system written yet
                String temp = world.checkInteraction(player.getPosition(), player.getDirection());
                System.out.println(temp);
                break;
        }
    }
}
