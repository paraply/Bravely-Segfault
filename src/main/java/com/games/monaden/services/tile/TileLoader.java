package com.games.monaden.services.tile;


import com.games.monaden.model.Tile;
import com.games.monaden.services.tile.TileParser;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;
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
            ClassLoader classLoader = this.getClass().getClassLoader();
            InputStream is = classLoader.getResourceAsStream("tiles/tilelist.xml");
            // Read in all the tiles from the HashMap
            parser.parse(is, tileParser);
            for (Tile t : tileParser.getTiles()){
                tileMap.put(t.getId(), t);
            }
            return tileMap;
        } catch (Exception e) {
//            System.out.println("TileLoader: Error in loadTiles");
            e.printStackTrace();
            return null;
        }
    }

}
