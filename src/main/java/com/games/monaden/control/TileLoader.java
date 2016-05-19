package com.games.monaden.control;


import com.games.monaden.model.Tile;
import com.games.monaden.services.tileParser.TileParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.HashMap;

/**
 * Created by mike on 2016-05-19.
 */
public class TileLoader {
    public HashMap<Integer, Tile> loadTiles(){
        SAXParser parser;
        SAXParserFactory factory = SAXParserFactory.newInstance();
        HashMap<Integer, Tile> tileMap = new HashMap<>();
        try {
            parser = factory.newSAXParser();
            TileParser tileParser = new TileParser();
            File tileFile =  new File(this.getClass().getResource("/tiles/tilelist.xml").getPath());
            // Read in all the tiles from the HashMap
            parser.parse(tileFile, tileParser);
            for (Tile t : tileParser.getTiles()){
                tileMap.put(t.getId(), t);
            }
            return tileMap;
        } catch (Exception e) {
            System.out.println("TileLoader: Error in loadTiles");
            return null;
        }
    }

}
