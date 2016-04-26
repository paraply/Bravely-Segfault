package com.games.monaden.model.gameObjects;

import com.games.monaden.model.World;
import com.games.monaden.model.Point;

/**
 * Created by Anton on 2016-04-19.
 */
public class GameObject {
    private Point position;
    //Should contain collision

    private World.MovementDirection object_direction = World.MovementDirection.DOWN;

    public World.MovementDirection getDirection(){
        return object_direction;
    }

    public void setDirection(World.MovementDirection direction){
        object_direction = direction;
    }


    public Point getPosition(){return position;}

    protected void setPosition(Point p){ position = p; }

    public GameObject(Point startPos)
    {
        position = startPos;
    }
}
