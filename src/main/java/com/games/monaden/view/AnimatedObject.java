package com.games.monaden.view;

import com.games.monaden.control.GameLoop;
import com.games.monaden.model.Point;
import com.games.monaden.model.gameObjects.GameObject;
import com.games.monaden.model.World;
import javafx.scene.canvas.GraphicsContext;

/**
 * Created by paraply on 2016-04-22.
 *
 * This class is a subclass to RenderObject
 * and is used to draw moving characters and animated objects.
 */
class AnimatedObject extends RenderObject {
    private Point previousPosition;
    private int inTransition = 0;
    private int animation_part = 0;
    private final int ANIMATION_FRAMES = 3; // How many pictures X-wise in the tileset. Could possibly be specified in XML later.
    private final int TRANSITION_SPEED = GameLoop.FREQUENCY == 32 ? 1 : 2 ; // Transition speed should be 2 if GameLoop frequency is 16. Should Be 1 if GameLoop frequency is 32.
    private final int ANIMATION_SPEED = 3; // Uses a mod of the inTransition number.
    private final int REAL_ANIMATION_SPEED = ANIMATION_SPEED * TRANSITION_SPEED;


    AnimatedObject(GameObject gameObject, GraphicsContext graphicsContext, String imageSection, String imageName,int width, int height, boolean movable) {
        super(gameObject, graphicsContext, imageSection, imageName, width,height);
        previousPosition = gameObject.getPosition();
    }

    public void draw(){
        calculateSourceX();
        calculateSourceY();
        drawToContext();
    }

    private void calculateSourceX(){
        if (inTransition == 0){ // Is currently not animating and moving
            if (!gameObject.getPosition().equals(previousPosition)){
                inTransition = World.tileSize; // Start transition
                image_src_X = 0;
                x = previousPosition.getX() * World.tileSize; // Stand still for now, we want to change direction first. Start moving in next transition.
                y =  previousPosition.getY() * World.tileSize;
            }else{ // Object is standing still
                image_src_X = 0;
                x = gameObject.getPosition().getX() * World.tileSize;
                y =  gameObject.getPosition().getY() * World.tileSize;
            }
        }else{
            inTransition -=TRANSITION_SPEED;
            image_src_X = (animation_part % ANIMATION_FRAMES) * image_width;
            if ((World.tileSize - inTransition) % REAL_ANIMATION_SPEED == 0){
                animation_part++;
            }
            switch (gameObject.getDirection()){
                case UP: y =  (gameObject.getPosition().getY() * World.tileSize) + inTransition; // Move up step by step
                    break;
                case DOWN: y =  (gameObject.getPosition().getY() * World.tileSize)   - inTransition; // Move down step by step
                    break;
                case LEFT: x =  (gameObject.getPosition().getX() * World.tileSize) +  inTransition; // Move left step by step
                    break;
                case RIGHT: x =  (gameObject.getPosition().getX() * World.tileSize)  - inTransition; // Move right step by step
                    break;
            }

            if (inTransition == 0){ // transition is done now
                previousPosition = gameObject.getPosition();
            }
        }

    }
}
