package com.games.monaden.model;

import java.util.List;

/**
 * Created by Admin on 2016-05-03.
 */
public class Inventory {

    private List<Item> itemList;

    public Inventory(List<Item> itemList) {
        this.itemList = itemList;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void addItem(Item item) {
        itemList.add(item);
    }

    public Item getItem(String item){
        //TODO
        return new Item("Need","to change");
    }

    public void moveItem(String item, int i) {
        //TODO
    }

    public boolean removeItem(Item item) {
        //TODO
        return false;
    }
}
