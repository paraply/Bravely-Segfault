package com.games.monaden.model;

import java.util.List;

/**
 *  This class contains an list with Items
 *  You can get the list, add, get and remove items.
 */
public class Inventory {

    private List<Item> itemList;

    public Inventory(List<Item> itemList) {
        this.itemList = itemList;
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
        return new Item("No item","No item exist in the list!");
    }

    /**
     * The idea is to move a item up or down in the list of items.
     * Feels like a funtion we don't need.
     * @param item
     * @param i
     */
    public void moveItem(String item, int i) {
        //??
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
