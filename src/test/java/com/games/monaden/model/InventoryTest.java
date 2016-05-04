package com.games.monaden.model;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/*
 *  The player will have a Inventory with Items
 *  You want to be able to show all items, add a item, get a item,
 *  change place with items, remove items.
 *
 *  It still unclear if we want to combine items.
 */

public class InventoryTest {

    Inventory inventory;
    List<Item> itemList;
    Item item;

    @Before
    public void init(){
        itemList = new LinkedList<>();
        inventory = new Inventory(itemList);
        item = new Item("Item","This is a item");
    }

    @Test
    public void testGetList(){
        List<Item> givenList = inventory.getItemList();
        assertTrue(givenList.equals(itemList) && givenList != null);
    }

    @Test
    public void testAddItem(){
        inventory.addItem(item);
        List<Item> givenList = inventory.getItemList();
        assertTrue(givenList.contains(item) && givenList.size() == 1);
    }

    @Test
    public void testGetItem(){
        inventory.addItem(item);
        Item givenItem = inventory.getItem("Item");
        System.out.println(givenItem.getName() + " is " + item.getName());
        assertTrue(givenItem.equals(item));
    }

    // Do we need this functionality?
    @Test
    public void testMoveItem() {
        inventory.addItem(item);
        Item newItem = new Item("new Item","This is a new item");
        inventory.addItem(newItem);
        List<Item> givenList = inventory.getItemList();
        inventory.moveItem("Item",1);
        assertTrue(itemList.get(0).equals(newItem) && givenList.get(1).equals(newItem) &&
                    itemList.get(1).equals(item) && givenList.get(0).equals(item));

    }

        @Test
    public void testRemoveItem(){
        inventory.addItem(item);
        Item newItem = new Item("new Item","This is a new item");
        inventory.addItem(newItem);
        boolean removed = inventory.removeItem(item);
        assertTrue(removed == true && itemList.size() == 1 &&
                itemList.contains(newItem) && !itemList.contains(item));
    }
}
