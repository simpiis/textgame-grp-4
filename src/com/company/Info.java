package com.company;

import java.util.Scanner;

public class Info {
    public static void info() {
        Scanner input = new Scanner(System.in);
        String decision;
        boolean cont = true;
        while (cont) {
            System.out.println("1) What is this game about?");
            System.out.println("2) How do I play");
            System.out.println("3) Back to Main Menu\n");
            System.out.println("What would you like to know?");
            decision = input.nextLine();

            if (decision.equals("1")) {
                System.out.println("[Press Enter to continue]\n");
                System.out.println("Welcome to the greatest text-based game of 2020!\n\nThe goal of this game is to collect as many coins\nas possible and slay all foes that may appear.");
                String catchEnter = input.nextLine();
                System.out.println("There are 49 rooms to explore and\nyou have 49 turns to explore them.");
                catchEnter = input.nextLine();
                System.out.println("Pick a hero that suits you to take on this adventure\nand use your special attacks when needed.");
                catchEnter = input.nextLine();
                System.out.println("If you are lucky you might find a\ntreasure chest full of goodies!");
                catchEnter = input.nextLine();
                System.out.println("but what is it for?");
                catchEnter = input.nextLine();
                System.out.print("  ____\n" +
                        " /  ..\\\n" +
                        "|    o | boo\n" +
                        "| v   v|  \n" +
                        "|/\\/\\/\\|\n\n");
            } else if (decision.equals("2")) {
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
                System.out.println("A hero can have a maximum of 200 health through healing with potions or the warrior special ability.");
                catchEnter = input.nextLine();
                System.out.println("The rogue can have a maximum of 75 mana through the usage of mana potions");
                catchEnter = input.nextLine();
                System.out.println("Fleeing has a 50% chance to succeed");
                catchEnter = input.nextLine();
                System.out.println("If you are on the uppermost row of the map fleeing will move you one square down.\nIn all other cases you will be moved one square up");
                catchEnter = input.nextLine();
                System.out.println("And this is all you need to know to play this amazing game!\n");
            } else if (decision.equals("3")) {
                cont = false;
            } else {
                System.out.println("Invalid number");
            }
        }
    }
}
