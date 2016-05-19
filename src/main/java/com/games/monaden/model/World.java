package com.games.monaden.model;

import com.games.monaden.model.gameObjects.Character;
import com.games.monaden.model.gameObjects.GameObject;
import com.games.monaden.services.dialogParser.DialogParser;
import com.games.monaden.services.levelParser.LevelParser;
import com.games.monaden.services.tileParser.TileParser;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
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

    private SAXParser parser;
    private LevelParser levelParser;
    private DialogParser dialogParser;
    private HashMap<Integer, Tile> tileMap;


    // Start the game using a starting level and the tilemap that was loaded in GameLoop : initializeGame
    public World(String startLevel, HashMap<Integer, Tile> tileMap) {
        if(instantiated) {
            System.err.println("A world object has already been instantiated!");
        }
        instantiated = true;
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            this.tileMap = tileMap;
            parser = factory.newSAXParser();
            levelParser = new LevelParser();
            dialogParser = new DialogParser();

            loadLevel(startLevel);
        } catch (Exception e) {
            System.err.println("World: Error in constructor: " + e.getMessage());
        }
    }

    private void loadLevel(String levelName){
        try {
            System.out.println("Parsing level: " + levelName);
            levelParser.clearTilemap();
            levelParser.clearInteractables();
            levelParser.clearTransitions();

            ClassLoader classLoader = this.getClass().getClassLoader();
            InputStream is = new FileInputStream(classLoader.getResource("maps/" + levelName).getFile());
//            File level = new File(classLoader.getResource("maps/" + levelName ).getFile());
//            System.out.println(level.getPath());
            parser.parse(is, levelParser);

            interactables = levelParser.getInteractables();
            addCharacterDialogs(interactables);
            System.out.println(interactables.get(0).getPosition().toString());
            transitions = levelParser.getTransitions();
            objects.clear();
            //Loop through the tilemap and create tiles for each
            for (int y = 0; y < MAP_SIZE; y++) {
                for (int x = 0; x < MAP_SIZE; x++) {
                    Tile currentTile =  tileMap.get( levelParser.getTileMap()[y][x] );
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
            System.err.println("World: Error loading level: " + levelName + " - " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void addCharacterDialogs (List<Character> characters) {

        for (Character c : characters) {
//            File dialogFile = new File(this.getClass().getResource("/dialogs/" + c.getDialogFile()).getPath());
//            System.out.println(dialogFile.getPath());
            try {
                ClassLoader classLoader = this.getClass().getClassLoader();
                InputStream is = new FileInputStream(classLoader.getResource("dialogs/" + c.getDialogFile()).getFile());
//                InputStream is = new FileInputStream(classLoader.getResource("/dialogs/" + c.getDialogFile()).toString());
//                Scanner scanner = new Scanner(is);
//                System.out.println("Scan: " + scanner.nextLine());
                parser.parse(is, dialogParser);
                c.setDialog(dialogParser.getRoot());
                dialogParser.reset();
            } catch (Exception e) {
                e.printStackTrace();
            }
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

        for (GameObject g : getInteractables()){
            if(g.getPosition().equals(newPoint) ){ // All interactables are solid
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
//                File dialogFile = new File("src/main/resources/parseTests/DialogTest.xml");
//                try {
//                    parser.parse(dialogFile, dialogParser);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                Dialog temp = dialogParser.getRoot();
//                Dialog temp = new Dialog("Hi, I'm Philip, an invisible level 24 typemancer");
//                temp.readInChoices("Fight me, you coward!", new Dialog("foo :: String -> String"));
//                temp.readInChoices("Okay bye", new Dialog(""));
//                return temp;
                return c.getDialog();
            }
        }

        return null;
    }
}
