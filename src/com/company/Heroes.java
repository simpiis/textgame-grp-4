package com.company;

import java.util.Scanner;

public class Heroes extends Creature {
    private int mageCounter = 0;
    private int rogueMana;


    public Heroes(int hp, int damage, String name) {
        super(hp, damage, name);
    }
    public void printHero() {
        System.out.println("You choose "+ getName() + "!");
        System.out.print(getName() + "s hp is: ");
        System.out.println(getHp());
        System.out.print(getName() + "s damage is: ");
        System.out.println(getDamage());

    }
    public int getMageCounter() {
        return mageCounter;
    }

    public void setMageCounter(int mageCounter) {
        this.mageCounter = mageCounter;
    }

    public int getRogueMana() {
        return rogueMana;
    }

    public void setRogueMana(int rogueMana) {
        this.rogueMana = rogueMana;
    }
    public static int magespecial(int position) {
        Scanner input = new Scanner (System.in);
        System.out.println("You can move to: ");
        if (position >= 2 && position <= 46) {
            System.out.print( "1. 1 left\n2. 2 left\n3. 1 right \n4. 2 right\n");
            System.out.print("> ");
            String choice = input.nextLine();
            if (choice.equals("1")){
                position -= 1;
            } else if (choice.equals("2")) {
                position -= 2;
            } else if (choice.equals("3")) {
                position += 1;
            } else if (choice.equals("4")) {
                position += 2;
            } else {
                System.out.println("Invalid number");
            }
            System.out.println("You moved to room: " + position);

        } else if (position == 0){
            System.out.print( "1. 1 right \n2. 2 right\n");
            System.out.print("> ");
            String choice = input.nextLine();
            if (choice.equals("1")){
                position += 1;
            } else if (choice.equals("2")) {
                position += 2;
            } else {
                System.out.println("Invalid number");
            }
            System.out.println("You moved to room: " + position);
        }else if (position == 1){
            System.out.print( "1. 1 left\n2. 1 right\n3. 2 right\n");
            System.out.print("> ");
            String choice = input.nextLine();
            if (choice.equals("1")){
                position -= 1;
            } else if (choice.equals("2")) {
                position += 1;
            } else if (choice.equals("3")) {
                position += 2;
            } else {
                System.out.println("Invalid number");
            }
            System.out.println("You moved to room: " + position);
        } else if (position == 47){
            System.out.print( "1. 1 right\n2. 1 left \n3. 2 left\n");
            System.out.print("> ");
            String choice = input.nextLine();
            if (choice.equals("1")){
                position += 1;
            } else if (choice.equals("2")) {
                position -= 1;
            } else if (choice.equals("3")) {
                position -= 2;
            } else {
                System.out.println("Invalid number");
            }
            System.out.println("You moved to room: " + position);

        } else if (position == 48){
            System.out.print( "1. 1 left\n2. 2 left\n");
            System.out.print("> ");
            String choice = input.nextLine();
            if (choice.equals("1")){
                position -= 1;
            } else if (choice.equals("2")) {
                position -= 2;
            } else {
                System.out.println("Invalid number");
            }
            System.out.println("You moved to room: " + position);
        }
        return position;
    }
}

