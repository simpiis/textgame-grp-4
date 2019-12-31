package com.company;

import java.util.Random;

public class Item {
    private String name;

    public Item(String name){
        this.name = name;

    }

    public String getName() {
        return name;
    }
    public static Item createItem(){
        Random rand = new Random();
        int potionType = rand.nextInt(2);
        if (potionType == 0){
            Potion potion = new Potion("Health potion", 2);
            return potion;
        }else{
            Potion potion = new Potion("Mana potion", 2);
            return potion;
        }
    }
}
