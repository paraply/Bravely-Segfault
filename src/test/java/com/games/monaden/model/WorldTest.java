package com.games.monaden.model;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class WorldTest {

    private static World startWorld;
    private World world;
    @BeforeClass
    public static void initClass(){
        startWorld = new World("src/main/resources/parseTests/start.xml" );
    }

    @Before
    public void initTest(){
        world = startWorld;
    }

    @Test
    public void moveUpBlocked() {

        Point p = new Point(1,1);
        Point newP = world.checkMovement(p, World.MovementDirection.UP);
        assertTrue(p.equals(newP));
    }

    @Test
    public void moveDownBlocked() {
        Point p = new Point(1,World.MAP_SIZE-2);
        Point newP = world.checkMovement(p, World.MovementDirection.DOWN);
        assertTrue(p.equals(newP));
    }

    @Test
    public void moveLeftBlocked() {
        Point p = new Point(1,1);
        Point newP = world.checkMovement(p, World.MovementDirection.LEFT);
        assertTrue(p.equals(newP));
    }

    @Test
    public void moveRightBlocked() {
        Point p = new Point(World.MAP_SIZE-2, 1);
        Point newP = world.checkMovement(p, World.MovementDirection.RIGHT);
        assertTrue(p.equals(newP));
    }

    @Test
    public void moveUpUnblocked() {
        Point p = new Point(1,2);
        Point newP = world.checkMovement(p, World.MovementDirection.UP);
        assertFalse(p.equals(newP));
    }

    @Test
    public void moveDownUnblocked() {
        Point p = new Point(1,1);
        Point newP = world.checkMovement(p, World.MovementDirection.DOWN);
        assertFalse(p.equals(newP));
    }

    @Test
    public void moveLeftUnblocked() {
        Point p = new Point(2,1);
        Point newP = world.checkMovement(p, World.MovementDirection.LEFT);
        assertFalse(p.equals(newP));
    }

    @Test
    public void moveRightUnblocked() {
        Point p = new Point(1,1);
        Point newP = world.checkMovement(p, World.MovementDirection.RIGHT);
        assertFalse(p.equals(newP));
    }

    //Should these tests consider indexOutOfBounds-exceptions?
    @Test
    public void moveUpEdge() {
        Point p = new Point(0,0);
        Point newP = world.checkMovement(p, World.MovementDirection.UP);
        assertTrue(p.equals(newP));
    }

    @Test
    public void moveDownEdge() {
        Point p = new Point(0,World.MAP_SIZE-1);
        Point newP = world.checkMovement(p, World.MovementDirection.DOWN);
        assertTrue(p.equals(newP));
    }

    @Test
    public void moveLeftEdge() {
        Point p = new Point(0,0);
        Point newP = world.checkMovement(p, World.MovementDirection.LEFT);
        assertTrue(p.equals(newP));
    }

    @Test
    public void moveRightEdge() {
        Point p = new Point(World.MAP_SIZE-1,0);
        Point newP = world.checkMovement(p, World.MovementDirection.RIGHT);
        assertTrue(p.equals(newP));
    }

    @Test
    public void UpEdgeException() {
        Point p = new Point(0,0);
        ArrayIndexOutOfBoundsException check = null;
        try{
            world.checkMovement(p, World.MovementDirection.UP);
        }catch(ArrayIndexOutOfBoundsException e){
            check = e;
        }
        assertNull(check);
    }
    @Test
    public void DownEdgeException() {
        Point p = new Point(0,World.MAP_SIZE-1);
        ArrayIndexOutOfBoundsException check = null;
        try{
            world.checkMovement(p, World.MovementDirection.DOWN);
        }catch(ArrayIndexOutOfBoundsException e){
            check = e;
        }
        assertNull(check);
    }
    @Test
    public void LeftEdgeException() {
        Point p = new Point(0,0);
        ArrayIndexOutOfBoundsException check = null;
        try{
            world.checkMovement(p, World.MovementDirection.LEFT);
        }catch(ArrayIndexOutOfBoundsException e){
            check = e;
        }
        assertNull(check);
    }


    @Test
    public void RightEdgeException() {
        Point p = new Point(World.MAP_SIZE-1,0);
        ArrayIndexOutOfBoundsException check = null;
        try{
            world.checkMovement(p, World.MovementDirection.RIGHT);
        }catch(ArrayIndexOutOfBoundsException e){
            check = e;
        }
        assertNull(check);
    }
}
