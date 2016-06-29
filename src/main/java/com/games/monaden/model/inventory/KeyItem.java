package com.games.monaden.model.inventory;

/**
 A key item is an item that is unique and that can be used as a requirement for a dialog choice.
 */
public class KeyItem implements Item {

    private final String name;
    private final String description;

    public KeyItem(String _name, String _description){
        name = _name;
        description = _description;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public boolean equal(Item item){
        if(item.getName() == name || item.getDescription() == description){
            return true;
        }
        return false;
    }

    public String toString(){ return "Name: " + name + "Description: " + description; }
}
