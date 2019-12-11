package com.company;

import java.security.SecureRandom;
import java.util.*;

import java.lang.*;

public class Main {
    public static void main(String[] args) {
        Main runApps = new Main();
        Scanner input = new Scanner(System.in);
        Heroes rogue = new Heroes(110, 10, "Rogue");
        Heroes warrior = new Heroes(90, 30, "Warrior");
        Heroes mage = new Heroes(100, 20, "Mage");
        Monster minotaur = new Monster(50,10, "Minotaur");
        Treasure chest1 = new Treasure(runApps.createItem(),runApps.createItem(), runApps.createKey(), 5 );
        int choice;
        int position = 0;
        int round = 1;
        Rooms[] rooms = runApps.createRooms(minotaur,chest1);
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

        int playerhp = 0;
        int playerdmg = 0;
        String name = "";

        switch (choice) {
            case 1:
                System.out.println("*************************");
                rogue.printHero();
                playerdmg=rogue.getDamage();
                playerhp=rogue.getHp();
                name=rogue.getName();
                System.out.println("*************************");
                break;
            case 2:
                System.out.println("*************************");
                warrior.printHero();
                playerdmg=warrior.getDamage();
                playerhp=warrior.getHp();
                name=warrior.getName();
                System.out.println("*************************");
                break;
            case 3:
                System.out.println("*************************");
                mage.printHero();
                playerdmg=mage.getDamage();
                playerhp=mage.getHp();
                name=mage.getName();
                System.out.println("*************************");
                break;
        }

        while(round <= 49){
            System.out.println("Round: " + round);
            if(rooms[position].getMonster()!= null){
                playerhp = combatMethod(playerdmg, playerhp, minotaur, name);
                rooms[position].setMonster(null);
            }else if(rooms[position].getTreasure()!= null){
                runApps.openTreasure(rooms,position,rooms[position].getTreasure());
            }else {
                position = runApps.move(position, name);
            }
            round++;
        }
    }
    public static int combatMethod(int playerdmg, int playerhp, Monster monster, String name) {
        SecureRandom chanceToFlee = new SecureRandom();
        int flee = 0;
        Scanner input = new Scanner(System.in);
        Monster minotaur = monster;
        String monsterType = minotaur.getName();
        int monsterhp = minotaur.getHp();
        int monsterdmg = minotaur.getDamage();

        System.out.println("You have encountered a " + monsterType + ", it has " + monsterhp + " health and " + monsterdmg + " damage!");
        System.out.println("--- Combat Menu ---");
        System.out.printf("%s%n%s%n%s", "1) Fight", "2) Flee (not fully implemented)","> ");
        int combatChoice = input.nextInt();

        switch (combatChoice) {
            case 1:
                while (monsterhp > 0 && playerhp > 0) {
                    System.out.println("Which attack would you like to use?");
                    System.out.printf("%s%n%s%n%s", "1) Melee", "2) Special ability (not implemented)","> ");
                    int attackChoice = input.nextInt();

                    if (attackChoice == 1) {
                        monsterhp -= playerdmg;
                        System.out.println("\nYou deal " + playerdmg + " damage to the " + monsterType);
                        if (monsterhp > 0) {
                            System.out.println("The " + monsterType + " has " + monsterhp + " health left");
                        }
                        if(monsterhp <= 0){
                            System.out.println(monsterType + " has died");
                            break;
                        }
                        System.out.println("The " + monsterType + " attacks for " + monsterdmg);
                        playerhp -= monsterdmg;
                        if (playerhp > monsterdmg) {
                            System.out.println("You have " + playerhp + " health left \n");
                        }
                        if (playerhp <= 0) {
                            System.out.println("The " + monsterType + " killed you. GAME OVER!");
                            System.exit(0);
                            break;
                        }
                    } else {
                        if (name.equals("Rogue")){
                            System.out.println("The bomb does 50 damage!");
                            int rougeSpecial = 50;
                            monsterhp -= rougeSpecial;
                            if (monsterhp > 0) {
                                System.out.println("The " + monsterType + " has " + monsterhp + " health left");
                            }
                            if(monsterhp <= 0){
                                System.out.println(monsterType + " has died");
                                break;
                            }
                        } else if (name.equals("Warrior")){
                            System.out.println("A Healing cure that heals you with 50hp");
                            int warriorSpecial = 50;
                            playerhp += warriorSpecial;
                            System.out.println("You have now " + playerhp + "hp");
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
        return playerhp;
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

    int move(int position, String name){
        printMap(position);
        int direction = 0;
        if (name.equals("Mage")){
            int counter = 0;
            for (int i = 3; i>counter; counter++){
                System.out.println("Do you want to use your special ability?");
                System.out.println("1.Yes \n2.No");
                int choise = input.nextInt();
                if (choise == 1) {
                    position = mageSpeciell(position);
                    printMap(position);
                } else {
                    break;
                }
            }
        }
        if(position == 0){
            System.out.println("Choose where to move \n 1. Right\n 2. Down");
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
            System.out.println("Choose where to move \n 1. Left\n 2. Down");
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
            System.out.println("Choose where to move \n 1. Right\n 2. Up");
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
            System.out.println("Choose where to move \n 1. Left\n 2. Up");
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
    Rooms[] createRooms(Monster monster, Treasure chest){
        Rooms[] roomlist = new Rooms[49];
        Random rand = new Random();
        int monsterroom = rand.nextInt(49);
        int chestroom = rand.nextInt(49);
        while(monsterroom == chestroom) {
            chestroom = rand.nextInt(49);
        }
        for(int p = 0; p < 49; p++){
            Rooms room1;
            if (p == monsterroom) {
                room1 = new Rooms(null, null, null, null, null, monster);
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
    public int mageSpeciell(int position) {
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

