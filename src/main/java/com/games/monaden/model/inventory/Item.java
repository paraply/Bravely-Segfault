package com.games.monaden.model.inventory;

/**
 Currently only one implementation of this
 Could be extended to have consumables and equipment if a battle system is added.
 */
public interface Item {

    String getDescription();

    String getName();
}
