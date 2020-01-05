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
        int firekeys = 5;
        int oceankeys = 6;
        int dirtkeys = 5;
        int windkeys = 5;
        Doors.createDoor();
        int mainMenu = 0;
        Scanner input = new Scanner(System.in);
        Heroes hero = new Heroes(1, 1, "");
        Monster minotaur = new Monster(50,10, "Minotaur");
        Monster typhone = new Monster(60,30, "Typhone");
        Monster wampa = new Monster(100,20, "Wampa");
        Score score = new Score();
        Treasure chest1 = new Treasure(Item.createItem(),Item.createItem(), Item.createItem());
        Treasure chest2 = new Treasure(Item.createItem(),Item.createItem(), Item.createItem());
        Treasure chest3 = new Treasure(Item.createItem(),Item.createItem(), Item.createItem());
        Keyboard keyboard = new Keyboard("2","1", "3", "4", "9");
        int choice;
        int position = 0;
        int round = 1;
        int highscore = 0;

        Rooms[] rooms = Rooms.createRooms(minotaur, typhone, wampa, chest1, chest2, chest3);

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
            System.out.println("1. Start new game\n2. Load game(not implemented)\n3. Quit\n4. Scoreboard\n5. Game Instructions");
            mainMenu = input.nextInt();
            if(mainMenu==3){
                System.exit(0);}
            else if(mainMenu==2){
            }
            else if (mainMenu == 4) {
                System.out.println("Current High Score is: " + highscore);
                System.out.println("Press '1' to go back to Main Menu");
                input.nextInt();
            }
            else if (mainMenu == 5) {
                int decision;

                System.out.println("1) What is this game about?");
                System.out.println("2) How do I play\n");
                System.out.println("What would you like to know?");
                decision = input.nextInt();

                if (decision == 1) {

                    System.out.println("[Press Enter to continue]\n");
                    System.out.println("Welcome to the greatest text-based game of 2020!\n\nThe goal of this game is to collect as many coins\nas possible and slay all foes that may appear.");
                    String catchEnter = input.nextLine();
                    String catchEnterz = input.nextLine();
                    System.out.println("There are 49 rooms to explore and\nyou have 49 turns to explore them.");
                    catchEnter = input.nextLine();
                    System.out.println("Pick a hero that suits you to take on this adventure\nand use your special attacks when needed.");
                    catchEnter = input.nextLine();
                    System.out.println("If you are lucky you might find a\ntreasure chest full of goodies and a key.");
                    catchEnter = input.nextLine();
                    System.out.println("but what is it for?");
                    catchEnter = input.nextLine();
                    System.out.print("  ____\n" +
                            " /  ..\\\n" +
                            "|    o | boo\n" +
                            "| v   v|  \n" +
                            "|/\\/\\/\\|\n\n");
                }else {
                    System.out.println("[Press Enter to continue]\n");
                    System.out.println("To play this awesome game there are some things you need to know..");
                    String catchEnter = input.nextLine();
                    catchEnter = input.nextLine();
                    System.out.println("This is a text-based game, if you have not yet realised.");
                    catchEnter = input.nextLine();
                    System.out.println("So to manoeuvre around you will not use anything other than your keyboard.");
                    catchEnter = input.nextLine();
                    System.out.println("A map of possible rooms to explore will appear once the game has started.");
                    catchEnter = input.nextLine();
                    System.out.println("A star '*' resembles your position.");
                    catchEnter = input.nextLine();
                    System.out.println("To move around, your will use either 1, 2, 3 or 4.");
                    catchEnter = input.nextLine();
                    System.out.println("These will also be the controls that you use in combat and when using items that you collect on your way");
                    catchEnter = input.nextLine();
                    System.out.println("And this is all you need to know to play this amazing game!\n");
                }
            }
            else {
                cont = false;
            }
        }

        do {
            System.out.println("***************************");
            System.out.println("*  Welcome to WoW Borgen  *");
            System.out.println("*                         *");
            System.out.println("*    Choose your hero!    *\n" +
                    "*    1. Rogue             *\n" +
                    "*    2. Warrior           *\n" +
                    "*    3. Mage              *");
            System.out.println("***************************");
            System.out.print("> " );

            choice = input.nextInt();

        } while (choice != 1 && choice != 2 && choice != 3);
        hero.choiceHero(choice);

        while(round <= 49){
            System.out.println("Round: " + round);
            System.out.println("Score: " + score.getScore());
            if(rooms[position].getMonster()!= null){
                if ( rooms[position].getMonster() == minotaur) {
                    int positionPrev = position;
                    int positionCombat = position;
                    position=Combat.combatMethod(hero, minotaur, playerInventory, score, keyboard, position);
                    if (positionPrev == position +7 || positionPrev == position -7){
                        round-=1;
                    }
                    if(positionCombat == position && minotaur.getHp() <=0) {
                        rooms[position].setMonster(null);
                    }
                }
                else if (rooms[position].getMonster() == typhone) {
                    int positionPrev = position;
                    int positionCombat = position;

                    position=Combat.combatMethod(hero, typhone, playerInventory, score, keyboard, position);
                    if (positionPrev == position + 7 || positionPrev == position - 7){
                        round-=1;
                    }
                    if(positionCombat == position && typhone.getHp() <=0) {
                        rooms[position].setMonster(null);
                    }
                } else {
                    int positionPrev = position;
                    int positionCombat = position;

                    position=Combat.combatMethod(hero, wampa, playerInventory, score, keyboard, position);
                    if (positionPrev == position +7 || positionPrev == position - 7){
                        round-=1;
                    }
                    if(positionCombat == position && wampa.getHp() <=0) {
                        rooms[position].setMonster(null);
                    }
                }

            }else if(rooms[position].getTreasure()!= null){
                playerInventory = Treasure.openTreasure(rooms,position,rooms[position].getTreasure(), playerInventory, score, firekeys, oceankeys, dirtkeys, windkeys);
            }else {
                position=Map.move(position, hero, rooms, keyboard, firekeys, oceankeys, dirtkeys, windkeys);
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
}




