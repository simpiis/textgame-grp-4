package com.company;

import java.util.Random;

public class Treasure {
    Item item1;
    Item item2;
    Item item3;


    public Item getItem1() {
        return item1;
    }

    public Item getItem2() { return item2; }

    public Item getItem3() { return item3; }

    public Treasure(Item item1, Item item2, Item item3){
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
    }
    public int coinsGenerator (){
        Random rand = new Random();
        int coins = 0;
        while (coins < 2){
            coins =rand.nextInt(10);
        }
        System.out.println("You found also found: " + coins + " coins");
        return coins;
    }

}
