package com.games.monaden.model.gameObjects;

import com.games.monaden.model.World;
import com.games.monaden.model.Point;

/**
 * Created by Anton on 2016-04-19.
 */
public class GameObject {
    private Point position;
    private String imageFile;
    private String imageSection;
    private int imageWidth, imageHeight;
    private boolean continuousAnimation;
    //Should contain collision

    public int getHeight(){
        return imageHeight;
    }

    public int getWidth(){
        return imageWidth;
    }

    public String getImagePath(){
        return imageSection + "/" + imageFile;
    }

    public boolean hasContinuousAnimation(){
        return continuousAnimation;
    }

    public void setContinuousAnimation(boolean animationStatus){
        continuousAnimation = animationStatus;
    }

    private World.MovementDirection object_direction = World.MovementDirection.DOWN;

    public World.MovementDirection getDirection(){
        return object_direction;
    }

    public void setDirection(World.MovementDirection direction){
        object_direction = direction;
    }


    public Point getPosition(){return position;}

    protected void setPosition(Point p){ position = p; }

    public GameObject(Point startPos, String imageSection, String imageFile, int imageWidth, int imageHeight)
    {
        position = startPos;
        this.imageSection = imageSection;
        this.imageFile = imageFile;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
    }
}
