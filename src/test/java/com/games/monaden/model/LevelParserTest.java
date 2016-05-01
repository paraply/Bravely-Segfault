package com.games.monaden.model;

import com.games.monaden.model.gameObjects.Character;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.io.File;
import java.rmi.server.ExportException;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by Philip on 2016-04-28.
 */
public class LevelParserTest {
    private SAXParserFactory factory = SAXParserFactory.newInstance();
    private SAXParser parser;
    private LevelParser levelParser;
    private static World world;
    private File mapFile;

    @BeforeClass
    public static void initClass () {
        world = new World();
    }

    @Before
    public void init () {
        try {
            parser = factory.newSAXParser();
        } catch (Exception e) {
            e.printStackTrace();
        }
        levelParser = new LevelParser(world);
        levelParser.clearTilemap();
        levelParser.clearCharacters();
    }

    /**
     * Checks that the parsed tilemap is the correct size (16x16)
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

    /**
     * Checks that clearTilemap sets all matrix cells to 0
     */
    @Test
    public void testClearTilemap () {
        int[] empty = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        mapFile = new File("src/main/resources/parseTests/TileLevelExample1.xml");
        try {
            parser.parse(mapFile, levelParser);
            int [][] tilemap = levelParser.getTileMap();
            for (int i = 0; i < World.mapSize; i++) {
                assertFalse(Arrays.equals(tilemap[i], empty));  //tilemap should not be empty
            }

            //Get an empty tilemap and overwrite the old one
            levelParser.clearTilemap();
            tilemap = levelParser.getTileMap();

            for (int i = 0; i < World.mapSize; i++) {
                assertTrue(Arrays.equals(tilemap[i], empty));   //tilemap should be empty
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Checks that the tilemap in test XML-file has an iterating first element in each row.
     */
    @Test
    public void iteratingLines () {
        mapFile = new File("src/main/resources/parseTests/TileLevelExample1.xml");
        try {
            parser.parse(mapFile, levelParser);
            int [][] tilemap = levelParser.getTileMap();
            int i = 1;
            for (int [] column : tilemap) {
                assertTrue(column[0] == i);
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks that the amount of interactable objects in the list is correct (1)
     */
    @Test
    public void testCharacterListSize () {
        mapFile = new File("src/main/resources/parseTests/TileLevelExample1.xml");
        try {
            parser.parse(mapFile, levelParser);
            assertTrue(levelParser.getInteractables().size() == 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks that the character in the character list is who they should be
     */
    @Test
    public void testCharacterContent () {
        mapFile = new File("src/main/resources/parseTests/TileLevelExample1.xml");
        try {
            parser.parse(mapFile, levelParser);
            Character character = (Character)levelParser.getInteractables().get(0);
            assertTrue(character.getName().equals("Philip"));
            assertTrue(character.getPosition().equals(new Point(5,2)));
            assertTrue(character.getImagePath().equals("characters/philip-tan.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}