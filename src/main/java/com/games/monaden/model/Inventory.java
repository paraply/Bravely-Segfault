package com.games.monaden.model;

import java.util.ArrayList;
import java.util.List;

/**
 *  This class contains an list with Items
 *  You can get the list, add, get and remove items.
 */
public class Inventory {

    private List<Item> itemList;

    public Inventory() {
        itemList = new ArrayList<>();
    }

    /**
     * This methods gets the current list.
     * @return List<Items>
     */

    public List<Item> getItemList() {
        return itemList;
    }

    /**
     * Adds a Item to the list
     * @param item
     */
    public void addItem(Item item) {
        itemList.add(item);
    }

    /**
     * Given a string will it return the wanted Item.
     * @param item
     * @return
     */
    public Item getItem(String item){
        for(Item theItem : itemList){
            if(theItem.getName().equals(item))
                return theItem;
        }
        return null;
    }

    public boolean containsItem(String item){
        for(Item i : itemList){
            if(i.getName().equals(item)){
                return true;
            }
        }
        return false;
    }

    /**
     * Removes a item form the list
     * @param item
     * @return
     */
    public boolean removeItem(Item item) {
        return itemList.remove(item);
    }
}
