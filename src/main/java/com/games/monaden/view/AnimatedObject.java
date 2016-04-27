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

//    private int inTransition = 0; // Used for moving objects. This will handle movement from previous position to the new one. If 0 then the object has not yet moved or is finished moving.





    private int animation_part = 0;



    private int currentAnimationFrame = 0;

    private final int TRANSITION_SPEED = GameLoop.FREQUENCY == 32 ? 1 : 2 ; // Transition speed should be 2 if GameLoop frequency is 16. Should Be 1 if GameLoop frequency is 32.
    private final int ANIMATION_SPEED = 3; // Uses a mod of the inTransition number.
    private final int REAL_ANIMATION_SPEED = ANIMATION_SPEED * TRANSITION_SPEED;


    AnimatedObject(GameObject gameObject) {
        super(gameObject);
        previousPosition = gameObject.getPosition();
    }

    public void draw(){
        calculateSourceX();
        calculateSourceY();
        drawToContext();
    }

    // *** Variables used for animations ***
    private int animationTicks = 0;     // The animations cannot run at the same frequency as the GameLoop frequency. It would be to fast.
                                        // This will loop 0 1 2 3 ... ANIMATION_FREQUENCY
                                        // When reached ANIMATION_FREQUENCY-value then animate.
    private final int ANIMATION_FREQUENCY = 8; // Which frequency that a new animation frame will be drawn. The higher number the slower animation.
    private final int ANIMATION_FRAMES = 2; // How many pictures X-wise in the animation tileset. Could possibly be specified in XML later. Remember that this is counted from ZERO!



    // ** Variables used for transitioning **
    private int currentTransitionStep = 0;  // 0 = object is not moving or has finished moving to the new coordinate
                                            // Otherwise the object is currently moving to another coordinate.
    private final int PIXELS_PER_STEP = 2;  // If we move a pixel at a time it will be to slow. This can be adjusted here.


    private void calculateSourceX(){

        if (gameObject.hasContinuousAnimation()){ // This is used for objects that are not moving but still has an animation.

            x = gameObject.getPosition().getX() * World.tileSize;
            y =  gameObject.getPosition().getY() * World.tileSize;

            animationTick(); // Animate

        }else{ // Else if object does not have a continuous animation. Then it should only be animated during transition.
            if (currentTransitionStep == 0){ // We are currently not in a moving state
                if (!gameObject.getPosition().equals(previousPosition)){    // Check if we should be in a moving state (e.g the objects coordinates has changed since last time)
                    currentTransitionStep = World.tileSize; // If we have a 32-bit tileSize, then we should move to another tile in maximum 32 steps.
                    image_src_X = 0; // Do not animate first. We want to change the objects direction this time.
                    x = previousPosition.getX() * World.tileSize; // Stand still for now, we want to change direction first. Start moving in next transition.
                    y =  previousPosition.getY() * World.tileSize;
                }else{ // Object is standing still
                    image_src_X = 0;
                    x = gameObject.getPosition().getX() * World.tileSize;
                    y =  gameObject.getPosition().getY() * World.tileSize;
                }
            }else{ // We are in a transitioning state
                animationTick();

                currentTransitionStep = currentTransitionStep - PIXELS_PER_STEP; // We move a specific number of pixels

            switch (gameObject.getDirection()){ //
                case UP: y =  (gameObject.getPosition().getY() * World.tileSize) + currentTransitionStep; // Move up step by step
                    break;
                case DOWN: y =  (gameObject.getPosition().getY() * World.tileSize)   - currentTransitionStep; // Move down step by step
                    break;
                case LEFT: x =  (gameObject.getPosition().getX() * World.tileSize) +  currentTransitionStep; // Move left step by step
                    break;
                case RIGHT: x =  (gameObject.getPosition().getX() * World.tileSize)  - currentTransitionStep; // Move right step by step
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
        if (animationTicks > ANIMATION_FREQUENCY ){
            animationTicks = 0;
        }
        if (animationTicks == ANIMATION_FREQUENCY){         // Now we should animate
            if (currentAnimationFrame == ANIMATION_FRAMES){ // Check if we draw the last frame previously. Then we should loop to the begining and draw the first frame
                currentAnimationFrame = 0;
            }else{
                currentAnimationFrame++;                    // Draw the next frame this time
            }

            image_src_X = currentAnimationFrame * gameObject.getWidth();
        }
    }


//    private void calculateSourceX(){
//        if (inTransition == 0){ // Is currently not animating and moving
//            if (!gameObject.getPosition().equals(previousPosition)){
//                inTransition = World.tileSize; // Start transition
//                image_src_X = 0;
//                x = previousPosition.getX() * World.tileSize; // Stand still for now, we want to change direction first. Start moving in next transition.
//                y =  previousPosition.getY() * World.tileSize;
//            }else{ // Object is standing still
//                image_src_X = 0;
//                x = gameObject.getPosition().getX() * World.tileSize;
//                y =  gameObject.getPosition().getY() * World.tileSize;
//            }
//        }else{
//            inTransition -=TRANSITION_SPEED;
//            image_src_X = (animation_part % ANIMATION_FRAMES) * gameObject.getWidth();
//            if ((World.tileSize - inTransition) % REAL_ANIMATION_SPEED == 0){
//                animation_part++;
//            }
//            switch (gameObject.getDirection()){
//                case UP: y =  (gameObject.getPosition().getY() * World.tileSize) + inTransition; // Move up step by step
//                    break;
//                case DOWN: y =  (gameObject.getPosition().getY() * World.tileSize)   - inTransition; // Move down step by step
//                    break;
//                case LEFT: x =  (gameObject.getPosition().getX() * World.tileSize) +  inTransition; // Move left step by step
//                    break;
//                case RIGHT: x =  (gameObject.getPosition().getX() * World.tileSize)  - inTransition; // Move right step by step
//                    break;
//            }
//
//            if (inTransition == 0){ // transition is done now
//                previousPosition = gameObject.getPosition();
//            }
//        }
//
//    }
}
