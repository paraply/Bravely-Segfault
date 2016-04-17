package Model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Anton on 2016-04-17.
 */
public class WorldTest {
    @Test
    public void move() throws Exception {
        World world = new World();
        assertFalse(world.Move());
    }

}