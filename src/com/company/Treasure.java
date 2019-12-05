package com.company;

public class Treasure {
    Item item1;
    Item item2;
    int coins;


    public Item getItem1() {
        return item1;
    }

    public Item getItem2() {
        return item2;
    }

    public int getCoins() {
        return coins;
    }

    public Treasure(Item item1, Item item2, int coins){
        this.item1 = item1;
        this.item2 = item2;
        this.coins = coins;
    }
}
