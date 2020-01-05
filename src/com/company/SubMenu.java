package com.company;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SubMenu {
    public static void subMenu(Keyboard keyboard, int position){

        Scanner input = new Scanner(System.in);
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
                try {
                File posFile = new File("position.txt");

                if (!posFile.exists()){
                        posFile.createNewFile();
                    }
                    PrintWriter pw = new PrintWriter(posFile);
                pw.println(position);
                pw.close();
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
