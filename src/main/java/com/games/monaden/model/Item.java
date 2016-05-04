package com.games.monaden.model;

/**
 * Item is things in the game you can either trigger effects or use in the game.
 * It contains name, description and if it a consumable och/or a KeyItem.
 */
public class Item {

    private final String name;
    private boolean consumable = false;
    private boolean keyItem = false;
    private final String description;

    /**
     * Constructor for Item with a given name and description
     * @param name
     * @param description
     */
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

    public String getName() {
        return name;
    }
}
