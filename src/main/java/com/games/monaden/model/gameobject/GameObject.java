package com.games.monaden.model.gameobject;

import com.games.monaden.model.primitives.MovementDirection;
import com.games.monaden.model.World;
import com.games.monaden.model.primitives.Point;
import javafx.scene.input.KeyCode;

/**
 Main entity class of the game, anything which exists in the world is a game object or a subclass of it.
 Contains a lot of information, could possibly be split up further to hold graphics information in some subclass or instance variable.
 */
public class GameObject {
    private Point position;
    private String imageFile;
    private boolean solid;
    private int imageWidth, imageHeight;
    private boolean continuousAnimation;
    private MovementDirection direction = MovementDirection.DOWN;
    private KeyCode[] movements = null;
    //Should contain collision

    private int animationFrames = 2;    //Counted from ZERO!

    private int zOrder = 0;

    public int getHeight(){
        return imageHeight;
    }

    public int getWidth(){
        return imageWidth;
    }

    public KeyCode[] getMovements(){ return movements; }

    public void setMovements(KeyCode[] givenMovements){
        movements = givenMovements;
    }

    public String getImagePath(){
        return pathForWindows("tiles" + "/" + imageFile);
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


    public MovementDirection getDirection(){
        return direction;
    }

    public void setDirection(MovementDirection direction){
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

    public GameObject(Point startPos, String imageFile, int imageWidth, int imageHeight)
    {
        position = startPos;
        this.imageFile = imageFile;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
    }

    public GameObject(Point startPos, String imageFile)
    {
        position = startPos;
        this.imageFile = imageFile;
        this.imageWidth = World.TILE_SIZE;
        this.imageHeight = World.TILE_SIZE;
    }

    public GameObject(Point startPos, String imageFile, boolean solid)
    {
        position = startPos;
        this.imageFile = imageFile;
        this.imageWidth = World.TILE_SIZE;
        this.imageHeight = World.TILE_SIZE;
        this.solid = solid;
    }

    public GameObject(Point startPos,String imageFile, boolean solid, int zOrder) {
        this(startPos, imageFile, solid);
        this.zOrder = zOrder;
    }

    private String pathForWindows (String string) {
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            return string.replace("\\", "/");
        }
        return string;
    }

}
