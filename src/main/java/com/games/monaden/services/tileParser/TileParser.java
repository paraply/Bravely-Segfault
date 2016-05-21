package com.games.monaden.services.tileParser;

import com.games.monaden.model.Tile;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Philip on 2016-04-21.
 * Parses
 */
public class TileParser extends DefaultHandler {
    private boolean bName = false;
    private boolean bGraphics = false;
    private boolean bSolid = false;
    private boolean bAnimated = false;

    private int id;
    private String name;
    private File filepath;
    private boolean solidness = false;
    private boolean animated = false;

    private List<Tile> tileList = new ArrayList<>();

    @Override
    public void startElement (String uri, String localName, String qName,
                              Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("tile")) {
            id = Integer.parseInt(attributes.getValue("id"));
        } else if (qName.equalsIgnoreCase("name")) {
            bName = true;
        } else if (qName.equalsIgnoreCase("graphics")) {
            bGraphics = true;
        } else if (qName.equalsIgnoreCase("solidness")) {
            bSolid = true;
        }   else if (qName.equalsIgnoreCase("animation")) {
            bAnimated = true;
    }
    }

    @Override
    public void endElement (String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("tile")) {
            createTile();
        }
    }

    @Override
    public void characters (char ch[], int start, int length) throws SAXException {
        if (bName) {
            this.name = new String(ch, start, length);
//            System.out.println("Name found: " + name);
            bName = false;
        } else if (bGraphics) {
            this.filepath = new File(new String (ch, start, length));
//            System.out.println("Filepath found: " + filepath);
            bGraphics = false;
        } else if (bSolid) {
            if ("solid".equalsIgnoreCase(new String(ch, start, length))) {
                this.solidness = true;
            }
//            System.out.println("Solidness found: " + solidness);
            bSolid = false;
        } else if (bAnimated){
            if ("animated".equalsIgnoreCase(new String(ch, start, length))) {
                this.animated = true;
            }
//            System.out.println("Animated found: " + animated + " " + name);
            bAnimated = false;
        }
    }

    /**
     * Should be called after fully parsing one tile
     */
    private void createTile () {
        try {
            tileList.add(new Tile(id, name, solidness, filepath, animated));
        } catch (NullPointerException e) {
            System.err.println("Something was missing when creating a tile");
        }
    }

    /**
     * Should be called after fully parsing all tiles from a file.
     * @return a sorted copy of the list of all parsed tiles.
     */
    public List<Tile> getTiles () {
        Collections.sort(tileList);
        checkDuplicateID();
        return new ArrayList<>(tileList);
    }

    public void clearTiles () {
        tileList.clear();
    }

    /**
     * Checks if the parsed content contains duplicate IDs.
     * Prints a message with the found duplicates if true.
     * @return true if two or more tiles share IDs
     */
    private boolean checkDuplicateID () {
        boolean duplicate = false;
        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append("Duplicates found:\n");
        Collections.sort(tileList);
        for (int i = 0; i < tileList.size() - 1; i++){
            if (tileList.get(i).getId() == tileList.get(i+1).getId()) {
                int id = tileList.get(i).getId();
                duplicate = true;
                errorMessage.append("ID: ");
                errorMessage.append(id);
                errorMessage.append("\tNames: ");
                errorMessage.append(tileList.get(i).getName());
                for (int j = i+1; j < tileList.size() && tileList.get(j).getId() == id; j++) {
                    errorMessage.append(", ");
                    errorMessage.append(tileList.get(j).getName());
                    i = j;
                }
                errorMessage.append('\n');
            }
        }

        if (duplicate) {
            throw new IllegalStateException(errorMessage.toString());
        }

        return duplicate;
    }
}
