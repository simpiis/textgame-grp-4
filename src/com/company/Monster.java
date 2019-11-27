package com.company;

public class Monster extends Creature{
    private String name;
    public Monster(int hp, int damage, String name) {
        super(hp, damage);
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
