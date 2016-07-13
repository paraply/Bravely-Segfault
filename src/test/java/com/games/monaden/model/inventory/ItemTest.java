package com.games.monaden.model.inventory;


import com.games.monaden.model.inventory.Item;
import com.games.monaden.model.inventory.KeyItem;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Admin on 2016-05-03.
 */
public class ItemTest {

    private Item item1;

    @Before
    public void init(){
        item1 = new KeyItem("Item1","a Key item");
    }

    @Test
    public void testKeyItem(){

        assertTrue(item1 instanceof KeyItem);
    }

    @Test
    public void testGetDescription(){
        assertTrue(item1.getDescription().equals("a Key item"));
    }

    @Test
    public void testGetItemName(){
        assertTrue(item1.getName().equals("Item1"));
    }


}
