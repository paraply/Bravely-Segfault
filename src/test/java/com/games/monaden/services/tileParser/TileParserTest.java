package com.games.monaden.services.tileParser;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
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
     * Test file has two duplicate IDs. The test tests the checkDuplicateID method.
     */
    @Test
    public void testDuplicates () {
        try {
            tileFile = new File ("src/main/resources/parseTests/TileTestDuplicate.xml");
            parser.parse(tileFile, tileParser);
            assertTrue(tileParser.checkDuplicateID());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}