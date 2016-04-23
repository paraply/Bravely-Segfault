package Model;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Anton on 2016-04-17.
 */


public class WorldTest {

    private static World startWorld;
    World world;

    @BeforeClass public static void init(){
        startWorld = new World();
    }

    @Before public void preparation(){
        world = startWorld;
    }

    @Test
    public void moveUpBlocked() {
        assertFalse(world.Move(1, 1, World.MovementDirection.UP));
    }

    @Test
    public void moveDownBlocked() {
        assertFalse(world.Move(World.mapSize-2, 1, World.MovementDirection.DOWN));
    }

    @Test
    public void moveLeftBlocked() {
        assertFalse(world.Move(1, 1, World.MovementDirection.LEFT));
    }

    @Test
    public void moveRightBlocked() {
        assertFalse(world.Move(1, World.mapSize-2, World.MovementDirection.RIGHT));
    }

    @Test
    public void moveUpUnblocked() {
        assertTrue(world.Move(2, 1, World.MovementDirection.UP));
    }

    @Test
    public void moveDownUnblocked() {
        assertTrue(world.Move(1, 1, World.MovementDirection.DOWN));
    }

    @Test
    public void moveLeftUnblocked() {
        assertTrue(world.Move(1, 2, World.MovementDirection.LEFT));
    }

    @Test
    public void moveRightUnblocked() {
        assertTrue(world.Move(1, 1, World.MovementDirection.RIGHT));
    }

    //Should these tests consider indexOutOfBounds-exceptions?
    @Test
    public void moveUpEdge() {
        try{
            world.Move(0, 0, World.MovementDirection.UP);
        }catch(ArrayIndexOutOfBoundsException e){
            assertNull(e);
        }
    }

    @Test
    public void moveDownEdge() {
    try{
        world.Move(World.mapSize-1, 0, World.MovementDirection.DOWN);
    }catch(ArrayIndexOutOfBoundsException e){
        assertNull(e);
    }

}

    @Test
    public void moveLeftEdge() {
       try{
            world.Move(0, 0, World.MovementDirection.LEFT);
       }catch(ArrayIndexOutOfBoundsException e){
            assertNull(e);
    }

}

    @Test
    public void moveRightEdge() {
        try{
            world.Move(0, World.mapSize-1, World.MovementDirection.RIGHT);
        }catch(ArrayIndexOutOfBoundsException e){
            assertNull(e);
        }
    }

}