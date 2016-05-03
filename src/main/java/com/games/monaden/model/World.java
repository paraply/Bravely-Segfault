package com.games.monaden.model;

import com.games.monaden.model.gameObjects.GameObject;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

/**
 * Created by Anton on 2016-04-17.
 * There should only be a single instance of this class, but it should not have a global access point.
 * For now only sending a message to the console, should be handled in a better way
 */
public class World extends Observable{
    private static boolean instantiated = false;

    public static final int tileSize = 32;
    public static final int mapSize = 16;

    private List<GameObject> objects = new ArrayList<>();
    private List<GameObject> interactables = new ArrayList<>();
    private HashMap<Point, String> transitions = new HashMap<>();

    public List<GameObject> getObjects(){
        return objects;
    }


    private SAXParserFactory factory = SAXParserFactory.newInstance();
    private SAXParser parser;
    private LevelParser levelParser;
    private File mapFile,tileFile;
    private List<Tile> tileList;
    private static TileParser tileParser;

    public World(String levelfile) {
        if(instantiated) {
            System.out.println("A world object has already been instantiated!");
        }
        instantiated = true;
        try {

//          Parser that will read XML-files with map and tile data
            parser = factory.newSAXParser();

//          Parser for levels. We need to provide this world object since a world object is required to create characters
            levelParser = new LevelParser(this);
            levelParser.clearTilemap();
//            levelParser.clearCharacters();
            mapFile = new File(levelfile);
            parser.parse(mapFile, levelParser);

            interactables = levelParser.getInteractables();
            notifyObservers("transition");
            tileParser = new TileParser();
            tileFile = new File("src/main/resources/parseTests/TileTest1.xml");
            parser.parse(tileFile, tileParser);
            tileList = tileParser.getTiles();

//            Loop through the tilemap and create tiles for each
            for(int y = 0; y < mapSize; y++) {
                for (int x = 0; x < mapSize; x++) {
                    Tile currentTile = tileList.get(levelParser.getTileMap()[y][x]);
                    GameObject newGameObject = new GameObject(new Point(x, y), "objects", currentTile.getFilepath().toString(), currentTile.getSolidness());
                    newGameObject.setContinuousAnimation(currentTile.getAnimated());
                    objects.add(newGameObject);
                }
            }


        } catch (Exception e) {
            System.err.println("Error in world constructor: " + e.getMessage());
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

//      Loop through every object and find the one at the position we want to move to
//      If that object is solid then it will not be possible
        for(GameObject g : objects) {
            if(g.getPosition().equals(newPoint)){
                if (g.getSolidness()){
                    return p;
                }
            }
        }

        if(transitions.containsKey(p)){
            //Should call for a levelparse using the filepath in transitions.get(p)
            //Should set the character at the new position from the level-file
            return p;
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

}
