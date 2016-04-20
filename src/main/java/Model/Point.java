/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Spock
 */
public class Point {

    private final int x;
    private final int y;


    Point(int x, int y) {
        this.x = x;
        this.y = y;
        }
    
    public int getX(){
        return this.x;
    }
    
    
    public int getY(){
        return this.y;
    }
}
