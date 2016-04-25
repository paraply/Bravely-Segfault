package com.games.monaden.View;

import com.games.monaden.Model.GameObjects.GameObject;
import com.games.monaden.Model.World;
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
    GraphicsContext context;
    Image image;

    int image_height, image_width ;
    int x,y; // Objects position in the world
    int image_src_X, image_src_Y; // Coordinates to get a specific picture from the tileset


    // Create a new instance of the RenderObject
    // A RenderObject must know where to draw = graphicsContext
    // It must know which imageSection to get the image such as "Characters"
    // It must have a imageFile which is accessed in the imageSection folder in resources,
    RenderObject(GameObject gameObject, GraphicsContext graphicsContext, String imageSection, String imageName, int width, int height){
        this.gameObject = gameObject;
        context = graphicsContext;
        image = new Image( imageSection + "/" + imageName + ".png");
        image_width = width;
        image_height = height;
    }


    // x,y values specifies where in the world the character should be drawn
    private void calculateWorldCoordinates(){
        x = gameObject.getX() * World.tileSize;
        y =  gameObject.getY() * World.tileSize;
    }

    // The X value from the source image is always 0 in an RenderObject without animation.
    private void calculateSourceX(){
        image_src_X = 0;
    }

    // The Y value depends on which direction the object is facing.
    // If the object is stationary then it will default to downwards
    // and use the top image from the tileset.
    void calculateSourceY(){
        switch (gameObject.getDirection()){
            case UP:
                image_src_Y = image_height * 3;
                break;
            case LEFT:
                image_src_Y = image_height;
                break;
            case RIGHT:
                image_src_Y = image_height * 2;
                break;
            default: //DOWN
                image_src_Y = 0;
        }
    }

    // Calculate coordinates and draw to context
    public void draw(){
        calculateWorldCoordinates();
        calculateSourceX();
        calculateSourceY();
        context.drawImage(image, image_src_X,image_src_Y, image_height, image_height, x, y, image_height, image_height);
    }
}
