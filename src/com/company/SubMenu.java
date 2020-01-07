package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SubMenu {
    public static void subMenu(Keyboard keyboard, int position, Heroes hero, ArrayList<Item> playerInventory, Monster wampa, Monster typhone, Monster minotaur, Score score, int round){

        Scanner input = new Scanner(System.in);
        boolean cont = true;
        while (cont) {
            System.out.println("--- Sub Menu ---");
            System.out.println("1. Show Instructions\n"
                    + "2. Save Game\n" + "3. Show keyboard commands\n" + "4. Change keyboard commands\n"
                    + "5. Back to game\n" + "6. Quit Game");
            String choice = input.nextLine();
            if (choice.equals("1")) {
                Info.info();
            } else if (choice.equals("2")) {
                try {
                PrintWriter pwHero = new PrintWriter("hero.txt");
                pwHero.println(hero.getName());
                pwHero.println(hero.getDamage());
                pwHero.println(hero.getHp());
                pwHero.println(hero.getRogueMana());
                pwHero.println(hero.getMageCounter());
                pwHero.println(position);
                pwHero.println(score.getScore());
                pwHero.println(round);
                pwHero.close();

                FileOutputStream fos = new FileOutputStream("inventory.txt");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(playerInventory);
                oos.close();

                PrintWriter pwMonster = new PrintWriter("Monster.txt");
                pwMonster.println(wampa.getHp());
                pwMonster.println(wampaPos);
                pwMonster.println(typhone.getHp());
                pwMonster.println(typhonePos);
                pwMonster.println(minotaur.getHp());
                pwMonster.println(minotaurPos);
                pwMonster.close();

                }catch (IOException e) {
                    e.printStackTrace();
                }

            } else if (choice.equals("3")) {
                System.out.println("Left   : " + keyboard.getLeft());
                System.out.println("Right  : " + keyboard.getRight());
                System.out.println("Down   : " + keyboard.getDown());
                System.out.println("Up     : " + keyboard.getUp());
                System.out.println("Option : " + keyboard.getOption());
                System.out.println("Press '1' to go back to sub menu");
                input.nextLine();
            } else if (choice.equals("4")) {
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
            } else if (choice.equals("5")) {
                break;
            } else if (choice.equals("6")) {
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
