package com.games.monaden.services.levelParser;

import com.games.monaden.model.Point;
import com.games.monaden.model.Transition;
import com.games.monaden.model.World;
import com.games.monaden.model.gameObjects.Character;
import com.games.monaden.model.gameObjects.GameObject;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Philip on 2016-04-26.
 * A SAXParser for Tilemaps. After parsing, the parser will create objects to get.
 */
public class LevelParser extends DefaultHandler {

    //TODO: Missing handling of frame count, dialogue, and transitions.

    private boolean bLine = false;
    private boolean bGameObject = false;
    private boolean bCharName = false;
    private boolean bPosition = false;
    private boolean bFile = false;
    private boolean bSolidness = false;
    private boolean bZOrder = false;
    private boolean bTransition = false;
    private boolean bTransPos = false;
    private boolean bFrame = false;
    private boolean bDialogue = false;

    private int row = 0;
    private int [][] tileMap = new int [World.MAP_SIZE][World.MAP_SIZE];
    private boolean solidness = false;
    private int zOrder = 0;
    private String charName;
    private Point position;
    private Point transPos;
    private String imageFile;
    private File dialogFile;

    private List<GameObject> gameObjects = new ArrayList<>();
    private List<Character> interactables = new ArrayList<>();
    private List<Transition> transitions = new ArrayList<>();

    public LevelParser() {
        super();
    }

    @Override
    public void startElement (String uri, String localName, String qName,
                              Attributes attributes) throws SAXException {
        switch (qName.toLowerCase()) {

            case "line":
                bLine = true;
                break;

//            case  "characters":
//            //TODO: New character?
//                break;

            case "gameobject":
                bGameObject = true;
                break;

            case "character":
                charName = attributes.getValue("name");
                bCharName = true;
                break;

            case "position":
                bPosition = true;
                break;

//            case "graphics":
//                bGraphics = true;
//                break;

            case "filename":
                bFile = true;
                break;

            case "frameCount":
            bFrame = true;
            break;

            case "zorder":
                bZOrder = true;
                break;

            case "solidness":
            bSolidness = true;
            break;

            case "dialogue":
                bDialogue = true;
                break;

            case "transition":
                bTransition = true;
                break;

            case "newposition":
                bTransPos = true;
                break;
        }
    }

    @Override
    public void endElement (String uri, String localName, String qName) throws SAXException{
        switch (qName.toLowerCase()) {
            case "tilemap":
                if (row != 16) {
                    try {
                        throw new Exception("The XML file does not contain 16 lines of tiles!");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case "gameobject":
                GameObject gameObject = new GameObject(position, imageFile, solidness, zOrder);
                gameObjects.add(gameObject);
                zOrder = 0;
                break;
            case "character":
                Character character = new Character(position, imageFile, zOrder);
                if (bCharName && charName != null) {
                    character.setName(charName);
                    bCharName = false;
                    charName = null;
                }
                if (dialogFile != null) {
                    character.setDialogFile(dialogFile);
                }
                interactables.add(character);
                zOrder = 0;
                break;
            case "transition":
                transitions.add(new Transition(position, transPos, imageFile));
                break;
        }
    }

    @Override
    public void characters (char ch[], int start, int length) throws SAXException {
        //Parses a line and puts it into the tilemap
        if (bLine) {
            String [] line = new String(ch, start, length).split(",");
            int [] lineInt = new int[line.length];
            for (int i = 0; i < line.length; i++) {
                lineInt[i] = Integer.parseInt(line[i]);
            }
            for (int i = 0; i < lineInt.length; i++) {
                tileMap[row][i] = lineInt[i];
            }
            row++;
            bLine = false;
        } else if (bPosition) {
            String [] point = new String(ch, start, length).split(",");
            position = new Point(Integer.parseInt(point[0])
                    , Integer.parseInt(point[1]));
            bPosition = false;
        } else if (bFile) {
            imageFile = new String(ch, start, length);
            bFile = false;
        } else if (bZOrder) {
            zOrder = Integer.parseInt(new String(ch, start, length));
            bZOrder = false;
        } else if (bSolidness) {
            String solidString = new String(ch, start, length);
            if (solidString.equals("solid")) {
                solidness = true;
            } else {
                solidness = false;
            }
            bSolidness = false;
        } else if (bDialogue) {
            dialogFile = new File (new String(ch, start, length));
            bDialogue = false;
        } else if(bTransPos){
            String [] point = new String(ch, start, length).split(",");
            transPos = new Point(Integer.parseInt(point[0])
                    , Integer.parseInt(point[1]));
            bTransPos = false;
        }
    }

    /**
     * Returns a copy of the GameObjects list
     * @return a copy of the GameObjects list
     */
    public List<GameObject> getGameObjects() {
        return new ArrayList<>(this.gameObjects);
    }

    /**
     * Returns a copy of the interactables list
     * @return a copy of the interactables list
     */
    public List<Character> getInteractables() {
        return new ArrayList<>(this.interactables);
    }


    /**
     * Returns a copy of the transitions list
     * @return a copy of the transitions list
     */
    public List<Transition> getTransitions() {
        return new ArrayList<>(this.transitions);
    }


    /**
     * Clones every row of the tilemap and returns these rows as a copy of the tilemap.
     * @return a copy of the tilemap
     */
    public int[][] getTileMap () {
        int [][] mapCopy = new int[World.MAP_SIZE][World.MAP_SIZE];
        for (int i = 0; i < World.MAP_SIZE; i++) {
            mapCopy[i] = tileMap[i].clone();
        }
        return mapCopy;
    }

    /**
     * Clears the GameObjects list
     */
    public void clearGameObjects() {
        gameObjects.clear();
    }

    /**
     * Clears the interactables list
     */
    public void clearInteractables() {
        interactables.clear();
    }

    /**
     * Clears the transitions list
     */
    public void clearTransitions() {
        transitions.clear();
    }

    /**
     * Clears the tilemap by setting all cells in the matrix to 0
     */
    public void clearTilemap () {
        int[] empty = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        for (int i = 0; i < World.MAP_SIZE; i++) {
            tileMap[i] = empty.clone();
        }
        row = 0;
    }
}
