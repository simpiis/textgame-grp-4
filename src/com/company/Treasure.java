package com.company;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

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
    public static ArrayList openTreasure(Rooms[] list , int position, Treasure chest, ArrayList inventory, Score score){
        Scanner input = new Scanner(System.in);
        System.out.println("You found a chest!");
        System.out.println("Press 1 to open\nPress 2 to ignore chest and permanently remove it. ");
        String choice = input.nextLine();
        if (choice.equals("1")) {
            System.out.println("First item is a " + (((Item)chest.getItem1()).getName()));
            System.out.println("Second item is a " + (((Item)chest.getItem2()).getName()));
            System.out.println("Third item is a " + (((Item)chest.getItem3()).getName()));
            inventory.add(chest.getItem1().getName());
            inventory.add(chest.getItem2().getName());
            inventory.add(chest.getItem3().getName());
            int sum = chest.coinsGenerator();
            sum += score.getScore();
            score.setScore(sum);
            list[position].setTreasure(null);
        }else{
            list[position].setTreasure(null);
        }
        return inventory;
    }

}
