package com.games.monaden.model;

import org.junit.Before;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Admin on 2016-05-03.
 */
public class InventoryTest {

    Inventory inventory;

    @Before
    public void init(){
        List<Item> itemList = new LinkedList<>();
        inventory = new Inventory(itemList);

    }
    // Create Inventory
    // Get List of item
    // Add an Item
    // Get Item
    // Move item
    // Remove Item
    // Combine Item?
}
