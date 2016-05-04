package com.games.monaden.model;

/**
 * Created by Admin on 2016-05-03.
 */
public class Item {

    private final String name;
    private boolean consumable = false;
    private boolean keyItem = false;
    private final String description;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
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

    public boolean getKeyItem() {
        return keyItem;
    }

    public String getDescription() {
        return description;
    }
}
