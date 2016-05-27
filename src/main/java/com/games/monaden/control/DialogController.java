package com.games.monaden.control;

import com.games.monaden.model.*;
import com.games.monaden.view.Render;
import javafx.scene.input.KeyCode;

import java.util.Observable;

/**
 * Created by Anton on 2016-05-24.
 */
public class DialogController extends Observable{
    private Dialog currentDialog;
    private Inventory inventory = new Inventory();
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
                    Dialog temp = currentDialog.makeAChoice(Render.getInstance().getDialog().getSelected(), inventory);
                    if (temp.getDialogText().equals("")) {
                        Render.getInstance().getDialog().hideDialog();
                        return true;
                    } else {
                        startDialog(temp);
                    }
                }
            }
        }
        return false;
    }

    public void startDialog(Dialog dialog) {
        currentDialog = dialog;
        if(dialog.getItem() != null){
            inventory.addItem(dialog.getItem());
        }
        if(dialog.getTransition() != null){
            setChanged();
            notifyObservers(dialog.getTransition());
            Render.getInstance().getDialog().hideDialog();
        }
        else {
            selection = 0;
            System.out.println("Creating new dialog: " + dialog.getDialogText());
            Render.getInstance().getDialog().newDialog(dialog, inventory);
            System.out.println(inventory.toString());
        }
    }
}
