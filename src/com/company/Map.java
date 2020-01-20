package com.company;

import java.util.ArrayList;
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
    public static int openDoor(String doorname, int position, int position2, Keys key, Treasure chest1, Treasure chest2, Treasure chest3){
        Scanner input = new Scanner(System.in);
        if(doorname == "Ocean"){
            if(key.getOceankeys() > 0){
                key.setOceankeys(key.getOceankeys()-1);
                System.out.println("Door opened");
                System.out.println("You have " + key.getOceankeys()+  " ocean keys left");
                return position2;
            }else{
                System.out.println("********************************");
                System.out.println("You need a key to open that door");
                System.out.println("********************************");
                return position;
            }
        }else if(doorname == "Wind"){
            if(key.getWindkeys() > 0){
                key.setWindkeys(key.getWindkeys()-1);
                System.out.println("Door opened");
                System.out.println("You have " + key.getWindkeys() +  " wind keys left");
                return position2;
            }else{
                System.out.println("********************************");
                System.out.println("You need a key to open that door");
                System.out.println("********************************");
                return position;
            }
        }else if(doorname == "Dirt"){
            if(key.getDirtkeys() > 0){
                key.setDirtkeys(key.getDirtkeys()-1);
                System.out.println("Door opened");
                System.out.println("You have " + key.getDirtkeys() +  " dirt keys left");
                return position2;
            }else{
                System.out.println("********************************");
                System.out.println("You need a key to open that door");
                System.out.println("********************************");
                return position;
            }
        }else{
            if(key.getFirekeys() > 0){
                key.setFirekeys(key.getFirekeys()-1);
                System.out.println("Door opened");
                System.out.println("You have " + key.getFirekeys() +  " fire keys left");
                return position2;
            }else{
                System.out.println("********************************");
                System.out.println("You need a key to open that door");
                System.out.println("********************************");
                return position;
            }
        }
    }


    public static int move(int position, Heroes hero, Rooms[] roomlist, Keyboard keyboard, ArrayList<Item> playerInventory, Keys key, Monster wampa, Monster typhone, Monster minotaur, Score score, int round, Treasure chest1, Treasure chest2, Treasure chest3){
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
                    position = hero.magespecial(position);
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
            System.out.println("Choose where to move \n" + keyboard.getRight() + ". Right\n" + keyboard.getDown() + ". Down\n" + keyboard.getOption() + ". Options");
            while(direction.equals("0")) {
                direction = input.nextLine();
                if (direction.equals(keyboard.getRight())) {
                    if(roomlist[position].getDoor1()!= null){
                        System.out.println((((Doors) roomlist[position].getDoor1()).getTypeOfDoor()) + " Door");
                        position = Map.openDoor((((Doors) roomlist[position].getDoor1()).getTypeOfDoor()),position,position+1, key, chest1, chest2, chest3);
                    }else{
                        position+=1;
                    }
                } else if (direction.equals(keyboard.getDown())){
                    if(roomlist[position].getDoor2()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()) + " Door");
                        position = Map.openDoor((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()),position,position+7,key, chest1, chest2, chest3);
                    }else{
                        position+=7;
                    }
                } else if (direction.equals(keyboard.getOption())){
                    SubMenu.subMenu(keyboard, position,hero, playerInventory, wampa, typhone, minotaur, score, round, chest1, chest2, chest3,roomlist);
                } else {
                    System.out.println("Not a valid option");
                    direction = "0";
                }
            }
        }else if(position == 6){
            System.out.println("Choose where to move \n" + keyboard.getLeft() + ". Left\n" + keyboard.getDown() + ". Down\n" + keyboard.getOption() + ". Options");
            while(direction.equals("0")) {
                direction = input.nextLine();
                if (direction.equals(keyboard.getLeft())) {
                    if(roomlist[position].getDoor1()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor1()).getTypeOfDoor()) + " Door");
                        position = Map.openDoor((((Doors) roomlist[position].getDoor1()).getTypeOfDoor()),position,position-1,key, chest1, chest2, chest3);
                    }else{
                        position-=1;
                    }
                } else if (direction.equals(keyboard.getDown())) {
                    if(roomlist[position].getDoor2()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()) + " Door");
                        position = Map.openDoor((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()),position,position+7,key, chest1, chest2, chest3);
                    }else{
                        position+=7;
                    }
                } else if (direction.equals(keyboard.getOption())){
                    SubMenu.subMenu(keyboard, position,hero, playerInventory, wampa, typhone, minotaur, score, round, chest1, chest2, chest3, roomlist);
                }else {
                    System.out.println("Not a valid option");
                    direction = "0";
                }
            }
        }else if(position == 42){
            System.out.println("Choose where to move \n" + keyboard.getRight() + ". Right\n" + keyboard.getUp() + ". Up\n" + keyboard.getOption() + ". Options");
            while(direction.equals("0")) {
                direction = input.nextLine();
                if (direction.equals(keyboard.getRight())) {
                    if(roomlist[position].getDoor1()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor1()).getTypeOfDoor()) + " Door");
                        position = Map.openDoor((((Doors) roomlist[position].getDoor1()).getTypeOfDoor()),position,position+1,key, chest1, chest2, chest3);
                    }else{
                        position+=1;
                    }
                }else if (direction.equals(keyboard.getUp())) {
                    if(roomlist[position].getDoor2()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()) + " Door");
                        position = Map.openDoor((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()),position,position-7,key, chest1, chest2, chest3);
                    }else{
                        position-=7;
                    }
                } else if (direction.equals(keyboard.getOption())){
                    SubMenu.subMenu(keyboard, position,hero, playerInventory, wampa, typhone, minotaur, score, round, chest1, chest2, chest3, roomlist);
                }else {
                    System.out.println("Not a valid option");
                    direction = "0";
                }
            }
        }else if(position == 48){
            System.out.println("Choose where to move \n" + keyboard.getLeft() + ". Left\n" + keyboard.getUp() + ". Up\n" + keyboard.getOption() + ". Options");
            while(direction.equals("0")) {
                direction = input.nextLine();
                if (direction.equals(keyboard.getLeft())) {
                    if(roomlist[position].getDoor1()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor1()).getTypeOfDoor()) + " Door");
                        position = Map.openDoor((((Doors) roomlist[position].getDoor1()).getTypeOfDoor()),position,position-1,key, chest1, chest2, chest3);
                    }else{
                        position-=1;
                    }
                } else if (direction.equals(keyboard.getUp())){
                    if(roomlist[position].getDoor2()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()) + " Door");
                        position = Map.openDoor((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()),position,position-7,key, chest1, chest2, chest3);
                    }else{
                        position-=7;
                    }
                } else if (direction.equals(keyboard.getOption())){
                    SubMenu.subMenu(keyboard, position,hero, playerInventory, wampa, typhone, minotaur, score, round, chest1, chest2, chest3, roomlist);
                }else {
                    System.out.println("Not a valid option");
                    direction = "0";
                }
            }
        }else if(position > 0 && position < 42 && position%7 == 0){
            System.out.println("Choose where to move \n" + keyboard.getRight() + ". Right\n" + keyboard.getDown() + ". Down\n" + keyboard.getUp() + ". Up\n" + keyboard.getOption() + ". Options");
            while(direction.equals("0")) {
                direction = input.nextLine();
                if (direction.equals(keyboard.getRight())) {
                    if(roomlist[position].getDoor1()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor1()).getTypeOfDoor()) + " Door");
                        position = Map.openDoor((((Doors) roomlist[position].getDoor1()).getTypeOfDoor()),position,position+1,key, chest1, chest2, chest3);
                    }else{
                        position+=1;
                    }
                }else if (direction.equals(keyboard.getUp())) {
                    if(roomlist[position].getDoor2()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()) + " Door");
                        position = Map.openDoor((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()),position,position-7,key, chest1, chest2, chest3);
                    }else{
                        position-=7;
                    }
                }else if (direction.equals(keyboard.getDown())){
                    if(roomlist[position].getDoor3()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor3()).getTypeOfDoor()) + " Door");
                        position = Map.openDoor((((Doors) roomlist[position].getDoor3()).getTypeOfDoor()),position,position+7,key, chest1, chest2, chest3);
                    }else{
                        position+=7;
                    }
                } else if (direction.equals(keyboard.getOption())){
                    SubMenu.subMenu(keyboard, position,hero, playerInventory, wampa, typhone, minotaur, score, round,chest1, chest2, chest3, roomlist);
                } else{
                    System.out.println("Not a valid option");
                    direction = "0";
                }
            }
        }else if(position > 0 && position < 6 ){
            System.out.println("Choose where to move \n" + keyboard.getLeft() + ". Left\n" + keyboard.getRight() + ". Right\n" + keyboard.getDown() + ". Down\n" + keyboard.getOption() + ". Options");
            while(direction.equals("0")) {
                direction = input.nextLine();
                if (direction.equals(keyboard.getRight())) {
                    if(roomlist[position].getDoor1()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor1()).getTypeOfDoor()) + " Door");
                        position = Map.openDoor((((Doors) roomlist[position].getDoor1()).getTypeOfDoor()),position,position+1,key, chest1, chest2, chest3);
                    }else{
                        position+=1;
                    }
                } else if (direction.equals(keyboard.getLeft())) {
                    if(roomlist[position].getDoor2()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()) + " Door");
                        position = Map.openDoor((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()),position,position-1,key, chest1, chest2, chest3);
                    }else{
                        position-=1;
                    }
                }else if(direction.equals(keyboard.getDown())){
                    if(roomlist[position].getDoor3()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor3()).getTypeOfDoor()) + " Door");
                        position = Map.openDoor((((Doors) roomlist[position].getDoor3()).getTypeOfDoor()),position,position+7,key, chest1, chest2, chest3);
                    }else{
                        position+=7;
                    }
                } else if (direction.equals(keyboard.getOption())){
                    SubMenu.subMenu(keyboard, position,hero, playerInventory, wampa, typhone, minotaur, score, round, chest1, chest2, chest3, roomlist);
                } else {
                    System.out.println("Not a valid option");
                    direction = "0";
                }
            }
        }else if(position > 42 && position < 48){
            System.out.println("Choose where to move \n" + keyboard.getLeft() + ". Left\n" + keyboard.getRight() + ". Right\n" + keyboard.getUp() + ". Up\n" + keyboard.getOption() + ". Options");
            while(direction.equals("0")) {
                direction = input.nextLine();
                if (direction.equals(keyboard.getRight())) {
                    if(roomlist[position].getDoor1()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor1()).getTypeOfDoor()) + " Door");
                        position = Map.openDoor((((Doors) roomlist[position].getDoor1()).getTypeOfDoor()),position,position+1,key, chest1, chest2, chest3);
                    }else{
                        position+=1;
                    }
                } else if (direction.equals(keyboard.getLeft())) {
                    if(roomlist[position].getDoor2()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()) + " Door");
                        position = Map.openDoor((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()),position,position-1,key, chest1, chest2, chest3);
                    }else{
                        position-=1;
                    }
                }else if(direction.equals(keyboard.getUp())){
                    if(roomlist[position].getDoor3()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor3()).getTypeOfDoor()) + " Door");
                        position = Map.openDoor((((Doors) roomlist[position].getDoor3()).getTypeOfDoor()),position,position-7,key, chest1, chest2, chest3);
                    }else{
                        position-=7;
                    }
                }else if (direction.equals(keyboard.getOption())){
                    SubMenu.subMenu(keyboard, position,hero, playerInventory, wampa, typhone, minotaur, score, round, chest1, chest2, chest3, roomlist);
                }else {
                    System.out.println("Not a valid option");
                    direction = "0";
                }
            }
        }else if(position < 48 && position > 6 && position % 7 == 6){
            System.out.println("Choose where to move \n" + keyboard.getLeft() + ". Left\n" + keyboard.getDown() + ". Down\n" + keyboard.getUp() + ". Up\n" + keyboard.getOption() + ". Options");
            while(direction.equals("0")) {
                direction = input.nextLine();
                if (direction.equals(keyboard.getLeft())) {
                    if(roomlist[position].getDoor1()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor1()).getTypeOfDoor()) + " Door");
                        position = Map.openDoor((((Doors) roomlist[position].getDoor1()).getTypeOfDoor()),position,position-1,key, chest1, chest2, chest3);
                    }else{
                        position-=1;
                    }
                } else if (direction.equals(keyboard.getUp())) {
                    if(roomlist[position].getDoor2()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()) + " Door");
                        position = Map.openDoor((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()),position,position-7,key, chest1, chest2, chest3);
                    }else{
                        position-=7;
                    }
                }else if(direction.equals(keyboard.getDown())){
                    if(roomlist[position].getDoor3()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor3()).getTypeOfDoor()) + " Door");
                        position = Map.openDoor((((Doors) roomlist[position].getDoor3()).getTypeOfDoor()),position,position+7,key, chest1, chest2, chest3);
                    }else{
                        position+=7;
                    }
                }else if (direction.equals(keyboard.getOption())){
                    SubMenu.subMenu(keyboard, position,hero, playerInventory, wampa, typhone, minotaur, score, round, chest1, chest2, chest3, roomlist);
                }else {
                    System.out.println("Not a valid option");
                    direction = "0";
                }
            }
        }else{
            System.out.println("Choose where to move \n" + keyboard.getLeft() + ". Left\n" + keyboard.getRight() + ". Right\n" + keyboard.getDown() + ". Down\n" + keyboard.getUp() + ". Up\n" + keyboard.getOption() + ". Options");
            while(direction.equals("0")) {
                direction = input.nextLine();
                if (direction.equals(keyboard.getRight())) {
                    if(roomlist[position].getDoor1()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor1()).getTypeOfDoor()) + " Door");
                        position = Map.openDoor((((Doors) roomlist[position].getDoor1()).getTypeOfDoor()),position,position+1,key, chest1, chest2, chest3);
                    }else{
                        position+=1;
                    }
                } else if (direction.equals(keyboard.getLeft())) {
                    if(roomlist[position].getDoor2()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()) + " Door");
                        position = Map.openDoor((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()),position,position-1,key, chest1, chest2, chest3);
                    }else{
                        position-=1;
                    }
                }else if(direction.equals(keyboard.getUp())){
                    if(roomlist[position].getDoor3()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor3()).getTypeOfDoor()) + " Door");
                        position = Map.openDoor((((Doors) roomlist[position].getDoor3()).getTypeOfDoor()),position,position-7,key, chest1, chest2, chest3);
                    }else{
                        position-=7;
                    }
                }else if(direction.equals(keyboard.getDown())){
                    if(roomlist[position].getDoor4()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor4()).getTypeOfDoor()) + " Door");
                        position = Map.openDoor((((Doors) roomlist[position].getDoor4()).getTypeOfDoor()),position,position+7,key, chest1, chest2, chest3);
                    }else{
                        position+=7;
                    }
                }else if (direction.equals(keyboard.getOption())){
                    SubMenu.subMenu(keyboard, position,hero, playerInventory, wampa, typhone, minotaur, score, round, chest1, chest2, chest3, roomlist);
                }else {
                    System.out.println("Not a valid option");
                    direction = "0";
                }
            }
        }
        return position;
    }
}

