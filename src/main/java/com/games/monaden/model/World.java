package com.games.monaden.model;

import com.games.monaden.model.gameObjects.GameObject;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
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

    private SAXParserFactory factory;
    private SAXParser parser;
    private LevelParser levelParser;

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

        // Initiate levelParser with this world as argument
        try {
            factory = SAXParserFactory.newInstance();
            parser = factory.newSAXParser();
        } catch (Exception e) {
            e.printStackTrace();
        }
        levelParser = new LevelParser(this);

        tileMap = new int[][]{
                {1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1},
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
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        };

        for(int y = 0; y < mapSize; y++) {
            for(int x = 0; x < mapSize; x++) {
                if(CheckSolidTile(tileMap[y][x])){
                    objects.add(new GameObject(new Point(x,y), "objects", "wall.png"));
                }
            }
        }
        GameObject tree = new GameObject(new Point(5,8), "objects", "tree.png",192,192);
        objects.add(tree);

        GameObject fire = new GameObject(new Point(10,8), "objects", "fire.png");
        fire.setContinuousAnimation(true);
        objects.add(fire);


        GameObject fire2 = new GameObject(new Point(11,8), "objects", "fire.png");
        fire2.setContinuousAnimation(true);
        objects.add(fire2);

        GameObject explosion = new GameObject(new Point(5,5), "objects", "explosion.png",160,160);
        explosion.setContinuousAnimation(true);
        explosion.setAnimationFrames(9);
        objects.add(explosion);

    }

    /**
     * Sets the current level to the given XML-file
     * @param mapFile XML-file that describes the level
     */
    public void setLevel (File mapFile) {
        try {
            levelParser.clearTilemap();
            levelParser.clearInteractables();
            parser.parse(mapFile, levelParser);

            tileMap = levelParser.getTileMap();
            interactables = levelParser.getInteractables();

        } catch (Exception e) {
            e.printStackTrace();
        }
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
        Point newPoint = p.nextTo(direction);

        for(GameObject g : interactables) {
            if(g.getPosition().equals(newPoint))
                return "There is an interactive object in front of the player. Start interaction.";
        }

        return "There was nothing to interact with.";
    }

    /** Temporary helper function for tilemap solidity
     *  Will be refactored to some kind of data structure later
     */
    private boolean CheckSolidTile(int value)
    {
        return value == 1;
    }

}
