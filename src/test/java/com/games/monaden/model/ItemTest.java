package com.games.monaden.model;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Admin on 2016-05-03.
 */
public class ItemTest {

    private Item item1;
    private Item item2;

    @Before
    public void init(){
        item1 = new Item("Item1","First Item");
        item1.setConsumable(true);
        item2 = new Item("Item2","Second Item");
        item2.setKeyItem(true);
    }

    @Test
    public void testConsumableItem(){

        assertTrue(item1.getConsumable() && !item2.getConsumable());
    }

    @Test
    public void testKeyItem(){
        assertTrue(item2.getKeyItem() && !item1.getKeyItem());
    }

    @Test
    public void testGetDescription(){
        assertTrue(item1.getDescription().equals("First Item"));
    }

    @Test
    public void testGetItemName(){
        assertTrue(item1.getName().equals("Item1"));
    }

}
