package com.games.monaden.services.dialogParser;

import com.games.monaden.model.Dialog;
import com.games.monaden.model.DialogChoice;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.util.*;

/**
 * Created by Philip on 2016/05/17.
 */
public class DialogParser extends DefaultHandler{

    private boolean bDialog;
    private boolean bText;
    private boolean bResponse;
    private boolean bChoice;
    private boolean bRequirement;
    private boolean bSubDialog;
    private boolean bAvatar;

    private Dialog root;
    private boolean gotRoot = false;

    private String condition;

    private Dialog currentDialog;

    private Stack<Dialog> parents = new Stack<>();
    private String choiceText;
    private List<String> requirements = new ArrayList<>();

    @Override
    public void startElement (String uri, String localName, String qName,
                              Attributes attributes) throws SAXException {
        switch (qName.toLowerCase()) {
            case "dialog":
                bDialog = true;
                break;
            case "image":
                bAvatar = true;
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
            case "keyitem":
                bRequirement = true;
                break;
        }
    }

    @Override
    public void characters (char ch[], int start, int length) throws SAXException, IllegalArgumentException {
        if (bDialog) {
            DialogChoice child = new DialogChoice(new Dialog(), "");
            if (currentDialog != null) {
                currentDialog.setChild(child);
                parents.push(currentDialog);
            }
            if (!gotRoot) {
                gotRoot = true;
                root = child.getDialog();
            }
                currentDialog = child.getDialog();
            bDialog = false;
        } else if (bAvatar) {
            currentDialog.setImageFile(new File(new String(ch, start, length)));
            bAvatar = false;
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

            DialogChoice child = new DialogChoice(new Dialog(), choiceText);
            for(String s : requirements){
                child.addRequirement(s);
                System.out.println("requirement!" + s);
            }
            currentDialog.addChoice(child);
            parents.push(currentDialog);
            currentDialog = child.getDialog();
            requirements.clear();

            bSubDialog = false;
        } else if(bRequirement){
            requirements.add(new String(ch, start, length));
            bRequirement = false;
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
                break;
        }
    }

    /**
     * Returns the start of the parsed dialog
     * @return root of the dialog tree
     */
    public Dialog getRoot () {
        return this.root;
    }
    public void reset () {
        gotRoot = false;
        root = null;
        currentDialog = null;
        parents = new Stack<>();
        choiceText = null;
        requirements.clear();
    }
}
