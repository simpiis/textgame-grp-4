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
    public static void openDoor(String doorname, int position, int position2, int firekeys, int oceankeys, int dirtkeys, int windkeys){
        if(doorname == "Ocean"){
            if(oceankeys > 0){
                oceankeys-=1;
                System.out.println("Door opened");
                position = position2;
            }else{
                System.out.println("You cant open that door");
            }
        }else if(doorname == "Wind"){
            if(windkeys > 0){
                windkeys-=1;
                System.out.println("Door opened");
                position = position2;
            }else{
                System.out.println("You cant open that door");
            }
        }else if(doorname == "Dirt"){
            if(dirtkeys > 0){
                dirtkeys-=1;
                System.out.println("Door opened");
                position = position2;
            }else{
                System.out.println("You cant open that door");
            }
        }else{
            if(firekeys > 0){
                firekeys-=1;
                System.out.println("Door opened");
                position = position2;
            }else{
                System.out.println("You cant open that door");
            }
        }
    }

    public static int move(int position, Heroes hero, Rooms[] roomlist, Keyboard keyboard, int firekeys, int oceankeys, int dirtkeys, int windkeys, ArrayList<Item> playerInventory, Creature wampa, Creature typhone, Creature minotaur, Score score){
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
                        Map.openDoor((((Doors) roomlist[position].getDoor1()).getTypeOfDoor()),position,position+1,firekeys,oceankeys,dirtkeys,windkeys);

                    }else{
                        position+=1;
                    }
                } else if (direction.equals(keyboard.getDown())){
                    if(roomlist[position].getDoor2()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()) + " Door");
                        Map.openDoor((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()),position,position+7,firekeys,oceankeys,dirtkeys,windkeys);
                    }else{
                        position+=7;
                    }
                } else if (direction.equals(keyboard.getOption())){
                    SubMenu.subMenu(keyboard, position,hero, playerInventory, wampa, typhone, minotaur, score);
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
                        Map.openDoor((((Doors) roomlist[position].getDoor1()).getTypeOfDoor()),position,position-1,firekeys,oceankeys,dirtkeys,windkeys);
                    }else{
                        position-=1;
                    }
                } else if (direction.equals(keyboard.getDown())) {
                    if(roomlist[position].getDoor2()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()) + " Door");
                        Map.openDoor((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()),position,position+7,firekeys,oceankeys,dirtkeys,windkeys);
                    }else{
                        position+=7;
                    }
                } else if (direction.equals(keyboard.getOption())){
                    SubMenu.subMenu(keyboard, position,hero, playerInventory, wampa, typhone, minotaur, score);
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
                        Map.openDoor((((Doors) roomlist[position].getDoor1()).getTypeOfDoor()),position,position+1,firekeys,oceankeys,dirtkeys,windkeys);
                    }else{
                        position+=1;
                    }
                }else if (direction.equals(keyboard.getUp())) {
                    if(roomlist[position].getDoor2()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()) + " Door");
                        Map.openDoor((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()),position,position-7,firekeys,oceankeys,dirtkeys,windkeys);
                    }else{
                        position-=7;
                    }
                } else if (direction.equals(keyboard.getOption())){
                    SubMenu.subMenu(keyboard, position,hero, playerInventory, wampa, typhone, minotaur, score);
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
                        Map.openDoor((((Doors) roomlist[position].getDoor1()).getTypeOfDoor()),position,position-1,firekeys,oceankeys,dirtkeys,windkeys);
                    }else{
                        position-=1;
                    }
                } else if (direction.equals(keyboard.getUp())){
                    if(roomlist[position].getDoor2()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()) + " Door");
                        Map.openDoor((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()),position,position-7,firekeys,oceankeys,dirtkeys,windkeys);
                    }else{
                        position-=7;
                    }
                } else if (direction.equals(keyboard.getOption())){
                    SubMenu.subMenu(keyboard, position,hero, playerInventory, wampa, typhone, minotaur, score);
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
                        Map.openDoor((((Doors) roomlist[position].getDoor1()).getTypeOfDoor()),position,position+1,firekeys,oceankeys,dirtkeys,windkeys);
                    }else{
                        position+=1;
                    }
                }else if (direction.equals(keyboard.getUp())) {
                    if(roomlist[position].getDoor2()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()) + " Door");
                        Map.openDoor((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()),position,position-7,firekeys,oceankeys,dirtkeys,windkeys);
                    }else{
                        position-=7;
                    }
                }else if (direction.equals(keyboard.getDown())){
                    if(roomlist[position].getDoor3()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor3()).getTypeOfDoor()) + " Door");
                        Map.openDoor((((Doors) roomlist[position].getDoor3()).getTypeOfDoor()),position,position+7,firekeys,oceankeys,dirtkeys,windkeys);
                    }else{
                        position+=7;
                    }
                } else if (direction.equals(keyboard.getOption())){
                    SubMenu.subMenu(keyboard, position,hero, playerInventory, wampa, typhone, minotaur, score);
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
                        Map.openDoor((((Doors) roomlist[position].getDoor1()).getTypeOfDoor()),position,position+1,firekeys,oceankeys,dirtkeys,windkeys);
                    }else{
                        position+=1;
                    }
                } else if (direction.equals(keyboard.getLeft())) {
                    if(roomlist[position].getDoor2()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()) + " Door");
                        Map.openDoor((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()),position,position-1,firekeys,oceankeys,dirtkeys,windkeys);
                    }else{
                        position-=1;
                    }
                }else if(direction.equals(keyboard.getDown())){
                    if(roomlist[position].getDoor3()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor3()).getTypeOfDoor()) + " Door");
                        Map.openDoor((((Doors) roomlist[position].getDoor3()).getTypeOfDoor()),position,position+7,firekeys,oceankeys,dirtkeys,windkeys);
                    }else{
                        position+=7;
                    }
                } else if (direction.equals(keyboard.getOption())){
                    SubMenu.subMenu(keyboard, position,hero, playerInventory, wampa, typhone, minotaur, score);
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
                        Map.openDoor((((Doors) roomlist[position].getDoor1()).getTypeOfDoor()),position,position+1,firekeys,oceankeys,dirtkeys,windkeys);
                    }else{
                        position+=1;
                    }
                } else if (direction.equals(keyboard.getLeft())) {
                    if(roomlist[position].getDoor2()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()) + " Door");
                        Map.openDoor((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()),position,position-1,firekeys,oceankeys,dirtkeys,windkeys);
                    }else{
                        position-=1;
                    }
                }else if(direction.equals(keyboard.getUp())){
                    if(roomlist[position].getDoor3()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor3()).getTypeOfDoor()) + " Door");
                        Map.openDoor((((Doors) roomlist[position].getDoor3()).getTypeOfDoor()),position,position-7,firekeys,oceankeys,dirtkeys,windkeys);
                    }else{
                        position-=7;
                    }
                }else if (direction.equals(keyboard.getOption())){
                    SubMenu.subMenu(keyboard, position,hero, playerInventory, wampa, typhone, minotaur, score);
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
                        Map.openDoor((((Doors) roomlist[position].getDoor1()).getTypeOfDoor()),position,position-1,firekeys,oceankeys,dirtkeys,windkeys);
                    }else{
                        position-=1;
                    }
                } else if (direction.equals(keyboard.getUp())) {
                    if(roomlist[position].getDoor2()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()) + " Door");
                        Map.openDoor((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()),position,position-7,firekeys,oceankeys,dirtkeys,windkeys);
                    }else{
                        position-=7;
                    }
                }else if(direction.equals(keyboard.getDown())){
                    if(roomlist[position].getDoor3()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor3()).getTypeOfDoor()) + " Door");
                        Map.openDoor((((Doors) roomlist[position].getDoor3()).getTypeOfDoor()),position,position+7,firekeys,oceankeys,dirtkeys,windkeys);
                    }else{
                        position+=7;
                    }
                }else if (direction.equals(keyboard.getOption())){
                    SubMenu.subMenu(keyboard, position,hero, playerInventory, wampa, typhone, minotaur, score);
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
                        Map.openDoor((((Doors) roomlist[position].getDoor1()).getTypeOfDoor()),position,position+1,firekeys,oceankeys,dirtkeys,windkeys);
                    }else{
                        position+=1;
                    }
                } else if (direction.equals(keyboard.getLeft())) {
                    if(roomlist[position].getDoor2()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()) + " Door");
                        Map.openDoor((((Doors) roomlist[position].getDoor2()).getTypeOfDoor()),position,position-1,firekeys,oceankeys,dirtkeys,windkeys);
                    }else{
                        position-=1;
                    }
                }else if(direction.equals(keyboard.getUp())){
                    if(roomlist[position].getDoor3()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor3()).getTypeOfDoor()) + " Door");
                        Map.openDoor((((Doors) roomlist[position].getDoor3()).getTypeOfDoor()),position,position-7,firekeys,oceankeys,dirtkeys,windkeys);
                    }else{
                        position-=7;
                    }
                }else if(direction.equals(keyboard.getDown())){
                    if(roomlist[position].getDoor4()!= null) {
                        System.out.println((((Doors) roomlist[position].getDoor4()).getTypeOfDoor()) + " Door");
                        Map.openDoor((((Doors) roomlist[position].getDoor4()).getTypeOfDoor()),position,position+7,firekeys,oceankeys,dirtkeys,windkeys);
                    }else{
                        position+=7;
                    }
                }else if (direction.equals(keyboard.getOption())){
                    SubMenu.subMenu(keyboard, position,hero, playerInventory, wampa, typhone, minotaur, score);
                }else {
                    System.out.println("Not a valid option");
                    direction = "0";
                }
            }
        }
        return position;
    }
}

