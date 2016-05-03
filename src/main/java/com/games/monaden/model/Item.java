package com.games.monaden.model;

/**
 * Created by Admin on 2016-05-03.
 */
public class Item {

    private boolean consumable = false;
    private boolean keyItem = false;

    public Item(String description) {
    }

    public void setConsumable(boolean consumable) {
        this.consumable = consumable;
    }

    public void setKeyItem(boolean keyItem) {
        this.keyItem = keyItem;
    }

    public boolean getConsumable() {
        return consumable;
    }
}
