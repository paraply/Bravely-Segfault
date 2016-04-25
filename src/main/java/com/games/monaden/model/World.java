package com.games.monaden.model;

/**
 * Created by Anton on 2016-04-17.
 * There should only be a single instance of this class, but it should not have a global access point.
 * For now only sending a message to the console, should be handled in a better way
 */
public class World{

    public static final int tileSize = 32;
    public static final int mapSize = 16;

    //Temporarily hardcoded here, should always load a map from a file
    //Currently hardcoded to have size 8*10 with magic numbers, should be changed ASAP!
    private int[][] tileMap = new int[mapSize][mapSize];

    private static boolean instantiated = false;

    public int[][] getTileMap(){
        return tileMap;
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
    }

    public enum MovementDirection {
        UP, DOWN, LEFT, RIGHT
    }
    /** Checks if a movement in one Direction in the tilemap is possible or not
     *  Returns the new position after movement, and also handles potential new screen
     */
    public Point CheckMovement(Point p, MovementDirection direction) {
        Point newPoint = p;
        switch(direction){
            case UP:
                newPoint = new Point(p.getX(), p.getY() - 1);
                break;
            case DOWN:
                newPoint = new Point(p.getX(), p.getY() + 1);
                break;
            case LEFT:
                newPoint = new Point(p.getX() - 1, p.getY());
                break;
            case RIGHT:
                newPoint = new Point(p.getX() + 1, p.getY());
                break;
        }
        if(newPoint.getY() < 0 || newPoint.getY() >= mapSize
                || newPoint.getX() < 0 || newPoint.getX() >= mapSize
                || CheckSolidTile(tileMap[p.getY()][p.getX()]))
            return p;

        return newPoint;
    }

    /** Temporary helper function for tilemap solidity
     *  Will be refactored to some kind of data structure later
     */
    private boolean CheckSolidTile(int value)
    {
        return value == 1;
    }

}
