package com.games.monaden.model;

import com.games.monaden.model.gameObjects.Character;
import com.games.monaden.model.gameObjects.GameObject;
import com.games.monaden.services.levelParser.LevelParser;
import com.games.monaden.services.tileParser.TileParser;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

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

    private SAXParser parser;
    private LevelParser levelParser;
    private List<Tile> tileList;

    public World(String startLevel) {
        if(instantiated) {
            System.out.println("A world object has already been instantiated!");
        }
        instantiated = true;
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            parser = factory.newSAXParser();
            levelParser = new LevelParser();
            TileParser tileParser = new TileParser();
//            File tileFile = new File( "src/main/resources/parseTests/TileTest1.xml");
            // Use a relative path to get the filelist file in tiles folder
            File tileFile =  new File(this.getClass().getResource("/tiles/tilelist.xml").getPath());
            parser.parse(tileFile, tileParser);
            tileList = tileParser.getTiles();

            loadLevel(startLevel);
        } catch (Exception e) {
            System.err.println("Error in world constructor: " + e.getMessage());
        }
    }

    private void loadLevel(String levelName){
        try {
            System.out.println("Parsing: " + levelName);
            levelParser.clearTilemap();
            levelParser.clearInteractables();
            levelParser.clearTransitions();
//          levelParser.clearCharacters();

//          using relative paths for tiles and maps. renamed objects folder to tiles
            File level = new File(this.getClass().getResource("/maps/" + levelName ).getPath());
            parser.parse(level, levelParser);

            interactables = levelParser.getInteractables();
            System.out.println(interactables.get(0).getPosition().toString());
            transitions = levelParser.getTransitions();
            objects.clear();
            //Loop through the tilemap and create tiles for each
            for (int y = 0; y < MAP_SIZE; y++) {
                for (int x = 0; x < MAP_SIZE; x++) {
                    Tile currentTile = tileList.get(levelParser.getTileMap()[y][x]);
                    GameObject newGameObject = new GameObject(new Point(x, y), "tiles", currentTile.getFilepath().toString(), currentTile.isSolid());
                    newGameObject.setContinuousAnimation(currentTile.isAnimated());
                    objects.add(newGameObject);
                }
            }
            setChanged();
            notifyObservers("transition");
        }
        catch(Exception e)
        {
            System.err.println("Error loading level: " + levelName + " - " + e.getMessage());
            System.err.println(e.getStackTrace());
        }
    }

    public enum MovementDirection {
        UP, DOWN, LEFT, RIGHT
    }

    /** Checks if a movement in one Direction in the tilemap is possible or not
     *  Returns the new position after movement, and also handles potential new screen
     */
    public Point checkMovement(Point currentPoint, World.MovementDirection direction) {
        Point newPoint = currentPoint.nextTo(direction);

        if(newPoint.getY() < 0 || newPoint.getY() >= World.MAP_SIZE
                || newPoint.getX() < 0 || newPoint.getX() >= World.MAP_SIZE)
            return currentPoint;

//      Loop through every object and find the one at the position we want to move to
//      If that object is solid then it will not be possible
        for(GameObject g : getObjects()) {
            if(g.getPosition().equals(newPoint) && g.isSolid()){
                return currentPoint;
            }
        }

        for(Transition t : getTransitions()){
            if(t.pos.equals(newPoint)) {
                //Should call for a levelparse using the filepath in t
                loadLevel(t.newLevel);
                return t.newPos;
            }
        }

        return newPoint;
    }

    public Dialog checkInteraction(Point currentPoint, World.MovementDirection direction) {
        Point newPoint = currentPoint.nextTo(direction);
        for(Character c : getInteractables()) {
            if(c.getPosition().equals(newPoint)) {
                //return c.getDialog();
                Dialog temp = new Dialog("Hi, I'm Philip, an invisible level 24 typemancer");
                temp.readInChoices("Fight me, you coward!", new Dialog("foo :: String -> String"));
                return temp;
            }
        }

        return null;
    }
}
