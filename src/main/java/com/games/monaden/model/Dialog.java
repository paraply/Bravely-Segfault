/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.games.monaden.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Stefan
 */
public class Dialog {
    
    private final String dialog;
    private final List<String> text;
    private final List<Dialog> choices;

    Dialog(String dialog){
        this.dialog = dialog;    
        this.text = new ArrayList<>();
        this.choices = new ArrayList<>();
    }
    
    public void readInChoices(String text, Dialog dialog){
        this.text.add(text);
        this.choices.add(dialog);
    }
    
    public Dialog makeAChoice(int id){
        return choices.get(id);
    }
    
    public String getChoiceText(int id){
        return text.get(id);
    }
    
    public String getDialog(){
        return dialog;
    }
    
    
    
}
