package com.games.monaden.model;

import com.games.monaden.model.gameObjects.Character;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * Created by Philip on 2016-04-26.
 *
 */
public class TileMapParser extends DefaultHandler {

    private boolean bLine = false;
    private boolean bCharName = false;
    private boolean bCharPos = false;
    private boolean bGraphics = false;
    private boolean bFile = false;
    private boolean bFrame = false;
    private boolean bDialogue = false;

    private String charName;
    private Point charPos;


    @Override
    public void startElement (String uri, String localName, String qName,
                              Attributes attributes) throws SAXException {
        switch (qName.toLowerCase()) {

            case "line":
                bLine = true;
                break;

            case  "characters":
            //TODO: New character?
                break;

            case "character":
                charName = attributes.getValue("name");
                break;

            case "position":
                bCharPos = true;
                break;

            case "graphics":
                bGraphics = true;
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
            
        }
    }

    @Override
    public void characters (char ch[], int start, int length) throws SAXException {

    }
}
