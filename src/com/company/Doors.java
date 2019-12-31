package com.company;

import java.util.Random;

public class Doors {
    private String typeOfDoor;
    public Doors(String typeOfDoor){
        this.typeOfDoor = typeOfDoor;
    }

    public String getTypeOfDoor() {
        return typeOfDoor;
    }

    public static  Doors createDoor(){
        String[] doortypes = new String[]{"Wind", "Fire", "Ocean", "Dirt"};
        Random rand = new Random();
        int number = rand.nextInt(4);
        Doors thisdoor = new Doors(doortypes[number]);
        return thisdoor;
    }
}
