package com.games.monaden.View;

import com.games.monaden.Control.GameLoop;
import com.games.monaden.Model.GameObjects.GameObject;
import com.games.monaden.Model.World;
import javafx.scene.canvas.GraphicsContext;

/**
 * Created by paraply on 2016-04-22.
 */
public class AnimatedObject extends RenderObject {
    private int gameObject_old_X, gameObject_old_Y;
    private int inTransition = 0;
    private int animation_part = 0;
    private final int ANIMAMATION_FRAMES = 3; // How many pictures X-wise in the tileset. Could possibly be specified in XML later.
    private final int TRANSITION_SPEED = GameLoop.FREQUENCY == 32 ? 1 : 2 ; // Transistion speed should be 2 if GameLoop frequency is 16. Should Be 1 if GameLoop frequency is 32.
    private final int ANIMATION_SPEED = 3; // Uses a mod of the inTransition number.
    private final int REAL_ANIMATION_SPEED = ANIMATION_SPEED * TRANSITION_SPEED;


    public AnimatedObject(GameObject gameObject, GraphicsContext graphicsContext, String imageSection, String imageName) {
        super(gameObject, graphicsContext, imageSection, imageName);
        gameObject_old_X = gameObject.getX();
        gameObject_old_Y = gameObject.getY();
    }

    public void draw(){
        calculateX();
        super.calculateY();
        context.drawImage(image, image_src_X,image_src_Y, IMAGE_HEIGHT, IMAGE_HEIGHT, x, y, IMAGE_HEIGHT, IMAGE_HEIGHT);
    }

    private void calculateX(){
        if (inTransition == 0){ // Is currently not animating and moving
            if ((gameObject.getX() != gameObject_old_X ) || (gameObject.getY() != gameObject_old_Y) ){ // Check if object should move
                inTransition = World.tileSize; // Start transition
                image_src_X = 0;
                x = gameObject_old_X * World.tileSize; // Stand still for now, we want to change direction first. Start moving in next transition.
                y =  gameObject_old_Y * World.tileSize;
            }else{ // Object is standing still
                image_src_X = 0;
                x = gameObject.getX() * World.tileSize;
                y =  gameObject.getY() * World.tileSize;
            }
        }else{
            inTransition -=TRANSITION_SPEED;
            image_src_X = (animation_part % ANIMAMATION_FRAMES) * IMAGE_WIDTH;
            if ((World.tileSize - inTransition) % REAL_ANIMATION_SPEED == 0){
                animation_part++;
            }
            switch (gameObject.getDirection()){
                case UP: y =  (gameObject.getY() * World.tileSize) + inTransition; // Move up step by step
                    break;
                case DOWN: y =  (gameObject.getY() * World.tileSize)   - inTransition; // Move down step by step
                    break;
                case LEFT: x =  (gameObject.getX() * World.tileSize) +  inTransition; // Move left step by step
                    break;
                case RIGHT: x =  (gameObject.getX() * World.tileSize)  - inTransition; // Move right step by step
                    break;
            }

            if (inTransition == 0){ // transition is done now
                gameObject_old_Y = gameObject.getY();
                gameObject_old_X = gameObject.getX();
            }
        }

    }
}
