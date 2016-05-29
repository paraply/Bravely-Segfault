package com.games.monaden.model;

public class Transition {
    public Point pos;
    public Point newPos;
    public String newLevel;
    public World.MovementDirection direction;

    public Transition(Point p, Point np, String l){
        pos = p;
        newPos = np;
        newLevel = l;
    }

    public Transition(Point p, Point np, String l, World.MovementDirection dir){
        pos = p;
        newPos = np;
        newLevel = l;
        direction = dir;
    }

}
