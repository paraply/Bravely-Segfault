package com.games.monaden.view;

import com.games.monaden.model.gameObjects.GameObject;
import com.games.monaden.model.World;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


/**
 * Created by paraply on 2016-04-21.
 *
 * This class draws objects without animations on the screen.
 * It is a superclass to AnimatedObject that handles animations.
 *
 * There should only be one instance of this class that is handled by Render.
 */

class RenderObject {
    GameObject gameObject;
    private Image image;
    int x,y;                        // objects position in the world
    int imageSrcX, imageSrcY;   // Coordinates to get a specific picture from the tileset
    private GraphicsContext context;

    // Create a new instance of the RenderObject
    RenderObject(GameObject gameObject, GraphicsContext context){
        try {
            this.gameObject = gameObject;
            image = new Image(gameObject.getImagePath());
            this.context = context;
        }catch (Exception e){
//            System.err.println("RenderObject Constructor: " + e.getMessage());
        }
    }

    // x,y values specifies where in the world the character should be drawn
    private void calculateWorldCoordinates(){
        x = gameObject.getPosition().getX() * World.TILE_SIZE;
        y =  gameObject.getPosition().getY() * World.TILE_SIZE;
    }

    // The X value from the source image is always 0 in an RenderObject without animation.
    private void calculateSourceX(){
        imageSrcX = 0;
    }

    // The Y value depends on which direction the object is facing.
    // If the object is stationary then it will default to downwards
    // and use the top image from the tileset.
    void calculateSourceY(){
        switch (gameObject.getDirection()){
            case LEFT:
                imageSrcY = gameObject.getHeight();
                break;
            case RIGHT:
                imageSrcY = gameObject.getHeight() * 2;
                break;
            case UP:
                imageSrcY = gameObject.getHeight() * 3;
                break;
            default: //DOWN
                imageSrcY = 0;
        }
    }

    // Calculate coordinates and draw to context
    public void draw(){
        calculateWorldCoordinates();
        calculateSourceX();
        calculateSourceY();
        drawToContext();
    }

    // Call JavaFX canvas to draw the image on the canvas
    void drawToContext(){
        context.drawImage(image, imageSrcX, imageSrcY, gameObject.getWidth(), gameObject.getHeight(), x, y, gameObject.getWidth(), gameObject.getHeight());
    }
}
