package com.games.monaden.services.dialogParser;

import com.games.monaden.model.Dialog;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by Philip on 2016/05/17.
 */
public class DialogParser extends DefaultHandler{

    private int childNr = 0;
    private int childSize = 0;
    private Stack<Dialog> parents= new Stack<>();

    @Override
    public void startElement (String uri, String localName, String qName,
                              Attributes attributes) throws SAXException {

    }

    @Override
    public void endElement (String uri, String localName, String qName) throws SAXException{
        switch (qName.toLowerCase()) {
            case "dialog":
                //TODO: Create dialog object, link to parent
                break;
        }

    }

    @Override
    public void characters (char ch[], int start, int length) throws SAXException {

    }
}
