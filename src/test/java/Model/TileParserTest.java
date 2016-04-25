package Model;

import org.junit.Before;
import org.junit.Test;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Philip on 2016-04-25.
 */
public class TileParserTest {
    File tileFile;
    SAXParserFactory factory = SAXParserFactory.newInstance();
    SAXParser parser;
    List<Tile> tileList;
    TileParser tileParser;

    @Before
    public void init () {
        try {
            parser = factory.newSAXParser();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tileParser = new TileParser();
    }

    /**
     * Test file has two tiles. The test checks if the parser returns a list with 2 elements.
     */
    @Test
    public void testSize () {
        try {
            tileFile = new File("src/main/resources/Test.xml");
            parser.parse(tileFile, tileParser);
            tileList = tileParser.getTiles();
            assertTrue(tileList.size() == 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}