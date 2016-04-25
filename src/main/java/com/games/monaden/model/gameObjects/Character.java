package com.games.monaden.model.gameObjects;

import com.games.monaden.model.World;
import com.games.monaden.model.Point;

/**
 * Created by Anton on 2016-04-19.
 */
public class Character extends GameObject {
    private World world;

    public Character(Point startPos, World _world) {
        super(startPos);
        world = _world;
    }

    public void Move(World.MovementDirection direction) {
        setPosition(world.CheckMovement(getPosition(), direction));
    }
}
