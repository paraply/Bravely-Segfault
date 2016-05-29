package com.games.monaden.model.primitives;

public class Transition {
    public Point pos;
    public Point newPos;
    public String newLevel;
    public MovementDirection direction;

    public Transition(Point p, Point np, String l){
        pos = p;
        newPos = np;
        newLevel = l;
    }

    public Transition(Point p, Point np, String l, MovementDirection dir){
        pos = p;
        newPos = np;
        newLevel = l;
        direction = dir;
    }

}
