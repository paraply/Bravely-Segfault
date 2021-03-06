package com.games.monaden.model;

import com.games.monaden.model.events.DialogEvent;
import com.games.monaden.model.gameobject.Character;
import com.games.monaden.model.gameobject.GameObject;
import com.games.monaden.model.primitives.Transition;

import java.util.*;

/**
 * There should only be a single instance of this class, but it should not have a global access point.
 * For now only sending a message to the console, should be handled in a better way
 */
public class World extends Observable{
    private static boolean instantiated = false;

    public static final int TILE_SIZE = 32;
    public static final int MAP_SIZE = 16;
    public static String playerName = "student";

    private List<GameObject> objects = new ArrayList<>();
    private List<Character> interactables = new ArrayList<>();
    private List<Transition> transitions = new ArrayList<>();
    private List<DialogEvent> events = new ArrayList<>();

    public List<GameObject> getObjects(){
        return objects;
    }
    public List<Character> getInteractables(){
        return interactables;
    }
    public List<Transition> getTransitions() { return transitions; }
    public List<DialogEvent> getEvents() {
        return events;
    }

    // Start the game using a starting level and the tilemap that was loaded in GameLoop : initializeGame
    public World() {
        if(instantiated) {
            System.err.println("A world object has already been instantiated!");
        }
        instantiated = true;

    }

    public void setCurrentLevel (List<GameObject> gameObjects, List<Character> interactables, List<Transition> transitions, List<DialogEvent> events) {
        this.objects = gameObjects;
        this.interactables = interactables;
        this.transitions = transitions;
        this.events = events;

        setChanged();
        notifyObservers();
    }

}
