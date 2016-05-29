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
}
