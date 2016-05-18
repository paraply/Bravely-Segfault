package com.games.monaden.services.dialogParser;

import com.games.monaden.model.Dialog;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.LinkedList;
import java.util.Stack;
import java.util.StringJoiner;

/**
 * Created by Philip on 2016/05/17.
 */
public class DialogParser extends DefaultHandler{

    private boolean bDialog;
    private boolean bText;
    private boolean bResponse;
    private boolean bChoice;
    private boolean bSubDialog;

    private Dialog root;
    private boolean gotRoot = false;

    private String condition;

    private Dialog currentDialog;

    private Stack<Dialog> parents= new Stack<>();
    private String choiceText;

    @Override
    public void startElement (String uri, String localName, String qName,
                              Attributes attributes) throws SAXException {
        switch (qName.toLowerCase()) {
            case "dialog":
                bDialog = true;
                break;
            case "text":
                bText = true;
                break;
            case "response":
                bResponse = true;
                condition = attributes.getValue("condition");
                break;
            case "choice":
                bChoice = true;
                break;
            case "subdialog":
                bSubDialog = true;
                break;
        }
    }

    @Override
    public void characters (char ch[], int start, int length) throws SAXException, IllegalArgumentException {
        if (bDialog) {
            Dialog child = new Dialog();
            if (currentDialog != null) {
                currentDialog.setChild(child);
                parents.push(currentDialog);
            }
            if (!gotRoot) {
                gotRoot = true;
                root = child;
            }
                currentDialog = child;
            bDialog = false;
        } else if(bText) {
            currentDialog.setDialogText(new String(ch, start, length));
            bText = false;
        } else if (bResponse) {

            bResponse = false;
        } else if (bChoice) {
            choiceText = new String(ch, start, length);
            bChoice = false;
        } else if (bSubDialog) {
            if (!gotRoot) {
                throw new IllegalArgumentException("subDialog: gotRoot wtf");
            }

            Dialog child = new Dialog();
            currentDialog.readInChoices(choiceText, child);
            parents.push(currentDialog);
            currentDialog = child;

            bSubDialog = false;
        }
    }

    @Override
    public void endElement (String uri, String localName, String qName) throws SAXException{
        switch (qName.toLowerCase()) {
            case "dialog":
                //TODO: Create dialog object, link to parent
                if (!parents.empty()) {
                    currentDialog = parents.pop();
                }
                break;
            case "subdialog":
                if (!parents.empty()) {
                    currentDialog = parents.pop();
                }
        }
    }

    /**
     * Returns the start of the parsed dialog
     * @return root of the dialog tree
     */
    public Dialog getRoot () {
        return this.root;
    }
}
