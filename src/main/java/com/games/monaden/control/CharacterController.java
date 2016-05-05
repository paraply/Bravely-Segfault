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

    public void handleMovement(KeyCode latest_movement_request, World world) {
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
        CheckMovement(player.getPosition(), dir, world);
        player.setDirection(dir);
    }

    public void handleInteractions(KeyCode latest_function_request, World world){
        switch (latest_function_request) {
            case ESCAPE:
                System.out.println("ESCAPE");
                System.exit(0);
                break;
            case SPACE:
                //Temporarily a string since no dialogue system written yet
                String temp = CheckInteraction(player.getPosition(), player.getDirection(), world);
                System.out.println(temp);
                break;
        }
    }


    /** Checks if a movement in one Direction in the tilemap is possible or not
     *  Returns the new position after movement, and also handles potential new screen
     */
    public void CheckMovement(Point p, World.MovementDirection direction, World world) {
        Point newPoint = p.nextTo(direction);

        if(newPoint.getY() < 0 || newPoint.getY() >= World.mapSize
                || newPoint.getX() < 0 || newPoint.getX() >= World.mapSize)
            return;

//      Loop through every object and find the one at the position we want to move to
//      If that object is solid then it will not be possible
        for(GameObject g : world.getObjects()) {
            if(g.getPosition().equals(newPoint)){
                if (g.getSolidness()){
                    return;
                }
            }
        }

        if(world.getTransitions().containsKey(p)){
            //Should call for a levelparse using the filepath in transitions.get(p)
            //Should set the character at the new position from the level-file
            return;
        }

        player.setPosition(newPoint);
    }

    public String CheckInteraction(Point p, World.MovementDirection direction, World world) {
        Point newPoint = p.nextTo(direction);

        for(GameObject g : world.getInteractables()) {
            if(g.getPosition().equals(newPoint))
                return "There is an interactive object in front of the player. Start interaction.";
        }

        return "There was nothing to interact with.";
    }
}
