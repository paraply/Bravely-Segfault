package com.games.monaden.model.gameobject;

import com.games.monaden.model.gameobject.GameObject;
import com.games.monaden.model.primitives.MovementDirection;
import com.games.monaden.model.primitives.Point;
import javafx.scene.input.KeyCode;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Anton on 2016-04-26.
 */
public class GameObjectTest {
    private GameObject gameObject;
    private KeyCode[] movement;

    @Before
    public void init(){
        gameObject = new GameObject(new Point(0,0), "characters/cat");
        movement = new KeyCode[2];
        movement[0] = KeyCode.UP;
        movement[1] = KeyCode.DOWN;
        gameObject.setMovements(movement);
    }

    @Test
    public void testDirection(){
        gameObject.setDirection(MovementDirection.RIGHT);
        assertTrue(gameObject.getDirection() == MovementDirection.RIGHT);
    }

    @Test
    public void testPosition() {
        assertTrue(gameObject.getPosition().equals(new Point(0,0)));
    }

    @Test
    public void testMovement(){
        KeyCode[] givenMovement = gameObject.getMovements();
        assertTrue(givenMovement[0] == movement[0] && givenMovement[1] == movement[1]);
    }

    /**
     * Test that the addStep can't take more steps than the movement is.
     */
    @Test
    public void testStep(){
        assertTrue(gameObject.getStep() == 0);
        gameObject.addStep();
        assertTrue(gameObject.getStep() == 1);
        gameObject.addStep();
        assertTrue(gameObject.getStep() == 0);
    }
}
