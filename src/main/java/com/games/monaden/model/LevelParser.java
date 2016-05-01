package com.games.monaden.model;

import com.games.monaden.model.gameObjects.Character;
import com.games.monaden.model.gameObjects.GameObject;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Philip on 2016-04-26.
 * A SAXParser for Tilemaps. After parsing, the parser will create objects to get.
 */
public class LevelParser extends DefaultHandler {

    //TODO: Missing handling of frame count, dialogue, and transitions.

    private World world;

    private boolean bLine = false;
    private boolean bCharName = false;
    private boolean bCharPos = false;
    private boolean bFile = false;
    private boolean bFrame = false;
    private boolean bDialogue = false;

    private int row = 0;
    private int [][] tileMap = new int [World.mapSize][World.mapSize];
    private String charName;
    private Point charPos;
    private String imageFile;

    private List<GameObject> characters = new ArrayList<>();

    public LevelParser(World world) {
        super();
        this.world = world;
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

            case "character":
                charName = attributes.getValue("name");
                break;

            case "position":
                bCharPos = true;
                break;

            case "graphics":
                //bGraphics = true;
                break;

            case "fileName":
                bFile = true;
                break;

            case "frameCount":
                bFrame = true;
                break;

            case "dialogue":
                bDialogue = true;
                break;

            case "transition":
                //TODO: ???
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
            case "character":
                characters.add(new Character(charPos, world, imageFile));
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
        } else if (bCharName) {
            charName = new String(ch, start, length);
            bCharName = false;
        } else if (bCharPos) {
            String [] point = new String(ch, start, length).split(",");
            charPos = new Point(Integer.parseInt(point[0])
                    , Integer.parseInt(point[1]));
            bCharPos = false;
        } else if (bFile) {
            imageFile = new String(ch, start, length);
            bFile = false;
        }
    }

    /**
     * Returns a copy of the character list
     * @return a copy of the character list
     */
    public List<GameObject> getCharacters () {
        return new ArrayList<>(this.characters);
    }

    /**
     * Clones every row of the tilemap and returns these rows as a copy of the tilemap.
     * @return a copy of the tilemap
     */
    public int[][] getTileMap () {
        int [][] mapCopy = new int[World.mapSize][World.mapSize];
        for (int i = 0; i < World.mapSize; i++) {
            mapCopy[i] = tileMap[i].clone();
        }
        return mapCopy;
    }

    /**
     * Clears the character list
     */
    public void clearCharacters () {
        characters.clear();
    }

    /**
     * Clears the tilemap by setting all cells in the matrix to 0
     */
    public void clearTilemap () {
        int[] empty = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        for (int i = 0; i < World.mapSize; i++) {
            tileMap[i] = empty.clone();
        }
    }
}
