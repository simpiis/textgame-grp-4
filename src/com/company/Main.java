package com.company;

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
        int choice;
        int position = 0;
        int round = 1;
        Rooms[] rooms = runApps.createRooms(minotaur);

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

        switch (choice) {
            case 1:
                System.out.println("*************************");
                rogue.printHero();
                playerdmg=rogue.getDamage();
                playerhp=rogue.getHp();
                System.out.println("*************************");
                break;
            case 2:
                System.out.println("*************************");
                warrior.printHero();
                playerdmg=warrior.getDamage();
                playerhp=warrior.getHp();
                System.out.println("*************************");
                break;
            case 3:
                System.out.println("*************************");
                mage.printHero();
                playerdmg=mage.getDamage();
                playerhp=mage.getHp();
                System.out.println("*************************");
                break;
        }

        while(round < 49){
            System.out.println("Round: " + round);
            if(rooms[position].getMonster()!= null){
                playerhp= combatMethod(playerdmg, playerhp, minotaur);
                rooms[position].setMonster(null);
            }else {
                position = runApps.move(position);
            }
            round+=1;
        }
    }
    public static int combatMethod(int playerdmg, int playerhp, Monster monster) {
        Scanner input = new Scanner(System.in);
        Monster minotaur = monster;
        String monsterType = minotaur.getName();
        int monsterhp = minotaur.getHp();
        int monsterdmg = minotaur.getDamage();

        System.out.println("You have encountered a " + monsterType + ", it has " + monsterhp + " health and " + monsterdmg + " damage!");
        System.out.println("--- Combat Menu ---");
        System.out.printf("%s%n%s%n%s", "1) Fight", "2) Flee","> ");
        int combatChoice = input.nextInt();

        switch (combatChoice) {
            case 1:
                while (monsterhp > 0 && playerhp > 0) {
                    System.out.println("Which attack would you like to use?");
                    System.out.printf("%s%n%s%n%s", "1) Melee", "2) Special ability","> ");
                    int attackChoice = input.nextInt();

                    if (attackChoice == 1) {
                        monsterhp -= playerdmg;
                        System.out.println("You deal " + playerdmg + " damage to the " + monsterType);
                        if (monsterhp > 0) {
                            System.out.println( monsterType + " has " + monsterhp + " health left");
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
                            System.out.println("The monster killed you. GAME OVER!");
                            System.exit(0);
                            break;
                        }
                    } else {
                        //monster hp - special ability

                        if(monsterhp <= 0){
                            System.out.println("Monster has died");
                        }
                    }
                }
                break;
            case 2:
                System.out.println("You attempt to flee");
                // roll for chance to flee
                break;
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

    int move(int position){
        printMap(position);
        int direction = 0;
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
            System.out.println("Choose where to move \n 1. Right\n 2. up");
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
            System.out.println("Choose where to move \n 1. left\n 2. Up");
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
    Rooms[] createRooms(Monster monster){
        Rooms[] roomlist = new Rooms[49];
        Random rand = new Random();
        int monsterroom = rand.nextInt(49);
        for(int p = 0; p < 49; p++){
            Rooms room1;
            if (p == monsterroom) {
                room1 = new Rooms(null, null, null, null, null, monster);
            }else{
                room1 = new Rooms(null, null, null, null, null, null);
            }
            roomlist[p] = room1;
        }
        return roomlist;
    }
}

