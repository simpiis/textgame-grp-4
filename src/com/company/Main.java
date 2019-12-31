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
        Treasure chest2 = new Treasure(runApps.createItem(),runApps.createItem(), runApps.createKey());
        Treasure chest3 = new Treasure(runApps.createItem(),runApps.createItem(), runApps.createKey());
        Keyboard keyboard = new Keyboard("2","1", "3", "4");
        int choice;
        int position = 0;
        int round = 1;
        int highscore = 0;
        Rooms[] rooms = Rooms.createRooms(minotaur, typhone, wampa, chest1, chest2, chest3);
        //runApps.createRooms(minotaur, typhone, wampa, chest1);
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
            System.out.println("*  Welcome to WoW Borgen  *");
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
                    int positionPrev = position;
                    int positionCombat = position;
                    if (positionPrev == position +7 || positionPrev == position -7){
                        round-=1;
                    }
                    position=Combat.combatMethod(hero, minotaur, playerInventory, score, keyboard, position);
                    if(positionCombat == position && minotaur.getHp() <=0) {
                        rooms[position].setMonster(null);
                    }
                }
                else if (rooms[position].getMonster() == typhone) {
                    int positionPrev = position;
                    int positionCombat = position;
                    if (positionPrev == position + 7 || positionPrev == position - 7){
                        round-=1;
                    }
                    position=Combat.combatMethod(hero, typhone, playerInventory, score, keyboard, position);
                    if(positionCombat == position && typhone.getHp() <=0) {
                        rooms[position].setMonster(null);
                    }
                } else {
                    int positionPrev = position;
                    int positionCombat = position;
                    if (positionPrev == position +7 || positionPrev == position - 7){
                        round-=1;
                    }
                    position=Combat.combatMethod(hero, wampa, playerInventory, score, keyboard, position);
                    if(positionCombat == position && wampa.getHp() <=0) {
                        rooms[position].setMonster(null);
                    }
                }

            }else if(rooms[position].getTreasure()!= null){
                playerInventory=runApps.openTreasure(rooms,position,rooms[position].getTreasure(), playerInventory, score);
            }else {
                position=Map.move(position, hero, rooms, keyboard);
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


    Scanner input = new Scanner(System.in);


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
            String choice = input.nextLine();
            if (choice.equals("1")) {

            } else if (choice.equals("2")) {

            } else if (choice.equals("3")) {

            } else if (choice.equals("4")) {
                System.out.println("Left : " + keyboard.getLeft());
                System.out.println("Right : " + keyboard.getRight());
                System.out.println("Down : " + keyboard.getDown());
                System.out.println("Up : " + keyboard.getUp());
                System.out.println("Press '1' to go back to sub menu");
                input.nextLine();
            } else if (choice.equals("5")) {
                System.out.print("Change Left from (" + keyboard.getLeft() + ") to : ");
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
                input.nextLine();
            } else if (choice.equals("6")) {
                break;
            } else if (choice.equals("7")) {
                System.out.println("Are you sure you want to quit?\n1. Yes\n2. No");
                String optionChoice = input.nextLine();
                if (optionChoice.equals("1")){
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

