package com.games.monaden.control;

import com.games.monaden.model.Dialog;
import com.games.monaden.model.Point;
import com.games.monaden.model.Transition;
import com.games.monaden.model.World;
import com.games.monaden.model.gameObjects.Character;
import com.games.monaden.model.gameObjects.GameObject;
import com.games.monaden.view.Render;
import javafx.scene.input.KeyCode;

import java.util.Observable;

public class CharacterController extends Observable {

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
//        player.setPosition(world.checkMovement(player.getPosition(), dir));
        Point pointMovedTo = getPoint(player.getPosition(), dir);
        if (!tileIsOccupied(pointMovedTo, world)) {
            pointMovedTo = transitionIfPossible(world, pointMovedTo);
            player.setPosition(pointMovedTo);
        }
        player.setDirection(dir);
    }

    private Point transitionIfPossible (World world, Point point) {
        for (Transition t : world.getTransitions()) {
            if (t.pos.equals(point)) {
                String newLevel = t.newLevel;
                setChanged();
                notifyObservers(newLevel);
                return t.newPos;
            }
        }
        return point;
    }

    private boolean tileIsOccupied (Point point, World world) {
        for (GameObject g : world.getObjects()) {
            if (g.getPosition().equals(point) && g.isSolid()) {
                return true;
            }
        }

        for (Character c : world.getInteractables()) {
            if (c.getPosition().equals(point)) {
                return true;
            }
        }
        return false;
    }

    private Point getPoint (Point currentPoint, World.MovementDirection direction) {
        Point newPoint = currentPoint.nextTo(direction);

        if(newPoint.getY() < 0 || newPoint.getY() >= World.MAP_SIZE
                || newPoint.getX() < 0 || newPoint.getX() >= World.MAP_SIZE) {
            return currentPoint;
        }

        return newPoint;

    }

    public Dialog handleInteractions(KeyCode funcReq, World world){
        switch (funcReq) {
            case ESCAPE:
                System.out.println("ESCAPE");
                System.exit(0);
                break;
            case SPACE:
                Point newPoint = player.getPosition().nextTo(player.getDirection());
                for(Character c : world.getInteractables()) {
                    if(c.getPosition().equals(newPoint)) {
                        return c.getDialog();
                    }
                }
                break;
        }
        return null;
    }
}
