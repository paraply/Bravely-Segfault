package com.games.monaden.model;

import com.games.monaden.model.gameObjects.Character;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Anton on 2016-04-26.
 */
public class CharacterTest {
    private static World startWorld;
    private World world;

    @BeforeClass
    public static void initClass(){
        startWorld = new World("src/main/resources/parseTests/start.xml" );
    }

    @Before
    public void init() {
        world = startWorld;
    }

    @Test
    public void testPositionUpdated() {
        Character character = new Character(new Point(1,1), world,"cat");
        character.Move(World.MovementDirection.DOWN);
        assertTrue(character.getPosition().equals(new Point(1,2)));
    }

    @Test
    public void testPositionNotUpdated() {
        Point position = new Point(1,1);
        Character character = new Character(position, world,"cat");
        character.Move(World.MovementDirection.UP);
        assertTrue(character.getPosition().equals(position));
    }


    @Test
    public void testDirectionUpdated() {
        Character character = new Character(new Point(1,1), world,"cat");
        character.Move(World.MovementDirection.UP);
        assertTrue(character.getDirection().equals(World.MovementDirection.UP));
    }
}
