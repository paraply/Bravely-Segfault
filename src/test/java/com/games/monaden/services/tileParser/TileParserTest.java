package com.games.monaden.services.tileParser;

import com.games.monaden.model.Tile;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Philip on 2016-04-25.
 */
public class TileParserTest {
    private File tileFile;
    private SAXParserFactory factory = SAXParserFactory.newInstance();
    private SAXParser parser;
    private List<Tile> tileList;
    private static TileParser tileParser;

    @BeforeClass
    public static void initClass () {
        tileParser = new TileParser();
    }

    @Before
    public void init () {
        try {
            parser = factory.newSAXParser();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tileParser.clearTiles();
    }

    /**
     * Test file has two tiles. The test checks if the parser returns a list with 3 elements.
     */
    @Test
    public void testSize () {
        try {
            tileFile = new File("src/main/resources/parseTests/TileTest1.xml");
            parser.parse(tileFile, tileParser);
            tileList = tileParser.getTiles();
            assertTrue(tileList.size() == 3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Same test file as testSize(), but this test checks for duplicates and finds none.
     */
    @Test
    public void testNoDuplicates () {
        IllegalStateException exception = null;
        try {
            tileFile = new File("src/main/resources/parseTests/TileTest1.xml");
            parser.parse(tileFile, tileParser);
            tileList = tileParser.getTiles();
        } catch (IllegalStateException e) {
            exception = e;
        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }
        assertNull(exception);
    }

    /**
     * Test file has two duplicate IDs. The test tests the checkDuplicateID method.
     */
    @Test
    public void testDuplicates () {
        IllegalStateException exception = null;
        try {
            tileFile = new File ("src/main/resources/parseTests/TileTestDuplicate.xml");
            parser.parse(tileFile, tileParser);
            tileParser.getTiles(); //Runs private method checkDuplicateID()
        } catch (IllegalStateException e) {
            exception = e;
            System.out.println(exception.getMessage());
        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }
        assertNotNull(exception);
    }
}