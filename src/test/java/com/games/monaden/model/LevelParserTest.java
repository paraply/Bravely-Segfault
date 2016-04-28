package com.games.monaden.model;

import org.junit.Before;
import org.junit.Test;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by Philip on 2016-04-28.
 */
public class LevelParserTest {
    SAXParserFactory factory = SAXParserFactory.newInstance();
    SAXParser parser;
    LevelParser levelParser;
    World world;
    File mapFile;

    @Before
    public void init () {
        try {
            parser = factory.newSAXParser();
        } catch (Exception e) {
            e.printStackTrace();
        }
        world = new World();
        levelParser = new LevelParser(world);
    }

    /**
     * Checks so that the parsed tilemap is the correct size (16x16)
     */
    @Test
    public void testSize () {
        mapFile = new File("src/main/resources/parseTests/TileLevelExample1.xml");
        try {
            parser.parse(mapFile, levelParser);
            int[][] tileMap = levelParser.getTileMap();
            int i;
            for (i = 0; i < World.mapSize; i++) {
                if (tileMap[i].length != 16) {
                    assertTrue(false);
                    break;
                }
            }
            if (i == 15) {
                assertTrue(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}