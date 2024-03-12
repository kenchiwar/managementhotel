package com.demo.entities;

public class Item {
    private Room room;
    private int quantity_item;

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getQuantity_item() {
        return quantity_item;
    }

    public void setQuantity_item(int quantity_item) {
        this.quantity_item = quantity_item;
    }

    public Item(){}
    
    public Item(Room room, int quantity_item) {
        this.room = room;
        this.quantity_item = quantity_item;
    }
    
}