package com.games.monaden.model;

import com.games.monaden.model.gameObjects.GameObject;
import com.games.monaden.services.levelParser.LevelParser;
import com.games.monaden.services.tileParser.TileParser;

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

    public static final int TILE_SIZE = 32;
    public static final int MAP_SIZE = 16;

    private List<GameObject> objects = new ArrayList<>();
    private List<GameObject> interactables = new ArrayList<>();
    private HashMap<Point, String> transitions = new HashMap<>();

    public List<GameObject> getObjects(){
        return objects;
    }
    public List<GameObject> getInteractables(){
        return interactables;
    }
    public HashMap<Point, String> getTransitions() { return transitions; }

    private SAXParser parser;
    private LevelParser levelParser;
    private List<Tile> tileList;

    public World(String levelfile) {
        if(instantiated) {
            System.out.println("A world object has already been instantiated!");
        }
        instantiated = true;
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            parser = factory.newSAXParser();
            levelParser = new LevelParser();
            TileParser tileParser = new TileParser();
            File tileFile = new File("src/main/resources/parseTests/TileTest1.xml");
            parser.parse(tileFile, tileParser);
            tileList = tileParser.getTiles();

            loadLevel(levelfile);
        } catch (Exception e) {
            System.err.println("Error in world constructor: " + e.getMessage());
        }
    }

    private void loadLevel(String levelFile){
        try {
            levelParser.clearTilemap();
            //levelParser.clearCharacters();
            File level = new File(levelFile);
            parser.parse(level, levelParser);

            interactables = levelParser.getInteractables();

            //Loop through the tilemap and create tiles for each
            for (int y = 0; y < MAP_SIZE; y++) {
                for (int x = 0; x < MAP_SIZE; x++) {
                    Tile currentTile = tileList.get(levelParser.getTileMap()[y][x]);
                    GameObject newGameObject = new GameObject(new Point(x, y), "objects", currentTile.getFilepath().toString(), currentTile.isSolid());
                    newGameObject.setContinuousAnimation(currentTile.isAnimated());
                    objects.add(newGameObject);
                }
            }

            notifyObservers("transition");
        }
        catch(Exception e)
        {
            System.err.println("Error loading level: " + e.getMessage());
        }
    }

    public enum MovementDirection {
        UP, DOWN, LEFT, RIGHT
    }
}
