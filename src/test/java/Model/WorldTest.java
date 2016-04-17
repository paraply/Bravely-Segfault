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
        assertFalse(world.Move(1,1,"up"));
    }

    @Test
    public void moveUpUnblocked() {
        World world = new World();
        assertTrue(world.Move(2,1,"up"));
    }

    @Test
    public void moveDownBlocked() {
        World world = new World();
        assertFalse(world.Move(6,1,"down"));
    }

    @Test
    public void moveDownUnblocked() {
        World world = new World();
        assertTrue(world.Move(5,1,"up"));
    }

    @Test
    public void moveLeftBlocked() {
        World world = new World();
        assertFalse(world.Move(1,1,"left"));
    }

    @Test
    public void moveLeftUnblocked() {
        World world = new World();
        assertTrue(world.Move(1,2,"left"));
    }

    @Test
    public void moveRightBlocked() {
        World world = new World();
        assertFalse(world.Move(1,8,"right"));
    }

    @Test
    public void moveRightUnblocked() {
        World world = new World();
        assertTrue(world.Move(1,7,"right"));
    }
}