package com.games.monaden.model;

import com.games.monaden.model.inventory.Inventory;
import com.games.monaden.model.inventory.Item;
import com.games.monaden.model.inventory.KeyItem;
import org.junit.Before;
import org.junit.Test;

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
    Item item;

    @Before
    public void init(){
        inventory = new Inventory();
        item = new KeyItem("Item","This is a item");
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

        @Test
    public void testRemoveItem(){
        inventory.addItem(item);
        Item newItem = new KeyItem("new Item","This is a new item");
        inventory.addItem(newItem);
        boolean removed = inventory.removeItem(item);
        assertTrue(removed && inventory.getItemList().size() == 1 &&
                inventory.containsItem(newItem.getName()) && !inventory.containsItem(item.getName()));
    }
}
