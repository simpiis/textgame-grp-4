package com.company;

import java.util.Scanner;

public class Map {

    public static void printMap(int position){
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
    public static int move(int position, Heroes hero, Rooms[] roomlist, Keyboard keyboard){
        Scanner input = new Scanner(System.in);
        Map.printMap(position);
        String direction = "0";
        int counter = 3;
        if (hero.getName().equals("Mage")){
            while (counter > hero.getMageCounter()){
                System.out.println("Do you want to use your special ability?");
                System.out.println("1. Yes \n2. No");
                String choice = input.nextLine();
                if (choice.equals("1")) {
                    position = magespecial(position);
                    Map.printMap(position);
                    int mage = hero.getMageCounter();
                    mage += 1;
                    hero.setMageCounter(mage);
                } else {
                    Map.printMap(position);
                    break;
                }
            }
        }
        if(position == 0){
            System.out.println("Choose where to move \n" + keyboard.getRight() + ". Right\n" + keyboard.getDown() + ". Down");
            while(direction.equals("0")) {
                direction = input.nextLine();
                if (direction.equals(keyboard.getRight())) {
                    if(roomlist[position].getDoor1()!= null){
                        System.out.println((((Doors) roomlist[position].getDoor1()).getTypeOfDoor()) + " Door");
                    }
                    position += 1;
                } else if (direction.equals(keyboard.getDown())){
                    if(roomlist[position].getDoor2()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()) + " Door");
                    }
                    position += 7;
                } else {
                    System.out.println("Not a valid option");
                    direction = "0";
                }
            }
        }else if(position == 6){
            System.out.println("Choose where to move \n" + keyboard.getLeft() + ". Left\n" + keyboard.getDown() + ". Down");
            while(direction.equals("0")) {
                direction = input.nextLine();
                if (direction.equals(keyboard.getLeft())) {
                    if(roomlist[position].getDoor1()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor1()).getTypeOfDoor()) + " Door");
                    }
                    position -= 1;
                } else if (direction.equals(keyboard.getDown())) {
                    if(roomlist[position].getDoor2()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()) + " Door");
                    }
                    position += 7;
                } else {
                    System.out.println("Not a valid option");
                    direction = "0";
                }
            }
        }else if(position == 42){
            System.out.println("Choose where to move \n" + keyboard.getRight() + ". Right\n" + keyboard.getUp() + ". Up");
            while(direction.equals("0")) {
                direction = input.nextLine();
                if (direction.equals(keyboard.getRight())) {
                    if(roomlist[position].getDoor1()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor1()).getTypeOfDoor()) + " Door");
                    }
                    position += 1;
                }else if (direction.equals(keyboard.getUp())) {
                    if(roomlist[position].getDoor2()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()) + " Door");
                    }
                    position -= 7;
                } else {
                    System.out.println("Not a valid option");
                    direction = "0";
                }
            }
        }else if(position == 48){
            System.out.println("Choose where to move \n" + keyboard.getLeft() + ". Left\n" + keyboard.getUp() + ". Up");
            while(direction.equals("0")) {
                direction = input.nextLine();
                if (direction.equals(keyboard.getLeft())) {
                    if(roomlist[position].getDoor1()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor1()).getTypeOfDoor()) + " Door");
                    }
                    position -= 1;
                } else if (direction.equals(keyboard.getUp())){
                    if(roomlist[position].getDoor2()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()) + " Door");
                    }
                    position -= 7;
                } else {
                    System.out.println("Not a valid option");
                    direction = "0";
                }
            }
        }else if(position > 0 && position < 42 && position%7 == 0){
            System.out.println("Choose where to move \n" + keyboard.getRight() + ". Right\n" + keyboard.getDown() + ". Down\n" + keyboard.getUp() + ". Up");
            while(direction.equals("0")) {
                direction = input.nextLine();
                if (direction.equals(keyboard.getRight())) {
                    if(roomlist[position].getDoor1()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor1()).getTypeOfDoor()) + " Door");
                    }
                    position += 1;
                }else if (direction.equals(keyboard.getUp())) {
                    if(roomlist[position].getDoor2()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()) + " Door");
                    }
                    position -= 7;
                }else if (direction.equals(keyboard.getDown())){
                    if(roomlist[position].getDoor3()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor3()).getTypeOfDoor()) + " Door");
                    }
                    position+=7;
                }else{
                    System.out.println("Not a valid option");
                    direction = "0";
                }
            }
        }else if(position > 0 && position < 6 ){
            System.out.println("Choose where to move \n" + keyboard.getLeft() + ". Left\n" + keyboard.getRight() + ". Right\n" + keyboard.getDown() + ". Down");
            while(direction.equals("0")) {
                direction = input.nextLine();
                if (direction.equals(keyboard.getRight())) {
                    if(roomlist[position].getDoor1()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor1()).getTypeOfDoor()) + " Door");
                    }
                    position += 1;
                } else if (direction.equals(keyboard.getLeft())) {
                    if(roomlist[position].getDoor2()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()) + " Door");
                    }
                    position -= 1;
                }else if(direction.equals(keyboard.getDown())){
                    if(roomlist[position].getDoor3()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor3()).getTypeOfDoor()) + " Door");
                    }
                    position +=7;
                }else {
                    System.out.println("Not a valid option");
                    direction = "0";
                }
            }
        }else if(position > 42 && position < 48){
            System.out.println("Choose where to move \n" + keyboard.getLeft() + ". Left\n" + keyboard.getRight() + ". Right\n" + keyboard.getUp() + ". Up");
            while(direction.equals("0")) {
                direction = input.nextLine();
                if (direction.equals(keyboard.getRight())) {
                    if(roomlist[position].getDoor1()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor1()).getTypeOfDoor()) + " Door");
                    }
                    position += 1;
                } else if (direction.equals(keyboard.getLeft())) {
                    if(roomlist[position].getDoor2()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()) + " Door");
                    }
                    position -= 1;
                }else if(direction.equals(keyboard.getUp())){
                    if(roomlist[position].getDoor3()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor3()).getTypeOfDoor()) + " Door");
                    }
                    position -=7;
                }else {
                    System.out.println("Not a valid option");
                    direction = "0";
                }
            }
        }else if(position < 48 && position > 6 && position % 7 == 6){
            System.out.println("Choose where to move \n" + keyboard.getLeft() + ". Left\n" + keyboard.getDown() + ". Down\n" + keyboard.getUp() + ". Up");
            while(direction.equals("0")) {
                direction = input.nextLine();
                if (direction.equals(keyboard.getLeft())) {
                    if(roomlist[position].getDoor1()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor1()).getTypeOfDoor()) + " Door");
                    }
                    position -= 1;
                } else if (direction.equals(keyboard.getUp())) {
                    if(roomlist[position].getDoor2()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()) + " Door");
                    }
                    position -= 7;
                }else if(direction.equals(keyboard.getDown())){
                    if(roomlist[position].getDoor3()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor3()).getTypeOfDoor()) + " Door");
                    }
                    position +=7;
                }else {
                    System.out.println("Not a valid option");
                    direction = "0";
                }
            }
        }else{
            System.out.println("Choose where to move \n" + keyboard.getLeft() + ". Left\n" + keyboard.getRight() + ". Right\n" + keyboard.getDown() + ". Down\n" + keyboard.getUp() + ". Up");
            while(direction.equals("0")) {
                direction = input.nextLine();
                if (direction.equals(keyboard.getRight())) {
                    if(roomlist[position].getDoor1()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor1()).getTypeOfDoor()) + " Door");
                    }
                    position += 1;
                } else if (direction.equals(keyboard.getLeft())) {
                    if(roomlist[position].getDoor2()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()) + " Door");
                    }
                    position -= 1;
                }else if(direction.equals(keyboard.getUp())){
                    if(roomlist[position].getDoor3()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor3()).getTypeOfDoor()) + " Door");
                    }
                    position -=7;
                }else if(direction.equals(keyboard.getDown())){
                    if(roomlist[position].getDoor4()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor4()).getTypeOfDoor()) + " Door");
                    }
                    position+=7;
                }else {
                    System.out.println("Not a valid option");
                    direction = "0";
                }
            }
        }
        return position;
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
