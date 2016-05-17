package com.games.monaden.model.gameObjects;

import com.games.monaden.model.Dialog;
import com.games.monaden.model.Point;

/**
 * Created by Anton on 2016-04-19.
 */
public class Character extends GameObject {
    private String name;
    private Dialog dialog;

    public void setPosition(Point p){super.setPosition(p);}

    public Character(Point startPos, String imageFile, int imageWidth, int imageHeight) {
        super(startPos, "characters", imageFile, imageWidth, imageHeight);
    }

    public Character(Point startPos, String imageFile) {
        super(startPos, "characters", imageFile);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dialog getDialog(){return dialog;}
}
