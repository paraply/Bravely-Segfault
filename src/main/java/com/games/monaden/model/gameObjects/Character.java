package com.games.monaden.model.gameObjects;

import com.games.monaden.model.World;
import com.games.monaden.model.Point;

/**
 * Created by Anton on 2016-04-19.
 */
public class Character extends GameObject {
    private World world;

    public Character(Point startPos, World _world,  String imageSection, String imageFile, int imageWidth, int imageHeight) {
        super(startPos, imageSection, imageFile, imageWidth, imageHeight);
        world = _world;
    }

    public Character(Point startPos, World _world,  String imageSection, String imageFile) {
        super(startPos, imageSection, imageFile);
        world = _world;
    }

    public void Move(World.MovementDirection direction) {
        setPosition(world.CheckMovement(getPosition(), direction));
        setDirection(direction);
    }
}
