package com.games.monaden.control;

import com.games.monaden.model.Dialog;
import com.games.monaden.services.dialogParser.DialogParser;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;

/**
 * Created by Philip on 2016/05/19.
 */
public class DialogLoader {
    SAXParser parser;
    SAXParserFactory factory = SAXParserFactory.newInstance();
    DialogParser dialogParser;

    public DialogLoader () {
        dialogParser = new DialogParser();
        try {
            parser = factory.newSAXParser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Dialog parseDialog (String dialogFile) {
        dialogParser.reset();

        ClassLoader classLoader = this.getClass().getClassLoader();
        InputStream is = classLoader.getResourceAsStream("dialogs/" + dialogFile);
        try {
            parser.parse(is, dialogParser);

            return dialogParser.getRoot();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Dialog("DialogLoader: Something went wrong here.");
    }
}
