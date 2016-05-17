package com.games.monaden.model;

public class Transition {
    public Point pos;
    public Point newPos;
    public String newLevel;

    public Transition(Point p, Point np, String l){
        pos = p;
        newPos = np;
        newLevel = l;
    }

}
