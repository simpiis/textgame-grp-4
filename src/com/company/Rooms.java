package com.company;

import java.util.Random;

public class Rooms {

    private Doors door1;
    private Doors door2;
    private Doors door3;
    private Doors door4;
    private Treasure treasure;
    private Monster monster;

    public Rooms (Doors door1,Doors door2,Doors door3,Doors door4, Treasure treasure, Monster monster){
        this.door1 = door1;
        this.door2 = door2;
        this.door3 = door3;
        this.door4 = door4;
        this.treasure = treasure;
        this.monster = monster;
    }

    public Doors getDoor1() {
        return door1;
    }

    public Doors getDoor2() {
        return door2;
    }

    public Doors getDoor3() {
        return door3;
    }

    public Doors getDoor4() {
        return door4;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public Treasure getTreasure() {
        return treasure;
    }

    public void setTreasure(Treasure treasure) {
        this.treasure = treasure;
    }
    public static  Rooms[] createRooms(Monster monster1, Monster monster2,Monster monster3, Treasure chest1, Treasure chest2, Treasure chest3, int wampaPos, int typhonePos, int minotaurPos){
       int monsterroom1 = 0;
       int monsterroom2 = 0;
       int monsterroom3 = 0;
        Rooms[] roomlist = new Rooms[49];
        Random rand = new Random();

        if (minotaurPos == 0) {
            monsterroom1 = rand.nextInt(49);  // minotaur
            if (monsterroom1 == 0) {
                monsterroom1 += 1;
            }
        } else {
            monsterroom1 = minotaurPos;
        }
        if (typhonePos == 0) {
            monsterroom2 = rand.nextInt(49); // typhone
            if (monsterroom2 == 0) {
                monsterroom2 += 1;
            }
        } else {
            monsterroom2 = typhonePos;
        }
        if (wampaPos == 0) {
            monsterroom3 = rand.nextInt(49); // wampa
            if (monsterroom3 == 0) {
                monsterroom3 += 1;
            }
        } else {
            monsterroom3 = wampaPos;
        }

        while (monsterroom1 == monsterroom2 || monsterroom1 == monsterroom3 || monsterroom2 == monsterroom3){
            monsterroom2 = rand.nextInt(49);
            monsterroom3 = rand.nextInt(49);
        }
        int chestroom1 = rand.nextInt(49);
        int chestroom2 = rand.nextInt(49);
        int chestroom3 = rand.nextInt(49);
        while(monsterroom1 == chestroom1 || monsterroom2 == chestroom1 || monsterroom3 == chestroom1 ) {
            chestroom1 = rand.nextInt(49);
        }
        while(monsterroom1 == chestroom2 || monsterroom2 == chestroom2 || monsterroom3 == chestroom2 || chestroom1 == chestroom2 ) {
            chestroom2 = rand.nextInt(49);
        }
        while(monsterroom1 == chestroom3 || monsterroom2 == chestroom3 || monsterroom3 == chestroom3 || chestroom1 == chestroom3 || chestroom2 == chestroom3 ) {
            chestroom3 = rand.nextInt(49);
        }
        for(int p = 0; p < 49; p++){
            Rooms room1;
            if(p == 0 || p== 6 || p == 42 ||p == 48){
                int choice = rand.nextInt(2);
                if(choice == 0){
                    room1 = new Rooms(createDoor(), null, null, null, null, null);
                }else{
                    room1 = new Rooms(null, createDoor(), null, null, null, null);
                }

            }else if(p < 48 && p > 6 && p % 7 == 6 || p > 42 && p < 48 || p > 0 && p < 6 || p > 0 && p < 42 && p%7 == 0){
                int choice = rand.nextInt(3);
                if(choice == 0){
                    room1 = new Rooms(createDoor(), createDoor(), null, null, null, null);
                }else if(choice == 1){
                    room1 = new Rooms(null, createDoor(), createDoor(), null, null, null);
                }else{
                    room1 = new Rooms(createDoor(), null, createDoor(), null, null, null);
                }
            }else{
                int choice = rand.nextInt(4);
                if(choice == 0){
                    room1 = new Rooms(createDoor(), createDoor(), createDoor(), null, null, null);
                }else if(choice == 1){
                    room1 = new Rooms(null, createDoor(), createDoor(), createDoor(), null, null);
                }else if (choice ==2){
                    room1 = new Rooms(createDoor(), null, createDoor(), createDoor(), null, null);
                }else{
                    room1 = new Rooms(createDoor(), createDoor(), null, createDoor(), null, null);
                }
            }
            roomlist[p] = room1;
        }
        if(monster1.getHp()!=0) {
            roomlist[monsterroom1].setMonster(monster1);
        }
        if(monster2.getHp()!=0) {
            roomlist[monsterroom2].setMonster(monster2);
        }
        if(monster3.getHp()!=0) {
            roomlist[monsterroom3].setMonster(monster3);
        }
        roomlist[chestroom1].setTreasure(chest1);
        roomlist[chestroom2].setTreasure(chest2);
        roomlist[chestroom3].setTreasure(chest3);
        return roomlist;
    }
    public static Doors createDoor(){
        String[] doortypes = new String[]{"Wind", "Fire", "Ocean", "Dirt"};
        Random rand = new Random();
        int number = rand.nextInt(4);
        Doors thisdoor = new Doors(doortypes[number]);
        return thisdoor;
    }
}
