package Model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Anton on 2016-04-17.
 */
public class WorldTest {
    @Test
    public void moveUpBlocked() {
        World world = new World();
        assertFalse(world.Move(1, 1, World.MovementDirection.UP));
    }

    @Test
    public void moveDownBlocked() {
        World world = new World();
        assertFalse(world.Move(World.mapSize-2, 1, World.MovementDirection.DOWN));
    }

    @Test
    public void moveLeftBlocked() {
        World world = new World();
        assertFalse(world.Move(1, 1, World.MovementDirection.LEFT));
    }

    @Test
    public void moveRightBlocked() {
        World world = new World();
        assertFalse(world.Move(1, World.mapSize-2, World.MovementDirection.RIGHT));
    }

    @Test
    public void moveUpUnblocked() {
        World world = new World();
        assertTrue(world.Move(2, 1, World.MovementDirection.UP));
    }

    @Test
    public void moveDownUnblocked() {
        World world = new World();
        assertTrue(world.Move(1, 1, World.MovementDirection.DOWN));
    }

    @Test
    public void moveLeftUnblocked() {
        World world = new World();
        assertTrue(world.Move(1, 2, World.MovementDirection.LEFT));
    }

    @Test
    public void moveRightUnblocked() {
        World world = new World();
        assertTrue(world.Move(1, 1, World.MovementDirection.RIGHT));
    }

    //Should these tests consider indexOutOfBounds-exceptions?
    @Test
    public void moveUpEdge() {
        World world = new World();
        assertFalse(world.Move(0, 0, World.MovementDirection.UP));
    }

    @Test
    public void moveDownEdge() {
        World world = new World();
        assertFalse(world.Move(World.mapSize-1, 0, World.MovementDirection.DOWN));
    }

    @Test
    public void moveLeftEdge() {
        World world = new World();
        assertFalse(world.Move(0, 0, World.MovementDirection.LEFT));
    }

    @Test
    public void moveRightEdge() {
        World world = new World();
        assertFalse(world.Move(0, World.mapSize-1, World.MovementDirection.RIGHT));
    }

}