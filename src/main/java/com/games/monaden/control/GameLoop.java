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

    public final static int FREQUENCY = 16;
    private int countDown = FREQUENCY;

    private World world;
    CharacterController playerCharacter;
    AudioController audioController;

    private Dialog currentDialog;
    private enum InputState { MOVEMENT, DIALOG }
    private InputState inputState = InputState.MOVEMENT;

    public void initializeGame(){
        world = new World("second.xml" );
        Render.getInstance().setWorld(world);
        playerCharacter = new CharacterController();
        audioController = new AudioController();
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
                    System.out.println("Creating new dialog: " + dialog.getDialogText());
                    Render.getInstance().renderDialog.newDialog(dialog);
                }
            }
        }
        else if(inputState == InputState.DIALOG){
            UserInput userInput = UserInput.getInstance();
            KeyCode moveReq = userInput.getLatestMovementKey();
            if (moveReq != null && currentDialog.getChoiceCount() != 0) {
                if(moveReq == KeyCode.UP){
                    System.out.println("UPP");
                    Render.getInstance().renderDialog.selectPreviousAnswer();
                    countDown = FREQUENCY;
                }
                else if(moveReq == KeyCode.DOWN) {
                    System.out.println("NER");
                    Render.getInstance().renderDialog.selectNextAnswer();
                    countDown = FREQUENCY;
                }
            }

            KeyCode funcReq = userInput.getLatestFunctionKey();
            if (funcReq != null) {
                if(funcReq == KeyCode.SPACE) {
                    if(currentDialog.getChoiceCount() == 0){
                        Render.getInstance().renderDialog.hideDialog();
                        inputState = InputState.MOVEMENT;
                    }
                    else {
                        currentDialog = currentDialog.makeAChoice(Render.getInstance().renderDialog.getSelected());
                        if (currentDialog.getDialogText().equals("")) {
                            Render.getInstance().renderDialog.hideDialog();
                            inputState = InputState.MOVEMENT;
                        } else {
                            Render.getInstance().renderDialog.newDialog(currentDialog);
                            //System.out.println(currentDialog.toString());
                        }
                    }
                }
            }
        }
    }
}
