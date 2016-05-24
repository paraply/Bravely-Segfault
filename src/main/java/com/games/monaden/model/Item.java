package com.games.monaden.model;

/**
 * Item is things in the game you can either trigger effects or use in the game.
 * It contains name, description and if it a consumable och/or a KeyItem.
 */
public interface Item {

    public String getDescription();

    public String getName();
}
