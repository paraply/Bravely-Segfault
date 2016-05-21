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
    private boolean solid;
    private int imageWidth, imageHeight;
    private boolean continuousAnimation;
    private World.MovementDirection direction = World.MovementDirection.DOWN;
    //Should contain collision

    private int animationFrames = 2;    //Counted from ZERO!

    private int zOrder = 0;

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

    public void setAnimationFrames(int animationFrames){
        this.animationFrames = animationFrames;
    }

    public int getAnimationFrames(){
        return animationFrames;
    }

    public boolean isSolid(){
        return solid;
    }


    public World.MovementDirection getDirection(){
        return direction;
    }

    public void setDirection(World.MovementDirection direction){
        this.direction = direction;
    }


    public Point getPosition(){return position;}

    protected void setPosition(Point p){ position = p; }

    public int getzOrder() {
        return this.zOrder;
    }

    public void setzOrder(int zOrder) {
        this.zOrder = zOrder;
    }

    public GameObject(Point startPos, String imageSection, String imageFile, int imageWidth, int imageHeight)
    {
        position = startPos;
        this.imageSection = imageSection;
        this.imageFile = imageFile;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
    }

    public GameObject(Point startPos, String imageSection, String imageFile)
    {
        position = startPos;
        this.imageSection = imageSection;
        this.imageFile = imageFile;
        this.imageWidth = World.TILE_SIZE;
        this.imageHeight = World.TILE_SIZE;
    }

    public GameObject(Point startPos, String imageSection, String imageFile, boolean solid)
    {
        position = startPos;
        this.imageSection = imageSection;
        this.imageFile = imageFile;
        this.imageWidth = World.TILE_SIZE;
        this.imageHeight = World.TILE_SIZE;
        this.solid = solid;
    }

    public GameObject(Point startPos, String imageFile, boolean solid, int zOrder) {
        this(startPos, "", imageFile, solid);
        this.zOrder = zOrder;
    }

}
