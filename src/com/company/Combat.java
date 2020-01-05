package com.company;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Scanner;

public class Combat {

    public static int combatMethod(Heroes hero, Monster monster, ArrayList inventory, Score score, Keyboard keyboard, int position) {
        int inventorySelection = 0;
        int count = 0;
        String submenu = "0";
        SecureRandom chanceToFlee = new SecureRandom();
        int flee = 0;
        boolean cont = true;
        Scanner input = new Scanner(System.in);

        System.out.println("You have encountered a " + monster.getName() + ", it has " + monster.getHp() + " health and " + monster.getDamage() + " damage!");
        while (cont){
            System.out.println("--- Combat Menu ---");
            System.out.printf("%s%n%s%n%s%n%s", "1) Fight", "2) Flee ", "3) Options", "> ");
            String combatChoice = input.nextLine();
            switch (combatChoice) {

                case "3":
                    SubMenu.subMenu(keyboard, position, hero, inventory, monster, monster, monster, score);
                    break;
                case "1":
                    cont = false;
                    while (monster.getHp() > 0 && hero.getHp() > 0) {
                        System.out.println("Which attack would you like to use?");
                        System.out.println("1) Melee");
                        System.out.println("2) Inventory");
                        if (hero.getName().equals("Rogue")) {
                            if (hero.getRogueMana() >= 25) {
                                System.out.println("3) Special ability ");
                            }
                        }
                        if (hero.getName().equals("Warrior") && count < 1) {
                            System.out.println("3) Special ability (You can only use this ability once for each Monster");
                        }
                        System.out.print(">");
                        String attackChoice = input.nextLine();
                        if (attackChoice.equals("2")) {
                            System.out.println("--- Inventory ---");
                            if (inventory.size() == 0) {
                                System.out.println("No items in inventory");
                            } else {
                                for (int i = 0; i < inventory.size(); i++) {
                                    System.out.println((i + 1) + ") " + inventory.get(i).toString());
                                }
                                System.out.println("Enter the number corresponding to the item you want to use");
                                inventorySelection = input.nextInt();
                                if (inventory.get(inventorySelection - 1).equals("Health potion")) {
                                    System.out.println("You consumed " + inventory.get(inventorySelection - 1) + " and gained 10 hp");
                                    hero.setHp(hero.getHp() + 10);
                                } else if (inventory.get(inventorySelection - 1).equals("Mana potion")) {
                                    if (hero.getName().equals("Rogue")) {
                                        System.out.println("You consumed " + inventory.get(inventorySelection - 1) + " and gained 25 mana");
                                        hero.setRogueMana(hero.getRogueMana() + 25);
                                    }
                                }
                                inventory.remove(inventorySelection - 1);
                            }

                        }
                        if (attackChoice.equals("1")) {
                            monster.setHp(monster.getHp() - hero.getDamage());
                            System.out.println("\nYou deal " + hero.getDamage() + " damage to the " + monster.getName());
                            if (monster.getHp() > 0) {
                                System.out.println("The " + monster.getName() + " has " + monster.getHp() + " health left");
                            }
                            if (monster.getHp() <= 0) {
                                score.setScore(score.getScore() + 5);
                                System.out.println(monster.getName() + " has died");
                                System.out.println("You have " + hero.getHp() + " health left.");
                                System.out.println("Do you want to use an item before leaving? \n1. Yes\n2. No");
                                submenu = input.nextLine();
                                if (submenu.equals("1")) {
                                    System.out.println("--- Inventory ---");
                                    if (inventory.size() != 0) {
                                        for (int i = 0; i < inventory.size(); i++) {
                                            System.out.println((i + 1) + ") " + inventory.get(i).toString());

                                        }
                                        System.out.println("Enter the number corresponding to the item you want to use");
                                        inventorySelection = input.nextInt();
                                        System.out.println("You consumed " + inventory.get(inventorySelection - 1) + " and gained 10hp");
                                        if (inventory.get(inventorySelection - 1).equals("Health potion")) {
                                            hero.setHp(hero.getHp() + 10);
                                        } else if (inventory.get(inventorySelection - 1).equals("Mana potion")) {
                                            if (hero.getName().equals("Rogue")) {
                                                System.out.println("You consumed " + inventory.get(inventorySelection - 1) + " and gained 25 mana");
                                                hero.setRogueMana(hero.getRogueMana() + 25);
                                            }
                                        }
                                        inventory.remove(inventorySelection - 1);

                                    } else {
                                        System.out.println("No items in inventory");
                                    }
                                }
                                break;
                            }
                            System.out.println("The " + monster.getName() + " attacks for " + monster.getDamage());
                            hero.setHp(hero.getHp() - monster.getDamage());
                            if (hero.getHp() > monster.getDamage()) {
                                System.out.println("You have " + hero.getHp() + " health left \n");
                            }
                            if (hero.getHp() <= 0) {
                                System.out.println("The " + monster.getName() + " killed you. GAME OVER!");
                                System.exit(0);
                                break;
                            }
                        } else if (attackChoice.equals("3")) {
                            if (hero.getName().equals("Rogue")) {
                                int mana = hero.getRogueMana() - 25;
                                hero.setRogueMana(mana);
                                int rougeSpecial = 50;
                                System.out.println("The bomb does " + rougeSpecial + " damage!");
                                monster.setHp(monster.getHp() - rougeSpecial);
                                if (monster.getHp() > 0) {
                                    System.out.println("The " + monster.getName() + " has " + monster.getHp() + " health left");
                                }
                                if (monster.getHp() <= 0) {
                                    score.setScore(score.getScore() + 5);
                                    System.out.println(monster.getName() + " has died");

                                    System.out.println("Do you want to use an item before leaving? \n1. Yes\n2. No");
                                    submenu = input.nextLine();
                                    if (submenu.equals("1")) {
                                        System.out.println("--- Inventory ---");
                                        if (inventory.size() != 0) {
                                            for (int i = 0; i < inventory.size(); i++) {
                                                System.out.println((1 + i) + ") " + inventory.get(i).toString());

                                            }
                                            System.out.println("Enter the number corresponding to the item you want to use");
                                            inventorySelection = input.nextInt();
                                            System.out.println("You consumed " + inventory.get(inventorySelection - 1) + " and gained 10hp");
                                            if (inventory.get(inventorySelection - 1).equals("Health potion")) {
                                                hero.setHp(hero.getHp() + 10);
                                            } else if (inventory.get(inventorySelection - 1).equals("Mana potion")) {
                                                if (hero.getName().equals("Rogue")) {
                                                    System.out.println("You consumed " + inventory.get(inventorySelection - 1) + " and gained 25 mana");
                                                    hero.setRogueMana(hero.getRogueMana() + 25);
                                                }
                                            }
                                            inventory.remove(inventorySelection - 1);
                                        } else {
                                            System.out.println("No items in inventory");
                                        }
                                    }
                                    break;
                                }
                                System.out.println("The " + monster.getName() + " attacks for " + monster.getDamage());
                                hero.setHp(hero.getHp() - monster.getDamage());
                                if (hero.getHp() > monster.getDamage()) {
                                    System.out.println("You have " + hero.getHp() + " health left \n");
                                }
                                if (hero.getHp() <= 0) {
                                    System.out.println("The " + monster.getName() + " killed you. GAME OVER!");
                                    System.exit(0);
                                    break;
                                }
                            } else if (hero.getName().equals("Warrior")) {
                                count += 1;
                                System.out.println("A Healing cure that heals you with 50hp");
                                int warriorSpecial = 50;
                                if (hero.getHp() <= 150) {
                                    hero.setHp(hero.getHp() + warriorSpecial);
                                } else {
                                    hero.setHp(200);
                                }
                            }
                        }
                    }
                    break;

                case "2":
                    cont = false;
                    flee = chanceToFlee.nextInt(2);
                    if (flee == 1) {
                        System.out.println("Could not run away");
                    } else {
                        System.out.println("You ran away");
                        if (position > 7) {
                            position -= 7;
                        }
                        else {
                            position += 7;
                        }
                    }
                    break;
            }
        }
        return position;
    }
}
