package com.games.monaden.control;

import com.games.monaden.model.Dialog;
import com.games.monaden.model.World;
import com.games.monaden.view.Render;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;

/**
 * Created by paraply on 2016-04-13.
 */
public class GameLoop extends AnimationTimer {

    // Could probably use inspiration from
    // https://carlfx.wordpress.com/2012/04/09/javafx-2-gametutorial-part-2/

    public final static int FREQUENCY = 16;
    private int countDown = FREQUENCY;

    private World world;
    CharacterController playerCharacter;

    private Dialog currentDialog;
    private enum InputState { MOVEMENT, DIALOG }
    private InputState inputState = InputState.MOVEMENT;
    private int currentChoice = 0;

    public void initializeGame(){
        world = new World("second.xml" );
        Render.getInstance().setWorld(world);
        playerCharacter = new CharacterController();
    }

    @Override
    public void handle(long now) {
        Render.getInstance().redraw();
        if (countDown > 0){  // used to add a delay (better than sleep) to user movement
            countDown--;
        }
        else if(inputState == InputState.MOVEMENT){
            UserInput userInput = UserInput.getInstance();
            KeyCode moveReq = userInput.getLatestMovementKey();
            if (moveReq != null) {
                playerCharacter.handleMovement(moveReq, world);
                countDown = FREQUENCY;
            }

            KeyCode funcReq = userInput.getLatestFunctionKey();
            if (funcReq != null) {
                Dialog dialog = playerCharacter.handleInteractions(funcReq, world);
                if(dialog != null){
                    currentDialog = dialog;
                    inputState = InputState.DIALOG;
                    currentChoice = 0;
                    System.out.println(currentDialog.toString());
                }
            }
        }
        else if(inputState == InputState.DIALOG){
            UserInput userInput = UserInput.getInstance();
            KeyCode moveReq = userInput.getLatestMovementKey();
            if (moveReq != null && currentDialog.getChoiceCount() != 0) {
                if(moveReq == KeyCode.UP){
                    currentChoice --;
                    if(currentChoice < 0){
                        currentChoice = currentDialog.getChoiceCount() - 1;
                    }
                    System.out.println("\n> " + currentDialog.getChoiceText(currentChoice) + "\n");
                    countDown = FREQUENCY;
                }
                else if(moveReq == KeyCode.DOWN) {
                    currentChoice++;
                    if (currentChoice >= currentDialog.getChoiceCount()) {
                        currentChoice = 0;
                    }
                    System.out.println("\n> " + currentDialog.getChoiceText(currentChoice) + "\n");
                    countDown = FREQUENCY;
                }
            }

            KeyCode funcReq = userInput.getLatestFunctionKey();
            if (funcReq != null) {
                if(funcReq == KeyCode.SPACE) {
                    if(currentDialog.getChoiceCount() == 0){
                        inputState = InputState.MOVEMENT;
                    }
                    else {
                        currentDialog = currentDialog.makeAChoice(currentChoice);
                        if (currentDialog.getDialogText().equals("")) {
                            inputState = InputState.MOVEMENT;
                        } else {
                            System.out.println(currentDialog.toString());
                        }
                    }
                }
            }
        }
    }
}
