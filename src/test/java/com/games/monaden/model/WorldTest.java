package com.games.monaden.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Anton on 2016-04-17.
 */
public class WorldTest {
    @Test
    public void moveUpBlocked() {
        World world = new World();
        Point p = new Point(1,1);
        Point newP = world.CheckMovement(p, World.MovementDirection.UP);
        assertTrue(p.equals(newP));
    }

    @Test
    public void moveDownBlocked() {
        World world = new World();
        Point p = new Point(1,World.mapSize-2);
        Point newP = world.CheckMovement(p, World.MovementDirection.DOWN);
        assertTrue(p.equals(newP));
    }

    @Test
    public void moveLeftBlocked() {
        World world = new World();
        Point p = new Point(1,1);
        Point newP = world.CheckMovement(p, World.MovementDirection.LEFT);
        assertTrue(p.equals(newP));
    }

    @Test
    public void moveRightBlocked() {
        World world = new World();
        Point p = new Point(World.mapSize-2, 1);
        Point newP = world.CheckMovement(p, World.MovementDirection.RIGHT);
        assertTrue(p.equals(newP));
    }

    @Test
    public void moveUpUnblocked() {
        World world = new World();
        Point p = new Point(1,2);
        Point newP = world.CheckMovement(p, World.MovementDirection.UP);
        assertFalse(p.equals(newP));
    }

    @Test
    public void moveDownUnblocked() {
        World world = new World();
        Point p = new Point(1,1);
        Point newP = world.CheckMovement(p, World.MovementDirection.DOWN);
        assertFalse(p.equals(newP));
    }

    @Test
    public void moveLeftUnblocked() {
        World world = new World();
        Point p = new Point(2,1);
        Point newP = world.CheckMovement(p, World.MovementDirection.LEFT);
        assertFalse(p.equals(newP));
    }

    @Test
    public void moveRightUnblocked() {
        World world = new World();
        Point p = new Point(1,1);
        Point newP = world.CheckMovement(p, World.MovementDirection.RIGHT);
        assertFalse(p.equals(newP));
    }

    //Should these tests consider indexOutOfBounds-exceptions?
    @Test
    public void moveUpEdge() {
        World world = new World();
        Point p = new Point(0,0);
        Point newP = world.CheckMovement(p, World.MovementDirection.UP);
        assertTrue(p.equals(newP));
    }

    @Test
    public void moveDownEdge() {
        World world = new World();
        Point p = new Point(0,World.mapSize-1);
        Point newP = world.CheckMovement(p, World.MovementDirection.DOWN);
        assertTrue(p.equals(newP));
    }

    @Test
    public void moveLeftEdge() {
        World world = new World();
        Point p = new Point(0,0);
        Point newP = world.CheckMovement(p, World.MovementDirection.LEFT);
        assertTrue(p.equals(newP));
    }

    @Test
    public void moveRightEdge() {
        World world = new World();
        Point p = new Point(World.mapSize-1,0);
        Point newP = world.CheckMovement(p, World.MovementDirection.RIGHT);
        assertTrue(p.equals(newP));
    }

}