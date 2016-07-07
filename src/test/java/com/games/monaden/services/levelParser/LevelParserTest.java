package com.games.monaden.services.levelParser;

import com.games.monaden.model.primitives.MovementDirection;
import com.games.monaden.model.primitives.Point;
import com.games.monaden.model.primitives.Transition;
import com.games.monaden.model.World;
import com.games.monaden.model.gameobject.Character;
import com.games.monaden.services.level.LevelParser;
import javafx.scene.input.KeyCode;
import org.junit.Before;
import org.junit.Test;

import javax.swing.text.Position;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.io.File;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by Philip on 2016-04-28.
 */
public class LevelParserTest {
    private SAXParserFactory factory = SAXParserFactory.newInstance();
    private SAXParser parser;
    private LevelParser levelParser;
    private File mapFile;

    @Before
    public void init () {
        try {
            parser = factory.newSAXParser();
        } catch (Exception e) {
            e.printStackTrace();
        }
        levelParser = new LevelParser();
        levelParser.clearTilemap();
        levelParser.clearInteractables();
        levelParser.clearTransitions();

        mapFile = new File("src/main/resources/parseTests/TileLevelExample1.xml");

    }

    /**
     * Checks that the parsed tilemap is the correct size (16x16)
     */
    @Test
    public void testSize () {
        try {
            parser.parse(mapFile, levelParser);
            int[][] tileMap = levelParser.getTileMap();
            int i;
            for (i = 0; i < World.MAP_SIZE; i++) {
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
        try {
            parser.parse(mapFile, levelParser);
            int [][] tilemap = levelParser.getTileMap();
            for (int i = 0; i < World.MAP_SIZE; i++) {
                assertFalse(Arrays.equals(tilemap[i], empty));  //tilemap should not be empty
            }

            //Get an empty tilemap and overwrite the old one
            levelParser.clearTilemap();
            tilemap = levelParser.getTileMap();

            for (int i = 0; i < World.MAP_SIZE; i++) {
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
        try {
            parser.parse(mapFile, levelParser);
            Character character = (Character)levelParser.getInteractables().get(0);
            assertTrue(character.getName().equals("Philip"));
            assertTrue(character.getPosition().equals(new Point(5,2)));
            System.out.println(character.getImagePath());
            assertTrue(character.getImagePath().equals("tiles/characters/philip-tan.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks that the amount of transitions in the list is correct (1)
     */
    @Test
    public void testTransitionListSize(){
        try {
            parser.parse(mapFile, levelParser);
            assertTrue(levelParser.getTransitions().size() == 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks that the character in the character list is who they should be
     */
    @Test
    public void testTransitionContent () {
        try {
            parser.parse(mapFile, levelParser);
            Transition transition = levelParser.getTransitions().get(0);
            assertTrue(transition.pos.equals(new Point(8,5)));
            assertTrue(transition.newPos.equals(new Point(2,2)));
            assertTrue(transition.newLevel.equals("monadenKitchen.xml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Test a Non Playable Characters movement input is correct in the end from the xml file!
     * from TileLevelExample1.xml <character name="Philip"  move="right:up:left:down">
     */
    @Test
    public void testCharacterMovement() {
        try {
            parser.parse(mapFile, levelParser);
            Character character = levelParser.getInteractables().get(0);
            KeyCode[] finalMovement = character.getMovements();
            assertTrue(KeyCode.RIGHT == finalMovement[0]);
            assertTrue(KeyCode.UP == finalMovement[1]);
            assertTrue(KeyCode.LEFT == finalMovement[2]);
            assertTrue(KeyCode.DOWN == finalMovement[3]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}