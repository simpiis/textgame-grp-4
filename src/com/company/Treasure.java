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
    public static ArrayList openTreasure(Rooms[] list , int position, Treasure chest, ArrayList inventory, Score score, int firekeys, int oceankeys, int dirtkeys, int windkeys) {
        Scanner input = new Scanner(System.in);
        String[] keytypes = new String[]{"Wind", "Fire", "Ocean", "Dirt"};
        Random rand = new Random();
        int number = rand.nextInt(4);
        int item1Cont = 0;
        int item2Cont = 0;
        int item3Cont = 0;
        int inventorySelection = 0;
        boolean drop = true;
        System.out.println("You found a chest!");
        System.out.println("Press 1 to open\nPress 2 to ignore chest and permanently remove it. ");
        String choice = input.nextLine();
        if (choice.equals("1")) {
            System.out.println("--------");
            System.out.println("You found:");
            System.out.println("1. " + (((Item) chest.getItem1()).getName()));
            System.out.println("2. " + (((Item) chest.getItem2()).getName()));
            System.out.println("3. " + keytypes[number] + " " + (((Item) chest.getItem3()).getName()));
            if (keytypes[number] == "Ocean"){
                oceankeys +=1;
            }else if(keytypes[number] == "Wind"){
                windkeys +=1;
            }else if (keytypes[number] == "Fire"){
                firekeys+=1;
            }else{
                dirtkeys +=1;
            }
            int sum = chest.coinsGenerator();
            sum += score.getScore();
            score.setScore(sum);
            System.out.println("--------");
            do {
                if (inventory.size() == 0 || inventory.size() == 1 || inventory.size() == 2) {
                    inventory.add(chest.getItem1().getName());
                    inventory.add(chest.getItem2().getName());
                    inventory.add(chest.getItem3().getName());
                    list[position].setTreasure(null);
                    break;
                }
                if (inventory.size() >= 3  ) {
                    if (inventory.size() < 5) {
                        int placeLeft = 5 - inventory.size();
                        System.out.println("You can only add: " + placeLeft + " more items");
                        System.out.println("What item do you want to add?");
                        String addChoice = input.nextLine();
                        if (addChoice.equals("1")) {
                            if (item1Cont == 0) {
                                inventory.add(chest.getItem1().getName());
                                item1Cont += 1;
                            } else {
                                System.out.println("You have already added this item");
                            }
                        } else if (addChoice.equals("2")) {
                            if (item2Cont == 0) {
                                inventory.add(chest.getItem2().getName());
                                item2Cont += 1;
                            } else {
                                System.out.println("You have already added this item");
                            }
                        } else if (addChoice.equals("3")){
                            if (item3Cont == 0) {
                                inventory.add(chest.getItem3().getName());
                                item3Cont += 1;
                            } else {
                                System.out.println("You have already added this item");
                            }
                        } else {
                            System.out.println("Invalid number");
                        }
                    } else {
                        if (item1Cont == 1 && item2Cont == 1 && item3Cont == 1) {
                            break;
                        }
                        System.out.println("You can´t add more items, your inventory is full");
                        System.out.println("1. Drop Item \n2. Dont pick up new items");
                        String dropItem = input.nextLine();
                        if (dropItem.equals("1")) {
                            for (int i = 0; i < inventory.size(); i++) {
                                System.out.println((i + 1) + ") " + inventory.get(i).toString());
                            }
                            System.out.println("Enter the number corresponding to the item you want to Drop");
                            String selection = input.nextLine();
                            if (selection.equals("1")) {
                                inventorySelection = 0;
                            } else if (selection.equals("2")) {
                                inventorySelection = 1;
                            }
                            if (selection.equals("3")) {
                                inventorySelection = 2;
                            }
                            if (selection.equals("4")) {
                                inventorySelection = 3;
                            }
                            if (selection.equals("5")) {
                                inventorySelection = 4;
                            }
                            inventory.remove(inventorySelection);
                        } else if (dropItem.equals("2")) {
                            drop = false;
                        } else {
                            System.out.println("Invalid number");
                        }
                    }
                }
            } while (drop);
            list[position].setTreasure(null);
        } else {
            list[position].setTreasure(null);
        }
        return inventory;
    }
}
