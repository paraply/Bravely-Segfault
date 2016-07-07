package com.games.monaden.model.gameobject;

import com.games.monaden.model.dialog.Dialog;
import com.games.monaden.model.primitives.MovementDirection;
import com.games.monaden.model.primitives.Point;
import javafx.scene.input.KeyCode;

import java.io.File;

/**
 A character is a GameObject that has a name and possibly contains a dialog.
 */
public class Character extends GameObject {
    private String name;
    private Dialog dialog;
    private File dialogFile;

    public void setMovements(KeyCode[] givenMovements){
        super.setMovements(givenMovements);
    }
    public KeyCode[] getMovements(){ return super.getMovements(); }

    public void setPosition(Point p){super.setPosition(p);}

    public Character(Point startPos, String imageFile, int imageWidth, int imageHeight) {
        super(startPos,  imageFile, imageWidth, imageHeight);
    }

    public Character(Point startPos, String imageFile) {
        super(startPos, imageFile);
    }

    public Character(Point startPos, String imageFile, int zOrder) {
        super(startPos,  imageFile, true, zOrder);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDialogFile (File file) {
        this.dialogFile = file;
    }

    public File getDialogFile () {
        return this.dialogFile;
    }

    public void setDialog (Dialog dialog) {
        this.dialog = dialog;
    }

    public Dialog getDialog(){return dialog;}
}
