package com.games.monaden.control;

import com.games.monaden.model.Dialog;
import com.games.monaden.model.Inventory;
import com.games.monaden.view.Render;
import javafx.scene.input.KeyCode;

/**
 * Created by Anton on 2016-05-24.
 */
public class DialogController {
    private Dialog currentDialog;
    private Inventory inventory;
    private int selection = 0;

    public void setCurrentDialog(Dialog d){currentDialog = d;}

    public boolean handleMovement(KeyCode moveReq){
        if (moveReq != null && currentDialog.getChoiceCount(inventory) != 0) {
            if(moveReq == KeyCode.UP){
                selection = currentDialog.selectUp(selection, inventory);
                Render.getInstance().getDialog().select(selection);
                return true;
            }
            else if(moveReq == KeyCode.DOWN) {
                selection = currentDialog.selectDown(selection, inventory);
                Render.getInstance().getDialog().select(selection);
                return true;
            }
        }

        return false;

    }

    public boolean handleSpecial(KeyCode funcReq){
        if (funcReq != null) {
            if(funcReq == KeyCode.SPACE) {
                if(currentDialog.getChoiceCount(inventory) == 0){
                    Render.getInstance().getDialog().hideDialog();
                    return true;
                }
                else {
                    currentDialog = currentDialog.makeAChoice(Render.getInstance().getDialog().getSelected(), inventory);
                    if (currentDialog.getDialogText().equals("")) {
                        Render.getInstance().getDialog().hideDialog();
                        return true;
                    } else {
                        Render.getInstance().getDialog().newDialog(currentDialog, inventory);
                    }
                }
            }
        }
        return false;
    }

    public void startDialog(Dialog dialog) {
        currentDialog = dialog;
        selection = 0;
        System.out.println("Creating new dialog: " + dialog.getDialogText());
        Render.getInstance().getDialog().newDialog(dialog, inventory);
    }
}
