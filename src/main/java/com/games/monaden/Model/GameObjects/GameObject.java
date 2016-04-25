package com.games.monaden.model.gameObjects;

import com.games.monaden.model.World;

/**
 * Created by Anton on 2016-04-19.
 */
public class GameObject {
    private int x;
    private int y;
    //Should contain collision

    private World.MovementDirection object_direction = World.MovementDirection.DOWN;

    public World.MovementDirection getDirection(){
        return object_direction;
    }

    public void setDirection(World.MovementDirection direction){
        object_direction = direction;
    }


    public int getX(){return x;}
    public int getY(){return y;}

    protected void setX(int _x){
            x = _x;
    }

    protected void setY(int _y){
            y = _y;
    }

    public GameObject(int startX, int startY)
    {
        x = startX;
        y = startY;
    }
}
