package com.games.monaden.model;

import com.games.monaden.model.gameObjects.GameObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anton on 2016-04-17.
 * There should only be a single instance of this class, but it should not have a global access point.
 * For now only sending a message to the console, should be handled in a better way
 */
public class World{
    private static boolean instantiated = false;

    public static final int tileSize = 32;
    public static final int mapSize = 16;

    private List<GameObject> objects = new ArrayList<>();
    private List<GameObject> interactables = new ArrayList<>();

    //Temporarily hardcoded here, should always load a map from a file
    private int[][] tileMap = new int[mapSize][mapSize];
    public int[][] getTileMap(){
        return tileMap;
    }

    public List<GameObject> getObjects(){
        return objects;
    }

    public World() {
        if(instantiated) {
            System.out.println("A world object has already been instantiated!");
        }
        instantiated = true;

        tileMap = new int[][]{
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        };

        for(int y = 0; y < mapSize; y++) {
            for(int x = 0; x < mapSize; x++) {
                if(CheckSolidTile(tileMap[y][x])){
                    objects.add(new GameObject(new Point(x,y), "objects", "wall.png",32,32));
                }
            }
        }

        objects.add(new GameObject(new Point(5,8), "objects", "tree.png",192,192));
    }

    public enum MovementDirection {
        UP, DOWN, LEFT, RIGHT
    }
    /** Checks if a movement in one Direction in the tilemap is possible or not
     *  Returns the new position after movement, and also handles potential new screen
     */
    public Point CheckMovement(Point p, MovementDirection direction) {
        Point newPoint = p.nextTo(direction);

        if(newPoint.getY() < 0 || newPoint.getY() >= mapSize
                || newPoint.getX() < 0 || newPoint.getX() >= mapSize)
            return p;

        for(GameObject g : objects) {
            if(g.getPosition().equals(newPoint)) return p;
        }

        return newPoint;
    }

    public String CheckInteraction(Point p, MovementDirection direction) {
        
        return "No interaction system is written yet";
    }

    /** Temporary helper function for tilemap solidity
     *  Will be refactored to some kind of data structure later
     */
    private boolean CheckSolidTile(int value)
    {
        return value == 1;
    }

}
