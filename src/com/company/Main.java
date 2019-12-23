package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.*;

import java.lang.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Main runApps = new Main();
        int mainMenu = 0;
        Scanner input = new Scanner(System.in);
        Heroes hero = new Heroes(1, 1, "");
        Monster minotaur = new Monster(50,10, "Minotaur");
        Monster typhone = new Monster(60,30, "Typhone");
        Monster wampa = new Monster(100,20, "Wampa");
        Score score = new Score();
        Treasure chest1 = new Treasure(runApps.createItem(),runApps.createItem(), runApps.createKey());
        Keyboard keyboard = new Keyboard("2","1", "3", "4");
        int choice;
        int position = 0;
        int round = 1;
        int highscore = 0;
        Rooms[] rooms = runApps.createRooms(minotaur, typhone, wampa, chest1);
        ArrayList<Item> playerInventory = new ArrayList<>();
        boolean cont = true;

        Path filePath = Paths.get("HighScore");
        Scanner scanner = new Scanner(filePath);
        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                highscore = scanner.nextInt();
            } else {
                scanner.next();
            }
        }

        while (cont) {
            System.out.println("--- Main Menu ---");
            System.out.println("1. Start new game\n2. Load game(not implemented)\n3. Quit\n4. Scoreboard");
            mainMenu = input.nextInt();
            if(mainMenu==3){
                System.exit(0);}
            else if(mainMenu==2){
            }
            else if (mainMenu == 4) {
                System.out.println("Current High Score is: " + highscore);
                System.out.println("Press '1' to go back to Main Menu");
                input.nextInt();
            } else {
                cont = false;
            }
        }

        do {
            System.out.println("*************************");
            System.out.println("*  Welcome to our game  *");
            System.out.println("*                       *");
            System.out.println("*   Choose your hero!   *\n" +
                    "*   1. Rogue            *\n" +
                    "*   2. Warrior          *\n" +
                    "*   3. Mage             *");
            System.out.println("*************************");
            System.out.print("> " );

            choice = input.nextInt();

        } while (choice != 1 && choice != 2 && choice != 3);

        switch (choice) {
            case 1:
                System.out.println("*************************");
                hero.setHp(110);
                hero.setDamage(10);
                hero.setName("Rogue");
                hero.setRogueMana(50);
                hero.printHero();
                System.out.println("*************************");

                break;
            case 2:
                System.out.println("*************************");
                hero.setHp(90);
                hero.setDamage(30);
                hero.setName("Warrior");
                hero.printHero();
                System.out.println("*************************");
                break;
            case 3:
                System.out.println("*************************");
                hero.setHp(100);
                hero.setDamage(20);
                hero.setName("Mage");
                hero.printHero();
                System.out.println("*************************");
                break;
        }

        while(round <= 49){
            System.out.println("Round: " + round);
            System.out.println("Score: " + score.getScore());
            if(rooms[position].getMonster()!= null){
                if ( rooms[position].getMonster() == minotaur) {
                    hero.setHp(combatMethod(hero, minotaur, playerInventory, score, keyboard));
                    rooms[position].setMonster(null);
                }
                else if (rooms[position].getMonster() == typhone) {
                    hero.setHp(combatMethod(hero, typhone, playerInventory, score, keyboard));
                    rooms[position].setMonster(null);
                } else {
                    hero.setHp(combatMethod(hero, wampa, playerInventory, score, keyboard));
                    rooms[position].setMonster(null);
                }

            }else if(rooms[position].getTreasure()!= null){
                playerInventory=runApps.openTreasure(rooms,position,rooms[position].getTreasure(), playerInventory, score);
            }else {
                position = runApps.move(position, hero, rooms, keyboard);
            }
            round++;
        }
        if (round > 49) {
            if (score.getScore() > highscore) {
                Writer fileWriter = new FileWriter("HighScore", false);
                int finalScore = score.getScore();
                fileWriter.write(finalScore + "");
                fileWriter.close();

                System.out.println("Your final score is: " + finalScore);
                System.out.println("You have now the High Score!");
            } else {
                System.out.println("Your final score is: " + score.getScore() + "\nWell Played!");
            }
        }
    }
    public static int combatMethod(Heroes hero, Monster monster, ArrayList inventory, Score score, Keyboard keyboard) {
        Main runApp = new Main();
        int inventorySelection = 0;
        int count = 0;
        int submenu = 0;
        SecureRandom chanceToFlee = new SecureRandom();
        int flee = 0;
        boolean cont = true;
        Scanner input = new Scanner(System.in);

        System.out.println("You have encountered a " + monster.getName() + ", it has " + monster.getHp() + " health and " + monster.getDamage() + " damage!");
        while (cont){
            System.out.println("--- Combat Menu ---");
            System.out.printf("%s%n%s%n%s%n%s", "1) Fight", "2) Flee (not fully implemented)", "3) Sub Menu", "> ");
            int combatChoice = input.nextInt();
            switch (combatChoice) {
                case 3:
                    runApp.subMenu(keyboard);
                    break;
                case 1:
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
                            System.out.println("3) Special ability (You can only use this ability 1 for 1 Monster");
                        }
                        System.out.print(">");
                        int attackChoice = input.nextInt();
                        if (attackChoice == 2) {
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
                        if (attackChoice == 1) {
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
                                submenu = input.nextInt();
                                if (submenu == 1) {
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
                        } else if (attackChoice == 3) {
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
                                    submenu = input.nextInt();
                                    if (submenu == 1) {
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
                case 2:
                    cont = false;
                    flee = chanceToFlee.nextInt(2);
                    if (flee == 1) {
                        System.out.println("Could not run away");
                    } else {
                        System.out.println("You ran away");
                        //move one square, seems to remove monster from map, or not enough steps to find it
                        break;
                    }
            }
        }
        return hero.getHp();
    }

    Scanner input = new Scanner(System.in);

    void printMap(int position){
        for(int p = 0; p<49; p++){
            if(p != position){
                System.out.print("[ ]");
            }else{
                System.out.print("[*]");
            }
            if(p==6 || p > 6 && p % 7 == 6){
                System.out.println("");
            }
        }
    }
    int move(int position, Heroes hero, Rooms[] roomlist, Keyboard keyboard){
        printMap(position);
        String direction = "0";
        int counter = 3;
        if (hero.getName().equals("Mage")){
            while (counter > hero.getMageCounter()){
                System.out.println("Do you want to use your special ability?");
                System.out.println("1. Yes \n2. No");
                int choice = input.nextInt();
                if (choice == 1) {
                    position = magespecial(position);
                    printMap(position);
                    int mage = hero.getMageCounter();
                    mage += 1;
                    hero.setMageCounter(mage);
                } else {
                    printMap(position);
                    break;
                }
            }
        }
        if(position == 0){
            System.out.println("Choose where to move \n" + keyboard.getRight() + ". Right\n" + keyboard.getDown() + ". Down");
            while(direction.equals("0")) {
                direction = input.nextLine();
                if (direction.equals(keyboard.getRight())) {
                    if(roomlist[position].getDoor1()!= null){
                        System.out.println((((Doors) roomlist[position].getDoor1()).getTypeOfDoor()) + " Door");
                    }
                    position += 1;
                } else if (direction.equals(keyboard.getDown())){
                    if(roomlist[position].getDoor2()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()) + " Door");
                    }
                    position += 7;
                } else {
                    System.out.println("Not a valid option");
                    direction = "0";
                }
            }
        }else if(position == 6){
            System.out.println("Choose where to move \n" + keyboard.getLeft() + ". Left\n" + keyboard.getDown() + ". Down");
            while(direction.equals("0")) {
                direction = input.nextLine();
                if (direction.equals(keyboard.getLeft())) {
                    if(roomlist[position].getDoor1()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor1()).getTypeOfDoor()) + " Door");
                    }
                    position -= 1;
                } else if (direction.equals(keyboard.getDown())) {
                    if(roomlist[position].getDoor2()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()) + " Door");
                    }
                    position += 7;
                } else {
                    System.out.println("Not a valid option");
                    direction = "0";
                }
            }
        }else if(position == 42){
            System.out.println("Choose where to move \n" + keyboard.getRight() + ". Right\n" + keyboard.getUp() + ". Up");
            while(direction.equals("0")) {
                direction = input.nextLine();
                if (direction.equals(keyboard.getRight())) {
                    if(roomlist[position].getDoor1()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor1()).getTypeOfDoor()) + " Door");
                    }
                    position += 1;
                }else if (direction.equals(keyboard.getUp())) {
                    if(roomlist[position].getDoor2()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()) + " Door");
                    }
                    position -= 7;
                } else {
                    System.out.println("Not a valid option");
                    direction = "0";
                }
            }
        }else if(position == 48){
            System.out.println("Choose where to move \n" + keyboard.getLeft() + ". Left\n" + keyboard.getUp() + ". Up");
            while(direction.equals("0")) {
                direction = input.nextLine();
                if (direction.equals(keyboard.getLeft())) {
                    if(roomlist[position].getDoor1()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor1()).getTypeOfDoor()) + " Door");
                    }
                    position -= 1;
                } else if (direction.equals(keyboard.getUp())){
                    if(roomlist[position].getDoor2()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()) + " Door");
                    }
                    position -= 7;
                } else {
                    System.out.println("Not a valid option");
                    direction = "0";
                }
            }
        }else if(position > 0 && position < 42 && position%7 == 0){
            System.out.println("Choose where to move \n" + keyboard.getRight() + ". Right\n" + keyboard.getDown() + ". Down\n" + keyboard.getUp() + ". Up");
            while(direction.equals("0")) {
                direction = input.nextLine();
                if (direction.equals(keyboard.getRight())) {
                    if(roomlist[position].getDoor1()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor1()).getTypeOfDoor()) + " Door");
                    }
                    position += 1;
                }else if (direction.equals(keyboard.getUp())) {
                    if(roomlist[position].getDoor2()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()) + " Door");
                    }
                    position -= 7;
                }else if (direction.equals(keyboard.getDown())){
                    if(roomlist[position].getDoor3()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor3()).getTypeOfDoor()) + " Door");
                    }
                    position+=7;
                }else{
                    System.out.println("Not a valid option");
                    direction = "0";
                }
            }
        }else if(position > 0 && position < 6 ){
            System.out.println("Choose where to move \n" + keyboard.getLeft() + ". Left\n" + keyboard.getRight() + ". Right\n" + keyboard.getDown() + ". Down");
            while(direction.equals("0")) {
                direction = input.nextLine();
                if (direction.equals(keyboard.getRight())) {
                    if(roomlist[position].getDoor1()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor1()).getTypeOfDoor()) + " Door");
                    }
                    position += 1;
                } else if (direction.equals(keyboard.getLeft())) {
                    if(roomlist[position].getDoor2()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()) + " Door");
                    }
                    position -= 1;
                }else if(direction.equals(keyboard.getDown())){
                    if(roomlist[position].getDoor3()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor3()).getTypeOfDoor()) + " Door");
                    }
                    position +=7;
                }else {
                    System.out.println("Not a valid option");
                    direction = "0";
                }
            }
        }else if(position > 42 && position < 48){
            System.out.println("Choose where to move \n" + keyboard.getLeft() + ". Left\n" + keyboard.getRight() + ". Right\n" + keyboard.getUp() + ". Up");
            while(direction.equals("0")) {
                direction = input.nextLine();
                if (direction.equals(keyboard.getRight())) {
                    if(roomlist[position].getDoor1()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor1()).getTypeOfDoor()) + " Door");
                    }
                    position += 1;
                } else if (direction.equals(keyboard.getLeft())) {
                    if(roomlist[position].getDoor2()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()) + " Door");
                    }
                    position -= 1;
                }else if(direction.equals(keyboard.getUp())){
                    if(roomlist[position].getDoor3()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor3()).getTypeOfDoor()) + " Door");
                    }
                    position -=7;
                }else {
                    System.out.println("Not a valid option");
                    direction = "0";
                }
            }
        }else if(position < 48 && position > 6 && position % 7 == 6){
            System.out.println("Choose where to move \n" + keyboard.getLeft() + ". Left\n" + keyboard.getDown() + ". Down\n" + keyboard.getUp() + ". Up");
            while(direction.equals("0")) {
                direction = input.nextLine();
                if (direction.equals(keyboard.getLeft())) {
                    if(roomlist[position].getDoor1()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor1()).getTypeOfDoor()) + " Door");
                    }
                    position -= 1;
                } else if (direction.equals(keyboard.getUp())) {
                    if(roomlist[position].getDoor2()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()) + " Door");
                    }
                    position -= 7;
                }else if(direction.equals(keyboard.getDown())){
                    if(roomlist[position].getDoor3()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor3()).getTypeOfDoor()) + " Door");
                    }
                    position +=7;
                }else {
                    System.out.println("Not a valid option");
                    direction = "0";
                }
            }
        }else{
            System.out.println("Choose where to move \n" + keyboard.getLeft() + ". Left\n" + keyboard.getRight() + ". Right\n" + keyboard.getDown() + ". Down\n" + keyboard.getUp() + ". Up");
            while(direction.equals("0")) {
                direction = input.nextLine();
                if (direction.equals(keyboard.getRight())) {
                    if(roomlist[position].getDoor1()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor1()).getTypeOfDoor()) + " Door");
                    }
                    position += 1;
                } else if (direction.equals(keyboard.getLeft())) {
                    if(roomlist[position].getDoor2()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()) + " Door");
                    }
                    position -= 1;
                }else if(direction.equals(keyboard.getUp())){
                    if(roomlist[position].getDoor3()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor3()).getTypeOfDoor()) + " Door");
                    }
                    position -=7;
                }else if(direction.equals(keyboard.getDown())){
                    if(roomlist[position].getDoor4()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor4()).getTypeOfDoor()) + " Door");
                    }
                    position+=7;
                }else {
                    System.out.println("Not a valid option");
                    direction = "0";
                }
            }
        }
        return position;
    }
    Rooms[] createRooms(Monster monster1, Monster monster2,Monster monster3, Treasure chest){
        Rooms[] roomlist = new Rooms[49];
        Random rand = new Random();
        int monsterroom1 = rand.nextInt(49);
        int monsterroom2 = rand.nextInt(49);
        int monsterroom3 = rand.nextInt(49);
        while (monsterroom1 == monsterroom2 || monsterroom1 == monsterroom3 || monsterroom2 == monsterroom3){
            monsterroom2 = rand.nextInt(49);
            monsterroom3 = rand.nextInt(49);
        }
        int chestroom = rand.nextInt(49);
        while(monsterroom1 == chestroom) {
            chestroom = rand.nextInt(49);
        }
        for(int p = 0; p < 49; p++){
            Rooms room1;
            if(p == 0 || p== 6 || p == 42 ||p == 48){
                int choice = rand.nextInt(2);
                if(choice == 0){
                    room1 = new Rooms(createDoor(), null, null, null, null, null);
                }else{
                    room1 = new Rooms(null, createDoor(), null, null, null, null);
                }

            }else if(p < 48 && p > 6 && p % 7 == 6 || p > 42 && p < 48 || p > 0 && p < 6 || p > 0 && p < 42 && p%7 == 0){
                int choice = rand.nextInt(3);
                if(choice == 0){
                    room1 = new Rooms(createDoor(), createDoor(), null, null, null, null);
                }else if(choice == 1){
                    room1 = new Rooms(null, createDoor(), createDoor(), null, null, null);
                }else{
                    room1 = new Rooms(createDoor(), null, createDoor(), null, null, null);
                }
            }else{
                int choice = rand.nextInt(4);
                if(choice == 0){
                    room1 = new Rooms(createDoor(), createDoor(), createDoor(), null, null, null);
                }else if(choice == 1){
                    room1 = new Rooms(null, createDoor(), createDoor(), createDoor(), null, null);
                }else if (choice ==2){
                    room1 = new Rooms(createDoor(), null, createDoor(), createDoor(), null, null);
                }else{
                    room1 = new Rooms(createDoor(), createDoor(), null, createDoor(), null, null);
                }
            }
            roomlist[p] = room1;
        }
        roomlist[monsterroom1].setMonster(monster1);
        roomlist[monsterroom2].setMonster(monster2);
        roomlist[monsterroom3].setMonster(monster3);
        roomlist[chestroom].setTreasure(chest);
        return roomlist;
    }

    public Item createItem(){
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
    public Keys createKey(){
            Keys key = new Keys("Rusty old key",1);
            return key;
    }

    ArrayList openTreasure(Rooms[] list , int position,Treasure chest, ArrayList inventory, Score score){
        System.out.println("You found a chest!");
        System.out.println("Press 1 to open\nPress 2 to ignore chest and permanently remove it. ");
        int choice = input.nextInt();
        if (choice == 1) {
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
    public int magespecial(int position) {
        System.out.println("You can move to: ");
        if (position >= 2 && position <= 46) {
            System.out.print( "1. 1 left\n2. 2 left\n3. 1 right \n4. 2 right\n");
            System.out.print("> ");
            int choice = input.nextInt();
            switch (choice){
                case 1:
                    position -= 1;
                    break;
                case 2:
                    position -= 2;
                    break;
                case 3:
                    position += 1;
                    break;
                case 4:
                    position += 2;
                    break;
                default:
                    break;
            }
            System.out.println("You moved to room: " + position);

        } else if (position == 0){
            System.out.print( "1. 1 right \n2. 2 right\n");
            System.out.print("> ");
            int choice = input.nextInt();
            switch (choice){
                case 1:
                    position += 1;
                    break;
                case 2:
                    position += 2;
                    break;
                default:
                    break;
            }
            System.out.println("You moved to room: " + position);
        }else if (position == 1){
            System.out.print( "1. 1 left\n2. 1 right\n3. 2 right\n");
            System.out.print("> ");
            int choice = input.nextInt();
            switch (choice){
                case 1:
                    position -= 1;
                    break;
                case 2:
                    position += 1;
                    break;
                case 3:
                    position += 2;
                    break;
                default:
                    break;
            }
            System.out.println("You moved to room: " + position);
        } else if (position == 47){
            System.out.print( "1. 1 right\n2. 1 left \n3. 2 left\n");
            System.out.print("> ");
            int choice = input.nextInt();
            switch (choice){
                case 1:
                    position +=1;
                    break;
                case 2:
                    position -= 1;
                    break;
                case 3:
                    position -= 2;
                    break;
                default:
                    break;
            }
            System.out.println("You moved to room: " + position);

        } else if (position == 48){
            System.out.print( "1. 1 left\n2. 2 left\n");
            System.out.print("> ");
            int choice = input.nextInt();
            switch (choice){
                case 1:
                    position -=1;
                    break;
                case 2:
                    position -=2;
                    break;
                default:
                    break;
            }
            System.out.println("You moved to room: " + position);

        }
        return position;
    }
    Doors createDoor(){
        String[] doortypes = new String[]{"Wind", "Fire", "Ocean", "Dirt"};
        Random rand = new Random();
        int number = rand.nextInt(4);
        Doors thisdoor = new Doors(doortypes[number]);
        return thisdoor;
    }
    public void subMenu(Keyboard keyboard){
        boolean cont = true;
        while (cont) {
            System.out.println("--- Sub Menu ---");
            System.out.println("1. Show Instructions(Don't Work)\n" + "2. Load Game(Don't Work)\n"
                    + "3. Save Game (Don't Work)\n" + "4. Show keyboard commands\n" + "5. Change keyboard commands\n"
                    + "6. Back to game\n" + "7. Quit Game");
            int choice = input.nextInt();
            if (choice == 1) {
            } else if (choice == 2) {

            } else if (choice == 3) {

            } else if (choice == 4) {
                System.out.println("Left : " + keyboard.getLeft());
                System.out.println("Right : " + keyboard.getRight());
                System.out.println("Down : " + keyboard.getDown());
                System.out.println("Up : " + keyboard.getUp());
                System.out.println("Press '1' to go back to sub menu");
                input.nextInt();
            } else if (choice == 5) {
                System.out.print("Change Left from (" + keyboard.getLeft() + ") to : ");
                input.nextLine();
                String left = input.nextLine();
                keyboard.setLeft(left);
                System.out.print("\nChange Right from (" + keyboard.getRight() + ") to : ");
                String right = input.nextLine();
                keyboard.setRight(right);
                System.out.print("\nChange Down from (" + keyboard.getDown() + ") to : ");
                String down = input.nextLine();
                keyboard.setDown(down);
                System.out.print("\nChange Up from (" + keyboard.getUp() + ") to : ");
                String up = input.nextLine();
                keyboard.setUp(up);
                System.out.print("\nPress '1' to go back to Sub Menu ");
                input.nextInt();
            } else if (choice == 6) {
                break;
            } else if (choice == 7) {
                System.out.println("Are you sure you want to quit?\n1. Yes\n2. No");
                int optionChoice = input.nextInt();
                if (optionChoice == 1){
                    System.out.println("Goodbye");
                    System.exit(0);
                } else {
                    cont = true;
                }
            }
            else {
                System.out.println("Invalid number, try again!");
            }
        }
    }

}

