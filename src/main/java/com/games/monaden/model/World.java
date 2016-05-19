package com.games.monaden.model;

import com.games.monaden.model.gameObjects.Character;
import com.games.monaden.model.gameObjects.GameObject;

import java.util.*;

/**
 * Created by Anton on 2016-04-17.
 * There should only be a single instance of this class, but it should not have a global access point.
 * For now only sending a message to the console, should be handled in a better way
 */
public class World extends Observable{
    private static boolean instantiated = false;

    public static final int TILE_SIZE = 32;
    public static final int MAP_SIZE = 16;

    private List<GameObject> objects = new ArrayList<>();
    private List<Character> interactables = new ArrayList<>();
    private List<Transition> transitions = new ArrayList<>();

    public List<GameObject> getObjects(){
        return objects;
    }
    public List<Character> getInteractables(){
        return interactables;
    }
    public List<Transition> getTransitions() { return transitions; }


    // Start the game using a starting level and the tilemap that was loaded in GameLoop : initializeGame
    public World() {
        if(instantiated) {
            System.err.println("A world object has already been instantiated!");
        }
        instantiated = true;
    }

    public void setCurrentLevel (List<GameObject> gameObjects, List<Character> interactables, List<Transition> transitions) {
        this.objects = gameObjects;
        this.interactables = interactables;
        this.transitions = transitions;
        
        setChanged();
        notifyObservers();
    }

//    public void addCharacterDialogs (List<Character> characters) {
//
//        for (Character c : characters) {
//            try {
//                ClassLoader classLoader = this.getClass().getClassLoader();
//                InputStream is = classLoader.getResourceAsStream("dialogs/" + c.getDialogFile());
//                parser.parse(is, dialogParser);
//                c.setDialog(dialogParser.getRoot());
//                dialogParser.reset();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public enum MovementDirection {
        UP, DOWN, LEFT, RIGHT
    }


    public Dialog checkInteraction(Point currentPoint, World.MovementDirection direction) {
        Point newPoint = currentPoint.nextTo(direction);
        for(Character c : getInteractables()) {
            if(c.getPosition().equals(newPoint)) {
                return c.getDialog();
            }
        }
        return null;
    }
}
