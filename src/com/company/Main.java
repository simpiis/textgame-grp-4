package com.company;

import java.security.SecureRandom;
import java.util.*;

import java.lang.*;

public class Main {
    public static void main(String[] args) {
        Main runApps = new Main();
        Scanner input = new Scanner(System.in);
        Heroes hero = new Heroes(1, 1, "");
        Monster minotaur = new Monster(50,10, "Minotaur");
        Monster typhone = new Monster(60,30, "Typhone");
        Monster wampa = new Monster(100,20, "Wampa");
        Treasure chest1 = new Treasure(runApps.createItem(),runApps.createItem(), runApps.createKey(), 5 );
        int choice;
        int position = 0;
        int round = 1;
        Rooms[] rooms = runApps.createRooms(minotaur, typhone, wampa, chest1);
        ArrayList<String> playerInventory = new ArrayList<>();

        //System.out.println("--- Main Menu ---");
        //System.out.println("1. Start new game\n2. Load game\n3. Quit");
        //if(mainMenu==3){System.exit(0);}
        //else if(mainMenu==2){ LOAD GAME}

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
            if(rooms[position].getMonster()!= null){
                if ( rooms[position].getMonster() == minotaur) {
                    hero.setHp(combatMethod(hero, minotaur));
                    rooms[position].setMonster(null);
                }
                else if (rooms[position].getMonster() == typhone) {
                    hero.setHp(combatMethod(hero, typhone));
                    rooms[position].setMonster(null);
                } else {
                    hero.setHp(combatMethod(hero, wampa));
                    rooms[position].setMonster(null);
                }

            }else if(rooms[position].getTreasure()!= null){
                runApps.openTreasure(rooms,position,rooms[position].getTreasure());
            }else {
                position = runApps.move(position, hero);
            }
            round++;
        }
    }
    public static int combatMethod(Heroes hero, Monster monster) {
        int count = 0;
        int submenu = 0;
        SecureRandom chanceToFlee = new SecureRandom();
        int flee = 0;
        Scanner input = new Scanner(System.in);

        System.out.println("You have encountered a " + monster.getName() + ", it has " + monster.getHp() + " health and " + monster.getDamage() + " damage!");
        System.out.println("--- Combat Menu ---");
        System.out.printf("%s%n%s%n%s", "1) Fight", "2) Flee (not fully implemented)","> ");
        int combatChoice = input.nextInt();

        switch (combatChoice) {
            case 1:
                while (monster.getHp() > 0 && hero.getHp() > 0) {
                    System.out.println("Which attack would you like to use?");
                    System.out.println("1) Melee");
                    if ( hero.getName().equals("Rogue")){
                        System.out.println("2) Special ability ");
                    }
                    if ( hero.getName().equals("Warrior") && count < 3){
                        System.out.println("2) Special ability (You can only use this ability 3 times, use it wisely");
                    }
                    System.out.println(">");
                    int attackChoice = input.nextInt();

                    if (attackChoice == 1) {
                        monster.setHp(monster.getHp()-hero.getDamage());
                        System.out.println("\nYou deal " + hero.getDamage() + " damage to the " + monster.getName());
                        if (monster.getHp() > 0) {
                            System.out.println("The " + monster.getName() + " has " + monster.getHp() + " health left");
                        }
                        if(monster.getHp() <= 0){
                            System.out.println(monster.getName() + " has died");
                            System.out.println("You have " + hero.getHp() + " health left.");
                            System.out.println("Do you want to use an item before leaving? \n1. Yes\n2. No");
                            submenu = input.nextInt();
                            if(submenu == 1) {
                                //open inventory
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
                    } else {
                        if (hero.getName().equals("Rogue")){
                            System.out.println("The bomb does 50 damage!");
                            int rougeSpecial = 50;
                            monster.setHp(monster.getHp()-rougeSpecial);
                            if (monster.getHp() > 0) {
                                System.out.println("The " + monster.getName() + " has " + monster.getHp() + " health left");
                            }
                            if(monster.getHp() <= 0){
                                System.out.println(monster.getName() + " has died");
                                System.out.println("Do you want to use an item before leaving? \n1. Yes\n2. No");
                                submenu = input.nextInt();
                                if(submenu == 1) {
                                    //open inventory
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
                        } else if (hero.getName().equals("Warrior")){
                            count += 1;
                            System.out.println("A Healing cure that heals you with 50hp (200hp is max)");
                            int warriorSpecial = 50;
                            if (hero.getHp() <= 150){
                                hero.setHp(hero.getHp() + warriorSpecial);
                            } else {
                                hero.setHp(200);
                            }
                            System.out.println("You have now " + hero.getHp() + "hp");

                            System.out.println("The " + monster.getName() + " has " + monster.getHp() + " health left");

                            System.out.println("The " + monster.getName() + " attacks for " + monster.getDamage());
                            hero.setHp(hero.getHp()-monster.getDamage());
                            if (hero.getHp() > monster.getDamage()) {
                                System.out.println("You have " + hero.getHp() + " health left \n");
                            }
                            if (hero.getHp() <= 0) {
                                System.out.println("The " + monster.getName() + " killed you. GAME OVER!");
                                System.exit(0);
                                break;
                            }
                        }
                    }
                }
                break;
            case 2:
                flee = chanceToFlee.nextInt(2);
                if (flee == 1){
                    System.out.println("Could not run away");
                }
                else{
                    System.out.println("You ran away");
                    //move one square, seems to remove monster from map, or not enough steps to find it
                break;}
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

    int move(int position, Heroes hero){
        printMap(position);
        int direction = 0;
        if (hero.getName().equals("Mage")){
            int counter = 0;
            for (int i = 3; i>counter; counter++){
                System.out.println("Do you want to use your special ability?");
                System.out.println("1.Yes \n2.No");
                int choice = input.nextInt();
                if (choice == 1) {
                    position = magespecial(position);
                    printMap(position);
                } else {
                    break;
                }
            }
        }
        if(position == 0){
            System.out.println("Choose where to move \n1. Right\n2. Down");
            while(direction == 0) {
                direction = input.nextInt();
                if (direction == 1) {
                    position += 1;
                } else if (direction == 2) {
                    position += 7;
                } else {
                    System.out.println("Not a valid option");
                    direction = 0;
                }
            }
        }else if(position == 6){
            System.out.println("Choose where to move \n1. Left\n2. Down");
            while(direction == 0) {
                direction = input.nextInt();
                if (direction == 1) {
                    position -= 1;
                } else if (direction == 2) {
                    position += 7;
                } else {
                    System.out.println("Not a valid option");
                    direction = 0;
                }
            }
        }else if(position == 42){
            System.out.println("Choose where to move \n1. Right\n2. Up");
            while(direction == 0) {
                direction = input.nextInt();
                if (direction == 1) {
                    position += 1;
                }else if (direction == 2) {
                    position -= 7;
                } else {
                    System.out.println("Not a valid option");
                    direction = 0;
                }
            }
        }else if(position == 48){
            System.out.println("Choose where to move \n1. Left\n2. Up");
            while(direction == 0) {
                direction = input.nextInt();
                if (direction == 1) {
                    position -= 1;
                } else if (direction == 2) {
                    position -= 7;
                } else {
                    System.out.println("Not a valid option");
                    direction = 0;
                }
            }
        }else if(position > 0 && position < 42 && position%7 == 0){
            System.out.println("Choose where to move \n1. Right\n2. Up\n3. Down");
            while(direction == 0) {
                direction = input.nextInt();
                if (direction == 1) {
                    position += 1;
                }else if (direction == 2) {
                    position -= 7;
                }else if (direction == 3){
                    position+=7;
                }else{
                    System.out.println("Not a valid option");
                    direction = 0;
                }
            }
        }else if(position > 0 && position < 6 ){
            System.out.println("Choose where to move \n1. Right\n2. Left\n3. Down");
            while(direction == 0) {
                direction = input.nextInt();
                if (direction == 1) {
                    position += 1;
                } else if (direction == 2) {
                    position -= 1;
                }else if(direction == 3){
                    position +=7;
                }else {
                    System.out.println("Not a valid option");
                    direction = 0;
                }
            }
        }else if(position > 42 && position < 48){
            System.out.println("Choose where to move \n1. Right\n2. Left\n3. Up");
            while(direction == 0) {
                direction = input.nextInt();
                if (direction == 1) {
                    position += 1;
                } else if (direction == 2) {
                    position -= 1;
                }else if(direction == 3){
                    position -=7;
                }else {
                    System.out.println("Not a valid option");
                    direction = 0;
                }
            }
        }else if(position < 48 && position > 6 && position % 7 == 6){
            System.out.println("Choose where to move \n1. Left\n2. Up\n3. Down");
            while(direction == 0) {
                direction = input.nextInt();
                if (direction == 1) {
                    position -= 1;
                } else if (direction == 2) {
                    position -= 7;
                }else if(direction == 3){
                    position +=7;
                }else {
                    System.out.println("Not a valid option");
                    direction = 0;
                }
            }
        }else{
            System.out.println("Choose where to move \n1. Right\n2. Left\n3. Up\n4. Down");
            while(direction == 0) {
                direction = input.nextInt();
                if (direction == 1) {
                    position += 1;
                } else if (direction == 2) {
                    position -= 1;
                }else if(direction == 3){
                    position -=7;
                }else if(direction == 4){
                    position+=7;
                }else {
                    System.out.println("Not a valid option");
                    direction = 0;
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
            if (p == monsterroom1) {
                room1 = new Rooms(null, null, null, null, null, monster1);
            }else if (p == monsterroom2){
                room1 = new Rooms(null, null, null, null, null, monster2);
            }else if (p == monsterroom3){
                room1 = new Rooms(null, null, null, null, null, monster3);
            }else if (p == chestroom){
                room1 = new Rooms(null, null, null, null, chest, null);
            }else{
                room1 = new Rooms(null, null, null, null, null, null);
            }
            roomlist[p] = room1;
        }
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
            Keys key = new Keys("rusty old key",1);
            return key;
    }

    void openTreasure(Rooms[] list , int position,Treasure chest){
        System.out.println("You found a chest!");
        System.out.println("Press 1 to open\nPress 2 to ignore chest and permanently remove it. ");
        int choice = input.nextInt();
        if (choice == 1) {
            System.out.println("First item is a " + (((Item)chest.getItem1()).getName()));
            System.out.println("Second item is a " + (((Item)chest.getItem2()).getName()));
            System.out.println("Third item is a " + (((Item)chest.getItem3()).getName()));
            System.out.println("You also found " + (chest.getCoins() + " gold"));
            list[position].setTreasure(null);
        }else{
            list[position].setTreasure(null);
        }
    }
    public int magespecial(int position) {
        System.out.println("You can move to: ");
        if (position >= 2 && position <= 46) {
            int position1 = position - 2;
            int position2 = position - 1;
            int position3 = position + 1;
            int position4 = position + 2;
            System.out.print( "1. " + position1 +"\n2. " + position2 +"\n3. " + position3 +"\n4. " + position4+ "\n");
            System.out.print("> ");
            int choice = input.nextInt();
            switch (choice){
                case 1:
                    position = position1;
                    break;
                case 2:
                    position = position2;
                    break;
                case 3:
                    position = position3;
                    break;
                case 4:
                    position = position4;
                    break;
                default:
                    break;
            }
            System.out.println("You moved to room: " + position);

        } else if (position == 0){
            int position1 = position + 1;
            int position2 = position + 2;
            System.out.print( "1. " + position1 +"\n2. " + position2 + "\n");
            System.out.print("> ");
            int choice = input.nextInt();
            switch (choice){
                case 1:
                    position = position1;
                    break;
                case 2:
                    position = position2;
                    break;
                default:
                    break;
            }
            System.out.println("You moved to room: " + position);
        }else if (position == 1){
            int position1 = position - 1;
            int position2 = position + 1;
            int position3 = position + 2;
            System.out.print( "1. " + position1 +"\n2. " + position2 +"\n3. " + position3+ "\n");
            System.out.print("> ");
            int choice = input.nextInt();
            switch (choice){
                case 1:
                    position = position1;
                    break;
                case 2:
                    position = position2;
                    break;
                case 3:
                    position = position3;
                    break;
                default:
                    break;
            }
            System.out.println("You moved to room: " + position);
        } else if (position == 47){
            int position1 = position + 1;
            int position2 = position - 1;
            int position3 = position - 2;
            System.out.print( "1. " + position1 +"\n2. " + position2 +"\n3. " + position3+ "\n");
            System.out.print("> ");
            int choice = input.nextInt();
            switch (choice){
                case 1:
                    position = position1;
                    break;
                case 2:
                    position = position2;
                    break;
                case 3:
                    position = position3;
                    break;
                default:
                    break;
            }
            System.out.println("You moved to room: " + position);

        } else if (position == 48){
            int position1 = position - 1;
            int position2 = position - 2;
            System.out.print( "1. " + position1 +"\n2. " + position2+ "\n");
            System.out.print("> ");
            int choice = input.nextInt();
            switch (choice){
                case 1:
                    position = position1;
                    break;
                case 2:
                    position = position2;
                    break;
                default:
                    break;
            }
            System.out.println("You moved to room: " + position);

        }
        return position;
    }
}   

