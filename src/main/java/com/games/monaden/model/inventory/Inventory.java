package com.games.monaden.model.inventory;

import java.util.ArrayList;
import java.util.List;

/**
 Class is responsible for keeping track of any items the player has in their inventory.
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

    public String toString(){
        StringBuilder result = new StringBuilder();
        for(Item item : itemList){
            result.append("Name: ");
            result.append(item.getName());
            result.append(" Description: ");
            result.append(item.getDescription());
            result.append("\n");
        }
        return result.toString();
    }
}
