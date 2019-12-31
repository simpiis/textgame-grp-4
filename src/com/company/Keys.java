package com.company;

public class Keys extends Item {
    private int uses;

    public Keys(String name, int uses){
        super (name);
        this.uses = uses;
    }
    public static Keys createKey(){
        Keys key = new Keys("Rusty old key",1);
        return key;
    }
}
