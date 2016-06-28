/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.games.monaden.model.primitives;

/**
 Primitive to keep track of two ints.
 Lacks a hashCode implementation.
 */

public class Point {

    private final int x;
    private final int y;

    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point nextTo(MovementDirection direction){
        switch(direction){
            case UP:
                return new Point(x, y - 1);
            case DOWN:
                return new Point(x, y + 1);
            case LEFT:
                return new Point(x - 1, y);
            case RIGHT:
                return new Point(x + 1, y);
            default:
                return this;
        }
    }

    public boolean equals(Object o) {
        if(!(o instanceof Point)) return super.equals(o);

        Point p = (Point)o;
        return p.getX() == x && p.getY() == y;
    }

    public String toString(){
        return "X: " + x + " Y: " + y;
    }
}
