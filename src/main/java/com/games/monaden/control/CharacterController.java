package com.games.monaden.control;

import com.games.monaden.model.primitives.MovementDirection;
import com.games.monaden.model.dialog.Dialog;
import com.games.monaden.model.primitives.Point;
import com.games.monaden.model.primitives.Transition;
import com.games.monaden.model.World;
import com.games.monaden.model.events.DialogEvent;
import com.games.monaden.model.gameobject.Character;
import com.games.monaden.model.gameobject.GameObject;
import com.games.monaden.view.Render;
import javafx.scene.input.KeyCode;

import java.util.Observable;

/**
 Class is responsible for all input that concerns the player character in the world.
 Moves the player, checks if any interactions should be started, calls for transitions between levels when needed.
 */
public class CharacterController extends Observable {

    private Character player;
    private MovementDirection newDirection;

    public CharacterController() {
        player = new Character(new Point(8 ,5), "characters/player.png", 32,32);
        player.setDirection(MovementDirection.LEFT);
        Render.getInstance().setPlayerCharacter(player);

    }

    public CharacterController(Character npc){
        player = npc;
    }

    /**
     * Mainly used for tests.
     * Should probably be removed after testing
     * @param point
     */
    CharacterController(Point point) {
        player = new Character(point, "cat.png", 32, 32);
    }

    public void handleMovement(KeyCode moveReq, World world) {
        MovementDirection dir = MovementDirection.UP;
        switch (moveReq) {
            case UP:
                dir = MovementDirection.UP;
                break;
            case DOWN:
                dir = MovementDirection.DOWN;
                break;
            case LEFT:
                dir = MovementDirection.LEFT;
                break;
            case RIGHT:
                dir = MovementDirection.RIGHT;
                break;
        }
        Point pointMovedTo = getPoint(player.getPosition(), dir);
        if (!tileIsOccupied(pointMovedTo, world)) {
            pointMovedTo = transitionIfPossible(world, pointMovedTo);
            player.setPosition(pointMovedTo);

            if (checkEvent(pointMovedTo, world)) {
                System.out.println("CheckEvent true!");
                setChanged();
                notifyObservers(getEvent(pointMovedTo, world));
            }
            if (newDirection != null) {
                dir = newDirection;
                newDirection = null;
            }
        }
        player.setDirection(dir);
    }

    private boolean characterOnTile (Point point, World world) {
        for (Character c : world.getInteractables()) {
            if (c.getPosition().equals(point)) {
                return true;
            }
        }
        return false;
    }

    private boolean objectOnTile (Point point, World world) {
        for (GameObject g : world.getObjects()) {
            if (g.getPosition().equals(point) && g.isSolid()) {
                return true;
            }
        }

        return false;
    }

    private boolean tileIsOccupied (Point point, World world) {
        return objectOnTile(point, world) || characterOnTile(point, world);
    }

    private Point transitionIfPossible (World world, Point point) {
        for (Transition t : world.getTransitions()) {
            if (t.pos.equals(point)) {
                if (t.direction != null){
                    newDirection = t.direction;
                }
                String newLevel = t.newLevel;
                setChanged();
                notifyObservers(newLevel);

                return t.newPos;
            }
        }
        return point;
    }

    //Called from outside this class when a transition is triggered somewhere else (for example from a dialog)
    public void transitionEvent(Transition t){
        player.setPosition(t.newPos);
    }

    private boolean checkEvent (Point point, World world) {
        for (DialogEvent de : world.getEvents()) {
            if (point.equals(de.getPosition())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Should be run after checkEvent() to avoid null.
     * @return
     */
    private DialogEvent getEvent (Point point, World world) {
        for (DialogEvent de : world.getEvents()) {
            if (point.equals(de.getPosition())) {
                return de;
            }
        }
        return null;
    }

    Point getPlayerPos () {
        return new Point(player.getPosition().getX(), player.getPosition().getY());
    }

    //Calculates a point in the direction next to the supplied point, taking into account world bounds
    private Point getPoint (Point currentPoint, MovementDirection direction) {
        Point newPoint = currentPoint.nextTo(direction);

        if(newPoint.getY() < 0 || newPoint.getY() >= World.MAP_SIZE
                || newPoint.getX() < 0 || newPoint.getX() >= World.MAP_SIZE) {
            return currentPoint;
        }

        return newPoint;

    }

    /**
     * Is Used only in test
     * @param funcReq
     * @param world
     * @return
     */
    public Dialog handleInteractions(KeyCode funcReq, World world){
        switch (funcReq) {
            case ESCAPE:
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
