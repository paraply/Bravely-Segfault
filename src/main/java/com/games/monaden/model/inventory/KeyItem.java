package com.games.monaden.model.inventory;

import com.games.monaden.model.inventory.Item;

/**
 * Created by Anton on 2016-05-24.
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
