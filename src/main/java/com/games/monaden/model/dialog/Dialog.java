/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.games.monaden.model.dialog;

import com.games.monaden.model.*;
import com.games.monaden.model.inventory.Inventory;
import com.games.monaden.model.inventory.Item;
import com.games.monaden.model.primitives.Transition;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 Class is a tree-structure for dialogs.
 Dialogs without any choices still contains a choice that is empty, in order to identify when to close the dialog.
 It also takes into account the player's current inventory, and only shows choices where the inventory satisfies their requirements.
 */
public class Dialog {
    
    private String dialogText;
    private final List<DialogChoice> choices;
    private File imageFile;
    private Item item;
    private Transition transition;

    public int getChoiceCount(Inventory inventory){
        int count = 0;
        for(DialogChoice dc : choices){
            if (dc.reqSatisfied(inventory)){
                count++;
            }
        }
        return count;
    }

    public int getChoiceTextCount(Inventory inventory){
        int count = 0;
        for(DialogChoice dc : choices){
            if (dc.reqSatisfied(inventory) && !dc.getChoiceText().equals("")){
                count++;
            }
        }
        return count;
    }

    /**
     * Constructor for when there is no text yet. Text is to be added later.
     */
    public Dialog(){
        this.choices = new ArrayList<>();
    }

    public Dialog(String dialogText){
        this.dialogText = dialogText;
        this.choices = new ArrayList<>();
    }
    
    public void addChoice(DialogChoice dc){
        this.choices.add(dc);
    }
    
    public Dialog makeAChoice(int id, Inventory inventory){
        DialogChoice d = choices.get(id);
        if(d.reqSatisfied(inventory)){
            return d.getDialog();
        }
        return null;
    }

    public int selectUp(int id, Inventory inventory){
        while(id > 0){
            id --;
            if(choices.get(id).reqSatisfied(inventory)) {
                return id;
            }
        }
        return id;
    }

    public int selectDown(int id, Inventory inventory){
        while(id < getChoiceCount(inventory) - 1){
            id ++;
            if(choices.get(id).reqSatisfied(inventory)) {
                return id;
            }
        }
        return id;
    }
    
    public String getChoiceText(int id){
        return choices.get(id).getChoiceText();
    }

    public void setItem(Item item){
        this.item = item;
    }

    public Item getItem(){
        return item;
    }

    public Transition getTransition(){ return transition; }

    public void setTransition(Transition t){ this.transition = t; }

    public String getDialogText(){
        return dialogText;
    }
    
    public void setDialogText(String text) {
        this.dialogText = text.replace("*YOU*", World.playerName);
        this.dialogText = dialogText.replace("*N*", System.lineSeparator());
    }

    public void setImageFile (File file) {
        this.imageFile = file;
    }

    public File getImageFile(){
        return imageFile;
    }

    /**
     * Sets a single child for when there is no choice.
     * @param child
     */
    public void setChild (DialogChoice child) {
        choices.add(child);
    }

    
    public String toString(){
        String result = getDialogText();
        for(int i = 0; i < choices.size(); i++){
            result += "\n*" + getChoiceText(i);
        }
        return result;
    }
}
