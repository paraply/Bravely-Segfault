package com.games.monaden.services.dialogParser;

import com.games.monaden.services.dialog.DialogParser;
import org.junit.Before;
import org.junit.Test;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.io.File;

/**
 * Created by Philip on 2016-05-18.
 */
public class DialogParserTest {
    private SAXParserFactory factory = SAXParserFactory.newInstance();
    private SAXParser parser;
    private DialogParser dialogParser;
    private File dialogFile;

    @Before
    public void init () {
        try {
            parser = factory.newSAXParser();
        } catch (Exception e) {
            e.printStackTrace();
        }
        dialogParser = new DialogParser();
    }

    @Test
    public void testTree () {
        dialogFile = new File("src/main/resources/parseTests/DialogTest.xml");
        try {
            parser.parse(dialogFile, dialogParser);
            System.out.println("Do something!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}