package com.company;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class SubMenu {
    public static void subMenu(Keyboard keyboard, int position, Heroes hero, ArrayList<Item> playerInventory, Creature wampa, Creature typhone, Creature minotaur, Score score){

        Scanner input = new Scanner(System.in);
        boolean cont = true;
        while (cont) {
            System.out.println("--- Sub Menu ---");
            System.out.println("1. Show Instructions\n" + "2. Load Game(Don't Work)\n"
                    + "3. Save Game (Don't Work)\n" + "4. Show keyboard commands\n" + "5. Change keyboard commands\n"
                    + "6. Back to game\n" + "7. Quit Game");
            String choice = input.nextLine();
            if (choice.equals("1")) {
                Info.info();
            } else if (choice.equals("2")) {

            } else if (choice.equals("3")) {
                try {
                File posFile = new File("position.txt");
                File heroFile = new File("hero.txt");
                File inventoryFile = new File("inventory.txt");
                File wampaFile = new File("wampa.txt");
                File typhoneFile = new File("typhone.txt");
                File minotaurFile = new File("minotaur.txt");
                File scoreFile = new File("score.txt");

                if (!posFile.exists()){
                        posFile.createNewFile();
                    }
                if (!heroFile.exists()){
                    heroFile.createNewFile();
                }
                if (!inventoryFile.exists()){
                    inventoryFile.createNewFile();
                }
                if (!wampaFile.exists()){
                    wampaFile.createNewFile();
                }
                if (!typhoneFile.exists()){
                    typhoneFile.createNewFile();
                }
                if (!minotaurFile.exists()){
                    minotaurFile.createNewFile();
                }
                PrintWriter pwPos = new PrintWriter(posFile);
                pwPos.println(position);
                pwPos.close();

                PrintWriter pwHero = new PrintWriter(heroFile);
                pwHero.println(hero.getName());                    //kanske separera
                pwHero.println(hero.getHp());
                pwHero.println(hero.getRogueMana());
                pwHero.close();

                PrintWriter pwInventory = new PrintWriter(inventoryFile);
                    for (int i = 0; i < playerInventory.size() ; i++) {
                        pwInventory.println(i);
                    }
                    pwInventory.close();

                    PrintWriter pwWampa = new PrintWriter(wampaFile);
                    pwWampa.println(wampa.getHp());
                    pwWampa.close();

                    PrintWriter pwTyphone = new PrintWriter(typhoneFile);
                    pwTyphone.println(typhone.getHp());
                    pwTyphone.close();

                    PrintWriter pwMinotaur = new PrintWriter(minotaurFile);
                    pwMinotaur.println(minotaur.getHp());
                    pwMinotaur.close();

                    PrintWriter pwScore = new PrintWriter(scoreFile);
                    pwScore.println(score.getScore());
                    pwScore.close();

                }catch (IOException e) {
                    e.printStackTrace();
                }

            } else if (choice.equals("4")) {
                System.out.println("Left   : " + keyboard.getLeft());
                System.out.println("Right  : " + keyboard.getRight());
                System.out.println("Down   : " + keyboard.getDown());
                System.out.println("Up     : " + keyboard.getUp());
                System.out.println("Option : " + keyboard.getOption());
                System.out.println("Press '1' to go back to sub menu");
                input.nextLine();
            } else if (choice.equals("5")) {
                System.out.print("Change Left from (" + keyboard.getLeft() + ") to : ");
                keyboard.setLeft(input.nextLine());
                System.out.print("\nChange Right from (" + keyboard.getRight() + ") to : ");
                keyboard.setRight(input.nextLine());
                System.out.print("\nChange Down from (" + keyboard.getDown() + ") to : ");
                keyboard.setDown(input.nextLine());
                System.out.print("\nChange Up from (" + keyboard.getUp() + ") to : ");
                keyboard.setUp(input.nextLine());
                System.out.print("\nChange Option from (" + keyboard.getOption() + ") to : ");
                keyboard.setOption(input.nextLine());
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
