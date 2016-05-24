package com.games.monaden.control;

import com.games.monaden.model.Dialog;
import com.games.monaden.view.Render;
import javafx.scene.input.KeyCode;

/**
 * Created by Anton on 2016-05-24.
 */
public class DialogController {
    private Dialog currentDialog;

    public void setCurrentDialog(Dialog d){currentDialog = d;}

    public boolean handleMovement(KeyCode moveReq){
        if (moveReq != null && currentDialog.getChoiceCount() != 0) {
            if(moveReq == KeyCode.UP){
                Render.getInstance().getDialog().selectPreviousAnswer();
                return true;
            }
            else if(moveReq == KeyCode.DOWN) {
                Render.getInstance().getDialog().selectNextAnswer();
                return true;
            }
        }

        return false;

    }

    public boolean handleSpecial(KeyCode funcReq){
        if (funcReq != null) {
            if(funcReq == KeyCode.SPACE) {
                if(currentDialog.getChoiceCount() == 0){
                    Render.getInstance().getDialog().hideDialog();
                    return true;
                }
                else {
                    currentDialog = currentDialog.makeAChoice(Render.getInstance().getDialog().getSelected());
                    if (currentDialog.getDialogText().equals("")) {
                        Render.getInstance().getDialog().hideDialog();
                        return true;
                    } else {
                        Render.getInstance().getDialog().newDialog(currentDialog);
                    }
                }
            }
        }
        return false;
    }
}
