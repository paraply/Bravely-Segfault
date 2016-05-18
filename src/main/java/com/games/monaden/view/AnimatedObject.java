package com.games.monaden.view;

import com.games.monaden.model.Point;
import com.games.monaden.model.World;
import com.games.monaden.model.gameObjects.GameObject;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paraply on 2016-04-22.
 *
 * This class is a subclass to RenderObject
 * and is used to draw moving characters and animated objects.
 */
class AnimatedObject extends RenderObject {
    private Point previousPosition;

    // *** Variables used for animations ***
    private int animationTicks = 0;             // The animations cannot run at the same frequency as the GameLoop frequency. It would be to fast.
                                                // This will loop 0 1 2 3 ... animationFrequency
                                                // When reached animationFrequency-value then animate.
    private int animationFrequency = 4;  // Which frequency that a new animation frame will be drawn. The higher number the slower animation.
//    private final int ANIMATION_FRAMES = 2;     // How many pictures X-wise in the animation tileset. Could possibly be specified in XML later. Remember that this is counted from ZERO!
    private int currentAnimationFrame = 0;      // We need to keep track of which animation frame we have rendered


    // ** Variables used for transitioning **
    private int currentTransitionStep = 0;      // 0 = object is not moving or has finished moving to the new coordinate
                                                // Otherwise the object is currently moving to another coordinate.
    private final int PIXELS_PER_STEP = 2;      // If we move a pixel at a time it will be to slow. This can be adjusted here.
                                                // REMEMBER: The tilesets size has to be divisible by this number

    private boolean inTransition;

    AnimatedObject(GameObject gameObject, GraphicsContext context) {
        super(gameObject, context);
        previousPosition = gameObject.getPosition();
//        if (gameObject.hasContinuousAnimation() == false){
//            animationFrequency = animationFrequency / 2;
//        }
    }

    public void startTransition(){
        inTransition = true;
    }


    public void draw(){
        calculateSourceX();
        calculateSourceY();
        drawToContext();
    }

    // We have to set the old coordinates to be the same as the new ones when we are transitioning
    // Otherwise the character would animate since the coordinates has changed
    public void hasTransitioned(){
        previousPosition = new Point(x,y);
    }

    private void calculateSourceX(){

        if (gameObject.hasContinuousAnimation()){ // This is used for objects that are not moving but still has an animation.

            x = gameObject.getPosition().getX() * World.TILE_SIZE;
            y =  gameObject.getPosition().getY() * World.TILE_SIZE;

            animationTick(); // Animate

        }else{ // Else if object does not have a continuous animation. Then it should only be animated during transition.
            if (currentTransitionStep == 0){                                // We are currently not in a moving state
                if (!gameObject.getPosition().equals(previousPosition)){    // Check if we should be in a moving state (e.g the objects coordinates has changed since last time)
                    if (inTransition){
                        previousPosition = gameObject.getPosition();
                        inTransition = false;
                        return;
                    }
                    currentTransitionStep = World.TILE_SIZE;                    // If we have a 32-bit TILE_SIZE, then we should move to another tile in maximum 32 steps.
                    imageSrcX = 0;                                              // Do not animate first. We want to change the objects direction this time.
                    x = previousPosition.getX() * World.TILE_SIZE;              // Stand still for now, we want to change direction first. Start moving in next transition.
                    y =  previousPosition.getY() * World.TILE_SIZE;
                }else{ // Object is standing still
                    imageSrcX = 0;
                    x = gameObject.getPosition().getX() * World.TILE_SIZE;
                    y =  gameObject.getPosition().getY() * World.TILE_SIZE;
                }
            }else{ // We are in a transitioning state
                animationTick();

                currentTransitionStep = currentTransitionStep - PIXELS_PER_STEP; // We move a specific number of pixels

            switch (gameObject.getDirection()){ //
                case UP: y =  (gameObject.getPosition().getY() * World.TILE_SIZE) + currentTransitionStep;       // Move up step by step
                    break;
                case DOWN: y =  (gameObject.getPosition().getY() * World.TILE_SIZE)   - currentTransitionStep;   // Move down step by step
                    break;
                case LEFT: x =  (gameObject.getPosition().getX() * World.TILE_SIZE) +  currentTransitionStep;    // Move left step by step
                    break;
                case RIGHT: x =  (gameObject.getPosition().getX() * World.TILE_SIZE)  - currentTransitionStep;   // Move right step by step
                    break;
            }

            if (currentTransitionStep == 0){ // transition is done now
                previousPosition = gameObject.getPosition();
            }

            }
        }
    }

    private void animationTick(){
        animationTicks++;
        if (animationTicks > animationFrequency){
            animationTicks = 0;
        }
        if (animationTicks == animationFrequency){         // Now we should animate
            if (currentAnimationFrame == gameObject.getAnimationFrames()){ // Check if we draw the last frame previously. Then we should loop to the begining and draw the first frame
                currentAnimationFrame = 0;
            }else{
                currentAnimationFrame++;                    // Draw the next frame this time
            }
            imageSrcX = currentAnimationFrame * gameObject.getWidth();
        }
    }


}
