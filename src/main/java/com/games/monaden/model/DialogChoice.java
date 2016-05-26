package com.games.monaden.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anton on 2016-05-26.
 */
public class DialogChoice {
    private Dialog dialog;
    private String choiceText;
    private List<String> requirements;

    public String getChoiceText(){return choiceText;}
    public Dialog getDialog(){return dialog;}

    public DialogChoice(Dialog d, String s){
        dialog = d;
        choiceText = s;
        requirements = new ArrayList<>();
    }

    public void addRequirement(String i){
        requirements.add(i);
    }

    public boolean reqSatisfied(Inventory inventory){
        boolean result = true;
        for(String r : requirements){
            if(!inventory.containsItem(r)){
                result = false;
            }
        }
        return result;
    }
}
