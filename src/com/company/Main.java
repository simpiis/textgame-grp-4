package com.company;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Heroes rogue = new Heroes(110, 10);
        Heroes warrior = new Heroes(90, 30);
        Heroes mage = new Heroes(100, 20);
        int choice;

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
                rogue.printRogue();
                playerdmg=rogue.getDamage();
                playerhp=rogue.getHp();
                System.out.println("*************************");
                break;
            case 2:
                System.out.println("*************************");
                warrior.printWarrior();
                playerdmg=warrior.getDamage();
                playerhp=warrior.getHp();
                System.out.println("*************************");
                break;
            case 3:
                System.out.println("*************************");
                mage.printMage();
                playerdmg=mage.getDamage();
                playerhp=mage.getHp();
                System.out.println("*************************");
                break;
        }
        //testvariabler
        int monsterlocation = 1;
        int playerlocation = 1;

        if (playerlocation == monsterlocation) {
            playerhp= combatMethod(playerdmg, playerhp);
        }
    }
    public static int combatMethod(int playerdmg, int playerhp) {
        Scanner input = new Scanner(System.in);
        Monster monotaur = new Monster(20,10, "Monotaur");
        String monsterType = monotaur.getName();
        int monsterhp = monotaur.getHp();
        int monsterdmg = monotaur.getDamage();

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
                        if (monsterhp > playerdmg) {
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
}

