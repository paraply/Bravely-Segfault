package com.games.monaden.model;

import com.games.monaden.model.gameobject.GameObject;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Anton on 2016-04-26.
 */
public class GameObjectTest {
    private GameObject gameObject;
    @Before
    public void init(){
        gameObject = new GameObject(new Point(0,0), "characters/cat");
    }

    @Test
    public void testDirection(){
        gameObject.setDirection(World.MovementDirection.RIGHT);
        assertTrue(gameObject.getDirection() == World.MovementDirection.RIGHT);
    }

    @Test
    public void testPosition() {
        assertTrue(gameObject.getPosition().equals(new Point(0,0)));
    }
}
