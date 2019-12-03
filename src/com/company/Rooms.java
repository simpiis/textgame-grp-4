package com.company;

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
}
