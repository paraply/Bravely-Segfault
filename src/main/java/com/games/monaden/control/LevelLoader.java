package com.games.monaden.control;

import com.games.monaden.model.World;
import com.games.monaden.model.gameObjects.Character;
import com.games.monaden.services.levelParser.LevelParser;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Philip on 2016/05/19.
 */
public class LevelLoader {

    private int [][] tileMap;
    private List<Character> interactables;


    SAXParser parser;
    SAXParserFactory factory = SAXParserFactory.newInstance();
    LevelParser levelParser;

    public LevelLoader () {
        tileMap = new int[World.MAP_SIZE][World.TILE_SIZE];
        interactables = new ArrayList<>();
        levelParser = new LevelParser();
        try {
            parser = factory.newSAXParser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadLevel (String levelName) {
        try {
            levelParser.clearTilemap();
            levelParser.clearInteractables();
            levelParser.clearTransitions();

            ClassLoader classLoader = this.getClass().getClassLoader();
            InputStream is = classLoader.getResourceAsStream("maps/" + levelName);
            parser.parse(is, levelParser);

            interactables.clear();
            interactables = levelParser.getInteractables();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int[][] getTileMap () {
        return this.tileMap;
    }

    public List<Character> getInteractables () {
        return this.interactables;
    }

}
