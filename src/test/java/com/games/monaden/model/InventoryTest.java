package com.games.monaden.model;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/*
 *  The player will have a Inventory with Items
 *  You want to be able to show all items, add a item, get a item,
 *  change place with items, remove items.
 *
 *  It still unclear if we want to combine items.
 */

public class InventoryTest {

    Inventory inventory;

    @Before
    public void init(){
        List<Item> itemList = new LinkedList<>();
        inventory = new Inventory(itemList);

    }

    @Test
    public void testGetList(){

    }

    @Test
    public void testAddItem(){

    }

    @Test
    public void testGetItem(){

    }

    @Test
    public void testMoveItem(){

    }

    @Test
    public void testRemoveItem(){

    }

    @Test
    public void testCombineItem(){

    }
}
